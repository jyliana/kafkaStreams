package com.course.kafka.consumer;

import com.course.kafka.entity.PurchaseRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
//@Service
public class PurchaseRequestConsumer {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  @Qualifier("cachePurchaseRequest")
  private Cache<String, Boolean> cachePurchaseRequest;

  private boolean isExistsInCache(String requestNumber) {
	return cachePurchaseRequest.getIfPresent(requestNumber) != null;
  }

  @KafkaListener(topics = "t-purchase-request")
  public void consumePurchaseRequest(String message) {
	try {
	  var purchaseRequest = objectMapper.readValue(message, PurchaseRequest.class);

	  if (isExistsInCache(purchaseRequest.getRequestNumber())) {
		log.warn("Purchase request already exists in cache: {}", purchaseRequest.getRequestNumber());
		return;
	  }

	  log.info("Processing purchase request: {}", purchaseRequest.getRequestNumber());
	  cachePurchaseRequest.put(purchaseRequest.getRequestNumber(), true);
	} catch (Exception e) {
	  log.error("Error processing purchase request", e);
	}
  }

}
