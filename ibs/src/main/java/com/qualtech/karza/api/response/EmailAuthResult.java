package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name="IB_K_EMAILAUTH_DATA_RES")
public class EmailAuthResult implements Serializable{


	private static final long serialVersionUID = -1487181621558070124L;
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_K_EMAILAUTH_DATA_SQC")
	@SequenceGenerator(name="IB_K_EMAILAUTH_DATA_SQC", sequenceName = "IB_K_EMAILAUTH_DATA_SQC", allocationSize = 1)
	@JsonIgnore
	@Column(name="SEQUENCE_ID")
	private long  sequenceid;
	
	private long  eid;
	
	private String corelationid;
	//@Embedded
	@OneToOne(fetch=FetchType.LAZY,mappedBy="authResult",cascade=CascadeType.ALL)
	private EmailAuthMeta meta;
	@Embedded
	private EmailAuthData data;

	public long getSequenceid() {
		return sequenceid;
	}
	public void setSequenceid(long sequenceid) {
		this.sequenceid = sequenceid;
	}
	public EmailAuthMeta getMeta() {
		if(meta.getParams()!=null)
			meta.getParams().setCorelationid(corelationid);
			//meta.getAuthResult().getCorelationid();
		meta.setAuthResult(this);
		return meta;
	}
	public void setMeta(EmailAuthMeta meta) {
		this.meta = meta;
	}
	public EmailAuthData getData() {
		return data;
	}
	public void setData(EmailAuthData data) {
		this.data = data;
	}
	public long getEid() {
		return eid;
	}
	public void setEid(long eid) {
		this.eid = eid;
	}
	@Column(name="CORELATIONID")
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	

}
