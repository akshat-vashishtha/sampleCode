package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "IB_BUREAU_INQUIRY_REQUEST_RES")
public class InquiryRequestInfoJsonRes implements Serializable {


	private static final long serialVersionUID = -487772733524459318L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_INQUIRY_SQC", allocationSize = 1)
	@JsonIgnore
	private int inquiryId;
	
	private String middlename;
	private String locality2;
	private String lastname;
	private String locality1;
	private String transactionamount;
	private String street;
	private String voterid;
	private String passportid;
	private String postal;
	private String rationcard;
	private String state;
	
	private String panid;
	private String maritalstatus;
	private String additionalid2;
	private String city;
	private String additionalid1;
	private String addrline1;
	

	@OneToOne(fetch=FetchType.LAZY,mappedBy = "inquiryInfoRes" ,cascade=CascadeType.ALL)
	private MobilePhoneRes mobilephone;
	
	private String nationalidcard;
	private String dob;
	private String gender;
	private String inquirypurpose;
	private String fullname;
	private String firstname;
	private String drivinglicense;
	
	
	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;


	public int getInquiryId() {
		return inquiryId;
	}


	public void setInquiryId(int inquiryId) {
		this.inquiryId = inquiryId;
	}


	public String getMiddlename() {
		return middlename;
	}


	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}


	public String getLocality2() {
		return locality2;
	}


	public void setLocality2(String locality2) {
		this.locality2 = locality2;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getLocality1() {
		return locality1;
	}


	public void setLocality1(String locality1) {
		this.locality1 = locality1;
	}


	public String getTransactionamount() {
		return transactionamount;
	}


	public void setTransactionamount(String transactionamount) {
		this.transactionamount = transactionamount;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getVoterid() {
		return voterid;
	}


	public void setVoterid(String voterid) {
		this.voterid = voterid;
	}


	public String getPassportid() {
		return passportid;
	}


	public void setPassportid(String passportid) {
		this.passportid = passportid;
	}


	public String getPostal() {
		return postal;
	}


	public void setPostal(String postal) {
		this.postal = postal;
	}


	public String getRationcard() {
		return rationcard;
	}


	public void setRationcard(String rationcard) {
		this.rationcard = rationcard;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPanid() {
		return panid;
	}


	public void setPanid(String panid) {
		this.panid = panid;
	}


	public String getMaritalstatus() {
		return maritalstatus;
	}


	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}


	public String getAdditionalid2() {
		return additionalid2;
	}


	public void setAdditionalid2(String additionalid2) {
		this.additionalid2 = additionalid2;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getAdditionalid1() {
		return additionalid1;
	}


	public void setAdditionalid1(String additionalid1) {
		this.additionalid1 = additionalid1;
	}


	public String getAddrline1() {
		return addrline1;
	}


	public void setAddrline1(String addrline1) {
		this.addrline1 = addrline1;
	}


	public MobilePhoneRes getMobilephone() {
		if(this.mobilephone!=null)
		{
			this.mobilephone.setInquiryInfoRes(this);
		}
		return mobilephone;
	}


	public void setMobilephone(MobilePhoneRes mobilephone) {
		this.mobilephone = mobilephone;
	}


	public String getNationalidcard() {
		return nationalidcard;
	}


	public void setNationalidcard(String nationalidcard) {
		this.nationalidcard = nationalidcard;
	}


	public String getDob() {
		return dob;
	}


	public void setDob(String dob) {
		this.dob = dob;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getInquirypurpose() {
		return inquirypurpose;
	}


	public void setInquirypurpose(String inquirypurpose) {
		this.inquirypurpose = inquirypurpose;
	}


	public String getFullname() {
		return fullname;
	}


	public void setFullname(String fullname) {
		this.fullname = fullname;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getDrivinglicense() {
		return drivinglicense;
	}


	public void setDrivinglicense(String drivinglicense) {
		this.drivinglicense = drivinglicense;
	}


	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}


	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}


	@Override
	public String toString() {
		return "InquiryRequestInfoJsonRes [inquiryId=" + inquiryId + ", middlename=" + middlename + ", locality2="
				+ locality2 + ", lastname=" + lastname + ", locality1=" + locality1 + ", transactionamount="
				+ transactionamount + ", street=" + street + ", voterid=" + voterid + ", passportid=" + passportid
				+ ", postal=" + postal + ", rationcard=" + rationcard + ", state=" + state + ", panid=" + panid
				+ ", maritalstatus=" + maritalstatus + ", additionalid2=" + additionalid2 + ", city=" + city
				+ ", additionalid1=" + additionalid1 + ", addrline1=" + addrline1 + ", mobilephone=" + mobilephone
				+ ", nationalidcard=" + nationalidcard + ", dob=" + dob + ", gender=" + gender + ", inquirypurpose="
				+ inquirypurpose + ", fullname=" + fullname + ", firstname=" + firstname + ", drivinglicense="
				+ drivinglicense + "]";
	}
	
}
