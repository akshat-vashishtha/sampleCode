package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)


@Entity
@Table(name="IB_K_LLP_CIN_LOOKUP_RES")
public class CompanyLLPCINLookUpResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;

	
	@JsonIgnore
	@Column(name="EID")
	private Long eid;
	@Column(name="COMPANAY_NAME")
	private String companyName;
	@Column(name="COMPANAY_ID")
	private String companyID;
	@JsonIgnore
	@Column(name="CORELATIONID")
	private String corelationid;
	@Id
	@JsonIgnore
	@Column(name="SEQUENCE_ID")
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_LLP_CIN_LOOKUP_SQC") 
	private Long sequenceid;
	
	
	
	
	public Long getSequenceid() {
		return sequenceid;
	}
	public void setSequenceid(Long sequenceid) {
		this.sequenceid = sequenceid;
	}
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}

	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CompanyLLPCINLookUpResult [companyName=" + companyName + ", companyID=" + companyID + "]";
	}
	
	
	
	
	
	
}
