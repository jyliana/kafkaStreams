package com.course.kafka.config;

import com.course.kafka.entity.CarLocation;
import com.course.kafka.error.handler.GlobalErrorHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {

  @Autowired
  private KafkaProperties kafkaProperties;

  @Autowired
  private ObjectMapper objectMapper;

  @Bean
  public ConsumerFactory<Object, Object> consumerFactory() {
	var properties = kafkaProperties.buildConsumerProperties();
	properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");

	return new DefaultKafkaConsumerFactory<>(properties);
  }

  @Bean(name = "farLocationContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<Object, Object> farLocationContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
	var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
	configurer.configure(factory, consumerFactory());

	factory.setRecordFilterStrategy(new RecordFilterStrategy<Object, Object>() {
	  @Override
	  public boolean filter(ConsumerRecord<Object, Object> consumerRecord) {
		try {
		  var carLocation = objectMapper.readValue(consumerRecord.value().toString(), CarLocation.class);
		  return carLocation.getDistance() <= 100;
		} catch (JsonProcessingException e) {
		  return false;
		}
	  }
	});
	return factory;
  }

  @Bean(name = "kafkaListenerContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
	var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
	configurer.configure(factory, consumerFactory());

	factory.setCommonErrorHandler(new GlobalErrorHandler());

	return factory;
  }

  @Bean(name = "imageRetryContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<Object, Object> imageRetryContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
	var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
	configurer.configure(factory, consumerFactory());

	factory.setCommonErrorHandler(new DefaultErrorHandler(new FixedBackOff(10000, 3)));

	return factory;
  }

  @Bean(name = "invoiceDltContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<Object, Object> invoiceDltContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
																							KafkaTemplate<String, String> kafkaTemplate) {

	var factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
	configurer.configure(factory, consumerFactory());

	var recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate, (record, ex) ->
			new TopicPartition("t-invoice-dead", record.partition()));

	factory.setCommonErrorHandler(new DefaultErrorHandler(recoverer, new FixedBackOff(3000, 5)));

	return factory;
  }

}
