package com.qualtech.icici.api.request;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_I_PS_REQ_APP")
public class PolicyStatusParamListMap implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonIgnore
	@GeneratedValue(generator = "REQ_SEQ")
	@SequenceGenerator(name = "REQ_SEQ", sequenceName = "IB_PS_REQ_SEQ")
	private int uniqueID;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="eid",nullable=false)	
	private PolicyStatusPayload policyStatusPayload;
	
	private String appNo;
	private String dob;
	
	public PolicyStatusPayload getPolicyStatusPayload() {
		return policyStatusPayload;
	}
	public void setPolicyStatusPayload(PolicyStatusPayload policyStatusPayload) {
		this.policyStatusPayload = policyStatusPayload;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getUniqueID() {
		return uniqueID;
	}
	public void setUniqueID(int uniqueID) {
		this.uniqueID = uniqueID;
	}
	@Override
	public String toString() {
		return "PolicyStatusParamListMap [policyStatusPayload=" + policyStatusPayload + ", uniqueID=" + uniqueID
				+ ", appNo=" + appNo + ", dob=" + dob + "]";
	}
	
}
