package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_K_PNG_RES")
public class PngResult implements Serializable{

	
	 private static final long serialVersionUID = -8251784410816419882L;
	 
	 @Id
	 @JsonIgnore
	 private String uniqueId;
	 @Column
	 @JsonIgnore
	 private String correlation_Id;
	 @Column(name="BILL_NO")	
	 private String billNo;
	 
	 @Column(name="DUE_DATE")
	 private String dueDate;
	 @Column(name="BILL_AMOUNT")
	 private String billAmount;
	 @Column(name="MOBILE")
	 private String mobile;
	 @Column(name="CUSTOMER_ADDRESS")
	 private String customerAddress;
	 @Column(name="BILL_DATE")
	 private String billDate;
	 @Column(name="EMAIL")
	 private String email;
	 @Column(name="CUSTOMER_NAME")
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
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	public String getCorrelation_Id() {
		return correlation_Id;
	}
	public void setCorrelation_Id(String correlation_Id) {
		this.correlation_Id = correlation_Id;
	}
	@Override
	public String toString() {
		return "PngResult [billNo=" + billNo + ", dueDate=" + dueDate + ", billAmount=" + billAmount + ", mobile="
				+ mobile + ", customerAddress=" + customerAddress + ", billDate=" + billDate + ", email=" + email
				+ ", customerName=" + customerName + "]";
	}
	 
	 
}
