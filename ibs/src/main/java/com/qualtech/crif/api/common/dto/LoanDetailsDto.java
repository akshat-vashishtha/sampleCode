package com.qualtech.crif.api.common.dto;

public class LoanDetailsDto {
	private String caseApiDtlId;
	private String bankId;
	private String bankName;
	private String applicationId;
	private String loanAmount;
	private String emiAmount;
	private String roi;
	private String applicationStatus;
	public String productId;
	public String tenure;
	public String finnOneId;
	public String countValue;
	public String getCountValue() {
		return countValue;
	}
	public void setCountValue(String countValue) {
		this.countValue = countValue;
	}
	public String getFinnOneId() {
		return finnOneId;
	}
	public void setFinnOneId(String finnOneId) {
		this.finnOneId = finnOneId;
	}
	public String getRoi() {
		return roi;
	}
	public void setRoi(String roi) {
		this.roi = roi;
	}
	public String getCaseApiDtlId() {
		return caseApiDtlId;
	}
	public void setCaseApiDtlId(String caseApiDtlId) {
		this.caseApiDtlId = caseApiDtlId;
	}
	public String getTenure() {
		return tenure;
	}
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(String emiAmount) {
		this.emiAmount = emiAmount;
	}
	
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	
	

}
