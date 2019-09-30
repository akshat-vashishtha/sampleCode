package com.qualtech.hdfc.api.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MetaDataRes {

	private String prn;
	private String coi;
	private String status;
	private String thankUPageUrl;
	private String downloadPDFUrl;
	private String customerId;
	private List<Errors> errors;
	
	public String getPrn() {
		return prn;
	}
	public void setPrn(String prn) {
		this.prn = prn;
	}
	public String getCoi() {
		return coi;
	}
	public void setCoi(String coi) {
		this.coi = coi;
	}
	@Override
	public String toString() {
		return "MetaDataRes [prn=" + prn + ", coi=" + coi + ", status=" + status + ", thankUPageUrl=" + thankUPageUrl
				+ ", downloadPDFUrl=" + downloadPDFUrl + ", customerId=" + customerId + ", errors=" + errors + "]";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getThankUPageUrl() {
		return thankUPageUrl;
	}
	public void setThankUPageUrl(String thankUPageUrl) {
		this.thankUPageUrl = thankUPageUrl;
	}
	public String getDownloadPDFUrl() {
		return downloadPDFUrl;
	}
	public void setDownloadPDFUrl(String downloadPDFUrl) {
		this.downloadPDFUrl = downloadPDFUrl;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public List<Errors> getErrors() {
		return errors;
	}
	public void setErrors(List<Errors> errors) {
		this.errors = errors;
	}
	
	
	
}
