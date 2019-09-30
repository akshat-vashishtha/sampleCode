package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KickoffAUTHIbsRequest implements Serializable{
	
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
	public String getSingleActionSessionId() {
		return singleActionSessionId;
	}
	public void setSingleActionSessionId(String singleActionSessionId) {
		this.singleActionSessionId = singleActionSessionId;
	}
	@Override
	public String toString() {
		return "KickoffAUTHIbsRequest [stgOneHitId=" + stgOneHitId + ", stgTwoHitId=" + stgTwoHitId
				+ ", actualMobileNumber=" + actualMobileNumber + ", actualEmailADDR=" + actualEmailADDR
				+ ", singleActionSessionId=" + singleActionSessionId + "]";
	}
    
    

}
