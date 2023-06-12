package com.example.camundademo.controller;

import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;



import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
public class worker {

	 final RestTemplate rest = new RestTemplate();
	    
		 
	 @ZeebeWorker(type = "worker1",name = "A")
     public void Task1(final JobClient client, final ActivatedJob job)
     {
        System.out.println("Worker1 Started");
        
         
         client.newCompleteCommand(job.getKey()).send().join();
         System.out.println("woker1 ended");
             
         }
	 
	 @ZeebeWorker(type = "worker2", name = "worker2")
     public void Task2(final JobClient client, final ActivatedJob job)
     {
        System.out.println("Worker2 Started");
        
         
         client.newCompleteCommand(job.getKey()).send().join();
         System.out.println("woker2 ended");
             
         }
	 
}
