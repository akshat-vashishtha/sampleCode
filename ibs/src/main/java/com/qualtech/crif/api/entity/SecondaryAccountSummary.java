package com.qualtech.crif.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="IB_CRF_SEC_ACNT_SMRY")
public class SecondaryAccountSummary {

	private String number_of_accounts;
	private String sanctioned_amount;
	private String untagged_accounts;
	private String secured_accounts;
	private String current_balance;
	private String active_accounts;
	private String disbursed_amount;
	private String unsecured_accounts;
	private String overdue_accounts;
	private Long secondary_account_id;
	private CriffDetailLogs crifdetaillogs;

	
	@OneToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	public CriffDetailLogs getCrifdetaillogs() {
		return crifdetaillogs;
	}
	public void setCrifdetaillogs(CriffDetailLogs crifdetaillogs) {
		this.crifdetaillogs = crifdetaillogs;
	}
	
	@Column(name="NUMBER_OF_ACCOUNTS")
	public String getNumber_of_accounts() {
		return number_of_accounts;
	}
	
	public void setNumber_of_accounts(String number_of_accounts) {
		this.number_of_accounts = number_of_accounts;
	}
	@Column(name="SANCTIONED_AMOUNT")
	public String getSanctioned_amount() {
		return sanctioned_amount;
	}
	public void setSanctioned_amount(String sanctioned_amount) {
		this.sanctioned_amount = sanctioned_amount;
	}
	@Column(name="UNTAGGED_ACCOUNTS")
	public String getUntagged_accounts() {
		return untagged_accounts;
	}
	public void setUntagged_accounts(String untagged_accounts) {
		this.untagged_accounts = untagged_accounts;
	
	}
	@Column(name="SECURED_ACCOUNTS")
	public String getSecured_accounts() {
		return secured_accounts;
	}
	public void setSecured_accounts(String secured_accounts) {
		this.secured_accounts = secured_accounts;
	}
	@Column(name="CURRENT_BALANCE")
	public String getCurrent_balance() {
		return current_balance;
	}
	public void setCurrent_balance(String current_balance) {
		this.current_balance = current_balance;
	}
	@Column(name="ACTIVE_ACCOUNTS")
	public String getActive_accounts() {
		return active_accounts;
	}
	public void setActive_accounts(String active_accounts) {
		this.active_accounts = active_accounts;
	}
	@Column(name="DISBURSED_AMOUNT")
	public String getDisbursed_amount() {
		return disbursed_amount;
	}
	public void setDisbursed_amount(String disbursed_amount) {
		this.disbursed_amount = disbursed_amount;
	}
	@Column(name="UNSECURED_ACCOUNTS")
	public String getUnsecured_accounts() {
		return unsecured_accounts;
	}
	public void setUnsecured_accounts(String unsecured_accounts) {
		this.unsecured_accounts = unsecured_accounts;
	}
	@Column(name="OVERDUE_ACCOUNTS")
	public String getOverdue_accounts() {
		return overdue_accounts;
	}
	public void setOverdue_accounts(String overdue_accounts) {
		this.overdue_accounts = overdue_accounts;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_PRIMARYACCOUNT_SQC")
	@SequenceGenerator(name="IB_CRF_PRIMARYACCOUNT_SQC", sequenceName = "IB_CRF_PRIMARYACCOUNT_SQC", allocationSize = 1)
	@Column(name="SECONDARY_ACCOUNT_ID")
	public Long getSecondary_account_id() {
		return secondary_account_id;
	}
	public void setSecondary_account_id(Long primary_account_id) {
		this.secondary_account_id = primary_account_id;
	}	
}
