package com.Camunda.MultipleInstance.Controller;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.camunda.zeebe.client.ZeebeClient;

@RestController

public class CamundaController {

	@Autowired
	ZeebeClient client;

	@GetMapping("/firstApi")
	public String firstApiCalled() {

		String url = "http://localhost:2078/secondApi";
		RestTemplate rest = new RestTemplate();

		ResponseEntity<String> response = rest.getForEntity(url, String.class);
		String vardemo = response.getBody();

		return vardemo;

	}

	@GetMapping("/secondApi")

	public void secondApicalled() throws InterruptedException {
		Timestamp startTime = new Timestamp(System.currentTimeMillis());

		long j = startTime.getTime();
		Map<String, Object> variablemap = new HashMap<>();
		variablemap.put("starttime", j);

		client.newCreateInstanceCommand().bpmnProcessId("Multiple-Instance").latestVersion().variables(variablemap)
				.send().join();

		System.out.println();

	}

	
	
//	  @GetMapping("/subprocess")
//	  
//	  public Map <String,Object>demo(@PathVariable Integer act){
//		  Map <String,Object>mapper=new HashMap<>();
//		  mapper.put("getActivity", act);
//		  System.out.println(mapper);
//		  client.newCreateInstanceCommand().bpmnProcessId("Process_0adk8gl").latestVersion().send().join();
//		return mapper;

//	@GetMapping("/first")
//	public String getAdvisorjson() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
//		JSONParser parser = new JSONParser();
//		Object obj = parser.parse(new FileReader("C:\\Users\\gayathri.2140387\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\today\\Activitiesdemo\\src\\main\\resources\\json\\Transaction"));
//		JSONObject jsonObject = (JSONObject) obj;
//		System.out.println(jsonObject);
//		return jsonObject.toString();
//	  }
//	
//	
//	@GetMapping("/second")
//	public String getAdvisorjson2() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
//		JSONParser parser = new JSONParser();
//		Object obj = parser.parse(new FileReader("C:\\Users\\gayathri.2140387\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\today\\Activitiesdemo\\src\\main\\resources\\json\\Cash"));
//		JSONObject jsonObject = (JSONObject) obj;
//		System.out.println(jsonObject);
//		return jsonObject.toString();
//	  }

//	@GetMapping("/third")
//	public String getAdvisorjson3() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
//		JSONParser parser = new JSONParser();
//		Object obj = parser.parse(new FileReader("C:\\Users\\gayathri.2140387\\Documents\\workspace-spring-tool-suite-4-4.13.0.RELEASE\\today\\Activitiesdemo\\src\\main\\resources\\json\\stock"));
//		JSONObject jsonObject = (JSONObject) obj;
//		System.out.println(jsonObject);
//		return jsonObject.toString();
//	  }
//	
//	
//
//	  @GetMapping("/startProcess") 
//	  public Map startWorkflowInstance() { //
//	 // ProcessInstanceResult workflowInstanceResult =
//	  client.newCreateInstanceCommand() .bpmnProcessId("Mainprocess").latestVersion().send().join(); 
//	  return null ;
//	  //workflowInstanceResult.getVariablesAsMap().get("accountdetails"); 
//	  }
//	  
//	  

}

//	  @GetMapping("/getActivity/{abc}")
//	  
//	  public Map <String,Object>demo(@PathVariable Integer act){
//		  Map <String,Object>mapper=new HashMap<>();
//		  mapper.put("getActivity", act);
//		  System.out.println(mapper);
//		  client.newCreateInstanceCommand().bpmnProcessId("Process_1djgpxh").latestVersion().send().join();
//		return mapper;
//	  
//	  }
//	  
//	  
//	  
//	
//	
//	
//	
//	@GetMapping("/first")
//	public String getAdvisorjson() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
//		JSONParser parser = new JSONParser();
//		Object obj = parser.parse(new FileReader("D:\\Activities\\activities.json"));
//		JSONObject jsonObject = (JSONObject) obj;
//		System.out.println(jsonObject);
//		return jsonObject.toString();
//	}
//}
//	
//	
//	
////	@GetMapping("/second")
////	public String getAdvisorjson1() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
////		JSONParser parser = new JSONParser();
////		Object obj = parser.parse(new FileReader("D:\\Household\\Transaction.json"));
////		JSONObject jsonObject = (JSONObject) obj;
////		System.out.println(jsonObject);
////		return jsonObject.toString();
////	}
////	
////
////	
////	
////	@GetMapping("/third")
////	public String getAdvisorjson2() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
////		JSONParser parser = new JSONParser();
////		Object obj = parser.parse(new FileReader("D:\\Household\\Cash.json"));
////		JSONObject jsonObject = (JSONObject) obj;
////		System.out.println(jsonObject);
////		return jsonObject.toString();
////	}
////	
////	@GetMapping("/four")
////	public String getAdvisorjson3() throws FileNotFoundException, IOException, ParseException, org.json.simple.parser.ParseException {
////		JSONParser parser = new JSONParser();
////		Object obj = parser.parse(new FileReader("D:\\Household\\Stock.json"));
////		JSONObject jsonObject = (JSONObject) obj;
////		System.out.println(jsonObject);
////		return jsonObject.toString();
////	}
////	
////}
//
//
//
//
//
//
//
//
//
//
