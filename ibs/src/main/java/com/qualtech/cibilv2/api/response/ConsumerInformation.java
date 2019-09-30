package com.qualtech.cibilv2.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown=true)

public class ConsumerInformation implements Serializable{

	private static final long serialVersionUID = -5212704840566826401L;
	//@JsonIgnore
	@JsonProperty("informationData")
	private ConsumerInformationData consumerInformationData;

	public ConsumerInformationData getConsumerInformationData() {
		return consumerInformationData;
	}

	public void setConsumerInformationData(ConsumerInformationData consumerInformationData) {
		this.consumerInformationData = consumerInformationData;
	}
	

	

	
}
