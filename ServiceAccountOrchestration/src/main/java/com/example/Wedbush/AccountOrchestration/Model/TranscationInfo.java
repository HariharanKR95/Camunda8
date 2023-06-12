package com.example.Wedbush.AccountOrchestration.Model;

import org.springframework.stereotype.Service;

@Service
public class TranscationInfo {

	private int transactionId;
	private String transactionDescription;
	private int accountId;
	private int accountTypeId;
	private String accountTypeDescription;
	private String tradeDate;
	private String settleDate;
	private String entryDate;
	private String marketCode;
	private String marketDescription;
	private String buySellIndicator;
	private String action;
	private boolean shortSaleIndicator;
	private int quantity;
	private int price;
	private int netAmount;
	private String cusip;
	private String symbol;
	private String securityDescription;
	private String isinId;
	private String sedolId;
	private String tradeCurrency;
	private String settleCurrency;
	private Object exchangeRate;
	private double feel;
	private String feeType1;
	private Object fee2;
	private Object feeType2;
	private Object fee3;
	private Object feeType3;
	private Object fee4;
	private Object feeType4;
	private Object fee5;
	private Object feeType5;
	private String countryOfTrade;
	private boolean cancelledTrade;
	private boolean rebilledTrade;
	@Override
	public String toString() {
		return "TranscationInfo [transactionId=" + transactionId + ", transactionDescription=" + transactionDescription
				+ ", accountId=" + accountId + ", accountTypeId=" + accountTypeId + ", accountTypeDescription="
				+ accountTypeDescription + ", tradeDate=" + tradeDate + ", settleDate=" + settleDate + ", entryDate="
				+ entryDate + ", marketCode=" + marketCode + ", marketDescription=" + marketDescription
				+ ", buySellIndicator=" + buySellIndicator + ", action=" + action + ", shortSaleIndicator="
				+ shortSaleIndicator + ", quantity=" + quantity + ", price=" + price + ", netAmount=" + netAmount
				+ ", cusip=" + cusip + ", symbol=" + symbol + ", securityDescription=" + securityDescription
				+ ", isinId=" + isinId + ", sedolId=" + sedolId + ", tradeCurrency=" + tradeCurrency
				+ ", settleCurrency=" + settleCurrency + ", exchangeRate=" + exchangeRate + ", feel=" + feel
				+ ", feeType1=" + feeType1 + ", fee2=" + fee2 + ", feeType2=" + feeType2 + ", fee3=" + fee3
				+ ", feeType3=" + feeType3 + ", fee4=" + fee4 + ", feeType4=" + feeType4 + ", fee5=" + fee5
				+ ", feeType5=" + feeType5 + ", countryOfTrade=" + countryOfTrade + ", cancelledTrade=" + cancelledTrade
				+ ", rebilledTrade=" + rebilledTrade + "]";
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionDescription() {
		return transactionDescription;
	}
	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
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
	public String getMarketCode() {
		return marketCode;
	}
	public void setMarketCode(String marketCode) {
		this.marketCode = marketCode;
	}
	public String getMarketDescription() {
		return marketDescription;
	}
	public void setMarketDescription(String marketDescription) {
		this.marketDescription = marketDescription;
	}
	public String getBuySellIndicator() {
		return buySellIndicator;
	}
	public void setBuySellIndicator(String buySellIndicator) {
		this.buySellIndicator = buySellIndicator;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public boolean isShortSaleIndicator() {
		return shortSaleIndicator;
	}
	public void setShortSaleIndicator(boolean shortSaleIndicator) {
		this.shortSaleIndicator = shortSaleIndicator;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
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
	public String getIsinId() {
		return isinId;
	}
	public void setIsinId(String isinId) {
		this.isinId = isinId;
	}
	public String getSedolId() {
		return sedolId;
	}
	public void setSedolId(String sedolId) {
		this.sedolId = sedolId;
	}
	public String getTradeCurrency() {
		return tradeCurrency;
	}
	public void setTradeCurrency(String tradeCurrency) {
		this.tradeCurrency = tradeCurrency;
	}
	public String getSettleCurrency() {
		return settleCurrency;
	}
	public void setSettleCurrency(String settleCurrency) {
		this.settleCurrency = settleCurrency;
	}
	public Object getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(Object exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public double getFeel() {
		return feel;
	}
	public void setFeel(double feel) {
		this.feel = feel;
	}
	public String getFeeType1() {
		return feeType1;
	}
	public void setFeeType1(String feeType1) {
		this.feeType1 = feeType1;
	}
	public Object getFee2() {
		return fee2;
	}
	public void setFee2(Object fee2) {
		this.fee2 = fee2;
	}
	public Object getFeeType2() {
		return feeType2;
	}
	public void setFeeType2(Object feeType2) {
		this.feeType2 = feeType2;
	}
	public Object getFee3() {
		return fee3;
	}
	public void setFee3(Object fee3) {
		this.fee3 = fee3;
	}
	public Object getFeeType3() {
		return feeType3;
	}
	public void setFeeType3(Object feeType3) {
		this.feeType3 = feeType3;
	}
	public Object getFee4() {
		return fee4;
	}
	public void setFee4(Object fee4) {
		this.fee4 = fee4;
	}
	public Object getFeeType4() {
		return feeType4;
	}
	public void setFeeType4(Object feeType4) {
		this.feeType4 = feeType4;
	}
	public Object getFee5() {
		return fee5;
	}
	public void setFee5(Object fee5) {
		this.fee5 = fee5;
	}
	public Object getFeeType5() {
		return feeType5;
	}
	public void setFeeType5(Object feeType5) {
		this.feeType5 = feeType5;
	}
	public String getCountryOfTrade() {
		return countryOfTrade;
	}
	public void setCountryOfTrade(String countryOfTrade) {
		this.countryOfTrade = countryOfTrade;
	}
	public boolean isCancelledTrade() {
		return cancelledTrade;
	}
	public void setCancelledTrade(boolean cancelledTrade) {
		this.cancelledTrade = cancelledTrade;
	}
	public boolean isRebilledTrade() {
		return rebilledTrade;
	}
	public void setRebilledTrade(boolean rebilledTrade) {
		this.rebilledTrade = rebilledTrade;
	}
	
	
}
