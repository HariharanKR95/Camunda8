package com.Camunda.StartandCancelInstance.Worker;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
public class StartInstanceWorker {

//	  @Autowired
//	  ZeebeClient zeebeClient;

	final RestTemplate rest = new RestTemplate();

	@ZeebeWorker(type = "service", name = "service")
	public void Task1(final JobClient client, final ActivatedJob job) {
		// System.out.println("Flow Started");

		System.out.println("Worker1 Started");

		
		System.out.println("Worker1 Started" + job.getBpmnProcessId());

		
		client.newCompleteCommand(job.getKey()).send().join();
		System.out.println("woker1 ended");

	}

}
