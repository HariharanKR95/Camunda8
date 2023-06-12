package com.example.demo.pojo;

public class ResponsePojo {
	public int id;
	public String name;
	public long aadharNumber;
	public String pan;
	public String state;
	@Override
	public String toString() {
		return "ResponsePojo [id=" + id + ", name=" + name + ", aadharNumber=" + aadharNumber + ", pan=" + pan
				+ ", state=" + state + ", city=" + city + "]";
	}
	public String city;
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
	public long getAadharNumber() {
		return aadharNumber;
	}
	public void setAadharNumber(long aadharNumber) {
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

}


