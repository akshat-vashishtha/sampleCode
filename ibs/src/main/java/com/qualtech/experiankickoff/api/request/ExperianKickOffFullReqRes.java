package com.qualtech.experiankickoff.api.request;

import com.qualtech.experiankickoff.api.common.dto.ExperianRequest;
import com.qualtech.experiankickoff.api.common.dto.MaskExperianRequest;
import com.qualtech.experiankickoff.api.response.AuthExperianResponse;
import com.qualtech.experiankickoff.api.response.ExperianGenQuestionResponse;
import com.qualtech.experiankickoff.api.response.ExperianMaskResponse;
import com.qualtech.experiankickoff.api.response.ExperianResponse;

public class ExperianKickOffFullReqRes {

	private ExperianKickoffReqRes reqRes;
	private ExperianResponse experianResponse;
	private ExperianRequest experianRequest;
	private ExperianMaskResponse expMaskResponse;
	private MaskExperianRequest expMaskRequest;
	
	private ExperianGenQuestionResponse expGenResponse;
	private ExperianGenQuestionRequest expGenRequest;
	
	private AuthExperianResponse expAuthResponse;
	private AuthExperianRequest expAuthRequest;
	
	public ExperianMaskResponse getExpMaskResponse() {
		return expMaskResponse;
	}
	public void setExpMaskResponse(ExperianMaskResponse expMaskResponse) {
		this.expMaskResponse = expMaskResponse;
	}
	public MaskExperianRequest getExpMaskRequest() {
		return expMaskRequest;
	}
	public void setExpMaskRequest(MaskExperianRequest expMaskRequest) {
		this.expMaskRequest = expMaskRequest;
	}
	
	public AuthExperianResponse getExpAuthResponse() {
		return expAuthResponse;
	}
	public void setExpAuthResponse(AuthExperianResponse expAuthResponse) {
		this.expAuthResponse = expAuthResponse;
	}
	public AuthExperianRequest getExpAuthRequest() {
		return expAuthRequest;
	}
	public void setExpAuthRequest(AuthExperianRequest expAuthRequest) {
		this.expAuthRequest = expAuthRequest;
	}
	public ExperianKickoffReqRes getReqRes() {
		return reqRes;
	}
	public void setReqRes(ExperianKickoffReqRes reqRes) {
		this.reqRes = reqRes;
	}
	public ExperianResponse getExperianResponse() {
		return experianResponse;
	}
	public void setExperianResponse(ExperianResponse experianResponse) {
		this.experianResponse = experianResponse;
	}
	public ExperianRequest getExperianRequest() {
		return experianRequest;
	}
	public void setExperianRequest(ExperianRequest experianRequest) {
		this.experianRequest = experianRequest;
	}
	public ExperianGenQuestionResponse getExpGenResponse() {
		return expGenResponse;
	}
	public void setExpGenResponse(ExperianGenQuestionResponse expGenResponse) {
		this.expGenResponse = expGenResponse;
	}
	public ExperianGenQuestionRequest getExpGenRequest() {
		return expGenRequest;
	}
	public void setExpGenRequest(ExperianGenQuestionRequest expGenRequest) {
		this.expGenRequest = expGenRequest;
	}
	
	
}
