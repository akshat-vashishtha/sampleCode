package com.qualtech.cibil.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.cibil.api.response.CibilResponsePayload;
@Entity
@Table(name="IB_CBL_ENQUIRYDETAILS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EnquiryDetails implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4355679758753038535L;
	private Long enquiryId;
	private String dateOfEnquiry="";
	private String enquiryShortName="";
	private String enquiryPurpose="";
	private String enquiryAmount="";
	@JsonIgnore
	private CibilResponsePayload cibilresponsepayload;
	@Column(name="DATEOFENQUIRY")
	public String getDateOfEnquiry() {
		return dateOfEnquiry;
	}
	public void setDateOfEnquiry(String dateOfEnquiry) {
		this.dateOfEnquiry = dateOfEnquiry;
	}
    @Column(name="ENQUIRYSHORTNAME")
	public String getEnquiryShortName() {
		return enquiryShortName;
	}
	public void setEnquiryShortName(String enquiryShortName) {
		this.enquiryShortName = enquiryShortName;
	}
    @Column(name="ENQUIRYPURPOSE")
	public String getEnquiryPurpose() {
		return enquiryPurpose;
	}
	public void setEnquiryPurpose(String enquiryPurpose) {
		this.enquiryPurpose = enquiryPurpose;
	}
    @Column(name="ENQUIRYAMOUNT")
	public String getEnquiryAmount() {
		return enquiryAmount;
	}
	public void setEnquiryAmount(String enquiryAmount) {
		this.enquiryAmount = enquiryAmount;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_ENQUIRYDETAILS_SQC")
    @SequenceGenerator(name = "IB_CBL_ENQUIRYDETAILS_SQC", sequenceName = "IB_CBL_ENQUIRYDETAILS_SQC")
	public Long getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(Long enquiryId) {
		this.enquiryId = enquiryId;
	}

	@Override
	public String toString() {
		return "EnquiryDetails [enquiryId=" + enquiryId + ", dateOfEnquiry=" + dateOfEnquiry + ", enquiryShortName="
				+ enquiryShortName + ", enquiryPurpose=" + enquiryPurpose + ", enquiryAmount=" + enquiryAmount + "]";
	}
	@ManyToOne
	@JoinColumn(name="CIBIL_UNIQUE_ID")
	public CibilResponsePayload getCibilresponsepayload() {
		return cibilresponsepayload;
	}
	public void setCibilresponsepayload(CibilResponsePayload cibilresponsepayload) {
		this.cibilresponsepayload = cibilresponsepayload;
	}
}
