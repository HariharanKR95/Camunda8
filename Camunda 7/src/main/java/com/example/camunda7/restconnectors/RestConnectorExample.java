package com.example.camunda7.restconnectors;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/show")
public class RestConnectorExample implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		
		
//		@RequestMapping(value = "/Show", method = RequestMethod.GET)
//		public boolean Show() {
//			
//			return Show();
//			
//		}
	}

}
