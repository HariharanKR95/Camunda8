package com.student.ServiceOrchestration.Worker;

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
public class StudentWorker {
	
	  

//	  @Autowired
//	  ZeebeClient zeebeClient;

	  

	  final RestTemplate rest = new RestTemplate();
	
	
	@ZeebeWorker(type = "DistinctionClass", name = "DistinctionClass")
	  public void distinctClass(final JobClient client, final ActivatedJob job) throws Exception {
	
		System.out.println("DistinctionClass worker job is activated");
	    //String url = baseurl + "getfindtradeconfirmation";
	   
	    Map<String, Object> map=job.getVariablesAsMap();
		System.out.println("DistinctionClass:"+map);

	    client.newCompleteCommand(job.getKey()).variables(map).send().join();

	  }

	@ZeebeWorker(type = "FirstClass", name = "FirstClass")
	  public void firstClass(final JobClient client, final ActivatedJob job) throws Exception {
	
		System.out.println("FirstClass worker job is activated");
	    //String url = baseurl + "getfindtradeconfirmation";
	   
	    Map<String, Object> map=job.getVariablesAsMap();
		System.out.println("FirstClass:"+map);

	    client.newCompleteCommand(job.getKey()).variables(map).send().join();

	  }

	@ZeebeWorker(type = "PassClass", name = "PassClass")
	  public void passClass(final JobClient client, final ActivatedJob job) throws Exception {
	
		System.out.println("PassClass worker job is activated");
	    //String url = baseurl + "getfindtradeconfirmation";
	   
	    Map<String, Object> map=job.getVariablesAsMap();
		System.out.println("PassClass:"+map);

	    client.newCompleteCommand(job.getKey()).variables(map).send().join();

	  }
	
	@ZeebeWorker(type = "FailClass", name = "FailClass")
	  public void failClass(final JobClient client, final ActivatedJob job) throws Exception {
	
		System.out.println("FailClass worker job is activated");
	    //String url = baseurl + "getfindtradeconfirmation";
	   
	    Map<String, Object> map=job.getVariablesAsMap();
		System.out.println("FailClass:"+map);

	    client.newCompleteCommand(job.getKey()).variables(map).send().join();

	  }
	
	
	

}
