package com.qualtech.multibureau.api.request;

import com.qualtech.multibureau.api.common.dto.BureauRequest;
import com.qualtech.multibureau.api.response.BureauResponse;

public class BureauFullReqRes {

	private BureauReqRes reqRes;
	
	private BureauRequest bureauRequest;
	
	private BureauResponse bureauResponse;
	
	

	public BureauReqRes getReqRes() {
		return reqRes;
	}

	public void setReqRes(BureauReqRes reqRes) {
		this.reqRes = reqRes;
	}

	public BureauRequest getBureauRequest() {
		return bureauRequest;
	}

	public void setBureauRequest(BureauRequest bureauRequest) {
		this.bureauRequest = bureauRequest;
	}

	public BureauResponse getBureauResponse() {
		return bureauResponse;
	}

	public void setBureauResponse(BureauResponse bureauResponse) {
		this.bureauResponse = bureauResponse;
	}

	@Override
	public String toString() {
		return "BureauFullReqRes [reqRes=" + reqRes + ", bureauRequest=" + bureauRequest + ", bureauResponse="
				+ bureauResponse + "]";
	}
}
