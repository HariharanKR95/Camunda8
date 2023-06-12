package com.camunda.ITHelpDesk.Model.Client;

import org.springframework.stereotype.Service;

@Service
public class ResponsePojo {
	
	private int id;
	private String name;
	private int aadharNumber;
	private String pan;
	private String state;
	private String city;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(int aadharNumber) {
		this.aadharNumber = aadharNumber;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "ResponsePojo [id=" + id + ", name=" + name + ", aadharNumber=" + aadharNumber + ", pan=" + pan
				+ ", state=" + state + ", city=" + city + "]";
	}
	
	
		
}
