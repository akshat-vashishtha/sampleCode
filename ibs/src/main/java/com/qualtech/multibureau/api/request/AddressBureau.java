package com.qualtech.multibureau.api.request;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_BUREAU_ADD_REQ")
public class AddressBureau {


	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ADD_SQC", allocationSize = 1)
	private int id;
	
	private String address_type;
	private String address_residence_code;
	private String addressInfo;
	private String address_city;
	private String address_pin;
	private String address_state;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="UNIQUEID", nullable=false)
	private BureauPayload bureauPayload;
	
	public BureauPayload getBureauPayload() {
		return bureauPayload;
	}
	public void setBureauPayload(BureauPayload bureauPayload) {
		this.bureauPayload = bureauPayload;
	}
	public String getAddress_type() {
		return address_type;
	}
	public void setAddress_type(String address_type) {
		this.address_type = address_type;
	}
	public String getAddress_residence_code() {
		return address_residence_code;
	}
	public void setAddress_residence_code(String address_residence_code) {
		this.address_residence_code = address_residence_code;
	}
	public String getAddress_city() {
		return address_city;
	}
	public void setAddress_city(String address_city) {
		this.address_city = address_city;
	}
	public String getAddress_pin() {
		return address_pin;
	}
	public void setAddress_pin(String address_pin) {
		this.address_pin = address_pin;
	}
	public String getAddress_state() {
		return address_state;
	}
	public void setAddress_state(String address_state) {
		this.address_state = address_state;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddressInfo() {
		return addressInfo;
	}
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
	@Override
	public String toString() {
		return "AddressBureau [id=" + id + ", address_type=" + address_type + ", address_residence_code="
				+ address_residence_code + ", addressInfo=" + addressInfo + ", address_city=" + address_city
				+ ", address_pin=" + address_pin + ", address_state=" + address_state + "]";
	}
	

}
