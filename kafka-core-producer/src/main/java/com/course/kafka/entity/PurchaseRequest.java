package com.course.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {

  private Integer id;
  private String prNumber;
  private Integer amount;
  private String currency;
  private LocalDateTime date;

}
