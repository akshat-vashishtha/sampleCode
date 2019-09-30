package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_CURR_APPT_RES")
public class CurrentApplicantRes implements Serializable {

	private static final long serialVersionUID = -487772733524459318L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CURR_APPT_SQC", allocationSize = 1)
	@JsonIgnore
	private int currAppicantId;

	private String votersidentitycard;
	private String universalidissuedate;
	private String universalidnumber;
	private String rationcardnumber;
	private String passportnumber;
	private String universalidexpirationdate;
	private String dateofbirthapplicant;
	private String mobilephonenumber;
	private String emailid;
	private String middlename1;
	private String passportexpirationdate;
	private String telephoneextension;
	private String middlename3;
	private String middlename2;
	private String voteridexpirationdate;
	private String voteridissuedate;
	private String firstname;
	private String lastname;
	private String driverlicenceissuedate;
	private String driverlicenceexpirationdate;
	private String driverlicencenumber;
	private String rationcardexpirationdate;
	private String telephonetype;
	private String incometaxpan;
	private String panissuedate;
	private String telephonenumberapplicant1st;
	private String panexpirationdate;
	private String rationcardissuedate;
	private String passportissuedate;
	private String gendercode;
	
	
	@OneToOne
	@JoinColumn(name = "currAppDetailsId", nullable = false)
	@JsonIgnore
	private CurrentApplicationDetailsRes currAddressRes;


	public int getCurrAppicantId() {
		return currAppicantId;
	}


	public void setCurrAppicantId(int currAppicantId) {
		this.currAppicantId = currAppicantId;
	}


	public String getVotersidentitycard() {
		return votersidentitycard;
	}


	public void setVotersidentitycard(String votersidentitycard) {
		this.votersidentitycard = votersidentitycard;
	}


	public String getUniversalidissuedate() {
		return universalidissuedate;
	}


	public void setUniversalidissuedate(String universalidissuedate) {
		this.universalidissuedate = universalidissuedate;
	}


	public String getUniversalidnumber() {
		return universalidnumber;
	}


	public void setUniversalidnumber(String universalidnumber) {
		this.universalidnumber = universalidnumber;
	}


	public String getRationcardnumber() {
		return rationcardnumber;
	}


	public void setRationcardnumber(String rationcardnumber) {
		this.rationcardnumber = rationcardnumber;
	}


	public String getPassportnumber() {
		return passportnumber;
	}


	public void setPassportnumber(String passportnumber) {
		this.passportnumber = passportnumber;
	}


	public String getUniversalidexpirationdate() {
		return universalidexpirationdate;
	}


	public void setUniversalidexpirationdate(String universalidexpirationdate) {
		this.universalidexpirationdate = universalidexpirationdate;
	}


	public String getDateofbirthapplicant() {
		return dateofbirthapplicant;
	}


	public void setDateofbirthapplicant(String dateofbirthapplicant) {
		this.dateofbirthapplicant = dateofbirthapplicant;
	}


	public String getMobilephonenumber() {
		return mobilephonenumber;
	}


	public void setMobilephonenumber(String mobilephonenumber) {
		this.mobilephonenumber = mobilephonenumber;
	}


