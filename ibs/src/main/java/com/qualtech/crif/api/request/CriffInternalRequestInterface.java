package com.qualtech.crif.api.request;

import com.qualtech.crif.api.dto.CrifTrackerDTO;

public interface CriffInternalRequestInterface {

	public  String callFirstRequest(String requestxml);
	public CrifTrackerDTO lastResponse(String Issuexml, String service);
}
