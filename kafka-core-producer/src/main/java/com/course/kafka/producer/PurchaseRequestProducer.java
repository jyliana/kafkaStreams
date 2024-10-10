package com.course.kafka.producer;

import com.course.kafka.entity.PurchaseRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PurchaseRequestProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void sendPurchaseRequest(PurchaseRequest purchaseRequest) {
	try {
	  var json = objectMapper.writeValueAsString(purchaseRequest);
	  kafkaTemplate.send("t-purchase-request", purchaseRequest.getRequestNumber(), json);
	} catch (Exception e) {
	  e.printStackTrace();
	}
  }

}
