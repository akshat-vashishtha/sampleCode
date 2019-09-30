
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
//    "secondaryuntaggednumberofaccounts",
//    "secondarydisbursedamount",
//    "secondaryactivenumberofaccounts",
//    "secondarycurrentbalance",
//    "secondarynumberofaccounts",
//    "secondaryoverduenumberofaccounts",
//    "secondarysecurednumberofaccounts",
//    "secondarysanctionedamount",
//    "secondaryunsecurednumberofaccounts"
//})
public class Secondaryaccountssummary implements Serializable
{

    //@JsonProperty("secondaryuntaggednumberofaccounts")
    private Integer secondaryuntaggednumberofaccounts;
    //@JsonProperty("secondarydisbursedamount")
    private Integer secondarydisbursedamount;
    //@JsonProperty("secondaryactivenumberofaccounts")
    private Integer secondaryactivenumberofaccounts;
    //@JsonProperty("secondarycurrentbalance")
    private Integer secondarycurrentbalance;
    //@JsonProperty("secondarynumberofaccounts")
    private Integer secondarynumberofaccounts;
    //@JsonProperty("secondaryoverduenumberofaccounts")
    private Integer secondaryoverduenumberofaccounts;
    //@JsonProperty("secondarysecurednumberofaccounts")
    private Integer secondarysecurednumberofaccounts;
    //@JsonProperty("secondarysanctionedamount")
    private Integer secondarysanctionedamount;
    //@JsonProperty("secondaryunsecurednumberofaccounts")
    private Integer secondaryunsecurednumberofaccounts;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1180144240536334394L;

    //@JsonProperty("secondaryuntaggednumberofaccounts")
    public Integer getSecondaryuntaggednumberofaccounts() {
        return secondaryuntaggednumberofaccounts;
    }

    //@JsonProperty("secondaryuntaggednumberofaccounts")
    public void setSecondaryuntaggednumberofaccounts(Integer secondaryuntaggednumberofaccounts) {
        this.secondaryuntaggednumberofaccounts = secondaryuntaggednumberofaccounts;
    }

    public Secondaryaccountssummary withSecondaryuntaggednumberofaccounts(Integer secondaryuntaggednumberofaccounts) {
        this.secondaryuntaggednumberofaccounts = secondaryuntaggednumberofaccounts;
        return this;
    }

    //@JsonProperty("secondarydisbursedamount")
    public Integer getSecondarydisbursedamount() {
        return secondarydisbursedamount;
    }

    //@JsonProperty("secondarydisbursedamount")
    public void setSecondarydisbursedamount(Integer secondarydisbursedamount) {
        this.secondarydisbursedamount = secondarydisbursedamount;
    }

    public Secondaryaccountssummary withSecondarydisbursedamount(Integer secondarydisbursedamount) {
        this.secondarydisbursedamount = secondarydisbursedamount;
        return this;
    }

    //@JsonProperty("secondaryactivenumberofaccounts")
    public Integer getSecondaryactivenumberofaccounts() {
        return secondaryactivenumberofaccounts;
    }

    //@JsonProperty("secondaryactivenumberofaccounts")
    public void setSecondaryactivenumberofaccounts(Integer secondaryactivenumberofaccounts) {
        this.secondaryactivenumberofaccounts = secondaryactivenumberofaccounts;
    }

    public Secondaryaccountssummary withSecondaryactivenumberofaccounts(Integer secondaryactivenumberofaccounts) {
        this.secondaryactivenumberofaccounts = secondaryactivenumberofaccounts;
        return this;
    }

    //@JsonProperty("secondarycurrentbalance")
    public Integer getSecondarycurrentbalance() {
        return secondarycurrentbalance;
    }

    //@JsonProperty("secondarycurrentbalance")
    public void setSecondarycurrentbalance(Integer secondarycurrentbalance) {
        this.secondarycurrentbalance = secondarycurrentbalance;
    }

    public Secondaryaccountssummary withSecondarycurrentbalance(Integer secondarycurrentbalance) {
        this.secondarycurrentbalance = secondarycurrentbalance;
        return this;
    }

    //@JsonProperty("secondarynumberofaccounts")
    public Integer getSecondarynumberofaccounts() {
        return secondarynumberofaccounts;
    }

    //@JsonProperty("secondarynumberofaccounts")
    public void setSecondarynumberofaccounts(Integer secondarynumberofaccounts) {
        this.secondarynumberofaccounts = secondarynumberofaccounts;
    }

    public Secondaryaccountssummary withSecondarynumberofaccounts(Integer secondarynumberofaccounts) {
        this.secondarynumberofaccounts = secondarynumberofaccounts;
        return this;
    }

    //@JsonProperty("secondaryoverduenumberofaccounts")
    public Integer getSecondaryoverduenumberofaccounts() {
        return secondaryoverduenumberofaccounts;
    }

    //@JsonProperty("secondaryoverduenumberofaccounts")
    public void setSecondaryoverduenumberofaccounts(Integer secondaryoverduenumberofaccounts) {
        this.secondaryoverduenumberofaccounts = secondaryoverduenumberofaccounts;
    }

    public Secondaryaccountssummary withSecondaryoverduenumberofaccounts(Integer secondaryoverduenumberofaccounts) {
        this.secondaryoverduenumberofaccounts = secondaryoverduenumberofaccounts;
        return this;
    }

    //@JsonProperty("secondarysecurednumberofaccounts")
    public Integer getSecondarysecurednumberofaccounts() {
        return secondarysecurednumberofaccounts;
    }

    //@JsonProperty("secondarysecurednumberofaccounts")
    public void setSecondarysecurednumberofaccounts(Integer secondarysecurednumberofaccounts) {
        this.secondarysecurednumberofaccounts = secondarysecurednumberofaccounts;
    }

    public Secondaryaccountssummary withSecondarysecurednumberofaccounts(Integer secondarysecurednumberofaccounts) {
        this.secondarysecurednumberofaccounts = secondarysecurednumberofaccounts;
        return this;
    }

    //@JsonProperty("secondarysanctionedamount")
    public Integer getSecondarysanctionedamount() {
        return secondarysanctionedamount;
    }

    //@JsonProperty("secondarysanctionedamount")
    public void setSecondarysanctionedamount(Integer secondarysanctionedamount) {
        this.secondarysanctionedamount = secondarysanctionedamount;
    }

    public Secondaryaccountssummary withSecondarysanctionedamount(Integer secondarysanctionedamount) {
        this.secondarysanctionedamount = secondarysanctionedamount;
        return this;
    }

    //@JsonProperty("secondaryunsecurednumberofaccounts")
    public Integer getSecondaryunsecurednumberofaccounts() {
        return secondaryunsecurednumberofaccounts;
    }

    //@JsonProperty("secondaryunsecurednumberofaccounts")
    public void setSecondaryunsecurednumberofaccounts(Integer secondaryunsecurednumberofaccounts) {
        this.secondaryunsecurednumberofaccounts = secondaryunsecurednumberofaccounts;
    }

    public Secondaryaccountssummary withSecondaryunsecurednumberofaccounts(Integer secondaryunsecurednumberofaccounts) {
        this.secondaryunsecurednumberofaccounts = secondaryunsecurednumberofaccounts;
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

    public Secondaryaccountssummary withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
