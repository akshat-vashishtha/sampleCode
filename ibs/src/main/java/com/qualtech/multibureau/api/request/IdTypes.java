package com.qualtech.multibureau.api.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class IdTypes implements Serializable {

	private String pan;
	private String passport;
	private String voter;
	private String drivingLicence;
	private String uid;
	private String rationcard;
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getVoter() {
		return voter;
	}
	public void setVoter(String voter) {
		this.voter = voter;
	}
	public String getDrivingLicence() {
		return drivingLicence;
	}
	public void setDrivingLicence(String drivingLicence) {
		this.drivingLicence = drivingLicence;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getRationcard() {
		return rationcard;
	}
	public void setRationcard(String rationcard) {
		this.rationcard = rationcard;
	}
	@Override
	public String toString() {
		return "IdTypes [pan=" + pan + ", passport=" + passport + ", voter=" + voter + ", drivingLicence="
				+ drivingLicence + ", uid=" + uid + ", rationcard=" + rationcard + "]";
	}
	
	
	
	
}
