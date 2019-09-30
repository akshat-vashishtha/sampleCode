package com.qualtech.equifax.api.service;

import com.qualtech.equifax.api.request.EquifaxApiRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;

public interface EquifaxServiceServiceWithoutPdf {
	public EquifaxApiResponse  callEquifaxRequest(EquifaxApiRequest equifaxapirequest,String servicetype);
}
