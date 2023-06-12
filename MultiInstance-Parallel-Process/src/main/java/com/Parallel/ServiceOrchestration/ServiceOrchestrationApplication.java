package com.Parallel.ServiceOrchestration;

import java.util.HashMap;
import java.util.Map;

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

public class ServiceOrchestrationApplication {
	final RestTemplate rest= new RestTemplate();

	public static void main(String[] args) {
		SpringApplication.run(ServiceOrchestrationApplication.class, args);
	}
	
	
	
	
}
