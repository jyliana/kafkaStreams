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
  public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
	log.warn("FoodOrder error, sending to ElasticSearch (example) : {}, because: {}",
			message.getPayload(), exception.getMessage());

	if (exception.getCause() instanceof RuntimeException) {
	  throw exception;
	}

	return null;
  }

}
