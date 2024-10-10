package com.course.kafka.consumer;

import com.course.kafka.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@Slf4j
//@Service
public class ImageConsumer {

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-image", concurrency = "2", containerFactory = "imageRetryContainerFactory")
  public void consume(String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition) throws JsonProcessingException {
	var image = objectMapper.readValue(message, Image.class);

	if ("svg".equalsIgnoreCase(image.getType())) {
	  throw new IllegalArgumentException("SVG images are not allowed");
	}

	log.info("Consumed message: {} from partition: {}", message, partition);
  }

}
