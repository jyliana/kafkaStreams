package com.course.kafka.scheduler;

import com.course.kafka.entity.Commodity;
import com.course.kafka.producer.CommodityProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class CommodityScheduler {

  private static final String COMMODITY_API_URL = "http://localhost:8080/api/commodity/v1/all";
  private final RestTemplate restTemplate = new RestTemplate();

  @Autowired
  private CommodityProducer producer;

  @Scheduled(fixedRate = 5000)
  public void fetchCommodities() {
	var commodities = restTemplate.getForObject(COMMODITY_API_URL, Commodity[].class);
	if (commodities != null) {
	  Arrays.stream(commodities).forEach(producer::sendMessage);
	}
  }

}
