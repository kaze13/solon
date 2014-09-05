package com.solon.dto;

import java.sql.Date;

public class NetValue {

	private int productId;
	private Date evalueDate;
	private int evalueType;
	private double netValue;
	private double netIncreaseRate;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Date getEvalueDate() {
		return evalueDate;
	}

	public void setEvalueDate(Date evalueDate) {
		this.evalueDate = evalueDate;
	}

	public int getEvalueType() {
		return evalueType;
	}

	public void setEvalueType(int evalueType) {
		this.evalueType = evalueType;
	}

	public double getNetValue() {
		return netValue;
	}

	public void setNetValue(double netValue) {
		this.netValue = netValue;
	}

	public double getNetIncreaseRate() {
		return netIncreaseRate;
	}

	public void setNetIncreaseRate(double netIncreaseRate) {
		this.netIncreaseRate = netIncreaseRate;
	}

	@Override
	public String toString() {
		return "NetValue [productId=" + productId + ", evalueDate="
				+ evalueDate + ", evalueType=" + evalueType + ", netValue="
				+ netValue + ", netIncreaseRate=" + netIncreaseRate + "]";
	}

}
