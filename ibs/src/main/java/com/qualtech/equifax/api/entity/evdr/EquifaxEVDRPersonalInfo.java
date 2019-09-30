package com.qualtech.equifax.api.entity.evdr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxEvdrAllDetails;

@Entity
@Table(name="IB_EQ_E_PRSNL_INFO")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxEVDRPersonalInfo 
{
	
	@Column(name="DATE_OF_BIRTH")
	private String date_of_birth;
	@Column(name="OCCUPATION")
	private String occupation ;
	@Transient
	private String totalincome;
	@Column(name="GENDER")
	private String gender;
	@Column(name="FIRST_NAME")
	private String first_name;
	@Column(name="MIDDLE_NAME")
	private String middle_name;
	@Column(name="LAST_NAME")
	private String last_name;
	@Column(name="AGE")
	private String age;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_E_P_INFO_SQC")
	@SequenceGenerator(name="IB_EQ_E_P_INFO_SQC", sequenceName = "IB_EQ_E_P_INFO_SQC", allocationSize = 1)
	@Column(name="PERSONAL_INFO_ID")
	private Long personal_info_id;
	@OneToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID")
	private EquifaxEvdrAllDetails equifaxevdrdetails_logs; 
	
	
	
	
	public EquifaxEvdrAllDetails getEquifaxevdrdetails_logs() {
		return equifaxevdrdetails_logs;
	}
	public void setEquifaxevdrdetails_logs(EquifaxEvdrAllDetails equifaxevdrdetails_logs) {
		this.equifaxevdrdetails_logs = equifaxevdrdetails_logs;
	}
	
	
	public String getTotalincome() {
		return totalincome;
	}
	public void setTotalincome(String totalincome) {
		this.totalincome = totalincome;
	}
//	@Column(name="DATE_OF_BIRTH")
	public String getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(String date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
//	@Column(name="OCCUPATION")
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
//	@Column(name="GENDER")
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
//	@Column(name="FIRST_NAME")
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
//	@Column(name="MIDDLE_NAME")
	public String getMiddle_name() {
		return middle_name;
	}
	public void setMiddle_name(String middle_name) {
		this.middle_name = middle_name;
	}
//	@Column(name="LAST_NAME")
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
//	@Column(name="AGE")
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
//	@Id
//	@Column(name="PERSONAL_INFO_ID")
	public Long getPersonal_info_id() {
		return personal_info_id;
	}
	public void setPersonal_info_id(Long personal_info_id) {
		this.personal_info_id = personal_info_id;
	}
	
	
}
