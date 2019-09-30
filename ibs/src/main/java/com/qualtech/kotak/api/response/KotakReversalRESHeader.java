package com.qualtech.kotak.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KotakReversalRESHeader implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 7620678578046622965L;
	
	private String requestId;
	
	private String msgSource;
	
	private String clientCode;
	
	private String datePost;
	
	
	private long request_unique_id;
	
	
	
	public long getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(long request_unique_id) {
		this.request_unique_id = request_unique_id;
	}
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
	public KotakReversalRESHeader(String requestId, String msgSource, String clientCode, String datePost,
			long request_unique_id) {
		super();
		this.requestId = requestId;
		this.msgSource = msgSource;
		this.clientCode = clientCode;
		this.datePost = datePost;
		this.request_unique_id = request_unique_id;
	}
	
	
	

}
