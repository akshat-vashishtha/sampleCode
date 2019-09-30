package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_CAMEMBERSHIPAUTH_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CAMemberShipAuthResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;

	private String uniqueid;
	private String correlationid;
	private String associateYear;
	private String copStatus;
	private String name;
	private String gender;
	private String fellowYear;
	private String qualification;
	private String address;
	@Column(name="ASSOCIATEYEAR")
	public String getAssociateYear() {
		return associateYear;
	}
	public void setAssociateYear(String associateYear) {
		this.associateYear = associateYear;
	}
	@Column(name="COPSTATUS")
	public String getCopStatus() {
		return copStatus;
	}
	public void setCopStatus(String copStatus) {
		this.copStatus = copStatus;
	}
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="GENDER")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="FELLOWYEAR")
	public String getFellowYear() {
		return fellowYear;
	}
	public void setFellowYear(String fellowYear) {
		this.fellowYear = fellowYear;
	}
	@Column(name="QUALIFICATION")
	public String getQualification() {
		return qualification;
	}
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CAMemberShipAuthResult [associateYear=" + associateYear + ", copStatus=" + copStatus + ", name=" + name
				+ ", gender=" + gender + ", fellowYear=" + fellowYear + ", qualification=" + qualification
				+ ", address=" + address + "]";
	}
	@JsonIgnore
	@Id
	@Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@JsonIgnore
	@Column(name="CORRELATION_ID")
	public String getCorrelationid() {
		return correlationid;
	}
	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}
	
	
	
}
