package com.qualtech.equifax.api.entity.pcs;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@Entity
@Table(name="IB_EQ_P_ACNT_DTLS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxPcsAccountDetailsSec
{
	  @Column(name="REASON")
	  private String reason;
	  @Column(name="GETLAST_PAYMENT")
	  private String last_payment;
	  @Column(name="REPORTED_DATE")
	  private String reported_date;
	  @Column(name="ACCOUNT_TYPE")
	  private String  account_type;
	  @Column(name="OWNER_SHIP_TYPE")
	  private String owner_ship_type;
	  @Column(name="SUIT_FILED_STATUS")
	  private String  suit_filed_status;
	  @Column(name="ASSET_CLASSIFICATION")
	  private String  asset_classification;
	  @Column(name="TERM_FREQUENCY")
	  private String  term_frequency;
	  @Column(name="COLLATERAL_VALUE")
	  private String  collateral_value;
	  @Column(name="COLLATERAL_TYPE")
	  private String  collateral_type;
	  @Column(name="BALANCE")
	  private String  balance;
	  @Column(name="INTERESTRATE")
	  private String  interestrate;
	  @Column(name="OPEN")
	  private String  open;
	  @Column(name="ACCOUNT_STATUS")
	  private String  account_status;
	  @Column(name="INSTITUTION")
	  private String  institution;
	  @Column(name="HISTORY_FOURTY_EIGHT_MONTHS")
	  private String  history_fourty_eight_months;
	  @Column(name="SEQUENCE")
	  private String sequence;
	  @Column(name="INSTALLMENT_AMOUNT")
	  private String  installment_amount;
	  @Column(name="SANCTION_AMOUNT")
	  private String  sanction_amount;
	  @Column(name="REPAYMENT_TENURE")
	  private String  repayment_tenure;
	  @Column(name="ACCOUNT_NUMBER")
	  private String  account_number;
	  @Column(name="DISPUTE_CODE")
	  private String dispute_code;
	  
	  @Column(name="PAST_DUE_AMOUNT")
	  private String  pastDueAmount;
	  @Column(name="OTHER_LAST_PAYMENT")
	  private String  otherLastPayment;
	  @Column(name="LAST_PAYMENT_DATE")
	  private String  lastPaymentDate;
	  @Column(name="HIGH_CREDIT")
	  private String  highCredit;
	  @Column(name="INDIVIDUAL_WRITE_OFF_AMNT")
	  private String  individualwriteOffAmount;
	  @Column(name="CREDIT_LIMIT")
	  private String  creditLimit;
	  @Column(name="DATE_CLOSED")
	  private String  dateClosed;
	  @Column(name="DATE_OPENED")
	  private String dateopened;
	  @Id
	  @Column(name="ACCOUNT_DETAIL_ID")
	  private Long  account_detail_id;
	  @Column(name="REQUEST_UNIQUE_ID")
	  private long request_unique_id;
	  
	  
	  public long getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(long request_unique_id) {
		this.request_unique_id = request_unique_id;
	}

	  public String getDateopened() {
		return dateopened;
	}
	  public void setDateopened(String dateopened) {
		this.dateopened = dateopened;
	}
	  
	  
public String getPastDueAmount() {
		return pastDueAmount;
	}
	public void setPastDueAmount(String pastDueAmount) {
		this.pastDueAmount = pastDueAmount;
	}
	public String getOtherLastPayment() {
		return otherLastPayment;
	}
	public void setOtherLastPayment(String otherLastPayment) {
		this.otherLastPayment = otherLastPayment;
	}
	public String getLastPaymentDate() {
		return lastPaymentDate;
	}
	public void setLastPaymentDate(String lastPaymentDate) {
		this.lastPaymentDate = lastPaymentDate;
	}
	public String getHighCredit() {
		return highCredit;
	}
	public void setHighCredit(String highCredit) {
		this.highCredit = highCredit;
	}
	public String getIndividualwriteOffAmount() {
		return individualwriteOffAmount;
	}
	public void setIndividualwriteOffAmount(String individualwriteOffAmount) {
		this.individualwriteOffAmount = individualwriteOffAmount;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getDateClosed() {
		return dateClosed;
	}
	public void setDateClosed(String dateClosed) {
		this.dateClosed = dateClosed;
	}


	 
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getLast_payment() {
		return last_payment;
	}
	public void setLast_payment(String last_payment) {
		this.last_payment = last_payment;
	}
	
	public String getReported_date() {
		return reported_date;
	}
	public void setReported_date(String reported_date) {
		this.reported_date = reported_date;
	}
	
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
	public String getCollateral_type() {
		return collateral_type;
	}
	public void setCollateral_type(String collateral_type) {
		this.collateral_type = collateral_type;
	}
	public String getInterestrate() {
		return interestrate;
	}
	public void setInterestrate(String interestrate) {
		this.interestrate = interestrate;
	}
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getTerm_frequency() {
		return term_frequency;
	}
	public void setTerm_frequency(String term_frequency) {
		this.term_frequency = term_frequency;
	}
	public String getAccount_status() {
		return account_status;
	}
	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}
	public String getHistory_fourty_eight_months() {
		return history_fourty_eight_months;
	}
	public void setHistory_fourty_eight_months(String history_fourty_eight_months) {
		this.history_fourty_eight_months = history_fourty_eight_months;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getInstallment_amount() {
		return installment_amount;
	}
	public void setInstallment_amount(String installment_amount) {
		this.installment_amount = installment_amount;
	}
	public String getSanction_amount() {
		return sanction_amount;
	}
	public void setSanction_amount(String sanction_amount) {
		this.sanction_amount = sanction_amount;
	}
	public String getRepayment_tenure() {
		return repayment_tenure;
	}
	public void setRepayment_tenure(String repayment_tenure) {
		this.repayment_tenure = repayment_tenure;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getDispute_code() {
		return dispute_code;
	}
	public void setDispute_code(String dispute_code) {
		this.dispute_code = dispute_code;
	}
	public String getOwner_ship_type() {
		return owner_ship_type;
	}
	public void setOwner_ship_type(String owner_ship_type) {
		this.owner_ship_type = owner_ship_type;
	}
	public String getSuit_filed_status() {
		return suit_filed_status;
	}
	public void setSuit_filed_status(String suit_filed_status) {
		this.suit_filed_status = suit_filed_status;
	}
	public String getCollateral_value() {
		return collateral_value;
	}
	public void setCollateral_value(String collateral_value) {
		this.collateral_value = collateral_value;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public String getAsset_classification() {
		return asset_classification;
	}
	public void setAsset_classification(String asset_classification) {
		this.asset_classification = asset_classification;
	}
	public Long getAccount_detail_id() {
		return account_detail_id;
	}
	public void setAccount_detail_id(Long account_detail_id) {
		this.account_detail_id = account_detail_id;
	}
	
	
}
