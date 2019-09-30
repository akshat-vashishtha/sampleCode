package com.qualtech.hdfc.api.request;

import com.qualtech.hdfc.api.response.ApiResponse;

public class HdfcFullReqRes {

	public ApiRequest hdfcRequest;
	public ApiResponse hdfcResponse;
	public ApiRequest getHdfcRequest() {
		return hdfcRequest;
	}
	public void setHdfcRequest(ApiRequest hdfcRequest) {
		this.hdfcRequest = hdfcRequest;
	}
	public ApiResponse getHdfcResponse() {
		return hdfcResponse;
	}
	public void setHdfcResponse(ApiResponse hdfcResponse) {
		this.hdfcResponse = hdfcResponse;
	}
	@Override
	public String toString() {
		return "HdfcFullReqRes [hdfcRequest=" + hdfcRequest + ", hdfcResponse=" + hdfcResponse + "]";
	}
	
	
	
	
	
	
}
