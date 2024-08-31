package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GeneralLedgerConsumer {

  @KafkaListener(id = "general-ledger.one", topics = "t-general-ledger")
  public void consumeOne(String message) {
	log.info("From consumerOne : {}", message);
  }

  @KafkaListener(topics = "t-general-ledger")
  public void consumeTwo(String message) {
	log.info("From consumerTwo : {}", message);
  }
}
