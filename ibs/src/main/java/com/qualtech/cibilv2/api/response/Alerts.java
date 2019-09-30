package com.qualtech.cibilv2.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Alerts implements Serializable{

	private static final long serialVersionUID = 8215734177941927879L;

	@JsonIgnore
	private AlertsInformationData alertsinformationData;
	@JsonIgnore
	private String content;
	
	public AlertsInformationData getAlertsinformationData() {
		return alertsinformationData;
	}
	public void setAlertsinformationData(AlertsInformationData alertsinformationData) {
		this.alertsinformationData = alertsinformationData;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
