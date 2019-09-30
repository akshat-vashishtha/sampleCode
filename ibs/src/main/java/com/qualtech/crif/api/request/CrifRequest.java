package com.qualtech.crif.api.request;

import java.io.Serializable;
import java.util.List;

import com.qualtech.crif.api.dto.Address;
import com.qualtech.crif.api.dto.Phone;
import com.qualtech.crif.api.dto.Relation;
import com.qualtech.crif.api.dto.UniqueId;

public class CrifRequest implements Serializable
{
	private static final long serialVersionUID = 6801923239681725351L;
	private String fName;
	private String mName;
	private String lName;
	private String dob;
	private String keyPersonName;
	private String keyPersonType;
	private String nomineeName;
	private String nomineeType;
	private List<UniqueId> ids;
	private List<Relation> relations;
	private List<Phone> phones;
	private List<Address> addresses;
	
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
	@Override
	public String toString() {
		return "CrifRequest [fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", dob=" + dob
				+ ", keyPersonName=" + keyPersonName + ", keyPersonType=" + keyPersonType + ", nomineeName="
				+ nomineeName + ", nomineeType=" + nomineeType + ", ids=" + ids + ", relations=" + relations
				+ ", phones=" + phones + ", addresses=" + addresses + "]";
	}

}
