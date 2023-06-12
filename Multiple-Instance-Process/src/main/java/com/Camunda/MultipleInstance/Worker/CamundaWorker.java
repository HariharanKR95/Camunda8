package com.Camunda.MultipleInstance.Worker;

import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;

@SpringBootApplication
public class CamundaWorker {

	final RestTemplate rest = new RestTemplate();
	Logger logger = LoggerFactory.getLogger(CamundaWorker.class);

	public static void main(String[] args) {

	}

	@ZeebeWorker(type = "Worker1", name = "Worker1")
	public void handleJobFoo1(final JobClient client, final ActivatedJob job) throws InterruptedException {
		long instanceId = job.getProcessInstanceKey();

		logger.info("ProccessId=....." + instanceId);

		logger.info("worker1 Started");
		Timestamp workerStartTime = new Timestamp(System.currentTimeMillis());

		Map<String, Object> variableMap = job.getVariablesAsMap();
		variableMap.put("instanceId", instanceId);

		long ApiTime = (long) variableMap.get("starttime");

		long diff = workerStartTime.getTime() - ApiTime;
		logger.info("Api & Worker 1 Diff=......." + diff);

		if (diff >= 2000) {
			logger.info("Red-Alert in Worker 1");

		} else {

			logger.info("Green-Alert in Worker 1");

		}

		TimeUnit.SECONDS.sleep(3);

		Timestamp workerEndTime = new Timestamp(System.currentTimeMillis());

		variableMap.put("workerEnd", workerEndTime);

		long total = workerEndTime.getTime() - workerStartTime.getTime();

		logger.warn("Worker1 Total_Response_Time(ms): " + total);
		client.newCompleteCommand(job.getKey()).variables(variableMap).send().join();
		logger.info("worker 1 completed");

	}

	@ZeebeWorker(type = "Worker2", name = "Worker2")
	public void handleJobFoo2(final JobClient client, final ActivatedJob job)
			throws JsonMappingException, JsonProcessingException, InterruptedException {

		long localInstanceId = job.getProcessInstanceKey();

		logger.info("ProccessId=....." + localInstanceId);

		logger.info("worker2 Started");

		Timestamp workerStartTime = new Timestamp(System.currentTimeMillis());
		Map<String, Object> variableMap = job.getVariablesAsMap();
		long prevWorkerInstanceId = (long) variableMap.get("instanceId");

		long Worker1 = (long) variableMap.get("workerEnd");

		if (prevWorkerInstanceId == localInstanceId) {

			long diff = workerStartTime.getTime() - Worker1;
			logger.info("Worker1 & Worker2 Diff=......." + diff);

			if (diff >= 2000) {
				logger.info("Red-Alert in Worker 2");

			} else {

				logger.info("Green-Alert in Worker 2");

			}

		}

		client.newCompleteCommand(job.getKey()).variables("").send().join();
		logger.info("worker 2 completed");

		Timestamp endTime = new Timestamp(System.currentTimeMillis());

		long total = endTime.getTime() - workerStartTime.getTime();
		logger.warn("Worker2 Total_Response_Time(ms): " + total);

	}

}