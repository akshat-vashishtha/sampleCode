package com.qualtech.experiankickoff.api.response;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_EX2_GEN_RES_QUESTION")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperianQuestionToCustomer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8160320220556994132L;

	
	@Id
	@OneToOne
	@JoinColumn(name="eid",nullable=false)
	@JsonIgnore
	private ExperianGenResPayloadResult experianGenResPayloadResult;
	
	

	public ExperianGenResPayloadResult getExperianGenResPayloadResult() {
		return experianGenResPayloadResult;
	}
	public void setExperianGenResPayloadResult(ExperianGenResPayloadResult experianGenResPayloadResult) {
		this.experianGenResPayloadResult = experianGenResPayloadResult;
	}
	private String bureauEmails;
	private String f1Dt;
	private String f2Dt;
	private String[] optionsSet1;
	private String[] optionsSet2;
	private String qid;
	private String question;
	private String toolTip;
	private String type;
	private String secondXMLResponse;
	private String crqPassed;
    
	
	
	public String getBureauEmails() {
		return bureauEmails;
	}
	public void setBureauEmails(String bureauEmails) {
		this.bureauEmails = bureauEmails;
	}
	public String getF1Dt() {
		return f1Dt;
	}
	public void setF1Dt(String f1Dt) {
		this.f1Dt = f1Dt;
	}
	public String getF2Dt() {
		return f2Dt;
	}
	public void setF2Dt(String f2Dt) {
		this.f2Dt = f2Dt;
	}
	public String[] getOptionsSet1() {
		return optionsSet1;
	}
	public void setOptionsSet1(String[] optionsSet1) {
		this.optionsSet1 = optionsSet1;
	}
	public String[] getOptionsSet2() {
		return optionsSet2;
	}
	public void setOptionsSet2(String[] optionsSet2) {
		this.optionsSet2 = optionsSet2;
	}
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getToolTip() {
		return toolTip;
	}
	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSecondXMLResponse() {
		return secondXMLResponse;
	}
	public void setSecondXMLResponse(String secondXMLResponse) {
		this.secondXMLResponse = secondXMLResponse;
	}
	public String getCrqPassed() {
		return crqPassed;
	}
	public void setCrqPassed(String crqPassed) {
		this.crqPassed = crqPassed;
	}
	@Override
	public String toString() {
		return "ExperianQuestionToCustomer [bureauEmails=" + bureauEmails + ", f1Dt=" + f1Dt + ", f2Dt=" + f2Dt
				+ ", optionsSet1=" + Arrays.toString(optionsSet1) + ", optionsSet2=" + Arrays.toString(optionsSet2)
				+ ", qid=" + qid + ", question=" + question + ", toolTip=" + toolTip + ", type=" + type
				+ ", secondXMLResponse=" + secondXMLResponse + ", crqPassed=" + crqPassed + "]";
	}
	
	
}
