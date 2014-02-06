package com.tomson.mywebapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculateService {
	private final Logger logger = LoggerFactory.getLogger(CalculateService.class);
	
	public int add(int a, int b){
		//for logback demo
		logger.info("call add method [info level]");		
		logger.debug("call add method [debug level]");	
		
		return a + b;
	}
}
