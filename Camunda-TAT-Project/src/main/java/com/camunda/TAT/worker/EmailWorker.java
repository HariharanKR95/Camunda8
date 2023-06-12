package com.camunda.TAT.worker;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
public class EmailWorker {

	final RestTemplate rest = new RestTemplate();
	Logger logger = LoggerFactory.getLogger(EmailWorker.class);

	@ZeebeWorker(type = "Profile")
	public void handleJob(final JobClient client, final ActivatedJob job) {
		Map<String, Object> variablesAsMap = job.getVariablesAsMap();

		logger.info("Profile" + variablesAsMap);

		client.newCompleteCommand(job.getKey()).variables(variablesAsMap).send().join();
		logger.info("Worker1 Ended");
	}

	// @ZeebeWorker(type = "io.camunda.zeebe:userTask")
	@ZeebeWorker(name = "JD", type = "JD")
	public void OfferLetterSent(final JobClient client, final ActivatedJob job) throws Exception {
	 
	  Map < String, Object > variableAsMap = job.getVariablesAsMap();
	  logger.info("JD" + variableAsMap);

	  boolean containsValueJava = variableAsMap.containsValue("java");
	  boolean containsValueCamunda = variableAsMap.containsValue("camunda");
	  boolean containsValueUI = variableAsMap.containsValue("ui");
	  boolean containsValueJBPM = variableAsMap.containsValue("jbpm");
	  String Incidentmessage = "";
	  if (containsValueJava) {
	    Incidentmessage = "java";
	    System.out.println("We are looking to hire a highly-skilled developer with extensive" +
	      " experience building high-performing, scalable apps in Java. Our Software Development" +
	      " and recommending changes to promote the existing Java-based apps");
	  } else if (containsValueCamunda) {
	    Incidentmessage = "camunda";
	    System.out.println("Camunda on design a workflow, coding and debugging is a must;" +
	      "Integrate and maintain moderately complex to extremely complex applications throughout the entire Software Development Life Cycle;" +
	      "Translate complex business requirements into technical specification");
	  } else if (containsValueUI) {
	    Incidentmessage = "ui";
	    System.out.println("A UI developer’s role is to translate creative software design concepts and ideas into reality using " +
	      " and the backend layer, who understands both and creates a niche as the translational layer in between, " +
	      "so that both lives up to its full potential and are not compromised because of each other.");
	  } else if (containsValueJBPM) {
	    Incidentmessage = "jbpm";
	    System.out.println("1-2 years of experience with Java, SQL, HTML, JavaScript, XML, JSP." +
	      "Knowledge of system development methodologies Dev Ops and Agile along with JIRA Confluence." +
	      "Develop and code BPM Solutions for complex technical designs to ensure the optimal solution is recommended.");
	  }
//	  HashMap < String, Object > variables = new HashMap < > ();
	  variableAsMap.put("result", Incidentmessage);
	  client.newCompleteCommand(job.getKey()).variables(variableAsMap).send().join();
	}

}
