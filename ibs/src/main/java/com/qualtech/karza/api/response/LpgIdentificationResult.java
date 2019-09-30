package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_K_LPG_IDENTI_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LpgIdentificationResult implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
	@Column(name="UNIQUEID")
	private Long uniqueid;
	@Column(name="ID")
	private String lpg_id;
	@JsonIgnore
	@Column(name="CORELATIONID")
	private String corelationid;
	
	
	
	public Long getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(Long uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	public String getLpg_id() {
		return lpg_id;
	}
	public void setLpg_id(String lpg_id) {
		this.lpg_id = lpg_id;
	}
	@Override
	public String toString() {
		return "LpgIdentificationResult [lpg_id=" + lpg_id + "]";
	}
	
	
}
