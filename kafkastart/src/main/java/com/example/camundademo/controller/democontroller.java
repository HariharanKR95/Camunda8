package com.example.camundademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.camundademo.producer.Producer;
import com.example.camundademo.producer.ServiceClass;

import io.camunda.zeebe.client.ZeebeClient;

@RestController
public class democontroller {
	
	@Autowired
	ZeebeClient client;
	
	@Autowired
	public Producer  producer;
	
	@Autowired
    private ServiceClass serv;
	
	/*
	 * @GetMapping("/start") public String startflow() {
	 * System.out.println("Starting the flow");
	 * client.newCreateInstanceCommand().bpmnProcessId("MessageStart").latestVersion
	 * ().send().join(); return "flow started successfully"; }
	 */
	
	@PostMapping("/kafkastart/{Message}")
	  public String sendMessage(@PathVariable String Message) {
		
	    producer.publishToTopic(Message);
	    System.out.println(Message);
	    //client.newCreateInstanceCommand().bpmnProcessId("Kafka_Message1").latestVersion().send().join();
	    client.newPublishMessageCommand().messageName(Message).correlationKey("").send().join();
	    return Message;


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

	
	
	
	

		
	
	

