package com.qualtech.icici.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_IC_REQ_PRODUCT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable{
	
	private static final long serialVersionUID = 4458315654728418974L;

	
	@Id
	@JsonIgnore
	@GeneratedValue(generator = "REQ_SEQ")
	@SequenceGenerator(name = "REQ_SEQ", sequenceName = "IB_PC_REQ_SEQ")
	@Column(name="UNIQUEID", unique = true, nullable = false)
	private int uniqueID;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="EID",nullable=false)		
	private PremCalc premCalcOne;
	
	public int getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}
	public PremCalc getPremCalcOne() {
		return premCalcOne;
	}
	public void setPremCalcOne(PremCalc premCalcOne) {
		this.premCalcOne = premCalcOne;
	}
	private String annualPremium;

    private String benefitOption;

    private String coverageOption;

    private String deathBenefit;

    private String ipAddress;

    private String loanAmount;

    private String loanTenure;

    private String masterCode;

    private String modalPremium;

    private String modeOfPayment;

    private String premiumPaymentOption;

    private String premiumPaymentTerm;

    private String productCode;

    private String productName;

    private String productType;

    private String term;
	
	public void setAnnualPremium(String annualPremium){
        this.annualPremium = annualPremium;
    }
    public String getAnnualPremium(){
        return this.annualPremium;
    }
    public void setBenefitOption(String benefitOption){
        this.benefitOption = benefitOption;
    }
    public String getBenefitOption(){
        return this.benefitOption;
    }
    public void setCoverageOption(String coverageOption){
        this.coverageOption = coverageOption;
    }
    public String getCoverageOption(){
        return this.coverageOption;
    }
    public void setDeathBenefit(String deathBenefit){
        this.deathBenefit = deathBenefit;
    }
    public String getDeathBenefit(){
        return this.deathBenefit;
    }
    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }
    public String getIpAddress(){
        return this.ipAddress;
    }
    public void setLoanAmount(String loanAmount){
        this.loanAmount = loanAmount;
    }
    public String getLoanAmount(){
        return this.loanAmount;
    }
    public void setLoanTenure(String loanTenure){
        this.loanTenure = loanTenure;
    }
    public String getLoanTenure(){
        return this.loanTenure;
    }
    public void setMasterCode(String masterCode){
        this.masterCode = masterCode;
    }
    public String getMasterCode(){
        return this.masterCode;
    }
    public void setModalPremium(String modalPremium){
        this.modalPremium = modalPremium;
    }
    public String getModalPremium(){
        return this.modalPremium;
    }
    public void setModeOfPayment(String modeOfPayment){
        this.modeOfPayment = modeOfPayment;
    }
    public String getModeOfPayment(){
        return this.modeOfPayment;
    }
    public void setPremiumPaymentOption(String premiumPaymentOption){
        this.premiumPaymentOption = premiumPaymentOption;
    }
    public String getPremiumPaymentOption(){
        return this.premiumPaymentOption;
    }
    public void setPremiumPaymentTerm(String premiumPaymentTerm){
        this.premiumPaymentTerm = premiumPaymentTerm;
    }
    public String getPremiumPaymentTerm(){
        return this.premiumPaymentTerm;
    }
    public void setProductCode(String productCode){
        this.productCode = productCode;
    }
    public String getProductCode(){
        return this.productCode;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public String getProductName(){
        return this.productName;
    }
    public void setProductType(String productType){
        this.productType = productType;
    }
    public String getProductType(){
        return this.productType;
    }
    public void setTerm(String term){
        this.term = term;
    }
    public String getTerm(){
        return this.term;
    }
}
