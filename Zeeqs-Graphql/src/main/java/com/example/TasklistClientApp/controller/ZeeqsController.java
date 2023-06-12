package com.example.TasklistClientApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.camunda.tasklist.CamundaTaskListClient;
import io.camunda.tasklist.auth.SimpleAuthentication;
import io.camunda.tasklist.dto.Form;
import io.camunda.tasklist.dto.Task;
import io.camunda.tasklist.dto.TaskState;
import io.camunda.tasklist.exception.TaskListException;

@RestController
public class ZeeqsController {
	final RestTemplate rest = new RestTemplate();
	Logger logger = LoggerFactory.getLogger(ZeeqsController.class);

	@PostMapping("/view/{bpmnProcessId}")
	public List<Object> getDummy(@PathVariable String bpmnProcessId)
			throws JsonMappingException, JsonProcessingException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		String str = "{\"query\":\"{\\r\\nprocessInstances(stateIn:[ACTIVATED]){\\r\\ntotalCount\\r\\nnodes{\\r\\nkey\\r\\nstate\\r\\nprocess{\\r\\nbpmnProcessId\\r\\n}\\r\\nelementInstances(stateIn:[ACTIVATED]){\\r\\nelementId\\r\\nelementName\\r\\nbpmnElementType\\r\\nstate\\r\\n}\\r\\n}\\r\\n}\\r\\n}\",\"variables\":{}}";

		HttpEntity<String> reqEntity = new HttpEntity<String>(str, headers);

		ResponseEntity<String> demo = rest.exchange("http://localhost:9000/graphql", HttpMethod.POST, reqEntity,
				String.class, headers);

		// System.out.println("@PathVariable_bpmnProcessId:- " + bpmnProcessId); // 1
		String variable = demo.getBody();
		ObjectMapper mapper = new ObjectMapper();
		Map obj = mapper.readValue(variable, Map.class);
		Map hitmap = (Map) obj.get("data");
		Map pinstance = (Map) hitmap.get("processInstances");
		List<Object> nodes = (List<Object>) pinstance.get("nodes");

		System.out.println("nodes----->" + nodes);

		System.out.println("bpmnProcessId" + bpmnProcessId);
		List<Object> Final = new ArrayList<Object>();
		for (Object getDetails : nodes) {

			Map s = (Map) getDetails;
			// System.out.println("ss" + s); //2
			System.out.println(s.get("process"));
			HashMap r = (HashMap) s.get("process");

			if (r.get("bpmnProcessId").equals(bpmnProcessId)) {
				System.out.println("This is R    " + r.get("bpmnProcessId"));

				for (Object node : nodes) {

					Map str1 = (Map) node;
					// System.out.println("ss" + s); //2
//                    Map ref = (Map) str1.get("process");
//                    Object ref1 = ref.get("bpmnProcessId");

					List<Object> elementInstances = (List<Object>) s.get("elementInstances");

					// List<Object> element = (List<Object>) s.get("elementInstances");
					// System.out.println("element" + element);

					for (Object elements : elementInstances) {

						Map ele = (Map) elements;

						String mp = (String) ele.get("elementId");
//                        String mp1 = (String) ele.get("elementName");
//                        String mp2 = (String) ele.get("bpmnElementType");
//                        String mp3 = (String) ele.get("state");

						Final.add(mp);
					}

				}

			}

//            if(r.toString().equals("bpmnProcessId=sample_sample_demo")) {
//                System.out.println("Yes For Sure");
//            }
//            else {
//                System.out.println("BPMN PROCESS ID IN R>>>>>>>>>>>>>"+r.toString());
//            }
//            r.equals(bpmnProcessId);
//            if(bpmnProcessId==s.get("bpmnProcessId")) {
//                
//            }
//            Map ref = (Map) s.get("process");
//            Object ref1 = ref.get("bpmnProcessId");
//            System.out.println(ref1);
//            
//            
//            
//        
//            if(ref1==bpmnProcessId) {
//                
//                
//            }
//        

		}
		return Final;

	}

	@PostMapping("/viewRunningInstance")
	public List<Object> getOrginrunninginstance() throws JsonMappingException, JsonProcessingException {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		String str = "{\r\n" + "  processInstances(stateIn: [ACTIVATED]) {\r\n" + "    totalCount\r\n"
				+ "    nodes {\r\n" + "      key\r\n" + "      state\r\n" + "      process {\r\n"
				+ "        bpmnProcessId\r\n" + "      }\r\n" + "      elementInstances(stateIn: [ACTIVATED]) {\r\n"
				+ "        elementId\r\n" + "        elementName\r\n" + "        bpmnElementType\r\n"
				+ "        state\r\n" + "      }\r\n" + "    }\r\n" + "  }\r\n" + "}";

		System.out.println(str);

		HttpEntity<String> reqEntity = new HttpEntity<String>(str);

		ResponseEntity<String> demo = rest.exchange("http://localhost:9000/graphql", HttpMethod.POST, reqEntity,
				String.class);

		String variable = demo.getBody();

		logger.info("Elastic Response:" + variable);

		ObjectMapper mapper = new ObjectMapper();

		Map obj = mapper.readValue(variable, Map.class);

		Map hitmap = (Map) obj.get("hits");

		List<Object> hit = (List<Object>) hitmap.get("hits");

		List<Object> Final = new ArrayList<Object>();

		for (Object getDetails : hit) {

			Map getSource = (Map) getDetails;
			Map getValue = (Map) getSource.get("_source");
			String getintent = (String) getValue.get("intent");
			Long timestamp = (Long) getValue.get("timestamp");
			Map getdata = (Map) getValue.get("value");

			getdata.put("intent", getintent);
			getdata.put("timestamp", timestamp);

			Final.add(getdata);

		}

		return Final;

	}

}