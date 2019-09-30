package com.qualtech.cibil.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.cibil.api.response.CibilResponsePayload;
@Entity
@Table(name="IB_CBL_TELEPHONE")
@JsonIgnoreProperties(ignoreUnknown=true)
public class TelePhone implements Serializable
{
	private static final long serialVersionUID = 7406273010133888675L;
	private Long TelephoneId;
	private String telephoneNumber="";
	private String telephoneType="";
	private String telephoneExtn="";
	@JsonIgnore
	private CibilResponsePayload cibilresponsepayload;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_TELP_SQC")
	@SequenceGenerator(name = "IB_CBL_TELP_SQC", sequenceName = "IB_CBL_TELP_SQC")
	public Long getTelephoneId() {
		return TelephoneId;
	}
	public void setTelephoneId(Long telephoneId) {
		TelephoneId = telephoneId;
	}
	@Column(name="TELEPHONENUMBER")
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
    @Column(name="TELEPHONETYPE")
	public String getTelephoneType() {
		return telephoneType;
	}
	public void setTelephoneType(String telephoneType) {
		this.telephoneType = telephoneType;
	}
    @Column(name="TELEPHONEEXTN")
	public String getTelephoneExtn() {
		return telephoneExtn;
	}
	public void setTelephoneExtn(String telephoneExtn) {
		this.telephoneExtn = telephoneExtn;
	}
	@Override
	public String toString() {
		return "TelePhone [TelephoneId=" + TelephoneId + ", telephoneNumber=" + telephoneNumber + ", telephoneType="
				+ telephoneType + ", telephoneExtn=" + telephoneExtn + "]";
	}
  
	@ManyToOne
	@JoinColumn(name="CIBIL_UNIQUE_ID")
	public CibilResponsePayload getCibilresponsepayload() {
		return cibilresponsepayload;
	}
	public void setCibilresponsepayload(CibilResponsePayload cibilresponsepayload) {
		this.cibilresponsepayload = cibilresponsepayload;
	}}
