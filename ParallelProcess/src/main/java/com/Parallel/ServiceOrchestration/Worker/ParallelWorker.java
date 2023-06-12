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

//	  @Autowired
//	  ZeebeClient zeebeClient;

	Logger logger = LoggerFactory.getLogger(ParallelWorker.class);
	final RestTemplate rest = new RestTemplate();

	@ZeebeWorker(type = "User", name = "User")
	public void Task1(final JobClient client, final ActivatedJob job) {
		// System.out.println("Flow Started");

		// String url ="http://localhost:9191/worker";
		logger.info("Worker1 Started");

		// Map<String, String> map = new HashMap<>();
		// Map<String, Object> map=job.getVariablesAsMap();
		// System.out.println(map);

		// ResponseEntity<String>response = rest.getForEntity(url, String.class);

		// String workervariable = response.getBody();
		// System.out.println(workervariable);
		client.newCompleteCommand(job.getKey()).variables("").send().join();
		logger.info("worker1 ended");

	}

	// Worker2
	@ZeebeWorker(type = "Order", name = "Order")
	public void Task2(final JobClient client, final ActivatedJob job) {

		logger.info("Worker2 Started");
		// Map<String, Object> map=job.getVariablesAsMap();
		// System.out.println(map);

		// String url ="http://localhost:9191/order";

		// Map<String, String> map1 = new HashMap<>();

		// ResponseEntity<String>response = rest.getForEntity(url, String.class, map1);

		// String ordervariable = response.getBody();
		// System.out.println(ordervariable);
		client.newCompleteCommand(job.getKey()).variables("").send().join();
		logger.info("Worker2 Ended");

	}

	// worker3
	@ZeebeWorker(type = "Shipped", name = "Shipped")
	public void Task3(final JobClient client, final ActivatedJob job) {
		logger.info("Worker3 Started");

		// Map<String, Object> map1=job.getVariablesAsMap();
		// System.out.println(map1);
		// String url ="http://localhost:9191/shipping";

		// Map<String, String> map2 = new HashMap<>();

		// ResponseEntity<String>response = rest.getForEntity(url, String.class, map2);

		// String shippedvariable = response.getBody();
		// System.out.println(shippedvariable);
		client.newCompleteCommand(job.getKey()).variables("").send().join();
		logger.info("Worker3 Ended");
	}

	// worker4
	@ZeebeWorker(type = "Payment", name = "Payment")
	public void Task4(final JobClient client, final ActivatedJob job) throws Exception {
//			String code="wrong_code";
//			try {
//				  
//				  if(true)
//				  {
//					  throw new Exception();
//				  }
//				} catch (Exception e) {
//				  client.newThrowErrorCommand(job.getKey()).errorCode(code).send();
//				}
		System.out.println("Worker4 Started");

		Map<String, Object> map2 = job.getVariablesAsMap();
		System.out.println(map2);
		String url = "http://localhost:9191/payment";

		Map<String, String> map3 = new HashMap<>();

		ResponseEntity<String> response = rest.getForEntity(url, String.class, map3);

		String paymentvariable = response.getBody();
		System.out.println(paymentvariable);
		client.newCompleteCommand(job.getKey()).variables(paymentvariable).send().join();

	}

	// getvariables
	@ZeebeWorker(type = "getall", name = "getall")
	public void Task5(final JobClient client, final ActivatedJob job) {
		System.out.println("Worker5 Started");

		// Map<String, Object> map3=job.getVariablesAsMap();
		// System.out.println(map3);

		Map<String, Object> map4 = job.getVariablesAsMap();
		System.out.println(map4);
//			try {
//		           // String str = "{\"id\":\"567890987\",\"name\":\"Hariharan\",\"Address\":\"Chennai\"}";
//					String str=map4.toString();
//		          //  JSONObject jsonObject = new JSONObject(str);
//		            
//		          //  System.out.println("OBJECT : "+jsonObject.toString());
//		       } catch (JSONException e) {
//		            //System.out.println("Exception : "+e.toString());
//		            
//		           
//		       }
//		            finally
//		            {

		System.out.println("hiiii" + JSONObject.valueToString(map4));
		// }

		client.newCompleteCommand(job.getKey()).variables(map4).send().join();

		System.out.println("Flow ended");

	}

//		@ZeebeWorker(type = "Rejected", name = "Rejected")
//		public void Task6(final JobClient client, final ActivatedJob job) 
//		{
//			System.out.println("Worker5 Started");
//			String url = "http://localhost:8098/rejected";
//			ResponseEntity<String>response = rest.getForEntity(url,String.class);
//		
//			String revariable = response.getBody();
//			System.out.println(revariable);
//		    
//			client.newCompleteCommand(job.getKey()).variables(revariable).send().join();
//			
//		}

}
