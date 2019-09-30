package com.qualtech.icici.api.request;

import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.icici.api.response.CustomerOnBoardResponse;
import com.qualtech.icici.api.response.PolicyStatusResponse;
import com.qualtech.icici.api.response.PremCalResponse;
import com.qualtech.icici.api.response.WelcomeKitResponse;

public class IciciFullReqRes {

	private IciciReqRes reqRes;
	private PolicyStatusResponse policyStatusResponse;
	private PolicyStatusRequest policyStatusRequest;
	private RequestIBs premCalcRequest;
	private PremCalResponse premCalResponse;
	private CustomerOnBoardRequest customerOnBoardRequest;
	private CustomerOnBoardResponse customerOnBoardResponse;
	private WelcomeKitResponse welcomeKitResponse;
	private WelcomeKitRequest welcomeKitRequest;
	
	public CustomerOnBoardResponse getCustomerOnBoardResponse() {
		return customerOnBoardResponse;
	}
	public void setCustomerOnBoardResponse(CustomerOnBoardResponse customerOnBoardResponse) {
		this.customerOnBoardResponse = customerOnBoardResponse;
	}
	public CustomerOnBoardRequest getCustomerOnBoardRequest() {
		return customerOnBoardRequest;
	}
	public void setCustomerOnBoardRequest(CustomerOnBoardRequest customerOnBoardRequest) {
		this.customerOnBoardRequest = customerOnBoardRequest;
	}
	public IciciReqRes getReqRes() {
		return reqRes;
	}
	public void setReqRes(IciciReqRes reqRes) {
		this.reqRes = reqRes;
	}
	public PolicyStatusResponse getPolicyStatusResponse() {
		return policyStatusResponse;
	}
	
	public void setPolicyStatusResponse(PolicyStatusResponse policyStatusResponse) {
		this.policyStatusResponse = policyStatusResponse;
	}
	public PolicyStatusRequest getPolicyStatusRequest() {
		return policyStatusRequest;
	}
	public void setPolicyStatusRequest(PolicyStatusRequest policyStatusRequest) {
		this.policyStatusRequest = policyStatusRequest;
	}
	
	public RequestIBs getPremCalcRequest() {
		return premCalcRequest;
	}
	public void setPremCalcRequest(RequestIBs premCalcRequest) {
		this.premCalcRequest = premCalcRequest;
	}
	public PremCalResponse getPremCalResponse() {
		return premCalResponse;
	}
	public void setPremCalResponse(PremCalResponse premCalResponse) {
		this.premCalResponse = premCalResponse;
	}
	public WelcomeKitResponse getWelcomeKitResponse() {
		return welcomeKitResponse;
	}
	public void setWelcomeKitResponse(WelcomeKitResponse welcomeKitResponse) {
		this.welcomeKitResponse = welcomeKitResponse;
	}
	public WelcomeKitRequest getWelcomeKitRequest() {
		return welcomeKitRequest;
	}
	public void setWelcomeKitRequest(WelcomeKitRequest welcomeKitRequest) {
		this.welcomeKitRequest = welcomeKitRequest;
	}
	@Override
	public String toString() {
		return "IciciFullReqRes [reqRes=" + reqRes + ", policyStatusResponse=" + policyStatusResponse
				+ ", policyStatusRequest=" + policyStatusRequest + ", premCalcRequest=" + premCalcRequest
				+ ", premCalResponse=" + premCalResponse + ", customerOnBoardRequest=" + customerOnBoardRequest
				+ ", customerOnBoardResponse=" + customerOnBoardResponse + ", welcomeKitResponse=" + welcomeKitResponse
				+ ", welcomeKitRequest=" + welcomeKitRequest + "]";
	}
	
}
