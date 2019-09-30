package com.qualtech.crif.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="IB_CRF_LOAN_DETAILS")
public class CriffLoanDetails 
{
	 private String current_balance;
	 private String	dispursed_date;
	 private String dispurse_amount;
	 private String over_due_amount;
	 private String acct_type;
	 private String security_details;
	 private String write_off_amount;
	 private String linked_accounts;
	 private String interest_rate;
	 private String account_status;
	 private String account_number;
	 private String credit_guaranator;
	 private String owner_ship_ind;
	 private String matched_type;
	 private String combined_payment_history;
	 private String date_reported;
	 private Long loan_detail_id;
	 private  CriffDetailLogs crifdetaillogs;
	
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	public CriffDetailLogs getCrifdetaillogs() {
		return crifdetaillogs;
	}
	public void setCrifdetaillogs(CriffDetailLogs crifdetaillogs) {
		this.crifdetaillogs = crifdetaillogs;
	}
	
	@Column(name="CURRENT_BALANCE")
    public String getCurrent_balance() {
		return current_balance;
	}
	public void setCurrent_balance(String current_balance) {
		this.current_balance = current_balance;
	}
	
	@Column(name="DISPURSED_DATE")
	public String getDispursed_date() {
		return dispursed_date;
	}
	public void setDispursed_date(String dispursed_date) {
		this.dispursed_date = dispursed_date;
	}
	@Column(name="DISPURSE_AMOUNT")
	public String getDispurse_amount() {
		return dispurse_amount;
	}
	public void setDispurse_amount(String dispurse_amount) {
		this.dispurse_amount = dispurse_amount;
	}
	@Column(name="OVER_DUE_AMOUNT")
	public String getOver_due_amount() {
		return over_due_amount;
	}
	public void setOver_due_amount(String over_due_amount) {
		this.over_due_amount = over_due_amount;
	}
	@Column(name="ACCT_TYPE")
	public String getAcct_type() {
		return acct_type;
	}
	public void setAcct_type(String acct_type) {
		this.acct_type = acct_type;
	}
	@Column(name="SECURITY_DETAILS")
	public String getSecurity_details() {
		return security_details;
	}
	public void setSecurity_details(String security_details) {
		this.security_details = security_details;
	}
	@Column(name="WRITE_OFF_AMOUNT")
	public String getWrite_off_amount() {
		return write_off_amount;
	}
	public void setWrite_off_amount(String write_off_amount) {
		this.write_off_amount = write_off_amount;
	}
	@Column(name="LINKED_ACCOUNTS")
	public String getLinked_accounts() {
		return linked_accounts;
	}
	public void setLinked_accounts(String linked_accounts) {
		this.linked_accounts = linked_accounts;
	}
	@Column(name="INTEREST_RATE")
	public String getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(String interest_rate) {
		this.interest_rate = interest_rate;
	}
	@Column(name="ACCOUNT_STATUS")
	public String getAccount_status() {
		return account_status;
	}
	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}
	@Column(name="ACCOUNT_NUMBER")
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	@Column(name="CREDIT_GUARANATOR")
	public String getCredit_guaranator() {
		return credit_guaranator;
	}
	public void setCredit_guaranator(String credit_guaranator) {
		this.credit_guaranator = credit_guaranator;
	}
	@Column(name="OWNER_SHIP_IND")
	public String getOwner_ship_ind() {
		return owner_ship_ind;
	}
	public void setOwner_ship_ind(String owner_ship_ind) {
		this.owner_ship_ind = owner_ship_ind;
	}
	@Column(name="MATCHED_TYPE")
	public String getMatched_type() {
		return matched_type;
	}
	public void setMatched_type(String matched_type) {
		this.matched_type = matched_type;
	}
	@Lob
	@Column(name="COMBINED_PAYMENT_HISTORY")
	public String getCombined_payment_history() {
		return combined_payment_history;
	}
	public void setCombined_payment_history(String combined_payment_history) {
		this.combined_payment_history = combined_payment_history;
	}
	@Column(name="DATE_REPORTED")
	public String getDate_reported() {
		return date_reported;
	}
	public void setDate_reported(String date_reported) {
		this.date_reported = date_reported;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_LOANDETAILS_SQC")
	@SequenceGenerator(name="IB_CRF_LOANDETAILS_SQC", sequenceName = "IB_CRF_LOANDETAILS_SQC", allocationSize = 1)
	@Column(name="LOAN_DETAIL_ID")
	public Long getLoan_detail_id() {
		return loan_detail_id;
	}
	public void setLoan_detail_id(Long loan_detail_id) {
		this.loan_detail_id = loan_detail_id;
	}
	@Override
	public String toString() {
		return "CriffLoanDetails [current_balance=" + current_balance + ", dispursed_date=" + dispursed_date
				+ ", dispurse_amount=" + dispurse_amount + ", over_due_amount=" + over_due_amount + ", acct_type="
				+ acct_type + ", security_details=" + security_details + ", write_off_amount=" + write_off_amount
				+ ", linked_accounts=" + linked_accounts + ", interest_rate=" + interest_rate + ", account_status="
				+ account_status + ", account_number=" + account_number + ", credit_guaranator=" + credit_guaranator
				+ ", owner_ship_ind=" + owner_ship_ind + ", matched_type=" + matched_type
				+ ", combined_payment_history=" + combined_payment_history + ", date_reported=" + date_reported
				+ ", loan_detail_id=" + loan_detail_id + ", crifdetaillogs=" + crifdetaillogs + "]";
	}

 
 
	
}
