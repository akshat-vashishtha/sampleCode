package com.qualtech.equifax.api.utils;

import com.qualtech.equifax.api.common.dto.EquifaxTrackerDTO;
import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;
import com.qualtech.equifax.api.request.EquifaxApiRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;

public interface ConvertPCSResponseToEntitiesInt {

	EquifaxPcsAllDetails convertEquifaxResponseToEntity(EquifaxApiResponse equifaxApiResponse,
			EquifaxApiRequest equifaxApiRequest, EquifaxTrackerDTO equifaxTrackerDTO);

}
