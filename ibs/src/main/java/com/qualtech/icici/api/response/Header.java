package com.qualtech.icici.api.response;

import java.io.Serializable;

public class Header implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5606629235099374953L;
	
	private String msgVersion = "";
	private String appId = "";
	private String correlationId = "";
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
	@Override
	public String toString() {
		return "IciciResponseHeader [msgVersion=" + msgVersion + ", appId=" + appId + ", correlationId=" + correlationId
				+ "]";
	}

}
