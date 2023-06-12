//package com.camundasaas.orchestration.Controller;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import io.camunda.zeebe.client.ZeebeClient;
//
//@RestController
//public class JsonReaderController {
//
//	@Autowired
//	ZeebeClient client;
//
//	// Start API :
//
//	@PostMapping("/saas")
//
//	public String demo() {
//		System.out.println("Starting Camunda Flow....");
//		client.newCreateInstanceCommand().bpmnProcessId("template-microservice-tutorial").latestVersion().send();
//
//		return "started successfully";
//
//	}
//
//}
