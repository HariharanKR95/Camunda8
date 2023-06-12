package com.camunda.TAT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.spring.client.EnableZeebeClient;

@SpringBootApplication
@EnableZeebeClient
public class QwertyITApplication {

	public static void main(String[] args) {
		SpringApplication.run(QwertyITApplication.class, args);
	}

}
