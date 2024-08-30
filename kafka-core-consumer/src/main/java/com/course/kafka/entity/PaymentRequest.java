package com.course.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

  private String paymentNumber;
  private Integer amount;
  private String currency;
  private String notes;
  private String transactionType;

}
