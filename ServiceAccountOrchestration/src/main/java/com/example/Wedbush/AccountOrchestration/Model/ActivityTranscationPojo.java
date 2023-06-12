package com.example.Wedbush.AccountOrchestration.Model;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class ActivityTranscationPojo {
	
	public int totalTransactions;
	@JsonProperty("transactions")
    public ArrayList<TranscationInfo> transactions;
	public int getTotalTransactions() {
		return totalTransactions;
	}
	public void setTotalTransactions(int totalTransactions) {
		this.totalTransactions = totalTransactions;
	}
	public ArrayList<TranscationInfo> getTransactioninfo() {
		return transactions;
	}
	public void setTransactioninfo(ArrayList<TranscationInfo> transactioninfo) {
		this.transactions = transactioninfo;
	}
	@Override
	public String toString() {
		return "ActivityTranscationPojo [totalTransactions=" + totalTransactions + ", transactioninfo="
				+ transactions + "]";
	}
	
}
