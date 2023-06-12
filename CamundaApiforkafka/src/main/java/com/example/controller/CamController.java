package com.example.controller;


import java.util.List;

import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.CamService;



@RestController
public class CamController<E> {
	@Autowired
	private CamService cam;
	
	
	@GetMapping("/getdefinition")
	public List<ProcessDefinitionDto> getAllTasks() {
	List<ProcessDefinitionDto> taskDtos = cam.getProcessDefinition();
	return taskDtos;
	}

	
	
@PostMapping("/{processName}")
public ProcessInstanceDto startProcessInstance(@PathVariable("processName") String processName) {
return cam.processinstance(processName);
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


}