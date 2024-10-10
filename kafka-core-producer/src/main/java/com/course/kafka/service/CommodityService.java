package com.course.kafka.service;

import com.course.kafka.entity.Commodity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CommodityService {

  private static final Map<String, Commodity> COMMODITY_BASE = new HashMap<>();
  private static final String COPPER = "copper";
  private static final String GOLD = "gold";
  private static final double MIN_ADJUSTMENT = 0.95;
  private static final double MAX_ADJUSTMENT = 1.05;

  static {
	var timestamp = System.currentTimeMillis();

	COMMODITY_BASE.put(COPPER, new Commodity(COPPER, 10_000, "tonne", timestamp));
	COMMODITY_BASE.put(GOLD, new Commodity(GOLD, 2500, "ounce", timestamp));
  }

  public Commodity generateDummyCommodity(String name) {
	if (!COMMODITY_BASE.containsKey(name)) {
	  throw new IllegalArgumentException("Invalid commodity name: " + name);
	}
	var baseCommodity = COMMODITY_BASE.get(name);
	double basePrice = baseCommodity.getPrice();

	double adjustment = Math.random() * (MAX_ADJUSTMENT - MIN_ADJUSTMENT) + MIN_ADJUSTMENT;
	double adjustedPrice = basePrice * adjustment;
	var timestamp = System.currentTimeMillis();

	return new Commodity(name, adjustedPrice, baseCommodity.getMeasurement(), timestamp);
  }


  public List<Commodity> generateDummyCommodities() {
	return COMMODITY_BASE.keySet().stream().map(this::generateDummyCommodity).toList();
  }

}
