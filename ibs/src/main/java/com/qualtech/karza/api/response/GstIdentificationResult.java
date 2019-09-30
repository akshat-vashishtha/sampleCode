package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
@Entity
@Table(name="IB_K_GST_IDENTIFICATION_RES")
public class GstIdentificationResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
	private String uniqueid;
	private Long sequenceid;
	private String  emailId;
	private String  applicationStatus;
	private String  mobNum;
	private String  pan;
	private String  gstinRefId;
	private String  regType;
	private String  gstinId;
	private String  registrationName;
	private String  tinNumber;
	private String correlation_id;
	@Column(name="EMAIL_ID")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Column(name="APPLICATION_STATUS")
	public String getApplicationStatus() {
		return applicationStatus;
	}
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}
	@Column(name="MOBILE_NUMBER")
	public String getMobNum() {
		return mobNum;
	}
	public void setMobNum(String mobNum) {
		this.mobNum = mobNum;
	}
	@Column(name="PAN")
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	@Column(name="GST_IN_REF_ID")
	public String getGstinRefId() {
		return gstinRefId;
	}
	public void setGstinRefId(String gstinRefId) {
		this.gstinRefId = gstinRefId;
	}
	@Column(name="REG_TYPE")
	public String getRegType() {
		return regType;
	}
	public void setRegType(String regType) {
		this.regType = regType;
	}
	@Column(name="GST_IN_ID")
	public String getGstinId() {
		return gstinId;
	}
	public void setGstinId(String gstinId) {
		this.gstinId = gstinId;
	}
	@Column(name="REGISTRATION_NAME")
	public String getRegistrationName() {
		return registrationName;
	}
	public void setRegistrationName(String registrationName) {
		this.registrationName = registrationName;
	}
	@Column(name="TIN_NUMBER")
	public String getTinNumber() {
		return tinNumber;
	}
	public void setTinNumber(String tinNumber) {
		this.tinNumber = tinNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "GstIdentificationResult [emailId=" + emailId + ", applicationStatus=" + applicationStatus + ", mobNum="
				+ mobNum + ", pan=" + pan + ", gstinRefId=" + gstinRefId + ", regType=" + regType + ", gstinId="
				+ gstinId + ", registrationName=" + registrationName + ", tinNumber=" + tinNumber + "]";
	}
	@JsonIgnore
	@Id
	@Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@JsonIgnore
	@Column(name="SEQUENCE_ID")
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_GST_IDENTI_FOR_RES_SQC",allocationSize=1)
	//QCIB_K_GST_IDENTI_FOR_RES_SQC
	public Long getSequenceid() {
		return sequenceid;
	}
	public void setSequenceid(Long sequenceid) {
		this.sequenceid = sequenceid;
	}
	@JsonIgnore
	@Column(name="CORRELATION_ID")
	public String getCorrelation_id() {
		return correlation_id;
	}
	public void setCorrelation_id(String correlation_id) {
		this.correlation_id = correlation_id;
	}
	
	
}

