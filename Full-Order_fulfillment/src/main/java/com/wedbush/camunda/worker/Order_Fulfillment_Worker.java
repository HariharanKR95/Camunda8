package com.wedbush.camunda.worker;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
public class Order_Fulfillment_Worker {


	@Autowired
	ZeebeClient zeebeClient;
	final RestTemplate rest = new RestTemplate();

	// 1st Worker Start :-
	// worker 1 -----> GetAccountByClientIdForActivity :-
	@ZeebeWorker(type = "Worker2", name = "Send Order Confirmation")
	public void getSendOrderConfirmation(final JobClient client, final ActivatedJob job) {
		System.out.println("Worker Start");
		
		Map<String, Object> map = job.getVariablesAsMap();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);
		String url = "http://localhost:2044/confirmationreceived";
		ResponseEntity<String> response = rest.getForEntity(url,String.class);
		map.put("getSendOrderConfirmation", response);
		System.out.println(response);

		client.newCompleteCommand(job.getKey()).variables(response).send().join();

	}
	@ZeebeWorker(type = "fulfill", name = "order fulfilled")
	public void getSendOrderfulfillment(final JobClient client, final ActivatedJob job) {
		System.out.println("Worker Start");
		
		Map<String, Object> map = job.getVariablesAsMap();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);
		String url = "http://localhost:2044/orderfulfilled";
		ResponseEntity<String> response = rest.getForEntity(url,String.class);
		map.put("getorderfulfilled", response);
		System.out.println(response);

		client.newCompleteCommand(job.getKey()).variables(response).send().join();

	}
	
	@ZeebeWorker(type = "cancelled", name = "order cancelled")
	public void getSendordercancelled(final JobClient client, final ActivatedJob job) {
		System.out.println("Worker Start");
		
		Map<String, Object> map = job.getVariablesAsMap();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(headers);
		String url = "http://localhost:2044/ordercancelled";
		ResponseEntity<String> response = rest.getForEntity(url,String.class);
		map.put("getorderfulfilled", response);
		System.out.println(response);

		client.newCompleteCommand(job.getKey()).variables(response).send().join();

	}
}
