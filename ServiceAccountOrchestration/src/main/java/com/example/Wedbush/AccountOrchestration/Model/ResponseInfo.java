package com.example.Wedbush.AccountOrchestration.Model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service 
public class ResponseInfo {
	
    private List<ActivityResponsePojo> accounts;
    private List<ActivityResponsePojo> transactions;
    private List<ActivityResponsePojo> stockMovements;
    private List<ActivityResponsePojo> cashMovements;
	public List<ActivityResponsePojo> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<ActivityResponsePojo> accounts) {
		this.accounts = accounts;
	}
	public List<ActivityResponsePojo> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<ActivityResponsePojo> transactions) {
		this.transactions = transactions;
	}
	public List<ActivityResponsePojo> getStockMovements() {
		return stockMovements;
	}
	public void setStockMovements(List<ActivityResponsePojo> stockMovements) {
		this.stockMovements = stockMovements;
	}
	public List<ActivityResponsePojo> getCashMovements() {
		return cashMovements;
	}
	public void setCashMovements(List<ActivityResponsePojo> cashMovements) {
		this.cashMovements = cashMovements;
	}
	
    
}
