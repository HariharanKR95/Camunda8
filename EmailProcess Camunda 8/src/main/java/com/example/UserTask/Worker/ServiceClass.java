package com.example.UserTask.Worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.camunda.zeebe.client.ZeebeClient;


@Service
public class ServiceClass {

  @Autowired
  private ZeebeClient zeebeclient;

  public String MessageTriggerTask(String email, String key) {

    zeebeclient.newPublishMessageCommand().messageName(email).correlationKey(key).send();

    return email;

    //return Message +" is Triggred Workflow Successfully"+newline+ " Process Instance Id is : "+z.ins+newline+"BPMN Process id is : "+z.i ;

  }
}