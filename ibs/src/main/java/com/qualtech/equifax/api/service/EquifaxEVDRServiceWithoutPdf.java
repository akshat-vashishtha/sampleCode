package com.qualtech.equifax.api.service;

import com.qualtech.equifax.api.request.EquifaxAPI_EVDRRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;

public interface EquifaxEVDRServiceWithoutPdf 
{
	public EquifaxApiResponse processEquifaxVIDRequest(EquifaxAPI_EVDRRequest equifaxApiRequest) ;
	
}
