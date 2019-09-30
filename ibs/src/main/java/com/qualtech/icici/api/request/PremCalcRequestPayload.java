package com.qualtech.icici.api.request;

import java.io.Serializable;

public class PremCalcRequestPayload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6099127990486474460L;
	
	
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private String maritalStatus;
	private String staff;
	private String ageProof;
	private String serviceTaxNotApplicable;
	
	private ProductDetails  productiondetails;


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {   
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getStaff() {
		return staff;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getAgeProof() {
		return ageProof;
	}

	public void setAgeProof(String ageProof) {
		this.ageProof = ageProof;
	}

	public String getServiceTaxNotApplicable() {
		return serviceTaxNotApplicable;
	}

	public void setServiceTaxNotApplicable(String serviceTaxNotApplicable) {
		this.serviceTaxNotApplicable = serviceTaxNotApplicable;
	}

	public ProductDetails getProductiondetails() {
		return productiondetails;
	}

	public void setProductiondetails(ProductDetails productiondetails) {
		this.productiondetails = productiondetails;
	}
	
	@Override
	public String toString() {
		return "IciciRequestPayload [firstName=" + firstName + ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth
				+ ", gender=" + gender + ", maritalStatus=" + maritalStatus + ", staff=" + staff + ", ageProof="
				+ ageProof + ", serviceTaxNotApplicable=" + serviceTaxNotApplicable + ", productiondetails="
				+ productiondetails + "]";
	}

}
