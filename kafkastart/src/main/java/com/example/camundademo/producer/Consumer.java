package com.example.camundademo.producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;



import io.camunda.zeebe.client.ZeebeClient;



@Service
public class Consumer {



 @Autowired
  ZeebeClient zeebe;



 @KafkaListener(topics = "zeebe", groupId = "mygroup")
  public String consumeFromTopic(String message) {
    System.out.println("Consummed message " + message);
    zeebe.newPublishMessageCommand().messageName(message).correlationKey("").send();
    return message;



 }



}