package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class CreditVidhyaIbsRequest implements Serializable{

	private String leadId;
	private String transactionId;
	private String losId;
	private String uniqueId;
	private String companyName;
	
	private String officeAddressLine1;
	private String officeAddressLine2;
	private String officeAddressLine3;
	private String officeCity;
	private String officeState;
	private String officePinCode;
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getLosId() {
		return losId;
	}
	public void setLosId(String losId) {
		this.losId = losId;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOfficeAddressLine1() {
		return officeAddressLine1;
	}
	public void setOfficeAddressLine1(String officeAddressLine1) {
		this.officeAddressLine1 = officeAddressLine1;
	}
	public String getOfficeAddressLine2() {
		return officeAddressLine2;
	}
	public void setOfficeAddressLine2(String officeAddressLine2) {
		this.officeAddressLine2 = officeAddressLine2;
	}
	public String getOfficeAddressLine3() {
		return officeAddressLine3;
	}
	public void setOfficeAddressLine3(String officeAddressLine3) {
		this.officeAddressLine3 = officeAddressLine3;
	}
	public String getOfficeCity() {
		return officeCity;
	}
	public void setOfficeCity(String officeCity) {
		this.officeCity = officeCity;
	}
	public String getOfficeState() {
		return officeState;
	}
	public void setOfficeState(String officeState) {
		this.officeState = officeState;
	}
	public String getOfficePinCode() {
		return officePinCode;
	}
	public void setOfficePinCode(String officePinCode) {
		this.officePinCode = officePinCode;
	}
	@Override
	public String toString() {
		return "CreditVidhyaIbsRequest [leadId=" + leadId + ", transactionId=" + transactionId + ", losId=" + losId
				+ ", uniqueId=" + uniqueId + ", companyName=" + companyName + ", officeAddressLine1="
				+ officeAddressLine1 + ", officeAddressLine2=" + officeAddressLine2 + ", officeAddressLine3="
				+ officeAddressLine3 + ", officeCity=" + officeCity + ", officeState=" + officeState
				+ ", officePinCode=" + officePinCode + "]";
	}
	

	
	
	
}
