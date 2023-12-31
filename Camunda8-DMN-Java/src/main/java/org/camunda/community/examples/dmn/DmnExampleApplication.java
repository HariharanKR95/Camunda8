package org.camunda.community.examples.dmn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.spring.client.EnableZeebeClient;

@SpringBootApplication
@EnableZeebeClient
public class DmnExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DmnExampleApplication.class, args);
	}

}
