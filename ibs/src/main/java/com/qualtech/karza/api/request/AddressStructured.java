package com.qualtech.karza.api.request;

import java.io.Serializable;

public class AddressStructured implements Serializable {

	private static final long serialVersionUID = 9098144247970959807L;
	private String careOf;
	private String house;
	private String street;
	private String landmark;
	private String locality;
	private String vtc;
	private String subdistrict;
	private String district;
	private String state;
	private String pincode;
	private String post_office;
	private String country;
	public String getCareOf() {
		return careOf;
	}
	public void setCareOf(String careOf) {
		this.careOf = careOf;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getVtc() {
		return vtc;
	}
	public void setVtc(String vtc) {
		this.vtc = vtc;
	}
	public String getSubdistrict() {
		return subdistrict;
	}
	public void setSubdistrict(String subdistrict) {
		this.subdistrict = subdistrict;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getPost_office() {
		return post_office;
	}
	public void setPost_office(String post_office) {
		this.post_office = post_office;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AddressStructured [careOf=" + careOf + ", house=" + house + ", street=" + street + ", landmark="
				+ landmark + ", locality=" + locality + ", vtc=" + vtc + ", subdistrict=" + subdistrict + ", district="
				+ district + ", state=" + state + ", pincode=" + pincode + ", post_office=" + post_office + ", country="
				+ country + "]";
	}
	
	
}
