
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
//    "summary",
//    "primarysummary",
//    "grpresponselist",
//    "secondarysummary"
//})
public class Grpresponses implements Serializable
{

    //@JsonProperty("summary")
    private Summary summary;
    //@JsonProperty("primarysummary")
    private Primarysummary primarysummary;
    //@JsonProperty("grpresponselist")
    private String grpresponselist;
    //@JsonProperty("secondarysummary")
    private Secondarysummary secondarysummary;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 6352509896271082022L;

    //@JsonProperty("summary")
    public Summary getSummary() {
        return summary;
    }

    //@JsonProperty("summary")
    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public Grpresponses withSummary(Summary summary) {
        this.summary = summary;
        return this;
    }

    //@JsonProperty("primarysummary")
    public Primarysummary getPrimarysummary() {
        return primarysummary;
    }

    //@JsonProperty("primarysummary")
    public void setPrimarysummary(Primarysummary primarysummary) {
        this.primarysummary = primarysummary;
    }

    public Grpresponses withPrimarysummary(Primarysummary primarysummary) {
        this.primarysummary = primarysummary;
        return this;
    }

    //@JsonProperty("grpresponselist")
    public String getGrpresponselist() {
        return grpresponselist;
    }

    //@JsonProperty("grpresponselist")
    public void setGrpresponselist(String grpresponselist) {
        this.grpresponselist = grpresponselist;
    }

    public Grpresponses withGrpresponselist(String grpresponselist) {
        this.grpresponselist = grpresponselist;
        return this;
    }

    //@JsonProperty("secondarysummary")
    public Secondarysummary getSecondarysummary() {
        return secondarysummary;
    }

    //@JsonProperty("secondarysummary")
    public void setSecondarysummary(Secondarysummary secondarysummary) {
        this.secondarysummary = secondarysummary;
    }

    public Grpresponses withSecondarysummary(Secondarysummary secondarysummary) {
        this.secondarysummary = secondarysummary;
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

    public Grpresponses withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
