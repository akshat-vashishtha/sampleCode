package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_K_BANKACCOUNT_RES")
public class BankAccountResult implements Serializable{

	private static final long serialVersionUID = -8251784410816419882L;
	@Id
	@JsonIgnore
	private String	   uniqueid;
	@Column
	@JsonIgnore
	private String   corelationid;	
	@Column
	private String bankTxnStatus;
	@Column
	private String accountNumber;
	@Column
	private String ifsc;
	@Column
	private String accountName;
	@Column
	private String bankResponse;
	
	
	
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
	public String getBankTxnStatus() {
		return bankTxnStatus;
	}
	public void setBankTxnStatus(String bankTxnStatus) {
		this.bankTxnStatus = bankTxnStatus;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getBankResponse() {
		return bankResponse;
	}
	public void setBankResponse(String bankResponse) {
		this.bankResponse = bankResponse;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "BankAccountResult [bankTxnStatus=" + bankTxnStatus + ", accountNumber=" + accountNumber + ", ifsc="
				+ ifsc + ", accountName=" + accountName + ", bankResponse=" + bankResponse + "]";
	}
	
	
}
