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
@Table(name="IB_K_MCASIGNATURE_RES")

public class MCASignatureResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;

	
	@Column(name="UNIQUEID")
	private String uniqueid;
	@Column(name="DATE_OF_APPOINTMENT")
	private String date_of_appointment;
	@Column(name="DESIGNATION")
	private String designation;
	@Column(name="DSC_EXPIRY_DATE")
	private String dsc_expiry_date;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="DIN_DPIN_PAN")
	private String din_dpin_pan;
	@Column(name="FULL_NAME")
	private String full_name;
	@Column(name="WHEATHER_DSC_REGISTERED")
	private String wheather_dsc_registered;
	@JsonIgnore
	@Column(name="CORRELATION_ID")
	private String corelationid;
	@JsonIgnore
	@Id
	@Column(name="SEQUENCE_ID")
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_MCASIGNATURE_RES_SQC") 
	private Long sequenceid;
	
	
	public void setSequenceid(Long sequenceid) {
		this.sequenceid = sequenceid;
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

	
	public String getDate_of_appointment() {
		return date_of_appointment;
	}
	public void setDate_of_appointment(String date_of_appointment) {
		this.date_of_appointment = date_of_appointment;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDsc_expiry_date() {
		return dsc_expiry_date;
	}
	public void setDsc_expiry_date(String dsc_expiry_date) {
		this.dsc_expiry_date = dsc_expiry_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDin_dpin_pan() {
		return din_dpin_pan;
	}
	public void setDin_dpin_pan(String din_dpin_pan) {
		this.din_dpin_pan = din_dpin_pan;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getWheather_dsc_registered() {
		return wheather_dsc_registered;
	}
	public void setWheather_dsc_registered(String wheather_dsc_registered) {
		this.wheather_dsc_registered = wheather_dsc_registered;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "MCASignatureResult [date_of_appointment=" + date_of_appointment + ", designation=" + designation
				+ ", dsc_expiry_date=" + dsc_expiry_date + ", address=" + address + ", din_dpin_pan=" + din_dpin_pan
				+ ", full_name=" + full_name + ", wheather_dsc_registered=" + wheather_dsc_registered + "]";
	}

	
	
	
}
