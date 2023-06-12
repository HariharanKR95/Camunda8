package com.camunda.dmn.controller;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;



import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;

@RestController
public class DmnController {

	@Autowired
	private ZeebeClient zeebeClient;

	@PostMapping("/eligiblecheck")
	public Map<String, Object> age(@RequestBody Map inputMap) {

		ProcessInstanceResult processInstance = (ProcessInstanceResult) zeebeClient.newCreateInstanceCommand()
				.bpmnProcessId("CustomerOnboarding").latestVersion().variables(inputMap).withResult()
				.send().join();
		//.requestTimeout(Duration.ofMillis(2000000))
		return processInstance.getVariablesAsMap();
		// return String.valueOf(processInstance.getProcessInstanceKey());

	}

}
