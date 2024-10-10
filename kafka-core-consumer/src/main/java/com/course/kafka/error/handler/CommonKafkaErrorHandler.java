package com.course.kafka.error.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;

@Slf4j
//@Component
public class CommonKafkaErrorHandler implements CommonErrorHandler {

  @Override
  public boolean handleOne(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer, MessageListenerContainer container) {
	log.error("handleOne() for : {}", record.value());

	return true;
  }

  @Override
  public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
	log.error("handleOtherException() for : {}", thrownException.getMessage());
  }

}
