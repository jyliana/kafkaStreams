package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaKeyConsumer {

  @KafkaListener(topics = "t-multi-partitions", concurrency = "3")
  public void consume(ConsumerRecord<String, String> consumerRecord) throws InterruptedException {
	log.info("Key : {}, Partition : {}, Message : {}", consumerRecord.key(),
			consumerRecord.partition(), consumerRecord.value());
//	TimeUnit.SECONDS.sleep(1);
  }
}
