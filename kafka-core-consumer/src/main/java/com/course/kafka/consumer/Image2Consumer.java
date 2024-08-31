package com.course.kafka.consumer;

import com.course.kafka.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Image2Consumer {

  @Autowired
  private ObjectMapper objectMapper;

  @RetryableTopic(autoCreateTopics = "true", attempts = "4",
		  topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE,
		  backoff = @Backoff(delay = 3000, maxDelay = 10_000, multiplier = 1.5, random = true),
		  dltTopicSuffix = "-dead"
  )
  @KafkaListener(topics = "t-image-2", concurrency = "2")
  public void consume(ConsumerRecord<String, String> record) throws JsonProcessingException {
	var image = objectMapper.readValue(record.value(), Image.class);
	if (image.getType().equalsIgnoreCase("svg")) {
	  log.warn("Throwing exception on partition {} for image : {}", record.partition(), image);
	  throw new IllegalArgumentException("Simulate API call failed");
	}

	log.info("Processing on partition {} for image {}", record.partition(), image);
  }

}
