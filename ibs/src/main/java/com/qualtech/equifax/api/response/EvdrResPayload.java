package com.qualtech.equifax.api.response;

public class EvdrResPayload {

	private EvdrResult result;

	public EvdrResult getResult() {
		return result;
	}

	public void setResult(EvdrResult result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "EvdrResPayload [result=" + result + "]";
	}
	
	
}
