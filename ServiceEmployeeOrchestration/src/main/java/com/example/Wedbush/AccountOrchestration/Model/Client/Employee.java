package com.example.Wedbush.AccountOrchestration.Model.Client;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class Employee {

	private int id;
	private String name;
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
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}

}
