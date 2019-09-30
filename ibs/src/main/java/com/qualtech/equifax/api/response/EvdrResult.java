package com.qualtech.equifax.api.response;

public class EvdrResult {

	private InquiryResponseHeader inquiryResponseHeader;
	private ReportData reportData;
	private InquiryRequestInfo inquiryRequestInfo;
	public InquiryResponseHeader getInquiryResponseHeader() {
		return inquiryResponseHeader;
	}
	public void setInquiryResponseHeader(InquiryResponseHeader inquiryResponseHeader) {
		this.inquiryResponseHeader = inquiryResponseHeader;
	}
	public ReportData getReportData() {
		return reportData;
	}
	public void setReportData(ReportData reportData) {
		this.reportData = reportData;
	}
	public InquiryRequestInfo getInquiryRequestInfo() {
		return inquiryRequestInfo;
	}
	public void setInquiryRequestInfo(InquiryRequestInfo inquiryRequestInfo) {
		this.inquiryRequestInfo = inquiryRequestInfo;
	}

	
	
}
