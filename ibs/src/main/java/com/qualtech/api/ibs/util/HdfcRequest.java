package com.qualtech.api.ibs.util;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class HdfcRequest {

	
	private String title;
	private String partnerId;
	private String prn;
	private String plan;
	private String ipAddress;
	private String domain;
	private String sumAssuredType;
	private String sumAssured;
	private String loanType;
	private String loanDisbursement_date;
	private String loanAmount;
	private String basicPremium;
	private String policyTerm;
	private String loanTenure;
	private String premiumPayable;
	private String isAgreementGenerated;
	private String fundingOption;
	private List<NomineeDetails> nomineeDetails;
	private IBSMedicalQuestionnaire medicalQuestionnaire;
	
	
	
	public IBSMedicalQuestionnaire getMedicalQuestionnaire() {
		return medicalQuestionnaire;
	}
	public void setMedicalQuestionnaire(IBSMedicalQuestionnaire medicalQuestionnaire) {
		this.medicalQuestionnaire = medicalQuestionnaire;
	}
	public String getFundingOption() {
		return fundingOption;
	}
	public void setFundingOption(String fundingOption) {
		this.fundingOption = fundingOption;
	}
	public List<NomineeDetails> getNomineeDetails() {
		return nomineeDetails;
	}
	public void setNomineeDetails(List<NomineeDetails> nomineeDetails) {
		this.nomineeDetails = nomineeDetails;
	}
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPrn() {
		return prn;
	}
	public void setPrn(String prn) {
		this.prn = prn;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getSumAssuredType() {
		return sumAssuredType;
	}
	public void setSumAssuredType(String sumAssuredType) {
		this.sumAssuredType = sumAssuredType;
	}
	public String getSumAssured() {
		return sumAssured;
	}
	public void setSumAssured(String sumAssured) {
		this.sumAssured = sumAssured;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getLoanDisbursement_date() {
		return loanDisbursement_date;
	}
	public void setLoanDisbursement_date(String loanDisbursement_date) {
		this.loanDisbursement_date = loanDisbursement_date;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getBasicPremium() {
		return basicPremium;
	}
	public void setBasicPremium(String basicPremium) {
		this.basicPremium = basicPremium;
	}
	public String getPolicyTerm() {
		return policyTerm;
	}
	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}
	public String getLoanTenure() {
		return loanTenure;
	}
	public void setLoanTenure(String loanTenure) {
		this.loanTenure = loanTenure;
	}
	public String getPremiumPayable() {
		return premiumPayable;
	}
	public void setPremiumPayable(String premiumPayable) {
		this.premiumPayable = premiumPayable;
	}
	public String getIsAgreementGenerated() {
		return isAgreementGenerated;
	}
	public void setIsAgreementGenerated(String isAgreementGenerated) {
		this.isAgreementGenerated = isAgreementGenerated;
	}
	/*private PersonalDetails personalDetails;*/
	/*public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}
	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}*/


	
}
