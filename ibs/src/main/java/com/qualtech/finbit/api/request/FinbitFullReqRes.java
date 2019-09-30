package com.qualtech.finbit.api.request;

import com.qualtech.finbit.api.response.FinbitAPIResponse;

public class FinbitFullReqRes {

	private FinbitApiRequest finbitapirequest;
	private FinbitAPIResponse finbitapiresponse;
	private FinbitReqRes reqRes;
	public FinbitApiRequest getFinbitapirequest() {
		return finbitapirequest;
	}
	public void setFinbitapirequest(FinbitApiRequest finbitapirequest) {
		this.finbitapirequest = finbitapirequest;
	}
	public FinbitAPIResponse getFinbitapiresponse() {
		return finbitapiresponse;
	}
	public void setFinbitapiresponse(FinbitAPIResponse finbitapiresponse) {
		this.finbitapiresponse = finbitapiresponse;
	}
	public FinbitReqRes getReqRes() {
		return reqRes;
	}
	public void setReqRes(FinbitReqRes reqRes) {
		this.reqRes = reqRes;
	}
}
