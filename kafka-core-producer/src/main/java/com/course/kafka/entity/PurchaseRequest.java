package com.course.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {

  private UUID requestId;
  private String requestNumber;
  private int amount;
  private String currency;

}
