package com.qualtech.hdfc.api.service.impl;

import com.qualtech.hdfc.api.request.ApiRequest;
import com.qualtech.hdfc.api.request.HdfcReqRes;
import com.qualtech.hdfc.api.response.ApiResponse;

public interface HdfcService {
	
	ApiResponse hdfcRequestServiceQC(ApiRequest hdfcRequest, HdfcReqRes rq);
}
