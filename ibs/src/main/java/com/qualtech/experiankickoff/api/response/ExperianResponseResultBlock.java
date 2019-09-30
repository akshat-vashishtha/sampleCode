package com.qualtech.experiankickoff.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_EX2_RESPONSE")
public class ExperianResponseResultBlock implements Serializable{

	private static final long serialVersionUID = -8251784410816419882L;

	@JsonIgnore
	@Id
	private String eid;
	private String errorString;
	private String stageOneId_;
	private String stageTwoId_;
	@Lob
	private String showHtmlReportForCreditReport;
	private String newUserId;
	private String subscriptionMsg;

	public String getNewUserId() {
		return newUserId;
	}

	public void setNewUserId(String newUserId) {
		this.newUserId = newUserId;
	}

	public String getSubscriptionMsg() {
		return subscriptionMsg;
	}

	public void setSubscriptionMsg(String subscriptionMsg) {
		this.subscriptionMsg = subscriptionMsg;
	}

	public String getErrorString() {
		return errorString;
	}

	public void setErrorString(String errorString) {
		this.errorString = errorString;
	}



	public String getStageOneId_() {
		return stageOneId_;
	}

	public void setStageOneId_(String stageOneId_) {
		this.stageOneId_ = stageOneId_;
	}

	public String getStageTwoId_() {
		return stageTwoId_;
	}

	public void setStageTwoId_(String stageTwoId_) {
		this.stageTwoId_ = stageTwoId_;
	}

	public String getShowHtmlReportForCreditReport() {
		return showHtmlReportForCreditReport;
	}

	public void setShowHtmlReportForCreditReport(String showHtmlReportForCreditReport) {
		this.showHtmlReportForCreditReport = showHtmlReportForCreditReport;
	}

	@Override
	public String toString() {
		return "ExperianResponseResultBlock [errorString=" + errorString + ", stageOneId=" + stageOneId_ + ", stageTwoId="+stageTwoId_+", showHtmlReportForCreditReport="+showHtmlReportForCreditReport+"]";
	}

	public String getEid() {
		return eid;
	}

	public void setEid(String eid) {
		this.eid = eid;
	}
	
	
	
	

	
}
