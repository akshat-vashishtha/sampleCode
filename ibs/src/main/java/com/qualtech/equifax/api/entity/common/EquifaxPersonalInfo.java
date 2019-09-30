package com.qualtech.equifax.api.entity.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxPersonalInfo {
	
	@Column(name="marital_status")
	private String maritalstatus;
	@Column(name="age")
	private String age;
	@Column(name="firstname")
	private String firstname;
	@Column(name="middlename")
	private String middlename;
	@Column(name="lastname")
	private String lastname;
	@Column(name="additional_md_name")
	private String additionalmiddlename;
	@Column(name="gender")
	private String gender;
	@Column(name="dateofbirth")
	private String dateofbirth;
	@Column(name="aliasname")
	private String aliasname;
	
	@Transient
	private String reporteddate;
	@Column(name="noOfDependents")
	private String family_noOfDependents;
	@Column(name="additionalnametype")
	private String family_additionalnametype;
	@Column(name="additionalname")
	private String family_additionalname;
	
	
	
	public String getFamily_noOfDependents() {
		return family_noOfDependents;
	}
	public void setFamily_noOfDependents(String family_noOfDependents) {
		this.family_noOfDependents = family_noOfDependents;
	}
	public String getFamily_additionalnametype() {
		return family_additionalnametype;
	}
	public void setFamily_additionalnametype(String family_additionalnametype) {
		this.family_additionalnametype = family_additionalnametype;
	}
	public String getFamily_additionalname() {
		return family_additionalname;
	}
	public void setFamily_additionalname(String family_additionalname) {
		this.family_additionalname = family_additionalname;
	}
	public String getMaritalstatus() {
		return maritalstatus;
	}
	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAdditionalmiddlename() {
		return additionalmiddlename;
	}
	public void setAdditionalmiddlename(String additionalmiddlename) {
		this.additionalmiddlename = additionalmiddlename;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateofbirth() {
		return dateofbirth;
	}
	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}
	public String getAliasname() {
		return aliasname;
	}
	public void setAliasname(String aliasname) {
		this.aliasname = aliasname;
	}
	public String getReporteddate() {
		return reporteddate;
	}
	public void setReporteddate(String reporteddate) {
		this.reporteddate = reporteddate;
	}

		
}
