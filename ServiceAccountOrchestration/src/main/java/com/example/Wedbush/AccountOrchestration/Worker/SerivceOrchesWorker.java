package com.example.Wedbush.AccountOrchestration.Worker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.Wedbush.AccountOrchestration.Model.Client.Clientpojo;
import com.example.Wedbush.AccountOrchestration.Model.Client.ResponsePojo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
public class SerivceOrchesWorker {
	
	final RestTemplate rest= new RestTemplate();
	
	
	@ZeebeWorker(type = "Employee", name = "Employee")
	public void Task1(final JobClient client, final ActivatedJob job) 
			throws JsonMappingException, JsonProcessingException 
	{
		System.out.println("Worker1 Started");
		System.out.println("Worker1......:"+job.getVariablesAsMap());
		
		Map<String, Object> instanceVariableMap=job.getVariablesAsMap();
			
	
		String url ="http://localhost:9090/employee";
		//System.out.println("Worker1 Started");
		
		//Map<String, String> map = new HashMap<>();
		
		ResponseEntity<String>response = rest.getForEntity(url, String.class);
	
		String variable = response.getBody();
		System.out.println(variable);
		ObjectMapper am = new ObjectMapper();
		//ActivityAccountPojo accounts1=am.readValue(Accapi, ActivityAccountPojo.class);
		Clientpojo employee=am.readValue(variable, Clientpojo.class);
		instanceVariableMap.put("accountInfoListFromWorker1", employee.getEmployee());
	   
	   
//		Map<String, Object> w1 = job.getVariablesAsMap();
//		w1.put("w1 response", accounts);
//		System.out.println("Worker1...:"+w1);
//	    client.newCompleteCommand(job.getKey()).variables(Accapi).send().join();
	    
			client.newCompleteCommand(job.getKey()).variables(instanceVariableMap).send().join();

			 System.out.println("Worker1 Ended");
			 
	}
	
    //Worker2  
	@ZeebeWorker(type = "Address", name = "Address")
	public void Task2(final JobClient client, final ActivatedJob job) throws JsonProcessingException 
	{
		
		
		System.out.println("Worker2 Started");
		//Map<String, Object> map=job.getVariablesAsMap();
		System.out.println("Worker2........."+job.getVariablesAsMap());
		Map<String, Object> instanceVariableMap=job.getVariablesAsMap();
		
		String url ="http://localhost:9090/address";
		
		
		ResponseEntity<String>response = rest.getForEntity(url, String.class);
	
		String variable = response.getBody();
	    ObjectMapper am = new ObjectMapper();
	    //ActivityTranscationPojo transactions=am.readValue(Trans, ActivityTranscationPojo.class);
		//AccountInfo clientin = mapper.readValue(Client, AccountInfo.class);
	    Clientpojo address=am.readValue(variable, Clientpojo.class);
	    
	    instanceVariableMap.put("accountInfoListFromWorker2", address.getAddress());
	    
	    
	   // System.out.println("Worker1: " +c);
//
		client.newCompleteCommand(job.getKey()).variables(instanceVariableMap).send().join();

		 System.out.println("Worker2 Ended");

	}
	
	//worker3
	@ZeebeWorker(type = "Id", name = "Id")
	public void Task3(final JobClient client, final ActivatedJob job) throws JsonMappingException, JsonProcessingException 
	{
		System.out.println("Worker3 Started");
		
		Map<String, Object> instanceVariableMap=job.getVariablesAsMap();
		System.out.println("Worker3........:"+job.getVariablesAsMap());
		String url ="http://localhost:9090/idproof";
		
		ResponseEntity<String>response = rest.getForEntity(url, String.class);
	
		String variable = response.getBody();
		  ObjectMapper am = new ObjectMapper();

		   Clientpojo idproof=am.readValue(variable, Clientpojo.class);
		    instanceVariableMap.put("accountInfoListFromWorker3", idproof.getIdProof());
		    String json = new ObjectMapper().writeValueAsString(instanceVariableMap);
		    
			System.out.println("Worker3.1....:"+json);
		   
		    
		   // System.out.println("Worker1: " +c);
	//
			client.newCompleteCommand(job.getKey()).variables(instanceVariableMap).send().join();

			
			 System.out.println("Worker3 Ended");

	}

//	@ZeebeWorker(type = "GetCash", name = "GetCash")
//	public void Task4(final JobClient client, final ActivatedJob job) throws JsonMappingException, JsonProcessingException 
//	{
//		System.out.println("Worker4 Started");
//		
//		Map<String, Object> instanceVariableMap=job.getVariablesAsMap();
//		System.out.println("Worker4..........:"+job.getVariablesAsMap());
//		String url ="http://localhost:9090/getcash";
//	
//		//Map<String, String> map2 = new HashMap<>();
//		
//		ResponseEntity<String>response = rest.getForEntity(url, String.class);
//	
//		String cashv = response.getBody();
//		ObjectMapper am = new ObjectMapper();
//		  ActivityCashPojo cashMovements=am.readValue(cashv, ActivityCashPojo.class);
//			//AccountInfo clientin = mapper.readValue(Client, AccountInfo.class);
//		  Map<String, Object> w4 = job.getVariablesAsMap();
//			
//			w4.put("w4response", cashMovements);
//			System.out.println("Worker4:.........."+w4);
//		    client.newCompleteCommand(job.getKey()).variables(cashv).send().join();
//		 ResponseInfo cashMovements=am.readValue(cashv, ResponseInfo.class);
//		    instanceVariableMap.put("accountInfoListFromWorker4", cashMovements.getCashMovements());
//		    String json = new ObjectMapper().writeValueAsString(instanceVariableMap);
//		    
//			System.out.println("Worker4.1....:"+json);
//		   
//		    
//		   // System.out.println("Worker1: " +c);
//	//
//			client.newCompleteCommand(job.getKey()).variables(instanceVariableMap).send().exceptionally(throwable->{
//				throw new RuntimeException("Could not comple job"+job, throwable);
//			});
//
//		  
//		  System.out.println("Worker4 Ended");
//
//
//	}

