package com.kafkaExample.configration;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;

public class kafkaconfig {
	
//	@Bean
//	public KafkaTemplate<String, String> kafkatemplate(){
//		return new KafkaTemplate<>(producerFactory());
//		
//	}
	
	@Bean
	public ConsumerFactory<String, String> consumerFactory(){
		
		Map<String, Object> configProps = new HashMap<String, Object>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG,"group_id");
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
		return new DefaultKafkaConsumerFactory<>(configProps);
		
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenercontainerFactory(){
	ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<String, String>();
	factory.setConsumerFactory(consumerFactory());
	return factory;
	}
}





 