package com.qualtech.karza.api.response;

import java.io.Serializable;

public class FaceResult implements Serializable{

	
	 private static final long serialVersionUID = -8251784410816419882L;
	 private String billNo;
	 private String dueDate;
	 private String billAmount;
	 private String mobile;
	 private String customerAddress;
	 private String billDate;
	 private String email;
	 private String customerName;
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getBillAmount() {
		return billAmount;
	}
	public void setBillAmount(String billAmount) {
		this.billAmount = billAmount;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getBillDate() {
		return billDate;
	}
	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "PngResult [billNo=" + billNo + ", dueDate=" + dueDate + ", billAmount=" + billAmount + ", mobile="
				+ mobile + ", customerAddress=" + customerAddress + ", billDate=" + billDate + ", email=" + email
				+ ", customerName=" + customerName + "]";
	}
	 
	 
}
