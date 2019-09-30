package com.qualtech.equifax.api.entity.mcr;

import java.util.List;

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
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;

@Entity
@Table(name="IB_EQ_M_ACNT_DTLS")
public class EquifaxMcrAccountDetail 
{
			@Transient
			private List<EquifaxMcrAdditionalMFIDetail>  equifaxMcrAdditionalMFIDetails;
			@Transient
			private List<EquifaxMcrHistory24MonthsDetail>history24MonthsDetails;
			@Transient
			private List<EquifaxMcrKeyPersonDetail> equifaxMcrKeyPersonDetails;
			@Transient
			private List<EquifaxMcrNomineeDetail> equifaxMcrNomineeDetails;
			@ManyToOne
			@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
			@JsonIgnore
			private EquifaxMcrAllDetaills  equifaxMcrDetailsLogs;
	
			
			@Id
			@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_ACCOUNT_SQC")
			@SequenceGenerator(name="IB_EQ_MCR_ACCOUNT_SQC", sequenceName = "IB_EQ_MCR_ACCOUNT_SQC", allocationSize = 1)
			@Column(name="ACCOUNT_ID")
		  private int account_id;	
		  
		  @Column(name="DATE_SANCTIONED")
		  private String date_sanctioned;
		  @Column(name="REPORTED_DATE")
		  private String reported_date;
		  @Column(name="DATE_CLOSED")
		  private String date_closed;
		  @Column(name="NO_OF_INSTALLMENTS")
		  private String no_of_installments;
		  @Column(name="CURRENT_BAL")
		  private String current_balance;
		  @Column(name="INSTITUTION")
		  private String institution;
		  @Column(name="KEY_PERSON")
		  @Lob
		  private String key_person;
		  @Column(name="ACCOUNT_STATUS")
		  private String account_status;
		  @Column(name="NOMINEE")
		  @Lob
		  private String nominee;
		  @Column(name="ACCOUNT_DETAIL_ID")
		  private String account_detail_id;
		  @Column(name="DISBURSED_AMMOUNT")
		  private String disbursed_ammount;
		  @Column(name="DATE_REPORTED")
		  private String date_reported;
		  @Column(name="SEQUENCE")
		  private String sequence;
		  @Column(name="INSTALLMENT_AMOUNT")
		  private String installment_amount;
		  @Column(name="DAYS_PAST_DUE")
		  private String days_past_due;
		  @Column(name="SANCTION_AMOUNT")
		  private String sanction_amount;
		  @Column(name="REPAYMENT_TENURE")
		  private String repayment_tenure;
		  @Column(name="ACCOUNT_NUMBER")
		  private String account_number;
		  @Column(name="KENDRA_AID_MFI")
		  private String kendra_aid_mfi;
		  @Column(name="ADDITIONAL_MFI_DETAILS")
		  @Lob
		  private String additional_mfi_details;
		  @Column(name="DATE_OPENED")
		  private String date_opened;
		  @Column(name="APPLIED_AMOUNT")
		  private String applied_amount;
		  @Column(name="LOAN_PURPOSE")
		  private String loan_purpose;
		  @Column(name="HISTORY_24_MONTHS")
		  @Lob
		  private String history_24_months;
		  @Column(name="BRANCH_ID_MFI")
		  private String branch_id_mfi;
		  @Column(name="LOAN_CATEGORY")
		  private String loan_category;
		  @Column(name="LOAN_CYCLE_ID")
		  private String loan_cycle_id;
		  @Column(name="REASON")
		  private String reason;
		  @Column(name="LAST_PAYMENT")
		  private String last_payment;
		  @Column(name="ACOUNT_TYPE")
		  private String  account_type;
		  @Column(name="OWNERSHIP_TYPE")
		  private String owner_ship_type;
		  @Column(name="SUIT_FIELD_STATUS")
		  private String  suit_filed_status;
		  @Column(name="ASSET_CLASSIFICATION")
		  private String  asset_classification;
		  @Column(name="TERM_FREQUENCY")
		  private String  term_frequency;
		  @Column(name="COLLATERAL_VALUE")
		  private String  collateral_value;
		  @Column(name="COLLATERAL_TYPE")
		  private String  collateral_type;
		  @Column(name="INTEREST_RATE")
		  private String  interestrate;
		  @Column(name="PAST_DUE_AMOUNT")
		  private String past_due_amount;
		  @Column(name="BALANCE")
		  private String balance;
		  @Column(name="WRITE_OFF_AMOUNT")
		  private String writeOffAmount;
		  @Column(name="OPEN")
		  private String open;
		  @Column(name="LAST_PAYMENT_DATE")
		  private String lastPaymentDate;
		  @Column(name="HIGH_CREDIT")
		  private String highCredit;
		  @Column(name="DATE_WRITTEN_OFF")
		  private String dateWrittenOff;
		  @Column(name="DATE_APPLIED")
		  private String dateApplied;
		 // @Column(name="")
		  @Transient
		  private String  dispute_code;
		  @Column(name="CLIENT_NAME")
		  private String clientName;
		  @Column(name="CURRENT_BALANCE")
		  private String currentBalance;
		  @Column(name="CREDIT_LIMIT")
		  private String creditLimit;
		  
		  
		 
