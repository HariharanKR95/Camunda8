package com.example.Wedbush.AccountOrchestration.Model;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class AccountInfo {
	
	private int accountId;
	private String accountNumber;
	private String accountName;
	private String accountType;
	private boolean taxable;
	@JsonProperty("isRetirementAccount")
	private boolean RetirementAccount;
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
	public boolean isTaxable() {
		return taxable;
	}
	public void setTaxable(boolean taxable) {
		this.taxable = taxable;
	}
	public boolean isRetirementAccount() {
		return RetirementAccount;
	}
	public void setRetirementAccount(boolean retirementAccount) {
		RetirementAccount = retirementAccount;
	}
	@Override
	public String toString() {
		return "AccountInfo [accountId=" + accountId + ", accountNumber=" + accountNumber + ", accountName="
				+ accountName + ", accountType=" + accountType + ", taxable=" + taxable + ", RetirementAccount="
				+ RetirementAccount + "]";
	}
	
	
}
