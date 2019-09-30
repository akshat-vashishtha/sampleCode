package com.qualtech.icici.api.service;

import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.response.PolicyStatusResponse;

public interface PolicyStatusService {

	public PolicyStatusResponse getPolicyStatus(RequestIBs requestIbs, IciciReqRes rq);
}
