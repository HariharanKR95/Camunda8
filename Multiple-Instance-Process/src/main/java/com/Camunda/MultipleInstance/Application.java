package com.Camunda.MultipleInstance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import io.camunda.zeebe.spring.client.EnableZeebeClient;

@SpringBootApplication
@EnableZeebeClient

public class Application {
	final RestTemplate rest = new RestTemplate();

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

}