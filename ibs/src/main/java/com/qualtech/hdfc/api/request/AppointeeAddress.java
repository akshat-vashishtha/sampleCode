package com.qualtech.hdfc.api.request;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class AppointeeAddress {

	@Column(name="APP_AREA")
	private String area;
	@Column(name="APP_CITY")
    private String city;
	@Column(name="APP_HOUSEORFLAT")
    private String houseOrFlat;
	@Column(name="APP_PINCODE")
    private String pinCode;
	@Column(name="APP_STATE")
    private String state;
	@Column(name="APP_STREET")
    private String street;
	
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getHouseOrFlat() {
		return houseOrFlat;
	}
	public void setHouseOrFlat(String houseOrFlat) {
		this.houseOrFlat = houseOrFlat;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	@Override
	public String toString() {
		return "Address [area=" + area + ", city=" + city + ", houseOrFlat=" + houseOrFlat + ", pinCode=" + pinCode
				+ ", state=" + state + ", street=" + street + "]";
	}
    
    
	
}
