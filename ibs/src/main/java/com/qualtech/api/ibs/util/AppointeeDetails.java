package com.qualtech.api.ibs.util;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
@Embeddable
public class AppointeeDetails {

	
	@Column(name="APP_TITLE")
	private String title;
	@Column(name="APP_GENDER")
    private String gender;
	@Column(name="APP_FIRSTNAME")
    private String firstName;
	@Column(name="APP_LASTNAME")
    private String lastName;
	@Column(name="APP_DOB")
    private String dob;
	@Column(name="APP_MOBILE")
    private String mobile;
	@Column(name="APP_RELATIONSHIPTONOMINEE")
    private String relationshipToNominee;
	@Embedded
    private AppointeeAddress adress;
    
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
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
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRelationshipToNominee() {
		return relationshipToNominee;
	}
	public void setRelationshipToNominee(String relationshipToNominee) {
		this.relationshipToNominee = relationshipToNominee;
	}
	public AppointeeAddress getAdress() {
		return adress;
	}
	public void setAdress(AppointeeAddress adress) {
		this.adress = adress;
	}
	@Override
	public String toString() {
		return "AppointeeDetails [title=" + title + ", gender=" + gender + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dob=" + dob + ", mobile=" + mobile + ", relationshipToNominee=" + relationshipToNominee
				+ ", adress=" + adress + "]";
	}
    
	
}
