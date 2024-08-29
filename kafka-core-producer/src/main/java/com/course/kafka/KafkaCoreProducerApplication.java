package com.course.kafka;

import com.course.kafka.producer.EmployeeJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaCoreProducerApplication implements CommandLineRunner {

  @Autowired
  private EmployeeJsonProducer producer;

  public static void main(String[] args) {
	SpringApplication.run(KafkaCoreProducerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
//	for (int i = 0; i < 5; i++) {
//	  var emp = new Employee("emp-" + i, "Employee " + i, LocalDate.now());
//	  producer.sendMessage(emp);
//	}
  }
}
