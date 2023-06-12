package com.kafkaExample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafkaExample.kafkaservice.ServiceImplementation;

@RestController

public class HelloController {
	@Autowired
	ServiceImplementation serviceimpl;
	
	@Autowired
	private KafkaTemplate<String, String> kafkatemplate;
	
	@GetMapping("/{message}")
	public String publish(@PathVariable("message") String publishMessage) {
		kafkatemplate.send("helloTopic", publishMessage);
		System.out.println("posted msg "+publishMessage);
		return "Message Published :" +publishMessage;
		
		
	}

}
