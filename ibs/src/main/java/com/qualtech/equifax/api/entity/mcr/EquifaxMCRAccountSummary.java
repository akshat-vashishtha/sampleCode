package com.qualtech.equifax.api.entity.mcr;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;

@Entity
@Table(name = "IB_EQ_M_ACNT_SMMRY")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxMCRAccountSummary implements Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4852822208343332017L;
	
	@Column
	private String total_balance_amount;
	@Column
	private String no_of_past_due_accounts;
	@Column
	private String total_monthly_payment_amount;
	@Column
	private String total_past_due;
	@Column
	private String no_of_active_account;
	@Column
	private String total_written_off_account;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_ACCNTSMMRY_SQC")
	@SequenceGenerator(name="IB_EQ_MCR_ACCNTSMMRY_SQC", sequenceName = "IB_EQ_MCR_ACCNTSMMRY_SQC", allocationSize = 1)
	private int account_id;
	@Column
	private String oldest_account;
	@Column
	private String no_of_zero_balance_account;
	@Column
	private String no_of_accounts;
	@Column
	private String total_sanction_amount;
	@Column
	private String total_credit_limit;
	@Column
	private String recent_account;
	@Column
	private String single_highest_sanction_amount;
	@Column
	private String total_high_credit;
	@Column
	private String average_open_balance;
	@Column
	private String single_highest_balance;
	@Column
	private String single_highest_credit;
	@Column(name = "NO_OF_WRITE_OFFS")
	private String noofwriteoffs;
	@OneToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxMcrAllDetaills equifaxmcrDetailslogs;

	public String getOldest_account() {
		return oldest_account;
	}

	public void setOldest_account(String oldest_account) {
		this.oldest_account = oldest_account;
	}

	public String getNo_of_zero_balance_account() {
		return no_of_zero_balance_account;
	}

	public void setNo_of_zero_balance_account(String no_of_zero_balance_account) {
		this.no_of_zero_balance_account = no_of_zero_balance_account;
	}

	public String getNo_of_accounts() {
		return no_of_accounts;
	}

	public void setNo_of_accounts(String no_of_accounts) {
		this.no_of_accounts = no_of_accounts;
	}

	public String getTotal_sanction_amount() {
		return total_sanction_amount;
	}

	public void setTotal_sanction_amount(String total_sanction_amount) {
		this.total_sanction_amount = total_sanction_amount;
	}

	public String getTotal_credit_limit() {
		return total_credit_limit;
	}

	public void setTotal_credit_limit(String total_credit_limit) {
		this.total_credit_limit = total_credit_limit;
	}

	public String getRecent_account() {
		return recent_account;
	}

	public void setRecent_account(String recent_account) {
		this.recent_account = recent_account;
	}

	public String getSingle_highest_sanction_amount() {
		return single_highest_sanction_amount;
	}

	public void setSingle_highest_sanction_amount(String single_highest_sanction_amount) {
		this.single_highest_sanction_amount = single_highest_sanction_amount;
	}

	public String getTotal_high_credit() {
		return total_high_credit;
	}

	public void setTotal_high_credit(String total_high_credit) {
		this.total_high_credit = total_high_credit;
	}

	public String getAverage_open_balance() {
		return average_open_balance;
	}

	public void setAverage_open_balance(String average_open_balance) {
		this.average_open_balance = average_open_balance;
	}

	public String getSingle_highest_balance() {
		return single_highest_balance;
	}

	public void setSingle_highest_balance(String single_highest_balance) {
		this.single_highest_balance = single_highest_balance;
	}

	public String getSingle_highest_credit() {
		return single_highest_credit;
	}

	public void setSingle_highest_credit(String single_highest_credit) {
		this.single_highest_credit = single_highest_credit;
	}

	public String getNoofwriteoffs() {
		return noofwriteoffs;
	}

	public void setNoofwriteoffs(String noofwriteoffs) {
		this.noofwriteoffs = noofwriteoffs;
	}

	public EquifaxMcrAllDetaills getEquifaxmcrDetailslogs() {
		return equifaxmcrDetailslogs;
	}

	public void setEquifaxmcrDetailslogs(EquifaxMcrAllDetaills equifaxmcrDetailslogs) {
		this.equifaxmcrDetailslogs = equifaxmcrDetailslogs;
	}

	public String getTotal_balance_amount() {
		return total_balance_amount;
	}

	public void setTotal_balance_amount(String total_balance_amount) {
		this.total_balance_amount = total_balance_amount;
	}

	public String getNo_of_past_due_accounts() {
		return no_of_past_due_accounts;
	}

	public void setNo_of_past_due_accounts(String no_of_past_due_accounts) {
		this.no_of_past_due_accounts = no_of_past_due_accounts;
	}

	public String getTotal_monthly_payment_amount() {
		return total_monthly_payment_amount;
	}

	public void setTotal_monthly_payment_amount(String total_monthly_payment_amount) {
		this.total_monthly_payment_amount = total_monthly_payment_amount;
	}

	public String getTotal_past_due() {
		return total_past_due;
	}

	public void setTotal_past_due(String total_past_due) {
		this.total_past_due = total_past_due;
	}

	public String getNo_of_active_account() {
		return no_of_active_account;
	}

	public void setNo_of_active_account(String no_of_active_account) {
		this.no_of_active_account = no_of_active_account;
	}

	public String getTotal_written_off_account() {
		return total_written_off_account;
	}

	public void setTotal_written_off_account(String total_written_off_account) {
		this.total_written_off_account = total_written_off_account;
	}

	public int getAccount_id() {
		return account_id;
	}

	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

}
