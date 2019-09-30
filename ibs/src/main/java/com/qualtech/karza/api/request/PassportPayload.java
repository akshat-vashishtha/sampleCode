package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_PASSPORT_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PassportPayload implements Serializable {

	private static final long serialVersionUID = 3060615500947785760L;
	 private String uniqueid;
	 private String name;
	 private String consent;
	 private String lastName;
	 private String dob;
	 private String doe;
	 private String gender;
	 private String passportNumber;
	 private String type;
	 private String country;
	 private String corelationid;
	 @Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="CONSENT")
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	@Column(name="LASTNAME")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Column(name="DOB")
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}@Column(name="DOE")
	public String getDoe() {
		return doe;
	}
	public void setDoe(String doe) {
		this.doe = doe;
	}
	@Column(name="GENDER")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="PASSPORT_NUMBER")
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	@Column(name="TYPE")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="COUNTRY")
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@JsonIgnore
	@Id
	@Column(name="UNIQUEID")
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_PASSPORT_REQ_SQC",allocationSize=1)
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	 
	 
	@Override
	public String toString() {
		return "PassportPayload [name=" + name + ", consent=" + consent + ", lastName=" + lastName + ", dob=" + dob
				+ ", doe=" + doe + ", gender=" + gender + ", passportNumber=" + passportNumber + ", type=" + type
				+ ", country=" + country + "]";
	}
	@Column(name="CORELATIONID")
	@JsonIgnore
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}

}
