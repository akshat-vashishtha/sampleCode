package com.qualtech.kotak.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class KotakReversalHeader implements Serializable{

	
	private static final long serialVersionUID = 8319813643574537118L;
	@Column(name="REQUEST_ID")
	private String requestId;
	@Column(name="MSG_SOURCE")
	private String msgSource;
	@Column(name="CLIENT_CODE")
	private String clientCode;
	@Column(name="DATE_POST")
	private String datePost;
	
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getMsgSource() {
		return msgSource;
	}
	
	public void setMsgSource(String msgSource) {
		this.msgSource = msgSource;
	}
	public String getClientCode() {
		return clientCode;
	}
	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}
	public String getDatePost() {
		return datePost;
	}
	public void setDatePost(String datePost) {
		this.datePost = datePost;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	@Override
	public String toString() {
		return "KotakReversalHeader [requestId=" + requestId + ", msgSource="
				+ msgSource + ", clientCode=" + clientCode + ", datePost="
				+ datePost +  "]";
	}
	
	
	
}
