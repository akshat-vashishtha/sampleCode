package com.qualtech.cibilv2.api.request;

import java.io.Serializable;

public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8770997252610005561L;
	private String addressType;
	private String addressLine;
	private String cityTown;
	private String state;
	private String pinCode;
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getCityTown() {
		return cityTown;
	}
	public void setCityTown(String cityTown) {
		this.cityTown = cityTown;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	@Override
	public String toString() {
		return "Address [addressType=" + addressType + ", addressLine=" + addressLine + ", cityTown=" + cityTown
				+ ", state=" + state + ", pinCode=" + pinCode + "]";
	}
	
	
	
	

	
}
