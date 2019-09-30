package com.qualtech.icici.api.service;

import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.response.WelcomeKitResponse;

public interface WelcomeKitService {

	public WelcomeKitResponse getWelcomeKit(RequestIBs requestIbs, IciciReqRes rq);
}
