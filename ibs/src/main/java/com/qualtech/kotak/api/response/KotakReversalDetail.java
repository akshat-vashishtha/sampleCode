package com.qualtech.kotak.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_KOTAK_REV_DTL")
@JsonIgnoreProperties(ignoreUnknown=true)
public class KotakReversalDetail implements Serializable {

	private static final long serialVersionUID = -4475043506344265084L;
	
	@ManyToOne
	@JoinColumn(name="UNIQUEID",nullable=false)
	@JsonIgnore
	private KotakReversalResponsePayload kotakReversalResponsePayload;
	@Column(name="MESSAGE_ID")
	private String messageId;
	@Column(name="STATUS_CODE")
	private String statusCode;
	@Column(name="STATUS_DESCRIPTION")
	private String statusDescription;
	@Column(name="UTR")
	private String utr;
	@Id
	@GeneratedValue(generator = "IB_KOTAK")
	@SequenceGenerator(name = "IB_KOTAK", sequenceName = "IB_KOTAK_REV_DTL_SQC")
	@JsonIgnore
	private long KOTK_REV_DTL_ID;

	public long getKOTK_REV_DTL_ID() {
		return KOTK_REV_DTL_ID;
	}
	public void setKOTK_REV_DTL_ID(long kOTK_REV_DTL_ID) {
		KOTK_REV_DTL_ID = kOTK_REV_DTL_ID;
	}
	
	
	
	public KotakReversalResponsePayload getKotakReversalResponsePayload() {
		return kotakReversalResponsePayload;
	}
	public void setKotakReversalResponsePayload(KotakReversalResponsePayload kotakReversalResponsePayload) {
		this.kotakReversalResponsePayload = kotakReversalResponsePayload;
	}
	public String getUtr() {
		return utr;
	}
	public void setUtr(String utr) {
		this.utr = utr;
	}

	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "KotakReversalDetail [messageId=" + messageId + ", statusCode=" + statusCode + ", statusDescription="
				+ statusDescription + ", utr=" + utr + "]";
	}

	
	
	

}
