package com.qualtech.equifax.api.response;

public class InquiryRequestInfo {

	String inquiryPurpose;
	private InquiryPhones inquiryPhones;
	String panid;
	String fname;
	private HomePhone homePhone;
	String transactionamount;
	String lname;
	String addLine1;
	private InquiryAdd inquiryAdd;
	String dob;
	String mobilephone;
	String state;
	String postal;
	public String getInquiryPurpose() {
		return inquiryPurpose;
	}
	public void setInquiryPurpose(String inquiryPurpose) {
		this.inquiryPurpose = inquiryPurpose;
	}
	public InquiryPhones getInquiryPhones() {
		return inquiryPhones;
	}
	public void setInquiryPhones(InquiryPhones inquiryPhones) {
		this.inquiryPhones = inquiryPhones;
	}
	public String getPanid() {
		return panid;
	}
	public void setPanid(String panid) {
		this.panid = panid;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public HomePhone getHomePhone() {
		return homePhone;
	}
	public void setHomePhone(HomePhone homePhone) {
		this.homePhone = homePhone;
	}
	public String getTransactionamount() {
		return transactionamount;
	}
	public void setTransactionamount(String transactionamount) {
		this.transactionamount = transactionamount;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddLine1() {
		return addLine1;
	}
	public void setAddLine1(String addLine1) {
		this.addLine1 = addLine1;
	}
	public InquiryAdd getInquiryAdd() {
		return inquiryAdd;
	}
	public void setInquiryAdd(InquiryAdd inquiryAdd) {
		this.inquiryAdd = inquiryAdd;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMobilephone() {
		return mobilephone;
	}
	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	@Override
	public String toString() {
		return "InquiryRequestInfo [inquiryPurpose=" + inquiryPurpose + ", inquiryPhones=" + inquiryPhones + ", panid="
				+ panid + ", fname=" + fname + ", homePhone=" + homePhone + ", transactionamount=" + transactionamount
				+ ", lname=" + lname + ", addLine1=" + addLine1 + ", inquiryAdd=" + inquiryAdd + ", dob=" + dob
				+ ", mobilephone=" + mobilephone + ", state=" + state + ", postal=" + postal + "]";
	}
	
	
	
}
