package com.course.kafka.producer;

import com.course.kafka.entity.Image;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ImageProducer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void sendImage(Image image, int partition) {
	try {
	  var json = objectMapper.writeValueAsString(image);

	  kafkaTemplate.send("t-image", partition, image.getType(), json);
	  log.info("Image sent: {} to partition: {}", image, partition);
	} catch (Exception e) {
	  throw new RuntimeException(e);
	}
  }
}
