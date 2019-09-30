package com.qualtech.equifax.api.entity.common;

import java.util.List;

import com.qualtech.equifax.api.common.dto.Relation;
import com.qualtech.equifax.api.entity.pcs.EquifaxPcsAddressDetails;

public class EquifaxPcsInquiryRequestInfo 
{
	private String inquirypurpose="";
	private String fName="";
	private String familyName="";
	private String dob="";
	private String Gender="";
	private String requestPurpose="";
	private String transactionAmount="";

	private String pan="";
	private String voterID="";
	private String passportId="";
	private String uid="";
	private String driverLicense="";
	private String hPhone="NA";
	private String mPhone="NA";
	private String oPhone="NA";

	private String inquiryAccount1="";
	private String inquiryAccount2="";
	private String inquiryAccount3="";
	private String inquiryAccount4="";
	private List<Relation> relations;
	
	
	public List<Relation> getRelations() {
		return relations;
	}
	
	public void setRelations(List<Relation> relations) {
		this.relations = relations;
	}

	private List<EquifaxPcsAddressDetails> address;

	public String getInquirypurpose() {
		return inquirypurpose;
	}

	public void setInquirypurpose(String inquirypurpose) {
		this.inquirypurpose = inquirypurpose;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getRequestPurpose() {
		return requestPurpose;
	}

	public void setRequestPurpose(String requestPurpose) {
		this.requestPurpose = requestPurpose;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getVoterID() {
		return voterID;
	}

	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}

	public String getPassportId() {
		return passportId;
	}

	public void setPassportId(String passportId) {
		this.passportId = passportId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getDriverLicense() {
		return driverLicense;
	}

	public void setDriverLicense(String driverLicense) {
		this.driverLicense = driverLicense;
	}

	public String gethPhone() {
		return hPhone;
	}

	public void sethPhone(String hPhone) {
		this.hPhone = hPhone;
	}

	public String getmPhone() {
		return mPhone;
	}

	public void setmPhone(String mPhone) {
		this.mPhone = mPhone;
	}

	public String getoPhone() {
		return oPhone;
	}

	public void setoPhone(String oPhone) {
		this.oPhone = oPhone;
	}

	public String getInquiryAccount1() {
		return inquiryAccount1;
	}

	public void setInquiryAccount1(String inquiryAccount1) {
		this.inquiryAccount1 = inquiryAccount1;
	}

	public String getInquiryAccount2() {
		return inquiryAccount2;
	}

	public void setInquiryAccount2(String inquiryAccount2) {
		this.inquiryAccount2 = inquiryAccount2;
	}

	public String getInquiryAccount3() {
		return inquiryAccount3;
	}

	public void setInquiryAccount3(String inquiryAccount3) {
		this.inquiryAccount3 = inquiryAccount3;
	}

	public String getInquiryAccount4() {
		return inquiryAccount4;
	}

	public void setInquiryAccount4(String inquiryAccount4) {
		this.inquiryAccount4 = inquiryAccount4;
	}

	public List<EquifaxPcsAddressDetails> getAddress() {
		return address;
	}

	public void setAddress(List<EquifaxPcsAddressDetails> address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "EquifaxPcsInquiryRequestInfo [inquirypurpose=" + inquirypurpose + ", fName=" + fName
				+ ", familyName=" + familyName + ", dob=" + dob + ", Gender=" + Gender + ", requestPurpose="
						+ requestPurpose + ", transactionAmount=" + transactionAmount + ", pan=" + pan + ", voterID="
						+ voterID + ", passportId=" + passportId + ", uid=" + uid + ", driverLicense=" + driverLicense
						+ ", hPhone=" + hPhone + ", mPhone=" + mPhone + ", oPhone=" + oPhone + ", inquiryAccount1="
						+ inquiryAccount1 + ", inquiryAccount2=" + inquiryAccount2 + ", inquiryAccount3=" + inquiryAccount3
						+ ", inquiryAccount4=" + inquiryAccount4 + ", address=" + address + "]";
	}

}
