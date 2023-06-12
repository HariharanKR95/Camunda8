package com.Camunda.StartandCancelInstance.Worker;

import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
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
public class ParallelWorker {
	
	  

//	  @Autowired
//	  ZeebeClient zeebeClient;

	  

	  final RestTemplate rest = new RestTemplate();
	
	
	  @ZeebeWorker(type = "service", name = "service")
		public void Task1(final JobClient client, final ActivatedJob job) 
		{
			//System.out.println("Flow Started");
			
			
			System.out.println("Worker1 Started");
			
		
			client.newCompleteCommand(job.getKey()).send().join();
			System.out.println("woker1 ended");
				
			}
		
//	    client.newCancelInstanceCommand().send();
//	    System.out.println("Cancel");
			

 
}
