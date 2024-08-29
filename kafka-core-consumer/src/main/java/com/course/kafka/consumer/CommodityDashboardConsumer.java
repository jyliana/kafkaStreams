package com.course.kafka.consumer;

import com.course.kafka.entity.Commodity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommodityDashboardConsumer {
  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-commodity", groupId = "cg-dashboard")
  public void consume(String message) throws JsonProcessingException, InterruptedException {
	var commodity = objectMapper.readValue(message, Commodity.class);

//	var randomDelayMillis= ThreadLocalRandom.current().nextLong(500, 2000);
//	TimeUnit.MILLISECONDS.sleep(randomDelayMillis);

	log.info("Dashboard logic for : {}", commodity);
  }

}
