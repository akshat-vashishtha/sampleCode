package com.qualtech.crif.api.common.dto;

import java.util.List;

public class IciciResponseDto {
	
private String applicatioId;
private String decision;
private String errorMsg;
private String reason;
private List<LoanOptionsDto> loanList;
private String response;
private String finalLoanAmount;
private String finalTenure;
private String fIntRate;
private String fProcFees;
private String fEmi;
private String fSplIntrate;
private String fSplProcFees;
private String request;
public String getRequest() {
	return request;
}
public void setRequest(String request) {
	this.request = request;
}
public String getfIntRate() {
	return fIntRate;
}
public void setfIntRate(String fIntRate) {
	this.fIntRate = fIntRate;
}
public String getfProcFees() {
	return fProcFees;
}
public void setfProcFees(String fProcFees) {
	this.fProcFees = fProcFees;
}
public String getfEmi() {
	return fEmi;
}
public void setfEmi(String fEmi) {
	this.fEmi = fEmi;
}
public String getfSplIntrate() {
	return fSplIntrate;
}
public void setfSplIntrate(String fSplIntrate) {
	this.fSplIntrate = fSplIntrate;
}
public String getfSplProcFees() {
	return fSplProcFees;
}
public void setfSplProcFees(String fSplProcFees) {
	this.fSplProcFees = fSplProcFees;
}
public String getFinalLoanAmount() {
	return finalLoanAmount;
}
public void setFinalLoanAmount(String finalLoanAmount) {
	this.finalLoanAmount = finalLoanAmount;
}
public String getFinalTenure() {
	return finalTenure;
}
public void setFinalTenure(String finalTenure) {
	this.finalTenure = finalTenure;
}
public String getApplicatioId() {
	return applicatioId;
}
public void setApplicatioId(String applicatioId) {
	this.applicatioId = applicatioId;
}
public String getDecision() {
	return decision;
}
public void setDecision(String decision) {
	this.decision = decision;
}
public String getErrorMsg() {
	return errorMsg;
}
public void setErrorMsg(String errorMsg) {
	this.errorMsg = errorMsg;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}
public List<LoanOptionsDto> getLoanList() {
	return loanList;
}
public void setLoanList(List<LoanOptionsDto> loanList) {
	this.loanList = loanList;
}
public String getResponse() {
	return response;
}
public void setResponse(String response) {
	this.response = response;
}
}
