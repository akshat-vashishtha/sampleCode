package com.qualtech.cibilv2.api.service;

import java.util.Map;

import com.qualtech.cibilv2.api.request.CibilReqRes;
import com.qualtech.cibilv2.api.request.CibilRequest2;
import com.qualtech.cibilv2.api.response.CibilAPIResponse2;

public interface CibilApiServiceV2 {

	public String convertJavaPojoToStringRequest(CibilRequest2 cibilapirequest) ;
	public CibilAPIResponse2 cibilRequestService(CibilRequest2 cibilapirequest, CibilReqRes cibilReqRes);
	public Map<String, String> requiredParam();
}
