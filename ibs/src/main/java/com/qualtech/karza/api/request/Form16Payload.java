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
@Table(name="IB_K_FORM16AUTH_REQ")
public class Form16Payload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
	@Id
	@JsonIgnore
	private String   uniqueId;
	@Column
	@JsonIgnore
	private String corelationId;
	@Column
	 private String tan;
	@Column
	 private String pan;
	@Column
	 private String cert_no;
	@Column
	private String amount;
	@Column
	private String fiscal_year;
	@Column
	private String consent;
	public String getTan() {
		return tan;
	}
	public void setTan(String tan) {
		this.tan = tan;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getCert_no() {
		return cert_no;
	}
	public void setCert_no(String cert_no) {
		this.cert_no = cert_no;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFiscal_year() {
		return fiscal_year;
	}
	public void setFiscal_year(String fiscal_year) {
		this.fiscal_year = fiscal_year;
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
		return "Form16Payload [tan=" + tan + ", pan=" + pan + ", cert_no=" + cert_no + ", amount=" + amount
				+ ", fiscal_year=" + fiscal_year + "]";
	}
}