		public void setCreditLimit(String creditLimit) {
			this.creditLimit = creditLimit;
		}
		  public String getCreditLimit() {
			return creditLimit;
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
		public String getAccount_type() {
			return account_type;
		}
		public void setAccount_type(String account_type) {
			this.account_type = account_type;
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
		public String getAsset_classification() {
			return asset_classification;
		}
		public void setAsset_classification(String asset_classification) {
			this.asset_classification = asset_classification;
		}
		public String getTerm_frequency() {
			return term_frequency;
		}
		public void setTerm_frequency(String term_frequency) {
			this.term_frequency = term_frequency;
		}
		public String getCollateral_value() {
			return collateral_value;
		}
		public void setCollateral_value(String collateral_value) {
			this.collateral_value = collateral_value;
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
		public String getPast_due_amount() {
			return past_due_amount;
		}
		public void setPast_due_amount(String past_due_amount) {
			this.past_due_amount = past_due_amount;
		}
		public String getBalance() {
			return balance;
		}
		public void setBalance(String balance) {
			this.balance = balance;
		}
		public String getWriteOffAmount() {
			return writeOffAmount;
		}
		public void setWriteOffAmount(String writeOffAmount) {
			this.writeOffAmount = writeOffAmount;
		}
		public String getOpen() {
			return open;
		}
		public void setOpen(String open) {
			this.open = open;
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
		public String getDateWrittenOff() {
			return dateWrittenOff;
		}
		public void setDateWrittenOff(String dateWrittenOff) {
			this.dateWrittenOff = dateWrittenOff;
		}
	
		public String getDateApplied() {
			return dateApplied;
		}
		public void setDateApplied(String dateApplied) {
			this.dateApplied = dateApplied;
		}
		
		public String getDispute_code() {
			return dispute_code;
		}
		public void setDispute_code(String dispute_code) {
			this.dispute_code = dispute_code;
		}
		public String getClientName() {
			return clientName;
		}
		public void setClientName(String clientName) {
			this.clientName = clientName;
		}
		public String getCurrentBalance() {
			return currentBalance;
		}
		public void setCurrentBalance(String currentBalance) {
			this.currentBalance = currentBalance;
		}
		
		
		  
		  
		  public List<EquifaxMcrAdditionalMFIDetail> getEquifaxMcrAdditionalMFIDetails() {
			return equifaxMcrAdditionalMFIDetails;
		}
		public void setEquifaxMcrAdditionalMFIDetails(List<EquifaxMcrAdditionalMFIDetail> equifaxMcrAdditionalMFIDetails) {
			this.equifaxMcrAdditionalMFIDetails = equifaxMcrAdditionalMFIDetails;
		}
		public List<EquifaxMcrHistory24MonthsDetail> getHistory24MonthsDetails() {
			return history24MonthsDetails;
		}
		public void setHistory24MonthsDetails(List<EquifaxMcrHistory24MonthsDetail> history24MonthsDetails) {
			this.history24MonthsDetails = history24MonthsDetails;
		}
		public List<EquifaxMcrKeyPersonDetail> getEquifaxMcrKeyPersonDetails() {
			return equifaxMcrKeyPersonDetails;
		}
		public void setEquifaxMcrKeyPersonDetails(List<EquifaxMcrKeyPersonDetail> equifaxMcrKeyPersonDetails) {
			this.equifaxMcrKeyPersonDetails = equifaxMcrKeyPersonDetails;
		}
		public List<EquifaxMcrNomineeDetail> getEquifaxMcrNomineeDetails() {
			return equifaxMcrNomineeDetails;
		}
		public void setEquifaxMcrNomineeDetails(List<EquifaxMcrNomineeDetail> equifaxMcrNomineeDetails) {
			this.equifaxMcrNomineeDetails = equifaxMcrNomineeDetails;
		}
		
		//		@ManyToOne
//		@JoinColumn(name = "REQUEST_UNIQUE_ID")  
		public EquifaxMcrAllDetaills getEquifaxMcrDetailsLogs() {
			return equifaxMcrDetailsLogs;
		}
		public void setEquifaxMcrDetailsLogs(EquifaxMcrAllDetaills equifaxMcrDetailsLogs) {
			this.equifaxMcrDetailsLogs = equifaxMcrDetailsLogs;
		}
		
		
//		@Column(name="DATE_SANCTIONED")  
		public String getDate_sanctioned() {
			return date_sanctioned;
		}
		public void setDate_sanctioned(String date_sanctioned) {
			this.date_sanctioned = date_sanctioned;
		}
//		@Column(name="REPORTED_DATE")
		public String getReported_date() {
			return reported_date;
		}
		public void setReported_date(String reported_date) {
			this.reported_date = reported_date;
		}
//		@Column(name="DATE_CLOSED")
		public String getDate_closed() {
			return date_closed;
		}
		public void setDate_closed(String date_closed) {
			this.date_closed = date_closed;
		}
//		@Column(name="NO_OF_INSTALLMENTS")
		public String getNo_of_installments() {
			return no_of_installments;
		}
		public void setNo_of_installments(String no_of_installments) {
			this.no_of_installments = no_of_installments;
		}
//		@Column(name="CURRENT_BALANCE")
		public String getCurrent_balance() {
			return current_balance;
		}
		public void setCurrent_balance(String current_balance) {
			this.current_balance = current_balance;
		}
//		@Column(name="INSTITUTION")
		public String getInstitution() {
			return institution;
		}
		public void setInstitution(String institution) {
			this.institution = institution;
		}
//		@Column(name="KEY_PERSON")
		public String getKey_person() {
			return key_person;
		}
		public void setKey_person(String key_person) {
			this.key_person = key_person;
		}
//		@Column(name="ACCOUNT_STATUS")
		public String getAccount_status() {
			return account_status;
		}
		public void setAccount_status(String account_status) {
			this.account_status = account_status;
		}
//		@Column(name="NOMINEE")
		public String getNominee() {
			return nominee;
		}
		public void setNominee(String nominee) {
			this.nominee = nominee;
		}
//		@Column(name="ACCOUNT_DETAIL_ID")
		public String getAccount_detail_id() {
			return account_detail_id;
		}
		public void setAccount_detail_id(String account_detail_id) {
			this.account_detail_id = account_detail_id;
		}
//		@Column(name="DISBURSED_AMMOUNT")
		public String getDisbursed_ammount() {
			return disbursed_ammount;
		}
		public void setDisbursed_ammount(String disbursed_ammount) {
			this.disbursed_ammount = disbursed_ammount;
		}
//		@Column(name="DATE_REPORTED")
		public String getDate_reported() {
			return date_reported;
		}
		public void setDate_reported(String date_reported) {
			this.date_reported = date_reported;
		}
//		@Column(name="SEQUENCE")
		public String getSequence() {
			return sequence;
		}
		public void setSequence(String sequence) {
			this.sequence = sequence;
		}
//		@Column(name="INSTALLMENT_AMOUNT")
		public String getInstallment_amount() {
			return installment_amount;
		}
		public void setInstallment_amount(String installment_amount) {
			this.installment_amount = installment_amount;
		}
//		@Column(name="DAYS_PAST_DUE")
		public String getDays_past_due() {
			return days_past_due;
		}
		public void setDays_past_due(String days_past_due) {
			this.days_past_due = days_past_due;
		}
//		@Column(name="SANCTION_AMOUNT")
		public String getSanction_amount() {
			return sanction_amount;
		}
		public void setSanction_amount(String sanction_amount) {
			this.sanction_amount = sanction_amount;
		}
//		@Column(name="REPAYMENT_TENURE")
		public String getRepayment_tenure() {
			return repayment_tenure;
		}
		public void setRepayment_tenure(String repayment_tenure) {
			this.repayment_tenure = repayment_tenure;
		}
//		@Column(name="account_number")
		public String getAccount_number() {
			return account_number;
		}
		public void setAccount_number(String account_number) {
			this.account_number = account_number;
		}
//		@Column(name="KENDRA_AID_MFI")
		public String getKendra_aid_mfi() {
			return kendra_aid_mfi;
		}
		public void setKendra_aid_mfi(String kendra_aid_mfi) {
			this.kendra_aid_mfi = kendra_aid_mfi;
		}
//		@Column(name="ADDITIONAL_MFI_DETAILS")
		public String getAdditional_mfi_details() {
			return additional_mfi_details;
		}
		public void setAdditional_mfi_details(String additional_mfi_details) {
			this.additional_mfi_details = additional_mfi_details;
		}
//		@Column(name="DATE_OPENED")
		public String getDate_opened() {
			return date_opened;
		}
		public void setDate_opened(String date_opened) {
			this.date_opened = date_opened;
		}
//		@Column(name="APPLIED_AMOUNT")
		public String getApplied_amount() {
			return applied_amount;
		}
		public void setApplied_amount(String applied_amount) {
			this.applied_amount = applied_amount;
		}
//		@Column(name="LOAN_PURPOSE")
		public String getLoan_purpose() {
			return loan_purpose;
		}
		public void setLoan_purpose(String loan_purpose) {
			this.loan_purpose = loan_purpose;
		}
//		@Column(name="HISTORY_24_MONTHS")
		public String getHistory_24_months() {
			return history_24_months;
		}
		public void setHistory_24_months(String history_24_months) {
			this.history_24_months = history_24_months;
		}
//		@Column(name="BRANCH_ID_MFI")
		public String getBranch_id_mfi() {
			return branch_id_mfi;
		}
		public void setBranch_id_mfi(String branch_id_mfi) {
			this.branch_id_mfi = branch_id_mfi;
		}
//		@Column(name="LOAN_CATEGORY")
		public String getLoan_category() {
			return loan_category;
		}
		public void setLoan_category(String loan_category) {
			this.loan_category = loan_category;
		}
//		@Column(name="LOAN_CYCLE_ID")
		public String getLoan_cycle_id() {
			return loan_cycle_id;
		}
		public void setLoan_cycle_id(String loan_cycle_id) {
			this.loan_cycle_id = loan_cycle_id;
		}
//		@Id
//		@Column(name="ACCOUNT_ID")
		public int getAccount_id() {
			return account_id;
		}
		public void setAccount_id(int account_id) {
			this.account_id = account_id;
		}
  
	
}
