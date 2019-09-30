package com.qualtech.creditvidya.api.request;

import com.qualtech.creditvidya.api.common.dto.EmailSaveRequest;
import com.qualtech.creditvidya.api.common.dto.EmailVerificationRequest;
import com.qualtech.creditvidya.api.response.EmailSaveResponse;
import com.qualtech.creditvidya.api.response.EmailVerificationResponse;

public class CreditFullReqRes {
	
	private String uniqueId;
	private Long eId;
	private CreditReqRes reqRes;
	private EmailVerificationRequest emailVerificationRequest;
	private EmailVerificationResponse emailVerificationResponse;
	private EmailSaveRequest  emailSaveRequest;
	private EmailSaveResponse emailSaveResponse;
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public Long geteId() {
		return eId;
	}
	public void seteId(Long eId) {
		this.eId = eId;
	}
	
	
	public CreditReqRes getReqRes() {
		return reqRes;
	}
	public void setReqRes(CreditReqRes reqRes) {
		this.reqRes = reqRes;
	}
	public EmailVerificationRequest getEmailVerificationRequest() {
		return emailVerificationRequest;
	}
	public void setEmailVerificationRequest(EmailVerificationRequest emailVerificationRequest) {
		this.emailVerificationRequest = emailVerificationRequest;
	}
	public EmailVerificationResponse getEmailVerificationResponse() {
		return emailVerificationResponse;
	}
	public void setEmailVerificationResponse(EmailVerificationResponse emailVerificationResponse) {
		this.emailVerificationResponse = emailVerificationResponse;
	}
	public EmailSaveRequest getEmailSaveRequest() {
		return emailSaveRequest;
	}
	public void setEmailSaveRequest(EmailSaveRequest emailSaveRequest) {
		this.emailSaveRequest = emailSaveRequest;
	}
	public EmailSaveResponse getEmailSaveResponse() {
		return emailSaveResponse;
	}
	public void setEmailSaveResponse(EmailSaveResponse emailSaveResponse) {
		this.emailSaveResponse = emailSaveResponse;
	}


}
