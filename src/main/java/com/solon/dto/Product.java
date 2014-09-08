package com.solon.dto;

import java.util.Date;

public class Product {
	private int productId;
	private String productName;
	private String productShortName;
	private int status;
	private int strategy;
	private String range;
	private String manager;
	private String minInvest;
	private String adoptionPeriod;
	private String closePeriod;
	private Date createDate;
	private String openDate;
	private String watchingOrg;
	private String trustee;
	private String bank;
	private String borker;
	private String counselor;
	private double subscriptionFee;
	private double annualManageFee;
	private double floatManageFee;
	private String subscriptionAccount;
	private String subscriptionBank;
	private String subscriptionId;
	private String subscriptionProcess;

	public Product(int id, String name, String shortName, int status,
			int strategy, String range, String manager, String minInvest,
			String adoptionPeriod, String closePeriod,
			java.util.Date createDate, String openDay, String watchingOg,
			String trustee, String bank, String broker, String counselor,
			double subscriptFee, double annualManageFee, double floatManageFee,
			String subscriptionAccount, String subscriptionBank,
			String subscriptionId, String subscriptProcess) {
		this.productId = id;
		this.productName = name;
		this.productShortName = shortName;
		this.status = status;
		this.strategy = strategy;
		this.range = range;
		this.manager = manager;
		this.minInvest = minInvest;
		this.adoptionPeriod = adoptionPeriod;
		this.closePeriod = closePeriod;
		this.createDate = new Date(createDate.getTime());
		this.openDate = openDay;
		this.watchingOrg = watchingOg;
		this.trustee = trustee;
		this.bank = bank;
		this.borker = broker;
		this.counselor = counselor;
		this.subscriptionFee = subscriptFee;
		this.annualManageFee = annualManageFee;
		this.floatManageFee = floatManageFee;
		this.subscriptionAccount = subscriptionAccount;
		this.subscriptionBank = subscriptionBank;
		this.subscriptionId = subscriptionId;
		this.subscriptionProcess = subscriptProcess;
	}

	public Product() {
		super();
		this.productId = 0;
		this.productName = "";
		this.productShortName = "";
		this.status = 0;
		this.strategy = 0;
		this.range = "";
		this.manager = "";
		this.minInvest = "";
		this.adoptionPeriod = "";
		this.closePeriod = "";
		this.createDate = new Date();
		this.openDate = "";
		this.watchingOrg = "";	
		this.trustee = "";
		this.bank = "";
		this.borker = "";
		this.counselor = "";
		this.subscriptionFee = 0;
		this.annualManageFee = 0;
		this.floatManageFee = 0;
		this.subscriptionAccount = "";
		this.subscriptionBank = "";
		this.subscriptionId = "";
		this.subscriptionProcess = "";
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductShortName() {
		return productShortName;
	}

	public void setProductShortName(String productShortName) {
		this.productShortName = productShortName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRange() {
		return range;
	}

	public void setRange(String range) {
		this.range = range;
	}

	public int getStrategy() {
		return strategy;
	}

	public void setStrategy(int strategy) {
		this.strategy = strategy;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getMinInvest() {
		return minInvest;
	}

	public void setMinInvest(String minInvest) {
		this.minInvest = minInvest;
	}

	public String getAdoptionPeriod() {
		return adoptionPeriod;
	}

	public void setAdoptionPeriod(String adoptionPeriod) {
		this.adoptionPeriod = adoptionPeriod;
	}

	public String getClosePeriod() {
		return closePeriod;
	}

	public void setClosePeriod(String closePeriod) {
		this.closePeriod = closePeriod;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getOpenDate() {
		return openDate;
	}

	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}

	public String getWatchingOrg() {
		return watchingOrg;
	}

	public void setWatchingOrg(String watchingOrg) {
		this.watchingOrg = watchingOrg;
	}

	public String getTrustee() {
		return trustee;
	}

	public void setTrustee(String trustee) {
		this.trustee = trustee;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBorker() {
		return borker;
	}

	public void setBorker(String borker) {
		this.borker = borker;
	}

	public String getCounselor() {
		return counselor;
	}

	public void setCounselor(String counselor) {
		this.counselor = counselor;
	}

	public double getAnnualManageFee() {
		return annualManageFee;
	}

	public void setAnnualManageFee(double annualManageFee) {
		this.annualManageFee = annualManageFee;
	}

	public double getSubscriptionFee() {
		return subscriptionFee;
	}

	public void setSubscriptionFee(double subscriptionFee) {
		this.subscriptionFee = subscriptionFee;
	}

	public double getFloatManageFee() {
		return floatManageFee;
	}

	public void setFloatManageFee(double floatManageFee) {
		this.floatManageFee = floatManageFee;
	}

	public String getSubscriptionAccount() {
		return subscriptionAccount;
	}

	public void setSubscriptionAccount(String subscriptionAccount) {
		this.subscriptionAccount = subscriptionAccount;
	}

	public String getSubscriptionBank() {
		return subscriptionBank;
	}

	public void setSubscriptionBank(String subscriptionBank) {
		this.subscriptionBank = subscriptionBank;
	}

	public String getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(String subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public String getSubscriptionProcess() {
		return subscriptionProcess;
	}

	public void setSubscriptionProcess(String subscriptionProcess) {
		this.subscriptionProcess = subscriptionProcess;
	}

	@Override
	public String toString() {
		return "ProductDto [productId=" + productId + ", productName="
				+ productName + ", productShortName=" + productShortName
				+ ", status=" + status + ", strategy=" + strategy + ", range="
				+ range + ", manager=" + manager + ", minInvest=" + minInvest
				+ ", adoptionPeriod=" + adoptionPeriod + ", closePeriod="
				+ closePeriod + ", createDate=" + createDate + ", openDate="
				+ openDate + ", watchingOrg=" + watchingOrg + ", trustee="
				+ trustee + ", bank=" + bank + ", borker=" + borker
				+ ", counselor=" + counselor + ", subscriptionFee="
				+ subscriptionFee + ", annualManageFee=" + annualManageFee
				+ ", floatManageFee=" + floatManageFee
				+ ", subscriptionAccount=" + subscriptionAccount
				+ ", subscriptionBank=" + subscriptionBank
				+ ", subscriptionId=" + subscriptionId
				+ ", subscriptionProcess=" + subscriptionProcess + "]";
	}

}
