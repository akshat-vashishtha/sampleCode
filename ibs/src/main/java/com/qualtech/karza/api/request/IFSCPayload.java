package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_IFSC_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IFSCPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
     private String uniqueid;
	 private String ifsc;
	 private String correlationid;
     @Column(name="IFSC") 
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
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
	@Override
	public String toString() {
		return "IFSCPayload [ifsc=" + ifsc + "]";
	}
	

	 
}
