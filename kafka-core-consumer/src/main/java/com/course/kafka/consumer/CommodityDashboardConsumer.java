package com.course.kafka.consumer;

import com.course.kafka.entity.Commodity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Slf4j
//@Service
public class CommodityDashboardConsumer {

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-commodity", groupId = "consumer-group-dashboard")
  public void consume(String message) {
	try {
	  var commodity = objectMapper.readValue(message, Commodity.class);

	  TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5, 10));

	  log.info("Dashboard consumer: {}", commodity);
	} catch (Exception e) {
	  log.error("Error processing commodity message", e);
	}
  }

}
