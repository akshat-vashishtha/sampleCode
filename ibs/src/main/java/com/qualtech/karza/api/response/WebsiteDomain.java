package com.qualtech.karza.api.response;

import java.io.Serializable;

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
@Table(name="IB_K_WEBDOMAIN_DOMAIN_RES")
public class WebsiteDomain implements Serializable {
	private static final long serialVersionUID = 8348785090820350364L;
	private String name;
	private String id;
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="UNIQUEID", nullable=false)
	private WebsiteDomainResult websiteDomainResult;
	@Id
	@GeneratedValue(generator="DOMAIN")
	@SequenceGenerator(name="DOMAIN",sequenceName="IB_K_WD_DOMAIN_SQC")
	@JsonIgnore
	private Long kid;
	
	public Long getKid() {
		return kid;
	}
	public void setKid(Long kid) {
		this.kid = kid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "WebsiteDomain [name=" + name + ", id=" + id + "]";
	}
	public WebsiteDomainResult getWebsiteDomainResult() {
		return websiteDomainResult;
	}
	public void setWebsiteDomainResult(WebsiteDomainResult websiteDomainResult) {
		this.websiteDomainResult = websiteDomainResult;
	}

	
}
