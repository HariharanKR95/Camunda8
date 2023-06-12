package com.example.Wedbush.AccountOrchestration.Model;

import org.springframework.stereotype.Service;

@Service
public class StockInfo {
	
	    private int stockMovementId;
	    private String stockMovementDescription;
	    private int accountId;
	    private int accountTypeId;
	    private String accountTypeDescription;
	    private String currency;
	    private String entryDate;
	    private String cusip;
	    private String symbol;
	    private String securityDescription;
	    private String tradeDate;
	    private String settleDate;
	    private int quantity;
		@Override
		public String toString() {
			return "StockInfo [stockMovementId=" + stockMovementId + ", stockMovementDescription="
					+ stockMovementDescription + ", accountId=" + accountId + ", accountTypeId=" + accountTypeId
					+ ", accountTypeDescription=" + accountTypeDescription + ", currency=" + currency + ", entryDate="
					+ entryDate + ", cusip=" + cusip + ", symbol=" + symbol + ", securityDescription="
					+ securityDescription + ", tradeDate=" + tradeDate + ", settleDate=" + settleDate + ", quantity="
					+ quantity + "]";
		}
		public int getStockMovementId() {
			return stockMovementId;
		}
		public void setStockMovementId(int stockMovementId) {
			this.stockMovementId = stockMovementId;
		}
		public String getStockMovementDescription() {
			return stockMovementDescription;
		}
		public void setStockMovementDescription(String stockMovementDescription) {
			this.stockMovementDescription = stockMovementDescription;
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
		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public String getEntryDate() {
			return entryDate;
		}
		public void setEntryDate(String entryDate) {
			this.entryDate = entryDate;
		}
		public String getCusip() {
			return cusip;
		}
		public void setCusip(String cusip) {
			this.cusip = cusip;
		}
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		public String getSecurityDescription() {
			return securityDescription;
		}
		public void setSecurityDescription(String securityDescription) {
			this.securityDescription = securityDescription;
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
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
			    

}
