	package com.student.ServiceOrchestration.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


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

public class DemoController {
	
	@Autowired
	ZeebeClient client;
	



@PostMapping("/demo/{abc}")
public String demo(@PathVariable Integer abc) { 
	

	System.out.println("marks:"+abc);
	Map<String,Object>map=new HashMap<>();
  //   int abc = 1001;
//	if(abc>75) 
//	{
//	map.put("type", "DistinctionClass");
//	System.out.println(map);
//	}
	 //if(abc >=60 && abc <=75)
	if(abc>75)
	{

	map.put("type", "FirstClass");
	System.out.println(map);

	}
//	else if(abc >=35 && abc <=60)
	else
	{
		map.put("type", "PassClass");
		System.out.println(map);
	}
//	else
//	{
//		map.put("type", "FailClass");
//		System.out.println(map);
//	}

	
	
client.newCreateInstanceCommand().
bpmnProcessId("StudentMarksnew").latestVersion(). variables (map).send().join();
return null;


}
}

