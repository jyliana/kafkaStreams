package com.course.kafka.consumer;

import com.course.kafka.entity.FoodOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
//@Service
public class FoodOrderConsumer {
  private static final int MAX_AMOUNT_ORDER = 7;

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-food-order", errorHandler = "myFoodOrderErrorHandler")
  public void consume(String message) throws JsonProcessingException {
	var foodOrder = objectMapper.readValue(message, FoodOrder.class);

	if (foodOrder.getAmount() > MAX_AMOUNT_ORDER) {
	  log.error("Amount {} exceeds the maximum allowed amount of {}: ", foodOrder.getAmount(), MAX_AMOUNT_ORDER);
	  throw new IllegalArgumentException("Food order amount is too high");
	}
	log.info("Consumed food order: {}", foodOrder);
  }

}
