package com.course.kafka.producer;

import com.course.kafka.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InvoiceProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void send(Invoice invoice) throws JsonProcessingException {
	var json = objectMapper.writeValueAsString(invoice);
	var partition = invoice.getAmount() % 2;

	kafkaTemplate.send("t-invoice", partition, invoice.getInvoiceNumber(), json);
	log.info("Sending invoice {} to partition {}", json, partition);
  }

}
