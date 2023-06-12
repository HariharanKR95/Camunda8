package com.example.Wedbush.AccountOrchestration.Model;

import org.springframework.stereotype.Service;

@Service
public class ActivityResponsePojo {
	
	private int accountId;
	private String accountNumber;
	private String accountName;
	private String accountType;
	private String transactionDescription;
	private String symbol;
	private String cashMovementDescription;
	private int netAmount;
	private String stockMovementDescription;
	private int quantity;
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getTransactionDescription() {
		return transactionDescription;
	}
	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getCashMovementDescription() {
		return cashMovementDescription;
	}
	public void setCashMovementDescription(String cashMovementDescription) {
		this.cashMovementDescription = cashMovementDescription;
	}
	public int getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(int netAmount) {
		this.netAmount = netAmount;
	}
	public String getStockMovementDescription() {
		return stockMovementDescription;
	}
	public void setStockMovementDescription(String stockMovementDescription) {
		this.stockMovementDescription = stockMovementDescription;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "ActivityResponsePojo [accountId=" + accountId + ", accountNumber=" + accountNumber + ", accountName="
				+ accountName + ", accountType=" + accountType + ", transactionDescription=" + transactionDescription
				+ ", symbol=" + symbol + ", cashMovementDescription=" + cashMovementDescription + ", netAmount="
				+ netAmount + ", stockMovementDescription=" + stockMovementDescription + ", quantity=" + quantity + "]";
	}
		
}
