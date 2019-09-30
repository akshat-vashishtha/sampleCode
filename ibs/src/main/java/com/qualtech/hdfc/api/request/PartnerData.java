package com.qualtech.hdfc.api.request;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({
    "sumAssuredType"
})

@JsonIgnoreProperties(ignoreUnknown=true)
@Embeddable
public class PartnerData {
	@JsonProperty("sumAssuredType")
    private String sumAssuredType;
	
	public String getFundingOption() {
		return fundingOption;
	}

	public void setFundingOption(String fundingOption) {
		this.fundingOption = fundingOption;
	}

	@JsonProperty("fundingOption")
	private String fundingOption;
    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public PartnerData() {
    }

    /**
     * 
     * @param sumAssuredType
     */
    public PartnerData(String sumAssuredType) {
        super();
        this.sumAssuredType = sumAssuredType;
    }

    @JsonProperty("sumAssuredType")
    public String getSumAssuredType() {
        return sumAssuredType;
    }

    @JsonProperty("sumAssuredType")
    public void setSumAssuredType(String sumAssuredType) {
        this.sumAssuredType = sumAssuredType;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}
