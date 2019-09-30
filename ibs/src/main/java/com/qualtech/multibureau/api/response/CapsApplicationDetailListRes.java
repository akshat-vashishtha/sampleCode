package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_CAPS_DETAIL_RES")
public class CapsApplicationDetailListRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAPS_DETAIL_SQC", allocationSize = 1)
	@JsonIgnore
	private int capsDetailId;

	private String reporttime;
	private String subscribercode;
	private String financepurpose;
	private String reportnumber;
	private String enquiryreason;
	private String dateofrequest;
	private String subscribername;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "capsApplicationDetailListRes" ,cascade=CascadeType.ALL)
	private CapsApplicantDetailsRes capsapplicantdetails;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "capsApplicationDetailListRes" ,cascade=CascadeType.ALL)
	private CapsApplicantAddressDetailsRes capsapplicantaddressdetails;
	
	private String amountfinanced;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "capsApplicationDetailListRes" ,cascade=CascadeType.ALL)
	private CapsOtherDetailsRes capsotherdetails;
	
	private String durationofagreement;

	@ManyToOne
	@JoinColumn(name = "capsId", nullable = false)
	@JsonIgnore
	private CapsRes capsRes;

	public int getCapsDetailId() {
		return capsDetailId;
	}

	public void setCapsDetailId(int capsDetailId) {
		this.capsDetailId = capsDetailId;
	}

	public String getReporttime() {
		return reporttime;
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	public String getSubscribercode() {
		return subscribercode;
	}

	public void setSubscribercode(String subscribercode) {
		this.subscribercode = subscribercode;
	}

	public String getFinancepurpose() {
		return financepurpose;
	}

	public void setFinancepurpose(String financepurpose) {
		this.financepurpose = financepurpose;
	}

	public String getReportnumber() {
		return reportnumber;
	}

	public void setReportnumber(String reportnumber) {
		this.reportnumber = reportnumber;
	}

	public String getEnquiryreason() {
		return enquiryreason;
	}

	public void setEnquiryreason(String enquiryreason) {
		this.enquiryreason = enquiryreason;
	}

	public String getDateofrequest() {
		return dateofrequest;
	}

	public void setDateofrequest(String dateofrequest) {
		this.dateofrequest = dateofrequest;
	}

	public String getSubscribername() {
		return subscribername;
	}

	public void setSubscribername(String subscribername) {
		this.subscribername = subscribername;
	}

	public CapsApplicantDetailsRes getCapsapplicantdetails() {
		if (this.capsapplicantdetails != null) {
			this.capsapplicantdetails.setCapsApplicationDetailListRes(this);
		}
		return capsapplicantdetails;
	}

	public void setCapsapplicantdetails(CapsApplicantDetailsRes capsapplicantdetails) {
		this.capsapplicantdetails = capsapplicantdetails;
	}

	public CapsApplicantAddressDetailsRes getCapsapplicantaddressdetails() {
		if (this.capsapplicantaddressdetails != null) {
			this.capsapplicantaddressdetails.setCapsApplicationDetailListRes(this);
		}
		return capsapplicantaddressdetails;
	}

	public void setCapsapplicantaddressdetails(CapsApplicantAddressDetailsRes capsapplicantaddressdetails) {
		this.capsapplicantaddressdetails = capsapplicantaddressdetails;
	}

	public String getAmountfinanced() {
		return amountfinanced;
	}

	public void setAmountfinanced(String amountfinanced) {
		this.amountfinanced = amountfinanced;
	}

	public CapsOtherDetailsRes getCapsotherdetails() {
		if (this.capsotherdetails != null) {
			this.capsotherdetails.setCapsApplicationDetailListRes(this);
		}
		return capsotherdetails;
	}

	public void setCapsotherdetails(CapsOtherDetailsRes capsotherdetails) {
		this.capsotherdetails = capsotherdetails;
	}

	public String getDurationofagreement() {
		return durationofagreement;
	}

	public void setDurationofagreement(String durationofagreement) {
		this.durationofagreement = durationofagreement;
	}

	public CapsRes getCapsRes() {
		return capsRes;
	}

	public void setCapsRes(CapsRes capsRes) {
		this.capsRes = capsRes;
	}

	@Override
	public String toString() {
		return "CapsApplicationDetailListRes [capsDetailId=" + capsDetailId + ", reporttime=" + reporttime
				+ ", subscribercode=" + subscribercode + ", financepurpose=" + financepurpose + ", reportnumber="
				+ reportnumber + ", enquiryreason=" + enquiryreason + ", dateofrequest=" + dateofrequest
				+ ", subscribername=" + subscribername + ", capsapplicantdetails=" + capsapplicantdetails
				+ ", capsapplicantaddressdetails=" + capsapplicantaddressdetails + ", amountfinanced=" + amountfinanced
				+ ", capsotherdetails=" + capsotherdetails + ", durationofagreement=" + durationofagreement + "]";
	}
	
	
}
