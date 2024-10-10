package com.course.kafka.producer;

import com.course.kafka.entity.CarLocation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CarLocationProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void sendCarLocation(CarLocation carLocation) {
	try {
	  String json = objectMapper.writeValueAsString(carLocation);
	  kafkaTemplate.send("t-location", carLocation.getId(), json);
	} catch (Exception e) {
	  e.printStackTrace();
	}
  }

}
