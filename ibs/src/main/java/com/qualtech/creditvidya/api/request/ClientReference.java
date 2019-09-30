package com.qualtech.creditvidya.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_CREDIT_CLT_REF")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientReference implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private String eid;
	private String leadId;
	private String transactionId;
	private String losId;
	
	@JsonIgnore
	private String sid;
	
	

	@Id
	@GeneratedValue(generator="IB_CREDIT_CLT_REQ_SQC")
	@SequenceGenerator(name="IB_CREDIT_CLT_REQ_SQC",sequenceName="IB_CREDIT_CLT_REQ_SQC",allocationSize=1)
	@Column(name="EID")
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
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

	@Column
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}


}
