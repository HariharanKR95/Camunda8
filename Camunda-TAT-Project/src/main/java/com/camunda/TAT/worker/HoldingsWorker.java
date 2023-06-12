package com.camunda.TAT.worker;
//
//package com.wedbush.camunda.worker;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//import org.json.simple.JSONObject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.slf4j.MDC;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.HttpClientErrorException;
//import org.springframework.web.client.HttpStatusCodeException;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonMappingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.wedbush.camunda.model.holdings.AccountInfo.AccountInfoHoldings;
//import com.wedbush.camunda.model.holdings.AccountInfo.AccountInfoParentHoldings;
//import com.wedbush.camunda.model.holdings.Error.HoldingsError;
//import com.wedbush.camunda.model.holdings.client.HoldingsClientResponse;
//import com.wedbush.camunda.model.holdings.computePositionsforHousehold.ComputePositionsforHouseholdParent;
//import com.wedbush.camunda.model.holdings.computePositionsforHousehold.ComputePositionsforHouseholds;
//import com.wedbush.camunda.model.holdings.getPositionBySecurity.Account;
//import com.wedbush.camunda.model.holdings.getPositionBySecurity.Annuities;
//import com.wedbush.camunda.model.holdings.getPositionBySecurity.ComputePositionsAccountSummary;
//import com.wedbush.camunda.model.holdings.getPositionBySecurity.FixedIncome;
//import com.wedbush.camunda.model.holdings.getPositionBySecurity.GetPositionBySecurity;
//import com.wedbush.camunda.model.holdings.getPositionBySecurity.Household;
//import com.wedbush.camunda.model.holdings.getPositionBySecurity.MutualFunds;
//import com.wedbush.camunda.model.holdings.getPositionBySecurity.Options;
//import com.wedbush.camunda.model.holdings.getPositionBySecurity.Others;
//import com.wedbush.camunda.model.holdings.getPositionBySecurity.ProductGrouping;
//import com.wedbush.camunda.model.holdings.getPositionBySecurity.StocksAndRelated;
//
//import io.camunda.zeebe.client.ZeebeClient;
//import io.camunda.zeebe.client.api.response.ActivatedJob;
//import io.camunda.zeebe.client.api.worker.JobClient;
//import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
//
//@SpringBootApplication
//public class HoldingsWorker {
//
//	@Autowired
//	ZeebeClient zeebeClient;
//
//	Logger logger = LoggerFactory.getLogger(HoldingsWorker.class);
//
//	final RestTemplate rest = new RestTemplate();
//
//	@Value("${getAccountInfoURL}")
//	private String getAccountInfowithclientIdURL;
//
//	@Value("${getAccountInfowithaccIdURL}")
//	private String getAccountInfowithaccIdURL;
//
//	@Value("${computePositionsforHouseholdURL}")
//	private String computePositionsforHouseholdURL;
//
//	@Value("${getPositionsBySecurityTypeForAHouseholdAndAccountsURL}")
//	private String getPositionsBySecurityTypeForAHouseholdAndAccountsURL;
//
/////////////////////////Worker 1- getAccountInfowithclientId //////////////////////////
//
//	@ZeebeWorker(type = "getAccountInfowithclientIdHoldings", name = "getAccountInfowithclientIdHoldings")
//	public void getAccountInfo(final JobClient client, final ActivatedJob job) {
//		
//		MDC.remove("processInstanceKey");
//		MDC.remove("correlationId");
//
//		logger.info("Holdings WEDInfy getAccountInfowithclientId worker job is activated");
//
//		Timestamp workerRequestTime = new Timestamp(System.currentTimeMillis());
//
//		Map<String, Object> variableMap = job.getVariablesAsMap();
//		
//		String traceparent = (String) variableMap.get("traceparent");
//		
//		String traceId = (String) variableMap.get("traceId");
//		String parentId = (String) variableMap.get("parentId");
//
//		String correlationId = (String) variableMap.get("correlationId");
//		String clientId = (String) variableMap.get("clientId");
//		
//		logger.info("Holdings getAccountInfowithclientId worker job is activated traceId:"+traceId);
//		logger.info("Holdings getAccountInfowithclientId worker job is activated parentId:"+parentId);
//		logger.info("Holdings getAccountInfowithclientId worker job is activated traceparent:"+traceparent);
//		
//		MDC.clear();
//		MDC.put("processInstanceKey", traceId);
//		MDC.put("correlationId", parentId);
//		MDC.put("clientId", String.valueOf(clientId));
//
//		
//		
//		logger.info("Holdings getAccountInfowithclientId correlationId is : " + correlationId);
//		
//		logger.info("Holdings getAccountInfowithclientId ClientId is : " + clientId + " and correlationId is : "
//				+ correlationId);
//		
//        
//		
//		logger.info("Process Instance Key in Holdings getAccountInfowithclientId : " + job.getProcessInstanceKey()
//				+ " and correlationId is : " + correlationId);
//
//		String clientToken = (String) variableMap.get("clientToken");
//
//		Map<String, Object> inputmap = new HashMap<>();
//
//		inputmap.put("clientId", clientId);
//		inputmap.put("correlationId", correlationId);
//
//		HttpEntity<Map> request = new HttpEntity<>(inputmap, getHeaderInfo(clientToken,traceparent));
//
//		HoldingsError holdingsError = new HoldingsError();
//
//		ObjectMapper mapper = new ObjectMapper();
//
//		logger.info("Holdings getAccountInfowithclientId url is..." + getAccountInfowithclientIdURL
//				+ " and correlationId is : " + correlationId);
//
//		try {
//			Timestamp requestTime = new Timestamp(System.currentTimeMillis());
//
//			ResponseEntity<String> response = rest.exchange(getAccountInfowithclientIdURL, HttpMethod.POST, request,
//					String.class);
//
//			String restResponse = response.getBody();
//			logger.info("Holdings getAccountInfowithclientId response is..." + restResponse + " and correlationId is : "
//					+ correlationId);
//
//			Timestamp responseTime = new Timestamp(System.currentTimeMillis());
//			
//			long apiDiffCheck = responseTime.getTime() - requestTime.getTime();
//			
//			
//			logger.warn(" API-Camunda_Holdings_getAccountInfowithclientIdAPI" 
//					+" URL" + getAccountInfowithclientIdURL
//					+" Status Success Total_Response_Time(ms): " + apiDiffCheck
//					+ " correlationId " + parentId);
//
//
//			logger.info("Holdings getAccountInfowithclientId response is ...." + restResponse
//					+ " and correlationId is : " + correlationId);
//
//			AccountInfoParentHoldings accountInfoParent = new AccountInfoParentHoldings();
//
//			accountInfoParent = mapper.readValue(restResponse, AccountInfoParentHoldings.class);
//
//			accountInfoParent.setCorrelationId(correlationId);
//
//			variableMap.put("accountInfoList", accountInfoParent);
//
//			client.newCompleteCommand(job.getKey()).variables(variableMap).send().join();
//
//			Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//			logger.warn(" Worker-Camunda_Holdings_getAccountInfowithclientIdAPI" 
//					+" URL" + getAccountInfowithclientIdURL
//					+" Status Success Total_Response_Time(ms): " + workerDiffCheck
//					+ " correlationId " + parentId);
//			
//			logger.info("Holdings getAccountInfowithclientId worker job is completed" + " and correlationId is : "
//					+ correlationId);
//		}
//
//		catch (HttpStatusCodeException e) {
//
//			logger.info(
//					"Holdings getAccountInfowithclientId HttpStatusCodeException catch block activated and correlationId is : "
//							+ correlationId);
//
//			logger.info(
//					"Process Instance Key in Holdings getAccountInfowithclientId HttpStatusCodeException Catch block...:"
//							+ job.getProcessInstanceKey() + " and correlationId is : " + correlationId);
//
//			String httpStatusMsg = e.getMessage();
//
//			holdingsError.setErrorMessage(httpStatusMsg);
//
//			holdingsError.setErrorOccurs("getAccountInfowithclientId");
//
//			holdingsError.setApiName(getAccountInfowithclientIdURL);
//
//			variableMap.put("apiError", holdingsError);
//
//			String code = "errorHandle";
//
//			zeebeClient.newSetVariablesCommand(job.getProcessInstanceKey()).variables(variableMap).local(true).send()
//					.join();
//
//			client.newThrowErrorCommand(job).errorCode(code)
//					.errorMessage("Exception occured getAccountInfowithclientId API...:").send().join();
//			
//
//			Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//			logger.warn(" Worker-Camunda_Holdings_getAccountInfowithclientIdAPI" 
//					+" URL" + getAccountInfowithclientIdURL
//					+" Status HttpStatusCodeException Total_Response_Time(ms): " + workerDiffCheck
//					+ " correlationId " + parentId);
//
//			logger.info(
//					"Holdings getAccountInfowithclientId  HttpStatusCodeException catch block worker job is completed and correlationId is : "
//							+ correlationId);
//
//		} catch (Exception ex) {
//			logger.info("Holdings getAccountInfowithclientId Exception catch block activated and correlationId is : "
//					+ correlationId);
//
//			logger.info("Process Instance Key in Holdings getAccountInfowithclientId Exception Catch block...:"
//					+ job.getProcessInstanceKey() + " and correlationId is : " + correlationId);
//
//			holdingsError.setErrorOccurs("getAccountInfowithclientId API");
//
//			String Errormessage = ex.getMessage();
//
//			holdingsError.setErrorMessage(Errormessage);
//
//			holdingsError.setApiName(getAccountInfowithclientIdURL);
//
//			variableMap.put("apiError", holdingsError);
//
//			String code = "errorHandle";
//
//			zeebeClient.newSetVariablesCommand(job.getProcessInstanceKey()).variables(variableMap).local(true).send()
//					.join();
//			client.newThrowErrorCommand(job).errorCode(code)
//					.errorMessage("Holdings Exception occured getAccountInfowithclientId API...:").send().join();
//			
//			Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//			logger.warn(" Worker-Camunda_Holdings_getAccountInfowithclientIdAPI" 
//					+" URL" + getAccountInfowithclientIdURL
//					+" Status Exception Total_Response_Time(ms): " + workerDiffCheck
//					+ " correlationId " + parentId);
//
//			logger.info("Holdings getAccountInfowithclientId Exception catch block worker job is completed"
//					+ " and correlationId is : " + correlationId);
//
//		}
//
//	}
//
////////////////////////////////worker2- getAccountInfowithaccId/////////////////////////////////////	
//
//	@ZeebeWorker(type = "getAccountInfowithaccIdHoldings", name = "getAccountInfowithaccIdHoldings")
//	public void GetAccountInfowithaccId(final JobClient client, final ActivatedJob job) throws Exception {
//		MDC.remove("processInstanceKey");
//		MDC.remove("correlationId");
//
//		logger.info("Holdings getAccountInfowithaccId worker job is activated");
//
//		Timestamp workerRequestTime = new Timestamp(System.currentTimeMillis());
//
//		Map<String, Object> variableMap = job.getVariablesAsMap();
//
//		Map<String, Object> reqbody = (Map) variableMap.get("inputRequestFromUI");
//
//		List<Integer> accInputList = (List<Integer>) reqbody.get("accountIds");
//
//		Integer gethouseholdId = (Integer) reqbody.get("householdId");
//
//		String correlationId = (String) variableMap.get("correlationId");
//		logger.info("Holdings getAccountInfowithaccId CorrelationId is : " + correlationId);
//
//		String clientId = (String) variableMap.get("clientId");
//		
//		String traceparent = (String) variableMap.get("traceparent");
//		
//		String traceId = (String) variableMap.get("traceId");
//		String parentId = (String) variableMap.get("parentId");
//		
//		logger.info("Holdings getAccountInfowithaccIdHoldings ClientId is : " + clientId + " and correlationId is : "
//				+ correlationId);
//		
//		
//		MDC.clear();
//		MDC.put("processInstanceKey", traceId);
//		MDC.put("correlationId", parentId);
//		MDC.put("clientId", String.valueOf(clientId));
//		
//		
//		Map<String, Object> inputmap = new HashMap<>();
//
//		inputmap.put("accountId", accInputList);
//		inputmap.put("householdId", gethouseholdId);
//		inputmap.put("correlationId", correlationId);
//
//		String clientToken = (String) variableMap.get("clientToken");
//
//
//		HttpEntity<Map> request = new HttpEntity<>(inputmap, getHeaderInfo(clientToken,traceparent));
//
//		HoldingsError holdingsError = new HoldingsError();
//
//		logger.info("Holdings getAccountInfowithaccId url is..." + getAccountInfowithaccIdURL
//				+ " and correlationId is : " + correlationId);
//
//		try {
//			Timestamp apiRequestTime = new Timestamp(System.currentTimeMillis());
//
//			ResponseEntity<String> response = rest.exchange(getAccountInfowithaccIdURL, HttpMethod.POST, request,
//					String.class);
//			
//			Timestamp apiResponseTime = new Timestamp(System.currentTimeMillis());
//			
//		
//
//			long apiDiffCheck = apiResponseTime.getTime() - apiRequestTime.getTime();
//
//
//			logger.warn(" API-Camunda_Holdings_getAccountInfowithaccId" 
//					+" URL" + getAccountInfowithaccIdURL
//					+" Status Success Total_Response_Time(ms): " + apiDiffCheck
//					+ " correlationId " + parentId);
//			
//			String restResponse = response.getBody();
//
//			AccountInfoParentHoldings accountInfoParent = new AccountInfoParentHoldings();
//
//			ObjectMapper mapper = new ObjectMapper();
//
//			accountInfoParent = mapper.readValue(restResponse, AccountInfoParentHoldings.class);
//
//			accountInfoParent.setCorrelationId(correlationId);
//
//			variableMap.put("accountInfoList", accountInfoParent);
//
//			client.newCompleteCommand(job.getKey()).variables(variableMap).send().join();
//
//			Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//			logger.warn(" Worker-Camunda_Holdings_getAccountInfowithaccId" 
//					+" URL" + getAccountInfowithaccIdURL
//					+" Status Success Total_Response_Time(ms): " + workerDiffCheck
//					+ " correlationId " + parentId);
//
//			logger.info(
//					"Holdings getAccountInfowithaccId worker job is completed and correlationId is : " + correlationId);
//		}
//
//		catch (HttpStatusCodeException e) {
//
//			logger.info(
//					"Holdings getAccountInfowithaccId HttpStatusCodeException catch block activated and correlationId is : "
//							+ correlationId);
//
//			String httpStatusMsg = e.getMessage();
//
//			holdingsError.setErrorMessage(httpStatusMsg);
//
//			holdingsError.setErrorOccurs("getAccountInfowithaccId");
//
//			holdingsError.setApiName(getAccountInfowithaccIdURL);
//
//			variableMap.put("apiError", holdingsError);
//
//			String code = "errorHandle";
//
//			zeebeClient.newSetVariablesCommand(job.getProcessInstanceKey()).variables(variableMap).local(true).send()
//					.join();
//
//			client.newThrowErrorCommand(job).errorCode(code)
//					.errorMessage("Exception occured getAccountInfowithaccId API...:").send().join();
//
//			Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//			logger.warn(" Worker-Camunda_Holdings_getAccountInfowithaccId" 
//					+" URL" + getAccountInfowithaccIdURL
//					+" Status HttpStatusCodeException Total_Response_Time(ms): " + workerDiffCheck
//					+ " correlationId " + parentId);
//			
//			logger.info("Holdings getAccountInfowithaccId HttpStatusCodeException catch block worker job is completed"
//					+ " and correlationId is : " + correlationId);
//
//		} catch (Exception ex) {
//
//			logger.info("Holdings getAccountInfowithaccId Exception catch block activated and correlationId is : "
//					+ correlationId);
//
//			holdingsError.setErrorOccurs("getAccountInfowithaccId API");
//
//			String Errormessage = ex.getMessage();
//
//			holdingsError.setErrorMessage(Errormessage);
//
//			holdingsError.setApiName(getAccountInfowithaccIdURL);
//
//			variableMap.put("apiError", holdingsError);
//
//			String code = "errorHandle";
//
//			logger.info("Process Instance Key in Holdings getAccountInfowithaccId Catch block...:"
//					+ job.getProcessInstanceKey() + " and correlationId is : " + correlationId);
//
//			zeebeClient.newSetVariablesCommand(job.getProcessInstanceKey()).variables(variableMap).local(true).send()
//					.join();
//
//			client.newThrowErrorCommand(job).errorCode(code)
//					.errorMessage("Exception occured getAccountInfowithaccId API...:").send().join();
//
//			Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//			logger.warn(" Worker-Camunda_Holdings_getAccountInfowithaccId" 
//					+" URL" + getAccountInfowithaccIdURL
//					+" Status Exception Total_Response_Time(ms): " + workerDiffCheck
//					+ " correlationId " + parentId);
//			
//			logger.info(
//					"Holdings GetAccountInfowithaccId Exception catch block worker job is completed and correlationId is : "
//							+ correlationId);
//
//		}
//
//	}
//
///////////////////////// Worker 3 - computePositionsforHousehold  /////////////////////////////
//
//	@ZeebeWorker(type = "computePositionsforHousehold", name = "computePositionsforHousehold")
//	public void computePositionsforHousehold(final JobClient client, final ActivatedJob job) throws Exception {
//		
//		MDC.remove("processInstanceKey");
//		MDC.remove("correlationId");
//		logger.info("Holdings computePositionsforHousehold worker called......");
//
//		Timestamp workerRequestTime = new Timestamp(System.currentTimeMillis());
//
//		Map<String, Object> variableMap = job.getVariablesAsMap();
//		String correlationId = (String) variableMap.get("correlationId");
//		String clientId = (String) variableMap.get("clientId");
//
//		String traceparent = (String) variableMap.get("traceparent");
//		
//		String traceId = (String) variableMap.get("traceId");
//		String parentId = (String) variableMap.get("parentId");
//		
//		MDC.clear();
//		MDC.put("processInstanceKey", traceId);
//		MDC.put("correlationId", parentId);
//		MDC.put("clientId", String.valueOf(clientId));
//
//		Map acclistmap = (Map) variableMap.get("accountInfoList");
//
//		Integer householdId = (Integer) acclistmap.get("householdId") == null ? 0
//				: (Integer) acclistmap.get("householdId");
//
//		Integer value = householdId.intValue();
//
//		Map<String, Object> inputRequestMap = new HashMap<>();
//
//		if (null != householdId && householdId.intValue() > 0) {
//
//			inputRequestMap.put("isHousehold", true);
//
//			inputRequestMap.put("householdId", householdId);
//
//		} else {
//			//updating the household to true for backend api bug
//			inputRequestMap.put("isHousehold", true);
//			inputRequestMap.put("householdId", 0);
//
//		}
//
//
//		logger.info("Holdings computePositionsforHousehold correlationId is : " + correlationId);
//
//		inputRequestMap.put("correlationId", correlationId);
//
//		Map<String, Object> accountInfoList = (Map<String, Object>) variableMap.get("accountInfoList");
//
//		ObjectMapper mapper = new ObjectMapper();
//
//		List<AccountInfoHoldings> accIds = (List<AccountInfoHoldings>) accountInfoList.get("accounts");
//
//		List<AccountInfoHoldings> acctInfoMapconverted = mapper.convertValue(accIds,
//				new TypeReference<List<AccountInfoHoldings>>() {
//				});
//
//		List<Object> accIdslist = new ArrayList<>();
//
//		acctInfoMapconverted.forEach((key) -> {
//
//			Long accId = key.getAccountId();
//
//			Map<String, Object> acctListInputMap = new HashMap<String, Object>();
//
//			acctListInputMap.put("accountId", accId);
//
//			accIdslist.add(acctListInputMap);
//		});
//
//		inputRequestMap.put("accounts", accIdslist);
//		inputRequestMap.put("householdId", householdId);
//		inputRequestMap.put("correlationId", correlationId);
//
//		Integer pageNumber = (Integer) variableMap.get("pageNumber");
//		Integer pageSize = (Integer) variableMap.get("pageSize");
//
//		String clientToken = (String) variableMap.get("clientToken");
//		
//		
//		
//
//		HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(inputRequestMap,
//				getHeaderInfo(clientToken,traceparent));
//
//		logger.info("Holdings computePositionsforHousehold input...." + JSONObject.toJSONString(inputRequestMap)
//				+ " and correlationId is : " + correlationId);
//
//		logger.info("Holdings computePositionsforHousehold url is..." + computePositionsforHouseholdURL
//				+ " and correlationId is : " + correlationId);
//
//		HoldingsError holdingsError = new HoldingsError();
//
//		try {
//
//			Timestamp apiRequestTime = new Timestamp(System.currentTimeMillis());
//
//			ResponseEntity<String> response = rest.exchange(computePositionsforHouseholdURL, HttpMethod.POST, request,
//					String.class);
//
//			String respString = response.getBody();
//
//			Timestamp apiResponseTime = new Timestamp(System.currentTimeMillis());
//
//
//			long apiDiffCheck = apiResponseTime.getTime() - apiRequestTime.getTime();
//
//
//			logger.warn(" API-Camunda_Holdings_computePositionsforHousehold" 
//					+" URL" + computePositionsforHouseholdURL
//					+" Status Success Total_Response_Time(ms): " + apiDiffCheck
//					+ " correlationId " + parentId);
//
//			logger.info("Holdings computePositionsforHousehold response is ...." + respString
//					+ " and correlationId is : " + correlationId);
//
//
//			ComputePositionsforHouseholdParent computePositionsforHousehold = new ComputePositionsforHouseholdParent();
//
//			computePositionsforHousehold = mapper.readValue(respString, ComputePositionsforHouseholdParent.class);
//
//			variableMap.put("computePositionsforHousehold", computePositionsforHousehold);
//
//			client.newCompleteCommand(job.getKey()).variables(variableMap).send().join();
//
//			Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//			logger.warn(" Worker-Camunda_Holdings_computePositionsforHousehold" 
//					+" URL" + computePositionsforHouseholdURL
//					+" Status Success Total_Response_Time(ms): " + workerDiffCheck
//					+ " correlationId " + parentId);
//
//			logger.info("Holdings ComputePositionsforHousehold worker job is completed" + " and correlationId is : "
//					+ correlationId);
//
//		}
//
//		catch (HttpStatusCodeException e) {
//
//			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
//				variableMap.put("computePositionsforHousehold404", true);
//
//			} else {
//				variableMap.put("computePositionsforHousehold404", false);
//
//				logger.info(
//						"Holdings computePositionsforHousehold HttpStatusCodeException catch block activated and correlationId is : "
//								+ correlationId);
//
//				String httpStatusMsg = e.getMessage();
//
//				holdingsError.setErrorMessage(httpStatusMsg);
//
//				holdingsError.setErrorOccurs("computePositionsforHousehold");
//
//				holdingsError.setApiName(computePositionsforHouseholdURL);
//
//				variableMap.put("apiError", holdingsError);
//
//				String code = "errorHandle";
//
//				client.newThrowErrorCommand(job).errorCode(code)
//						.errorMessage("Exception occured computePositionsforHousehold API...:").send().join();
//
//			}
//
//			zeebeClient.newSetVariablesCommand(job.getProcessInstanceKey()).variables(variableMap).local(true).send()
//					.join();
//
//			client.newCompleteCommand(job.getKey()).variables(variableMap).send().join();
//			
//			Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//			logger.warn(" Worker-Camunda_Holdings_computePositionsforHousehold" 
//					+" URL" + computePositionsforHouseholdURL
//					+" Status HttpStatusCodeException Total_Response_Time(ms): " + workerDiffCheck
//					+ " correlationId " + parentId);
//
//			logger.info(
//					"Holdings computePositionsforHousehold HttpStatusCodeException catch block worker job is completed and correlationId is : "
//							+ correlationId);
//
//		} catch (Exception ex) {
//
//			logger.info("Holdings computePositionsforHousehold Exception catch block activated and correlationId is : "
//					+ correlationId);
//
//			holdingsError.setErrorOccurs("computePositionsforHousehold API");
//
//			String Errormessage = ex.getMessage();
//
//			holdingsError.setErrorMessage(Errormessage);
//
//			holdingsError.setApiName(computePositionsforHouseholdURL);
//
//			variableMap.put("apiError", holdingsError);
//
//			logger.info("Process Instance Key in Holdings computePositionsforHousehold Exception Catch block...:"
//					+ job.getProcessInstanceKey() + " and correlationId is : " + correlationId);
//
//			String code = "errorHandle";
//
//			zeebeClient.newSetVariablesCommand(job.getProcessInstanceKey()).variables(variableMap).local(true).send()
//					.join();
//
//			client.newThrowErrorCommand(job).errorCode(code)
//					.errorMessage("Exception occured computePositionsforHousehold API...:").send().join();
//			
//			Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//			logger.warn(" Worker-Camunda_Holdings_computePositionsforHousehold" 
//					+" URL" + computePositionsforHouseholdURL
//					+" Status Exception Total_Response_Time(ms): " + workerDiffCheck
//					+ " correlationId " + parentId);
//
//			logger.info("Holdings computePositionsforHousehold Exception catch block worker job is completed"
//					+ " and correlationId is : " + correlationId);
//
//		}
//	}
//
/////////////////////// Worker 4 - getPositionsBySecurityTypeForAHouseholdAndAccounts /////////////////////////
//
//	@ZeebeWorker(type = "getPositionForHolding", name = "getPositionForHolding")
//	public void GetPositionsBySecurityTypeForAHouseholdAndAccounts(final JobClient client, final ActivatedJob job)
//			throws JsonMappingException, JsonProcessingException {
//		
//		MDC.remove("processInstanceKey");
//		MDC.remove("correlationId");
//
//		logger.info("Holdings getPositionsBySecurityTypeForAHouseholdAndAccounts worker called......");
//
//		Timestamp workerRequestTime = new Timestamp(System.currentTimeMillis());
//
//		Map<String, Object> variableMap = job.getVariablesAsMap();
//		
//		String correlationId = (String) variableMap.get("correlationId");
//		String clientId = (String) variableMap.get("clientId");
//
//		String traceparent = (String) variableMap.get("traceparent");
//		
//		String traceId = (String) variableMap.get("traceId");
//		String parentId = (String) variableMap.get("parentId");
//		
//		MDC.clear();
//		MDC.put("processInstanceKey", traceId);
//		MDC.put("correlationId", parentId);
//		MDC.put("clientId", String.valueOf(clientId));
//
//		Map acclistmap = (Map) variableMap.get("accountInfoList");
//
//		Integer householdId = (Integer) acclistmap.get("householdId");
//
//		Map inputRequestMap = new HashMap<>();
//
//		logger.info("Holdings getPositionsBySecurityTypeForAHouseholdAndAccounts correlationId is : " + correlationId);
//
//		inputRequestMap.put("correlationId", correlationId);
//
//		Map<String, Object> accountInfoList = (Map<String, Object>) variableMap.get("accountInfoList");
//
//		ObjectMapper mapper = new ObjectMapper();
//
//		List<AccountInfoHoldings> accIds = (List<AccountInfoHoldings>) accountInfoList.get("accounts");
//
//		List<AccountInfoHoldings> acctInfoMapconverted = mapper.convertValue(accIds,
//				new TypeReference<List<AccountInfoHoldings>>() {
//				});
//
//		List<Long> accIdslist = new ArrayList<>();
//
//		acctInfoMapconverted.forEach((key) -> {
//
//			Long accId = key.getAccountId();
//
//			accIdslist.add(accId);
//		});
//
//		inputRequestMap.put("accountIds", accIdslist);
//		inputRequestMap.put("householdId", householdId);
//		inputRequestMap.put("correlationId", correlationId);
//
//		Integer pageNumber = (Integer) variableMap.get("pageNumber");
//		Integer pageSize = (Integer) variableMap.get("pageSize");
//
//		String clientToken = (String) variableMap.get("clientToken");
//		
//
//		HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(inputRequestMap,
//				getHeaderInfo(clientToken,traceparent));
//
//		logger.info(
//				"Holdings getPositionsBySecurityTypeForAHouseholdAndAccounts getPositionsBySecurityTypeForAHouseholdAndAccounts input...."
//						+ JSONObject.toJSONString(inputRequestMap) + " and correlationId is : " + correlationId);
//
//		HoldingsError holdingsError = new HoldingsError();
//
//		logger.info("Holdings  GetPositionsBySecurityType url is..."
//				+ getPositionsBySecurityTypeForAHouseholdAndAccountsURL + " and correlationId is : " + correlationId);
//
//		try {
//			Timestamp apiRequestTime = new Timestamp(System.currentTimeMillis());
//
//			ResponseEntity<String> response = rest.exchange(getPositionsBySecurityTypeForAHouseholdAndAccountsURL,
//					HttpMethod.POST, request, String.class);
//
//			String respString = response.getBody();
//
//			Timestamp apiResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long apiDiffCheck = apiResponseTime.getTime() - apiRequestTime.getTime();
//
//
//			logger.warn(" API-Camunda_Holdings_getPositionsBySecurityTypeForAHouseholdAndAccounts" 
//					+" URL" + getPositionsBySecurityTypeForAHouseholdAndAccountsURL
//					+" Status Success Total_Response_Time(ms): " + apiDiffCheck
//					+ " correlationId " + parentId);
//
//			logger.info("Holdings getPositionsBySecurityTypeForAHouseholdAndAccountsURL response is ...." + respString
//					+ " and correlationId is : " + correlationId);
//
//			GetPositionBySecurity holdingProductGrouping = new GetPositionBySecurity();
//
//			holdingProductGrouping = mapper.readValue(respString, GetPositionBySecurity.class);
//
//			holdingProductGrouping.setCorrelationId(correlationId);
//
//			variableMap.put("getPositionsBySecurityTypeForAHouseholdAndAccounts", holdingProductGrouping);
//
//			client.newCompleteCommand(job.getKey()).variables(variableMap).send().join();
//
//			Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//			logger.warn(" Worker-Camunda_Holdings_getPositionsBySecurityTypeForAHouseholdAndAccounts" 
//					+" URL" + getPositionsBySecurityTypeForAHouseholdAndAccountsURL
//					+" Status Success Total_Response_Time(ms): " + workerDiffCheck
//					+ " correlationId " + parentId);
//
//			logger.info("Holdings getPositionsBySecurityTypeForAHouseholdAndAccounts worker job is completed"
//					+ " and correlationId is : " + correlationId);
//		}
//
//		catch (HttpStatusCodeException e) {
//
//			logger.info(
//					"Holdings getPositionsBySecurityTypeForAHouseholdAndAccountsError HttpStatusCodeException catch block activated and correlationId is : "
//							+ correlationId);
//
//			if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
//				variableMap.put("getPosition404", true);
//
//			} else {
//				variableMap.put("getPosition404", false);
//
//				String httpStatusMsg = e.getMessage();
//
//				holdingsError.setErrorMessage(httpStatusMsg);
//
//				holdingsError.setErrorOccurs("getPositionsBySecurityTypeForAHouseholdAndAccounts");
//
//				holdingsError.setApiName(getPositionsBySecurityTypeForAHouseholdAndAccountsURL);
//
//				variableMap.put("apiError", holdingsError);
//
//				String code = "errorHandle";
//
//				client.newThrowErrorCommand(job).errorCode(code)
//						.errorMessage("Exception occured getPositionsBySecurityTypeForAHouseholdAndAccounts API...:")
//						.send().join();
//
//			}
//
//			zeebeClient.newSetVariablesCommand(job.getProcessInstanceKey()).variables(variableMap).local(true).send()
//					.join();
//
//			client.newCompleteCommand(job.getKey()).variables(variableMap).send().join();
//			
//			Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//			logger.warn(" Worker-Camunda_Holdings_getPositionsBySecurityTypeForAHouseholdAndAccounts" 
//					+" URL" + getPositionsBySecurityTypeForAHouseholdAndAccountsURL
//					+" Status HttpStatusCodeException Total_Response_Time(ms): " + workerDiffCheck
//					+ " correlationId " + parentId);
//
//			logger.info(
//					"Holdings getPositionsBySecurityTypeForAHouseholdAndAccounts HttpStatusCodeException catch block worker job is completed and correlationId is : "
//							+ correlationId);
//
//		} catch (Exception ex) {
//
//			logger.info(
//					"Holdings getPositionsBySecurityTypeForAHouseholdAndAccountsError Exception catch block activated and correlationId is : "
//							+ correlationId);
//
//			holdingsError.setErrorOccurs("getPositionsBySecurityTypeForAHouseholdAndAccounts API");
//
//			String Errormessage = ex.getMessage();
//
//			holdingsError.setErrorMessage(Errormessage);
//
//			holdingsError.setApiName(getPositionsBySecurityTypeForAHouseholdAndAccountsURL);
//
//			variableMap.put("apiError", holdingsError);
//
//			logger.info(
//					"Process Instance Key in Holdings getPositionsBySecurityTypeForAHouseholdAndAccounts Exception Catch block...:"
//							+ job.getProcessInstanceKey() + " and correlationId is : " + correlationId);
//
//			String code = "errorHandle";
//
//			zeebeClient.newSetVariablesCommand(job.getProcessInstanceKey()).variables(variableMap).local(true).send()
//					.join();
//
//			client.newThrowErrorCommand(job).errorCode(code)
//					.errorMessage("Exception occured getPositionsBySecurityTypeForAHouseholdAndAccounts API...:").send()
//					.join();
//			
//			Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//			long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//			logger.warn(" Worker-Camunda_Holdings_getPositionsBySecurityTypeForAHouseholdAndAccounts" 
//					+" URL" + getPositionsBySecurityTypeForAHouseholdAndAccountsURL
//					+" Status Exception Total_Response_Time(ms): " + workerDiffCheck
//					+ " correlationId " + parentId);
//
//			logger.info(
//					"Holdings getPositionsBySecurityTypeForAHouseholdAndAccounts Exception Catch block worker job is completed"
//							+ " and correlationId is : " + correlationId);
//
//		}
//
//	}
//
///////////////////////// Worker 5 - Consolidate   /////////////////////////////
//
//	@ZeebeWorker(type = "generateHoldingPayLoad", name = "generateHoldingPayLoad")
//	public void getConsolidate(final JobClient client, final ActivatedJob job) throws Exception {
//
//		MDC.remove("processInstanceKey");
//		MDC.remove("correlationId");
//		logger.info("Holdings generateHoldingPayLoad worker called......");
//
//		Timestamp workerRequestTime = new Timestamp(System.currentTimeMillis());
//
//		Map<String, Object> variableMap = job.getVariablesAsMap();
//	
//		String correlationId = (String) variableMap.get("correlationId");
//		String clientId = (String) variableMap.get("clientId");
//
//	String traceparent = (String) variableMap.get("traceparent");
//		
//		String traceId = (String) variableMap.get("traceId");
//		String parentId = (String) variableMap.get("parentId");
//		
//		MDC.clear();
//		MDC.put("processInstanceKey", traceId);
//		MDC.put("correlationId", parentId);
//		MDC.put("clientId", String.valueOf(clientId));
//
//		Map<String, Object> holdingProductGroupingJson = (Map) variableMap
//				.get("getPositionsBySecurityTypeForAHouseholdAndAccounts");
//
//		Map<String, Object> computePositionsforHouseholdjson = (Map) variableMap.get("computePositionsforHousehold");
//
//		ObjectMapper mapper = new ObjectMapper();
//
//		GetPositionBySecurity holdingProductGrouping = mapper.convertValue(holdingProductGroupingJson,
//				new TypeReference<GetPositionBySecurity>() {
//				});
//
//		ComputePositionsforHouseholdParent computePositionsforHousehold = mapper.convertValue(
//				computePositionsforHouseholdjson, new TypeReference<ComputePositionsforHouseholdParent>() {
//
//				});
//
//		String json = mapper.writeValueAsString(holdingProductGrouping);
//
//		HoldingsClientResponse holdingsClientResponse = new HoldingsClientResponse();
//
//		boolean getPosition404 = false;
//
//		if (variableMap.containsKey("getPosition404")) {
//
//			getPosition404 = (Boolean) variableMap.get("getPosition404");
//
//		}
//		if (!getPosition404) {
//
//			holdingsClientResponse = populateHoldingHouseHoldStock(holdingProductGrouping, holdingsClientResponse);
//			holdingsClientResponse = populateHoldingHouseHoldAnnuity(holdingProductGrouping, holdingsClientResponse);
//			holdingsClientResponse = populateHoldingHouseHoldOptions(holdingProductGrouping, holdingsClientResponse);
//			holdingsClientResponse = populateHoldingHouseHoldFixedIncom(holdingProductGrouping, holdingsClientResponse);
//			holdingsClientResponse = populateHoldingHouseHoldMutualFund(holdingProductGrouping, holdingsClientResponse);
//			holdingsClientResponse = populateHoldingHouseHoldOther(holdingProductGrouping, holdingsClientResponse);
//			holdingsClientResponse = populateHoldingComputepositionAccountSummary(holdingProductGrouping, holdingsClientResponse);
//			
//			holdingsClientResponse.setAsOfDateTime(computePositionsforHousehold.getAsOfDateTime());
//
//		}
//
//		boolean computePositionsforHousehold404 = false;
//		if (variableMap.containsKey("computePositionsforHousehold404")) {
//
//			computePositionsforHousehold404 = (Boolean) variableMap.get("computePositionsforHousehold404");
//
//		}
//		if (!computePositionsforHousehold404) {
//
//			holdingsClientResponse = populateHolding(computePositionsforHousehold, holdingsClientResponse, variableMap);
//
//		}
//
//		variableMap.put("holdingsClientResponse", holdingsClientResponse);
//
//		client.newCompleteCommand(job.getKey()).variables(variableMap).send().join();
//
//		Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//
//		long workerDiffCheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//		logger.warn(" Worker-Camunda_Holdings_generateHoldingPayLoad" 
//				+" URL" + ""
//				+" Status Success Total_Response_Time(ms): " + workerDiffCheck
//				+ " correlationId " + parentId);
//
//		logger.info("Holdings generateHoldingPayLoad worker Completed......");
//
//	}
//
//	private HoldingsClientResponse populateHolding(ComputePositionsforHouseholdParent computePositionsforHousehold,
//			HoldingsClientResponse holdingsClientResponse, Map<String, Object> variablemap) {
//
//		ComputePositionsforHouseholds ComputePositionsforHousehold = computePositionsforHousehold
//				.getComputePositionsHouseholdSummary();
//		String clientId = (String) variablemap.get("clientId");
//
//		logger.info("Holdings ClientId : " + clientId);
//
//		holdingsClientResponse.setClientId(clientId);
//		if (null != ComputePositionsforHousehold) {
//			holdingsClientResponse.setTotalGainLossValue(ComputePositionsforHousehold.getTotalGainLossValue());
//			holdingsClientResponse.setTotalGainLossPct(ComputePositionsforHousehold.getTotalGainLossPct());
//			holdingsClientResponse.setTotalHouseholdValue(ComputePositionsforHousehold.getTotalHouseholdValue());
//			holdingsClientResponse.setTodayGainLossValue(ComputePositionsforHousehold.getTodayGainLossValue());
//			holdingsClientResponse.setTodayGainLossPct(ComputePositionsforHousehold.getTodayGainLossPct());
//			holdingsClientResponse
//					.setIstodaysGainLossValueDown(ComputePositionsforHousehold.isIstodaysGainLossValueDown());
//			holdingsClientResponse
//					.setIstotalGainLossValueDown(ComputePositionsforHousehold.isIstotalGainLossValueDown());
//			holdingsClientResponse.setCashTotal(ComputePositionsforHousehold.getCashTotal());
//			holdingsClientResponse.setCashValue(ComputePositionsforHousehold.getCashValue());
//			holdingsClientResponse.setfDICSweep(ComputePositionsforHousehold.getFdicSweep());
//			holdingsClientResponse.setMargin(ComputePositionsforHousehold.getMargin());
//			// #Adding extra fields#//
//			holdingsClientResponse.setStocksAndRelated(ComputePositionsforHousehold.getStocksAndRelated());
//			holdingsClientResponse.setMutualFund(ComputePositionsforHousehold.getMutualFund());
//			holdingsClientResponse.setOptions(ComputePositionsforHousehold.getOptions());
//			holdingsClientResponse.setFixedIncome(ComputePositionsforHousehold.getFixedIncome());
//			holdingsClientResponse.setAnnuities(ComputePositionsforHousehold.getAnnuities());
//			holdingsClientResponse.setOther(ComputePositionsforHousehold.getOther());
//			holdingsClientResponse.setMarketValue(ComputePositionsforHousehold.getMarketValue());
//		}
//
//		return holdingsClientResponse;
//
//	}
//
////////StocksAndRelated/////
//
//	private HoldingsClientResponse populateHoldingHouseHoldStock(GetPositionBySecurity holdingProductGrouping,
//			HoldingsClientResponse holdingsClientResponse) {
//
//		ProductGrouping productGrouping = holdingProductGrouping.getProductGrouping();
//
//		if (null != productGrouping) {
//
//			StocksAndRelated stocksAndRelated = productGrouping.getStocksAndRelated();
//
//			if (null != stocksAndRelated) {
//
//				List<Household> householdBySecurityList = stocksAndRelated.getHousehold();
//
//				List<Account> AccountBySecurityList = stocksAndRelated.getAccounts();
//
//				if (null != householdBySecurityList && householdBySecurityList.size() > 0) {
//
//					holdingsClientResponse.setHouseholdStocksAndRelated(householdBySecurityList);
//
//				}
//				if (null != AccountBySecurityList && AccountBySecurityList.size() > 0) {
//
//					holdingsClientResponse.setStocksAndRelatedByAccount(AccountBySecurityList);
//
//				}
//
//			}
//		}
//		return holdingsClientResponse;
//
//	}
//
//	/////// HouseHold Annuity////////
//
//	private HoldingsClientResponse populateHoldingHouseHoldAnnuity(GetPositionBySecurity holdingProductGrouping,
//			HoldingsClientResponse holdingsClientResponse) {
//
//		ProductGrouping productGrouping = holdingProductGrouping.getProductGrouping();
//
//		if (null != productGrouping) {
//
//			Annuities annuities = productGrouping.getAnnuities();
//
//			if (null != annuities) {
//
//				List<Household> householdBySecurityList = annuities.getHousehold();
//
//				List<Account> AccountBySecurityList = annuities.getAccounts();
//
//				if (null != householdBySecurityList && householdBySecurityList.size() > 0) {
//
//					holdingsClientResponse.setHouseholdAnnuities(householdBySecurityList);
//
//				}
//				if (null != AccountBySecurityList && AccountBySecurityList.size() > 0) {
//
//					holdingsClientResponse.setAnnuitiesByAccount(AccountBySecurityList);
//
//				}
//
//			}
//
//		}
//
//		return holdingsClientResponse;
//
//	}
//
//	////////// HouseHold Option//////////////
//
//	private HoldingsClientResponse populateHoldingHouseHoldOptions(GetPositionBySecurity holdingProductGrouping,
//			HoldingsClientResponse holdingsClientResponse) {
//
//		ProductGrouping productGrouping = holdingProductGrouping.getProductGrouping();
//
//		if (null != productGrouping) {
//
//			Options householdOption = productGrouping.getOptions();
//
//			if (null != householdOption) {
//
//				List<Household> householdBySecurityList = householdOption.getHousehold();
//
//				List<Account> AccountBySecurityList = householdOption.getAccounts();
//
//				if (null != householdBySecurityList && householdBySecurityList.size() > 0) {
//
//					holdingsClientResponse.setHouseholdOptions(householdBySecurityList);
//
//				}
//				if (null != AccountBySecurityList && AccountBySecurityList.size() > 0) {
//
//					holdingsClientResponse.setOptionsByAccount(AccountBySecurityList);
//
//				}
//
//			}
//
//		}
//
//		return holdingsClientResponse;
//
//	}
//
////////////FixedIncome//////////////
//
//	private HoldingsClientResponse populateHoldingHouseHoldFixedIncom(GetPositionBySecurity holdingProductGrouping,
//			HoldingsClientResponse holdingsClientResponse) {
//
//		ProductGrouping productGrouping = holdingProductGrouping.getProductGrouping();
//
//		if (null != productGrouping) {
//
//			FixedIncome fixedIncome = productGrouping.getFixedIncome();
//
//			if (null != fixedIncome) {
//
//				List<Household> householdBySecurityList = fixedIncome.getHousehold();
//
//				List<Account> AccountBySecurityList = fixedIncome.getAccounts();
//
//				if (null != householdBySecurityList && householdBySecurityList.size() > 0) {
//
//					holdingsClientResponse.setHouseholdFixedIncome(householdBySecurityList);
//
//				}
//				if (null != AccountBySecurityList && AccountBySecurityList.size() > 0) {
//
//					holdingsClientResponse.setFixedIncomeByAccount(AccountBySecurityList);
//
//				}
//
//			}
//
//		}
//
//		return holdingsClientResponse;
//
//	}
//
////////////MutualFund//////////////
//
//	private HoldingsClientResponse populateHoldingHouseHoldMutualFund(GetPositionBySecurity holdingProductGrouping,
//			HoldingsClientResponse holdingsClientResponse) {
//
//		ProductGrouping productGrouping = holdingProductGrouping.getProductGrouping();
//
//		if (null != productGrouping) {
//
//			MutualFunds mutualFunds = productGrouping.getMutualFunds();
//
//			if (null != mutualFunds) {
//
//				List<Household> householdBySecurityList = mutualFunds.getHousehold();
//
//				List<Account> AccountBySecurityList = mutualFunds.getAccounts();
//
//				if (null != householdBySecurityList && householdBySecurityList.size() > 0) {
//
//					holdingsClientResponse.setHouseholdMutualFunds(householdBySecurityList);
//
//				}
//				if (null != AccountBySecurityList && AccountBySecurityList.size() > 0) {
//
//					holdingsClientResponse.setMutualFundsByAccount(AccountBySecurityList);
//
//				}
//			}
//
//		}
//
//		return holdingsClientResponse;
//
//	}
//
////////////Other//////////////
//
//	private HoldingsClientResponse populateHoldingHouseHoldOther(GetPositionBySecurity holdingProductGrouping,
//			HoldingsClientResponse holdingsClientResponse) {
//
//		ProductGrouping productGrouping = holdingProductGrouping.getProductGrouping();
//
//		if (null != productGrouping) {
//
//			Others householdOther = productGrouping.getOthers();
//
//			if (null != householdOther) {
//
//				List<Household> householdBySecurityList = householdOther.getHousehold();
//
//				List<Account> AccountBySecurityList = householdOther.getAccounts();
//
//				if (null != householdBySecurityList && householdBySecurityList.size() > 0) {
//
//					holdingsClientResponse.setHouseholdOther(householdBySecurityList);
//
//				}
//
//				if (null != AccountBySecurityList && AccountBySecurityList.size() > 0) {
//
//					holdingsClientResponse.setHouseholdOtherByAccount(AccountBySecurityList);
//
//				}
//
//			}
//
//		}
//
//		return holdingsClientResponse;
//
//	}
//
//	//Compute position for AccountSummary
//
//	private HoldingsClientResponse populateHoldingComputepositionAccountSummary(GetPositionBySecurity holdingProductGrouping,
//			HoldingsClientResponse holdingsClientResponse) {
//
//		List<ComputePositionsAccountSummary> getComputePositionsAccountSummary = holdingProductGrouping.getComputePositionsAccountSummaries();
//
//		if (null != getComputePositionsAccountSummary) {
//
//					holdingsClientResponse.setComputePositionsAccountSummaries(getComputePositionsAccountSummary);
//		}
//
//		return holdingsClientResponse;
//
//	}
//
//////////////////////////////Error////////////////////////////////////////////
//
//	@ZeebeWorker(type = "errorHandling", name = "errorHandling")
//	public void LogAccException(final JobClient client, final ActivatedJob job) throws Exception {
//
//		logger.info("Holdings exceptionHandling job is activated");
//		
//		Timestamp workerRequestTime = new Timestamp(System.currentTimeMillis());
//
//		Map<String, Object> variableMap = job.getVariablesAsMap();
//		
//	String traceparent = (String) variableMap.get("traceparent");
//		
//		String traceId = (String) variableMap.get("traceId");
//		String parentId = (String) variableMap.get("parentId");
//		String clientId = (String) variableMap.get("clientId");
//
//		MDC.clear();
//		MDC.put("processInstanceKey", traceId);
//		MDC.put("correlationId", parentId);
//		MDC.put("clientId", String.valueOf(clientId));
//
//		Map getClientDetailsError = (Map) variableMap.get("apiError");
//
//		ObjectMapper mapper = new ObjectMapper();
//
//		variableMap.put("apiError", getClientDetailsError);
//
//		HoldingsError holdingError = (HoldingsError) mapper.convertValue(getClientDetailsError,
//				new TypeReference<HoldingsError>() {
//				});
//
//		client.newCompleteCommand(job.getKey()).variables(variableMap).send().join();
//		
//		Timestamp workerResponseTime = new Timestamp(System.currentTimeMillis());
//
//		long workerDiffcheck = workerResponseTime.getTime() - workerRequestTime.getTime();
//
//		logger.warn(" Worker-Camunda_Holdings_exceptionHandling" + " URL" + ""
//				+ " Status Success Total_Response_Time(ms): " + workerDiffcheck + " correlationId "
//				+ parentId);
//
//
//		logger.info("Holdings exceptionHandling worker job is completed");
//
//	}
//
//	private HttpHeaders getHeaderInfo(String clientToken,String traceparent) {
//		HttpHeaders headers = new HttpHeaders();
//
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//		headers.setBearerAuth(clientToken);
//		headers.add("traceparent", traceparent);
//
//		return headers;
//	}
//
//}
