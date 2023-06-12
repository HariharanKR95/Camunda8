package com.wedbush.camunda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.camunda.zeebe.client.ZeebeClient;

@RestController
public class Order_Fulfillment_Controller {
	
	@Autowired
	ZeebeClient client;
	
	@PostMapping("/start/{message}")
	public String messaging(@PathVariable String message) {
		client.newPublishMessageCommand().messageName(message).correlationKey("").send().join();
		return "Message";
	}
	

	
	
	@GetMapping("/paymentreceive")
	public String paymentreceive() {
		String Message="payementrec";
		String correlationkey="2345";
		String str = Message+" "+correlationkey;
		client.newPublishMessageCommand().messageName(Message).correlationKey(correlationkey).send().join();

		return str;
	}
	@GetMapping("/cancelreceived")
	public String cancelreceive() {
		String Message="cancelrec";
		String correlationkey="234";
		String str = Message+" "+correlationkey;
		client.newPublishMessageCommand().messageName(Message).correlationKey(correlationkey).send().join();

		return str;
	}
}

//@PostMapping("/{receivedorder}/{key}")
//public String confirmationreceived(@PathVariable String receivedorder,String key) {
//	client.newPublishMessageCommand().messageName(receivedorder).correlationKey(key).send().join();
//	return "confirmationreceivedMessage";
//}
//@PostMapping("/{payementrec}/{key}")
//public String paymentreceived(@PathVariable String payementrec,String key) {
//	client.newPublishMessageCommand().messageName(payementrec).correlationKey(key).send().join();
//	return "paymentreceivedMessage";
//}

