package com.qualtech.cibilv2.api.request;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CibilRequestPayload2 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1013229247392297707L;
	
	private String headerTag;
	private String version;
	private String userId;
	private String password;
	private String memberReferenceNumber;
	private String enquiryAmount;
	private String loanPurpose;
	private String reportType;
	private String futureUse;
	private String fName;
	private String mName;
	private String lName;
	//private List<ApplicantName> applicantName;
	private String alternateName;
	private String voterId;
	private String uid;
	private String pan;
	private String rationCard;
	private List<MemberIdTypeDescription> memberIdTypeDescription;
	private List<TelephoneNumberTypeIndicator> telephoneNumberTypeIndicator;
	private String memberBirthDate;
	private String memberGenderType;
	private List<Address> address;
	private String keyPersonName;
	private String keyPersonsRelationship;
	private List <MemberRelationship> memberRelationship;
	private String nomineeName;
	private String nomineeRelationship;
	private String account1;
	private String gstStateCode;
	private String branchReferenceNo;
	private String centreReferenceNo;
	//private String endSegmentTag;
	public String getHeaderTag() {
		return headerTag;
	}
	public void setHeaderTag(String headerTag) {
		this.headerTag = headerTag;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMemberReferenceNumber() {
		return memberReferenceNumber;
	}
	public void setMemberReferenceNumber(String memberReferenceNumber) {
		this.memberReferenceNumber = memberReferenceNumber;
	}
	public String getEnquiryAmount() {
		return enquiryAmount;
	}
	public void setEnquiryAmount(String enquiryAmount) {
		this.enquiryAmount = enquiryAmount;
	}
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getFutureUse() {
		return futureUse;
	}
	public void setFutureUse(String futureUse) {
		this.futureUse = futureUse;
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
	public String getAlternateName() {
		return alternateName;
	}
	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getRationCard() {
		return rationCard;
	}
	public void setRationCard(String rationCard) {
		this.rationCard = rationCard;
	}
	public List<MemberIdTypeDescription> getMemberIdTypeDescription() {
		return memberIdTypeDescription;
	}
	public void setMemberIdTypeDescription(List<MemberIdTypeDescription> memberIdTypeDescription) {
		this.memberIdTypeDescription = memberIdTypeDescription;
	}
	public List<TelephoneNumberTypeIndicator> getTelephoneNumberTypeIndicator() {
		return telephoneNumberTypeIndicator;
	}
	public void setTelephoneNumberTypeIndicator(List<TelephoneNumberTypeIndicator> telephoneNumberTypeIndicator) {
		this.telephoneNumberTypeIndicator = telephoneNumberTypeIndicator;
	}
	public String getMemberBirthDate() {
		return memberBirthDate;
	}
	public void setMemberBirthDate(String memberBirthDate) {
		this.memberBirthDate = memberBirthDate;
	}
	public String getMemberGenderType() {
		return memberGenderType;
	}
	public void setMemberGenderType(String memberGenderType) {
		this.memberGenderType = memberGenderType;
	}
	public List<Address> getAddress() {
		return address;
	}
	public void setAddress(List<Address> address) {
		this.address = address;
	}
	public String getKeyPersonName() {
		return keyPersonName;
	}
	public void setKeyPersonName(String keyPersonName) {
		this.keyPersonName = keyPersonName;
	}
	public String getKeyPersonsRelationship() {
		return keyPersonsRelationship;
	}
	public void setKeyPersonsRelationship(String keyPersonsRelationship) {
		this.keyPersonsRelationship = keyPersonsRelationship;
	}
	public List<MemberRelationship> getMemberRelationship() {
		return memberRelationship;
	}
	public void setMemberRelationship(List<MemberRelationship> memberRelationship) {
		this.memberRelationship = memberRelationship;
	}
	public String getNomineeName() {
		return nomineeName;
	}
	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}
	public String getNomineeRelationship() {
		return nomineeRelationship;
	}
	public void setNomineeRelationship(String nomineeRelationship) {
		this.nomineeRelationship = nomineeRelationship;
	}
	public String getAccount1() {
		return account1;
	}
	public void setAccount1(String account1) {
		this.account1 = account1;
	}
	public String getGstStateCode() {
		return gstStateCode;
	}
	public void setGstStateCode(String gstStateCode) {
		this.gstStateCode = gstStateCode;
	}
	public String getBranchReferenceNo() {
		return branchReferenceNo;
	}
	public void setBranchReferenceNo(String branchReferenceNo) {
		this.branchReferenceNo = branchReferenceNo;
	}
	public String getCentreReferenceNo() {
		return centreReferenceNo;
	}
	public void setCentreReferenceNo(String centreReferenceNo) {
		this.centreReferenceNo = centreReferenceNo;
	}
	@Override
	public String toString() {
		return "CibilRequestPayload2 [headerTag=" + headerTag + ", version=" + version + ", userId=" + userId
				+ ", password=" + password + ", memberReferenceNumber=" + memberReferenceNumber + ", enquiryAmount="
				+ enquiryAmount + ", loanPurpose=" + loanPurpose + ", reportType=" + reportType + ", futureUse="
				+ futureUse + ", fName=" + fName + ", mName=" + mName + ", lName=" + lName + ", alternateName="
				+ alternateName + ", voterId=" + voterId + ", uid=" + uid + ", pan=" + pan + ", rationCard="
				+ rationCard + ", memberIdTypeDescription=" + memberIdTypeDescription
				+ ", telephoneNumberTypeIndicator=" + telephoneNumberTypeIndicator + ", memberBirthDate="
				+ memberBirthDate + ", memberGenderType=" + memberGenderType + ", address=" + address
				+ ", keyPersonName=" + keyPersonName + ", keyPersonsRelationship=" + keyPersonsRelationship
				+ ", memberRelationship=" + memberRelationship + ", nomineeName=" + nomineeName
				+ ", nomineeRelationship=" + nomineeRelationship + ", account1=" + account1 + ", gstStateCode="
				+ gstStateCode + ", branchReferenceNo=" + branchReferenceNo + ", centreReferenceNo=" + centreReferenceNo
				+ "]";
	}
	
	
}
