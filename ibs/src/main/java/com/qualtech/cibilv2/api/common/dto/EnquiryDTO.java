package com.qualtech.cibilv2.api.common.dto;


public class EnquiryDTO 
{
	
	private String dateOfEnquiry;
	private String enquiryShortName;
	private String enquiryPurpose;
	private String enquiryAmount;

	public String getDateOfEnquiry() {
		return dateOfEnquiry;
	}

	public void setDateOfEnquiry(String dateOfEnquiry) {
		this.dateOfEnquiry = dateOfEnquiry;
	}

	public String getEnquiryShortName() {
		return enquiryShortName;
	}

	public void setEnquiryShortName(String enquiryShortName) {
		this.enquiryShortName = enquiryShortName;
	}

	public String getEnquiryPurpose() {
		return enquiryPurpose;
	}

	public void setEnquiryPurpose(String enquiryPurpose) {
		this.enquiryPurpose = enquiryPurpose;
	}

	public String getEnquiryAmount() {
		return enquiryAmount;
	}

	public void setEnquiryAmount(String enquiryAmount) {
		this.enquiryAmount = enquiryAmount;
	}

}
