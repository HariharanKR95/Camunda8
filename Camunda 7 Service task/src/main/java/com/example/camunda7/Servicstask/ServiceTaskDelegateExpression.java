package com.example.camunda7.Servicstask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("demo")

public class ServiceTaskDelegateExpression implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		

		System.out.println("Delegate Expression Implementation of Service Task Called");
	}

	
	

}
