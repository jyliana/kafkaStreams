package com.course.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
public class RebalanceConsumer {

  @KafkaListener(topics = {"t-rebalance-alpha", "t-rebalance-beta"}, groupId = "group-rebalance", concurrency = "3")
  public void consume(String message) throws InterruptedException {
	TimeUnit.MILLISECONDS.sleep(5);
  }

}
