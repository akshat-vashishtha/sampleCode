package com.qualtech.cibil.api.entity;

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

import com.qualtech.cibil.api.response.CibilResponsePayload;
@Entity
@Table(name="IB_CBL_ACCOUNT_M_SUM")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountManualSummary implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4796061684877781852L;
	private Long summaryId;
	private String accountTotal="0";
	private String accountOverdue="0";
	private String accountZeroBalance="0";
	private String advanceHighCRSancAmt="";
	private String currentBalance="0";
	private String overdueBalance="0";
	private String suitfiled_wilfulCount="0";
	private String writtenOffAndSettleCount="0";
	private String recentDate="NA";
	private String oldestDate="NA";
	@JsonIgnore
	private CibilResponsePayload cibilresponsepayload;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_ACCOUNT_M_SUM_SQC")
    @SequenceGenerator(name = "IB_CBL_ACCOUNT_M_SUM_SQC", sequenceName = "IB_CBL_ACCOUNT_M_SUM_SQC")
	public Long getSummaryId() {
		return summaryId;
	}
	public void setSummaryId(Long summaryId) {
		this.summaryId = summaryId;
	}
	@Column(name="ACCOUNTTOTAL")
	public String getAccountTotal() {
		return accountTotal;
	}
	public void setAccountTotal(String accountTotal) {
		this.accountTotal = accountTotal;
	}
	@Column(name="ACCOUNTOVERDUE")
	public String getAccountOverdue() {
		return accountOverdue;
	}
	public void setAccountOverdue(String accountOverdue) {
		this.accountOverdue = accountOverdue;
	}
	@Column(name="ACCOUNTZEROBALANCE")
	public String getAccountZeroBalance() {
		return accountZeroBalance;
	}
	public void setAccountZeroBalance(String accountZeroBalance) {
		this.accountZeroBalance = accountZeroBalance;
	}
	@Column(name="ADVANCEHIGHCRSANCAMT")
	public String getAdvanceHighCRSancAmt() {
		return advanceHighCRSancAmt;
	}
	public void setAdvanceHighCRSancAmt(String advanceHighCRSancAmt) {
		this.advanceHighCRSancAmt = advanceHighCRSancAmt;
	}
	@Column(name="CURRENTBALANCE")
	public String getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}
	@Column(name="OVERDUEBALANCE")
	public String getOverdueBalance() {
		return overdueBalance;
	}
	public void setOverdueBalance(String overdueBalance) {
		this.overdueBalance = overdueBalance;
	}
	@Column(name="RECENTDATE")
	public String getRecentDate() {
		return recentDate;
	}
	public void setRecentDate(String recentDate) {
		this.recentDate = recentDate;
	}
	@Column(name="OLDESTDATE")
	public String getOldestDate() {
		return oldestDate;
	}
	public void setOldestDate(String oldestDate) {
		this.oldestDate = oldestDate;
	}
	@Column(name="SUITFILED_WILFULCOUNT")
	public String getSuitfiled_wilfulCount() {
		return suitfiled_wilfulCount;
	}
	public void setSuitfiled_wilfulCount(String suitfiled_wilfulCount) {
		this.suitfiled_wilfulCount = suitfiled_wilfulCount;
	}
	@Column(name="WRITTENOFFANDSETTLECOUNT")
	public String getWrittenOffAndSettleCount() {
		return writtenOffAndSettleCount;
	}
	public void setWrittenOffAndSettleCount(String writtenOffAndSettleCount) {
		this.writtenOffAndSettleCount = writtenOffAndSettleCount;
	}
	@Override
	public String toString() {
		return "AccountManualSummary [summaryId=" + summaryId
				+ ", accountTotal=" + accountTotal + ", accountOverdue="
				+ accountOverdue + ", accountZeroBalance=" + accountZeroBalance
				+ ", advanceHighCRSancAmt=" + advanceHighCRSancAmt
				+ ", currentBalance=" + currentBalance + ", overdueBalance="
				+ overdueBalance + ", suitfiled_wilfulCount="
				+ suitfiled_wilfulCount + ", writtenOffAndSettleCount="
				+ writtenOffAndSettleCount + ", recentDate=" + recentDate
				+ ", oldestDate=" + oldestDate + "]";
	}
	/*@Column(name="CIBIL_UNIQUE_ID")
	public String getCibil_unique_id() {
		return cibil_unique_id;
	}
	public void setCibil_unique_id(String cibil_unique_id) {
		this.cibil_unique_id = cibil_unique_id;
	}*/
	@OneToOne
	@JoinColumn(name="CIBIL_UNIQUE_ID")
	public CibilResponsePayload getCibilresponsepayload() {
		return cibilresponsepayload;
	}
	public void setCibilresponsepayload(CibilResponsePayload cibilresponsepayload) {
		this.cibilresponsepayload = cibilresponsepayload;
	}
}
