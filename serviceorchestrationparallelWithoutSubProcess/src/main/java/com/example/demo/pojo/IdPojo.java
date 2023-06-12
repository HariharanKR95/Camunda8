package com.example.demo.pojo;

public class IdPojo {
	
	public int id;
	public long aadharNumber;
	public String pan;
	@Override
	public String toString() {
		return "IdPojo [id=" + id + ", aadharNumber=" + aadharNumber + ", pan=" + pan + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

}
