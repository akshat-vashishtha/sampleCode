package com.qualtech.equifax.api.response;

public class VidnsdlResponses {

	
	private NsdlRequest nsdlrequest;
	private NsdlResponse nsdlresponse;
	
	public NsdlRequest getNsdlrequest() {
		return nsdlrequest;
	}
	public void setNsdlrequest(NsdlRequest nsdlrequest) {
		this.nsdlrequest = nsdlrequest;
	}
	public NsdlResponse getNsdlresponse() {
		return nsdlresponse;
	}
	public void setNsdlresponse(NsdlResponse nsdlresponse) {
		this.nsdlresponse = nsdlresponse;
	}
	@Override
	public String toString() {
		return "VidnsdlResponses [nsdlrequest=" + nsdlrequest + ", nsdlresponse=" + nsdlresponse + "]";
	}
	
	
}
