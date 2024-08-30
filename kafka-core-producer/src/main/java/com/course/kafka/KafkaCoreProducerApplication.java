package com.course.kafka;

import com.course.kafka.entity.PaymentRequest;
import com.course.kafka.producer.PaymentRequestProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaCoreProducerApplication implements CommandLineRunner {

  @Autowired
  private PaymentRequestProducer producer;

  public static void main(String[] args) {
	SpringApplication.run(KafkaCoreProducerApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
	var paymentRequest_AlphaTransaction1 = new PaymentRequest("Pay-Alpha", 551, "USD", "Notes alpha", "Budget reserve");
	var paymentRequest_AlphaTransaction2 = new PaymentRequest("Pay-Alpha", 551, "USD", "Notes alpha", "Approval workflow");
	var paymentRequest_AlphaTransaction3 = new PaymentRequest("Pay-Alpha", 551, "USD", "Notes alpha", "Push notification");

	var paymentRequest_BetaTransaction1 = new PaymentRequest("Pay-Beta", 552, "USD", "Notes beta", "Budget reserve");
	var paymentRequest_BetaTransaction2 = new PaymentRequest("Pay-Beta", 552, "USD", "Notes beta", "Approval workflow");
	var paymentRequest_BetaTransaction3 = new PaymentRequest("Pay-Beta", 552, "USD", "Notes beta", "Push notification");

	producer.send(paymentRequest_AlphaTransaction1);
	producer.send(paymentRequest_AlphaTransaction2);
	producer.send(paymentRequest_AlphaTransaction3);
	producer.send(paymentRequest_BetaTransaction1);
	producer.send(paymentRequest_BetaTransaction2);
	producer.send(paymentRequest_BetaTransaction3);

	producer.send(paymentRequest_AlphaTransaction1);
	producer.send(paymentRequest_BetaTransaction1);
  }
}
