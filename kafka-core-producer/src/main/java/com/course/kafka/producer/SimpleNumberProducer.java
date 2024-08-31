package com.course.kafka.producer;

import com.course.kafka.entity.SimpleNumber;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleNumberProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void send(SimpleNumber simpleNumber) throws JsonProcessingException {
	var json = objectMapper.writeValueAsString(simpleNumber);

	kafkaTemplate.send("t-simple-number", json);
	log.info("Sending simple number : {}", json);
  }

}
