
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
public class Variation____ implements Serializable
{

    //@JsonProperty("reporteddate")
    private String reporteddate;
    //@JsonProperty("value")
    private String value;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2489504707813680037L;

    //@JsonProperty("reporteddate")
    public String getReporteddate() {
        return reporteddate;
    }

    //@JsonProperty("reporteddate")
    public void setReporteddate(String reporteddate) {
        this.reporteddate = reporteddate;
    }

    public Variation____ withReporteddate(String reporteddate) {
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

    public Variation____ withValue(String value) {
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

    public Variation____ withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
