package com.qualtech.api.ibs.util;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Table
@Entity(name="IBS_HDFC_NOMINEEDETAIL")
@JsonIgnoreProperties(ignoreUnknown=true)

public class NomineeDetails {

	
	@Id
	@GeneratedValue(generator = "IBS_HDFC_NOMINEEDETAIL_SQC")
	@SequenceGenerator(name = "IBS_HDFC_NOMINEEDETAIL_SQC", sequenceName = "IBS_HDFC_NOMINEEDETAIL_SQC")
	@JsonIgnore
	private int id;
	
	@JsonIgnore
	 private String corelationid;
	
	private String title;
    private String gender;
    private String firstName;
    private String lastName;
    private String dob;
    private String mobile;
    private String relationshipToCustomer;
    private String percentageAllocation;
    @Embedded
    private Address adress;
    @Embedded
    private AppointeeDetails appointeeDetails;
    
    
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
	public String getRelationshipToCustomer() {
		return relationshipToCustomer;
	}
	public void setRelationshipToCustomer(String relationshipToCustomer) {
		this.relationshipToCustomer = relationshipToCustomer;
	}
	public String getPercentageAllocation() {
		return percentageAllocation;
	}
	public void setPercentageAllocation(String percentageAllocation) {
		this.percentageAllocation = percentageAllocation;
	}
	public Address getAdress() {
		return adress;
	}
	public void setAdress(Address adress) {
		this.adress = adress;
	}
	public AppointeeDetails getAppointeeDetails() {
		return appointeeDetails;
	}
	public void setAppointeeDetails(AppointeeDetails appointeeDetails) {
		this.appointeeDetails = appointeeDetails;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	@Override
	public String toString() {
		return "NomineeDetails [id=" + id + ", corelationid=" + corelationid + ", title=" + title + ", gender=" + gender
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", mobile=" + mobile
				+ ", relationshipToCustomer=" + relationshipToCustomer + ", percentageAllocation="
				+ percentageAllocation + ", adress=" + adress + ", appointeeDetails=" + appointeeDetails + "]";
	}
	
	
}
