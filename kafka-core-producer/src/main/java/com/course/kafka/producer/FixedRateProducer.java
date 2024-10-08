package com.course.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
//@Service
public class FixedRateProducer {
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  private int i = 0;

  @Scheduled(fixedRate = 1000)
  public void sendMessage() {
	i++;
	log.info("i is now: {}", i);
	kafkaTemplate.send("t-fixedrate", "Fixed rate message " + i);
  }

}
