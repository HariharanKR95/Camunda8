package com.example.demo.json;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class JsonReader {
	
	@GetMapping("/address")
	public JSONObject jsonaddress() {
		JSONParser parser = new JSONParser();
		try {
			Object obj=parser.parse(new FileReader("D:\\Camunda Training  Data\\Camunda Usecase\\ServiceOrchestrationSequenceErrorHandling\\src\\main\\resources\\jSON\\Address.json"));
			JSONObject jsonObject = (JSONObject)obj;
			return jsonObject;
		} 
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/employe")
	public JSONObject jsonemploye() {
		JSONParser parser = new JSONParser();
		try {
			Object obj=parser.parse(new FileReader("D:\\Camunda Training  Data\\Camunda Usecase\\ServiceOrchestrationSequenceErrorHandling\\src\\main\\resources\\jSON\\Employee.json"));
			JSONObject jsonObject = (JSONObject)obj;
			return jsonObject;
		} 
		catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	@GetMapping("/id")
	public JSONObject jsonid() {
		JSONParser parser = new JSONParser();
		try {
			Object obj=parser.parse(new FileReader("D:\\Camunda Training  Data\\Camunda Usecase\\ServiceOrchestrationSequenceErrorHandling\\src\\main\\resources\\jSON\\Id.json"));
			JSONObject jsonObject = (JSONObject)obj;
			return jsonObject;
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
		
	}

}
