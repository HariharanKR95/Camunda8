package com.multiinstance.Parallel.Worker;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
public class MultiInstanceWorker {
	
	@Value("${multi-instance-example.number-of-buckets}")
	  public long numberOfBuckets;
	  
	  @Value("${multi-instance-example.number-of-elements}")
	  public long numberOfElements;
	@Autowired
	ZeebeClient zeebeClient;

	Logger logger = LoggerFactory.getLogger(MultiInstanceWorker.class);
	final RestTemplate rest = new RestTemplate();

	@ZeebeWorker(type = "bucketCreation", name = "bucketCreation")
	public void Task1(final JobClient client, final ActivatedJob job) {

		logger.info("bucketCreation Started");
		Map<String, Object> variables = job.getVariablesAsMap();
		logger.info("Handling start bucket execution with vars{}", variables);
	    String bucketId = (String) variables.get("bucketId");
	    String businessKey = (String) variables.get("businessKey");
	    logger.info("Working on bucket {}", bucketId);
	  zeebeClient
	            .newPublishMessageCommand()
	            .messageName("StartMessage")
	            .correlationKey("1234")
	            .variables(Map.of(
	                "bucketId", bucketId,
	                "businessKey", businessKey))
	            .send()
	            .join();
	        
	      }
	

	// Worker2
	@ZeebeWorker(type = "demo", name = "demo")
	public void Task2(final JobClient client, final ActivatedJob job) {

		logger.info("Worker2 Started");
		
		Map<String, Object> variablemap = new HashMap<>();
		variablemap.put("102", "Naveen");
		variablemap.put("103", "Rajesh");

		System.out.println("Worker2:" + variablemap);

		zeebeClient.newCompleteCommand(job.getKey()).variables(variablemap).send().join();
		logger.info("Worker2 Ended");

	}


}
