package com.camunda.ITHelpDesk.Worker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.camunda.ITHelpDesk.Model.Client.Clientpojo;
import com.camunda.ITHelpDesk.Model.Client.ResponsePojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
public class HelpdeskWorker {
	Logger logger = LoggerFactory.getLogger(HelpdeskWorker.class);

	final RestTemplate rest = new RestTemplate();

	@ZeebeWorker(name = "TicketAnalyze", type = "TicketAnalyze")

	public void ticketAnalyze(final JobClient client, final ActivatedJob job) throws Exception {

		Map<String, Object> map = job.getVariablesAsMap();

		logger.info("Ticket analyze Worker " + map);

		Map<String, Object> variableapprove = new HashMap<>();
		variableapprove.put("Incident", "accept");

		 String message;
		  if (!variableapprove.equals("accept")) {
		    message = "Incident Approved";
		  } else {
		    message = "Incident Rejected";
		  }
		  HashMap < String, Object > incidentstatus = new HashMap < > ();
		  incidentstatus.put("result1", message);

		client.newCompleteCommand(job.getKey()).variables(map).send().join();

	}

	@ZeebeWorker(name = "IncidentApproved", type = "IncidentApproved")

	public void IncidentApproved(final JobClient client, final ActivatedJob job) throws Exception {

		Map<String, Object> variableasmap = job.getVariablesAsMap();

		logger.info("variableasmap: " + variableasmap);

		logger.info("Incident: Approved");

		client.newCompleteCommand(job.getKey()).variables(variableasmap).send().join();
	}

	@ZeebeWorker(name = "IncidentRejected", type = "IncidentRejected")

	public void IncidentRejected(final JobClient client, final ActivatedJob job) throws Exception {

		Map<String, Object> variableasmap = job.getVariablesAsMap();

		logger.info("variableasmap: " + variableasmap);

		logger.info("Incident: Rejected");

		client.newCompleteCommand(job.getKey()).variables(variableasmap).send().join();
	}

	@ZeebeWorker(name = "IncidentSolving", type = "IncidentSolving")
	public void incidentsolving(final JobClient client, final ActivatedJob job) throws Exception {
	  Map < String, Object > variableasmap = job.getVariablesAsMap();
	
	  variableasmap.put("status", "Solved");
	  String Incidentmessage;
	  if (!variableasmap.equals("Solved")) {
	    Incidentmessage = "status solved";
	  } else {
	    Incidentmessage = "status Unsolved";
	  }
	  HashMap < String, Object > incidentstatus = new HashMap < > ();
	  incidentstatus.put("result2", Incidentmessage);
	  client.newCompleteCommand(job.getKey()).variables(incidentstatus).send().join();
	}
}
