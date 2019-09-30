package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_EPFAUTHOTP_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EPFAuthOTPResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;

	private String uniqueid;
	private String correlationid;
	private String message;
    @Column(name="MESSAGE")
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EPFAuthOTPResult [message=" + message + "]";
	}
	@JsonIgnore
	@Id
	@Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
    @JsonIgnore
    @Column(name="CORRELATION_ID")
	public String getCorrelationid() {
		return correlationid;
	}


	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}
	
	


	
}
