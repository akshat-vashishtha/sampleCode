
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
//    "primarynumberofaccounts",
//    "primarysanctionedamount",
//    "primaryuntaggednumberofaccounts",
//    "primarysecurednumberofaccounts",
//    "primarycurrentbalance",
//    "primaryactivenumberofaccounts",
//    "primarydisbursedamount",
//    "primaryunsecurednumberofaccounts",
//    "primaryoverduenumberofaccounts"
//})
public class Primaryaccountssummary implements Serializable
{

    //@JsonProperty("primarynumberofaccounts")
    private Integer primarynumberofaccounts;
    //@JsonProperty("primarysanctionedamount")
    private Integer primarysanctionedamount;
    //@JsonProperty("primaryuntaggednumberofaccounts")
    private Integer primaryuntaggednumberofaccounts;
    //@JsonProperty("primarysecurednumberofaccounts")
    private Integer primarysecurednumberofaccounts;
    //@JsonProperty("primarycurrentbalance")
    private Integer primarycurrentbalance;
    //@JsonProperty("primaryactivenumberofaccounts")
    private Integer primaryactivenumberofaccounts;
    //@JsonProperty("primarydisbursedamount")
    private Integer primarydisbursedamount;
    //@JsonProperty("primaryunsecurednumberofaccounts")
    private Integer primaryunsecurednumberofaccounts;
    //@JsonProperty("primaryoverduenumberofaccounts")
    private Integer primaryoverduenumberofaccounts;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -3932186734381509415L;

    //@JsonProperty("primarynumberofaccounts")
    public Integer getPrimarynumberofaccounts() {
        return primarynumberofaccounts;
    }

    //@JsonProperty("primarynumberofaccounts")
    public void setPrimarynumberofaccounts(Integer primarynumberofaccounts) {
        this.primarynumberofaccounts = primarynumberofaccounts;
    }

    public Primaryaccountssummary withPrimarynumberofaccounts(Integer primarynumberofaccounts) {
        this.primarynumberofaccounts = primarynumberofaccounts;
        return this;
    }

    //@JsonProperty("primarysanctionedamount")
    public Integer getPrimarysanctionedamount() {
        return primarysanctionedamount;
    }

    //@JsonProperty("primarysanctionedamount")
    public void setPrimarysanctionedamount(Integer primarysanctionedamount) {
        this.primarysanctionedamount = primarysanctionedamount;
    }

    public Primaryaccountssummary withPrimarysanctionedamount(Integer primarysanctionedamount) {
        this.primarysanctionedamount = primarysanctionedamount;
        return this;
    }

    //@JsonProperty("primaryuntaggednumberofaccounts")
    public Integer getPrimaryuntaggednumberofaccounts() {
        return primaryuntaggednumberofaccounts;
    }

    //@JsonProperty("primaryuntaggednumberofaccounts")
    public void setPrimaryuntaggednumberofaccounts(Integer primaryuntaggednumberofaccounts) {
        this.primaryuntaggednumberofaccounts = primaryuntaggednumberofaccounts;
    }

    public Primaryaccountssummary withPrimaryuntaggednumberofaccounts(Integer primaryuntaggednumberofaccounts) {
        this.primaryuntaggednumberofaccounts = primaryuntaggednumberofaccounts;
        return this;
    }

    //@JsonProperty("primarysecurednumberofaccounts")
    public Integer getPrimarysecurednumberofaccounts() {
        return primarysecurednumberofaccounts;
    }

    //@JsonProperty("primarysecurednumberofaccounts")
    public void setPrimarysecurednumberofaccounts(Integer primarysecurednumberofaccounts) {
        this.primarysecurednumberofaccounts = primarysecurednumberofaccounts;
    }

    public Primaryaccountssummary withPrimarysecurednumberofaccounts(Integer primarysecurednumberofaccounts) {
        this.primarysecurednumberofaccounts = primarysecurednumberofaccounts;
        return this;
    }

    //@JsonProperty("primarycurrentbalance")
    public Integer getPrimarycurrentbalance() {
        return primarycurrentbalance;
    }

    //@JsonProperty("primarycurrentbalance")
    public void setPrimarycurrentbalance(Integer primarycurrentbalance) {
        this.primarycurrentbalance = primarycurrentbalance;
    }

    public Primaryaccountssummary withPrimarycurrentbalance(Integer primarycurrentbalance) {
        this.primarycurrentbalance = primarycurrentbalance;
        return this;
    }

    //@JsonProperty("primaryactivenumberofaccounts")
    public Integer getPrimaryactivenumberofaccounts() {
        return primaryactivenumberofaccounts;
    }

    //@JsonProperty("primaryactivenumberofaccounts")
    public void setPrimaryactivenumberofaccounts(Integer primaryactivenumberofaccounts) {
        this.primaryactivenumberofaccounts = primaryactivenumberofaccounts;
    }

    public Primaryaccountssummary withPrimaryactivenumberofaccounts(Integer primaryactivenumberofaccounts) {
        this.primaryactivenumberofaccounts = primaryactivenumberofaccounts;
        return this;
    }

    //@JsonProperty("primarydisbursedamount")
    public Integer getPrimarydisbursedamount() {
        return primarydisbursedamount;
    }

    //@JsonProperty("primarydisbursedamount")
    public void setPrimarydisbursedamount(Integer primarydisbursedamount) {
        this.primarydisbursedamount = primarydisbursedamount;
    }

    public Primaryaccountssummary withPrimarydisbursedamount(Integer primarydisbursedamount) {
        this.primarydisbursedamount = primarydisbursedamount;
        return this;
    }

    //@JsonProperty("primaryunsecurednumberofaccounts")
    public Integer getPrimaryunsecurednumberofaccounts() {
        return primaryunsecurednumberofaccounts;
    }

    //@JsonProperty("primaryunsecurednumberofaccounts")
    public void setPrimaryunsecurednumberofaccounts(Integer primaryunsecurednumberofaccounts) {
        this.primaryunsecurednumberofaccounts = primaryunsecurednumberofaccounts;
    }

    public Primaryaccountssummary withPrimaryunsecurednumberofaccounts(Integer primaryunsecurednumberofaccounts) {
        this.primaryunsecurednumberofaccounts = primaryunsecurednumberofaccounts;
        return this;
    }

    //@JsonProperty("primaryoverduenumberofaccounts")
    public Integer getPrimaryoverduenumberofaccounts() {
        return primaryoverduenumberofaccounts;
    }

    //@JsonProperty("primaryoverduenumberofaccounts")
    public void setPrimaryoverduenumberofaccounts(Integer primaryoverduenumberofaccounts) {
        this.primaryoverduenumberofaccounts = primaryoverduenumberofaccounts;
    }

    public Primaryaccountssummary withPrimaryoverduenumberofaccounts(Integer primaryoverduenumberofaccounts) {
        this.primaryoverduenumberofaccounts = primaryoverduenumberofaccounts;
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

    public Primaryaccountssummary withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
