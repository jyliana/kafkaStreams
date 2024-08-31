package com.course.kafka;

import com.course.kafka.producer.Image2Producer;
import com.course.kafka.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaCoreProducerApplication implements CommandLineRunner {

  @Autowired
  private Image2Producer producer;

  @Autowired
  private ImageService service;

  public static void main(String[] args) {
	SpringApplication.run(KafkaCoreProducerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
//	var image1 = service.generateImage("jpg");
//	var image2 = service.generateImage("svg");
//	var image3 = service.generateImage("jpg");
//	var image4 = service.generateImage("jpg");
//	var image5 = service.generateImage("jpg");
//	var image6 = service.generateImage("jpg");
//
//	producer.send(image1, 0);
//	producer.send(image2, 0);
//	producer.send(image3, 0);
//	producer.send(image4, 1);
//	producer.send(image5, 1);
//	producer.send(image6, 1);
  }

}
