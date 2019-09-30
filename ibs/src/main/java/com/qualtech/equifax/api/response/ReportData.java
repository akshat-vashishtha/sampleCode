package com.qualtech.equifax.api.response;

public class ReportData {

	private VerifyIdReponse verifyIdResponse;
	private IdAndContactInfo idAndContactInfo;
	String disclaimer;
	public VerifyIdReponse getVerifyIdResponse() {
		return verifyIdResponse;
	}
	public void setVerifyIdResponse(VerifyIdReponse verifyIdResponse) {
		this.verifyIdResponse = verifyIdResponse;
	}
	public IdAndContactInfo getIdAndContactInfo() {
		return idAndContactInfo;
	}
	public void setIdAndContactInfo(IdAndContactInfo idAndContactInfo) {
		this.idAndContactInfo = idAndContactInfo;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	@Override
	public String toString() {
		return "ReportData [verifyIdResponse=" + verifyIdResponse + ", idAndContactInfo=" + idAndContactInfo
				+ ", disclaimer=" + disclaimer + "]";
	}
	
	
}
