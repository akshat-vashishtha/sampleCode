package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)


@Entity
@Table(name="IB_K_ADDRESS2_RES")
public class AddressData2 implements Serializable{

	private static final long serialVersionUID = -8251784410816419882L;
	
	@Id
    @GeneratedValue(generator="address2")
	@SequenceGenerator(name="address2",sequenceName="IB_K_ADDRESS2_SQC")
    @JsonIgnore
	private int kid;
	
	@Column(name="BUILDING")
	private String building;
	@Column(name="PIN")
	private String pin;
	@Column(name="DISTRIC")
	private String district;
	@Column(name="FLOOR")
	private String floor;
	@Column(name="HOUSE")
	private String house;
	@Column(name="LOCALITY")
	private String locality;
	@Column(name="STATE")
	private String state;
	@Column(name="COMPLEX")
	private String complex;
	@Column(name="UNTAGGED")
	private String untagged;
	@Column(name="CO")
	private String co;
	@Column(name="STREET")
	private String street;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="UNIQUEID", nullable=false)
	private AddressResult addressResult;
	
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getFloor() {
		return floor;
	}
	public void setFloor(String floor) {
		this.floor = floor;
	}
	public String getHouse() {
		return house;
	}
	public void setHouse(String house) {
		this.house = house;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getComplex() {
		return complex;
	}
	public void setComplex(String complex) {
		this.complex = complex;
	}
	public String getUntagged() {
		return untagged;
	}
	public void setUntagged(String untagged) {
		this.untagged = untagged;
	}
	public String getCo() {
		return co;
	}
	public void setCo(String co) {
		this.co = co;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AddressData2 [building=" + building + ", pin=" + pin + ", district=" + district + ", floor=" + floor
				+ ", house=" + house + ", locality=" + locality + ", state=" + state + ", complex=" + complex
				+ ", untagged=" + untagged + ", co=" + co + ", street=" + street + "]";
	}
	public AddressResult getAddressResult() {
		return addressResult;
	}
	public void setAddressResult(AddressResult addressResult) {
		this.addressResult = addressResult;
	}
	
}
