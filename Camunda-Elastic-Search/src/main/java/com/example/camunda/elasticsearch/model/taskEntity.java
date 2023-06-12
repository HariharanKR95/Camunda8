package com.example.camunda.elasticsearch.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Service
@JsonIgnoreProperties
public class taskEntity {

	private String elementId;
	private Long elementInstanceKey;
	private Long jobKey;
	private String jobType;
	private Integer retries;
	private String jobWorker;
	private String state;
	private LocalDateTime time;
	public String getElementId() {
		return elementId;
	}
	public void setElementId(String elementId) {
		this.elementId = elementId;
	}
	public Long getElementInstanceKey() {
		return elementInstanceKey;
	}
	public void setElementInstanceKey(Long elementInstanceKey) {
		this.elementInstanceKey = elementInstanceKey;
	}
	public Long getJobKey() {
		return jobKey;
	}
	public void setJobKey(Long jobKey) {
		this.jobKey = jobKey;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public Integer getRetries() {
		return retries;
	}
	public void setRetries(Integer retries) {
		this.retries = retries;
	}
	public String getJobWorker() {
		return jobWorker;
	}
	public void setJobWorker(String jobWorker) {
		this.jobWorker = jobWorker;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	
	

}

