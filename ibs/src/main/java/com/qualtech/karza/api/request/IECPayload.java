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
@Table(name="IB_K_IEC_REQ")
public class IECPayload implements Serializable{


	private static final long serialVersionUID = -6914504164196462220L;
	@Id
	@JsonIgnore
	private String  uniqueId;
	@Column
	@JsonIgnore
	private String corelationId ;
	@Column
	private String consent;
	@Column
	private String iec;
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	public String getIec() {
		return iec;
	}
	public void setIec(String iec) {
		this.iec = iec;
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
	public String getCorelationId() {
		return corelationId;
	}
	public void setCorelationId(String corelationId) {
		this.corelationId = corelationId;
	}
	@Override
	public String toString() {
		return "IECPayload [consent=" + consent + ", iec=" + iec + "]";
	}
	
	
}
