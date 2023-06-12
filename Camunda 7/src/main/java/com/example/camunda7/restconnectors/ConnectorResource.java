package com.example.camunda7.restconnectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectorResource {
	
	/*
	 * @RequestMapping
	 * 
	 * @GetMapping("/check") public void israiny() { System.out.println("jgvhg");
	 * //return israiny(); }
	 */
	@GetMapping("/hello")
    public String index() {

        return "true";
    }
}
