package com.example.camunda7.restwithoutconnectors;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Service("demo")
@RestController
public class RestWithoutConnector implements JavaDelegate{

	@Autowired
	RestTemplate RT;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		String url = "http://localhost:8081/check";
		
		String obj1 = RT.getForObject(url, String.class);
		System.out.println(obj1);
	}

}
