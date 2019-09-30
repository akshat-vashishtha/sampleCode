package com.qualtech.experiankickoff.api.response;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_EX2_AUTH_RESPONSE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperianAuthResPayloadResult {

	@JsonIgnore
	@Id
	private String eid;
	private String responseJson;
	private String stgOneHitId;
	private String stgTwoHitId;
	@Lob
	private String showHtmlReportForCreditReport;
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}
	public String getStgOneHitId() {
		return stgOneHitId;
	}
	public void setStgOneHitId(String stgOneHitId) {
		this.stgOneHitId = stgOneHitId;
	}
	public String getStgTwoHitId() {
		return stgTwoHitId;
	}
	public void setStgTwoHitId(String stgTwoHitId) {
		this.stgTwoHitId = stgTwoHitId;
	}
	public String getShowHtmlReportForCreditReport() {
		return showHtmlReportForCreditReport;
	}
	public void setShowHtmlReportForCreditReport(
			String showHtmlReportForCreditReport) {
		this.showHtmlReportForCreditReport = showHtmlReportForCreditReport;
	}
	
	@Override
	public String toString(){
		return "ExperianAuthResPayloadResult [ responseJson="+responseJson+", stgOneHitId="+stgOneHitId+", stgTwoHitId="+stgTwoHitId+", showHtmlReportForCreditReport="+showHtmlReportForCreditReport+" ]";
	}
}
