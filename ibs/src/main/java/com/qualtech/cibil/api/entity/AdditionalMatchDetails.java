package com.qualtech.cibil.api.entity;

import java.io.Serializable;
import java.util.List;

public class AdditionalMatchDetails implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String name1="";
	private String name2="";
	private String name3="";
	private String name4="";
	private String name5="";
	private String dateofBirth;
	private String gender;
	private List<TelePhone> telephones;
	private List<AddressDetails> addresses;
	private List<IDDetails>  iddetails;
	private List<EmailContact>  emailcontact;
	private String lenOfTransmission;
	
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getName3() {
		return name3;
	}
	public void setName3(String name3) {
		this.name3 = name3;
	}
	public String getName4() {
		return name4;
	}
	public void setName4(String name4) {
		this.name4 = name4;
	}
	public String getName5() {
		return name5;
	}
	public void setName5(String name5) {
		this.name5 = name5;
	}
	public String getDateofBirth() {
		return dateofBirth;
	}
	public void setDateofBirth(String dateofBirth) {
		this.dateofBirth = dateofBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public List<TelePhone> getTelephones() {
		return telephones;
	}
	public void setTelephones(List<TelePhone> telephones) {
		this.telephones = telephones;
	}
	public List<AddressDetails> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<AddressDetails> addresses) {
		this.addresses = addresses;
	}
	public List<IDDetails> getIddetails() {
		return iddetails;
	}
	public void setIddetails(List<IDDetails> iddetails) {
		this.iddetails = iddetails;
	}
	public List<EmailContact> getEmailcontact() {
		return emailcontact;
	}
	public void setEmailcontact(List<EmailContact> emailcontact) {
		this.emailcontact = emailcontact;
	}
	public String getLenOfTransmission() {
		return lenOfTransmission;
	}
	public void setLenOfTransmission(String lenOfTransmission) {
		this.lenOfTransmission = lenOfTransmission;
	}
	@Override
	public String toString() {
		return "AdditionalMatchDetails [name1=" + name1 + ", name2=" + name2 + ", name3=" + name3 + ", name4=" + name4
				+ ", name5=" + name5 + ", dateofBirth=" + dateofBirth + ", gender=" + gender + ", telephones="
				+ telephones + ", addresses=" + addresses + ", iddetails=" + iddetails + ", emailcontact="
				+ emailcontact + ", lenOfTransmission=" + lenOfTransmission + "]";
	}
}
