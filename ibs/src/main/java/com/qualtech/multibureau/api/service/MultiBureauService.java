package com.qualtech.multibureau.api.service;

import com.qualtech.multibureau.api.common.dto.BureauRequest;
import com.qualtech.multibureau.api.request.BureauReqRes;
import com.qualtech.multibureau.api.response.BureauResponse;

public interface MultiBureauService {

	BureauResponse bureauService(BureauRequest req,BureauReqRes request);
}
