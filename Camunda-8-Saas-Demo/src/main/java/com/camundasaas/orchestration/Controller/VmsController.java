package com.camundasaas.orchestration.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.camundasaas.orchestration.Model.Client.VendorRegistration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.auth.SaasAuthentication;
import io.camunda.tasklist.auth.SimpleAuthentication;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskList;
import io.camunda.tasklist.dto.TaskState;
import io.camunda.tasklist.exception.TaskListException;
import io.camunda.zeebe.client.ZeebeClient;

@RestController
public class VmsController {

	@Autowired
	ZeebeClient zeebeClient;

	final RestTemplate rest = new RestTemplate();

	@PostMapping("/startWorkFlow")
	public void startWorkFlow() throws Exception {
		System.out.println("starting");

//		JSONParser jsonparser = new JSONParser();
//		Object obj = jsonparser.parse(new FileReader("D:\\FileReader\\WounderSoft\\shopaid.txt"));
//		JSONObject jsonObject = (JSONObject) obj;
//		System.out.println(jsonObject);
//
//		String str = jsonObject.toString();
//		System.out.println("String===" + str);
//		Map jsonObjMap = jsonObject;
//		System.out.println(jsonObjMap);

		zeebeClient.newCreateInstanceCommand().bpmnProcessId("VendorManagementSystem").latestVersion().send().join();

		
		
		// client.newCreateInstanceCommand().bpmnProcessId("UserTask1").latestVersion().variables(str).send().join();
		System.out.println("started");
	}

	
	
	
	
	
	@CrossOrigin
	@RequestMapping(value = "/startWorkFlow", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json", consumes = "application/json")
	public String getVendorDetails(@RequestBody String reqBody) {
		System.out.println("Vendor Details : "+ reqBody);
		Map<String, Object> reqBodyMap = new HashMap<String, Object>();
		 ObjectMapper mapper = new ObjectMapper();
		 
		try {
			reqBodyMap = mapper.readValue(reqBody, Map.class);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		zeebeClient.newCreateInstanceCommand().bpmnProcessId("VendorManagementSystem").latestVersion().variables(reqBodyMap).send().join();
		System.out.println("flow  Started");
		
		return reqBody;
	}
	
	
	

	
///////////////////////// Get Active Task List ///////////////////////////////
	
	@GetMapping("/getCreatedTask")
	public TaskList getTask() throws TaskListException {
		
		String inputBaseUrl="https://bru-2.tasklist.camunda.io/1a8d8e18-4054-4bd2-afad-6f2adf8c58b8";
		String inputAuthUrl="https://login.cloud.camunda.io/oauth/token";
		String inputClientId = "jiIaOU5bGP1HJbyR3jZ.bhqsiCpTMTZZ";
		String inputClientSecret = "wz0YxMw.oapyIi48t8aUrqOMXfubR9953gBuwa8cMqMG-595cyhM16wPAhNIKdJf";
		
		SaasAuthentication sa = new SaasAuthentication(inputClientId, inputClientSecret);
		
		CamundaTaskListClient client = new CamundaTaskListClient.Builder().authentication(sa)
		    .taskListUrl("https://bru-2.tasklist.camunda.io/1a8d8e18-4054-4bd2-afad-6f2adf8c58b8").build();
		
		return client.getTasks(true, TaskState.CREATED, 50);
//		System.out.println("TaskList---: "+client.toString());
//
//		List<Task> getTaskList = client.getTasks(false, TaskState.CREATED, 50);
//		
//		 for(Object getTask : getTaskList ) {
//			 
//			 System.out.println(getTask.toString());
//			// Map getTaskAsMap = (Map) getTask;
//			 //System.out.println(getTaskAsMap);
//		 }
//		 System.out.println("TaskList---: "+getTaskList.toString());
//		
//		 return getTaskList;
		
//		SimpleAuthentication sa = new SimpleAuthentication("demo", "demo");
//
//		CamundaTaskListClient client = new CamundaTaskListClient.Builder().taskListUrl("http://localhost:8080")
//				.shouldReturnVariables().authentication(sa).build();
//
		//List<Task> tasks = client.getAssigneeTasks(null, TaskState.CREATED, null);
//		
//		System.out.println("Active Task List : "+tasks);
//
////		 Task task = client.getTask(jobkey);
		
	}


	
//////////////////////////////////////////////////



	
//////////////////////////////////////////////////
@CrossOrigin
@GetMapping("/getTaskList")
public List<Object> getTaskList () {

//HttpHeaders headers = new HttpHeaders();
//HttpEntity<String> reqEntity = new HttpEntity<String>(headers);
String url = "http://localhost:8081/getCreatedTask";
//ResponseEntity<Map> respEntity = rest.exchange(url, HttpMethod.POST,reqEntity,Map.class );

//Map getBody = respEntity.getBody();
//System.out.println(getBody);


RestTemplate restTemplate = new RestTemplate();
//List<Object> result = restTemplate.getForObject(url, List.class);

Map result = restTemplate.getForObject(url, Map.class);

List getItems = (List) result.get("items");

System.out.println("Items---"+getItems);


List<Object> finalTaskList = new ArrayList<Object>();

for(Object getList :getItems ) {

//System.out.println("getList--"+getList);

Map getListAsMap = (Map) getList;


String getId = (String) getListAsMap.get("id");
String getName = (String) getListAsMap.get("name");
String getAssignee = (String)getListAsMap.get("assignee");
String getProcessDefinitionId = (String) getListAsMap.get("processDefinitionId");


Map<String, Object> finalMap = new HashMap<String, Object>();

finalMap.put("id", getId);
finalMap.put("name", getName);
finalMap.put("assignee", getAssignee);
finalMap.put("processDefinitionId", getProcessDefinitionId);

finalTaskList.add(finalMap);

}
return finalTaskList;


}
@CrossOrigin
@PostMapping("/getapprove")
//@RequestMapping(value = "/getapprove", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json", consumes = "application/json")
public VendorRegistration approvedmail() throws Exception {

	String approve="Request Approved";
	System.out.println(approve);
	return null;
	

	// client.newSetVariablesCommand(Long.valueOf().variables(input).local(true).send().join();

}
@CrossOrigin
@RequestMapping(value = "/getrejected", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json", consumes = "application/json")
public VendorRegistration rejectedmail() throws Exception {

	String reject="Request Rejected";
	System.out.println(reject);
	return null;
	
		
}	


	
	@PostMapping("/helloo")
	public String hellomethod() {
		
		return "Hellooo";
		
	}
	
}
