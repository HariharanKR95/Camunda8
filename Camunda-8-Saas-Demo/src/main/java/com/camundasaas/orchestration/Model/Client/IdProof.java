package com.camundasaas.orchestration.Model.Client;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class IdProof {
	
	private int id;
	private int aadharNumber;
	private String pan;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	@Override
	public String toString() {
		return "Id [id=" + id + ", aadharNumber=" + aadharNumber + ", pan=" + pan + "]";
	}
	
	
	
	
	
	

}
