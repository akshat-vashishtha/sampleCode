package com.qualtech.creditvidya.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_CREDIT_VRFY_COM_INFO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rocAddress;
	private String domainCompanyMapping;
	private String companyCin;
	private String companyAlternateDomain;
	private String companyRegistrationDate;
	private String companyAge;
	private String directors;
	
	@JsonIgnore
	private Long eid;
	private String uniqueid;
	@JsonIgnore
	private String sid;
    
    @Id
    @GeneratedValue(generator="IB_CREDIT_VRFY_COM_SQC")
	@SequenceGenerator(name="IB_CREDIT_VRFY_COM_SQC",sequenceName="IB_CREDIT_VRFY_COM_SQC",allocationSize=1)
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
	@Column(name="ROC_ADDRESS")
    public String getRocAddress() {
		return rocAddress;
	}
	public void setRocAddress(String rocAddress) {
		this.rocAddress = rocAddress;
	}
	@Column(name="DOMAIN_COM_MAPPING")
	public String getDomainCompanyMapping() {
		return domainCompanyMapping;
	}
	public void setDomainCompanyMapping(String domainCompanyMapping) {
		this.domainCompanyMapping = domainCompanyMapping;
	}
	@Column(name="COMPANY_CIN")
	public String getCompanyCin() {
		return companyCin;
	}
	public void setCompanyCin(String companyCin) {
		this.companyCin = companyCin;
	}
	@Column(name="COMPANY_ALTERNATE_DOMAIN")
	public String getCompanyAlternateDomain() {
		return companyAlternateDomain;
	}
	public void setCompanyAlternateDomain(String companyAlternateDomain) {
		this.companyAlternateDomain = companyAlternateDomain;
	}
	@Column(name="COMPANY_REGISTRATION_DATE")
	public String getCompanyRegistrationDate() {
		return companyRegistrationDate;
	}
	public void setCompanyRegistrationDate(String companyRegistrationDate) {
		this.companyRegistrationDate = companyRegistrationDate;
	}
	@Column(name="COMPANY_AGE")
	public String getCompanyAge() {
		return companyAge;
	}
	public void setCompanyAge(String companyAge) {
		this.companyAge = companyAge;
	}
	@Column(name="DIRECTORS")
	public String getDirectors() {
		return directors;
	}
	public void setDirectors(String directors) {
		this.directors = directors;
	}   
	
	@Override
	public String toString() {
		return "CompanyInfo [rocAddress=" + rocAddress + ", domainCompanyMapping=" + domainCompanyMapping
				+ ", companyCin=" + companyCin + ", companyAlternateDomain=" + companyAlternateDomain
				+ ", companyRegistrationDate=" + companyRegistrationDate + ", companyAge=" + companyAge + ", directors="
				+ directors + "]";
	}
	
	@Column
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
}
