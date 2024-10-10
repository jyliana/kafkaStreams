package com.course.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class HelloKafkaAIConsumer {

  @KafkaListener(topics = "t-hello")
  public void listen(String message) {
	System.out.println("Received message: " + message);
  }

}
