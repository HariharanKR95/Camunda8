package com.example.camundademo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
@EnableZeebeClient

public class KafkaDemoApplication {
	final RestTemplate rest= new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}
	
	
		
		

}
