package com.course.kafka.api;

import com.course.kafka.entity.Commodity;
import com.course.kafka.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commodity/v1")
public class CommodityApi {

  @Autowired
  private CommodityService service;

  @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Commodity> generateAllCommodities() {
	return service.generateDummyCommodities();
  }

}
