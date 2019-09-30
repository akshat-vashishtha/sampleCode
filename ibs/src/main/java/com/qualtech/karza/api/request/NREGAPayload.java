package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_K_NREGA_REQ")
public class NREGAPayload implements Serializable {

	
	private static final long serialVersionUID = 3981289275049382443L;
     @Column
	 private String jobcardid;
	 @Column
	 private String consent;
	 @Id
	 @JsonIgnore
	 private String uniqueId;
	 @Column
	 @JsonIgnore
	 private String corelationid;
;	public String getJobcardid() {
		return jobcardid;
	}
	public void setJobcardid(String jobcardid) {
		this.jobcardid = jobcardid;
	}
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	 
}
