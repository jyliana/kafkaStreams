package com.course.kafka.consumer;

import com.course.kafka.entity.SimpleNumber;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SimpleNumberConsumer {

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-simple-number")
  public void consume(String message) throws JsonProcessingException {
	var simpleNumber = objectMapper.readValue(message, SimpleNumber.class);
	if (simpleNumber.getNumber() % 2 != 0) {
	  throw new IllegalArgumentException("Odd number : " + simpleNumber.getNumber());
	}

	log.info("Processing simple number : {}", simpleNumber);
  }

}
