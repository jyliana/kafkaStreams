package com.course.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class FixedRateConsumer {

  @KafkaListener(topics = "t-fixedrate")
  public void consume(String message) {
	System.out.println("Consuming message: " + message);
  }

}
