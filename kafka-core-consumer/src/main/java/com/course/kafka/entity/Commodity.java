package com.course.kafka.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
public class Commodity {

  private String name;
  private Double price;
  private String measurement;
  private Long timestamp;

  public Commodity(String name, Double price, String measurement, Long timestamp) {
	this.name = name;
	this.setPrice(price);
	this.measurement = measurement;
	this.timestamp = timestamp;
  }

  public void setPrice(Double price) {
	this.price = Math.round(price * 100d) / 100d;
  }
}
