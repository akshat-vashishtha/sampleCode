package com.qualtech.equifax.api.entity.pcs;

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

import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;
import com.qualtech.equifax.api.entity.common.PreviousName;

@Entity
@Table(name="IB_EQ_P_PRSNL_DTLS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxPcsPersonalDetails {

	@Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_PCS_PERSONAL_SQC")
	  @SequenceGenerator(name="IB_EQ_PCS_PERSONAL_SQC", sequenceName = "IB_EQ_PCS_PERSONAL_SQC", allocationSize = 1)
	@Column(name="PERSONALDETAIL_ID")
	private Long PersonalDetail_id;
	@Column(name="TOTALINCOME")
	private String totalincome="";
	@Column(name="OCCUPATION")
	private String occupation="";
	@Column(name="AGE")
	private String age="";
	@Column(name="FIRSTNAME")
	private String firstName="";
	@Column(name="MIDDLENAME")
	private String middleName="";
	@Column(name="LASTNAME")
	private String lastName="";
	@Column(name="GENDER")
	private String gender="";
	@Column(name="DATEOFBIRTH")
	private String dateofbirth="";
	@Transient
	private String maritalStatus="";
	@Transient
	private String dateOfbirthInfo="";
	@Transient
	private List<PreviousName> previousNames;
	@Column(name="ALIASNAMEINFO")
	private String aliasNameInfo="";
	@Transient
	private String fullName="";
	@Column(name="SUFFIX")
	private String suffix;
	@Column(name="ADDITIONALMIDDLENAME")
	private String additionalmiddlename="";
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxPcsAllDetails   equifaxPcsAllDetails;
	
	public EquifaxPcsAllDetails getEquifaxPcsAllDetails() {
		return equifaxPcsAllDetails;
	}
	public void setEquifaxPcsAllDetails(EquifaxPcsAllDetails equifaxPcsAllDetails) {
		this.equifaxPcsAllDetails = equifaxPcsAllDetails;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getAdditionalmiddlename() {
		return additionalmiddlename;
	}
	public void setAdditionalmiddlename(String additionalmiddlename) {
		this.additionalmiddlename = additionalmiddlename;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getDateOfbirthInfo() {
		return dateOfbirthInfo;
	}
	public void setDateOfbirthInfo(String dateOfbirthInfo) {
		this.dateOfbirthInfo = dateOfbirthInfo;
	}

	public List<PreviousName> getPreviousNames() {
		return previousNames;
	}
	public void setPreviousNames(List<PreviousName> previousNames) {
		this.previousNames = previousNames;
	}
	public String getAliasNameInfo() {
		return aliasNameInfo;
	}
	public void setAliasNameInfo(String aliasNameInfo) {
		this.aliasNameInfo = aliasNameInfo;
	}
	public Long getPersonalDetail_id() {
		return PersonalDetail_id;
	}
	public void setPersonalDetail_id(Long personalDetail_id) {
		PersonalDetail_id = personalDetail_id;
	}
	public String getTotalincome() {
		return totalincome;
	}
	public void setTotalincome(String totalincome) {
		this.totalincome = totalincome;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	@Override
	public String toString() {
		return "EquifaxPcsPersonalDetails [PersonalDetail_id="
				+ PersonalDetail_id + ", totalincome=" + totalincome
				+ ", occupation=" + occupation + ", age=" + age
				+ ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", gender=" + gender
				+ ", dateofbirth=" + dateofbirth + ", maritalStatus="
				+ maritalStatus + ", dateOfbirthInfo=" + dateOfbirthInfo
				+ ", previousNames=" + previousNames + ", aliasNameInfo="
				+ aliasNameInfo + ", fullName=" + fullName + "]";
	}
}
