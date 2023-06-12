package com.multiinstance.Parallel.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;

@RestController

public class MultiInstanceController {

	@Autowired
	ZeebeClient zeebeclient;

	@PostMapping("/testApi")
	public String TestApi() {

		zeebeclient.newCreateInstanceCommand().bpmnProcessId("Multi-Instance").latestVersion().variables("").send()
				.join();
		return "Test Api Started Succesfully";
	}

	
}
