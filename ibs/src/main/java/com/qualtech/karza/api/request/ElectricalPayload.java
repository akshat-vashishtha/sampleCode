package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_K_ELECTRICAL_KYC_REQ")
public class ElectricalPayload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
	private String uniqueId;
	@Column
	@JsonIgnore
	private String corelationId;
	@Column
	private String consumer_id;
	@Column
	private String service_provider;
	@Transient
	private String consent;
	@Transient
	private String name;
	public String getConsumer_id() {
		return consumer_id;
	}
	
	

	@Override
	public String toString() {
		return "ElectricalPayload [consumer_id=" + consumer_id + ", service_provider=" + service_provider + ", consent="
				+ consent + ", name=" + name + "]";
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setConsumer_id(String consumer_id) {
		this.consumer_id = consumer_id;
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
	
	
	
}
