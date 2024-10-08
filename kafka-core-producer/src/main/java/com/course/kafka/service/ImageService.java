package com.course.kafka.service;

import com.course.kafka.entity.Image;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ImageService {
  private static final AtomicInteger COUNTER = new AtomicInteger();

  public Image generateImage(String type) {
	var name = "image-" + COUNTER.getAndIncrement();
	var size = ThreadLocalRandom.current().nextLong(100, 10_000);

	return new Image(name, size, type);
  }

}
