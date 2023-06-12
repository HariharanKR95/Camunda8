package com.camundasaas.orchestration.Model.Client;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class Clientpojo {

	private ArrayList<Employee> employee;
	private ArrayList<IdProof> idProof;
	private ArrayList<Address>address;
	public ArrayList<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(ArrayList<Employee> employee) {
		this.employee = employee;
	}
	
	public ArrayList<IdProof> getIdProof() {
		return idProof;
	}
	public void setIdProof(ArrayList<IdProof> idProof) {
		this.idProof = idProof;
	}
	public ArrayList<Address> getAddress() {
		return address;
	}
	public void setAddress(ArrayList<Address> address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Clientpojo [employee=" + employee + ", idProof=" + idProof + ", address=" + address + "]";
	}
	
	
	
}
