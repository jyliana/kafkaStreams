package com.course.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

  private int amount;
  private String currency;
  private String bankAccountNumber;
  private String notes;
  private LocalDate paymentDate;

}
