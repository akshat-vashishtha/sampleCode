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
@Table(name="IB_K_WEBDOMAIN_REGISTRY_RES")
public class WebsiteDomainRegistry implements Serializable {
	private static final long serialVersionUID = 8348785090820350364L;

	@Column(name="PHONE")
	private String phone;
	@Column(name="EXPIRY")
	private String expiry;
	@Column(name="ID")
	private String id;
	@Column(name="EMAIL")
	private String email;
	
	@Id
	@GeneratedValue(generator="REGISTRY")
	@SequenceGenerator(name="REGISTRY",sequenceName="IB_K_WD_REGISTRY_SQC")
	@JsonIgnore
	private Long kid;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="UNIQUEID", nullable=false)
	private WebsiteDomainResult websiteDomainResult;
		
	public Long getKid() {
		return kid;
	}
	public void setKid(Long kid) {
		this.kid = kid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getExpiry() {
		return expiry;
	}
	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "WebsiteDomainRegistry [phone=" + phone + ", expiry=" + expiry + ", id=" + id + ", email=" + email + "]";
	}
	public WebsiteDomainResult getWebsiteDomainResult() {
		return websiteDomainResult;
	}
	public void setWebsiteDomainResult(WebsiteDomainResult websiteDomainResult) {
		this.websiteDomainResult = websiteDomainResult;
	}
	
}
