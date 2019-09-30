package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_K_WEBDOMAIN_RES")
public class WebsiteDomainResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
	
	@Column(name="UPDATE_DATE")
	private String updateDate;
	@Column(name="EXPIRATION_DATE")
	private String  expirationDate;
	@Column(name="CREATION_DATE")
	private String  creationDate;
	@Id
	@Column(name="UNIQUEID")
	@JsonIgnore
	private Long uniqueid;
	@Column(name="CORELATIONID")
	@JsonIgnore
	private String corelationid;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="websiteDomainResult", cascade=CascadeType.ALL)
	private WebsiteDomain domain;
	@OneToOne(fetch=FetchType.LAZY, mappedBy="websiteDomainResult", cascade=CascadeType.ALL)
	private WebsiteDomainAdmin admin;
	@OneToOne(fetch=FetchType.LAZY, mappedBy="websiteDomainResult", cascade=CascadeType.ALL)
	private WebsiteDomainTech tech;
	@OneToOne(fetch=FetchType.LAZY, mappedBy="websiteDomainResult", cascade=CascadeType.ALL)
	private WebsiteDomainRegistry registry;
	@OneToOne(fetch=FetchType.LAZY, mappedBy="websiteDomainResult", cascade=CascadeType.ALL)
	private WebsiteDomainRegistrar registrar;
	@OneToOne(fetch=FetchType.LAZY, mappedBy="websiteDomainResult", cascade=CascadeType.ALL)
	private WebsiteDomainNameServer nameserver;
	@OneToOne(fetch=FetchType.LAZY, mappedBy="websiteDomainResult", cascade=CascadeType.ALL)
	private WebsiteDomainRegistrant registrant;
	
	public Long getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(Long uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public WebsiteDomain getDomain() {
		domain.setWebsiteDomainResult(this);
		return domain;
	}
	public void setDomain(WebsiteDomain domain) {
		this.domain = domain;
	}
	public WebsiteDomainAdmin getAdmin() {
		admin.setWebsiteDomainResult(this);
		return admin;
	}
	public void setAdmin(WebsiteDomainAdmin admin) {
		this.admin = admin;
	}
	public WebsiteDomainTech getTech() {
		tech.setWebsiteDomainResult(this);
		return tech;
	}
	public void setTech(WebsiteDomainTech tech) {
		this.tech = tech;
	}
	public WebsiteDomainRegistry getRegistry() {
		registry.setWebsiteDomainResult(this);
		return registry;
	}
	public void setRegistry(WebsiteDomainRegistry registry) {
		this.registry = registry;
	}
	public WebsiteDomainRegistrar getRegistrar() {
		registrar.setWebsiteDomainResult(this);
		return registrar;
	}
	public void setRegistrar(WebsiteDomainRegistrar registrar) {
		this.registrar = registrar;
	}

	public WebsiteDomainNameServer getNameserver() {
		nameserver.setWebsiteDomainResult(this);
		return nameserver;
	}
	public void setNameserver(WebsiteDomainNameServer nameserver) {
		this.nameserver = nameserver;
	}
	public WebsiteDomainRegistrant getRegistrant() {
		registrant.setWebsiteDomainResult(this);
		return registrant;
	}
	public void setRegistrant(WebsiteDomainRegistrant registrant) {
		this.registrant = registrant;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "WebsiteDomainResult [updateDate=" + updateDate + ", expirationDate=" + expirationDate
				+ ", creationDate=" + creationDate + ", domain=" + domain + ", admin=" + admin + ", tech=" + tech
				+ ", registry=" + registry + ", registrar=" + registrar + ", nameserver=" + nameserver + ", registrant="
				+ registrant + "]";
	}
	
}

