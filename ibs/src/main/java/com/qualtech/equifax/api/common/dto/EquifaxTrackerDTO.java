package com.qualtech.equifax.api.common.dto;

import java.io.Serializable;

import com.qualtech.equifax.api.entity.common.EquifaxIdentityInfo;
import com.qualtech.equifax.api.entity.common.EquifaxPersonalInfo;
import com.qualtech.equifax.api.entity.mcr.EquifaxMCRIdAndContactDetails;

public class EquifaxTrackerDTO  implements Serializable
{
	private static final long serialVersionUID = -3449564081969442942L;
	private String equifaxApiReq;
	private String equifaxApiRes;
	private String equifaxjsonRequest;
	private String equifaxjsonResponse;
	private String typeofService;
	private String responseTransactionJson;
	private String finalHtml;
	private String byteArray;
	private String disclaimer;
	private EquifaxIdentityInfo equifaxIdentityInfo;
	private EquifaxMCRIdAndContactDetails mcrIdAndContactDetails;
	private EquifaxPersonalInfo equifaxPersonalInfo;
	
	
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	
	public EquifaxIdentityInfo getEquifaxIdentityInfo() {
		return equifaxIdentityInfo;
	}
	public void setEquifaxIdentityInfo(EquifaxIdentityInfo equifaxIdentityInfo) {
		this.equifaxIdentityInfo = equifaxIdentityInfo;
	}
	public EquifaxMCRIdAndContactDetails getMcrIdAndContactDetails() {
		return mcrIdAndContactDetails;
	}
	public void setMcrIdAndContactDetails(EquifaxMCRIdAndContactDetails mcrIdAndContactDetails) {
		this.mcrIdAndContactDetails = mcrIdAndContactDetails;
	}
	public EquifaxPersonalInfo getEquifaxPersonalInfo() {
		return equifaxPersonalInfo;
	}
	public void setEquifaxPersonalInfo(EquifaxPersonalInfo equifaxPersonalInfo) {
		this.equifaxPersonalInfo = equifaxPersonalInfo;
	}
	public void setFinalHtml(String finalHtml) {
		this.finalHtml = finalHtml;
	}
	public String getFinalHtml() {
		return finalHtml;
	}
	public void setByteArray(String byteArray) {
		this.byteArray = byteArray;
	}

	public String getByteArray() {
		return byteArray;
	}
	public String getResponseTransactionJson() 
	{
		return responseTransactionJson;
	}
	public void setResponseTransactionJson(String responseTransactionJson) {
		this.responseTransactionJson = responseTransactionJson;
	}
	public String getTypeofService()
	{
		return typeofService;
	}

	public void setTypeofService(String typeofService) {
		this.typeofService = typeofService;
	}
	
	public String getEquifaxApiReq() {
		return equifaxApiReq;
	}
	public void setEquifaxApiReq(String equifaxApiReq) {
		this.equifaxApiReq = equifaxApiReq;
	}
	public String getEquifaxApiRes() {
		return equifaxApiRes;
	}
	public void setEquifaxApiRes(String equifaxApiRes) {
		this.equifaxApiRes = equifaxApiRes;
	}
	public String getEquifaxjsonRequest() {
		return equifaxjsonRequest;
	}
	public void setEquifaxjsonRequest(String equifaxjsonRequest) {
		this.equifaxjsonRequest = equifaxjsonRequest;
	}
	public String getEquifaxjsonResponse() {
		return equifaxjsonResponse;
	}
	public void setEquifaxjsonResponse(String equifaxjsonResponse) {
		this.equifaxjsonResponse = equifaxjsonResponse;
	}
	@Override
	public String toString() {
		return "EquifaxTrackerDTO [equifaxApiReq=" + equifaxApiReq + ", equifaxApiRes=" + equifaxApiRes
				+ ", equifaxjsonRequest=" + equifaxjsonRequest + ", equifaxjsonResponse=" + equifaxjsonResponse
				+ ", typeofService=" + typeofService + ", responseTransactionJson=" + responseTransactionJson
				+ ", finalHtml=" + finalHtml + ", byteArray=" + byteArray + "]";
	}



	
	
}
