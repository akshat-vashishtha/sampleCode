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
@Table(name="IB_K_TELE2_REQ")
public class TelephonePayload2 implements Serializable{

	
	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
	private String   uniqueId;	
	@Column
	@JsonIgnore
	private String	 corelationId ;
	@Column
	private String tel_no;
	@Column
	private String consent;
	
	public String getTel_no() {
		return tel_no;
	}
	public void setTel_no(String tel_no) {
		this.tel_no = tel_no;
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
	@Override
	public String toString() {
		return "TelephonePayload [tel_no=" + tel_no + ", consent=" + consent + "]";
	}
	
}
