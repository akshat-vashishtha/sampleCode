package com.qualtech.karza.api.utils;

public class GSTAuthAddrBean {

	private String email;
	private String address;
	private String address2;
	private String mobile;
	private String ntr;
	private String lastUpdatedDate;
	private String leftBraket;
	private String rightBraket;
	
	public void setLeftBraket(String leftBraket) {
		this.leftBraket = leftBraket;
	}
	public void setRightBraket(String rightBraket) {
		this.rightBraket = rightBraket;
	}
	public String getLeftBraket() {
		return leftBraket;
	}
	public String getRightBraket() {
		return rightBraket;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNtr() {
		return ntr;
	}
	public void setNtr(String ntr) {
		this.ntr = ntr;
	}
	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	@Override
	public String toString() {
		return "GSTAuthAddrBean [email=" + email + ", address=" + address + ", address2=" + address2 + ", mobile="
				+ mobile + ", ntr=" + ntr + ", lastUpdatedDate=" + lastUpdatedDate + ", leftBraket=" + leftBraket
				+ ", rightBraket=" + rightBraket + "]";
	}
	
	
	
	
}
