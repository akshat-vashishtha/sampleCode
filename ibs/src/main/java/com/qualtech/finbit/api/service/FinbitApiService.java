package com.qualtech.finbit.api.service;

import com.qualtech.finbit.api.request.FinbitApiRequest;
import com.qualtech.finbit.api.request.FinbitReqRes;
import com.qualtech.finbit.api.response.FinbitAPIResponse;

public interface FinbitApiService 
{
	 public FinbitAPIResponse callFinbitService(FinbitApiRequest finbitapirequest, FinbitReqRes reqRes);
}
