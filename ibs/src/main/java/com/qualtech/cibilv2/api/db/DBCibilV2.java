package com.qualtech.cibilv2.api.db;

import com.qualtech.cibilv2.api.response.CibilAPIResponse2;

public interface DBCibilV2 {

	public Long insertRequestResponseTotal(Cibil2FullRequestResponse cibil2FullReqRes);
	public Long insertResponse(CibilAPIResponse2 res, Long UNIQUE_ID);
}
