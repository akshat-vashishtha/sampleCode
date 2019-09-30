package com.qualtech.karza.api.response;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_EMAILAUTH_META_RES")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EmailAuthMeta{

	
	@Embedded
	private EmailAuthParam params;
	@Id
	private long  eid;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="EID")
	@JsonIgnore
	private EmailAuthResult authResult;
	
	/*@OneToOne(fetch=FetchType.LAZY,mappedBy="",cascade=CascadeType.ALL)*/
	public EmailAuthParam getParams() {
		return params;
	}
	public void setParam(EmailAuthParam params) {
		this.params = params;
		this.getAuthResult().getCorelationid();
	}

	@Override
	public String toString() {
		return "EmailAuthMeta [params=" + params + "]";
	}
	public EmailAuthResult getAuthResult() {
		return authResult;
	}
	public void setAuthResult(EmailAuthResult authResult) {
		this.authResult = authResult;
	}
	public long getEid() {
		return eid;
	}
	public void setEid(long eid) {
		this.eid = eid;
	}
	public void setParams(EmailAuthParam params) {
		this.params = params;
	}
	
}
