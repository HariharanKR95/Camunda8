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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import com.example.UserTask.Worker.ServiceClass;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;

@RestController

public class EmailController {
	
	@Autowired
	ZeebeClient client;
	 @Autowired
	  private ServiceClass serv;
	@CrossOrigin
	 @GetMapping("/start")
	 public String demo() { 
	 	
	 	System.out.println("Starting the flow");
	 	client.newCreateInstanceCommand().bpmnProcessId("Emailprocess2").latestVersion().send().join();
	 	
				 return "started successfully";
			 }

	
	@GetMapping("/{email}/{key}")
	  public String MessageTriggerController(@PathVariable String email, @PathVariable String key) {
	    System.out.println(email + key);
	    serv.MessageTriggerTask(email, key);
	   // client.newCompleteCommand(job.getKey()).send().join();
	   // client.newCreateInstanceCommand().bpmnProcessId("Event_1n9xj49").latestVersion().send().join();
	    return email + " : " + key;

	  }
	
	


}
