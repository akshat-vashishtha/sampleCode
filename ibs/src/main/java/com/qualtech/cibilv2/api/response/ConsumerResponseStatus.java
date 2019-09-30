package com.qualtech.cibilv2.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class ConsumerResponseStatus implements Serializable{

	private static final long serialVersionUID = -5337259434025580935L;
	@JsonIgnore
	private ConsumerResInfoData consumerResInfoData;

	public ConsumerResInfoData getConsumerResInfoData() {
		return consumerResInfoData;
	}

	public void setConsumerResInfoData(ConsumerResInfoData consumerResInfoData) {
		this.consumerResInfoData = consumerResInfoData;
	}
	

	
	

}
