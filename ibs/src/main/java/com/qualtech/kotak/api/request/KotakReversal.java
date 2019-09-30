package com.qualtech.kotak.api.request;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_KOTAK_REQ_REV_HDR")
public class KotakReversal implements Serializable{
	private static final long serialVersionUID = 4624212678766280983L;
	@Id
	@JsonIgnore
	private long request_unique_id;
	@Embedded
	private KotakReversalHeader reversalHeader;
	
	@OneToOne (fetch=FetchType.LAZY,mappedBy="kotakReversal", cascade=CascadeType.ALL)
	private KotakReversalDtls details;
	
	
	
	public long getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(long request_unique_id) {
		this.request_unique_id = request_unique_id;
	}
	public KotakReversalDtls getDetails() {
		details.setKotakReversal(this);
		return details;
	}
	public void setDetails(KotakReversalDtls details) {
		this.details = details;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public KotakReversalHeader getReversalHeader() {
		return reversalHeader;
	}
	public void setReversalHeader(KotakReversalHeader reversalHeader) {
		this.reversalHeader = reversalHeader;
	}
	@Override
	public String toString() {
		return "KotakReversal [reversalHeader=" + reversalHeader + ", details=" + details + "]";
	}
	
	
}
