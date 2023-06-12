package com.example.demo.controller;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParser;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceResult;

@RestController

public class ControllerServiceClass {
	@Autowired
	ZeebeClient client;

	@Autowired
	RestTemplate rest;

//	@PostMapping("/start")
//	public Map<String, Object> task() {
//
//		ProcessInstanceResult result = client.newCreateInstanceCommand().bpmnProcessId("Error_404")
//				.latestVersion().withResult().requestTimeout(Duration.ofMillis(100000)).send().join();
//		// client.newCancelInstanceCommand("2251799813707693")
//		Map<String, Object> map = (Map) result.getVariablesAsMap().get("finalResponse");
//
//		return map;

//	}

	@PostMapping("/cancel")
	public String task1() {

		long processid = 2251799813685513l;
		client.newCancelInstanceCommand(processid).send();

		return "Cancelled instance";

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/starterror")
	public List<Object> task2() throws ParseException {

		ProcessInstanceResult res = client.newCreateInstanceCommand().bpmnProcessId("Error_404").latestVersion()
				.withResult().requestTimeout(Duration.ofMillis(100000)).send().join();

		Map<String, Object> map = (Map) res.getVariablesAsMap();
		List<Object> list = new ArrayList<>();
		for (Entry<String, Object> mapResponse : map.entrySet()) {
			if (mapResponse.getKey().contains("error")) {
				list.add(mapResponse.getValue());
			} else if (mapResponse.getKey().equals("finalData")) {
				// resultantMap.put("finalResponse", map.get("finalResponse"));
				list.add(mapResponse.getValue());
			}
		}

		return list;
	}
}
