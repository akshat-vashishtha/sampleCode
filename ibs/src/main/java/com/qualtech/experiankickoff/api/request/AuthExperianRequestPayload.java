package com.qualtech.experiankickoff.api.request;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_EX2_AUTH_REQ_PAYLOAD")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AuthExperianRequestPayload implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8773946779200361078L;
	
	@JsonIgnore
	@Id
	private String eid;
	private String stgOneHitId;
	private String stgTwoHitId;
	private String actualMobileNumber;
	private String actualEmailADDR;
	private String singleActionSessionId;
	
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

	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}


	public String getActualMobileNumber() {
		return actualMobileNumber;
	}
	public void setActualMobileNumber(String actualMobileNumber) {
		this.actualMobileNumber = actualMobileNumber;
	}
	public String getActualEmailADDR() {
		return actualEmailADDR;
	}
	public void setActualEmailADDR(String actualEmailADDR) {
		this.actualEmailADDR = actualEmailADDR;
	}
	
	@Override
	public String toString() {
		return "AuthExperianRequestPayload [eid=" + eid + ", stgOneHitId=" + stgOneHitId + ", stgTwoHitId="
				+ stgTwoHitId + ", actualMobileNumber=" + actualMobileNumber + ", actualEmailADDR=" + actualEmailADDR
				+ ", singleActionSessionId=" + singleActionSessionId + "]";
	}
	public String asFormData() {
		return "stgOneHitId="+stgOneHitId+"&stgTwoHitId="+stgTwoHitId+"&ActualMobileNumber="+actualMobileNumber+"&ActualEmailADDR="+actualEmailADDR;
	}
	public String getSingleActionSessionId() {
		return singleActionSessionId;
	}
	public void setSingleActionSessionId(String singleActionSessionId) {
		this.singleActionSessionId = singleActionSessionId;
	}
}
