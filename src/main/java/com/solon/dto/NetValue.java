package com.solon.dto;

import java.sql.Date;

public class NetValue implements Comparable<NetValue> {

	private int id;
	private int productId;
	private Date evalueDate;
	private int evalueType = 1;
	private double netValue;
	private double netIncreaseRate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
	public int compareTo(NetValue o) {
		return evalueDate.compareTo(o.evalueDate);
	}

	@Override
	public String toString() {
		return "NetValue [productId=" + productId + ", evalueDate="
				+ evalueDate + ", evalueType=" + evalueType + ", netValue="
				+ netValue + ", netIncreaseRate=" + netIncreaseRate + "]";
	}

}
