package com.Parallel.ServiceOrchestration.Model;

import org.springframework.stereotype.Service;

@Service
public class Rejected {
	
	private String RejectedMail="ERORR";

	public String getRejectedMail() {
		return RejectedMail;
	}

	public void setRejectedMail(String rejectedMail) {
		RejectedMail = rejectedMail;
	}

	
	
	

}
