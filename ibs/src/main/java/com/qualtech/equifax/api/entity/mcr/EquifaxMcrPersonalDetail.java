package com.qualtech.equifax.api.entity.mcr;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;
import com.qualtech.equifax.api.entity.common.PreviousName;
@Entity
@Table(name="IB_EQ_M_PRSNL_DTLS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxMcrPersonalDetail {
	 

	
	@Column(name="DATEOFBIRTH")
	private String date_of_birth="";
	@Column(name="MARITAL_STATUS")
	private String maritalstatus="";
	@Column
	private String additionalmiddlename="";
	@Column
	private String gender="";
	@Column(name="FIRSTNAME")
	private String first_name="";
	@Column(name="MIDDLENAME")
	private String middle_name="";
	@Column(name="LASTNAME")
	private String last_name="";
	@Column
	private String age="";
	@Column
	private String aliasname="";
	@Column(name="REPORTED_DATE")
	private String reporteddate="";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_PERSNL_SQC")
	@SequenceGenerator(name="IB_EQ_MCR_PERSNL_SQC", sequenceName = "IB_EQ_MCR_PERSNL_SQC", allocationSize = 1)
	@Column(name="PERSONALDETAIL_ID")
	private int personal_info_id;
	@Column
	private String suffix="";
	@Transient
	private List<PreviousName> previousNamesList;
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxMcrAllDetaills  equifaxMcrDetailsLogs;
	
	
	
	public EquifaxMcrAllDetaills getEquifaxMcrDetailsLogs() {
		return equifaxMcrDetailsLogs;
	}
	public void setEquifaxMcrDetailsLogs(EquifaxMcrAllDetaills equifaxMcrDetailsLogs) {
		this.equifaxMcrDetailsLogs = equifaxMcrDetailsLogs;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setPreviousNamesList(List<PreviousName> previousNamesList) {
		this.previousNamesList = previousNamesList;
	}
	public List<PreviousName> getPreviousNamesList() {
		return previousNamesList;
	}
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public String getMaritalstatus() {
		return maritalstatus;
	}
	public void setMaritalstatus(String maritalstatus) {
		this.maritalstatus = maritalstatus;
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
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
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
	public int getPersonal_info_id() {
		return personal_info_id;
	}
	public void setPersonal_info_id(int personal_info_id) {
		this.personal_info_id = personal_info_id;
	}
	
	
}
