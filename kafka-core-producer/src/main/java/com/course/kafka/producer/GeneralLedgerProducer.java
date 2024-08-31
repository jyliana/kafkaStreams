package com.course.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
@Slf4j
public class GeneralLedgerProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;
  private AtomicInteger counter = new AtomicInteger();

  @Autowired
  private ObjectMapper objectMapper;

  public void send(String message) {
	kafkaTemplate.send("t-general-ledger", message);
	log.info("Sending message : {} ", message);
  }

//  @Scheduled(fixedRate = 1000)
  public void schedule() {
	var message = "Ledger " + counter.incrementAndGet();
	send(message);
  }

}
