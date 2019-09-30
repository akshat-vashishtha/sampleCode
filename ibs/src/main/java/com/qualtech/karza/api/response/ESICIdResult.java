package com.qualtech.karza.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_K_ESICI_KYC_RES")
public class ESICIdResult implements Serializable {

	private static final long serialVersionUID = -8251784410816419882L;
	@Id
	@JsonIgnore
	private String unique_Id;
	@Column
	@JsonIgnore
	private String correlation_Id;
	@Column(name = "UHID")
	private String uhId;
	@Column(name = "DOB")
	private String dob;
	@Column(name = "ADHAAR_STATUS")
	private String adhaarStatus;
	@Column(name = "RELATION_IP")
	private String relationWithIP;
	@Column(name = "CURR_DATE_OF_APPOIN")
	private String currentDateOfAppointment;
	@Column(name = "NOMINEE_ADDRESS")
	private String nomineeAddress;
	@Column(name = "PRESENT_ADDRESS")
	private String presentAddress;
	@Column(name = "DISABILITY_TYPE")
	private String disabilityType;
	@Column(name = "ADHAAR_NO")
	private String adhaarNo;
	@Column(name = "PHONE_NO")
	private String phoneNo;
	@Column(name = "MARITIAL_STATUS")
	private String maritialStatus;
	@Column(name = "NAME")
	private String name;
	@Column(name = "GENDER")
	private String gender;
	
	@OrderColumn(name="unique_Id")
	@OneToMany(fetch=FetchType.LAZY, mappedBy="esicIdResult", cascade=CascadeType.ALL)
	private List<ESICIdResultContributionDetails> contributionDetails;
	@Column(name = "NOMINEEADHAARNO")
	private String nomineeAdhaarNo;
	@Column(name = "FATHERORHUSBAND")
	private String fatherOrHusband;
	@Column(name = "REGISTRATIONDATE")
	private String registrationDate;
	@Column(name = "PERMANENTADDRESS")
	private String permanentAddress;
	@Column(name = "NOMINEENAME")
	private String nomineeName;
	@Column(name = "INSURANCENO")
	private String insuranceNo;
	@Column(name = "FIRSTDATEOFAPPOINTMENT")
	private String firstDateOfAppointment;
	@Column(name = "DISPENSARYNAME")
	private String dispensaryName;

	public String getUhId() {
		return uhId;
	}

	public void setUhId(String uhId) {
		this.uhId = uhId;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getAdhaarStatus() {
		return adhaarStatus;
	}

	public void setAdhaarStatus(String adhaarStatus) {
		this.adhaarStatus = adhaarStatus;
	}

	public String getRelationWithIP() {
		return relationWithIP;
	}

	public void setRelationWithIP(String relationWithIP) {
		this.relationWithIP = relationWithIP;
	}

	public String getCurrentDateOfAppointment() {
		return currentDateOfAppointment;
	}

	public void setCurrentDateOfAppointment(String currentDateOfAppointment) {
		this.currentDateOfAppointment = currentDateOfAppointment;
	}

	public String getNomineeAddress() {
		return nomineeAddress;
	}

	public void setNomineeAddress(String nomineeAddress) {
		this.nomineeAddress = nomineeAddress;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getDisabilityType() {
		return disabilityType;
	}

	public void setDisabilityType(String disabilityType) {
		this.disabilityType = disabilityType;
	}

	public String getAdhaarNo() {
		return adhaarNo;
	}

	public void setAdhaarNo(String adhaarNo) {
		this.adhaarNo = adhaarNo;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getMaritialStatus() {
		return maritialStatus;
	}

	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<ESICIdResultContributionDetails> getContributionDetails() {
		for (ESICIdResultContributionDetails esicIdResultContributionDetails : contributionDetails) {
			esicIdResultContributionDetails.setCorrelation_Id(this.correlation_Id);
			esicIdResultContributionDetails.setEsicIdResult(this);
		}
		return contributionDetails;
	}

	public void setContributionDetails(
			List<ESICIdResultContributionDetails> contributionDetails) {
		this.contributionDetails = contributionDetails;
	}

	public String getNomineeAdhaarNo() {
		return nomineeAdhaarNo;
	}

	public void setNomineeAdhaarNo(String nomineeAdhaarNo) {
		this.nomineeAdhaarNo = nomineeAdhaarNo;
	}

	public String getFatherOrHusband() {
		return fatherOrHusband;
	}

	public void setFatherOrHusband(String fatherOrHusband) {
		this.fatherOrHusband = fatherOrHusband;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getInsuranceNo() {
		return insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}

	public String getFirstDateOfAppointment() {
		return firstDateOfAppointment;
	}

	public void setFirstDateOfAppointment(String firstDateOfAppointment) {
		this.firstDateOfAppointment = firstDateOfAppointment;
	}

	public String getDispensaryName() {
		return dispensaryName;
	}

	public void setDispensaryName(String dispensaryName) {
		this.dispensaryName = dispensaryName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	public String getUnique_Id() {
		return unique_Id;
	}

	public void setUnique_Id(String unique_Id) {
		this.unique_Id = unique_Id;
	}

	public String getCorrelation_Id() {
		return correlation_Id;
	}

	public void setCorrelation_Id(String correlation_Id) {
		this.correlation_Id = correlation_Id;
	}

	@Override
	public String toString() {
		return "ESICIdResult [uhId=" + uhId + ", dob=" + dob
				+ ", adhaarStatus=" + adhaarStatus + ", relationWithIP="
				+ relationWithIP + ", currentDateOfAppointment="
				+ currentDateOfAppointment + ", nomineeAddress="
				+ nomineeAddress + ", presentAddress=" + presentAddress
				+ ", disabilityType=" + disabilityType + ", adhaarNo="
				+ adhaarNo + ", phoneNo=" + phoneNo + ", maritialStatus="
				+ maritialStatus + ", name=" + name + ", gender=" + gender
				+ ", contributionDetails=" + contributionDetails
				+ ", nomineeAdhaarNo=" + nomineeAdhaarNo + ", fatherOrHusband="
				+ fatherOrHusband + ", registrationDate=" + registrationDate
				+ ", permanentAddress=" + permanentAddress + ", nomineeName="
				+ nomineeName + ", insuranceNo=" + insuranceNo
				+ ", firstDateOfAppointment=" + firstDateOfAppointment
				+ ", dispensaryName=" + dispensaryName + "]";
	}

}
