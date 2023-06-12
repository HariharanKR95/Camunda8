package com.wedbush.camunda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.client.ZeebeClient;
@CrossOrigin
@RestController
public class Customer_Order_Controller {
	
	@Autowired
	ZeebeClient client;
	
	
	
	@PostMapping("/start")
	public String messaging() {
		client.newCreateInstanceCommand().bpmnProcessId("Customer_order1238").latestVersion().send().join();
		return "Flow Starter";
	}
	
	
	@GetMapping("/confirmationreceived")
	public String message() {
		String Message="receivedorder";
		String correlationkey="123";
		String str = Message+" "+correlationkey;
		client.newPublishMessageCommand().messageName(Message).correlationKey(correlationkey).send().join();
		return str;
	}

	@GetMapping("/orderfulfilled")
	public String orderfulfill() {
		String Message="orderfulfill";
		String correlationkey="12";
		String str = Message+" "+correlationkey;
		client.newPublishMessageCommand().messageName(Message).correlationKey(correlationkey).send().join();
		return str;
	}
	@GetMapping("/mindchanged")
	public String mindchanged() {
		String Message="changed";
		String correlationkey="13";
		String str = Message+" "+correlationkey;
		client.newPublishMessageCommand().messageName(Message).correlationKey(correlationkey).send().join();
		return str;
	}
	@GetMapping("/ordercancelled")
	public String ordercancel() {
		String Message="cancel";
		String correlationkey="23";
		String str = Message+" "+correlationkey;
		client.newPublishMessageCommand().messageName(Message).correlationKey(correlationkey).send().join();
		return str;
	}
	

}


//http://localhost:9191/start/Message
	
////	@PostMapping("/startevent")
////public String message() {
////	//client.newCreateInstanceCommand().bpmnProcessId("Messaging11").latestVersion().send().join();
////	return "confirmationreceived";
//}
