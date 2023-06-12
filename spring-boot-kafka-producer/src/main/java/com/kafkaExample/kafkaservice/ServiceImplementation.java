package com.kafkaExample.kafkaservice;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceImplementation implements KafkaService {

	private final RestTemplate rest;



	@Value("${camunda.api.basepath}")
	private String restApiBasePath;

	public ServiceImplementation(RestTemplateBuilder builder) {
	this.rest = builder.build();
	}


	public List<ProcessDefinitionDto> getProcessDefinition() {
	String url = restApiBasePath + "process-definition";
	ResponseEntity<ProcessDefinitionDto[]> response = rest.getForEntity(url, ProcessDefinitionDto[].class);

	//Response.status( response.getStatus() ).entity( response ).type(MediaType.APPLICATION_JSON).build();
	ProcessDefinitionDto[] tasks = response.getBody();
	List<ProcessDefinitionDto> taskList = new ArrayList<ProcessDefinitionDto>();



	for (ProcessDefinitionDto taskDto : tasks) {



	taskList.add(taskDto);
	}
	return taskList;
	}



	public ProcessInstanceDto processinstance(String processName) {
		
//		String requestUrl = restApiBasePath + "process-definition/key/" + processName
//				+ "/start";
		
		String requestUrl = restApiBasePath + "/message";
		
	System.out.println(restApiBasePath + "/message");
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity requestEntity = new HttpEntity( requestHeaders);
		
		
		
		ResponseEntity<ProcessInstanceDto> response = rest.postForEntity(requestUrl, requestEntity,ProcessInstanceDto.class);
		
		ProcessInstanceDto pid = response.getBody();

		return pid;
	}




	}


