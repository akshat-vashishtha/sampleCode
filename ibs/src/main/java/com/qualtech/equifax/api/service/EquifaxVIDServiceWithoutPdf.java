package com.qualtech.equifax.api.service;

import com.qualtech.equifax.api.request.EquifaxAPI_VIDRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;

public interface EquifaxVIDServiceWithoutPdf 
{
	EquifaxApiResponse processEquifaxVIDRequest(EquifaxAPI_VIDRequest request);
}
