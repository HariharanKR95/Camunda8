package org.camunda.community.examples.dmn.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;

@RestController
public class OnboardCustomerRestApi {

    @Autowired
    private ZeebeClient zeebeClient;

    
	@PostMapping("/customer")
    public Map<String, Object> bill(@RequestBody Map inputMap) {

		ProcessInstanceResult processInstance = (ProcessInstanceResult) zeebeClient.newCreateInstanceCommand()
				.bpmnProcessId("CustomerOnboarding").latestVersion().variables(inputMap).withResult().send().join();

		return processInstance.getVariablesAsMap();
		// return String.valueOf(processInstance.getProcessInstanceKey());

	}
	

    
}
