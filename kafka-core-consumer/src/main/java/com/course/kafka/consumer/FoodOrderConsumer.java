package com.course.kafka.consumer;

import com.course.kafka.entity.FoodOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FoodOrderConsumer {

  private static final Integer MAX_ORDER_AMOUNT = 7;
  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-food-order", errorHandler = "myFoodOrderErrorHandler")
  public void consume(String message) throws JsonProcessingException {
	var foodOrder = objectMapper.readValue(message, FoodOrder.class);
	if (foodOrder.getAmount() > MAX_ORDER_AMOUNT) {
	  throw new IllegalArgumentException("Order amount is too many : " + foodOrder.getAmount());
	}

	log.info("Processing food order : {}", foodOrder);
  }

}
