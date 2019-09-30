package com.qualtech.cibilv2.api.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown=true)
public class ConsumerInformationData {
    @JsonProperty("field")
	private List<ConsumerInformationDataField> consumerInformationDataField;

	public List<ConsumerInformationDataField> getConsumerInformationDataField() {
		return consumerInformationDataField;
	}

	public void setConsumerInformationDataField(List<ConsumerInformationDataField> consumerInformationDataField) {
		this.consumerInformationDataField = consumerInformationDataField;
	}
	
	
	
}