	public String getEmailid() {
		return emailid;
	}


	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}


	public String getMiddlename1() {
		return middlename1;
	}


	public void setMiddlename1(String middlename1) {
		this.middlename1 = middlename1;
	}


	public String getPassportexpirationdate() {
		return passportexpirationdate;
	}


	public void setPassportexpirationdate(String passportexpirationdate) {
		this.passportexpirationdate = passportexpirationdate;
	}


	public String getTelephoneextension() {
		return telephoneextension;
	}


	public void setTelephoneextension(String telephoneextension) {
		this.telephoneextension = telephoneextension;
	}


	public String getMiddlename3() {
		return middlename3;
	}


	public void setMiddlename3(String middlename3) {
		this.middlename3 = middlename3;
	}


	public String getMiddlename2() {
		return middlename2;
	}


	public void setMiddlename2(String middlename2) {
		this.middlename2 = middlename2;
	}


	public String getVoteridexpirationdate() {
		return voteridexpirationdate;
	}


	public void setVoteridexpirationdate(String voteridexpirationdate) {
		this.voteridexpirationdate = voteridexpirationdate;
	}


	public String getVoteridissuedate() {
		return voteridissuedate;
	}


	public void setVoteridissuedate(String voteridissuedate) {
		this.voteridissuedate = voteridissuedate;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getDriverlicenceissuedate() {
		return driverlicenceissuedate;
	}


	public void setDriverlicenceissuedate(String driverlicenceissuedate) {
		this.driverlicenceissuedate = driverlicenceissuedate;
	}


	public String getDriverlicenceexpirationdate() {
		return driverlicenceexpirationdate;
	}


	public void setDriverlicenceexpirationdate(String driverlicenceexpirationdate) {
		this.driverlicenceexpirationdate = driverlicenceexpirationdate;
	}


	public String getDriverlicencenumber() {
		return driverlicencenumber;
	}


	public void setDriverlicencenumber(String driverlicencenumber) {
		this.driverlicencenumber = driverlicencenumber;
	}


	public String getRationcardexpirationdate() {
		return rationcardexpirationdate;
	}


	public void setRationcardexpirationdate(String rationcardexpirationdate) {
		this.rationcardexpirationdate = rationcardexpirationdate;
	}


	public String getTelephonetype() {
		return telephonetype;
	}


	public void setTelephonetype(String telephonetype) {
		this.telephonetype = telephonetype;
	}


	public String getIncometaxpan() {
		return incometaxpan;
	}


	public void setIncometaxpan(String incometaxpan) {
		this.incometaxpan = incometaxpan;
	}


	public String getPanissuedate() {
		return panissuedate;
	}


	public void setPanissuedate(String panissuedate) {
		this.panissuedate = panissuedate;
	}


	public String getTelephonenumberapplicant1st() {
		return telephonenumberapplicant1st;
	}


	public void setTelephonenumberapplicant1st(String telephonenumberapplicant1st) {
		this.telephonenumberapplicant1st = telephonenumberapplicant1st;
	}


	public String getPanexpirationdate() {
		return panexpirationdate;
	}


	public void setPanexpirationdate(String panexpirationdate) {
		this.panexpirationdate = panexpirationdate;
	}


	public String getRationcardissuedate() {
		return rationcardissuedate;
	}


	public void setRationcardissuedate(String rationcardissuedate) {
		this.rationcardissuedate = rationcardissuedate;
	}


	public String getPassportissuedate() {
		return passportissuedate;
	}


	public void setPassportissuedate(String passportissuedate) {
		this.passportissuedate = passportissuedate;
	}


	public String getGendercode() {
		return gendercode;
	}


	public void setGendercode(String gendercode) {
		this.gendercode = gendercode;
	}


	public CurrentApplicationDetailsRes getCurrAddressRes() {
		return currAddressRes;
	}


	public void setCurrAddressRes(CurrentApplicationDetailsRes currAddressRes) {
		this.currAddressRes = currAddressRes;
	}


	@Override
	public String toString() {
		return "CurrentApplicantRes [currAppicantId=" + currAppicantId + ", votersidentitycard=" + votersidentitycard
				+ ", universalidissuedate=" + universalidissuedate + ", universalidnumber=" + universalidnumber
				+ ", rationcardnumber=" + rationcardnumber + ", passportnumber=" + passportnumber
				+ ", universalidexpirationdate=" + universalidexpirationdate + ", dateofbirthapplicant="
				+ dateofbirthapplicant + ", mobilephonenumber=" + mobilephonenumber + ", emailid=" + emailid
				+ ", middlename1=" + middlename1 + ", passportexpirationdate=" + passportexpirationdate
				+ ", telephoneextension=" + telephoneextension + ", middlename3=" + middlename3 + ", middlename2="
				+ middlename2 + ", voteridexpirationdate=" + voteridexpirationdate + ", voteridissuedate="
				+ voteridissuedate + ", firstname=" + firstname + ", lastname=" + lastname + ", driverlicenceissuedate="
				+ driverlicenceissuedate + ", driverlicenceexpirationdate=" + driverlicenceexpirationdate
				+ ", driverlicencenumber=" + driverlicencenumber + ", rationcardexpirationdate="
				+ rationcardexpirationdate + ", telephonetype=" + telephonetype + ", incometaxpan=" + incometaxpan
				+ ", panissuedate=" + panissuedate + ", telephonenumberapplicant1st=" + telephonenumberapplicant1st
				+ ", panexpirationdate=" + panexpirationdate + ", rationcardissuedate=" + rationcardissuedate
				+ ", passportissuedate=" + passportissuedate + ", gendercode=" + gendercode + "]";
	}

}
