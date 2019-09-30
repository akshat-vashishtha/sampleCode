package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class ElecElectricity implements Serializable{
	
	@Override
	public String toString() {
		return "ElectricityResult [bill_no=" + bill_no + ", bill_due_date=" + bill_due_date + ", consumer_number="
				+ consumer_number + ", bill_amount=" + bill_amount + ", mobile_number=" + mobile_number
				+ ", bill_issue_date=" + bill_issue_date + ", amount_payable=" + amount_payable + ", total_amount="
				+ total_amount + ", address=" + address + ", consumer_name=" + consumer_name + ", email_address="
				+ email_address + ", bill_date=" + bill_date + "]";
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column
	private String bill_no;
	@Column
	private String bill_due_date;
	@Column(name="CONSUMER_NO")
	private String consumer_number;
	@Column
	private String bill_amount;
	@Column(name="MOBILE")
	private String mobile_number;
	@Column
	private String bill_issue_date;
	@Column
	private String amount_payable;
	@Column
	private String total_amount;
	@Column
	private String address;
	@Column
	private String consumer_name;
	@Column(name="EMAIL")
	private String email_address;
	@Column
	private String bill_date;
	
	
	public String getBill_no() {
		return bill_no;
	}
	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}
	public String getBill_due_date() {
		return bill_due_date;
	}
	public void setBill_due_date(String bill_due_date) {
		this.bill_due_date = bill_due_date;
	}
	public String getConsumer_number() {
		return consumer_number;
	}
	public void setConsumer_number(String consumer_number) {
		this.consumer_number = consumer_number;
	}
	public String getBill_amount() {
		return bill_amount;
	}
	public void setBill_amount(String bill_amount) {
		this.bill_amount = bill_amount;
	}
	public String getMobile_number() {
		return mobile_number;
	}
	public void setMobile_number(String mobile_number) {
		this.mobile_number = mobile_number;
	}
	public String getBill_issue_date() {
		return bill_issue_date;
	}
	public void setBill_issue_date(String bill_issue_date) {
		this.bill_issue_date = bill_issue_date;
	}
	public String getAmount_payable() {
		return amount_payable;
	}
	public void setAmount_payable(String amount_payable) {
		this.amount_payable = amount_payable;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getConsumer_name() {
		return consumer_name;
	}
	public void setConsumer_name(String consumer_name) {
		this.consumer_name = consumer_name;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getBill_date() {
		return bill_date;
	}
	public void setBill_date(String bill_date) {
		this.bill_date = bill_date;
	}
	
	
	

}
