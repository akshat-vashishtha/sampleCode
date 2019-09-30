package com.qualtech.karza.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class MCASignaureData implements Serializable {
	private static final long serialVersionUID = 5767002823262771605L;

	private String date_of_appointment;
	private String designation;
	private String dsc_expiry_date;
	private String address;
	private String DIN_DPIN_PAN;
	private String full_name;
	private String wheather_dsc_registered;
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
	public String getDIN_DPIN_PAN() {
		return DIN_DPIN_PAN;
	}
	public void setDIN_DPIN_PAN(String dIN_DPIN_PAN) {
		DIN_DPIN_PAN = dIN_DPIN_PAN;
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
		return "MCASignaureData [date_of_appointment=" + date_of_appointment + ", designation=" + designation
				+ ", dsc_expiry_date=" + dsc_expiry_date + ", address=" + address + ", DIN_DPIN_PAN=" + DIN_DPIN_PAN
				+ ", full_name=" + full_name + ", wheather_dsc_registered=" + wheather_dsc_registered + "]";
	}
	
	
	
}
