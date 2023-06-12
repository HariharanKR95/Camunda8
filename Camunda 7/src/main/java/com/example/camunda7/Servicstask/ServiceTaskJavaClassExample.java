package com.example.camunda7.Servicstask;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ServiceTaskJavaClassExample implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
System.out.println("hi");
		
		String key = execution.getBusinessKey();
		String empName = (String)execution.getVariable("empNmae");
		System.out.println("key..:"+key);
		System.out.println("empname..:"+empName);
		execution.setVariable("empName", "rajesh");
	}

}
