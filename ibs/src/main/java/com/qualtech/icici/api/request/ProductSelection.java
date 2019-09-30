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

@JsonIgnoreProperties(ignoreUnknown = true)
/*@Entity
@Table(name="IB_ICICI_CST_PSEL")*/
public class ProductSelection implements Serializable
{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	/*@Id
	@GeneratedValue(generator = "REQ_SEQ")
	@SequenceGenerator(name = "REQ_SEQ", sequenceName = "IB_ICICI_CST_PSEL_SEQ")
	@Column(name="UNIQUE_ID", unique = true, nullable = false)*/
	private int uniqueID;
	
	@JsonIgnore
	/*@OneToOne
    @JoinColumn(name="EID",nullable=false)*/	
    private CustomerOnBoardRequest customerOnBoard;

    private String masterCode;
    private String productId;
    private String policyTerm;
    private String premiumPayingTerm;
    private String totalPremium;
    private String modalPremium;
    private String lifeCoverOption;
    private String productName;
    private String loanAmount;
    private String sumAssured;
    private String adhb;
    private String benefitOption;
    private String ciBenefit;
    private String loanTenure;
    private String premiumpaymentoption;
    private String premiumPayingFrequency;

	public int getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}

	public CustomerOnBoardRequest getCustomerOnBoard() {
		return customerOnBoard;
	}

	public void setCustomerOnBoard(CustomerOnBoardRequest customerOnBoard) {
		this.customerOnBoard = customerOnBoard;
	}



	public String getMasterCode() {
		return masterCode;
	}



	public void setMasterCode(String masterCode) {
		this.masterCode = masterCode;
	}



	public String getProductId() {
		return productId;
	}



	public void setProductId(String productId) {
		this.productId = productId;
	}



	public String getPolicyTerm() {
		return policyTerm;
	}



	public void setPolicyTerm(String policyTerm) {
		this.policyTerm = policyTerm;
	}



	public String getPremiumPayingTerm() {
		return premiumPayingTerm;
	}



	public void setPremiumPayingTerm(String premiumPayingTerm) {
		this.premiumPayingTerm = premiumPayingTerm;
	}



	public String getTotalPremium() {
		return totalPremium;
	}



	public void setTotalPremium(String totalPremium) {
		this.totalPremium = totalPremium;
	}



	public String getModalPremium() {
		return modalPremium;
	}



	public void setModalPremium(String modalPremium) {
		this.modalPremium = modalPremium;
	}



	



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public String getLoanAmount() {
		return loanAmount;
	}



	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}



	public String getSumAssured() {
		return sumAssured;
	}



	public void setSumAssured(String sumAssured) {
		this.sumAssured = sumAssured;
	}



	


	public String getBenefitOption() {
		return benefitOption;
	}



	public void setBenefitOption(String benefitOption) {
		this.benefitOption = benefitOption;
	}






	public String getLoanTenure() {
		return loanTenure;
	}



	public void setLoanTenure(String loanTenure) {
		this.loanTenure = loanTenure;
	}



	public String getPremiumpaymentoption() {
		return premiumpaymentoption;
	}



	public void setPremiumpaymentoption(String premiumpaymentoption) {
		this.premiumpaymentoption = premiumpaymentoption;
	}



	public String getPremiumPayingFrequency() {
		return premiumPayingFrequency;
	}



	public void setPremiumPayingFrequency(String premiumPayingFrequency) {
		this.premiumPayingFrequency = premiumPayingFrequency;
	}



	public String getLifeCoverOption() {
		return lifeCoverOption;
	}



	public void setLifeCoverOption(String lifeCoverOption) {
		this.lifeCoverOption = lifeCoverOption;
	}



	public String getAdhb() {
		return adhb;
	}



	public void setAdhb(String adhb) {
		this.adhb = adhb;
	}



	public String getCiBenefit() {
		return ciBenefit;
	}



	public void setCiBenefit(String ciBenefit) {
		this.ciBenefit = ciBenefit;
	}



	@Override
	public String toString() {
		return "ProductSelection [masterCode=" + masterCode + ", productId=" + productId + ", policyTerm=" + policyTerm
				+ ", premiumPayingTerm=" + premiumPayingTerm + ", totalPremium=" + totalPremium + ", modalPremium="
				+ modalPremium + ", lifeCoverOption=" + lifeCoverOption + ", productName=" + productName
				+ ", loanAmount=" + loanAmount + ", sumAssured=" + sumAssured + ", adhb=" + adhb + ", benefitOption="
				+ benefitOption + ", ciBenefit=" + ciBenefit + ", loanTenure=" + loanTenure + ", premiumpaymentoption="
				+ premiumpaymentoption + ", premiumPayingFrequency=" + premiumPayingFrequency + "]";
	}



	

}
			
					
			
	