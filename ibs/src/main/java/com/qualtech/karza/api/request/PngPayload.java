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
@Table(name="IB_K_PNG_REQ")
public class PngPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
	@Id
	@JsonIgnore
	private String uniqueId;
	@Column
	@JsonIgnore
	 private String corelationId; 
	@Column
	private String consumer_id;
	@Column
	private String bp_no;
	@Column
	private String service_provider;
	@Column
	private String consent;
	public String getConsumer_id() {
		return consumer_id;
	}
	public void setConsumer_id(String consumer_id) {
		this.consumer_id = consumer_id;
	}
	public String getBp_no() {
		return bp_no;
	}
	public void setBp_no(String bp_no) {
		this.bp_no = bp_no;
	}
	public String getService_provider() {
		return service_provider;
	}
	public void setService_provider(String service_provider) {
		this.service_provider = service_provider;
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
	public String getCorelationId() {
		return corelationId;
	}
	public void setCorelationId(String corelationId) {
		this.corelationId = corelationId;
	}
	@Override
	public String toString() {
		return "PngPayload [consumer_id=" + consumer_id + ", bp_no=" + bp_no + ", service_provider=" + service_provider
				+ ", consent=" + consent + "]";
	}
	
}
