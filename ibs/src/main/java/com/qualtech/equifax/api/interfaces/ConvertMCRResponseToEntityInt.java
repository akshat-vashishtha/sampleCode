package com.qualtech.equifax.api.interfaces;

import com.qualtech.equifax.api.common.dto.EquifaxTrackerDTO;
import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;
import com.qualtech.equifax.api.request.EquifaxApiRequest;
import com.qualtech.equifax.api.response.EquifaxApiResponse;

public interface ConvertMCRResponseToEntityInt {

	EquifaxMcrAllDetaills convertEquifaxResponseToEntity(EquifaxApiResponse equifaxApiResponse,
			EquifaxApiRequest equifaxApiRequest, EquifaxTrackerDTO equifaxTrackerDTO);

}
