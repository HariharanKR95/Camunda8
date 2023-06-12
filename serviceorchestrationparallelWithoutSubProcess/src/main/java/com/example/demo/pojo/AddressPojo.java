package com.example.demo.pojo;

import org.springframework.stereotype.Service;

@Service
public class AddressPojo {
	
	public int id;
	public String state;
	public String city;
	@Override
	public String toString() {
		return "AddressPojo [id=" + id + ", state=" + state + ", city=" + city + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	

}
