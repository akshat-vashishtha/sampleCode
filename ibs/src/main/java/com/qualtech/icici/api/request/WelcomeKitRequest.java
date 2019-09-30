
package com.qualtech.icici.api.request;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_IC_WLCM_KT_REQ")
public class WelcomeKitRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@Id
	private int eid;
	private String policyNo;
	private String templateId;
	private String agentCode;

	 // Getter Methods 

	 public String getPolicyNo() {
	  return policyNo;
	 }

	 public String getTemplateId() {
	  return templateId;
	 }

	 public String getAgentCode() {
	  return agentCode;
	 }

	 // Setter Methods 

	 public void setPolicyNo(String policyNo) {
	  this.policyNo = policyNo;
	 }

	 public void setTemplateId(String templateId) {
	  this.templateId = templateId;
	 }

	 public void setAgentCode(String agentCode) {
	  this.agentCode = agentCode;
	 }
	 
	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	@Override
	public String toString() {
		return "WelcomeKitRequest [policyNo=" + policyNo + ", templateId=" + templateId + ", agentCode=" + agentCode
				+ "]";
	}

}
