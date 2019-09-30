package com.qualtech.cibil.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.cibil.api.response.CibilResponsePayload;
@Entity
@Table(name="IB_CBL_ACCOUNTSUMMARY")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountSummary implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4796061684877781852L;
	private Long summaryId;
	private String accountNumber="NA";
	private String accountGroup="NA";
	private String liveClosedIndicator="NA";
	private String crHcrSanctionedAmount="NA";
	private String currentBalance="NA";
	@JsonIgnore
	private CibilResponsePayload cibilresponsepayload;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_ACCOUNTSUMMARY_SQC")
	@SequenceGenerator(name = "IB_CBL_ACCOUNTSUMMARY_SQC", sequenceName = "IB_CBL_ACCOUNTSUMMARY_SQC")
	public Long getSummaryId() {
		return summaryId;
	}
	public void setSummaryId(Long summaryId) {
		this.summaryId = summaryId;
	}
	@Column(name="ACCOUNTNUMBER")
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Column(name="ACCOUNTGROUP")
	public String getAccountGroup() {
		return accountGroup;
	}
	public void setAccountGroup(String accountGroup) {
		this.accountGroup = accountGroup;
	}
	@Column(name="LIVECLOSEDINDICATOR")
	public String getLiveClosedIndicator() {
		return liveClosedIndicator;
	}
	public void setLiveClosedIndicator(String liveClosedIndicator) {
		this.liveClosedIndicator = liveClosedIndicator;
	}
	@Column(name="CRHCRSANCTIONEDAMOUNT")
	public String getCrHcrSanctionedAmount() {
		return crHcrSanctionedAmount;
	}
	public void setCrHcrSanctionedAmount(String crHcrSanctionedAmount) {
		this.crHcrSanctionedAmount = crHcrSanctionedAmount;
	}
	@Column(name="CURRENTBALANCE")
	public String getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}
	@Override
	public String toString() {
		return "AccountSummary [summaryId=" + summaryId + ", accountNumber=" + accountNumber + ", accountGroup="
				+ accountGroup + ", liveClosedIndicator=" + liveClosedIndicator + ", crHcrSanctionedAmount="
				+ crHcrSanctionedAmount + ", currentBalance=" + currentBalance + "]";
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
