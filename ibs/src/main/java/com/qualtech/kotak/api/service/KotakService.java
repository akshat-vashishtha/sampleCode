package com.qualtech.kotak.api.service;

import com.qualtech.kotak.api.dto.KotakRequest;
import com.qualtech.kotak.api.dto.KotakRequestReversal;
import com.qualtech.kotak.api.request.KotakReqRes;
import com.qualtech.kotak.api.response.KotakResponse;
import com.qualtech.kotak.api.response.KotakReversalResponse;

public interface KotakService {

	KotakReversalResponse kotakReversalRequestService(KotakRequestReversal kotakRequest, KotakReqRes rq);
	KotakResponse kotakRequestService(KotakRequest kotakRequest, KotakReqRes rq);
}
