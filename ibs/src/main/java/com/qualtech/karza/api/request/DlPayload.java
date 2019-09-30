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
@Table(name = "IB_K_DL_KYC_REQ")
public class DlPayload implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1967711269513368218L;

	@Id
	@JsonIgnore
	private String uniqueid;
	@Column
	@JsonIgnore
	private String corelationid;
	@Column
	private String dl_no;
	@Column
	private String dob;
	@Transient
	private String name;
	@Transient
	private String consent;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DlPayload [dl_no=" + dl_no + ", dob=" + dob + ", name=" + name
				+ ", consent=" + consent + "]";
	}
  

	public String getDl_no() {
		return dl_no;
	}

	public void setDl_no(String dl_no) {
		this.dl_no = dl_no;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
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

}
