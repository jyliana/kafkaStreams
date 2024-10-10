package com.course.kafka.consumer;

import com.course.kafka.entity.SimpleNumber;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
//@Service
public class SimpleNumberConsumer {

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-simple-number")
  public void consume(String message) throws JsonProcessingException {
	var simpleNumber = objectMapper.readValue(message, SimpleNumber.class);
	int number = simpleNumber.getNumber();
	if (number % 2 != 0) {
	  throw new IllegalArgumentException("Number is odd: " + number);
	}
	log.info("Number is even: {}", message);
  }

}
