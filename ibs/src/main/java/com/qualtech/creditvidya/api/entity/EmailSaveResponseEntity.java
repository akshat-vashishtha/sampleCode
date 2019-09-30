package com.qualtech.creditvidya.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="IB_EMAIL_SAVE_RES")
public class EmailSaveResponseEntity {

	private String correlationId;
	private String uniqueId;
	private String sid;
	
	@Column(name="UNIQUEID")
	public String getUniqueId() {
		return uniqueId;
	}


	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}


	@Id
	@Column(name="SID")
	public String getSid() {
		return sid;
	}


	public void setSid(String sid) {
		this.sid = sid;
	}

	
	@Column(name="CORELATIONID")
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
}
