package com.course.kafka.consumer;

import com.course.kafka.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class InvoiceConsumer {

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-invoice", concurrency = "2", containerFactory = "invoiceDltContainerFactory")
  public void consume(String message) throws JsonProcessingException {
	var invoice = objectMapper.readValue(message, Invoice.class);

	if (invoice.getAmount() < 1) {
	  throw new IllegalArgumentException("Invalid amount for " + invoice);
	}

	log.info("Processing invoice : {}", invoice);
  }

}
