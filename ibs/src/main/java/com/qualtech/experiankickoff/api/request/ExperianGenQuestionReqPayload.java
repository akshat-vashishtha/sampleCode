package com.qualtech.experiankickoff.api.request;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_EX2_GEN_REQUEST_PAYLOAD")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperianGenQuestionReqPayload {


	@JsonIgnore
	@Id
	private String eid;
	private String stgOneHitId;
	private String stgTwoHitId;
	private String captchCode	;
	private String singleActionSessionId;
	
	private String answer;
	private String questionId;

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

	public String getCaptchCode() {
		return captchCode;
	}
	public void setCaptchCode(String captchCode) {
		this.captchCode = captchCode;
	}
	public String getSingleActionSessionId() {
		return singleActionSessionId;
	}
	public void setSingleActionSessionId(String singleActionSessionId) {
		this.singleActionSessionId = singleActionSessionId;
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
	
	@Override
	public String toString() {
		return "ExperianGenQuestionReqPayload [stgOneHitId=" + stgOneHitId + ", stgTwoHitId=" + stgTwoHitId
				+ ", captchCode=" + captchCode + ", singleActionSessionId=" + singleActionSessionId + ", answer="
				+ answer + ", questionId=" + questionId + "]";
	}
	public String asFormData() {
		String str="stgOneHitId="+stgOneHitId+"&stgTwoHitId="+stgTwoHitId;
		if(captchCode!=null){
			str+="&captchCode="+captchCode;
		}
		if(singleActionSessionId!=null){
			str+="&singleActionSessionId="+singleActionSessionId;
		}
		if(answer!=null){
			str+="&answer="+answer;
		}
		if(questionId!=null){
			str+="&questionId="+questionId;
		}
		
		return str;
	}

}
