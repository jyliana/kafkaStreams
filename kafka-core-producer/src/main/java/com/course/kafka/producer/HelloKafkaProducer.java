package com.course.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;

public class HelloKafkaProducer {

  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendHello(String name) {
	kafkaTemplate.send("t-hello", "Hello " + name);
  }

}
