package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.camunda.zeebe.spring.client.EnableZeebeClient;

@SpringBootApplication
@EnableZeebeClient
public class UpdatedserviceorchestrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(UpdatedserviceorchestrationApplication.class, args);
	}
	@Bean
    RestTemplate resttemplate() {
        return new RestTemplate();
    }

}
