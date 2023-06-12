package com.camunda.dmn.worker;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
public class DmnWorker {
	
	@Autowired
	ZeebeClient zeebeclient;
	
	@ZeebeWorker(type = "demo")
public void pensionEligibility(final JobClient client, final ActivatedJob job) {
		
		System.out.println("worker started ");
		
		Map<String, Object> variableMap = job.getVariablesAsMap();
		System.out.println(variableMap);
		
		String CheckEligibility =(String) variableMap.get("ManualCheckNecessary");
		System.out.println("CheckEligibility : "+ CheckEligibility);
		
		zeebeclient.newCompleteCommand(job.getKey()).variables("").send();
		
	}
	
}
