package org.camunda.community.examples.dmn.process;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class ProcessingWorker {
	@Autowired
	ZeebeClient zeebeclient;

	@ZeebeWorker(type = "demo")
	public void Paid(final JobClient client, final ActivatedJob job) {

		Map<String, Object> variableMap = job.getVariablesAsMap();
		System.out.println("Paid:" + variableMap);

		zeebeclient.newCompleteCommand(job.getKey()).variables("").send().join();

	}

}
