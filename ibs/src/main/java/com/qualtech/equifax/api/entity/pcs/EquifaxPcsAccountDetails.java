package com.qualtech.equifax.api.entity.pcs;

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
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonManagedReference;

import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;


@Entity

@Table(name="IB_EQ_P_ACCOUNT_DETAILS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxPcsAccountDetails 
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
	  @Lob
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

	  @Transient
	  private String  pastDueAmount;
	  @Transient
	  private String  otherLastPayment;
	  @Transient
	  private String  lastPaymentDate;
	  @Transient
	  private String  highCredit;
	  @Transient
	  private String  individualwriteOffAmount;
	  @Transient
	  private String  creditLimit;
	  @Transient
	  private String  dateClosed;
	  @Transient
	  private String dateopened;
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_P_ACCCOUNT_SQC")
	  @SequenceGenerator(name="IB_EQ_P_ACCCOUNT_SQC", sequenceName = "IB_EQ_P_ACCCOUNT_SQC", allocationSize = 1)
	 @Column(name="ACCOUNT_DETAIL_ID")
	  private Long  account_detail_id;
	  @ManyToOne
	  @JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	  @JsonIgnore
	  private EquifaxPcsAllDetails   equifaxPcsAllDetails;
	  
	  @Transient
	  @JsonManagedReference
	  private List<EquifaxHistory48MonthsDetail> history48MonthsDetail;
	  
	 
	
	public EquifaxPcsAllDetails getEquifaxPcsAllDetails() {
		return equifaxPcsAllDetails;
	}
	public void setEquifaxPcsAllDetails(EquifaxPcsAllDetails equifaxPcsAllDetails) {
		this.equifaxPcsAllDetails = equifaxPcsAllDetails;
	}
	public void setHistory48MonthsDetail(List<EquifaxHistory48MonthsDetail> history48MonthsDetail) {
		this.history48MonthsDetail = history48MonthsDetail;
	}
	  public List<EquifaxHistory48MonthsDetail> getHistory48MonthsDetail() {
		return history48MonthsDetail;
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

	 
//	@Column(name="REASON")  
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
//	@Column(name="GETLAST_PAYMENT")
	public String getLast_payment() {
		return last_payment;
	}
	public void setLast_payment(String last_payment) {
		this.last_payment = last_payment;
	}
	
//	@Column(name="REPORTED_DATE")
	public String getReported_date() {
		return reported_date;
	}
	public void setReported_date(String reported_date) {
		this.reported_date = reported_date;
	}
	
//	@Column(name="ACCOUNT_TYPE")
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	
//	@Column(name="INSTITUTION")
	public String getInstitution() {
		return institution;
	}
	public void setInstitution(String institution) {
		this.institution = institution;
	}
//	@Column(name="COLLATERAL_TYPE")
	public String getCollateral_type() {
		return collateral_type;
	}
	public void setCollateral_type(String collateral_type) {
		this.collateral_type = collateral_type;
	}
//	@Column(name="INTERESTRATE")
	public String getInterestrate() {
		return interestrate;
	}
	public void setInterestrate(String interestrate) {
		this.interestrate = interestrate;
	}
//	@Column(name="BALANCE")
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
//	@Column(name="TERM_FREQUENCY")
	public String getTerm_frequency() {
		return term_frequency;
	}
	public void setTerm_frequency(String term_frequency) {
		this.term_frequency = term_frequency;
	}
//	@Column(name="ACCOUNT_STATUS")
	public String getAccount_status() {
		return account_status;
	}
	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}
//	@Column(name="HISTORY_FOURTY_EIGHT_MONTHS")
	public String getHistory_fourty_eight_months() {
		return history_fourty_eight_months;
	}
	public void setHistory_fourty_eight_months(String history_fourty_eight_months) {
		this.history_fourty_eight_months = history_fourty_eight_months;
	}
//	@Column(name="SEQUENCE")
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
//	@Column(name="INSTALLMENT_AMOUNT")
	public String getInstallment_amount() {
		return installment_amount;
	}
	public void setInstallment_amount(String installment_amount) {
		this.installment_amount = installment_amount;
	}
//	@Column(name="SANCTION_AMOUNT")
	public String getSanction_amount() {
		return sanction_amount;
	}
	public void setSanction_amount(String sanction_amount) {
		this.sanction_amount = sanction_amount;
	}
//	@Column(name="REPAYMENT_TENURE")
	public String getRepayment_tenure() {
		return repayment_tenure;
	}
	public void setRepayment_tenure(String repayment_tenure) {
		this.repayment_tenure = repayment_tenure;
	}
//	@Column(name="ACCOUNT_NUMBER")
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
//	@Column(name="DISPUTE_CODE")
	public String getDispute_code() {
		return dispute_code;
	}
	public void setDispute_code(String dispute_code) {
		this.dispute_code = dispute_code;
	}
//	@Column(name="OWNER_SHIP_TYPE")
	public String getOwner_ship_type() {
		return owner_ship_type;
	}
	public void setOwner_ship_type(String owner_ship_type) {
		this.owner_ship_type = owner_ship_type;
	}
//	@Column(name="SUIT_FILED_STATUS")
	public String getSuit_filed_status() {
		return suit_filed_status;
	}
	public void setSuit_filed_status(String suit_filed_status) {
		this.suit_filed_status = suit_filed_status;
	}
//	@Column(name="COLLATERAL_VALUE")
	public String getCollateral_value() {
		return collateral_value;
	}
	public void setCollateral_value(String collateral_value) {
		this.collateral_value = collateral_value;
	}
//	@Column(name="OPEN")
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
//	@Column(name="ASSET_CLASSIFICATION")
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
	@Override
	public String toString() {
		return "EquifaxPcsAccountDetails [reason=" + reason + ", last_payment=" + last_payment + ", reported_date="
				+ reported_date + ", account_type=" + account_type + ", owner_ship_type=" + owner_ship_type
				+ ", suit_filed_status=" + suit_filed_status + ", asset_classification=" + asset_classification
				+ ", term_frequency=" + term_frequency + ", collateral_value=" + collateral_value + ", collateral_type="
				+ collateral_type + ", balance=" + balance + ", interestrate=" + interestrate + ", open=" + open
				+ ", account_status=" + account_status + ", institution=" + institution
				+ ", history_fourty_eight_months=" + history_fourty_eight_months + ", sequence=" + sequence
				+ ", installment_amount=" + installment_amount + ", sanction_amount=" + sanction_amount
				+ ", repayment_tenure=" + repayment_tenure + ", account_number=" + account_number + ", dispute_code="
				+ dispute_code + ", pastDueAmount=" + pastDueAmount + ", otherLastPayment=" + otherLastPayment
				+ ", lastPaymentDate=" + lastPaymentDate + ", highCredit=" + highCredit + ", individualwriteOffAmount="
				+ individualwriteOffAmount + ", creditLimit=" + creditLimit + ", dateClosed=" + dateClosed
				+ ", dateopened=" + dateopened + ", account_detail_id=" + account_detail_id +  ", history48MonthsDetail="
				+ history48MonthsDetail + "]";
	}

	
	
	
	
}
