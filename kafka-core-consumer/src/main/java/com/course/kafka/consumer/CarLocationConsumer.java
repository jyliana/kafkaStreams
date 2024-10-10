package com.course.kafka.consumer;

import com.course.kafka.entity.CarLocation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
//@Service
public class CarLocationConsumer {

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-location", groupId = "group-location-all")
  public void listenAll(String message) throws JsonProcessingException {
	var carLocation = objectMapper.readValue(message, CarLocation.class);
	log.info("listenAll() : {}", carLocation);
  }

  @KafkaListener(topics = "t-location", groupId = "group-location-far", containerFactory = "locationFarContainerFactory")
  public void listenFar(String message) throws JsonProcessingException {
	var carLocation = objectMapper.readValue(message, CarLocation.class);
	log.info("listenFar() : {}", carLocation);
  }

}
