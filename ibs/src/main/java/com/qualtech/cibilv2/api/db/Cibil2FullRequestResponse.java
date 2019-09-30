package com.qualtech.cibilv2.api.db;

import com.qualtech.cibilv2.api.request.CibilReqRes;
import com.qualtech.cibilv2.api.request.CibilRequest2;
import com.qualtech.cibilv2.api.response.CibilAPIResponse2;

public class Cibil2FullRequestResponse {
	
	private Long EID;
	private String uniqueId;
	private CibilReqRes cibilReqRes;
	private CibilRequest2 cibilRequest;
	private CibilAPIResponse2 cibilResponse;
	
	public Long getEID() {
		return EID;
	}
	public void setEID(Long eID) {
		EID = eID;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public CibilReqRes getCibilReqRes() {
		return cibilReqRes;
	}
	public void setCibilReqRes(CibilReqRes cibilReqRes) {
		this.cibilReqRes = cibilReqRes;
	}
	public CibilRequest2 getCibilRequest() {
		return cibilRequest;
	}
	public void setCibilRequest(CibilRequest2 cibilRequest) {
		this.cibilRequest = cibilRequest;
	}
	public CibilAPIResponse2 getCibilResponse() {
		return cibilResponse;
	}
	public void setCibilResponse(CibilAPIResponse2 cibilResponse) {
		this.cibilResponse = cibilResponse;
	}
	
}
