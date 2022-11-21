package com.pru.offboarding.associate.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping(value = "/pru-associate")
public class CircuitBreakerController {


	@Autowired
	private RestTemplate restTemplate;
	
	
	@GetMapping(value = "/sample-api")
	@Retry(name = "sample-api", fallbackMethod = "invokeFallbackMethod")
	public String sampleApi() {
		
		ResponseEntity<String> str = 
				restTemplate.getForEntity("http://localhost:8888/pru-dept/get-dept/", String.class);
		
		return str.getBody();
	}
	
	public String invokeFallbackMethod(Exception ex) {
		return "Service Error : "+ex.getMessage();
	}
}
