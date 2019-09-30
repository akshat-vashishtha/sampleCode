package com.qualtech.cibil.api.request;

import java.io.Serializable;
import java.util.List;

import com.qualtech.cibil.api.common.dto.Address;
import com.qualtech.cibil.api.common.dto.TelePhone;
import com.qualtech.cibil.api.common.dto.UniqueId;

public class CibilRequest  implements Serializable
{
	private static final long serialVersionUID = -723227378152476338L;
	private String scoreType="00";
	private String firstName;
	private String middleName;
	private String lastName;
	private String dob;
	private String gender;
	private List<UniqueId> uniqueids; 
	private List<TelePhone> telephones; 
	private List<Address> addresses;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public List<UniqueId> getUniqueids() {
		return uniqueids;
	}
	public void setUniqueids(List<UniqueId> uniqueids) {
		this.uniqueids = uniqueids;
	}
	public List<TelePhone> getTelephones() {
		return telephones;
	}
	public void setTelephones(List<TelePhone> telephones) {
		this.telephones = telephones;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public String getScoreType() {
		return scoreType;
	}
	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}
	@Override
	public String toString() {
		return "CibilRequest [scoreType=" + scoreType + ", firstName="
				+ firstName + ", middleName=" + middleName + ", lastName="
				+ lastName + ", dob=" + dob + ", gender=" + gender
				+ ", uniqueids=" + uniqueids + ", telephones=" + telephones
				+ ", addresses=" + addresses + "]";
	}
	
}
