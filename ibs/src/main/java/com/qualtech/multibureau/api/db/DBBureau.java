package com.qualtech.multibureau.api.db;

import java.util.List;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.db.ServiceMaster;
import com.qualtech.multibureau.api.common.dto.AllBureauDto;
import com.qualtech.multibureau.api.request.BureauFullReqRes;
import com.qualtech.multibureau.api.request.Header;

public interface DBBureau {
	ServiceMaster getServiceCredential(String serviceRequest);
	int insertBureauReqRes(BureauFullReqRes fullReqRes,AllBureauDto dto);
	AuthValidator validateAuth(Header header);
}