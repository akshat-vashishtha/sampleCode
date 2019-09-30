package com.qualtech.experiankickoff.api.request;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_EX2_REQUEST_PAYLOAD")
public class ExperianKickOffPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
	
	 @JsonIgnore
	 @Id
	 private String eid;
	 private String clientName;
	 private String allowInput;
	 private String allowEdit;
	 private String allowCaptcha;
	 private String allowConsent;
	 private String allowConsent_additional;
	 private String allowEmailVerify;
	 private String allowVoucher;
	 private String voucherCode;
	 private String noValidationByPass;
	 private String emailConditionalByPass;
	 private String firstName;
	 private String middleName;
	 private String surName;
	 private String dateOfBirth;
	 private String gender;
	 private String mobileNo;
	 private String telephoneNo;
	 private String telephoneType;
	 private String email;
	 private String flatno;
	 private String buildingName;
	 private String roadName;
	 private String city;
	 private String state;
	 private String pincode;
	 private String pan;
	 private String passport;
	 private String aadhaar;
	 private String voterid;
	 private String driverlicense;
	 private String rationcard;
	 private String reason;
	 
	
	 
	 public String getClientName() {
		return clientName;
	}


	public void setClientName(String clientName) {
		this.clientName = clientName;
	}


	public String getAllowInput() {
		return allowInput;
	}


	public void setAllowInput(String allowInput) {
		this.allowInput = allowInput;
	}


	public String getAllowEdit() {
		return allowEdit;
	}


	public void setAllowEdit(String allowEdit) {
		this.allowEdit = allowEdit;
	}


	public String getAllowCaptcha() {
		return allowCaptcha;
	}


	public void setAllowCaptcha(String allowCaptcha) {
		this.allowCaptcha = allowCaptcha;
	}


	public String getAllowConsent() {
		return allowConsent;
	}


	public void setAllowConsent(String allowConsent) {
		this.allowConsent = allowConsent;
	}


	public String getAllowConsent_additional() {
		return allowConsent_additional;
	}


	public void setAllowConsent_additional(String allowConsent_additional) {
		this.allowConsent_additional = allowConsent_additional;
	}


	public String getAllowEmailVerify() {
		return allowEmailVerify;
	}


	public void setAllowEmailVerify(String allowEmailVerify) {
		this.allowEmailVerify = allowEmailVerify;
	}


	public String getAllowVoucher() {
		return allowVoucher;
	}


	public void setAllowVoucher(String allowVoucher) {
		this.allowVoucher = allowVoucher;
	}


	public String getVoucherCode() {
		return voucherCode;
	}


	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}


	public String getNoValidationByPass() {
		return noValidationByPass;
	}


	public void setNoValidationByPass(String noValidationByPass) {
		this.noValidationByPass = noValidationByPass;
	}


	public String getEmailConditionalByPass() {
		return emailConditionalByPass;
	}


	public void setEmailConditionalByPass(String emailConditionalByPass) {
		this.emailConditionalByPass = emailConditionalByPass;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getSurName() {
		return surName;
	}


	public void setSurName(String surName) {
		this.surName = surName;
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


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}


	public String getTelephoneNo() {
		return telephoneNo;
	}


	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}


	public String getTelephoneType() {
		return telephoneType;
	}


	public void setTelephoneType(String telephoneType) {
		this.telephoneType = telephoneType;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getFlatno() {
		return flatno;
	}


	public void setFlatno(String flatno) {
		this.flatno = flatno;
	}


	public String getBuildingName() {
		return buildingName;
	}


	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}


	public String getRoadName() {
		return roadName;
	}


	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public String getPan() {
		return pan;
	}


	public void setPan(String pan) {
		this.pan = pan;
	}


	public String getPassport() {
		return passport;
	}


	public void setPassport(String passport) {
		this.passport = passport;
	}


	public String getAadhaar() {
		return aadhaar;
	}


	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}


	public String getVoterid() {
		return voterid;
	}


	public void setVoterid(String voterid) {
		this.voterid = voterid;
	}


	public String getDriverlicense() {
		return driverlicense;
	}


	public void setDriverlicense(String driverlicense) {
		this.driverlicense = driverlicense;
	}


	public String getRationcard() {
		return rationcard;
	}


	public void setRationcard(String rationcard) {
		this.rationcard = rationcard;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	@Override
	public String toString() {
		
		return "ExperianPayload  [ ClientName="+clientName+",AllowInput="+allowInput+",AllowEdit="+allowEdit+",AllowCaptcha="+allowCaptcha+",AllowConsent="+allowConsent+",AllowConsent_additional="+allowConsent_additional+",AllowEmailVerify="+allowEmailVerify+",AllowVoucher="+allowVoucher+",VoucherCode="+voucherCode+",NoValidationByPass="+noValidationByPass+",EmailConditionalByPass="+emailConditionalByPass+",FirstName="+firstName+",MiddleName="+middleName+",SurName="+surName+",DateOfBirth="+dateOfBirth+",Gender="+gender+",MobileNo="+mobileNo+",TelephoneNo="+telephoneNo+",TelephoneType="+telephoneType+",Email="+email+",Flatno="+flatno+",BuildingName="+buildingName+",RoadName="+roadName+",City="+city+",State="+state+",Pincode="+pincode+",Pan="+pan+",Passport="+passport+",Aadhaar="+aadhaar+",Voterid="+voterid+",Driverlicense="+driverlicense+",Rationcard="+rationcard+",Reason="+reason+"]";
		
	}
	
	public String asFormData() {
		return "clientName="+clientName+"&allowInput="+allowInput+"&allowEdit="+allowEdit+"&allowCaptcha="+allowCaptcha+"&allowConsent="+allowConsent+"&allowConsent_additional="+allowConsent_additional+"&allowEmailVerify="+allowEmailVerify+"&allowVoucher="+allowVoucher+"&voucherCode="+voucherCode+"&noValidationByPass="+noValidationByPass+"&emailConditionalByPass="+emailConditionalByPass+"&firstName="+firstName+"&middleName="+middleName+"&surName="+surName+"&dateOfBirth="+dateOfBirth+"&gender="+gender+"&mobileNo="+mobileNo+"&telephoneNo="+telephoneNo+"&telephoneType="+telephoneType+"&email="+email+"&flatno="+flatno+"&buildingName="+buildingName+"&roadName="+roadName+"&city="+city+"&state="+state+"&pincode="+pincode+"&pan="+pan+"&passport="+passport+"&aadhaar="+aadhaar+"&voterid="+voterid+"&driverlicense="+driverlicense+"&rationcard="+rationcard+"&reason="+reason;
	}


	public String getEid() {
		return eid;
	}


	public void setEid(String eid) {
		this.eid = eid;
	}



 
	 
}
