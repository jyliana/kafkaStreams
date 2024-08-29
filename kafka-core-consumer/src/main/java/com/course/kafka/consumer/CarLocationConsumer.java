package com.course.kafka.consumer;

import com.course.kafka.entity.CarLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CarLocationConsumer {

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-location", groupId = "cg-all-location")
  public void listenAll(String message) throws JsonProcessingException {
	var carLocation = objectMapper.readValue(message, CarLocation.class);
	log.info("listenAll : {}", carLocation);
  }

  @KafkaListener(topics = "t-location", groupId = "cg-far-location", containerFactory = "farLocationContainerFactory")
  public void listenFar(String message) throws JsonProcessingException {
	var carLocation = objectMapper.readValue(message, CarLocation.class);
	log.info("listenFar : {}", carLocation);
  }

}
