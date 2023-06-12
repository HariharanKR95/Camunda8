package com.example.camunda7.messageevents;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class IntermediateMessageEve implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		System.out.println("intermediate Message Events");
		// TODO Auto-generated method stub
		
	}

}
