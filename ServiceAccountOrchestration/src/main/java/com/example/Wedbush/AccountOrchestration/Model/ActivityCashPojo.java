package com.example.Wedbush.AccountOrchestration.Model;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class ActivityCashPojo {
	
	
	private int totalCashMovements;
	@JsonProperty("cashMovements")
    private ArrayList<CashInfo> cashMovements;
	public int getTotalCashMovements() {
		return totalCashMovements;
	}
	public void setTotalCashMovements(int totalCashMovements) {
		this.totalCashMovements = totalCashMovements;
	}
	public ArrayList<CashInfo> getCashMovements() {
		return cashMovements;
	}
	public void setCashMovements(ArrayList<CashInfo> cashMovements) {
		this.cashMovements = cashMovements;
	}
	@Override
	public String toString() {
		return "ActivityCashPojo [totalCashMovements=" + totalCashMovements + ", cashMovements=" + cashMovements + "]";
	}
	

}
