package com.qualtech.hdfc.api.db;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.ServiceMaster;
import com.qualtech.hdfc.api.request.HdfcFullReqRes;
import com.qualtech.hdfc.api.request.HdfcReqRes;
import com.qualtech.hdfc.api.request.Header;

public interface DBHdfc {

	
	ServiceMaster getServiceCredential(String serviceRequest);

	void insertHdfcRequestResponse(HdfcReqRes rq, HdfcFullReqRes fullReqRes);

	AuthValidator validateAuth(Header header);

}
