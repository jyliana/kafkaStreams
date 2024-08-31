package com.course.kafka.producer;

import com.course.kafka.entity.FoodOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FoodOrderProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void send(FoodOrder foodOrder) throws JsonProcessingException {
	var json = objectMapper.writeValueAsString(foodOrder);

	kafkaTemplate.send("t-food-order", json);
	log.info("Sending order : {}", json);
  }

}
