package com.tomson.mywebapp.service.impl;

import org.springframework.stereotype.Service;

import com.tomson.mywebapp.service.WelcomeService;

@Service
//@Transactional(readOnly = true)
public class WelcomeServiceImpl implements WelcomeService {
	public String getGreeting(String userName) {
		return "Hello, " + userName;
	}
}
