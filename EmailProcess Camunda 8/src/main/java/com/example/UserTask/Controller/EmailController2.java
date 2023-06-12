package com.example.UserTask.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.example.UserTask.Worker.ServiceClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.command.FinalCommandStep;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;
import io.camunda.zeebe.client.api.worker.JobClient;

@RestController

public class EmailController2 {
	
	@Autowired
	ZeebeClient client;
	 @Autowired
	  private ServiceClass serv;
	@CrossOrigin
	 @PostMapping("/start")
	 public String demo(@RequestParam String ElementId ) { 
	 	
	 	System.out.println("Starting the flow");
	 	client.newCreateInstanceCommand().bpmnProcessId("").latestVersion().send().join();
				 return "started successfully";
			 }
@PostMapping("/Complete")
public void handleJob(final JobClient client, final ActivatedJob job) {
	System.out.println("Compete");
	client.newCompleteCommand(job.getKey()).variables("").send().join();
	
	
}
	
@PostMapping("/cancel")
public String cancel() { 
	
	System.out.println("Cancelling  the flow");
	long key = 2251799813685843l;
	client.newCancelInstanceCommand(key).send();
	return "flow cancelled";
	
}



}
