package com.Camunda.StartandCancelInstance;

 import org.springframework.beans.factory.annotation.Autowired;

 import org.springframework.boot.SpringApplication;

 import org.springframework.boot.autoconfigure.SpringBootApplication;

  

 import io.camunda.zeebe.client.ZeebeClient;

 import io.camunda.zeebe.client.api.response.ActivatedJob;

 import io.camunda.zeebe.client.api.worker.JobClient;

 import io.camunda.zeebe.spring.client.EnableZeebeClient;

 import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

  

 @SpringBootApplication

 @EnableZeebeClient

 public class ServiceOrchestrationApplication {

  

 @Autowired

 private ZeebeClient zeebe;

  

 public static void main(String[] args) {

 SpringApplication.run(ServiceOrchestrationApplication.class, args);

 }

  

  

// @ZeebeWorker(type = "demoworker", name = "demoworker")
//
//  
//
// public void handleFirefighterCoordination(final JobClient client, final ActivatedJob job, Runnable doneCallback) 
// {
//
//
// System.out.println(job.getBpmnProcessId());
//
//  
//
// System.out.println("New Intsance is created. Instance ID is" +" "+job.getProcessInstanceKey());
//
// client.newCompleteCommand(job.getKey()).send().join();
//
//  
//
// }

 }

  
