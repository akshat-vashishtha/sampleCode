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
@Table(name="IB_CBL_IDDETAILS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class IDDetails implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long identityid;
	private String idType="";
	private String idNumber="";
	private String issueDate="";
	private String expirationDate="";
	@JsonIgnore
	private CibilResponsePayload cibilresponsepayload;
	@Column(name="IDTYPE")
	public String getIdType() {
		return idType;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	@Column(name="IDNUMBER")
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	@Column(name="ISSUEDATE")
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	@Column(name="EXPIRATIONDATE")
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "IDDetails [idType=" + idType + ", idNumber=" + idNumber + ", issueDate=" + issueDate
				+ ", expirationDate=" + expirationDate + "]";
	}
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_IDDETAILS_SQC")
    @SequenceGenerator(name = "IB_CBL_IDDETAILS_SQC", sequenceName = "IB_CBL_IDDETAILS_SQC")
	public long getIdentityid() {
		return identityid;
	}
	public void setIdentityid(long identityid) {
		this.identityid = identityid;
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
