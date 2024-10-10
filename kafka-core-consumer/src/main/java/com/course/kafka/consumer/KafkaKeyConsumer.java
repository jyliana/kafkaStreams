package com.course.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
//@Service
public class KafkaKeyConsumer {

  @KafkaListener(topics = "t-multi-partitions", concurrency = "4")
  public void consume(ConsumerRecord<String, String> record) throws InterruptedException {
	log.info("Partition: {}, Key: {}, Value: {}", record.partition(), record.key(), record.value());
	TimeUnit.SECONDS.sleep(1);
  }

}
