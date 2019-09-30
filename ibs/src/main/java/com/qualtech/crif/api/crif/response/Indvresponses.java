
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
//    "indvresponselist",
//    "primarysummary",
//    "secondarysummary"
//})
public class Indvresponses implements Serializable
{

    //@JsonProperty("summary")
    private Summary_ summary;
    //@JsonProperty("indvresponselist")
    private String indvresponselist;
    //@JsonProperty("primarysummary")
    private Primarysummary_ primarysummary;
    //@JsonProperty("secondarysummary")
    private Secondarysummary_ secondarysummary;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5387327762184992609L;

    //@JsonProperty("summary")
    public Summary_ getSummary() {
        return summary;
    }

    //@JsonProperty("summary")
    public void setSummary(Summary_ summary) {
        this.summary = summary;
    }

    public Indvresponses withSummary(Summary_ summary) {
        this.summary = summary;
        return this;
    }

    //@JsonProperty("indvresponselist")
    public String getIndvresponselist() {
        return indvresponselist;
    }

    //@JsonProperty("indvresponselist")
    public void setIndvresponselist(String indvresponselist) {
        this.indvresponselist = indvresponselist;
    }

    public Indvresponses withIndvresponselist(String indvresponselist) {
        this.indvresponselist = indvresponselist;
        return this;
    }

    //@JsonProperty("primarysummary")
    public Primarysummary_ getPrimarysummary() {
        return primarysummary;
    }

    //@JsonProperty("primarysummary")
    public void setPrimarysummary(Primarysummary_ primarysummary) {
        this.primarysummary = primarysummary;
    }

    public Indvresponses withPrimarysummary(Primarysummary_ primarysummary) {
        this.primarysummary = primarysummary;
        return this;
    }

    //@JsonProperty("secondarysummary")
    public Secondarysummary_ getSecondarysummary() {
        return secondarysummary;
    }

    ////////@JsonProperty("secondarysummary")
    public void setSecondarysummary(Secondarysummary_ secondarysummary) {
        this.secondarysummary = secondarysummary;
    }

    public Indvresponses withSecondarysummary(Secondarysummary_ secondarysummary) {
        this.secondarysummary = secondarysummary;
        return this;
    }

  //  @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

//    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Indvresponses withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
