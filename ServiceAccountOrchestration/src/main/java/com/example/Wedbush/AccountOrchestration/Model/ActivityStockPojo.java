package com.example.Wedbush.AccountOrchestration.Model;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class ActivityStockPojo {
	
	public int totalStockMovements;
	@JsonProperty("stockMovements")
    public ArrayList<StockInfo> stockMovements;
	public int getTotalStockMovements() {
		return totalStockMovements;
	}
	public void setTotalStockMovements(int totalStockMovements) {
		this.totalStockMovements = totalStockMovements;
	}
	public ArrayList<StockInfo> getStockMovements() {
		return stockMovements;
	}
	public void setStockMovements(ArrayList<StockInfo> stockMovements) {
		this.stockMovements = stockMovements;
	}
	@Override
	public String toString() {
		return "ActivityStockPojo [totalStockMovements=" + totalStockMovements + ", stockMovements=" + stockMovements
				+ "]";
	}
	
}
