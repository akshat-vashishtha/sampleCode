package com.qualtech.cibil.api.entity;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DisputeDetails implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String disputeid;
	private String disputeDate;
	
	private List<Remark> disputeRemark;
	
	
	public String getDisputeDate() {
		return disputeDate;
	}
	public void setDisputeDate(String disputeDate) {
		this.disputeDate = disputeDate;
	}
	
	public List<Remark> getDisputeRemark() {
		return disputeRemark;
	}
	public void setDisputeRemark(List<Remark> disputeRemark) {
		this.disputeRemark = disputeRemark;
	}
	@Override
	public String toString() {
		return "DisputeDetails [disputeDate=" + disputeDate
				+ ", disputeRemark=" + disputeRemark + "]";
	}
    
	
	
	public String getDisputeid() {
		return disputeid;
	}
	public void setDisputeid(String disputeid) {
		this.disputeid = disputeid;
	}
}
