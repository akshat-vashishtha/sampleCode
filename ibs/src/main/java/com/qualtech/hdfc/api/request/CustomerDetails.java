
package com.qualtech.hdfc.api.request;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_HDFC_CUST_DTL_MED_DATA")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomerDetails implements Serializable{
	
	
	@OneToOne
	@JoinColumn(name="UNIQUEID",nullable=false)
	@JsonIgnore
	private HdfcRequestPayload customerDetailsmap;
	
	@Id
	@JsonIgnore
	@GeneratedValue(generator = "HDFC_SQC")
	@SequenceGenerator(name = "HDFC_SQC", sequenceName = "IB_HDFC_CUST_SQC")
	private int request_unique_id;
	
	

   public int getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(int request_unique_id) {
		this.request_unique_id = request_unique_id;
	}
public HdfcRequestPayload getCustomerDetailsmap() {
		return customerDetailsmap;
	}
	public void setCustomerDetailsmap(HdfcRequestPayload customerDetailsmap) {
		this.customerDetailsmap = customerDetailsmap;
	}

	// @JsonProperty("planId")
    private String planId;
   // @JsonProperty("partnerId")
    private String partnerId;
   // @JsonProperty("sumAssured")
    private String sumAssured;
   // @JsonProperty("rider")
    private String rider;
   // @JsonProperty("dob")
    private String dob;
   // @JsonProperty("loanType")
    private String loanType;
   // @JsonProperty("loanDisbursement_date")
    private String loanDisbursement_date;
   // @JsonProperty("loanAmount")
    private String loanAmount;
   // @JsonProperty("basicPremium")
    private String basicPremium;
   // @JsonProperty("policyTerm")
    private String policyTerm;
   // @JsonProperty("loanTenure")
    private String loanTenure;
   // @JsonProperty("premiumPayable")
    private String premiumPayable;
   // @JsonProperty("personalDetails")
    @OneToOne (fetch=FetchType.LAZY,mappedBy="customeDtl", cascade=CascadeType.ALL)
    private PersonalDetails personalDetails;
   // @JsonProperty("medicalQuestionnaire")
    @Embedded
    private MedicalQuestionnaire medicalQuestionnaire;
   // @JsonProperty("isAgreementGenerated")
    private String isAgreementGenerated;

   // @JsonProperty("planId")
    public String getPlanId() {
        return planId;
    }
   // @JsonProperty("loanTenure")
    public String getLoanTenure() {
		return loanTenure;
	}
   // @JsonProperty("loanTenure")
	public void setLoanTenure(String loanTenure) {
		this.loanTenure = loanTenure;
	}

	//@JsonProperty("planId")
    public void setPlanId(String planId) {
        this.planId = planId;
    }

   // @JsonProperty("partnerId")
    public String getPartnerId() {
        return partnerId;
    }

   // @JsonProperty("partnerId")
    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

   // @JsonProperty("sumAssured")
    public String getSumAssured() {
        return sumAssured;
    }

   // @JsonProperty("sumAssured")
    public void setSumAssured(String sumAssured) {
        this.sumAssured = sumAssured;
    }
   // @JsonProperty("isAgreementGenerated")
    public String getIsAgreementGenerated() {
		return isAgreementGenerated;
	}
   // @JsonProperty("isAgreementGenerated")
	public void setIsAgreementGenerated(String isAgreementGenerated) {
		this.isAgreementGenerated = isAgreementGenerated;
	}

	//@JsonProperty("rider")
    public String getRider() {
        return rider;
    }

   // @JsonProperty("rider")
    public void setRider(String rider) {
        this.rider = rider;
    }

   // @JsonProperty("dob")
    public String getDob() {
        return dob;
    }

   // @JsonProperty("dob")
    public void setDob(String dob) {
        this.dob = dob;
    }

   // @JsonProperty("loanType")
    public String getLoanType() {
        return loanType;
    }

   // @JsonProperty("loanType")
    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }


   public String getLoanDisbursement_date() {
		return loanDisbursement_date;
	}
	public void setLoanDisbursement_date(String loanDisbursement_date) {
		this.loanDisbursement_date = loanDisbursement_date;
	}
	// @JsonProperty("loanAmount")
    public String getLoanAmount() {
        return loanAmount;
    }

   // @JsonProperty("loanAmount")
    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

   // @JsonProperty("basicPremium")
    public String getBasicPremium() {
        return basicPremium;
    }

   // @JsonProperty("basicPremium")
    public void setBasicPremium(String basicPremium) {
        this.basicPremium = basicPremium;
    }

   // @JsonProperty("policyTerm")
    public String getPolicyTerm() {
        return policyTerm;
    }

   // @JsonProperty("policyTerm")
    public void setPolicyTerm(String policyTerm) {
        this.policyTerm = policyTerm;
    }

   // @JsonProperty("premiumPayable")
    public String getPremiumPayable() {
        return premiumPayable;
    }

   // @JsonProperty("premiumPayable")
    public void setPremiumPayable(String premiumPayable) {
        this.premiumPayable = premiumPayable;
    }

   // @JsonProperty("personalDetails")
    public PersonalDetails getPersonalDetails() {
    	if(personalDetails!=null) {
    		personalDetails.setCustomeDtl(this);
    	}
        return personalDetails;
    }

   // @JsonProperty("personalDetails")
    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
    }

   // @JsonProperty("medicalQuestionnaire")
    public MedicalQuestionnaire getMedicalQuestionnaire() {
        return medicalQuestionnaire;
    }

   // @JsonProperty("medicalQuestionnaire")
    public void setMedicalQuestionnaire(MedicalQuestionnaire medicalQuestionnaire) {
        this.medicalQuestionnaire = medicalQuestionnaire;
    }


}
