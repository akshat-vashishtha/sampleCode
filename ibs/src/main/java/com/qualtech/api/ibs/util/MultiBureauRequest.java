package com.qualtech.api.ibs.util;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class MultiBureauRequest implements Serializable{
	
	
	private String priority;
    private String productType;
    private String loanType;
    private String loanAmount;
    private String jointInd;
    private String inquirySubmitted_by;
    private String sourceSystemName;
    private String sourceSystemVersion;
    private String sourceSystemVender;
    private String sourceSystem_instance_id;
    private String loanPurposeDesc;
    private String branchIfsccode;
    private String kendra;
    private String inquiryStage;
    private String authrizationflag;
    private String authroizedBy;
    private String individualCorporate_flag;
    private String constitution;
    private String noOfDependents;
    private String alias;
    private String actOpeningDt;
    private String accountNumber1;
    private String accountNumber2;
    private String accountNumber3;
    private String accountNumber4;
    private String tanure;
    private String groupId;
    private String numberCreditCards;
    private String creditCardNo;
    private String monthlyIncome;
    private String soaEmployerNameC;
    private String timeWithEmploy;
    private String companyCategory;
    private String natureOfBusiness;
    private String assetCost;
    private String collateral1;
    private String collateral1Valuation1;
    private String collateral1Valuation2;
    private String collateral2Valuation1;
    private String collateral2Valuation2;
    private String collateral2;
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getJointInd() {
		return jointInd;
	}
	public void setJointInd(String jointInd) {
		this.jointInd = jointInd;
	}
	public String getInquirySubmitted_by() {
		return inquirySubmitted_by;
	}
	public void setInquirySubmitted_by(String inquirySubmitted_by) {
		this.inquirySubmitted_by = inquirySubmitted_by;
	}
	public String getSourceSystemName() {
		return sourceSystemName;
	}
	public void setSourceSystemName(String sourceSystemName) {
		this.sourceSystemName = sourceSystemName;
	}
	public String getSourceSystemVersion() {
		return sourceSystemVersion;
	}
	public void setSourceSystemVersion(String sourceSystemVersion) {
		this.sourceSystemVersion = sourceSystemVersion;
	}
	public String getSourceSystemVender() {
		return sourceSystemVender;
	}
	public void setSourceSystemVender(String sourceSystemVender) {
		this.sourceSystemVender = sourceSystemVender;
	}
	public String getSourceSystem_instance_id() {
		return sourceSystem_instance_id;
	}
	public void setSourceSystem_instance_id(String sourceSystem_instance_id) {
		this.sourceSystem_instance_id = sourceSystem_instance_id;
	}
	public String getLoanPurposeDesc() {
		return loanPurposeDesc;
	}
	public void setLoanPurposeDesc(String loanPurposeDesc) {
		this.loanPurposeDesc = loanPurposeDesc;
	}
	public String getBranchIfsccode() {
		return branchIfsccode;
	}
	public void setBranchIfsccode(String branchIfsccode) {
		this.branchIfsccode = branchIfsccode;
	}
	public String getKendra() {
		return kendra;
	}
	public void setKendra(String kendra) {
		this.kendra = kendra;
	}
	public String getInquiryStage() {
		return inquiryStage;
	}
	public void setInquiryStage(String inquiryStage) {
		this.inquiryStage = inquiryStage;
	}
	public String getAuthrizationflag() {
		return authrizationflag;
	}
	public void setAuthrizationflag(String authrizationflag) {
		this.authrizationflag = authrizationflag;
	}
	public String getAuthroizedBy() {
		return authroizedBy;
	}
	public void setAuthroizedBy(String authroizedBy) {
		this.authroizedBy = authroizedBy;
	}
	public String getIndividualCorporate_flag() {
		return individualCorporate_flag;
	}
	public void setIndividualCorporate_flag(String individualCorporate_flag) {
		this.individualCorporate_flag = individualCorporate_flag;
	}
	public String getConstitution() {
		return constitution;
	}
	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}
	public String getNoOfDependents() {
		return noOfDependents;
	}
	public void setNoOfDependents(String noOfDependents) {
		this.noOfDependents = noOfDependents;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getActOpeningDt() {
		return actOpeningDt;
	}
	public void setActOpeningDt(String actOpeningDt) {
		this.actOpeningDt = actOpeningDt;
	}
	public String getAccountNumber1() {
		return accountNumber1;
	}
	public void setAccountNumber1(String accountNumber1) {
		this.accountNumber1 = accountNumber1;
	}
	public String getAccountNumber2() {
		return accountNumber2;
	}
	public void setAccountNumber2(String accountNumber2) {
		this.accountNumber2 = accountNumber2;
	}
	public String getAccountNumber3() {
		return accountNumber3;
	}
	public void setAccountNumber3(String accountNumber3) {
		this.accountNumber3 = accountNumber3;
	}
	public String getAccountNumber4() {
		return accountNumber4;
	}
	public void setAccountNumber4(String accountNumber4) {
		this.accountNumber4 = accountNumber4;
	}
	public String getTanure() {
		return tanure;
	}
	public void setTanure(String tanure) {
		this.tanure = tanure;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getNumberCreditCards() {
		return numberCreditCards;
	}
	public void setNumberCreditCards(String numberCreditCards) {
		this.numberCreditCards = numberCreditCards;
	}
	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	public String getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public String getSoaEmployerNameC() {
		return soaEmployerNameC;
	}
	public void setSoaEmployerNameC(String soaEmployerNameC) {
		this.soaEmployerNameC = soaEmployerNameC;
	}
	public String getTimeWithEmploy() {
		return timeWithEmploy;
	}
	public void setTimeWithEmploy(String timeWithEmploy) {
		this.timeWithEmploy = timeWithEmploy;
	}
	public String getCompanyCategory() {
		return companyCategory;
	}
	public void setCompanyCategory(String companyCategory) {
		this.companyCategory = companyCategory;
	}
	public String getNatureOfBusiness() {
		return natureOfBusiness;
	}
	public void setNatureOfBusiness(String natureOfBusiness) {
		this.natureOfBusiness = natureOfBusiness;
	}
	public String getAssetCost() {
		return assetCost;
	}
	public void setAssetCost(String assetCost) {
		this.assetCost = assetCost;
	}
	public String getCollateral1() {
		return collateral1;
	}
	public void setCollateral1(String collateral1) {
		this.collateral1 = collateral1;
	}
	public String getCollateral1Valuation1() {
		return collateral1Valuation1;
	}
	public void setCollateral1Valuation1(String collateral1Valuation1) {
		this.collateral1Valuation1 = collateral1Valuation1;
	}
	public String getCollateral1Valuation2() {
		return collateral1Valuation2;
	}
	public void setCollateral1Valuation2(String collateral1Valuation2) {
		this.collateral1Valuation2 = collateral1Valuation2;
	}
	public String getCollateral2Valuation1() {
		return collateral2Valuation1;
	}
	public void setCollateral2Valuation1(String collateral2Valuation1) {
		this.collateral2Valuation1 = collateral2Valuation1;
	}
	public String getCollateral2Valuation2() {
		return collateral2Valuation2;
	}
	public void setCollateral2Valuation2(String collateral2Valuation2) {
		this.collateral2Valuation2 = collateral2Valuation2;
	}
	public String getCollateral2() {
		return collateral2;
	}
	public void setCollateral2(String collateral2) {
		this.collateral2 = collateral2;
	}
	@Override
	public String toString() {
		return "MultiBureauRequest [priority=" + priority + ", productType=" + productType + ", loanType=" + loanType
				+ ", loanAmount=" + loanAmount + ", jointInd=" + jointInd + ", inquirySubmitted_by="
				+ inquirySubmitted_by + ", sourceSystemName=" + sourceSystemName + ", sourceSystemVersion="
				+ sourceSystemVersion + ", sourceSystemVender=" + sourceSystemVender + ", sourceSystem_instance_id="
				+ sourceSystem_instance_id + ", loanPurposeDesc=" + loanPurposeDesc + ", branchIfsccode="
				+ branchIfsccode + ", kendra=" + kendra + ", inquiryStage=" + inquiryStage + ", authrizationflag="
				+ authrizationflag + ", authroizedBy=" + authroizedBy + ", individualCorporate_flag="
				+ individualCorporate_flag + ", constitution=" + constitution + ", noOfDependents=" + noOfDependents
				+ ", alias=" + alias + ", actOpeningDt=" + actOpeningDt + ", accountNumber1=" + accountNumber1
				+ ", accountNumber2=" + accountNumber2 + ", accountNumber3=" + accountNumber3 + ", accountNumber4="
				+ accountNumber4 + ", tanure=" + tanure + ", groupId=" + groupId + ", numberCreditCards="
				+ numberCreditCards + ", creditCardNo=" + creditCardNo + ", monthlyIncome=" + monthlyIncome
				+ ", soaEmployerNameC=" + soaEmployerNameC + ", timeWithEmploy=" + timeWithEmploy + ", companyCategory="
				+ companyCategory + ", natureOfBusiness=" + natureOfBusiness + ", assetCost=" + assetCost
				+ ", collateral1=" + collateral1 + ", collateral1Valuation1=" + collateral1Valuation1
				+ ", collateral1Valuation2=" + collateral1Valuation2 + ", collateral2Valuation1="
				+ collateral2Valuation1 + ", collateral2Valuation2=" + collateral2Valuation2 + ", collateral2="
				+ collateral2 + "]";
	}
    
    
	
}
