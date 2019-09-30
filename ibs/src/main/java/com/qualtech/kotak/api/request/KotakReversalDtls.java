package com.qualtech.kotak.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_KOTAK_REQ_REV_DTL")
public class KotakReversalDtls implements Serializable {

	private static final long serialVersionUID = -4475043506344265084L;
	@MapsId
	@OneToOne
	@JoinColumn(name="request_unique_id",nullable=false)
	@JsonIgnore
	private KotakReversal kotakReversal;
	@Column(name="MESSAGE_ID")
	private String messageId;
	@Id
	private long request_unique_id;
	
	
	public long getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(long request_unique_id) {
		this.request_unique_id = request_unique_id;
	}
	public KotakReversal getKotakReversal() {
		return kotakReversal;
	}
	public void setKotakReversal(KotakReversal kotakReversal) {
		this.kotakReversal = kotakReversal;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessageId() {
		return messageId;
	}
	@Override
	public String toString() {
		return "KotakReversalDtls [ messageId=" + messageId + "]";
	}
	
	
	

}
