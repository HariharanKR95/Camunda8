package com.kafkaExample.kafkaservice;

import java.util.List;

import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;
import org.camunda.bpm.engine.rest.dto.runtime.ProcessInstanceDto;


public interface KafkaService {
	
	 List<ProcessDefinitionDto> getProcessDefinition();
	 

		public 	ProcessInstanceDto processinstance(String processName); 
			

}
