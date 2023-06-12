package com.kafkaExample.consumer;

import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
@Service
@EnableKafka
public class KafkaConsumer {

	@KafkaListener(topics = "helloTopic",groupId = "group_id")
	public String Message(String message)
	{
		System.out.println("comsumed message: "+message);
		return message;
	}
}
