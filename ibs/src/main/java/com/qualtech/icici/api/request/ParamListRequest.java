
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
@Table(name="IB_IC_PLC_STS_PRMLST") 
public class ParamListRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
	@GeneratedValue(generator = "REQ_SEQ")
	@SequenceGenerator(name = "REQ_SEQ", sequenceName = "IB_IC_PLSTS_SEQ")
	private int uniqueID;
	private String appNo;
	private String dob;
	
	@OneToOne
    @JsonIgnore
    @JoinColumn(name="eid",nullable=false)	
	PolicyStatusRequest policyStatusRequest;
	
	public PolicyStatusRequest getPolicyStatusRequest() {
		return policyStatusRequest;
	}
	public void setPolicyStatusRequest(PolicyStatusRequest policyStatusRequest) {
		this.policyStatusRequest = policyStatusRequest;
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
		return "ParamListRequest [appNo=" + appNo + ", dob=" + dob + "]";
	}
}
