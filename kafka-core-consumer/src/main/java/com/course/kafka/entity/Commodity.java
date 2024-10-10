package com.course.kafka.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Commodity {

  private String name;
  private double price;
  private String measurement;
  private long timestamp;

  public Commodity(String name, double price, String measurement, long timestamp) {
	this.name = name;
	this.setPrice(price);
	this.measurement = measurement;
	this.timestamp = timestamp;
  }

  public void setPrice(double price) {
	this.price = Math.round(price * 100.0) / 100.0;
  }

}
