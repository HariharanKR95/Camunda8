package com.example.camunda.elasticsearch.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.camunda.zeebe.client.ZeebeClient;

@RestController
@CrossOrigin(maxAge = 3600)

public class ElasticdemoController {

	/**
	 * Using constructor injection instead of @Autowired
	 */
	ZeebeClient zeebeClient;

	// end //

	final RestTemplate rest = new RestTemplate();

//	@Value("${getElasticURL}")
//	private String getElasticURL;

	Logger logger = LoggerFactory.getLogger(ElasticdemoController.class);
//	@Autowired
//	private Clientpojo client;
//	@Autowired
//	private Valuepojo value;

	@PostMapping("/elasticData/{processInstanceKey}")

	public List<Object> getElasticData(@PathVariable Long processInstanceKey)
			throws JsonMappingException, JsonProcessingException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String str = "{\"query\":{\"term\":{\"value.processInstanceKey\":" + processInstanceKey + "} } }";

		HttpEntity<String> reqEntity = new HttpEntity<String>(str, headers);

		ResponseEntity<String> demo = rest.exchange("http://localhost:9200/zeebe-record_process-instance_8.0.2_2022-12-06/_search", HttpMethod.POST, reqEntity, String.class, headers);
		String variable = demo.getBody();

		logger.info("Elastic Response:" + variable);

		ObjectMapper mapper = new ObjectMapper();

		Map obj = mapper.readValue(variable, Map.class);

		Map hitmap = (Map) obj.get("hits");

		List<Object> hit = (List<Object>) hitmap.get("hits");

		List<Object> Final = new ArrayList<Object>();

		for (Object getDetails : hit) {

			Map getSource = (Map) getDetails;
			Map getValue = (Map) getSource.get("_source");
			String getintent = (String) getValue.get("intent");
			Long timestamp = (Long) getValue.get("timestamp");
			Map getdata = (Map) getValue.get("value");

			getdata.put("intent", getintent);
			getdata.put("timestamp", timestamp);
			
			Final.add(getdata);
			

		}

		return Final;

	}

//	@GetMapping("/runningActiveInstance")
//
//	public Clientpojo demo() throws JsonMappingException, JsonProcessingException {
//		RestTemplate rest = new RestTemplate();
//		Clientpojo data = new Clientpojo();
//		data = rest.getForObject(getElasticURL, Clientpojo.class);
//		logger.info("Elastic Response:" + data.toString());
//
//		data.get_shards();
//		data.getHits();
//		data.getTook();
//
//		return data;
//
//	}
//	

}