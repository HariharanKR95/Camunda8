package com.example.Wedbush.AccountOrchestration.Model;

import org.springframework.stereotype.Service;

@Service
public class CashInfo {
	
	    private int cashMovementId;
	    private String cashMovementDescription;
	    private int accountId;
	    private int accountTypeId;
	    private String accountTypeDescription;
	    private String category;
	    private String action;
	    private String tradeDate;
	    private String settleDate;
	    private String entryDate;
	    private String effectiveDate;
	    private int tradeQuantity;
	    private int quantityPaidon;
	    private int netAmount;
	    private String cusip;
	    private int price;
	    private Object previousFactor;
	    private Object currentFactor;
	    private String enteredBy;
		@Override
		public String toString() {
			return "CashInfo [cashMovementId=" + cashMovementId + ", cashMovementDescription=" + cashMovementDescription
					+ ", accountId=" + accountId + ", accountTypeId=" + accountTypeId + ", accountTypeDescription="
					+ accountTypeDescription + ", category=" + category + ", action=" + action + ", tradeDate="
					+ tradeDate + ", settleDate=" + settleDate + ", entryDate=" + entryDate + ", effectiveDate="
					+ effectiveDate + ", tradeQuantity=" + tradeQuantity + ", quantityPaidon=" + quantityPaidon
					+ ", netAmount=" + netAmount + ", cusip=" + cusip + ", price=" + price + ", previousFactor="
					+ previousFactor + ", currentFactor=" + currentFactor + ", enteredBy=" + enteredBy + "]";
		}
		public int getCashMovementId() {
			return cashMovementId;
		}
		public void setCashMovementId(int cashMovementId) {
			this.cashMovementId = cashMovementId;
		}
		public String getCashMovementDescription() {
			return cashMovementDescription;
		}
		public void setCashMovementDescription(String cashMovementDescription) {
			this.cashMovementDescription = cashMovementDescription;
		}
		public int getAccountId() {
			return accountId;
		}
		public void setAccountId(int accountId) {
			this.accountId = accountId;
		}
		public int getAccountTypeId() {
			return accountTypeId;
		}
		public void setAccountTypeId(int accountTypeId) {
			this.accountTypeId = accountTypeId;
		}
		public String getAccountTypeDescription() {
			return accountTypeDescription;
		}
		public void setAccountTypeDescription(String accountTypeDescription) {
			this.accountTypeDescription = accountTypeDescription;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		public String getTradeDate() {
			return tradeDate;
		}
		public void setTradeDate(String tradeDate) {
			this.tradeDate = tradeDate;
		}
		public String getSettleDate() {
			return settleDate;
		}
		public void setSettleDate(String settleDate) {
			this.settleDate = settleDate;
		}
		public String getEntryDate() {
			return entryDate;
		}
		public void setEntryDate(String entryDate) {
			this.entryDate = entryDate;
		}
		public String getEffectiveDate() {
			return effectiveDate;
		}
		public void setEffectiveDate(String effectiveDate) {
			this.effectiveDate = effectiveDate;
		}
		public int getTradeQuantity() {
			return tradeQuantity;
		}
		public void setTradeQuantity(int tradeQuantity) {
			this.tradeQuantity = tradeQuantity;
		}
		public int getQuantityPaidon() {
			return quantityPaidon;
		}
		public void setQuantityPaidon(int quantityPaidon) {
			this.quantityPaidon = quantityPaidon;
		}
		public int getNetAmount() {
			return netAmount;
		}
		public void setNetAmount(int netAmount) {
			this.netAmount = netAmount;
		}
		public String getCusip() {
			return cusip;
		}
		public void setCusip(String cusip) {
			this.cusip = cusip;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public Object getPreviousFactor() {
			return previousFactor;
		}
		public void setPreviousFactor(Object previousFactor) {
			this.previousFactor = previousFactor;
		}
		public Object getCurrentFactor() {
			return currentFactor;
		}
		public void setCurrentFactor(Object currentFactor) {
			this.currentFactor = currentFactor;
		}
		public String getEnteredBy() {
			return enteredBy;
		}
		public void setEnteredBy(String enteredBy) {
			this.enteredBy = enteredBy;
		}
	    
	

}
