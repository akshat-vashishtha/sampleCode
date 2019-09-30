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
@Table(name="IB_K_WEBDOMAIN_NSERVER_RES")
public class WebsiteDomainNameServer implements Serializable {
	private static final long serialVersionUID = 8348785090820350364L;
	@Column(name="NAME")
	private String name;
	@Id
	@GeneratedValue(generator="SERVER")
	@SequenceGenerator(name="SERVER",sequenceName="IB_K_WD_NSERVER_SQC")
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "WebsiteDomainNameServer [name=" + name + "]";
	}
	public WebsiteDomainResult getWebsiteDomainResult() {
		return websiteDomainResult;
	}
	public void setWebsiteDomainResult(WebsiteDomainResult websiteDomainResult) {
		this.websiteDomainResult = websiteDomainResult;
	}
	
}
