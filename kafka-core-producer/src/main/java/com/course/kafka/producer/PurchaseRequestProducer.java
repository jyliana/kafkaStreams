package com.course.kafka.producer;

import com.course.kafka.entity.PurchaseRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PurchaseRequestProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void send(PurchaseRequest purchaseRequest) throws JsonProcessingException {
	var json = objectMapper.writeValueAsString(purchaseRequest);
	kafkaTemplate.send("t-purchase-request", purchaseRequest.getPrNumber(), json);
	log.info("Purchase request send : {}", purchaseRequest);
  }

}
