package com.qualtech.experiankickoff.api.response;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_EX2_MASK_RESPONSE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperianMaskResPayloadResult {

	@JsonIgnore
	@Id
	private String eid;
	private String responseJson;
	private String stgOneHitId;
	private String stgTwoHitId;
	@Transient
	private String[] maskedEmailADDR;
	@Transient
	private String[] maskMobileno;
	@JsonIgnore
	@Column(name="MASKEDEMAILADDR")
	private String emailADDR;
	@JsonIgnore
	@Column(name="MASKMOBILENO")
	private String mobileno;
	private String sessionId;
	
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}
	public String getStgOneHitId() {
		return stgOneHitId;
	}
	public void setStgOneHitId(String stgOneHitId) {
		this.stgOneHitId = stgOneHitId;
	}
	public String getStgTwoHitId() {
		return stgTwoHitId;
	}
	public void setStgTwoHitId(String stgTwoHitId) {
		this.stgTwoHitId = stgTwoHitId;
	}

	

	
	public String[] getMaskedEmailADDR() {
		return maskedEmailADDR;
	}
	public void setMaskedEmailADDR(String[] maskedEmailADDR) {
		this.emailADDR = Arrays.toString(maskedEmailADDR);
		this.maskedEmailADDR = maskedEmailADDR;
	}
	public String[] getMaskMobileno() {
		return maskMobileno;
	}
	public void setMaskMobileno(String[] maskMobileno) {
		this.mobileno = Arrays.toString(maskMobileno);
		this.maskMobileno = maskMobileno;
	}
	
	public String getEmailADDR() {
		emailADDR = Arrays.toString(maskedEmailADDR);
		return emailADDR;
	}
	public void setEmailADDR(String emailADDR) {
		this.emailADDR = emailADDR;
	}
	
	public String getMobileno() {
		mobileno = Arrays.toString(maskMobileno);
		return mobileno;
	}
	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	@Override
	public String toString() {
		return "ExperianMaskResPayloadResult [responseJson=" + responseJson + ", stgOneHitId=" + stgOneHitId
				+ ", stgTwoHitId=" + stgTwoHitId + ", maskedEmailADDR=" + Arrays.toString(maskedEmailADDR) + ", maskMobileno="
				+ Arrays.toString(maskMobileno) + ", sessionId=" + sessionId + "]";
	}

}
