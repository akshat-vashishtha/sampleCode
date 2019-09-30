package com.qualtech.experiankickoff.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.experiankickoff.api.common.dto.ErrorInfo;
import com.qualtech.experiankickoff.api.request.Header;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperianResponse extends ExperianKickOffResponse implements Serializable  {
	private static final long serialVersionUID = 8348785090820350364L;

	private Header header;
	private ErrorInfo errorInfo;
	private ExperianResponsePayload payload;
	@JsonIgnore
	private String sessionId;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	
	public ExperianResponsePayload getPayload() {
		return payload;
	}
	public void setPayload(ExperianResponsePayload payload) {
		this.payload = payload;
	}
	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}
	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "ExperianResponse [header=" + header + ", errorInfo=" + errorInfo + ", payload=" + payload + "]";
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