	@ZeebeWorker(type = "generatePayLoad", name = "generatePayLoad")
	public void Task5(final JobClient client, final ActivatedJob job) throws JsonProcessingException 
	{
		
				
		System.out.println("Final worker started");
		
		Map<String,Object> instanceVariableMap =job.getVariablesAsMap();
        
		ObjectMapper am=new ObjectMapper();
		
		List<ResponsePojo> accinfoList1 =am.convertValue(instanceVariableMap.get("accountInfoListFromWorker1"),
	    		new TypeReference<List<ResponsePojo>>() {
			
	    });
		List<ResponsePojo> accinfoList2 =am.convertValue(instanceVariableMap.get("accountInfoListFromWorker2"),
	    		new TypeReference<List<ResponsePojo>>() {
			
	    });
		List<ResponsePojo> accinfoList3 =am.convertValue(instanceVariableMap.get("accountInfoListFromWorker3"),
	    		new TypeReference<List<ResponsePojo>>() {
			
	    });
//		List<ResponsePojo> accinfoList4 =am.convertValue(instanceVariableMap.get("accountInfoListFromWorker4"),
//	    		new TypeReference<List<ResponsePojo>>() {
//			
//	    });
		Map<Integer, ResponsePojo> accntInfoList1Map = new HashMap<Integer, ResponsePojo>();
		
		accinfoList1.forEach((responseInfo)->{
			
			accntInfoList1Map.put(responseInfo.getId(), responseInfo);
			
			
		});
		System.out.println("oneeeeeee");
		
		System.out.println("accinfoList1"+ accinfoList1);
		System.out.println("accinfoList2"+ accinfoList2);
		System.out.println("accinfoList3"+ accinfoList3);
		
		accinfoList2.forEach((accountInfo) -> {
			Integer addRess = accountInfo.getId();	
			
			ResponsePojo accInfoFromMap=accntInfoList1Map.get(addRess); 
//			accInfoFromMap.setAccountName(accountInfo.getAccountName());
//			accInfoFromMap.setAccountNumber(accountInfo.getAccountNumber());
//			accInfoFromMap.setAccountType(accountInfo.getAccountType());
            
			accInfoFromMap.setState(accountInfo.getState());
			accInfoFromMap.setCity(accountInfo.getCity());
							
			
			accntInfoList1Map.put(addRess, accInfoFromMap);
		});
		
		System.out.println("twoooooo");
		
		String json = new ObjectMapper().writeValueAsString(instanceVariableMap);
		System.out.println("print final...."+json);
		
		accinfoList3.forEach((accInfo1)->{
			Integer idProof= accInfo1.getId(); 

		ResponsePojo accinfofromMap1=accntInfoList1Map.get(idProof);
		accinfofromMap1.setAadharNumber(accInfo1.getAadharNumber());
		accinfofromMap1.setPan(accInfo1.getPan());
		accntInfoList1Map.put(idProof, accinfofromMap1);
		});
		System.out.println("threeeeeee");
		
		String json1 = new ObjectMapper().writeValueAsString(instanceVariableMap);
		System.out.println("print final2...."+json1);

		

//		accinfoList4.forEach((accInfo2)->{
//			Integer accnumber1= accInfo2.getAccountId(); 
//
//			ActivityResponsePojo accinfofromMap1=accntInfoList1Map.get(accnumber1);
//			accinfofromMap1.setCashMovementDescription(accInfo2.getCashMovementDescription());
//			accinfofromMap1.setNetAmount(accInfo2.getNetAmount());
//			accntInfoList1Map.put(accnumber1, accinfofromMap1);
//			});
		String jsons =new ObjectMapper().writeValueAsString(accntInfoList1Map);
		System.out.println("All values :"+jsons);
		System.out.println("Consolidated worker Completed");
		client.newCompleteCommand (job.getKey()).variables (accntInfoList1Map).send().exceptionally (throwable -> 
		{ throw new RuntimeException("Could not complete job" + job, throwable);

		});

			
		
	//	client.newCompleteCommand(job.getKey()).variables(map).send().join();
		
		System.out.println("Flow ended");
		
		
	}

	

}
