package com.qualtech.creditvidya.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_CREDIT_VRFY_DOMAIN_INFO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DomainInfo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Long eid;
	private String uniqueid;
	private boolean domainExists;
	private boolean domainExpiryFlag;
	private String domainCreatedDate;
	
	private Integer domainAge;
	private String domainUpdatedDate;
	private String domainExpiryDate;
	private String registrantName;
	private String registrantOrganization;
	private String registrantEmail;
	private String registrantStreet1;
	private String registrantStreet2;
	private String registrantCity;
	private String registrantState;
	private String registrantPostalcode;
	private String registrantCountry;
	@JsonIgnore
	private String sid;
	
	
	@Column
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	@Id
	@GeneratedValue(generator="IB_CREDIT_VRFY_DOMAIN_SQC")
	@SequenceGenerator(name="IB_CREDIT_VRFY_DOMAIN_SQC",sequenceName="IB_CREDIT_VRFY_DOMAIN_SQC",allocationSize=1)
	@Column(name="EID")
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}

	
	@Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@Transient
	public boolean isDomainExists() {
		return domainExists;
	}
	public void setDomainExists(boolean domainExists) {
		this.domainExists = domainExists;
	}
	@Transient
	public boolean isDomainExpiryFlag() {
		return domainExpiryFlag;
	}
	public void setDomainExpiryFlag(boolean domainExpiryFlag) {
		this.domainExpiryFlag = domainExpiryFlag;
	}
	@Column(name="DOMAIN_CREATED_DATE")
	public String getDomainCreatedDate() {
		return domainCreatedDate;
	}
	public void setDomainCreatedDate(String domainCreatedDate) {
		this.domainCreatedDate = domainCreatedDate;
	}
	@Column(name="DOMAIN_AGE")
	public Integer getDomainAge() {
		return domainAge;
	}
	public void setDomainAge(Integer domainAge) {
		this.domainAge = domainAge;
	}
	@Column(name="DOMAIN_UPDATED_DATE")
	public String getDomainUpdatedDate() {
		return domainUpdatedDate;
	}
	public void setDomainUpdatedDate(String domainUpdatedDate) {
		this.domainUpdatedDate = domainUpdatedDate;
	}
	@Column(name="DOMAIN_EXPIRY_DATE")
	public String getDomainExpiryDate() {
		return domainExpiryDate;
	}
	public void setDomainExpiryDate(String domainExpiryDate) {
		this.domainExpiryDate = domainExpiryDate;
	}
	@Column(name="REGISTRATION_NAME")
	public String getRegistrantName() {
		return registrantName;
	}
	public void setRegistrantName(String registrantName) {
		this.registrantName = registrantName;
	}
	@Column(name="REGISTRATION_ORG")
	public String getRegistrantOrganization() {
		return registrantOrganization;
	}
	public void setRegistrantOrganization(String registrantOrganization) {
		this.registrantOrganization = registrantOrganization;
	}
	@Column(name="REGISTRANT_EMAIL")
	public String getRegistrantEmail() {
		return registrantEmail;
	}
	public void setRegistrantEmail(String registrantEmail) {
		this.registrantEmail = registrantEmail;
	}
	@Column(name="REGISTRANT_STREET1")
	public String getRegistrantStreet1() {
		return registrantStreet1;
	}
	public void setRegistrantStreet1(String registrantStreet1) {
		this.registrantStreet1 = registrantStreet1;
	}
	@Column(name="REGISTRANT_STREET2")
	public String getRegistrantStreet2() {
		return registrantStreet2;
	}
	public void setRegistrantStreet2(String registrantStreet2) {
		this.registrantStreet2 = registrantStreet2;
	}
	@Column(name="REGISTRANT_CITY")
	public String getRegistrantCity() {
		return registrantCity;
	}
	public void setRegistrantCity(String registrantCity) {
		this.registrantCity = registrantCity;
	}
	@Column(name="REGISTRANT_STATE")
	public String getRegistrantState() {
		return registrantState;
	}
	public void setRegistrantState(String registrantState) {
		this.registrantState = registrantState;
	}
	@Column(name="REGISTRANT_POSTALCODE")
	public String getRegistrantPostalcode() {
		return registrantPostalcode;
	}
	public void setRegistrantPostalcode(String registrantPostalcode) {
		this.registrantPostalcode = registrantPostalcode;
	}
	@Column(name="REGISTRANT_COUNTRY")
	public String getRegistrantCountry() {
		return registrantCountry;
	}
	public void setRegistrantCountry(String registrantCountry) {
		this.registrantCountry = registrantCountry;
	}
	
	@Override
	public String toString() {
		return "DomainInfo [domainExists=" + domainExists + ", domainExpiryFlag=" + domainExpiryFlag
				+ ", domainCreatedDate=" + domainCreatedDate + ", domainAge=" + domainAge + ", domainUpdatedDate="
				+ domainUpdatedDate + ", domainExpiryDate=" + domainExpiryDate + ", registrantName=" + registrantName
				+ ", registrantOrganization=" + registrantOrganization + ", registrantEmail=" + registrantEmail
				+ ", registrantStreet1=" + registrantStreet1 + ", registrantStreet2=" + registrantStreet2
				+ ", registrantCity=" + registrantCity + ", registrantState=" + registrantState
				+ ", registrantPostalcode=" + registrantPostalcode + ", registrantCountry=" + registrantCountry + "]";
	}
	

}
