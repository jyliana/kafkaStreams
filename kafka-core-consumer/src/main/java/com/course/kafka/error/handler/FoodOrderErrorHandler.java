package com.course.kafka.error.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

@Slf4j
@Service(value = "myFoodOrderErrorHandler")
public class FoodOrderErrorHandler implements ConsumerAwareListenerErrorHandler {


  @Override
  public Object handleError(Message<?> message, ListenerExecutionFailedException e, Consumer<?, ?> consumer) {
	log.warn("Food order error, sending to ElasticSearch : {}, because : {}", message.getPayload(), e.getCause().getMessage());

	if (e.getCause() instanceof RuntimeException) {
	  throw e;
	}

	return null;
  }
}
