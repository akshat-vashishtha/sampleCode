package com.qualtech.equifax.api.request;

import java.io.Serializable;
import java.util.List;

import com.qualtech.equifax.api.common.dto.Address;
import com.qualtech.equifax.api.common.dto.Phone;
import com.qualtech.equifax.api.common.dto.Relation;
import com.qualtech.equifax.api.common.dto.UniqueId;

public class EquifaxRequest  implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6702622356881173443L;
    
	private String consumerId;
	private String fName;
	private String mName;
	private String lName;
	private String dob;
	private String gender;
	private String maritalStatus;
	private String requestType;
	private String inquiryPurpose;
	private String keyPersonName;
	private String keyPersonType;
	private String nomineeName;
	private String nomineeType;
	List<UniqueId> ids;
	List<Relation> relations;
	List<Phone> phones;
	List<Address> addresses;
	
	
	
	
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
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
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	

	public String getInquiryPurpose() {
		return inquiryPurpose;
	}
	
		public void setInquiryPurpose(String inquiryPurpose) {
		this.inquiryPurpose = inquiryPurpose;
	}
		@Override
		public String toString() {
			return "EquifaxRequest [fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", dob=" + dob
					+ ", requestType=" + requestType + ", inquiryPurpose=" + inquiryPurpose + ", keyPersonName="
					+ keyPersonName + ", keyPersonType=" + keyPersonType + ", nomineeName=" + nomineeName
					+ ", nomineeType=" + nomineeType + ", ids=" + ids + ", relations=" + relations + ", phones="
					+ phones + ", addresses=" + addresses + "]";
		}
	
	
}
