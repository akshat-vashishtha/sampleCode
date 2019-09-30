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
@Table(name="IB_K_LPG_KYC_RES")
public class LpgResult2 implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
	private String uniqueid;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="DISTRIBUTOR_NAME")
	private String distributorName;
	
	@Column(name="SUBSIDIZEDREFILLCONSUMED")
	private String subsidizedRefillConsumed;
	
	@Column(name="PIN")
	private String pin;
	
	@Column(name="CONSUMER_EMAIL")
	private String consumerEmail;
	
	@Column(name="GIVENUP_SUBSIDY")
	private String givenUpSubsidy;
	
	@Column(name="BANK_NAME")
	private String bankName;
	
	@Column(name="IFSCCODE")
	private String ifscCode;
	
	@Column(name="CITY")
	private String city;
	
	@Column(name="AADHAR_NO")
	private String aadhaarNo;
	
	@Column(name="CONSUMER_CONTACT")
	private String consumerContact;
	
	@Column(name="APX_SUBSIDY_AVAILED")
	private String approximateSubsidyAvailed;
	
	@Column(name="CONSUMER_NAME")
	private String consumerName;
	
	@Column(name="CONSUMER_NO")
	private String consumerNo;
	
	@Column(name="DIST_ADDRESS")
	private String distributorAddress;
	
	@Column(name="DIST_CODE")
	private String distributorCode;
	
	@Column(name="BANK_ACT_NO")
	private String bankAccountNo;
	
	@Column(name="LAST_BOOKING_DATE")
	private String lastBookingDate;
	
	@Column(name="CONSUMER_ADDRESS")
	private String consumerAddress;
	
	@Column(name="TOTAL_REFILL_CONSUMED")
	private String totalRefillConsumed;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDistributorName() {
		return distributorName;
	}
	public void setDistributorName(String distributorName) {
		this.distributorName = distributorName;
	}
	public String getSubsidizedRefillConsumed() {
		return subsidizedRefillConsumed;
	}
	public void setSubsidizedRefillConsumed(String subsidizedRefillConsumed) {
		this.subsidizedRefillConsumed = subsidizedRefillConsumed;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getConsumerEmail() {
		return consumerEmail;
	}
	public void setConsumerEmail(String consumerEmail) {
		this.consumerEmail = consumerEmail;
	}
	public String getGivenUpSubsidy() {
		return givenUpSubsidy;
	}
	public void setGivenUpSubsidy(String givenUpSubsidy) {
		this.givenUpSubsidy = givenUpSubsidy;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getAadhaarNo() {
		return aadhaarNo;
	}
	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}
	public String getConsumerContact() {
		return consumerContact;
	}
	public void setConsumerContact(String consumerContact) {
		this.consumerContact = consumerContact;
	}
	public String getApproximateSubsidyAvailed() {
		return approximateSubsidyAvailed;
	}
	public void setApproximateSubsidyAvailed(String approximateSubsidyAvailed) {
		this.approximateSubsidyAvailed = approximateSubsidyAvailed;
	}
	
	public String getConsumerName() {
		return consumerName;
	}
	public void setConsumerName(String consumerName) {
		this.consumerName = consumerName;
	}
	public String getConsumerNo() {
		return consumerNo;
	}
	public void setConsumerNo(String consumerNo) {
		this.consumerNo = consumerNo;
	}
	public String getDistributorAddress() {
		return distributorAddress;
	}
	public void setDistributorAddress(String distributorAddress) {
		this.distributorAddress = distributorAddress;
	}
	public String getDistributorCode() {
		return distributorCode;
	}
	public void setDistributorCode(String distributorCode) {
		this.distributorCode = distributorCode;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getLastBookingDate() {
		return lastBookingDate;
	}
	public void setLastBookingDate(String lastBookingDate) {
		this.lastBookingDate = lastBookingDate;
	}
	public String getConsumerAddress() {
		return consumerAddress;
	}
	public void setConsumerAddress(String consumerAddress) {
		this.consumerAddress = consumerAddress;
	}
	public String getTotalRefillConsumed() {
		return totalRefillConsumed;
	}
	public void setTotalRefillConsumed(String totalRefillConsumed) {
		this.totalRefillConsumed = totalRefillConsumed;
	}
		
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "LpgResult2 [status=" + status + ", distributorName=" + distributorName + ", subsidizedRefillConsumed="
				+ subsidizedRefillConsumed + ", pin=" + pin + ", consumerEmail=" + consumerEmail + ", givenUpSubsidy="
				+ givenUpSubsidy + ", bankName=" + bankName + ", ifscCode=" + ifscCode + ", city=" + city
				+ ", aadhaarNo=" + aadhaarNo + ", consumerContact=" + consumerContact + ", approximateSubsidyAvailed="
				+ approximateSubsidyAvailed + ", consumerName=" + consumerName + ", consumerNo=" + consumerNo
				+ ", distributorAddress=" + distributorAddress + ", distributorCode=" + distributorCode
				+ ", bankAccountNo=" + bankAccountNo + ", lastBookingDate=" + lastBookingDate + ", consumerAddress="
				+ consumerAddress + ", totalRefillConsumed=" + totalRefillConsumed + "]";
	}
	
	
	
}
