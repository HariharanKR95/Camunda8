package com.camunda.ITHelpDesk.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.IdClass;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;

@RestController
public class HelpDeskController {

	@Autowired
	ZeebeClient zeebeclient;

	// Start API :

	@PostMapping("/startCamunda")

	public Map<String,Object> demo(@RequestBody Map inputmap) {
		zeebeclient.newCreateInstanceCommand().bpmnProcessId("IT-Helpdesk").latestVersion().variables(inputmap).send().join();

		return inputmap ;

	}
	
}