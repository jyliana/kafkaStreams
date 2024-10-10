package com.course.kafka.producer;

import com.course.kafka.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmployeeJson2Producer {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public void sendMessage(Employee employee) {
    // convert the employee to JSON string and publish it to topic t-employee
    try {
      var json = objectMapper.writeValueAsString(employee);
      kafkaTemplate.send("t-employee-2", json);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
