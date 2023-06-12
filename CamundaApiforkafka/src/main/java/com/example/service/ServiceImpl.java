package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


@Service

public class ServiceImpl implements CamService {

//	@Value("${camunda.processname}")
//	private String processName;

	
	

	
	
	
private final RestTemplate rest;



@Value("${camunda.api.basepath}")
private String restApiBasePath;

public ServiceImpl(RestTemplateBuilder builder) {
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
	
	String requestUrl = restApiBasePath + "process-definition/key/" + processName
			+ "/start";
	
System.out.println(restApiBasePath + "process-definition/key/" + processName+ "/start");
	
	HttpHeaders requestHeaders = new HttpHeaders();
	requestHeaders.setContentType(MediaType.APPLICATION_JSON);
	HttpEntity requestEntity = new HttpEntity( requestHeaders);
	
	
	
	ResponseEntity<ProcessInstanceDto> response = rest.postForEntity(requestUrl, requestEntity,ProcessInstanceDto.class);
	
	ProcessInstanceDto pid = response.getBody();

	return pid;
}





}
	
