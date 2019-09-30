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
@Table(name = "IB_BUREAU_CAPSAPPLT_RES")
public class CapsApplicantDetailsRes implements Serializable {


	private static final long serialVersionUID = 612526037397077639L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAPSAPPLT_SQC", allocationSize = 1)
	@JsonIgnore
	private int capsApplicantId;

	private String voteridexpirationdate;
	private String driverlincencenumber;
	private String driverlincenceexpirationdate;
	private String universalidissuedate;
	private String universalidnumber;
	private String passportnumber;
	private String votersidentitycard;
	private String rationcardnumber;
	private String panissuedate;
	private String mobilephonenumber;
	private String universalidexpirationdate;
	private String middlename1;
	private String passportexpirationdate;
	private String emailid;
	private String middlename3;
	private String middlename2;
	private String voteridissuedate;
	private String driverlincenceissuedate;
	private String dateofbirthapplicant;
	private String lastname;
	private String rationcardexpirationdate;
	private String firstname;
	private String panexpirationdate;
	private String rationcardissuedate;
	private String telephonetype;
	private String incometaxpan;
	private String passportissuedate;
	private String gendercode;
	
	

	@OneToOne
	@JoinColumn(name = "capsDetailId", nullable = false)
	@JsonIgnore
	private CapsApplicationDetailListRes capsApplicationDetailListRes;



	public int getCapsApplicantId() {
		return capsApplicantId;
	}



	public void setCapsApplicantId(int capsApplicantId) {
		this.capsApplicantId = capsApplicantId;
	}



	public String getVoteridexpirationdate() {
		return voteridexpirationdate;
	}



	public void setVoteridexpirationdate(String voteridexpirationdate) {
		this.voteridexpirationdate = voteridexpirationdate;
	}



	public String getDriverlincencenumber() {
		return driverlincencenumber;
	}



	public void setDriverlincencenumber(String driverlincencenumber) {
		this.driverlincencenumber = driverlincencenumber;
	}



	public String getDriverlincenceexpirationdate() {
		return driverlincenceexpirationdate;
	}



	public void setDriverlincenceexpirationdate(String driverlincenceexpirationdate) {
		this.driverlincenceexpirationdate = driverlincenceexpirationdate;
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



	public String getPassportnumber() {
		return passportnumber;
	}



	public void setPassportnumber(String passportnumber) {
		this.passportnumber = passportnumber;
	}



	public String getVotersidentitycard() {
		return votersidentitycard;
	}



	public void setVotersidentitycard(String votersidentitycard) {
		this.votersidentitycard = votersidentitycard;
	}



	public String getRationcardnumber() {
		return rationcardnumber;
	}



	public void setRationcardnumber(String rationcardnumber) {
		this.rationcardnumber = rationcardnumber;
	}



	public String getPanissuedate() {
		return panissuedate;
	}



	public void setPanissuedate(String panissuedate) {
		this.panissuedate = panissuedate;
	}



	public String getMobilephonenumber() {
		return mobilephonenumber;
	}



	public void setMobilephonenumber(String mobilephonenumber) {
		this.mobilephonenumber = mobilephonenumber;
	}



	public String getUniversalidexpirationdate() {
		return universalidexpirationdate;
	}



	public void setUniversalidexpirationdate(String universalidexpirationdate) {
		this.universalidexpirationdate = universalidexpirationdate;
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



	public String getEmailid() {
		return emailid;
	}



	public void setEmailid(String emailid) {
		this.emailid = emailid;
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



	public String getVoteridissuedate() {
		return voteridissuedate;
	}



	public void setVoteridissuedate(String voteridissuedate) {
		this.voteridissuedate = voteridissuedate;
	}



	public String getDriverlincenceissuedate() {
		return driverlincenceissuedate;
	}



	public void setDriverlincenceissuedate(String driverlincenceissuedate) {
		this.driverlincenceissuedate = driverlincenceissuedate;
	}



	public String getDateofbirthapplicant() {
		return dateofbirthapplicant;
	}



	public void setDateofbirthapplicant(String dateofbirthapplicant) {
		this.dateofbirthapplicant = dateofbirthapplicant;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getRationcardexpirationdate() {
		return rationcardexpirationdate;
	}



	public void setRationcardexpirationdate(String rationcardexpirationdate) {
		this.rationcardexpirationdate = rationcardexpirationdate;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
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



	public CapsApplicationDetailListRes getCapsApplicationDetailListRes() {
		return capsApplicationDetailListRes;
	}



	public void setCapsApplicationDetailListRes(CapsApplicationDetailListRes capsApplicationDetailListRes) {
		this.capsApplicationDetailListRes = capsApplicationDetailListRes;
	}



	@Override
	public String toString() {
		return "CapsApplicantDetailsRes [capsApplicantId=" + capsApplicantId + ", voteridexpirationdate="
				+ voteridexpirationdate + ", driverlincencenumber=" + driverlincencenumber
				+ ", driverlincenceexpirationdate=" + driverlincenceexpirationdate + ", universalidissuedate="
				+ universalidissuedate + ", universalidnumber=" + universalidnumber + ", passportnumber="
				+ passportnumber + ", votersidentitycard=" + votersidentitycard + ", rationcardnumber="
				+ rationcardnumber + ", panissuedate=" + panissuedate + ", mobilephonenumber=" + mobilephonenumber
				+ ", universalidexpirationdate=" + universalidexpirationdate + ", middlename1=" + middlename1
				+ ", passportexpirationdate=" + passportexpirationdate + ", emailid=" + emailid + ", middlename3="
				+ middlename3 + ", middlename2=" + middlename2 + ", voteridissuedate=" + voteridissuedate
				+ ", driverlincenceissuedate=" + driverlincenceissuedate + ", dateofbirthapplicant="
				+ dateofbirthapplicant + ", lastname=" + lastname + ", rationcardexpirationdate="
				+ rationcardexpirationdate + ", firstname=" + firstname + ", panexpirationdate=" + panexpirationdate
				+ ", rationcardissuedate=" + rationcardissuedate + ", telephonetype=" + telephonetype
				+ ", incometaxpan=" + incometaxpan + ", passportissuedate=" + passportissuedate + ", gendercode="
				+ gendercode + "]";
	}

	
	
}
