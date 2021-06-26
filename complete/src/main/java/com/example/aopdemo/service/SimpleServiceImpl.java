package com.example.aopdemo.service;

import org.springframework.stereotype.Service;

@Service
public class SimpleServiceImpl implements SimpleService {

	@Override
	public String demo() {
		 
		return "This is a example!";
	}

}
