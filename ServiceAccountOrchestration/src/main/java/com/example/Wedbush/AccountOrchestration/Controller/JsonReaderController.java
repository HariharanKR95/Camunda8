package com.example.Wedbush.AccountOrchestration.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Wedbush.AccountOrchestration.Model.AccountInfo;
import com.example.Wedbush.AccountOrchestration.Model.ActivityAccountPojo;
import com.example.Wedbush.AccountOrchestration.Model.ActivityCashPojo;
import com.example.Wedbush.AccountOrchestration.Model.ActivityResponsePojo;
import com.example.Wedbush.AccountOrchestration.Model.ActivityStockPojo;
import com.example.Wedbush.AccountOrchestration.Model.ActivityTranscationPojo;
import com.example.Wedbush.AccountOrchestration.Model.CashInfo;
import com.example.Wedbush.AccountOrchestration.Model.ResponseInfo;
import com.example.Wedbush.AccountOrchestration.Model.StockInfo;
import com.example.Wedbush.AccountOrchestration.Model.TranscationInfo;

import io.camunda.zeebe.client.ZeebeClient;

@RestController
public class JsonReaderController {

	@Autowired
	ZeebeClient client;
	
	@Autowired
	ActivityResponsePojo arp;
	
	@Autowired
	ResponseInfo ri;
	
	
	@Autowired
	ActivityAccountPojo aap;
	@Autowired
	AccountInfo ai;
	@Autowired
	ActivityCashPojo acp;
	@Autowired
	CashInfo ci;
	@Autowired
	ActivityStockPojo asp;
	@Autowired
	StockInfo si;
	@Autowired
	ActivityTranscationPojo atp;
	@Autowired
	TranscationInfo ti;
	
	
	
	
	

	// Start API :

	@PostMapping("/startCamunda")

	public String demo() {
	 System.out.println("Starting Camunda Flow...."); 
	 client.newCreateInstanceCommand().bpmnProcessId("Service_Orchestartion"). latestVersion().send();

	return "started successfully";

	}

	@GetMapping("/employee")
	// public Worker1 jsonFileReader2() throws FileNotFoundException, IOException,
	// ParseException {
	public String jsonFileReader1() throws FileNotFoundException, IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(new FileReader("D:\\STS-WORKSPACE\\Camunda-Poc\\ServiceAccountOrchestration\\src\\main\\resources\\Jsonfiles\\Employee.json"));
		JSONObject jsonObject = (JSONObject) obj;	
		return jsonObject.toString();

	}
	@GetMapping("/address")
	public String jsonFileReader2() throws FileNotFoundException, IOException, ParseException {
		
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(new FileReader("D:\\STS-WORKSPACE\\Camunda-Poc\\ServiceAccountOrchestration\\src\\main\\resources\\Jsonfiles\\Address.json"));
		JSONObject jsonObject = (JSONObject)obj;
		
		return jsonObject.toString();

	
	}

	
	@GetMapping("/idproof")
	public String jsonFileReader3() throws FileNotFoundException, IOException, ParseException {
		
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(new FileReader("D:\\STS-WORKSPACE\\Camunda-Poc\\ServiceAccountOrchestration\\src\\main\\resources\\Jsonfiles\\Id.json"));
		JSONObject jsonObject = (JSONObject)obj;
		//String string = (String)jsonObject;
		return jsonObject.toString();
		

	
	}
//	@GetMapping("/getcash")
//	public String jsonFileReader4() throws FileNotFoundException, IOException, ParseException {
//		
//		JSONParser jsonParser = new JSONParser();
//		Object obj = jsonParser.parse(new FileReader("D:\\WEDBUSH\\Eclipse\\Wedbush.AccountOrchestration\\Wedbush.AccountOrchestration\\src\\main\\resources\\Jsonfiles\\GetCash.json"));
//		JSONObject jsonObject = (JSONObject)obj;
//		
//		return jsonObject.toString();
//
//	
//	}
	


}
