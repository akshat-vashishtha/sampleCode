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
@Table(name = "IB_K_ESICIDAuth_REQ")
public class ESICIdPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
    @Id
    @JsonIgnore
	private String uniqueId;
    @Column
    @JsonIgnore
	private String corelationId;
    @Column
	private String esic_id;
    @Column
	private String consent;

	public String getEsic_id() {
		return esic_id;
	}

	public void setEsic_id(String esic_id) {
		this.esic_id = esic_id;
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
		return "ESICIdPayload [esic_id=" + esic_id + ", consent=" + consent
				+ "]";
	}

}
