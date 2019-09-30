package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_K_ShopEst_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopEstablishmentPayload implements Serializable{


	private static final long serialVersionUID = -6914504164196462220L;
	@Column(name="CONSENT")
	private String consent;
	@Column(name="REG_NO")
	private String reg_no;
	@Transient
	private String area_code;
	@Id
	@JsonIgnore
	private String uniqueid;
	@Column
	@JsonIgnore
	private String corelationId;
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getCorelationId() {
		return corelationId;
	}
	public void setCorelationId(String corelationId) {
		this.corelationId = corelationId;
	}
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	public String getReg_no() {
		return reg_no;
	}
	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ShopEstablishmentPayload [consent=" + consent + ", reg_no=" + reg_no + ", area_code=" + area_code + "]";
	}
	

	
}
