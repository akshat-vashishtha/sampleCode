package com.qualtech.kotak.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class KotakResponseAckHeader implements Serializable {

	private static final long serialVersionUID = -749601171233318375L;
	
	@Column(name="MESSAGE_ID")
	private String messageId;
	@Column(name="STATUS_CODE")
	private String statusCd;
	@Column(name="STATUS_REMARK")
	private String statusRem;
	
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getStatusCd() {
		return statusCd;
	}
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}
	public String getStatusRem() {
		return statusRem;
	}
	public void setStatusRem(String statusRem) {
		this.statusRem = statusRem;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
		return "KotakResponseAckHeader [ messageId="
				+ messageId + ", statusCd=" + statusCd + ", statusRem="
				+ statusRem + "]";
	}
	
	

}
