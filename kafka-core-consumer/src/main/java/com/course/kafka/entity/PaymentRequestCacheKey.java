package com.course.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentRequestCacheKey {

  private String paymentNumber;
  private Integer amount;
  private String transactionType;

}
