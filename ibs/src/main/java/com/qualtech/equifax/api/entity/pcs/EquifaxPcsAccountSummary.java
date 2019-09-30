package com.qualtech.equifax.api.entity.pcs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;

@Entity
@Table(name="IB_EQ_P_ACNT_SMRY")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxPcsAccountSummary 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_PCS_ACCOUNTSUMMARY_SQC")
	@SequenceGenerator(name="IB_EQ_PCS_ACCOUNTSUMMARY_SQC", sequenceName = "IB_EQ_PCS_ACCOUNTSUMMARY_SQC", allocationSize = 1)
	@Column(name="ACCOUNT_ID")
		  private Long	account_id;
		  @Column(name="OLDEST_ACCOUNT")
		  private String	oldest_account;
		  @Column(name="NO_OF_ZERO_BALANCE_ACCOUNT")
		  private String	no_of_zero_balance_account;
		  @Column(name="NO_OF_ACCOUNTS")
		  private String    no_of_accounts;
		  @Column(name="TOTAL_SANCTION_AMOUNT")
		  private String	total_sanction_amount;
		  @Column(name="TOTAL_CREDIT_LIMIT")
		  private String	total_credit_limit;
		  @Column(name="RECENT_ACCOUNT")
		  private String	recent_account;
		  @Column(name="SINGLE_HIGHEST_SANCTION_AMOUNT")
		  private String	single_highest_sanction_amount;
		  @Column(name="TOTAL_HIGH_CREDIT")
		  private String	total_high_credit;
		  @Column(name="TOTAL_BALANCE_AMOUNT")
		  private String	total_balance_amount;
		  @Column(name="NO_OF_WRITE_OFFS")
		  private String	no_of_write_offs;
		  @Column(name="NO_OF_PAST_DUE_ACCOUNT")
		  private String	no_of_past_due_account;
		  @Column(name="AVERAGE_OPEN_BALANCE")
		  private String	average_open_balance;
		  @Column(name="TOTAL_MONTHLY_PAYMENT_AMOUNT")
		  private String	total_monthly_payment_amount;
		  @Column(name="TOTAL_PAST_DUE")
		  private String	total_past_due;
		  @Column(name="SINGLE_HIGHEST_BALANCE")
		  private String	single_highest_balance;
		  @Column(name="SINGLE_HIGHEST_CREDIT")
		  private String	single_highest_credit;
		  @Column(name="NO_OF_ACTIVE_ACCOUNTS")
		  private String    no_of_active_accounts;
		  @OneToOne
		  @JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
		  private  EquifaxPcsAllDetails equifaxpcDetailslogs;
		 
        
		public EquifaxPcsAllDetails getEquifaxpcDetailslogs() {
			return equifaxpcDetailslogs;
         }
         public void setEquifaxpcDetailslogs(EquifaxPcsAllDetails equifaxpcDetailslogs) {
			this.equifaxpcDetailslogs = equifaxpcDetailslogs;
         }
	
		  public Long getAccount_id() {
			return account_id;
		}
		public void setAccount_id(Long account_id) {
			this.account_id = account_id;
		}
		
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
		public String getTotal_balance_amount() {
			return total_balance_amount;
		}
		public void setTotal_balance_amount(String total_balance_amount) {
			this.total_balance_amount = total_balance_amount;
		}
		public String getNo_of_write_offs() {
			return no_of_write_offs;
		}
		public void setNo_of_write_offs(String no_of_write_offs) {
			this.no_of_write_offs = no_of_write_offs;
		}
		public String getNo_of_past_due_account() {
			return no_of_past_due_account;
		}
		public void setNo_of_past_due_account(String no_of_past_due_account) {
			this.no_of_past_due_account = no_of_past_due_account;
		}
		
		public String getAverage_open_balance() {
			return average_open_balance;
		}
		public void setAverage_open_balance(String average_open_balance) {
			this.average_open_balance = average_open_balance;
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
		public String getNo_of_active_accounts() {
			return no_of_active_accounts;
		}
		
		public void setNo_of_active_accounts(String no_of_active_accounts) {
			this.no_of_active_accounts = no_of_active_accounts;
		}
		
}
