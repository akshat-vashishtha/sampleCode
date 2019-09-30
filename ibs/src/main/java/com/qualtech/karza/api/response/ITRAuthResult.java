package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_ITRAUTH_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRAuthResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;

	private String eid;
	private String corelationid;
	private String status;
	private String validity;
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="VALIDITY")
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Id
	@Column(name="EID")
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_ITRAUTH_SQC",allocationSize=1)
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	@Column(name="CORELATIONID")
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	@Override
	public String toString() {
		return "ITRAuthResult [status=" + status + ", validity=" + validity + "]";
	}

	
	

	
}
