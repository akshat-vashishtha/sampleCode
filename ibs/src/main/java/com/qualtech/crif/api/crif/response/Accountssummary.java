
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
//    "derivedattributes",
//    "primaryaccountssummary",
//    "secondaryaccountssummary"
//})
public class Accountssummary implements Serializable
{

//    @JsonProperty("derivedattributes")
    private Derivedattributes derivedattributes;
//    @JsonProperty("primaryaccountssummary")
    private Primaryaccountssummary primaryaccountssummary;
    //@JsonProperty("secondaryaccountssummary")
    private Secondaryaccountssummary secondaryaccountssummary;
    //@JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -2290075861706249146L;

    //@JsonProperty("derivedattributes")
    public Derivedattributes getDerivedattributes() {
        return derivedattributes;
    }

    //@JsonProperty("derivedattributes")
    public void setDerivedattributes(Derivedattributes derivedattributes) {
        this.derivedattributes = derivedattributes;
    }

    public Accountssummary withDerivedattributes(Derivedattributes derivedattributes) {
        this.derivedattributes = derivedattributes;
        return this;
    }

    //@JsonProperty("primaryaccountssummary")
    public Primaryaccountssummary getPrimaryaccountssummary() {
        return primaryaccountssummary;
    }

    //@JsonProperty("primaryaccountssummary")
    public void setPrimaryaccountssummary(Primaryaccountssummary primaryaccountssummary) {
        this.primaryaccountssummary = primaryaccountssummary;
    }

    public Accountssummary withPrimaryaccountssummary(Primaryaccountssummary primaryaccountssummary) {
        this.primaryaccountssummary = primaryaccountssummary;
        return this;
    }

    //@JsonProperty("secondaryaccountssummary")
    public Secondaryaccountssummary getSecondaryaccountssummary() {
        return secondaryaccountssummary;
    }

    //@JsonProperty("secondaryaccountssummary")
    public void setSecondaryaccountssummary(Secondaryaccountssummary secondaryaccountssummary) {
        this.secondaryaccountssummary = secondaryaccountssummary;
    }

    public Accountssummary withSecondaryaccountssummary(Secondaryaccountssummary secondaryaccountssummary) {
        this.secondaryaccountssummary = secondaryaccountssummary;
        return this;
    }

    //@JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    //@JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public Accountssummary withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
