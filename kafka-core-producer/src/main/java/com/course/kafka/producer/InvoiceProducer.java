package com.course.kafka.producer;

import com.course.kafka.entity.Invoice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void sendInvoice(Invoice invoice) {
	try {
	  var json = objectMapper.writeValueAsString(invoice);
	  kafkaTemplate.send("t-invoice", (int) invoice.getAmount() % 2, invoice.getInvoiceNumber(), json);
	} catch (Exception e) {
	  throw new RuntimeException(e);
	}
  }

}
