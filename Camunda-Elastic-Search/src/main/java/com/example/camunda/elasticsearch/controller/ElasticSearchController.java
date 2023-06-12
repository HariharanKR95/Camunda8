package com.example.camunda.elasticsearch.controller;
//package com.example.Spring.elasticsearch.controller;
//
//import java.util.List;
//import java.util.Map;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.logging.type.HttpRequest;
//
//@RestController
//public class ElasticSearchController {
//@Autowired
////private Tasklist tasklist;
//
//	final RestTemplate rest = new RestTemplate();
//	Logger logger = LoggerFactory.getLogger(ElasticSearchController.class);
//
//	@PostMapping("/getTaskDetails/{Date}/{InstanceId}")
//	public Map getInstanceData(@PathVariable String Date, @PathVariable Long InstanceId) throws JsonMappingException, JsonProcessingException {
//
//		HttpHeaders header = new HttpHeaders();
//		header.setContentType(MediaType.APPLICATION_JSON);
//		String str = "{\"query\":{\"match\":{\"value.processInstanceKey\":" + InstanceId + "}}}";
//		HttpEntity<String> reqEntiy = new HttpEntity<>(str, header);
////		ResponseEntity<String> s = rest.exchange("http://localhost:9200/zeebe-record_process-instance_8.0.2_"+Date+"/_search",
////				HttpMethod.POST, reqEntiy, String.class);
//		ResponseEntity<String> s = rest.exchange("http://localhost:9200/tasklist-task-1.3.0_"+Date+"/_search",
//				HttpMethod.POST, reqEntiy, String.class);
//
//		String body = s.getBody();
//		ObjectMapper obj = new ObjectMapper();
//
//		Map rej = obj.readValue(body, Map.class);
//
//		return rej;
//
//	}
//
//}
