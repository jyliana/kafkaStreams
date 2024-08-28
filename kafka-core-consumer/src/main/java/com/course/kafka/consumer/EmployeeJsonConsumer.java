package com.course.kafka.consumer;

import com.course.kafka.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeJsonConsumer {

  @Autowired
  private ObjectMapper objectMapper;

  @KafkaListener(topics = "t-employee")
  public void listen(String message) throws JsonProcessingException {
	var employee = objectMapper.readValue(message, Employee.class);
	log.info("Employee is : {}", employee);

  }

}
