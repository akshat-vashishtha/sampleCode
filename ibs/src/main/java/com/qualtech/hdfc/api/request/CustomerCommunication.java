
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
    "sendMIFToEmail",
    "sendMIFToSms",
    "sendCOIToEmail",
    "sendCOIUrlToSms",
    "sendCOIToSms"
})
@JsonIgnoreProperties(ignoreUnknown=true)
@Embeddable
public class CustomerCommunication {

    @JsonProperty("sendMIFToEmail")
    private String sendMIFToEmail;
    @JsonProperty("sendMIFToSms")
    private String sendMIFToSms;
    @JsonProperty("sendCOIToEmail")
    private String sendCOIToEmail;
    @JsonProperty("sendCOIUrlToSms")
    private String sendCOIUrlToSms;
    @JsonProperty("sendCOIToSms")
    private String sendCOIToSms;
    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public CustomerCommunication() {
    }

    /**
     * 
     * @param sendMIFToEmail
     * @param sendCOIUrlToSms
     * @param sendMIFToSms
     * @param sendCOIToSms
     * @param sendCOIToEmail
     */
    public CustomerCommunication(String sendMIFToEmail, String sendMIFToSms, String sendCOIToEmail, String sendCOIUrlToSms, String sendCOIToSms) {
        super();
        this.sendMIFToEmail = sendMIFToEmail;
        this.sendMIFToSms = sendMIFToSms;
        this.sendCOIToEmail = sendCOIToEmail;
        this.sendCOIUrlToSms = sendCOIUrlToSms;
        this.sendCOIToSms = sendCOIToSms;
    }

    @JsonProperty("sendMIFToEmail")
    public String getSendMIFToEmail() {
        return sendMIFToEmail;
    }

    @JsonProperty("sendMIFToEmail")
    public void setSendMIFToEmail(String sendMIFToEmail) {
        this.sendMIFToEmail = sendMIFToEmail;
    }

    @JsonProperty("sendMIFToSms")
    public String getSendMIFToSms() {
        return sendMIFToSms;
    }

    @JsonProperty("sendMIFToSms")
    public void setSendMIFToSms(String sendMIFToSms) {
        this.sendMIFToSms = sendMIFToSms;
    }

    @JsonProperty("sendCOIToEmail")
    public String getSendCOIToEmail() {
        return sendCOIToEmail;
    }

    @JsonProperty("sendCOIToEmail")
    public void setSendCOIToEmail(String sendCOIToEmail) {
        this.sendCOIToEmail = sendCOIToEmail;
    }

    @JsonProperty("sendCOIUrlToSms")
    public String getSendCOIUrlToSms() {
        return sendCOIUrlToSms;
    }

    @JsonProperty("sendCOIUrlToSms")
    public void setSendCOIUrlToSms(String sendCOIUrlToSms) {
        this.sendCOIUrlToSms = sendCOIUrlToSms;
    }

    @JsonProperty("sendCOIToSms")
    public String getSendCOIToSms() {
        return sendCOIToSms;
    }

    @JsonProperty("sendCOIToSms")
    public void setSendCOIToSms(String sendCOIToSms) {
        this.sendCOIToSms = sendCOIToSms;
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
