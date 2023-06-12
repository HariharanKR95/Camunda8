package com.example.demo.pojo;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class RootPojo {
	// @JsonIgnoreProperties("address")
	private ArrayList<AddressPojo> address;
	// @JsonIgnoreProperties("employee")
	private ArrayList<EmployePojo> employee;
	// @JsonIgnoreProperties("idProof")
	private ArrayList<IdPojo> idProof;

	public ArrayList<AddressPojo> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<AddressPojo> address) {
		this.address = address;
	}

	public ArrayList<EmployePojo> getEmployee() {
		return employee;
	}

	public void setEmployee(ArrayList<EmployePojo> employee) {
		this.employee = employee;
	}

	public ArrayList<IdPojo> getIdProof() {
		return idProof;
	}

	public void setIdProof(ArrayList<IdPojo> idProof) {
		this.idProof = idProof;
	}

	@Override
	public String toString() {
		return "RootPojo [address=" + address + ", employee=" + employee + ", idProof=" + idProof + "]";
	}

}