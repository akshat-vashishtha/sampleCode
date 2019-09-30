package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Entity
@Table(name="IB_K_KYC_OCR_RES_FRONT_TOP")
@JsonIgnoreProperties(ignoreUnknown=true)
public class KycOcrResFrontTop implements Serializable{
	

     
	 @Column(name="AADHAAR")
	 private String aadhaar;
	 @Column(name="ADDRESS")
	 private String address;
	 @Column(name="NAME")
	 private String name;
	 @Column(name="PHONE")
	 private String phone;
	 @Column(name="PIN")
	 private String pin;
	 @Id
	 @OneToOne
	 @JoinColumn(name="UNIQUEID")
	 @JsonIgnore
	 private KycOcrResResult kycocrresresult;
	 
	 
	
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "KycOcrResFrontTop [aadhaar=" + aadhaar + ", address=" + address + ", name=" + name + ", phone=" + phone
				+ ", pin=" + pin + "]";
	}
	public KycOcrResResult getKycocrresresult() {
		return kycocrresresult;
	}
	public void setKycocrresresult(KycOcrResResult kycocrresresult) {
		this.kycocrresresult = kycocrresresult;
	}
	 
}
