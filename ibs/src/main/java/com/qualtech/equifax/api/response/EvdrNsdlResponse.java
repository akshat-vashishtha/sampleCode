package com.qualtech.equifax.api.response;

public class EvdrNsdlResponse {

	String nsdlRespId;
	String pan;
	String returnCodeDesc;
	public String getNsdlRespId() {
		return nsdlRespId;
	}
	public void setNsdlRespId(String nsdlRespId) {
		this.nsdlRespId = nsdlRespId;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getReturnCodeDesc() {
		return returnCodeDesc;
	}
	public void setReturnCodeDesc(String returnCodeDesc) {
		this.returnCodeDesc = returnCodeDesc;
	}
	@Override
	public String toString() {
		return "EvdrNsdlResponse [nsdlRespId=" + nsdlRespId + ", pan=" + pan + ", returnCodeDesc=" + returnCodeDesc
				+ "]";
	}
	
	
}
