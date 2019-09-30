package com.qualtech.crif.api.dto;

import java.io.Serializable;

import com.qualtech.crif.api.crif.response.Indvreportfile;

public class CrifTrackerDTO implements Serializable
{
	private static final long serialVersionUID = -7711529974342207694L;
	private String apiResponse;
	private String crifApiReq;
	private String crifApiRes;
	private String crifApiIssueXmlReq;
	private String crifApiIssueXmlRes;
	private Indvreportfile indvreportfile;
	private String htmlResponse;
	private String service;
	private String pdfByteArray;
	private String pdfPath;
	
	public String getPdfByteArray() {
		return pdfByteArray;
	}
	public void setPdfByteArray(String pdfByteArray) {
		this.pdfByteArray = pdfByteArray;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	
	public String getHtmlResponse() {
		return htmlResponse;
	}
	public void setHtmlResponse(String htmlResponse) {
		this.htmlResponse = htmlResponse;
	}
	public String getApiResponse() {
		return apiResponse;
	}
	public void setApiResponse(String apiResponse) {
		this.apiResponse = apiResponse;
	}
	public String getCrifApiReq() {
		return crifApiReq;
	}
	public void setCrifApiReq(String crifApiReq) {
		this.crifApiReq = crifApiReq;
	}
	public String getCrifApiRes() {
		return crifApiRes;
	}
	public void setCrifApiRes(String crifApiRes) {
		this.crifApiRes = crifApiRes;
	}
	public String getCrifApiIssueXmlReq() {
		return crifApiIssueXmlReq;
	}
	public void setCrifApiIssueXmlReq(String crifApiIssueXmlReq) {
		this.crifApiIssueXmlReq = crifApiIssueXmlReq;
	}
	public String getCrifApiIssueXmlRes() {
		return crifApiIssueXmlRes;
	}
	public void setCrifApiIssueXmlRes(String crifApiIssueXmlRes) {
		this.crifApiIssueXmlRes = crifApiIssueXmlRes;
	}
	public Indvreportfile getIndvreportfile() {
		return indvreportfile;
	}
	public void setIndvreportfile(Indvreportfile indvreportfile) {
		this.indvreportfile = indvreportfile;
	}
	@Override
	public String toString() {
		return "CrifTrackerDTO [apiResponse=" + apiResponse + ", crifApiReq=" + crifApiReq + ", crifApiRes="
				+ crifApiRes + ", crifApiIssueXmlReq=" + crifApiIssueXmlReq + ", crifApiIssueXmlRes="
				+ crifApiIssueXmlRes + ", indvreportfile=" + indvreportfile + ", htmlResponse=" + htmlResponse
				+ ", service=" + service + ", pdfByteArray=" + pdfByteArray + "]";
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	
	
}
