package com.qualtech.icici.api.response;

import java.io.Serializable;

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
@Table(name="IB_IC_PRECALC_RES_PRESUMM")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PremiumSummary implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@JsonIgnore
	@GeneratedValue(generator = "REQ_SEQ")
	@SequenceGenerator(name = "REQ_SEQ", sequenceName = "IB_PC_RES_SEQ")
	private int uniqueID;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="eid",nullable=false)	
	private EbiResponse premiumSummaryOne;
	
	 public int getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}
	
	public EbiResponse getPremiumSummaryOne() {
		return premiumSummaryOne;
	}
	public void setPremiumSummaryOne(EbiResponse premiumSummaryOne) {
		this.premiumSummaryOne = premiumSummaryOne;
	}

	private String productType;

	    private String productName;

	    private String productCode;

	    private String state;

	    private String city;

	    private String modeOfPayment;

	    private String premiumPaymentOption;

	    private String premiumPaymentTerm;

	    private String masterCode;

	    private String age;

	    private String annualPremium;

	    private String term;

	    private String deathBenefit;

	    private String basePremium;

	    private String coverageOption;

	    private String adbrPremium;

	    private String abrPremium;

	    private String cirPremium;

	    private String ibrPremium;

	    private String wopPremium;

	    private String premiumInstallment;

	    private String premiumInstallmentWithTax;

	    private String serviceTax;

	    private String eduCess;

	    private String krishiKalyan;

	    private String totalFirstPremium;

	    private String totalFirstPremiumShow;

	    private String annualPremiumwithoutTax;

	    private String annualPremiumWithTax;

	    private String adbSumAssured;

	    private String cirSumAssured;

	    private String tpdSumAssured;

	    private String serviceTaxUS;

	    private String eduCessUS;

	    private String krishiKalyanUS;

	    private String modalPremium;

	    private String salesChannel;

	    private String statisticalCode;

	    private String benefitOption;

	    private String loanTenure;

	    private String loanAmount;

	   
		public void setProductType(String productType){
	        this.productType = productType;
	    }
	    public String getProductType(){
	        return this.productType;
	    }
	    public void setProductName(String productName){
	        this.productName = productName;
	    }
	    public String getProductName(){
	        return this.productName;
	    }
	    public void setProductCode(String productCode){
	        this.productCode = productCode;
	    }
	    public String getProductCode(){
	        return this.productCode;
	    }
	    public void setState(String state){
	        this.state = state;
	    }
	    public String getState(){
	        return this.state;
	    }
	    public void setCity(String city){
	        this.city = city;
	    }
	    public String getCity(){
	        return this.city;
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
	    public void setMasterCode(String masterCode){
	        this.masterCode = masterCode;
	    }
	    public String getMasterCode(){
	        return this.masterCode;
	    }
	    public void setAge(String age){
	        this.age = age;
	    }
	    public String getAge(){
	        return this.age;
	    }
	    public void setAnnualPremium(String annualPremium){
	        this.annualPremium = annualPremium;
	    }
	    public String getAnnualPremium(){
	        return this.annualPremium;
	    }
	    public void setTerm(String term){
	        this.term = term;
	    }
	    public String getTerm(){
	        return this.term;
	    }
	    public void setDeathBenefit(String deathBenefit){
	        this.deathBenefit = deathBenefit;
	    }
	    public String getDeathBenefit(){
	        return this.deathBenefit;
	    }
	    public void setBasePremium(String basePremium){
	        this.basePremium = basePremium;
	    }
	    public String getBasePremium(){
	        return this.basePremium;
	    }
	    public void setCoverageOption(String coverageOption){
	        this.coverageOption = coverageOption;
	    }
	    public String getCoverageOption(){
	        return this.coverageOption;
	    }
	    public void setAdbrPremium(String adbrPremium){
	        this.adbrPremium = adbrPremium;
	    }
	    public String getAdbrPremium(){
	        return this.adbrPremium;
	    }
	    public void setAbrPremium(String abrPremium){
	        this.abrPremium = abrPremium;
	    }
	    public String getAbrPremium(){
	        return this.abrPremium;
	    }
	    public void setCirPremium(String cirPremium){
	        this.cirPremium = cirPremium;
	    }
	    public String getCirPremium(){
	        return this.cirPremium;
	    }
	    public void setIbrPremium(String ibrPremium){
	        this.ibrPremium = ibrPremium;
	    }
	    public String getIbrPremium(){
	        return this.ibrPremium;
	    }
	    public void setWopPremium(String wopPremium){
	        this.wopPremium = wopPremium;
	    }
	    public String getWopPremium(){
	        return this.wopPremium;
	    }
	    public void setPremiumInstallment(String premiumInstallment){
	        this.premiumInstallment = premiumInstallment;
	    }
	    public String getPremiumInstallment(){
	        return this.premiumInstallment;
	    }
	    public void setPremiumInstallmentWithTax(String premiumInstallmentWithTax){
	        this.premiumInstallmentWithTax = premiumInstallmentWithTax;
	    }
	    public String getPremiumInstallmentWithTax(){
	        return this.premiumInstallmentWithTax;
	    }
	    public void setServiceTax(String serviceTax){
	        this.serviceTax = serviceTax;
	    }
	    public String getServiceTax(){
	        return this.serviceTax;
	    }
	    public void setEduCess(String eduCess){
	        this.eduCess = eduCess;
	    }
	    public String getEduCess(){
	        return this.eduCess;
	    }
	    public void setKrishiKalyan(String krishiKalyan){
	        this.krishiKalyan = krishiKalyan;
	    }
	    public String getKrishiKalyan(){
	        return this.krishiKalyan;
	    }
	    public void setTotalFirstPremium(String totalFirstPremium){
	        this.totalFirstPremium = totalFirstPremium;
	    }
	    public String getTotalFirstPremium(){
	        return this.totalFirstPremium;
	    }
	    public void setTotalFirstPremiumShow(String totalFirstPremiumShow){
	        this.totalFirstPremiumShow = totalFirstPremiumShow;
	    }
	    public String getTotalFirstPremiumShow(){
	        return this.totalFirstPremiumShow;
	    }
	    public void setAnnualPremiumwithoutTax(String annualPremiumwithoutTax){
	        this.annualPremiumwithoutTax = annualPremiumwithoutTax;
	    }
	    public String getAnnualPremiumwithoutTax(){
	        return this.annualPremiumwithoutTax;
	    }
	    public void setAnnualPremiumWithTax(String annualPremiumWithTax){
	        this.annualPremiumWithTax = annualPremiumWithTax;
	    }
	    public String getAnnualPremiumWithTax(){
	        return this.annualPremiumWithTax;
	    }
	    public void setAdbSumAssured(String adbSumAssured){
	        this.adbSumAssured = adbSumAssured;
	    }
	    public String getAdbSumAssured(){
	        return this.adbSumAssured;
	    }
	    public void setCirSumAssured(String cirSumAssured){
	        this.cirSumAssured = cirSumAssured;
	    }
	    public String getCirSumAssured(){
	        return this.cirSumAssured;
	    }
	    public void setTpdSumAssured(String tpdSumAssured){
	        this.tpdSumAssured = tpdSumAssured;
	    }
	    public String getTpdSumAssured(){
	        return this.tpdSumAssured;
	    }
	    public void setServiceTaxUS(String serviceTaxUS){
	        this.serviceTaxUS = serviceTaxUS;
	    }
	    public String getServiceTaxUS(){
	        return this.serviceTaxUS;
	    }
	    public void setEduCessUS(String eduCessUS){
	        this.eduCessUS = eduCessUS;
	    }
	    public String getEduCessUS(){
	        return this.eduCessUS;
	    }
	    public void setKrishiKalyanUS(String krishiKalyanUS){
	        this.krishiKalyanUS = krishiKalyanUS;
	    }
	    public String getKrishiKalyanUS(){
	        return this.krishiKalyanUS;
	    }
	    public void setModalPremium(String modalPremium){
	        this.modalPremium = modalPremium;
	    }
	    public String getModalPremium(){
	        return this.modalPremium;
	    }
	    public void setSalesChannel(String salesChannel){
	        this.salesChannel = salesChannel;
	    }
	    public String getSalesChannel(){
	        return this.salesChannel;
	    }
	    public void setStatisticalCode(String statisticalCode){
	        this.statisticalCode = statisticalCode;
	    }
	    public String getStatisticalCode(){
	        return this.statisticalCode;
	    }
	    public void setBenefitOption(String benefitOption){
	        this.benefitOption = benefitOption;
	    }
	    public String getBenefitOption(){
	        return this.benefitOption;
	    }
	    public void setLoanTenure(String loanTenure){
	        this.loanTenure = loanTenure;
	    }
	    public String getLoanTenure(){
	        return this.loanTenure;
	    }
	    public void setLoanAmount(String loanAmount){
	        this.loanAmount = loanAmount;
	    }
	    public String getLoanAmount(){
	        return this.loanAmount;
	    }
}
