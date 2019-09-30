package com.qualtech.api.ibs.util;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommonDataInRequest implements Serializable
{

	private String title;
	private String fName;
	private String mName;
	private String lName;
	private String dob;
	private String email;
	private String gender;
	private String keyPersonName;
	private String keyPersonType;
	private String emailAlternate;
	private String maritalStatus;
	private String nomineeName;
	private String nomineeType;
	private String inquiryPurpose;
	private String consumerId;
	private List<PersonId> ids;
	private List<PersonPhone> phones;
	private List<PersonAddressess> addresses;
	private List<PersonRelation> relations;
	
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmailAlternate() {
		return emailAlternate;
	}
	public void setEmailAlternate(String emailAlternate) {
		this.emailAlternate = emailAlternate;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getKeyPersonName() {
		return keyPersonName;
	}
	public void setKeyPersonName(String keyPersonName) {
		this.keyPersonName = keyPersonName;
	}
	public String getKeyPersonType() {
		return keyPersonType;
	}
	public void setKeyPersonType(String keyPersonType) {
		this.keyPersonType = keyPersonType;
	}
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public String getNomineeType() {
		return nomineeType;
	}
	public void setNomineeType(String nomineeType) {
		this.nomineeType = nomineeType;
	}
	public String getInquiryPurpose() {
		return inquiryPurpose;
	}
	public void setInquiryPurpose(String inquiryPurpose) {
		this.inquiryPurpose = inquiryPurpose;
	}
	public List<PersonId> getIds() {
		return ids;
	}
	public void setIds(List<PersonId> ids) {
		this.ids = ids;
	}
	public List<PersonPhone> getPhones() {
		return phones;
	}
	public void setPhones(List<PersonPhone> phones) {
		this.phones = phones;
	}
	public List<PersonAddressess> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<PersonAddressess> addresses) {
		this.addresses = addresses;
	}
	public List<PersonRelation> getRelations() {
		return relations;
	}
	public void setRelations(List<PersonRelation> relations) {
		this.relations = relations;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "CommonDataInRequest [fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", dob=" + dob
				+ ", email=" + email + ", gender=" + gender + ", keyPersonName=" + keyPersonName + ", keyPersonType="
				+ keyPersonType + ", emailAlternate=" + emailAlternate + ", maritalStatus=" + maritalStatus
				+ ", nomineeName=" + nomineeName + ", nomineeType=" + nomineeType + ", inquiryPurpose=" + inquiryPurpose
				+ ", ids=" + ids + ", phones=" + phones + ", addresses=" + addresses + ", relations=" + relations + "]";
	}
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	
	
}
