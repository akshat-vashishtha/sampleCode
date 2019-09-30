package com.qualtech.creditvidya.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "IB_EMAIL_VRFY_FORM_INFO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormInfo implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Long eid;
	private String employeeCode;
	private String dateOfJoining;
	private String department;
	private String designation;
	private String currentPosting;
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String pincode;
	private String mobileNumber;
	private String uniqueid;
	
	@JsonIgnore
	private String sid;

	@Column
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	@Id
	@GeneratedValue(generator="IB_CREDIT_VRFY_FORM_SQC")
	@SequenceGenerator(name="IB_CREDIT_VRFY_FORM_SQC",sequenceName="IB_CREDIT_VRFY_FORM_SQC",allocationSize=1)
	@Column(name = "EID")
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	@Transient
	public String getUniqueid() {
		return uniqueid;
	}
	
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@Column(name = "EMP_CODE")
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	@Column(name = "DOJ")
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	@Column(name = "DEPARTMENT")
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	@Column(name = "DESIGNATION")
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Column(name = "CURRENT_POSTING")
	public String getCurrentPosting() {
		return currentPosting;
	}
	public void setCurrentPosting(String currentPosting) {
		this.currentPosting = currentPosting;
	}
	@Column(name = "ADDRESS_LINE1")
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	@Column(name = "ADDRESS_LINE2")
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	@Column(name = "CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name = "STATE")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "PINCODE")
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	@Column(name = "MOBILE")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "FormInfo [employeeCode=" + employeeCode + ", dateOfJoining=" + dateOfJoining + ", department="
				+ department + ", designation=" + designation + ", currentPosting=" + currentPosting + ", addressLine1="
				+ addressLine1 + ", addressLine2=" + addressLine2 + ", city=" + city + ", state=" + state + ", pincode="
				+ pincode + ", mobileNumber=" + mobileNumber + "]";
	}
}
