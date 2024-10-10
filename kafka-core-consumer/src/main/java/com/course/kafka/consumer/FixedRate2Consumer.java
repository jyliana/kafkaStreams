package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
//@Service
public class FixedRate2Consumer {

  @KafkaListener(topics = "t-fixedrate-2")
  public void consume(String message) {
	log.info("Consuming message: {}", message);
  }
}
