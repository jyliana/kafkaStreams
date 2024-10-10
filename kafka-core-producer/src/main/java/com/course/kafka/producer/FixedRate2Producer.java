package com.course.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
//@Service
public class FixedRate2Producer {
  private static final String TOPIC = "t-fixedrate-2";
  private int counter = 0;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Scheduled(fixedRate = 1000)
  public void sendMessage() {
	counter++;
	String message = "Message " + counter;
	kafkaTemplate.send(TOPIC, message);
	log.info("Sent message: {} to topic: {}", message, TOPIC);
  }

}
