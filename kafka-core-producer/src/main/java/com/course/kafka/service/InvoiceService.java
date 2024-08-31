package com.course.kafka.service;

import com.course.kafka.entity.Invoice;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class InvoiceService {

  private final AtomicInteger counter = new AtomicInteger();

  public Invoice generateInvoice() {
	var invoiceNumber = "INV-" + counter.incrementAndGet();
	var amount = ThreadLocalRandom.current().nextInt(1, 100);

	return new Invoice(invoiceNumber, amount, "USD");
  }
}
