package com.course.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class CounterProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  public void sendMessage(int number) {
	for (int i = 0; i < number; i++) {
	  var message = "Data " + i;
	  kafkaTemplate.send("t-counter", message);
	}
  }

}
