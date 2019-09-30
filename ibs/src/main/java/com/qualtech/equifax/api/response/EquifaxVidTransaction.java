package com.qualtech.equifax.api.response;


public class EquifaxVidTransaction {

	private InquiryResponseHeader inquiryresponseheader;
	private EquifaxVerifyIdResponse verifyidresponse;
	private EquifaxVerifyIdInquiryRequestInfo inquiryrequestinfo;
	private String disclaimer;
	
	
	public EquifaxVerifyIdResponse getVerifyidresponse() {
		return verifyidresponse;
	}
	public void setVerifyidresponse(EquifaxVerifyIdResponse verifyidresponse) {
		this.verifyidresponse = verifyidresponse;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	
	
	@Override
	public String toString() {
		return "EquifaxVidTransaction [inquiryresponseheader=" + inquiryresponseheader + ", verifyidresponse="
				+ verifyidresponse + ", inquiryrequestinfo=" + inquiryrequestinfo + ", disclaimer=" + disclaimer + "]";
	}
	public InquiryResponseHeader getInquiryresponseheader() {
		return inquiryresponseheader;
	}
	public void setInquiryresponseheader(InquiryResponseHeader inquiryresponseheader) {
		this.inquiryresponseheader = inquiryresponseheader;
	}
	public EquifaxVerifyIdInquiryRequestInfo getInquiryrequestinfo() {
		return inquiryrequestinfo;
	}
	public void setInquiryrequestinfo(EquifaxVerifyIdInquiryRequestInfo inquiryrequestinfo) {
		this.inquiryrequestinfo = inquiryrequestinfo;
	}
	
	
	
}
