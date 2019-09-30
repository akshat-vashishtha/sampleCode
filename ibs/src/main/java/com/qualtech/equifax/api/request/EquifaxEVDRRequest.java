package com.qualtech.equifax.api.request;

import java.io.Serializable;
import java.util.List;

import com.qualtech.equifax.api.common.dto.Address;
import com.qualtech.equifax.api.common.dto.Phone;
import com.qualtech.equifax.api.common.dto.Relation;
import com.qualtech.equifax.api.common.dto.UniqueId;

public class EquifaxEVDRRequest implements Serializable
{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -8846334363190097154L;
	/**
	 * 
	 */
	
    
	String fName;
	String mName;
	String lName;
	String dob;
	String inquiryPurpose;
	String keyPersonName;
	String keyPersonType;
	String nomineeName;
	String nomineeType;
	String amount;
	String email;
	
	List<UniqueId> ids;
	List<Relation> relations;
	List<Phone> phones;
	List<Address> addresses;
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
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
	@Override
	public String toString() {
		return "EquifaxEVDRRequest [fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", dob=" + dob
				+ ", inquiryPurpose=" + inquiryPurpose + ", keyPersonName=" + keyPersonName + ", keyPersonType="
				+ keyPersonType + ", nomineeName=" + nomineeName + ", nomineeType=" + nomineeType + ", amount=" + amount
				+ ", ids=" + ids + ", relations=" + relations + ", phones=" + phones + ", addresses=" + addresses + "]";
	}
	public void setInquiryPurpose(String inquiryPurpose) {
		this.inquiryPurpose = inquiryPurpose;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	  
}
