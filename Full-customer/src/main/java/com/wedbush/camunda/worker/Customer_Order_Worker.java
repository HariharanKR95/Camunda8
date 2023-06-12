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
public class Customer_Order_Worker {

	@Autowired
	ZeebeClient zeebeClient;


	final RestTemplate rest = new RestTemplate();

	// 1st Worker Start :-
	// worker 1 -----> :-

	@ZeebeWorker(type = "Worker1", name = "Send order to fullfillment")
	public void getSendorderfullfillment(final JobClient client, final ActivatedJob job) {
			System.out.println("Send order to fullfillment Worker Start");
			Map<String, Object> map = job.getVariablesAsMap();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(headers);
			String url = "http://localhost:2055/start/orderreceived";
			ResponseEntity<String> response = rest.postForEntity(url, request, String.class);
			map.put("getSendorderfullfillment", response);
			//zeebeClient.newPublishMessageCommand().messageName("").correlationKey("").send().join();
			System.out.println("Send order to fullfillment worker1"+response);

		client.newCompleteCommand(job.getKey()).variables(response).send().join();

	}
	
	
	@ZeebeWorker(type = "Worker3", name = "Send payment")
	public void getSendpayment(final JobClient client, final ActivatedJob job) {
			System.out.println("Send payment Worker Start");
			Map<String, Object> map = job.getVariablesAsMap();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(headers);
			String url = "http://localhost:2055/paymentreceive";
			ResponseEntity<String> response = rest.getForEntity(url, String.class);
			map.put("getSendpayment", response);
			//zeebeClient.newPublishMessageCommand().messageName("").correlationKey("").send().join();
			System.out.println("Send payment worker3"+response);

		client.newCompleteCommand(job.getKey()).variables(response).send().join();

	}

	@ZeebeWorker(type = "cancel", name = "send cancellation")
	public void getSendcancel(final JobClient client, final ActivatedJob job) {
			System.out.println("cancelWorker Start");
			Map<String, Object> map = job.getVariablesAsMap();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> request = new HttpEntity<>(headers);
			String url = "http://localhost:2055/cancelreceived";
			ResponseEntity<String> response = rest.getForEntity(url, String.class);
			map.put("getSendpayment", response);
			//zeebeClient.newPublishMessageCommand().messageName("").correlationKey("").send().join();
			System.out.println("cancelworker"+response);

		client.newCompleteCommand(job.getKey()).variables(response).send().join();

	}

}
