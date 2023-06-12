package com.example.demo.worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.pojo.ResponsePojo;
import com.example.demo.pojo.RootPojo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
public class WorkerClass {

	@Autowired
	ZeebeClient zeebeclient;

	final RestTemplate rest = new RestTemplate();

	Logger logger = LoggerFactory.getLogger(WorkerClass.class);

	String url = "http://localhost:2222/";
	Map<String, Object> finalResponse = new HashMap<>();
	Map<String, Object> variableAsMap = null;
	Map<String, Object> resultantMap = new HashMap<>();

	@ZeebeWorker(type = "employe", name = "employe")
	public Object empJsonWorker(final JobClient client, final ActivatedJob job) {
		String endPoint = "employe1";
		try {
			logger.info("employe worker job activated ");
			variableAsMap = job.getVariablesAsMap();

			ResponseEntity<String> response = rest.getForEntity(url + endPoint, String.class);
			String responseBody = response.getBody();
			System.out.println(responseBody);

			ObjectMapper mapper = new ObjectMapper();
			RootPojo rootpojo = mapper.readValue(responseBody, RootPojo.class);
			variableAsMap.put("employee", rootpojo.getEmployee());

			client.newCompleteCommand(job.getKey()).variables(variableAsMap).send().join();
			logger.info("employe worker job completed");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());

			Map<String, Object> errmssg = new HashMap<>();
			errmssg.put("errormessage", e.getMessage());
			errmssg.put("Error occurred", endPoint);
			errmssg.put("API name", url + endPoint);
			finalResponse.put("finalResponseemploye", errmssg);

			zeebeclient.newSetVariablesCommand(job.getProcessInstanceKey()).variables(finalResponse).send().join();

			client.newThrowErrorCommand(job.getKey()).errorCode("Error_Occurred")
					.errorMessage("Error_Occurred in employe job").send().join();
			client.newCompleteCommand(job.getKey()).variables(finalResponse).send().join();
		}
		return null;

	}

	@ZeebeWorker(type = "address")
	public Object addressJsonWorker(final JobClient client, final ActivatedJob job) {
		try {
			logger.info("address worker job activated ");
			variableAsMap = job.getVariablesAsMap();
			String endPoint = "address1";
			ResponseEntity<String> response = rest.getForEntity(url + endPoint, String.class);
			String responseBody = response.getBody();
			System.out.println(responseBody);
			ObjectMapper mapper = new ObjectMapper();
			RootPojo rootpojo = mapper.readValue(responseBody, RootPojo.class);
			variableAsMap.put("address", rootpojo.getAddress());

			client.newCompleteCommand(job.getKey()).variables(variableAsMap).send().join();
			logger.info("address worker job completed");
		} catch (Exception e) {
			e.printStackTrace();

			Map<String, Object> errmssg = new HashMap<>();
			errmssg.put("errormessage", e.getMessage());
			errmssg.put("Error occurred", "address");
			errmssg.put("API name", url + "address");
			finalResponse.put("finalResponseaddress", errmssg);

			zeebeclient.newSetVariablesCommand(job.getProcessInstanceKey()).variables(finalResponse).send().join();

			client.newThrowErrorCommand(job.getKey()).errorCode("Error_Occurred")
					.errorMessage("Error_Occurred in address job").send().join();
			client.newCompleteCommand(job.getKey()).variables(finalResponse).send().join();
		}
		return null;

	}

	@ZeebeWorker(type = "id")
	public Object idJsonWorker(final JobClient client, final ActivatedJob job) {
		try {
			logger.info("id worker job activated ");
			variableAsMap = job.getVariablesAsMap();

			ResponseEntity<String> response = rest.getForEntity(url + "id", String.class);
			String responseBody = response.getBody();
			System.out.println(responseBody);
			ObjectMapper mapper = new ObjectMapper();
			RootPojo rootpojo = mapper.readValue(responseBody, RootPojo.class);
			variableAsMap.put("id", rootpojo.getIdProof());

			client.newCompleteCommand(job.getKey()).variables(variableAsMap).send().join();
			logger.info("id worker job completed");
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> errmssg = new HashMap<>();
			errmssg.put("errormessage", e.getMessage());
			errmssg.put("Error occurred", "id");
			errmssg.put("API name", url + "id");
			finalResponse.put("finalResponseid", errmssg);

			zeebeclient.newSetVariablesCommand(job.getProcessInstanceKey()).variables(finalResponse).send().join();
			client.newThrowErrorCommand(job.getKey()).errorCode("Error_Occurred")
					.errorMessage("Error_Occurred in id job").send().join();
			client.newCompleteCommand(job.getKey()).variables(finalResponse).send().join();
		}
		return null;
	}

	@ZeebeWorker(type = "job4")
	public Object allJsonWorker(final JobClient client, final ActivatedJob job) {
		try {
			logger.info("all worker job activated ");
			variableAsMap = job.getVariablesAsMap();
			ObjectMapper mapper = new ObjectMapper();
			List<ResponsePojo> addressli = mapper.convertValue(variableAsMap.get("address"),
					new TypeReference<List<ResponsePojo>>() {
					});
			List<ResponsePojo> employeeli = mapper.convertValue(variableAsMap.get("employee"),
					new TypeReference<List<ResponsePojo>>() {
					});
			List<ResponsePojo> idli = mapper.convertValue(variableAsMap.get("id"),
					new TypeReference<List<ResponsePojo>>() {
					});
			Map<Integer, ResponsePojo> mapresponse = new HashMap<Integer, ResponsePojo>();
//			addressli.forEach((value)->{
//				mapresponse.put(value.getId(), value);
//				
//			});	

			addressli.forEach((addressInfo) -> {
				mapresponse.put(addressInfo.getId(), addressInfo);
				Integer addRess = addressInfo.getId();
				ResponsePojo responsepojo = mapresponse.get(addRess);

				logger.info("response pojo" + mapresponse.get(addRess));
				responsepojo.setState(addressInfo.getState());
				responsepojo.setCity(addressInfo.getCity());
				mapresponse.put(addRess, responsepojo);
				logger.info("after mapping" + mapresponse);
			});
			employeeli.forEach((empInfo) -> {
				Integer emp = empInfo.getId();

				ResponsePojo responsepojo = mapresponse.get(emp);

				responsepojo.setName(empInfo.getName());
				mapresponse.put(emp, responsepojo);

			});
			idli.forEach((idinfo) -> {

				Integer id = idinfo.getId();

				ResponsePojo responsepojo = mapresponse.get(id);

				responsepojo.setAadharNumber(idinfo.getAadharNumber());
				responsepojo.setPan(idinfo.getPan());
				mapresponse.put(id, responsepojo);
				logger.info("mapresponsejob4" + mapresponse);

			});

			Map<String, Object> finalResponse = new HashMap<>();
			finalResponse.put("finalData", mapresponse);
			System.out.println("in mapresponse" + mapresponse);

			String data = mapper.writeValueAsString(mapresponse);
			logger.info("data" + data);
			client.newCompleteCommand(job.getKey()).variables(finalResponse).send().join();

			logger.info("all json worker ended");

		} catch (Exception e) {
			e.printStackTrace();

			Map<String, Object> errmssg = new HashMap<>();
			errmssg.put("errormessage", e.getMessage());
			errmssg.put("Error occurred", "job4");
			errmssg.put("API name", url + "job4");
			finalResponse.put("finalResponsejob4", errmssg);

			zeebeclient.newSetVariablesCommand(job.getProcessInstanceKey()).variables(finalResponse).send().join();

			client.newThrowErrorCommand(job.getKey()).errorCode("Error_Occurred")
					.errorMessage("Error_Occurred in all job").send().join();
			client.newCompleteCommand(job.getKey()).variables(finalResponse).send().join();
		}
		return null;

	}

	@ZeebeWorker(type = "service2", name = "service2")
	public void servicejobtask2(final JobClient client, final ActivatedJob job) {

		System.out.println("service2 started");
		List<Object> list = new ArrayList<>();
		variableAsMap = job.getVariablesAsMap();
		for (Entry<String, Object> map : variableAsMap.entrySet()) {
			String key = map.getKey();
			Object value = map.getValue();

			if (key.contains("Response")) {
				list.add(value);
			}

		}
		variableAsMap.put("error", list);

		client.newCompleteCommand(job.getKey()).variables(variableAsMap).send().join();

		System.out.println("service2 ended");

	}

}
