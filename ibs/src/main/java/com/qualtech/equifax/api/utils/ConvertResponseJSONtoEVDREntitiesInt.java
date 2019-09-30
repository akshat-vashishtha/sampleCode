package com.qualtech.equifax.api.utils;

import com.qualtech.equifax.api.common.dto.EquifaxEVDRTrackerDTO;
import com.qualtech.equifax.api.entity.EquifaxEvdrAllDetails;
import com.qualtech.equifax.api.request.EquifaxAPI_EVDRRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;

public interface ConvertResponseJSONtoEVDREntitiesInt {
	EquifaxEvdrAllDetails equifaxEvdrdetailLogs(String lowerkeyjson, EquifaxApiResponse equifaxApiResponse,
			EquifaxAPI_EVDRRequest equifaxevdrApiRequest, EquifaxEVDRTrackerDTO equifaxevdrtracker);

	
}
