package com.course.kafka.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
//@Service
public class GeneralLedgerScheduler {

  @Autowired
  private KafkaListenerEndpointRegistry registry;

  @Scheduled(cron = "0 14 1 * * *")
  public void pause() {
	log.info("Pausing listener");
	registry.getListenerContainer("consumer-ledger-one").pause();
  }

  @Scheduled(cron = "1 16 1 * * *")
  public void resume() {
	log.info("Resuming listener");
	registry.getListenerContainer("consumer-ledger-one").resume();
  }
}
