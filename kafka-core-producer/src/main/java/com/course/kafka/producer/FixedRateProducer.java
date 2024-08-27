package com.course.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class FixedRateProducer {
  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  private final AtomicInteger counter = new AtomicInteger();

  @Scheduled(fixedRate = 1000)
  public void sendMessage() {
	var i = counter.incrementAndGet();
	log.info("i is: {}", i);
	kafkaTemplate.send("t-fixedrate", "Fixed rate " + i);
  }

}
