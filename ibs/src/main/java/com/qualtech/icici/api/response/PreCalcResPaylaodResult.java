package com.qualtech.icici.api.response;

import java.io.Serializable;

public class PreCalcResPaylaodResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6523922368890986711L;
	
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private String maritalStatus;
	private String staff;
	private String ageProof;
	private String ipaddress;
	private String branchCode;
	private String sourceCode;
	private String illustration;
	private PremiumSummary premiumsummary;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getStaff() {
		return staff;
	}
	public void setStaff(String staff) {
		this.staff = staff;
	}
	public String getAgeProof() {
		return ageProof;
	}
	public void setAgeProof(String ageProof) {
		this.ageProof = ageProof;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getSourceCode() {
		return sourceCode;
	}
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	public String getIllustration() {
		return illustration;
	}
	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}
	public PremiumSummary getPremiumsummary() {
		return premiumsummary;
	}
	public void setPremiumsummary(PremiumSummary premiumsummary) {
		this.premiumsummary = premiumsummary;
	}
	@Override
	public String toString() {
		return "IciciResponsePayload [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth="
				+ dateOfBirth + ", gender=" + gender + ", maritalStatus=" + maritalStatus + ", staff=" + staff
				+ ", ageProof=" + ageProof + ", ipaddress=" + ipaddress + ", branchCode=" + branchCode + ", sourceCode="
				+ sourceCode + ", illustration=" + illustration + ", premiumsummary=" + premiumsummary + "]";
	}

}
