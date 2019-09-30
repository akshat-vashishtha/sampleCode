package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name="IB_K_WEBDOMAIN_RGSTRANT_RES")
public class WebsiteDomainRegistrant implements Serializable {
	private static final long serialVersionUID = 8348785090820350364L;

	@Column(name="CITY")
	private String city;
	@Column(name="FAX")
	private String fax;
	@Column(name="NAME")
	private String name;
	@Column(name="COUNTRY")
	private String country;
	@Column(name="STATE_PROVIDER")
	private String stateProvince;
	@Column(name="PHONE")
	private String phone;
	@Column(name="STREET")
	private String street;
	@Column(name="ORGANIZATION")
	private String organization;
	@Column(name="POSTAL")
	private String postal;
	@Column(name="EMAIL")
	private String email;
	@Id
	@GeneratedValue(generator="RGSTRANT")
	@SequenceGenerator(name="RGSTRANT",sequenceName="IB_K_WD_REGISTRANT_SQC")
	@JsonIgnore
	private Long kid;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="UNIQUEID", nullable=false)
	private WebsiteDomainResult websiteDomainResult;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStateProvince() {
		return stateProvince;
	}
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Long getKid() {
		return kid;
	}
	public void setKid(Long kid) {
		this.kid = kid;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "WebsiteDomainRegistrant [city=" + city + ", fax=" + fax + ", name=" + name + ", country=" + country
				+ ", stateProvince=" + stateProvince + ", phone=" + phone + ", street=" + street + ", organization="
				+ organization + ", postal=" + postal + ", email=" + email + "]";
	}
	public WebsiteDomainResult getWebsiteDomainResult() {
		return websiteDomainResult;
	}
	public void setWebsiteDomainResult(WebsiteDomainResult websiteDomainResult) {
		this.websiteDomainResult = websiteDomainResult;
	}
	
}
