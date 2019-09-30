package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_K_BankAccount_REQ")
public class BankAccountPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
    @Id
    @JsonIgnore
	private String uniqueid;
    @Column
    @JsonIgnore
	private String corelationid;
    @Column
	private String consent;
    @Column
	private String ifsc;
    @Column
	private String accountNumber;

	public String getConsent() {
		return consent;
	}

	public void setConsent(String consent) {
		this.consent = consent;
	}

	public String getIfsc() {
		return ifsc;
	}

	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getCorelationid() {
		return corelationid;
	}

	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}

	@Override
	public String toString() {
		return "BankAccountPayload [consent=" + consent + ", ifsc=" + ifsc
				+ ", accountNumber=" + accountNumber + "]";
	}

}
