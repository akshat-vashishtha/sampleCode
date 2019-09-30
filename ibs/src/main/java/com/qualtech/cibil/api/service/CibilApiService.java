package com.qualtech.cibil.api.service;

import com.qualtech.cibil.api.entity.CibilReqResVpn;
import com.qualtech.cibil.api.request.CibilApiRequest;
import com.qualtech.cibil.api.response.CibilAPIResponse;

public interface CibilApiService 
{
	 public CibilAPIResponse callCibilService(CibilApiRequest cibilapirequest, CibilReqResVpn rq);
}
