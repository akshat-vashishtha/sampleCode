package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
@Entity
@Table(name="IB_K_UDYOG_RES")
public class UdyogAadharResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
	private String uniqueid;
	private String correlationid;
	private String pin;
	private String dateOFCommencement;
	private String aadhar;
	private String district;
	private String districtIndustryCentre;
	private String nameOfEnterPrise;
	private String numberOfEmp;
	private String state;
	private String ownerName;
	private String majorActivity;
	private String email;
	private String pan;
	private String ifsc;
	private String mobile;
	private String address;
	private String socialCategory;
	private String accountNumber;
	private String entType;
	private String gender;
	private String typeOfOrg;
	private String investment;
	private String nicDigitCode;
	@Column(name="PIN")
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	@Column(name="DATE_OF_COMMENCEMENT")
	public String getDateOFCommencement() {
		return dateOFCommencement;
	}
	public void setDateOFCommencement(String dateOFCommencement) {
		this.dateOFCommencement = dateOFCommencement;
	}
	@Column(name="AADHAR")
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	@Column(name="DISTRICT")
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	@Column(name="DISTRICT_INDUSTRY_CENTER")
	public String getDistrictIndustryCentre() {
		return districtIndustryCentre;
	}
	public void setDistrictIndustryCentre(String districtIndustryCentre) {
		this.districtIndustryCentre = districtIndustryCentre;
	}
	@Column(name="NAME_OF_ENTERPRISE")
	public String getNameOfEnterPrise() {
		return nameOfEnterPrise;
	}
	public void setNameOfEnterPrise(String nameOfEnterPrise) {
		this.nameOfEnterPrise = nameOfEnterPrise;
	}
	@Column(name="NUMBER_OF_EMP")
	public String getNumberOfEmp() {
		return numberOfEmp;
	}
	public void setNumberOfEmp(String numberOfEmp) {
		this.numberOfEmp = numberOfEmp;
	}
	@Column(name="STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name="OWNER_NAME")
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	@Column(name="MAJOR_ACTIVITY")
	public String getMajorActivity() {
		return majorActivity;
	}
	public void setMajorActivity(String majorActivity) {
		this.majorActivity = majorActivity;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="PAN")
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	@Column(name="IFSC")
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	@Column(name="MOBILE")
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="ADDRESS")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="SOCIAL_CATEGORY")
	public String getSocialCategory() {
		return socialCategory;
	}
	public void setSocialCategory(String socialCategory) {
		this.socialCategory = socialCategory;
	}
	@Column(name="ACCOUNT_NUMBER")
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Column(name="ENT_TYPE")
	public String getEntType() {
		return entType;
	}
	public void setEntType(String entType) {
		this.entType = entType;
	}
	@Column(name="GENDER")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="TYPE_OF_ORG")
	public String getTypeOfOrg() {
		return typeOfOrg;
	}
	public void setTypeOfOrg(String typeOfOrg) {
		this.typeOfOrg = typeOfOrg;
	}
	@Column(name="INVESTMENT")
	public String getInvestment() {
		return investment;
	}
	public void setInvestment(String investment) {
		this.investment = investment;
	}
	@Column(name="NIC_DIGIT_CODE")
	public String getNicDigitCode() {
		return nicDigitCode;
	}
	public void setNicDigitCode(String nicDigitCode) {
		this.nicDigitCode = nicDigitCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@JsonIgnore
	@Id
	@Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@Column(name="CORRELATION_ID")
	public String getCorrelationid() {
		return correlationid;
	}
	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}
    @Override
	public String toString() {
		return "UdyogAadharResult [pin=" + pin + ", dateOFCommencement=" + dateOFCommencement + ", aadhar=" + aadhar
				+ ", district=" + district + ", districtIndustryCentre=" + districtIndustryCentre
				+ ", nameOfEnterPrise=" + nameOfEnterPrise + ", numberOfEmp=" + numberOfEmp + ", state=" + state
				+ ", ownerName=" + ownerName + ", majorActivity=" + majorActivity + ", email=" + email + ", pan=" + pan
				+ ", ifsc=" + ifsc + ", mobile=" + mobile + ", address=" + address + ", socialCategory="
				+ socialCategory + ", accountNumber=" + accountNumber + ", entType=" + entType + ", gender=" + gender
				+ ", typeOfOrg=" + typeOfOrg + ", investment=" + investment + ", nicDigitCode=" + nicDigitCode + "]";
	}

}

