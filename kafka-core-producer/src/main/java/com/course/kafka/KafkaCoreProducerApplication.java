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
	var image1 = service.generateImage("JPG");
	var image2 = service.generateImage("SVG");
	var image3 = service.generateImage("PNG");
	var image4 = service.generateImage("GIF");
	var image5 = service.generateImage("BMP");
	var image6 = service.generateImage("TIFF");

	producer.sendImage(image1, 0);
	producer.sendImage(image2, 0);
	producer.sendImage(image3, 0);

	producer.sendImage(image4, 1);
	producer.sendImage(image5, 1);
	producer.sendImage(image6, 1);


  }

}
