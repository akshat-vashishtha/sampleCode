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
@Table(name = "IB_K_LPG2_REQ")
public class LpgPayload2 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    @Id
    @JsonIgnore
	private String uniqueid;
    @Column
    @JsonIgnore
	private String corelationid;
    @Column
	private String lpg_id;
    @Column
	private String consent;

	public String getLpg_id() {
		return lpg_id;
	}

	public void setLpg_id(String lpg_id) {
		this.lpg_id = lpg_id;
	}

	public String getConsent() {
		return consent;
	}

	public void setConsent(String consent) {
		this.consent = consent;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getCorelationid() {
		return corelationid;
	}

	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}

	@Override
	public String toString() {
		return "LpgPayload [lpg_id=" + lpg_id + ", consent=" + consent + "]";
	}

}
