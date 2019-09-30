package com.qualtech.creditvidya.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Entity
@Table(name="IB_EMAIL_SAVE_REQ")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailSaveReqPayload implements Serializable{
	private static final long serialVersionUID = 5015650153386975994L;
	private String eid;
	private String uniqueId;
	private String email;
	private String mobileNumber;
	private String firstName;
	private String middleName;
	private String lastName;
	private ClientReference clientReference;
	private String companyName;
	private String officeAddressLine1;
	private String officeAddressLine2;
	private String officeAddressLine3;
	private String officeCity;
	private String officeState;
	private String officePinCode;
	@JsonIgnore
	private String corelationid;
	
	@Id
	@Column(name="SID")
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	
	
	@Column(name="UNIQUEID")
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="MOBILE")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	@Column(name="F_NAME")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@Column(name="M_NAME")
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	@Column(name="L_NAME")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@Transient
	public ClientReference getClientReference() {
		if(clientReference!=null){
			clientReference.setEid(this.eid);
		}
		return clientReference;
	}
	public void setClientReference(ClientReference clientReference) {
		this.clientReference = clientReference;
	}
	
	@Column(name="COMPANY_NAME")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	@Column(name="OFC_ADD_LINE1")
	public String getOfficeAddressLine1() {
		return officeAddressLine1;
	}
	public void setOfficeAddressLine1(String officeAddressLine1) {
		this.officeAddressLine1 = officeAddressLine1;
	}
	
	@Column(name="OFC_ADD_LINE2")
	public String getOfficeAddressLine2() {
		return officeAddressLine2;
	}
	public void setOfficeAddressLine2(String officeAddressLine2) {
		this.officeAddressLine2 = officeAddressLine2;
	}
	
	@Column(name="OFC_ADD_LINE3")
	public String getOfficeAddressLine3() {
		return officeAddressLine3;
	}
	public void setOfficeAddressLine3(String officeAddressLine3) {
		this.officeAddressLine3 = officeAddressLine3;
	}
	
	@Column(name="OFC_CITY")
	public String getOfficeCity() {
		return officeCity;
	}
	public void setOfficeCity(String officeCity) {
		this.officeCity = officeCity;
	}
	
	@Column(name="OFC_STATE_CODE")
	public String getOfficeState() {
		return officeState;
	}
	public void setOfficeState(String officeState) {
		this.officeState = officeState;
	}
	
	@Column(name="OFC_PINCODE")
	public String getOfficePinCode() {
		return officePinCode;
	}
	public void setOfficePinCode(String officePinCode) {
		this.officePinCode = officePinCode;
	}
	
	
	@Override
	public String toString() {
		return "CreditVidyaValidationPayload [uniqueId=" + uniqueId + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
				+ ", clientReference=" + clientReference + ", companyName=" + companyName + ", officeAddressLine1="
				+ officeAddressLine1 + ", officeAddressLine2=" + officeAddressLine2 + ", officeAddressLine3="
				+ officeAddressLine3 + ", officeCity=" + officeCity + ", officeState=" + officeState
				+ ", officePinCode=" + officePinCode + "]";
	}
	
	@Column
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}

}
