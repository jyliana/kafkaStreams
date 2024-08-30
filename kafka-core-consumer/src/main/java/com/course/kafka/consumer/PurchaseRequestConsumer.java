package com.course.kafka.consumer;

import com.course.kafka.entity.PurchaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.benmanes.caffeine.cache.Cache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PurchaseRequestConsumer {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  @Qualifier("cachePurchaseRequest")
  private Cache<Integer, Boolean> cache;

  private boolean isExistsInCache(Integer purchaseRequestId) {
	return Optional.ofNullable(cache.getIfPresent(purchaseRequestId)).orElse(false);
  }

  @KafkaListener(topics = "t-purchase-request")
  public void consume(String message) throws JsonProcessingException {
	var purchaseRequest = objectMapper.readValue(message, PurchaseRequest.class);

	var processed = isExistsInCache(purchaseRequest.getId());

	if (!processed) {
	  log.info("Processing {}", purchaseRequest);
	  cache.put(purchaseRequest.getId(), true);
	}
  }

}
