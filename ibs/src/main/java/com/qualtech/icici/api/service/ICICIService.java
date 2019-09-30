package com.qualtech.icici.api.service;

import com.qualtech.api.ibs.util.RequestIBs;
import com.qualtech.icici.api.request.IciciReqRes;
import com.qualtech.icici.api.response.PremCalResponse;

public interface ICICIService {

	PremCalResponse premiumCalculationService(RequestIBs policyStatusRequest, IciciReqRes rq);
}
