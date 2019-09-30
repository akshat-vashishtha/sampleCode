package com.qualtech.equifax.api.request;

import java.io.Serializable;
import java.util.List;

import com.qualtech.equifax.api.common.dto.Address;
import com.qualtech.equifax.api.common.dto.Phone;
import com.qualtech.equifax.api.common.dto.Relation;
import com.qualtech.equifax.api.common.dto.UniqueId;

public class EquifaxVIDRequest implements Serializable
{
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -482133717205313118L;
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	String fName;
	String mName;
	String lName;
	String dob;
	String requestType;
	String inquiryPurpose;
	String keyPersonName;
	String keyPersonType;
	String nomineeName;
	String nomineeType;
	String email;
	String identification_id;
	List<UniqueId> ids;
	List<Relation> relations;
	List<Phone> phones;
	List<Address> addresses;
	
	
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


	public String getInquiryPurpose() {
		return inquiryPurpose;
	}


	public void setInquiryPurpose(String inquiryPurpose) {
		this.inquiryPurpose = inquiryPurpose;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public List<UniqueId> getIds() {
		return ids;
	}


	public void setIds(List<UniqueId> ids) {
		this.ids = ids;
	}


	public List<Relation> getRelations() {
		return relations;
	}


	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}


	public List<Phone> getPhones() {
		return phones;
	}


	public void setPhones(List<Phone> phones) {
		this.phones = phones;
	}


	public List<Address> getAddresses() {
		return addresses;
	}


	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	public String getIdentification_id() {
		return identification_id;
	}
	
	public void setIdentification_id(String identification_id) {
		this.identification_id = identification_id;
	}
	@Override
	public String toString() {
		return "EquifaxVIDRequest [fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", dob=" + dob
				+ ", requestType=" + requestType + ", inquiryPurpose=" + inquiryPurpose + ", identification_id="
				+ identification_id + ", keyPersonName=" + keyPersonName + ", keyPersonType=" + keyPersonType
				+ ", nomineeName=" + nomineeName + ", nomineeType=" + nomineeType + ", email=" + email + ", ids=" + ids
				+ ", relations=" + relations + ", phones=" + phones + ", addresses=" + addresses + "]";
	}
	
	
}
