//package com.camundasaas.orchestration.Worker;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import com.camundasaas.orchestration.Model.Client.Clientpojo;
//import com.camundasaas.orchestration.Model.Client.ResponsePojo;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import io.camunda.zeebe.client.api.response.ActivatedJob;
//import io.camunda.zeebe.client.api.worker.JobClient;
//import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
//
//@SpringBootApplication
//public class SerivceOrchesWorker {
//	Logger logger = LoggerFactory.getLogger(SerivceOrchesWorker.class);
//
//	final RestTemplate rest = new RestTemplate();
//
//	@ZeebeWorker(type = "Demo", name = "Demo")
//	public void Task1(final JobClient client, final ActivatedJob job)
//			throws JsonMappingException, JsonProcessingException {
//		logger.info("Demo Worker Started");
//		logger.info("Demo Worker ......:" + job.getVariablesAsMap());
//
//		Map<String, Object> instanceVariableMap = job.getVariablesAsMap();
//
//		client.newCompleteCommand(job.getKey()).variables(instanceVariableMap).send().join();
//
//		logger.info("Demo Worker Ended");
//
//	}
//
//}
