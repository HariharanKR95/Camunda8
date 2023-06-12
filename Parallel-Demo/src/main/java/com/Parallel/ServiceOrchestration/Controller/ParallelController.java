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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;

@RestController

public class ParallelController {

	@Autowired
	ZeebeClient zeebeclient;

	@PostMapping("/testApi")
	public String TestApi() {

		zeebeclient.newCreateInstanceCommand().bpmnProcessId("Paralell_process").latestVersion().variables("").send()
				.join();
		return "Test Api Started Succesfully";
	}

}
