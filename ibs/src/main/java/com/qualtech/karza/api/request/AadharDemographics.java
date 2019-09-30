package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class AadharDemographics implements Serializable {

	private static final long serialVersionUID = 2359173498682578014L;
	@Column
	private String phone;
	@Column
	private String email;
	@Column
	private String gender;//male/female/transgender
	@Column(name="ADDRESS_FORMAT")
	private String addressFormat;
	@Embedded
	private AadharDob dob;
	@Embedded
	private AadharName name;
	@Embedded
	private AadharAddressFreetext addressFreetext;

	
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getAddressFormat() {
		return addressFormat;
	}
	public AadharDob getDob() {
		return dob;
	}
	public void setDob(AadharDob dob) {
		this.dob = dob;
	}
	public AadharName getName() {
		return name;
	}
	public void setName(AadharName name) {
		this.name = name;
	}
	public AadharAddressFreetext getAddressFreetext() {
		return addressFreetext;
	}
	public void setAddressFreetext(AadharAddressFreetext addressFreetext) {
		this.addressFreetext = addressFreetext;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setAddressFormat(String addressFormat) {
		this.addressFormat = addressFormat;
	}
	@Override
	public String toString() {
		return "AadharDemographics [phone=" + phone + ", email=" + email + ", gender=" + gender + ", addressFormat="
				+ addressFormat + ", dob=" + dob + ", name=" + name + ", addressFreetext=" + addressFreetext + "]";
	}



	
}
