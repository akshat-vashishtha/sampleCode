package com.qualtech.cibil.api.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.cibil.api.response.CibilResponsePayload;
@Entity
@Table(name="IB_CBL_ACCOUNTDETAILS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountDetails implements Serializable
{
	
	private static final long serialVersionUID = 1379160936226349305L;
	private Long AccountId;
	private String reportingMemberName="NA";
	private String accountNumber="NA";
	private String accountType="NA";
	private String ownershipIndicator="NA";
	private String dateOpened="NA";
	private String dateOfLastPayment="NA";
	private String dateClose="NA";
	private String dateReportedVerified="NA";
	private String sanctionAmount="NA";
	private String currntBalance="NA";
	private String amountOverdue="NA";
	private String paymentHistory1="";
	private String paymentHistory2="";
	private String paymentHstrStartDate="NA";
	private String paymentHistoryEndDate="NA";
	private String suitfilledDefault="NA";
	private String writtenOffStatus="NA";
	private String valueOfColleteral="NA";
	private String typeOfColleteral="NA";
	private String creditLimit="NA";
	private String cashLimit="NA";
	private String rateOfInterest="NA";
	private String repaymentTenure="NA";
	private String emiAmount="NA";
	private String writtenOffAmountTotal="NA";
	private String writtenOffAmountPrincipal="NA";
	private String settlementAmount="NA";
	private String paymentfrequency="NA";
	private String actualPaymentAmount="NA";
	private String errorCode="NA";
	private String dateOfEntryForCibilRemarkscode="NA";
	private String cibilRemarksCode="NA";
	private String dateOfEntryForErrorcode="NA";
	private String dateOfERemarksCode="NA";
	private String errordisputecode1="NA";
	private String errordisputcode2="NA";
	private String dateofedisputeremarkscode;
	private String DATEOFEERRORCODE;
	private String DATEOFFENTRYFORERRORCODE;
    private String DATEOFENTRYOFDISPUTECODE;
    private String DATEREPORTED;
    @JsonIgnore
    private CibilResponsePayload cibilresponsepayload;
    
	private List<PaymentHistoryUI> daysPastDueAssetClassificationData;
	
	@Column(name="ERRORDISPUTECODE1")
	public String getErrordisputecode1() {
		return errordisputecode1;
	}
	public void setErrordisputecode1(String errordisputecode1) {
		this.errordisputecode1 = errordisputecode1;
	}
    @Column(name="ERRORDISPUTCODE2")
	public String getErrordisputcode2() {
		return errordisputcode2;
	}
	public void setErrordisputcode2(String errordisputcode2) {
		this.errordisputcode2 = errordisputcode2;
	}
    @Column(name="REPORTINGMEMBERNAME")
	public String getReportingMemberName() {
		return reportingMemberName;
	}
	public void setReportingMemberName(String reportingMemberName) {
		this.reportingMemberName = reportingMemberName;
	}
    @Column(name="ACCOUNTNUMBER")
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
    @Column(name="ACCOUNTTYPE")
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
    @Column(name="OWNERSHIPINDICATOR")
	public String getOwnershipIndicator() {
		return ownershipIndicator;
	}
	public void setOwnershipIndicator(String ownershipIndicator) {
		this.ownershipIndicator = ownershipIndicator;
	}
    @Column(name="DATEOPENED")
	public String getDateOpened() {
		return dateOpened;
	}
	public void setDateOpened(String dateOpened) {
		this.dateOpened = dateOpened;
	}
    @Column(name="DATEOFLASTPAYMENT")
	public String getDateOfLastPayment() {
		return dateOfLastPayment;
	}
	public void setDateOfLastPayment(String dateOfLastPayment) {
		this.dateOfLastPayment = dateOfLastPayment;
	}
    @Column(name="DATECLOSE")
	public String getDateClose() {
		return dateClose;
	}
	public void setDateClose(String dateClose) {
		this.dateClose = dateClose;
	}
    @Column(name="DATEREPORTEDVERIFIED")
	public String getDateReportedVerified() {
		return dateReportedVerified;
	}
	public void setDateReportedVerified(String dateReportedVerified) {
		this.dateReportedVerified = dateReportedVerified;
	}
   @Column(name="SANCTIONAMOUNT")
	public String getSanctionAmount() {
		return sanctionAmount;
	}
	public void setSanctionAmount(String sanctionAmount) {
		this.sanctionAmount = sanctionAmount;
	}
    @Column(name="CURRNTBALANCE")
	public String getCurrntBalance() {
		return currntBalance;
	}
	public void setCurrntBalance(String currntBalance) {
		this.currntBalance = currntBalance;
	}
    @Column(name="AMOUNTOVERDUE")
	public String getAmountOverdue() {
		return amountOverdue;
	}
	public void setAmountOverdue(String amountOverdue) {
		this.amountOverdue = amountOverdue;
	}
    @Column(name="PAYMENTHISTORY1")
	public String getPaymentHistory1() {
		return paymentHistory1;
	}
	public void setPaymentHistory1(String paymentHistory1) {
		this.paymentHistory1 = paymentHistory1;
	}
    @Column(name="PAYMENTHISTORY2")
	public String getPaymentHistory2() {
		return paymentHistory2;
	}
	public void setPaymentHistory2(String paymentHistory2) {
		this.paymentHistory2 = paymentHistory2;
	}
    @Column(name="PAYMENTHSTRSTARTDATE")
	public String getPaymentHstrStartDate() {
		return paymentHstrStartDate;
	}
	public void setPaymentHstrStartDate(String paymentHstrStartDate) {
		this.paymentHstrStartDate = paymentHstrStartDate;
	}
   @Column(name="PAYMENTHISTORYENDDATE")
	public String getPaymentHistoryEndDate() {
		return paymentHistoryEndDate;
	}
	public void setPaymentHistoryEndDate(String paymentHistoryEndDate) {
		this.paymentHistoryEndDate = paymentHistoryEndDate;
	}
    @Column(name="SUITFILLEDDEFAULT")
	public String getSuitfilledDefault() {
		return suitfilledDefault;
	}
	public void setSuitfilledDefault(String suitfilledDefault) {
		this.suitfilledDefault = suitfilledDefault;
	}
    @Column(name="WRITTENOFFSTATUS")
	public String getWrittenOffStatus() {
		return writtenOffStatus;
	}
	public void setWrittenOffStatus(String writtenOffStatus) {
		this.writtenOffStatus = writtenOffStatus;
	}
    @Column(name="VALUEOFCOLLETERAL")
	public String getValueOfColleteral() {
		return valueOfColleteral;
	}
	public void setValueOfColleteral(String valueOfColleteral) {
		this.valueOfColleteral = valueOfColleteral;
	}
    @Column(name="CREDITLIMIT")
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
    @Column(name="CASHLIMIT")
	public String getCashLimit() {
		return cashLimit;
	}
	public void setCashLimit(String cashLimit) {
		this.cashLimit = cashLimit;
	}
    @Column(name="RATEOFINTEREST")
	public String getRateOfInterest() {
		return rateOfInterest;
	}
	public void setRateOfInterest(String rateOfInterest) {
		this.rateOfInterest = rateOfInterest;
	}
    @Column(name="REPAYMENTTENURE")
	public String getRepaymentTenure() {
		return repaymentTenure;
	}
	public void setRepaymentTenure(String repaymentTenure) {
		this.repaymentTenure = repaymentTenure;
	}
    @Column(name="EMIAMOUNT")
	public String getEmiAmount() {
		return emiAmount;
	}
	public void setEmiAmount(String emiAmount) {
		this.emiAmount = emiAmount;
	}
   @Column(name="WRITTENOFFAMOUNTTOTAL")
	public String getWrittenOffAmountTotal() {
		return writtenOffAmountTotal;
	}
	public void setWrittenOffAmountTotal(String writtenOffAmountTotal) {
		this.writtenOffAmountTotal = writtenOffAmountTotal;
	}
    @Column(name="WRITTENOFFAMOUNTPRINCIPAL")
	public String getWrittenOffAmountPrincipal() {
		return writtenOffAmountPrincipal;
	}
	public void setWrittenOffAmountPrincipal(String writtenOffAmountPrincipal) {
		this.writtenOffAmountPrincipal = writtenOffAmountPrincipal;
	}
    @Column(name="SETTLEMENTAMOUNT")
	public String getSettlementAmount() {
		return settlementAmount;
	}
	public void setSettlementAmount(String settlementAmount) {
		this.settlementAmount = settlementAmount;
	}
    @Column(name="PAYMENTFREQUENCY")
	public String getPaymentfrequency() {
		return paymentfrequency;
	}
	public void setPaymentfrequency(String paymentfrequency) {
		this.paymentfrequency = paymentfrequency;
	}
    @Column(name="ACTUALPAYMENTAMOUNT")
	public String getActualPaymentAmount() {
		return actualPaymentAmount;
	}
	public void setActualPaymentAmount(String actualPaymentAmount) {
		this.actualPaymentAmount = actualPaymentAmount;
	}
    @Column(name="ERRORCODE")
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
    @Column(name="DATEOFENTRYFORCIBILREMARKSCODE")
	public String getDateOfEntryForCibilRemarkscode() {
		return dateOfEntryForCibilRemarkscode;
	}
	public void setDateOfEntryForCibilRemarkscode(String dateOfEntryForCibilRemarkscode) {
		this.dateOfEntryForCibilRemarkscode = dateOfEntryForCibilRemarkscode;
	}
    @Column(name="CIBILREMARKSCODE")
	public String getCibilRemarksCode() {
		return cibilRemarksCode;
	}
	public void setCibilRemarksCode(String cibilRemarksCode) {
		this.cibilRemarksCode = cibilRemarksCode;
	}
    @Column(name="DATEOFENTRYFORERRORCODE")
	public String getDateOfEntryForErrorcode() {
		return dateOfEntryForErrorcode;
	}
	public void setDateOfEntryForErrorcode(String dateOfEntryForErrorcode) {
		this.dateOfEntryForErrorcode = dateOfEntryForErrorcode;
	}
    @Column(name="DATEOFEREMARKSCODE")
	public String getDateOfERemarksCode() {
		return dateOfERemarksCode;
	}
	public void setDateOfERemarksCode(String dateOfERemarksCode) {
		this.dateOfERemarksCode = dateOfERemarksCode;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_ACC_SQC")
	@SequenceGenerator(name = "IB_CBL_ACC_SQC", sequenceName = "IB_CBL_ACC_SQC")
	public Long getAccountId() {
		return AccountId;
	}
	public void setAccountId(Long accountId) {
		AccountId = accountId;
	}
	@Transient
	public String getTypeOfColleteral() {
		return typeOfColleteral;
	}
	public void setTypeOfColleteral(String typeOfColleteral) {
		this.typeOfColleteral = typeOfColleteral;
	}
    @OneToMany(fetch=FetchType.LAZY,mappedBy="accountdetails",cascade=CascadeType.ALL)
	public List<PaymentHistoryUI> getDaysPastDueAssetClassificationData() {
		return daysPastDueAssetClassificationData;
	}

	public void setDaysPastDueAssetClassificationData(List<PaymentHistoryUI> daysPastDueAssetClassificationData) {
		this.daysPastDueAssetClassificationData = daysPastDueAssetClassificationData;
	}
	@Override
	public String toString() {
		return "AccountDetails [AccountId=" + AccountId + ", reportingMemberName=" + reportingMemberName
				+ ", accountNumber=" + accountNumber + ", accountType=" + accountType + ", ownershipIndicator="
				+ ownershipIndicator + ", dateOpened=" + dateOpened + ", dateOfLastPayment=" + dateOfLastPayment
				+ ", dateClose=" + dateClose + ", dateReportedVerified=" + dateReportedVerified + ", sanctionAmount="
				+ sanctionAmount + ", currntBalance=" + currntBalance + ", amountOverdue=" + amountOverdue
				+ ", paymentHistory1=" + paymentHistory1 + ", paymentHistory2=" + paymentHistory2
				+ ", paymentHstrStartDate=" + paymentHstrStartDate + ", paymentHistoryEndDate=" + paymentHistoryEndDate
				+ ", suitfilledDefault=" + suitfilledDefault + ", writtenOffStatus=" + writtenOffStatus
				+ ", valueOfColleteral=" + valueOfColleteral + ", typeOfColleteral=" + typeOfColleteral
				+ ", creditLimit=" + creditLimit + ", cashLimit=" + cashLimit + ", rateOfInterest=" + rateOfInterest
				+ ", repaymentTenure=" + repaymentTenure + ", emiAmount=" + emiAmount + ", writtenOffAmountTotal="
				+ writtenOffAmountTotal + ", writtenOffAmountPrincipal=" + writtenOffAmountPrincipal
				+ ", settlementAmount=" + settlementAmount + ", paymentfrequency=" + paymentfrequency
				+ ", actualPaymentAmount=" + actualPaymentAmount + ", errorCode=" + errorCode
				+ ", dateOfEntryForCibilRemarkscode=" + dateOfEntryForCibilRemarkscode + ", cibilRemarksCode="
				+ cibilRemarksCode + ", dateOfEntryForErrorcode=" + dateOfEntryForErrorcode + ", dateOfERemarksCode="
				+ dateOfERemarksCode + ", errordisputecode1=" + errordisputecode1 + ", errordisputcode2="
				+ errordisputcode2 + ", daysPastDueAssetClassificationData=" + daysPastDueAssetClassificationData
				+ "]";
	}
   /* @Column(name="CIBIL_UNIQUE_ID")
	public String getCibil_unique_id() {
		return cibil_unique_id;
	}
	public void setCibil_unique_id(String cibil_unique_id) {
		this.cibil_unique_id = cibil_unique_id;
	}*/
	@Transient
	public String getDateofedisputeremarkscode() {
		return dateofedisputeremarkscode;
	}
	public void setDateofedisputeremarkscode(String dateofedisputeremarkscode) {
		this.dateofedisputeremarkscode = dateofedisputeremarkscode;
	}
	@Transient
	public String getDATEOFEERRORCODE() {
		return DATEOFEERRORCODE;
	}
	public void setDATEOFEERRORCODE(String dATEOFEERRORCODE) {
		DATEOFEERRORCODE = dATEOFEERRORCODE;
	}
	@Transient
	public String getDATEOFFENTRYFORERRORCODE() {
		return DATEOFFENTRYFORERRORCODE;
	}
	public void setDATEOFFENTRYFORERRORCODE(String dATEOFFENTRYFORERRORCODE) {
		DATEOFFENTRYFORERRORCODE = dATEOFFENTRYFORERRORCODE;
	}
	@Transient
	public String getDATEOFENTRYOFDISPUTECODE() {
		return DATEOFENTRYOFDISPUTECODE;
	}
	public void setDATEOFENTRYOFDISPUTECODE(String dATEOFENTRYOFDISPUTECODE) {
		DATEOFENTRYOFDISPUTECODE = dATEOFENTRYOFDISPUTECODE;
	}
	@Transient
	public String getDATEREPORTED() {
		return DATEREPORTED;
	}
	public void setDATEREPORTED(String dATEREPORTED) {
		DATEREPORTED = dATEREPORTED;
	}
	@ManyToOne
	@JoinColumn(name="CIBIL_UNIQUE_ID")
	public CibilResponsePayload getCibilresponsepayload() {
		return cibilresponsepayload;
	}
	public void setCibilresponsepayload(CibilResponsePayload cibilresponsepayload) {
		this.cibilresponsepayload = cibilresponsepayload;
	}
}
