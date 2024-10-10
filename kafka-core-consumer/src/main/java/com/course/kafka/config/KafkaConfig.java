package com.course.kafka.config;

import com.course.kafka.entity.CarLocation;
import com.course.kafka.entity.PaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.ssl.SslBundles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

  @Autowired
  private KafkaProperties kafkaProperties;

  @Bean
  public ConsumerFactory<Object, Object> consumerFactory(SslBundles sslBundles) {
	var properties = kafkaProperties.buildConsumerProperties(sslBundles);
	properties.put(ConsumerConfig.METRICS_SAMPLE_WINDOW_MS_CONFIG, "40000");

	return new DefaultKafkaConsumerFactory<>(properties);
  }

  @Bean(name = "locationFarContainerFactory")
  ConcurrentKafkaListenerContainerFactory<Object, Object> locationFarContainerFactory(
		  ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
		  SslBundles sslBundles,
		  ObjectMapper objectMapper
  ) {
	var factory = new ConcurrentKafkaListenerContainerFactory<>();
	configurer.configure(factory, consumerFactory(sslBundles));

	factory.setRecordFilterStrategy(consumerRecord -> {
	  try {
		var carLocation = objectMapper.readValue(consumerRecord.value().toString(), CarLocation.class);
		return carLocation.getDistance() < 100;
	  } catch (Exception e) {
		return false;
	  }
	});

	return factory;
  }

  @Bean(name = "paymentRequestContainerFactory")
  ConcurrentKafkaListenerContainerFactory<Object, Object> paymentRequestContainerFactory(
		  ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
		  SslBundles sslBundles,
		  ObjectMapper objectMapper,
		  @Qualifier("cachePaymentRequest") Cache<String, Boolean> cachePaymentRequest
  ) {
	var factory = new ConcurrentKafkaListenerContainerFactory<>();
	configurer.configure(factory, consumerFactory(sslBundles));

	factory.setRecordFilterStrategy(consumerRecord -> {
	  try {
		var paymentRequest = objectMapper.readValue(consumerRecord.value().toString(), PaymentRequest.class);
		var cacheKey = paymentRequest.calculateHash();

		return cachePaymentRequest.getIfPresent(cacheKey) != null;
	  } catch (Exception e) {
		return false;
	  }
	});

	return factory;
  }

  @Bean(name = "imageRetryContainerFactory")
  ConcurrentKafkaListenerContainerFactory<Object, Object> imageRetryContainerFactory(
		  ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
		  SslBundles sslBundles
  ) {
	var factory = new ConcurrentKafkaListenerContainerFactory<>();
	configurer.configure(factory, consumerFactory(sslBundles));

	factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(10_000, 3)));

	return factory;
  }

  @Bean(name = "invoiceDltContainerFactory")
  ConcurrentKafkaListenerContainerFactory<Object, Object> invoiceDltContainerFactory(
		  ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
		  SslBundles sslBundles,
		  KafkaTemplate<String, String> kafkaTemplate
  ) {
	var factory = new ConcurrentKafkaListenerContainerFactory<>();
	configurer.configure(factory, consumerFactory(sslBundles));

	var recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate, (record, ex) ->
			new TopicPartition("t-invoice-dead", record.partition()));

	factory.setCommonErrorHandler(new DefaultErrorHandler(recoverer, new FixedBackOff(3000, 5)));

	return factory;
  }

}
