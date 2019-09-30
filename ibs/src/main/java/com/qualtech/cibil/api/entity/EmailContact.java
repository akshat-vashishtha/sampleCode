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
@Table(name="IB_CBL_EMAILCONTACT")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EmailContact implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long uniqueid;
	private String emailId;
	@JsonIgnore
	private CibilResponsePayload cibilresponsepayload;
    @Column(name="EMAIL")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	@Override
	public String toString() {
		return "EmailContact [emailId=" + emailId + "]";
	}
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_EMAILCONTACT_SQC")
    @SequenceGenerator(name = "IB_CBL_EMAILCONTACT_SQC", sequenceName = "IB_CBL_EMAILCONTACT_SQC")
	public long getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(long uniqueid) {
		this.uniqueid = uniqueid;
	}
	@ManyToOne
	@JoinColumn(name="CIBIL_UNIQUE_ID")
	public CibilResponsePayload getCibilresponsepayload() {
		return cibilresponsepayload;
	}

	public void setCibilresponsepayload(CibilResponsePayload cibilresponsepayload) {
		this.cibilresponsepayload = cibilresponsepayload;
	}
	
}
