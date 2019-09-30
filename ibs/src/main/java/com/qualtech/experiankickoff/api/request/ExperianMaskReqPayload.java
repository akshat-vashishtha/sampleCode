package com.qualtech.experiankickoff.api.request;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_EX2_MASK_REQUEST_PAYLOAD")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperianMaskReqPayload {

	@JsonIgnore
	@Id
	private String eid;
	private String stgOneHitId;
	private String stgTwoHitId;
	private String clientName;
	
	
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
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
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	@Override
	public String toString() {
		return "ExperianMaskRequestPayload [stgOneHitId=" + stgOneHitId + ", stgTwoHitId=" + stgTwoHitId +", clientName="+clientName+ "]";
	}
	public String asFormData() {
		return "stgOneHitId="+stgOneHitId+"&stgTwoHitId="+stgTwoHitId+"&clientName="+clientName;
	}


}
