package com.example.TasklistClientApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.spring.client.EnableZeebeClient;


@EnableZeebeClient
@SpringBootApplication

public class ZeeqsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZeeqsApplication.class, args);
	}

}
