package com.qualtech.icici.api.db;

import com.qualtech.api.db.AuthValidator;
import com.qualtech.api.ibs.util.Header;
import com.qualtech.icici.api.request.IciciFullReqRes;

public interface DBIcici {

AuthValidator validateAuth(Header header);
	
	int insertPremCalReqRes(IciciFullReqRes fullReqRes);
	int insertCustomerOnBoardReqRes(IciciFullReqRes fullReqRes);
	int insertWelcomeKitReqRes(IciciFullReqRes fullReqRes);
	int insertPolicyStatusReqRes(IciciFullReqRes fullReqRes);
}
