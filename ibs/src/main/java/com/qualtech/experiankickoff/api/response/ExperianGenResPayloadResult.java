package com.qualtech.experiankickoff.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_EX2_GEN_RESPONSE")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperianGenResPayloadResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6658534752267440571L;

	@JsonIgnore
	@Id
	private String eid;
	
	@OneToOne (fetch=FetchType.LAZY,mappedBy="experianGenResPayloadResult", cascade=CascadeType.ALL)
	private ExperianQuestionToCustomer questionToCustomer;
	
	private String responseJson;
	@Lob
	private String showHtmlReportForCreditReport;
	private String stgOneHitId;
	private String stgTwoHitId;
	private String answer;
	private String questionId;
	private String questionType;
	private String ipAddress;
	private String merchantRefNo;
	
	public ExperianQuestionToCustomer getQuestionToCustomer() {
		questionToCustomer.setExperianGenResPayloadResult(this);
		return questionToCustomer;
	}
	public void setQuestionToCustomer(ExperianQuestionToCustomer questionToCustomer) {
		this.questionToCustomer = questionToCustomer;
	}
	public String getResponseJson() {
		return responseJson;
	}
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}
	public String getShowHtmlReportForCreditReport() {
		return showHtmlReportForCreditReport;
	}
	public void setShowHtmlReportForCreditReport(
			String showHtmlReportForCreditReport) {
		this.showHtmlReportForCreditReport = showHtmlReportForCreditReport;
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
	
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getMerchantRefNo() {
		return merchantRefNo;
	}
	public void setMerchantRefNo(String merchantRefNo) {
		this.merchantRefNo = merchantRefNo;
	}
	@Override
	public String toString() {
		return "ExperianGenResPayloadResult [questionToCustomer=" + questionToCustomer + ", responseJson="
				+ responseJson + ", showHtmlReportForCreditReport=" + showHtmlReportForCreditReport + ", stgOneHitId="
				+ stgOneHitId + ", stgTwoHitId=" + stgTwoHitId + ", answer=" + answer + ", questionId=" + questionId
				+ ", questionType=" + questionType + ", ipAddress=" + ipAddress + ", merchantRefNo=" + merchantRefNo
				+ "]";
	}

}
