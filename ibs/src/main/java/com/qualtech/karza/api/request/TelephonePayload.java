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
@Table(name = "IB_K_TELEPHONIC_KYC_REQ")
public class TelephonePayload implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @JsonIgnore
	private String uniqueId;
    @Column
    @JsonIgnore
	private String corelationId;
    @Column
	private String tel_no;
    @Transient
	private String consent;
    @Transient
	private String name;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "TelephonePayload [tel_no=" + tel_no + ", consent=" + consent
				+ ", name=" + name + "]";
	}

	public void setName(String name) {
		this.name = name;
	}

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

}
