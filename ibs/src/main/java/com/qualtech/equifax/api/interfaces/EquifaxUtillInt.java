package com.qualtech.equifax.api.interfaces;

import com.qualtech.equifax.api.request.EquifaxAPI_EVDRRequest;
import com.qualtech.equifax.api.request.EquifaxAPI_VIDRequest;
import com.qualtech.equifax.api.request.EquifaxApiRequest;

public interface EquifaxUtillInt {

	String rquestxmlforPCS(EquifaxApiRequest equifaxapirequest);

	String rquestxmlforMCR(EquifaxApiRequest equifaxapirequest);

	String requestxmlForVID(EquifaxAPI_VIDRequest equifaxApiRequest);

	String requestxmlForEVDR(EquifaxAPI_EVDRRequest equifaxApiRequest);

}
