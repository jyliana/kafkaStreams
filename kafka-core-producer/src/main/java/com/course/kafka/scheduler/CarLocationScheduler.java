package com.course.kafka.scheduler;


import com.course.kafka.entity.CarLocation;
import com.course.kafka.producer.CarLocationProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
//@Service
public class CarLocationScheduler {

  private final CarLocation car1;
  private final CarLocation car2;
  private final CarLocation car3;

  @Autowired
  private CarLocationProducer producer;

  public CarLocationScheduler() {
	var now = System.currentTimeMillis();

	car1 = new CarLocation("car-one", now, 0);
	car2 = new CarLocation("car-two", now, 110);
	car3 = new CarLocation("car-three", now, 95);
  }

//  @Scheduled(fixedRate = 10000)
  public void generateDummyData() throws JsonProcessingException {
	var now = System.currentTimeMillis();

	car1.setTimestamp(now);
	car2.setTimestamp(now);
	car3.setTimestamp(now);

	car1.setDistance(car1.getDistance() + 1);
	car2.setDistance(car2.getDistance() - 1);
	car3.setDistance(car3.getDistance() + 1);

	sendCarLocation(car1);
	sendCarLocation(car2);
	sendCarLocation(car3);

  }

  private void sendCarLocation(CarLocation carLocation) {
	producer.sendCarLocation(carLocation);
	log.info("Sent car location: {}", carLocation);
  }

}