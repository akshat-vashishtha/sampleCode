package com.qualtech.cibil.api.db;

import com.qualtech.cibil.api.entity.CibilReqResVpn;
import com.qualtech.cibil.api.response.CibilResponsePayload;

public interface DBCibil {

	int insertCibilData(CibilResponsePayload cibilresponsepayload, String correalationId, String requestData,
			String responseData, CibilReqResVpn rq);

}
