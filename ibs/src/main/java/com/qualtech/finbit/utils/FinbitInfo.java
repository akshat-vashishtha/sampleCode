package com.qualtech.finbit.utils;

import com.qualtech.finbit.api.request.AccntDetailSecondApi;
import com.qualtech.finbit.api.request.LoginAuthentication;

public class FinbitInfo {

	private int firstApiStatusCode;
	private int secondApiStatusCode;
	private int thirdApiStatusCode;
	private LoginAuthentication loginAuthentication;
	private AccntDetailSecondApi accntDetailSecondApi;
	public int getFirstApiStatusCode() {
		return firstApiStatusCode;
	}
	public void setFirstApiStatusCode(int firstApiStatusCode) {
		this.firstApiStatusCode = firstApiStatusCode;
	}
	public int getSecondApiStatusCode() {
		return secondApiStatusCode;
	}
	public void setSecondApiStatusCode(int secondApiStatusCode) {
		this.secondApiStatusCode = secondApiStatusCode;
	}
	public int getThirdApiStatusCode() {
		return thirdApiStatusCode;
	}
	public void setThirdApiStatusCode(int thirdApiStatusCode) {
		this.thirdApiStatusCode = thirdApiStatusCode;
	}
	public LoginAuthentication getLoginAuthentication() {
		return loginAuthentication;
	}
	public void setLoginAuthentication(LoginAuthentication loginAuthentication) {
		this.loginAuthentication = loginAuthentication;
	}
	
	public AccntDetailSecondApi getAccntDetailSecondApi() {
		return accntDetailSecondApi;
	}
	public void setAccntDetailSecondApi(AccntDetailSecondApi accntDetailSecondApi) {
		this.accntDetailSecondApi = accntDetailSecondApi;
	}
	@Override
	public String toString() {
		return "FinbitInfo [firstApiStatusCode=" + firstApiStatusCode + ", secondApiStatusCode=" + secondApiStatusCode
				+ ", thirdApiStatusCode=" + thirdApiStatusCode + ", loginAuthentication=" + loginAuthentication
				+ ", accntDetailSecondApi=" + accntDetailSecondApi + "]";
	}
	
	
}
