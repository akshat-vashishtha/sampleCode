package com.qualtech.hdfc.api.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @author qualtech
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiResponseHeader {

	private static final long serialVersionUID = 1794255435559192435L;
	String msgVersion = "";
	String appId = "";
	String correlationId = "";
	public String getMsgVersion() {
		return msgVersion;
	}
	public void setMsgVersion(String msgVersion) {
		this.msgVersion = msgVersion;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
