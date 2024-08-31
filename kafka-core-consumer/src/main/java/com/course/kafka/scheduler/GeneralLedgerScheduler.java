package com.course.kafka.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GeneralLedgerScheduler {

  @Autowired
  private KafkaListenerEndpointRegistry registry;

  @Scheduled(cron = "0 31 18 * * ?")
  public void stop() {
	log.info("Stopping consumer");
	registry.getListenerContainer("general-ledger.one").pause();
  }

  @Scheduled(cron = "1 33 18 * * ?")
  public void start() {
	log.info("Starting consumer");
	registry.getListenerContainer("general-ledger.one").resume();
  }

}
