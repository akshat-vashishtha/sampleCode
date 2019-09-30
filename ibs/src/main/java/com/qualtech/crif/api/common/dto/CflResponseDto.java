package com.qualtech.crif.api.common.dto;

public class CflResponseDto {

	private String status;
	private String genLeadId;
	private String errorFlag;
	private String errorMsg;
	private String maxAllwLoanAmt;
	private String loanAppId;
	private String roi;
	private String finnOneId;
	private String pushResponse;
	private String GetAppResponse;
    private String loanStatus;
    private String GetAppRequest;
    private String pushRequest;
   
    public String getGetAppRequest() {
		return GetAppRequest;
	}
	public void setGetAppRequest(String getAppRequest) {
		GetAppRequest = getAppRequest;
	}
	public String getPushRequest() {
		return pushRequest;
	}
	public void setPushRequest(String pushRequest) {
		this.pushRequest = pushRequest;
	}
	public String getPushResponse() {
		return pushResponse;
	}
	public void setPushResponse(String pushResponse) {
		this.pushResponse = pushResponse;
	}
	public String getGetAppResponse() {
		return GetAppResponse;
	}
	public void setGetAppResponse(String getAppResponse) {
		GetAppResponse = getAppResponse;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getGenLeadId() {
		return genLeadId;
	}
	public void setGenLeadId(String genLeadId) {
		this.genLeadId = genLeadId;
	}
	public String getErrorFlag() {
		return errorFlag;
	}
	public void setErrorFlag(String errorFlag) {
		this.errorFlag = errorFlag;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getMaxAllwLoanAmt() {
		return maxAllwLoanAmt;
	}
	public void setMaxAllwLoanAmt(String maxAllwLoanAmt) {
		this.maxAllwLoanAmt = maxAllwLoanAmt;
	}
	public String getLoanAppId() {
		return loanAppId;
	}
	public void setLoanAppId(String loanAppId) {
		this.loanAppId = loanAppId;
	}
	public String getRoi() {
		return roi;
	}
	public void setRoi(String roi) {
		this.roi = roi;
	}
	public String getFinnOneId() {
		return finnOneId;
	}
	public void setFinnOneId(String finnOneId) {
		this.finnOneId = finnOneId;
	}
}
