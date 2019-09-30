
package com.qualtech.crif.api.crif.response;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
//import com.fasterxml.jackson.annotation.JsonAnyGetter;
//import com.fasterxml.jackson.annotation.JsonAnySetter;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//@JsonInclude(JsonInclude.Include.NON_NULL)
//@JsonPropertyOrder({
//    "reporteddate",
//    "value"
//})
public class Variation___ implements Serializable
{

    //@JsonProperty("reporteddate")
    private String reporteddate;
    //@JsonProperty("value")
    private String value;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1357072051164329347L;

    //@JsonProperty("reporteddate")
    public String getReporteddate() {
        return reporteddate;
    }

    //@JsonProperty("reporteddate")
    public void setReporteddate(String reporteddate) {
        this.reporteddate = reporteddate;
    }

    public Variation___ withReporteddate(String reporteddate) {
        this.reporteddate = reporteddate;
        return this;
    }

    //@JsonProperty("value")
    public String getValue() {
        return value;
    }

    //@JsonProperty("value")
    public void setValue(String value) {
        this.value = value;
    }

    public Variation___ withValue(String value) {
        this.value = value;
        return this;
    }

//    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

//    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Variation___ withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
