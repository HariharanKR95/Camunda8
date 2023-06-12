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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.demo.pojo.ResponsePojo;
import com.example.demo.pojo.RootPojo;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
		String endPoint = "employe";
		try {
			logger.info("employe worker job activated ");
			variableAsMap = job.getVariablesAsMap();

			ResponseEntity<String> response = rest.getForEntity(url + endPoint, String.class);
			String responseBody = response.getBody();
			System.out.println(responseBody);

			ObjectMapper mapper = new ObjectMapper();
			RootPojo rootpojo = mapper.readValue(responseBody, RootPojo.class);
			variableAsMap.put("employeeData", rootpojo.getEmployee());

			client.newCompleteCommand(job.getKey()).variables(variableAsMap).send().join();
			logger.info("employe worker job completed");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			Map<String, Object> errmssg = new HashMap<>();

			if (HttpStatus.NOT_FOUND != null) {
				finalResponse.put("Employee404", "Not_Found");
			} else {
				errmssg.put("errormessage", e.getMessage());
				errmssg.put("Error occurred", endPoint);
				errmssg.put("API name", url + endPoint);
				finalResponse.put("finalResponseemploye", errmssg);
			}

			client.newCompleteCommand(job.getKey()).variables(finalResponse).send().join();
		}
		return null;

	}

	@ZeebeWorker(type = "address")
	public Object addressJsonWorker(final JobClient client, final ActivatedJob job) {
		try {
			logger.info("address worker job activated ");
			variableAsMap = job.getVariablesAsMap();
			String endPoint = "address";
			ResponseEntity<String> response = rest.getForEntity(url + endPoint, String.class);
			String responseBody = response.getBody();
			System.out.println(responseBody);
			ObjectMapper mapper = new ObjectMapper();
			RootPojo rootpojo = mapper.readValue(responseBody, RootPojo.class);
			variableAsMap.put("addressData", rootpojo.getAddress());

			client.newCompleteCommand(job.getKey()).variables(variableAsMap).send().join();
			logger.info("address worker job completed");
		} catch (Exception e) {
			e.printStackTrace();

			Map<String, Object> errmssg = new HashMap<>();
			if (HttpStatus.NOT_FOUND != null) {
				finalResponse.put("Address404", "Not_Found");
			} else {
				errmssg.put("errormessage", e.getMessage());
				errmssg.put("Error occurred", "address");
				errmssg.put("API name", url + "address");
				finalResponse.put("finalResponseaddress", errmssg);
			}

			client.newCompleteCommand(job.getKey()).variables(finalResponse).send().join();
		}
		return null;

	}

	@ZeebeWorker(type = "id")
	public Object idJsonWorker(final JobClient client, final ActivatedJob job) {
		try {
			logger.info("id worker job activated ");
			variableAsMap = job.getVariablesAsMap();
			String endPoint = "id";
			ResponseEntity<String> response = rest.getForEntity(url + endPoint, String.class);
			String responseBody = response.getBody();
			System.out.println(responseBody);
			ObjectMapper mapper = new ObjectMapper();
			RootPojo rootpojo = mapper.readValue(responseBody, RootPojo.class);
			variableAsMap.put("idData", rootpojo.getIdProof());

			client.newCompleteCommand(job.getKey()).variables(variableAsMap).send().join();
			logger.info("id worker job completed");
		} catch (Exception e) {
			e.printStackTrace();
			Map<String, Object> errmssg = new HashMap<>();
			if (HttpStatus.NOT_FOUND != null) {
				finalResponse.put("Id404", "Not_Found");
			} else {
				errmssg.put("errormessage", e.getMessage());
				errmssg.put("Error occurred", "id");
				errmssg.put("API name", url + "id");
				finalResponse.put("finalResponseid", errmssg);
			}

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
			List<ResponsePojo> addressli = mapper.convertValue(variableAsMap.get("addressData"),
					new TypeReference<List<ResponsePojo>>() {
					});
			System.out.println(addressli);
			List<ResponsePojo> employeeli = mapper.convertValue(variableAsMap.get("employeeData"),
					new TypeReference<List<ResponsePojo>>() {
					});
			System.out.println(employeeli);
			List<ResponsePojo> idli = mapper.convertValue(variableAsMap.get("idData"),
					new TypeReference<List<ResponsePojo>>() {
					});
			Map<Integer, ResponsePojo> mapresponse = new HashMap<Integer, ResponsePojo>();
			Map<Object, Object> mapres = new HashMap<Object, Object>();
			// variableAsMap.containsKey("Employee404") ||
			System.out.println("167      error        " + variableAsMap.containsKey("Employee404"));
			if (variableAsMap.containsKey("Employee404")) {
				// if(employeeli==null) {
//				mapres.put(variableAsMap.get("employeeData"), null);
//				finalResponse.put("Dataemp", mapres);
				mapper.setSerializationInclusion(Include.ALWAYS);
			} else {
				employeeli.forEach((empInfo) -> {
					// if (addressli == null && idli == null) {
					mapresponse.put(empInfo.getId(), empInfo);
					// }
					Integer emp = empInfo.getId();

					ResponsePojo responsepojo = mapresponse.get(emp);

					responsepojo.setName(empInfo.getName());
					mapresponse.put(emp, responsepojo);

				});
			}

			if (variableAsMap.containsKey("Address404")) {
				// if(addressli==null) {
//				mapres.put(variableAsMap.get("Address404"), null);
//				finalResponse.put("DataAdd", mapres);
				mapper.setSerializationInclusion(Include.ALWAYS);
			} else {
				addressli.forEach((addressInfo) -> {
					if (employeeli == null) {
						mapresponse.put(addressInfo.getId(), addressInfo);
					}
					Integer addRess = addressInfo.getId();
					ResponsePojo responsepojo = mapresponse.get(addRess);

					logger.info("response pojo" + mapresponse.get(addRess));
					responsepojo.setState(addressInfo.getState());
					responsepojo.setCity(addressInfo.getCity());
					mapresponse.put(addRess, responsepojo);
					logger.info("after mapping" + mapresponse);
				});
			}

			if (variableAsMap.containsKey("Id404")) {
				// if(idli==null) {
//				mapres.put(variableAsMap.get("idData"), null);
//				finalResponse.put("DataId", mapres);
				mapper.setSerializationInclusion(Include.ALWAYS);
			} else {
				idli.forEach((idinfo) -> {
					if (employeeli == null && addressli == null) {
						mapresponse.put(idinfo.getId(), idinfo);
					}
					Integer id = idinfo.getId();

					ResponsePojo responsepojo = mapresponse.get(id);

					responsepojo.setAadharNumber(idinfo.getAadharNumber());
					responsepojo.setPan(idinfo.getPan());
					mapresponse.put(id, responsepojo);
					logger.info("mapresponsejob4" + mapresponse);

				});
			}

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

			if (key.contains("finalData")) {
				list.add(value);
			}
//			else {
//				list.add(value);	
//			}
		}
		variableAsMap.put("error", list);
		// client.newCompleteCommand(job.getKey()).send().join();
		client.newCompleteCommand(job.getKey()).variables(variableAsMap).send().join();

		System.out.println("service2 ended");

	}

}
