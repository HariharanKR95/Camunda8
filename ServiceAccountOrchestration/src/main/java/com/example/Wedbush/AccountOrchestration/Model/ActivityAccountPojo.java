package com.example.Wedbush.AccountOrchestration.Model;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class ActivityAccountPojo {
	
	
	private String householdId;
	
	   private ArrayList<AccountInfo> accounts;

	public String getHouseholdId() {
		return householdId;
	}

	public void setHouseholdId(String householdId) {
		this.householdId = householdId;
	}

	public ArrayList<AccountInfo> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<AccountInfo> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "ActivityAccountPojo [householdId=" + householdId + ", accounts=" + accounts + "]";
	}
	
	
	}
