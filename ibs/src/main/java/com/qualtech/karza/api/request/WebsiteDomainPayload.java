package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name="IB_K_WEBDOMAIN_REQ")
public class WebsiteDomainPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;

	@Column(name="DOMAIN")
	 private String domain;
	 @Id
	 @JsonIgnore
	 @Column(name="UNIQUEID")
	 private Long uniqueid;
	 @JsonIgnore
	 @Column(name="CORELATIONID")
	 private String corelationid;
	 
	 
	 
	 
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

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String toString() {
		return "WebsiteDomainPayload [domain=" + domain + "]";
	}

	 
}
