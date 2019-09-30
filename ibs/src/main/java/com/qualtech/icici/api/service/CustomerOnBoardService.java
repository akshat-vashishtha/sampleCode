package com.qualtech.icici.api.service;

import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.response.CustomerOnBoardResponse;

public interface CustomerOnBoardService {

	public CustomerOnBoardResponse getCustomerOnBoard(RequestIBs requestIbs, IciciReqRes rq);
}
