package com.qualtech.equifax.api.entity.mcr;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;
@Embeddable
public class EquifaxMCRIdAndContactDetails {
	
	@Column(name="address")
	private String address;
	@Column(name="contact_seq")
	private String seq;
	@Column(name="State")
	private String  state;
	@Column(name="type")
	private String  type;
	@Transient
	private String  reporteddate;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReporteddate() {
		return reporteddate;
	}
	public void setReporteddate(String reporteddate) {
		this.reporteddate = reporteddate;
	}

}
