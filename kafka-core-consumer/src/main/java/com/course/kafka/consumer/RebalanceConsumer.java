package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RebalanceConsumer {

  @KafkaListener(topics = "t-rebalance", concurrency = "2")
  public void consume(ConsumerRecord<String, String> consumerRecord) {
	log.info("Partition : {}, Offset : {}, Message : {}", consumerRecord.partition(), consumerRecord.offset(), consumerRecord.value());
  }

}


