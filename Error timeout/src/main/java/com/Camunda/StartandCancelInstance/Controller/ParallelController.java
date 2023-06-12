 package com.Camunda.StartandCancelInstance.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;

@RestController

public class ParallelController {
	
	@Autowired
	ZeebeClient client;
	
	

	
	 
	 @PostMapping("/start")
	 public String demo() { 
	 	
	 	System.out.println("Starting the flow");
	 	Map<String,Object>map=new HashMap<>();
	 	 ProcessInstanceResult workflowInstanceResult = client.newCreateInstanceCommand().
				 bpmnProcessId("services1").latestVersion(). variables (map) .withResult().send().join();
				 return "started successfully";
			 }
	 	
	 @PostMapping("/cancel")
	 public String cancel() { 
	 	
	 	System.out.println("Cancelling  the flow");
	 	long key = 2251799813685843l;
	 	client.newCancelInstanceCommand(key).send();
	 	return "flow cancelled";


}
	 
	
	 
	 
}


