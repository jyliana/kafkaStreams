package com.course.kafka.consumer;

import com.course.kafka.entity.PaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
//@Service
public class PaymentRequestConsumer {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  @Qualifier("cachePaymentRequest")
  private Cache<String, Boolean> cachePaymentRequest;

  private boolean isExistsInCache(String key) {
	return cachePaymentRequest.getIfPresent(key) != null;
  }

  @KafkaListener(topics = "t-payment-request")
  public void consumePaymentRequest(String message) {
	try {
	  var paymentRequest = objectMapper.readValue(message, PaymentRequest.class);
	  var cacheKey = paymentRequest.calculateHash();

	  if (isExistsInCache(cacheKey)) {
		log.warn("Payment request already exists in cache: {}", paymentRequest);
		return;
	  }

	  log.info("Processing payment request: {}", paymentRequest);
	  cachePaymentRequest.put(cacheKey, true);
	} catch (Exception e) {
	  log.error("Error processing purchase request", e);
	}
  }

}
