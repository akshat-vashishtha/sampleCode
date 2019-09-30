package com.qualtech.cibilv2.api.response;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Account implements Serializable{

	private static final long serialVersionUID = -3626997711579003323L;
	
	private Amount amount;
	private AccountInformation accountInformation;
	private Borrower borrower;
	@JsonProperty("DPD")
	private Dpd dpd;
	private Dates dates;
	private Status status;
	
	public Amount getAmount() {
		return amount;
	}
	public void setAmount(Amount amount) {
		this.amount = amount;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Dates getDates() {
		return dates;
	}
	public void setDates(Dates dates) {
		this.dates = dates;
	}
	public Borrower getBorrower() {
		return borrower;
	}
	public void setBorrower(Borrower borrower) {
		this.borrower = borrower;
	}
	
	public Dpd getDpd() {
		return dpd;
	}
	public void setDpd(Dpd dpd) {
		this.dpd = dpd;
	}
	public AccountInformation getAccountInformation() {
		return accountInformation;
	}
	public void setAccountInformation(AccountInformation accountInformation) {
		this.accountInformation = accountInformation;
	}
	
}
