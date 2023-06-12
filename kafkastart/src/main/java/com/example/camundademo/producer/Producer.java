package com.example.camundademo.producer;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;



@Service
public class Producer {
  public static final String topic = "zeebe";



 @Autowired
  private KafkaTemplate < String, Object > kafkaTemp;



 public String publishToTopic(String Message) {
    System.out.println("Publishing to topic " + topic);
    kafkaTemp.send(topic, Message);
    System.out.println(Message);
    return Message;



 }


 
}