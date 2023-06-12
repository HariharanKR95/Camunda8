package com.wedbush.camunda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.spring.client.EnableZeebeClient;

@SpringBootApplication
@EnableZeebeClient
public class CamundaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaApplication.class, args);
	}

}
