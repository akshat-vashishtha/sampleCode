package com.qualtech.creditvidya.api.response;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_CREDIT_EMAIL_VRFY_CLT_REF")
public class EmailVerifiClientReference {

	@JsonIgnore
	private Long eid;
	
	private String leadId;
	private String transactionId;
	private String losId;
	
	@JsonIgnore
	@Column
	private String sid;
	

	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	@Id
	@GeneratedValue(generator="IB_CREDIT_CLT_REQ_SQC")
	@SequenceGenerator(name="IB_CREDIT_CLT_REQ_SQC",sequenceName="IB_CREDIT_CLT_REQ_SQC",allocationSize=1)
	@Column(name="EID")
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	
	@Column(name="LEAD_ID")
	public String getLeadId() {
		return leadId;
	}
	@Override
	public String toString() {
		return "ClientReference [EID="+eid+", leadId=" + leadId + ", transactionId=" + transactionId + ", losId=" + losId + "]";
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	
	@Column(name="TRANSACTION_ID")
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	@Column(name="LOS_ID")
	public String getLosId() {
		return losId;
	}
	public void setLosId(String losId) {
		this.losId = losId;
	}
	

	
}
