package com.qualtech.icici.api.response;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_IC_PRECALC_RES_EBIRES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EbiResponse {

	@JsonIgnore
	@Id
	private int eid;
	private String firstName;

	private String lastName;

	private String dateOfBirth;

	private String gender;

	private String maritalStatus;

	private String staff;

	private String ageProof;

	private String illustration;

	@OneToOne (fetch=FetchType.LAZY,mappedBy="premiumSummaryOne", cascade=CascadeType.ALL)
	private PremiumSummary premiumSummary;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGender() {
		return this.gender;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getMaritalStatus() {
		return this.maritalStatus;
	}

	public void setStaff(String staff) {
		this.staff = staff;
	}

	public String getStaff() {
		return this.staff;
	}

	public void setAgeProof(String ageProof) {
		this.ageProof = ageProof;
	}

	public String getAgeProof() {
		return this.ageProof;
	}

	public void setIllustration(String illustration) {
		this.illustration = illustration;
	}

	public String getIllustration() {
		return this.illustration;
	}

	public void setPremiumSummary(PremiumSummary premiumSummary) {
		this.premiumSummary = premiumSummary;
	}

	public PremiumSummary getPremiumSummary() {
		premiumSummary.setPremiumSummaryOne(this);
		return premiumSummary;
	}

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}
	
}
