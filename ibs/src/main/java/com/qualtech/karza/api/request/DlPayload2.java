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
@Table(name="IB_K_DL2_REQ")
public class DlPayload2 implements Serializable {

	private static final long serialVersionUID = 1967711269513368218L;
	@Id
	@JsonIgnore
	private String uniqueId;
	@Column
	@JsonIgnore
	private String corelationId;
    @Column(name="DO_NO")
	private String dl_no;
	@Column
	private String dob;
    @Column
	private String consent;

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
		return "DlPayload [dl_no=" + dl_no + ", dob=" + dob + ", consent="
				+ consent + "]";
	}

}
