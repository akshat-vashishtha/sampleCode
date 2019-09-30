package com.qualtech.crif.api.common.dto;

public class LoanOptionsDto {
	private String loanAmount;
	private String tenure;
	private String interestRate;
	private String processingFee;
	private String emi;
	private String companyPf;
	private String splRoi;
	private String splPf;
	private String splPfPer;
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getTenure() {
		return tenure;
	}
	public void setTenure(String tenure) {
		this.tenure = tenure;
	}
	public String getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}
	public String getProcessingFee() {
		return processingFee;
	}
	public void setProcessingFee(String processingFee) {
		this.processingFee = processingFee;
	}
	public String getEmi() {
		return emi;
	}
	public void setEmi(String emi) {
		this.emi = emi;
	}
	public String getCompanyPf() {
		return companyPf;
	}
	public void setCompanyPf(String companyPf) {
		this.companyPf = companyPf;
	}
	public String getSplRoi() {
		return splRoi;
	}
	public void setSplRoi(String splRoi) {
		this.splRoi = splRoi;
	}
	public String getSplPf() {
		return splPf;
	}
	public void setSplPf(String splPf) {
		this.splPf = splPf;
	}
	public String getSplPfPer() {
		return splPfPer;
	}
	public void setSplPfPer(String splPfPer) {
		this.splPfPer = splPfPer;
	}
	
}
