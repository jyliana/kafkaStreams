package com.course.kafka.producer;

import com.course.kafka.entity.PaymentRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PaymentRequestProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void sendPaymentRequest(PaymentRequest paymentRequest) {
	try {
	  var json = objectMapper.writeValueAsString(paymentRequest);
	  kafkaTemplate.send("t-payment-request", json);
	} catch (Exception e) {
	  e.printStackTrace();
	}
  }

}
