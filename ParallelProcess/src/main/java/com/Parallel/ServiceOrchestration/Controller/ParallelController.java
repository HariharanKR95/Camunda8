package com.Parallel.ServiceOrchestration.Controller;

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

import com.Parallel.ServiceOrchestration.Model.Rejected;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;

@RestController

public class ParallelController {
	
	@Autowired
	ZeebeClient client;
	
	

	 @Autowired
	 public Rejected rj;
	 
	 @PostMapping("/demo/{abc}")
	 public String demo(@PathVariable Integer abc) { 
	 	
	 	System.out.println("ID:"+abc);
	 	Map<String,Object>map=new HashMap<>();
	 	 ProcessInstanceResult workflowInstanceResult = client.newCreateInstanceCommand().
				 bpmnProcessId("Parallel_Proccess").latestVersion(). variables (map) .withResult().send().join();
				 return null;
			 }
	 	

	 @GetMapping("/order")
		
		public String jsonFileReader1() throws FileNotFoundException, IOException, ParseException {
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(new FileReader("D:\\Camunda-POC\\Json\\Order.json"));
			JSONObject jsonObject = (JSONObject) obj;
			return jsonObject.toString();

		}

	 @GetMapping("/shipping")
	 public String jsonFileReader2() throws FileNotFoundException, IOException, ParseException {
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(new FileReader("D:\\Camunda-POC\\Json\\Shipped.json"));
			JSONObject jsonObject = (JSONObject) obj;
			return jsonObject.toString();

		
	 }
	 
	 @GetMapping("/payment")
	 public String jsonFileReader3() throws FileNotFoundException, IOException, ParseException {
			JSONParser jsonParser = new JSONParser();
			Object obj = jsonParser.parse(new FileReader("D:\\Camunda-POC\\Json\\Payment.json"));
			JSONObject jsonObject = (JSONObject) obj;
			return jsonObject.toString();

	 }
			
	 @GetMapping("/rejected")
	 public Rejected r() {
		return rj;
	 }
	
//ProcessInstanceResult workflowInstanceResult = client.newCreateInstanceCommand().
//bpmnProcessId("StudentMarksnew").latestVersion(). variables (map) .withResult().send().join();
//return null;


}


