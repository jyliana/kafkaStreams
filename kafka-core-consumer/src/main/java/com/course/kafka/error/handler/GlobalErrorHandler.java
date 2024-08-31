package com.course.kafka.error.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GlobalErrorHandler implements CommonErrorHandler {

  @Override
  public boolean handleOne(Exception e, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
	log.warn("Global error handling for message : {}, because of : {}, {}", record.value(), e.getMessage(), e.getCause().getMessage());

	return true;
  }

}
