package com.course.kafka.producer;

import com.course.kafka.entity.PaymentRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentRequestProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void send(PaymentRequest paymentRequest) throws JsonProcessingException {
	var json = objectMapper.writeValueAsString(paymentRequest);
	kafkaTemplate.send("t-payment-request", paymentRequest.getPaymentNumber(), json);
	log.info("Payment request send : {}", paymentRequest);
  }

}
