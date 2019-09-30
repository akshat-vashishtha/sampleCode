package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_CURR_APP_DETAILS_RES")
public class CurrentApplicationDetailsRes implements Serializable {

	private static final long serialVersionUID = -253021057991052198L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CURR_APP_DTLS_SQC", allocationSize = 1)
	@JsonIgnore
	private int currAppDetailsId;

	private String financepurpose;
	private String enquiryreason;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "currAddressRes" ,cascade=CascadeType.ALL)
	private CurrentAppAddDetailsRes currentapplicantaddressdetails;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "currAddressRes" ,cascade=CascadeType.ALL)
	private CurrentOtherDetailsRes currentotherdetails;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "currAddressRes" ,cascade=CascadeType.ALL)
	private CurrentApplicantRes currentapplicantdetails;
	
	private String amountfinanced;
	private String durationofagreement;
	
	@OneToOne
	@JoinColumn(name = "currAppId", nullable = false)
	@JsonIgnore
	private CurrentApplicationRes currAppRes;

	public int getCurrAppDetailsId() {
		return currAppDetailsId;
	}

	public void setCurrAppDetailsId(int currAppDetailsId) {
		this.currAppDetailsId = currAppDetailsId;
	}

	public String getFinancepurpose() {
		return financepurpose;
	}

	public void setFinancepurpose(String financepurpose) {
		this.financepurpose = financepurpose;
	}

	public String getEnquiryreason() {
		return enquiryreason;
	}

	public void setEnquiryreason(String enquiryreason) {
		this.enquiryreason = enquiryreason;
	}

	public CurrentAppAddDetailsRes getCurrentapplicantaddressdetails() {
		if (this.currentapplicantaddressdetails != null) {
			this.currentapplicantaddressdetails.setCurrAddressRes(this);
		}
		return currentapplicantaddressdetails;
	}

	public void setCurrentapplicantaddressdetails(CurrentAppAddDetailsRes currentapplicantaddressdetails) {
		this.currentapplicantaddressdetails = currentapplicantaddressdetails;
	}

	public CurrentOtherDetailsRes getCurrentotherdetails() {
		if (this.currentotherdetails != null) {
			this.currentotherdetails.setCurrAddressRes(this);
		}
		return currentotherdetails;
	}

	public void setCurrentotherdetails(CurrentOtherDetailsRes currentotherdetails) {
		this.currentotherdetails = currentotherdetails;
	}

	public CurrentApplicantRes getCurrentapplicantdetails() {
		if (this.currentapplicantdetails != null) {
			this.currentapplicantdetails.setCurrAddressRes(this);
		}
		return currentapplicantdetails;
	}

	public void setCurrentapplicantdetails(CurrentApplicantRes currentapplicantdetails) {
		this.currentapplicantdetails = currentapplicantdetails;
	}

	public String getAmountfinanced() {
		return amountfinanced;
	}

	public void setAmountfinanced(String amountfinanced) {
		this.amountfinanced = amountfinanced;
	}

	public String getDurationofagreement() {
		return durationofagreement;
	}

	public void setDurationofagreement(String durationofagreement) {
		this.durationofagreement = durationofagreement;
	}

	public CurrentApplicationRes getCurrAppRes() {
		return currAppRes;
	}

	public void setCurrAppRes(CurrentApplicationRes currAppRes) {
		this.currAppRes = currAppRes;
	}

	@Override
	public String toString() {
		return "CurrentApplicationDetailsRes [currAppDetailsId=" + currAppDetailsId + ", financepurpose="
				+ financepurpose + ", enquiryreason=" + enquiryreason + ", currentapplicantaddressdetails="
				+ currentapplicantaddressdetails + ", currentotherdetails=" + currentotherdetails
				+ ", currentapplicantdetails=" + currentapplicantdetails + ", amountfinanced=" + amountfinanced
				+ ", durationofagreement=" + durationofagreement + "]";
	}

}