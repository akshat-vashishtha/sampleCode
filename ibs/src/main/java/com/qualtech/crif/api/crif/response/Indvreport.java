
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
//    "alerts",
//    "statusdetails",
//    "request",
//    "grpresponses",
//    "scores",
//    "indvresponses",
//    "inquiryhistory",
//    "personalinfovariation",
//    "responses",
//    "header",
//    "secondarymatches",
//    "accountssummary"
//})
public class Indvreport implements Serializable
{

    //@JsonProperty("alerts")
    private Alerts alerts;
    //@JsonProperty("statusdetails")
    private Statusdetails statusdetails;
    //@JsonProperty("request")
    private Request request;
    //@JsonProperty("grpresponses")
    private Grpresponses grpresponses;
    //@JsonProperty("scores")
    private Scores scores;
    //@JsonProperty("indvresponses")
    private Indvresponses indvresponses;
    //@JsonProperty("inquiryhistory")
    private String inquiryhistory;
    //@JsonProperty("personalinfovariation")
    private Personalinfovariation personalinfovariation;
    //@JsonProperty("responses")
    private Responses responses;
    //@JsonProperty("header")
    private Header header;
    //@JsonProperty("secondarymatches")
    private String secondarymatches;
    //@JsonProperty("accountssummary")
    private Accountssummary accountssummary;
//    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8251095776154933086L;

    //@JsonProperty("alerts")
    public Alerts getAlerts() {
        return alerts;
    }

    //@JsonProperty("alerts")
    public void setAlerts(Alerts alerts) {
        this.alerts = alerts;
    }

    public Indvreport withAlerts(Alerts alerts) {
        this.alerts = alerts;
        return this;
    }

    //@JsonProperty("statusdetails")
    public Statusdetails getStatusdetails() {
        return statusdetails;
    }

    //@JsonProperty("statusdetails")
    public void setStatusdetails(Statusdetails statusdetails) {
        this.statusdetails = statusdetails;
    }

    public Indvreport withStatusdetails(Statusdetails statusdetails) {
        this.statusdetails = statusdetails;
        return this;
    }

    //@JsonProperty("request")
    public Request getRequest() {
        return request;
    }

    //@JsonProperty("request")
    public void setRequest(Request request) {
        this.request = request;
    }

    public Indvreport withRequest(Request request) {
        this.request = request;
        return this;
    }

    //@JsonProperty("grpresponses")
    public Grpresponses getGrpresponses() {
        return grpresponses;
    }

    //@JsonProperty("grpresponses")
    public void setGrpresponses(Grpresponses grpresponses) {
        this.grpresponses = grpresponses;
    }

    public Indvreport withGrpresponses(Grpresponses grpresponses) {
        this.grpresponses = grpresponses;
        return this;
    }

    //@JsonProperty("scores")
    public Scores getScores() {
        return scores;
    }

    //@JsonProperty("scores")
    public void setScores(Scores scores) {
        this.scores = scores;
    }

    public Indvreport withScores(Scores scores) {
        this.scores = scores;
        return this;
    }

    //@JsonProperty("indvresponses")
    public Indvresponses getIndvresponses() {
        return indvresponses;
    }

    //@JsonProperty("indvresponses")
    public void setIndvresponses(Indvresponses indvresponses) {
        this.indvresponses = indvresponses;
    }

    public Indvreport withIndvresponses(Indvresponses indvresponses) {
        this.indvresponses = indvresponses;
        return this;
    }

    //@JsonProperty("inquiryhistory")
    public String getInquiryhistory() {
        return inquiryhistory;
    }

    //@JsonProperty("inquiryhistory")
    public void setInquiryhistory(String inquiryhistory) {
        this.inquiryhistory = inquiryhistory;
    }

    public Indvreport withInquiryhistory(String inquiryhistory) {
        this.inquiryhistory = inquiryhistory;
        return this;
    }

    //@JsonProperty("personalinfovariation")
    public Personalinfovariation getPersonalinfovariation() {
        return personalinfovariation;
    }

    //@JsonProperty("personalinfovariation")
    public void setPersonalinfovariation(Personalinfovariation personalinfovariation) {
        this.personalinfovariation = personalinfovariation;
    }

    public Indvreport withPersonalinfovariation(Personalinfovariation personalinfovariation) {
        this.personalinfovariation = personalinfovariation;
        return this;
    }

    //@JsonProperty("responses")
    public Responses getResponses() {
        return responses;
    }

    //@JsonProperty("responses")
    public void setResponses(Responses responses) {
        this.responses = responses;
    }

    public Indvreport withResponses(Responses responses) {
        this.responses = responses;
        return this;
    }

    //@JsonProperty("header")
    public Header getHeader() {
        return header;
    }

    //@JsonProperty("header")
    public void setHeader(Header header) {
        this.header = header;
    }

    public Indvreport withHeader(Header header) {
        this.header = header;
        return this;
    }

    //@JsonProperty("secondarymatches")
    public String getSecondarymatches() {
        return secondarymatches;
    }

    //@JsonProperty("secondarymatches")
    public void setSecondarymatches(String secondarymatches) {
        this.secondarymatches = secondarymatches;
    }

    public Indvreport withSecondarymatches(String secondarymatches) {
        this.secondarymatches = secondarymatches;
        return this;
    }

    //@JsonProperty("accountssummary")
    public Accountssummary getAccountssummary() {
        return accountssummary;
    }

    ////@JsonProperty("accountssummary")
    public void setAccountssummary(Accountssummary accountssummary) {
        this.accountssummary = accountssummary;
    }

    public Indvreport withAccountssummary(Accountssummary accountssummary) {
        this.accountssummary = accountssummary;
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

    public Indvreport withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
