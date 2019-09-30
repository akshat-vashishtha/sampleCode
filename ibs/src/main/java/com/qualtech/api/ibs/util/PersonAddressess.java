package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class PersonAddressess implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3507149475393565566L;
    private String addressline1;
    private String addressline2;
    private String addressline3;
    private String addressline4;
    private String addressline5;
    private String residencecode;
    private String city;
    private String state;
    private String pin;
    private String addresscategory;
    private String type;
    
    private String country;
    private String house;
    private String street;
    private String landmark;
    private String location;
    private String postOffice;
    private String subDist;
    
    
    
    
    
    
    
    
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPostOffice() {
		return postOffice;
	}
	public void setPostOffice(String postOffice) {
		this.postOffice = postOffice;
	}
	public String getSubDist() {
		return subDist;
	}
	public void setSubDist(String subDist) {
		this.subDist = subDist;
	}
	public String getAddressline1() {
		return addressline1;
	}
	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}
	public String getAddressline2() {
		return addressline2;
	}
	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}
	public String getAddressline3() {
		return addressline3;
	}
	public void setAddressline3(String addressline3) {
		this.addressline3 = addressline3;
	}
	public String getAddressline4() {
		return addressline4;
	}
	public void setAddressline4(String addressline4) {
		this.addressline4 = addressline4;
	}
	public String getAddressline5() {
		return addressline5;
	}
	public void setAddressline5(String addressline5) {
		this.addressline5 = addressline5;
	}
	public String getResidencecode() {
		return residencecode;
	}
	public void setResidencecode(String residencecode) {
		this.residencecode = residencecode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getAddresscategory() {
		return addresscategory;
	}
	public void setAddresscategory(String addresscategory) {
		this.addresscategory = addresscategory;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PersonAddressess [addressline1=" + addressline1 + ", addressline2=" + addressline2 + ", addressline3="
				+ addressline3 + ", addressline4=" + addressline4 + ", addressline5=" + addressline5
				+ ", residencecode=" + residencecode + ", city=" + city + ", state=" + state + ", pin=" + pin
				+ ", addresscategory=" + addresscategory + ", type=" + type + "]";
	}
    
    
	

}
