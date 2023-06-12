package com.Parallel.ServiceOrchestration.Worker;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
public class ParallelWorker {
	@Autowired
	ZeebeClient zeebeClient;

	Logger logger = LoggerFactory.getLogger(ParallelWorker.class);
	final RestTemplate rest = new RestTemplate();

	@ZeebeWorker(type = "Worker1", name = "Worker1")
	public void Task1(final JobClient client, final ActivatedJob job) {

		logger.info("Worker1 Started");
		Map<String, Object> variablemap = new HashMap<>();
		variablemap.put("100", "Hari");
		variablemap.put("101", "Kannan");

		System.out.println("Worker1:" + variablemap);
		zeebeClient.newCompleteCommand(job.getKey()).variables(variablemap).send().join();
		logger.info("worker1 ended");

	}

	// Worker2
	@ZeebeWorker(type = "Worker2", name = "Worker2")
	public void Task2(final JobClient client, final ActivatedJob job) {

		logger.info("Worker2 Started");
		
		Map<String, Object> variablemap = new HashMap<>();
		variablemap.put("102", "Naveen");
		variablemap.put("103", "Rajesh");

		System.out.println("Worker2:" + variablemap);

		zeebeClient.newCompleteCommand(job.getKey()).variables(variablemap).send().join();
		logger.info("Worker2 Ended");

	}

	// worker3
	@ZeebeWorker(type = "Worker3", name = "Worker3")
	public void Task3(final JobClient client, final ActivatedJob job) {

		logger.info("Worker3 Started");
		
		Map<String, Object> variablemap = new HashMap<>();
		variablemap.put("104", "Ashok");
		variablemap.put("105", "Gayathri");

		System.out.println("Worker3:" + variablemap);

		zeebeClient.newCompleteCommand(job.getKey()).variables(variablemap).send().join();
		logger.info("Worker3 Ended");

	}
	@ZeebeWorker(type = "Worker4", name = "Worker4")
	public void Task4(final JobClient client, final ActivatedJob job) {

		logger.info("Worker4 Started");
		
		Map<String, Object> variablemap = new HashMap<>();
		variablemap.put("106", "Subha");
		variablemap.put("107", "Gayathri");

		System.out.println("Worker4:" + variablemap);

		zeebeClient.newCompleteCommand(job.getKey()).variables(variablemap).send().join();
		logger.info("Worker4 Ended");

	}



	// getvariables
	@ZeebeWorker(type = "Worker5", name = "Worker5")
	public void Task5(final JobClient client, final ActivatedJob job) {
		logger.info("Worker5 Started");

		

		Map<String, Object> Variablemap = job.getVariablesAsMap();
		
		
		System.out.println("Worker5 Getall:"+Variablemap);

		zeebeClient.newCompleteCommand(job.getKey()).variables(Variablemap).send().join();

		logger.info("Worker5 ended");

	}

}
