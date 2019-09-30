
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
////@JsonPropertyOrder({
//    "newdelinqaccountinlastsixmonths",
//    "newaccountsinlastsixmonths",
//    "lengthofcredithistorymonth",
//    "averageaccountagemonth",
//    "lengthofcredithistoryyear",
//    "averageaccountageyear",
//    "inquiriesinlastsixmonths"
//})
public class Derivedattributes implements Serializable
{

    //@JsonProperty("newdelinqaccountinlastsixmonths")
    private Integer newdelinqaccountinlastsixmonths;
    //@JsonProperty("newaccountsinlastsixmonths")
    private Integer newaccountsinlastsixmonths;
    //@JsonProperty("lengthofcredithistorymonth")
    private Integer lengthofcredithistorymonth;
    //@JsonProperty("averageaccountagemonth")
    private Integer averageaccountagemonth;
    //@JsonProperty("lengthofcredithistoryyear")
    private Integer lengthofcredithistoryyear;
    //@JsonProperty("averageaccountageyear")
    private Integer averageaccountageyear;
    //@JsonProperty("inquiriesinlastsixmonths")
    private Integer inquiriesinlastsixmonths;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8727363924215678672L;

    //@JsonProperty("newdelinqaccountinlastsixmonths")
    public Integer getNewdelinqaccountinlastsixmonths() {
        return newdelinqaccountinlastsixmonths;
    }

    //@JsonProperty("newdelinqaccountinlastsixmonths")
    public void setNewdelinqaccountinlastsixmonths(Integer newdelinqaccountinlastsixmonths) {
        this.newdelinqaccountinlastsixmonths = newdelinqaccountinlastsixmonths;
    }

    public Derivedattributes withNewdelinqaccountinlastsixmonths(Integer newdelinqaccountinlastsixmonths) {
        this.newdelinqaccountinlastsixmonths = newdelinqaccountinlastsixmonths;
        return this;
    }

    //@JsonProperty("newaccountsinlastsixmonths")
    public Integer getNewaccountsinlastsixmonths() {
        return newaccountsinlastsixmonths;
    }

    //@JsonProperty("newaccountsinlastsixmonths")
    public void setNewaccountsinlastsixmonths(Integer newaccountsinlastsixmonths) {
        this.newaccountsinlastsixmonths = newaccountsinlastsixmonths;
    }

    public Derivedattributes withNewaccountsinlastsixmonths(Integer newaccountsinlastsixmonths) {
        this.newaccountsinlastsixmonths = newaccountsinlastsixmonths;
        return this;
    }

    //@JsonProperty("lengthofcredithistorymonth")
    public Integer getLengthofcredithistorymonth() {
        return lengthofcredithistorymonth;
    }

    //@JsonProperty("lengthofcredithistorymonth")
    public void setLengthofcredithistorymonth(Integer lengthofcredithistorymonth) {
        this.lengthofcredithistorymonth = lengthofcredithistorymonth;
    }

    public Derivedattributes withLengthofcredithistorymonth(Integer lengthofcredithistorymonth) {
        this.lengthofcredithistorymonth = lengthofcredithistorymonth;
        return this;
    }

    //@JsonProperty("averageaccountagemonth")
    public Integer getAverageaccountagemonth() {
        return averageaccountagemonth;
    }

    //@JsonProperty("averageaccountagemonth")
    public void setAverageaccountagemonth(Integer averageaccountagemonth) {
        this.averageaccountagemonth = averageaccountagemonth;
    }

    public Derivedattributes withAverageaccountagemonth(Integer averageaccountagemonth) {
        this.averageaccountagemonth = averageaccountagemonth;
        return this;
    }

    //@JsonProperty("lengthofcredithistoryyear")
    public Integer getLengthofcredithistoryyear() {
        return lengthofcredithistoryyear;
    }

    //@JsonProperty("lengthofcredithistoryyear")
    public void setLengthofcredithistoryyear(Integer lengthofcredithistoryyear) {
        this.lengthofcredithistoryyear = lengthofcredithistoryyear;
    }

    public Derivedattributes withLengthofcredithistoryyear(Integer lengthofcredithistoryyear) {
        this.lengthofcredithistoryyear = lengthofcredithistoryyear;
        return this;
    }

    //@JsonProperty("averageaccountageyear")
    public Integer getAverageaccountageyear() {
        return averageaccountageyear;
    }

    //@JsonProperty("averageaccountageyear")
    public void setAverageaccountageyear(Integer averageaccountageyear) {
        this.averageaccountageyear = averageaccountageyear;
    }

    public Derivedattributes withAverageaccountageyear(Integer averageaccountageyear) {
        this.averageaccountageyear = averageaccountageyear;
        return this;
    }

    //@JsonProperty("inquiriesinlastsixmonths")
    public Integer getInquiriesinlastsixmonths() {
        return inquiriesinlastsixmonths;
    }

    //@JsonProperty("inquiriesinlastsixmonths")
    public void setInquiriesinlastsixmonths(Integer inquiriesinlastsixmonths) {
        this.inquiriesinlastsixmonths = inquiriesinlastsixmonths;
    }

    public Derivedattributes withInquiriesinlastsixmonths(Integer inquiriesinlastsixmonths) {
        this.inquiriesinlastsixmonths = inquiriesinlastsixmonths;
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

    public Derivedattributes withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
