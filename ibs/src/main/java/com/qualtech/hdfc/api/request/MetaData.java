
package com.qualtech.hdfc.api.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonIgnoreProperties(ignoreUnknown=true)
@Embeddable
public class MetaData implements Serializable{

    private String partnerId;
    private String plan;
    private String prn;
    private String action;
    private String channel;
    private String ipAddress;
    private String domain;
    private String encryptedAuthSalt;
    @Embedded
    private CustomerCommunication customerCommunication;
    @Embedded
    private PartnerData partnerData;
    @JsonIgnore
    @Transient
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getPrn() {
		return prn;
	}
	public void setPrn(String prn) {
		this.prn = prn;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getEncryptedAuthSalt() {
		return encryptedAuthSalt;
	}
	public void setEncryptedAuthSalt(String encryptedAuthSalt) {
		this.encryptedAuthSalt = encryptedAuthSalt;
	}
	public CustomerCommunication getCustomerCommunication() {
		return customerCommunication;
	}
	public void setCustomerCommunication(CustomerCommunication customerCommunication) {
		this.customerCommunication = customerCommunication;
	}
	public PartnerData getPartnerData() {
		return partnerData;
	}
	public void setPartnerData(PartnerData partnerData) {
		this.partnerData = partnerData;
	}
	public Map<String, Object> getAdditionalProperties() {
		return additionalProperties;
	}
	public void setAdditionalProperties(Map<String, Object> additionalProperties) {
		this.additionalProperties = additionalProperties;
	}

   
    
   
}
