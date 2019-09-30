package com.qualtech.hdfc.api.utils;

import com.qualtech.hdfc.api.request.ApiRequest;
import com.qualtech.hdfc.api.response.ApiResponseHdfcPayload;

public interface ConvertRequestInt {

	public String hdfcRequest(ApiRequest hdfcRequest);
	public ApiResponseHdfcPayload httpCall(String req, String url) ;
	
}
