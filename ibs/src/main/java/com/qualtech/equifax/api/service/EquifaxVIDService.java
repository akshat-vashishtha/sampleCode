package com.qualtech.equifax.api.service;

import com.qualtech.equifax.api.request.EquifaxAPI_VIDRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;

public interface EquifaxVIDService 
{
	EquifaxApiResponse processEquifaxVIDRequest(EquifaxAPI_VIDRequest request);

}
