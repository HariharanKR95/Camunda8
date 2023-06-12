package com.Camunda.StartandCancelInstance.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;

@RestController

public class StartController {

	@Autowired
	ZeebeClient client;

	@PostMapping("/start")
	public String demo() {

		System.out.println("Starting the flow");
	
		client.newCreateInstanceCommand().bpmnProcessId("services1")
				.latestVersion().send().join();
		return "started successfully";
	}

}
