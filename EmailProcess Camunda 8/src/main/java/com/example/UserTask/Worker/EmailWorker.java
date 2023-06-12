package com.example.UserTask.Worker;

import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.client.RestTemplate;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
public class EmailWorker {
	
	  

//	  @Autowired
//	  ZeebeClient zeebeClient;

	  

	  final RestTemplate rest = new RestTemplate();
	  @Autowired
	    private JavaMailSender javaMailSender;
	
	  @ZeebeWorker(type = "io.camunda.zeebe:userTask")
	  public void handleJob(final JobClient client, final ActivatedJob job) throws MessagingException {
    	Map<String, Object> variablesAsMap = job.getVariablesAsMap();
    	
		
    	String sender ="ashokkumar4147@gmail.com" ;	
      String receiver = "ashokkumarpalanisamy97@gmail.com";
      String subject = "Complete job";
      String body ="<p><a href=\"http://localhost:9191/email/123\">Click Here</a></p>";

    	
    	sendMail(sender, receiver, subject, body);
      String resultMessage = "Mail Sent Successfully to " + receiver;

     HashMap<String, Object> variables = new HashMap<>();
     variables.put("result", resultMessage);
//
    
 	  
 	    client.newCompleteCommand(job.getKey()).variables(variables).send().join();
 		 System.out.println("Worker1 Ended");
 	  }
	
	  
	  private void sendMail(String sender, String receiver, String subject, String body) throws MessagingException {
	     MimeMessage message = javaMailSender.createMimeMessage();

	        MimeMessageHelper helper = new MimeMessageHelper(message, true);
	        helper.setFrom(sender);
	        helper.setTo(receiver);
	        helper.setSubject(subject);
	        helper.setText(body, true); 
	        javaMailSender.send(message);
	    }

	  
}
