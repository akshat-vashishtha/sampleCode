package com.qualtech.creditvidya.api.service;

import com.qualtech.creditvidya.api.common.dto.EmailSaveRequest;
import com.qualtech.creditvidya.api.common.dto.EmailVerificationRequest;
import com.qualtech.creditvidya.api.request.CreditReqRes;
import com.qualtech.creditvidya.api.request.HttpCallDetails;
import com.qualtech.creditvidya.api.response.EmailSaveResponse;
import com.qualtech.creditvidya.api.response.EmailVerificationResponse;

public interface CreditVidyaService 
{
	public EmailSaveResponse emailService(EmailSaveRequest emailSaveRequest,CreditReqRes reqRes);
	
	public HttpCallDetails httpCall(String request,String pUrl);



	public EmailVerificationResponse emailVerificationService(EmailVerificationRequest emailVerificationRequest,
			CreditReqRes rqres);

	
}
