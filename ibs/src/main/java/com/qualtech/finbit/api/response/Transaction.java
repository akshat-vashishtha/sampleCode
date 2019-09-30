package com.qualtech.finbit.api.response;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_FINBIT_RES_TRANS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Transaction 
{
	@Id
	@GeneratedValue(generator = "IB_FINBIT_RES_TRANS_SQC")
	@SequenceGenerator(name = "IB_FINBIT_RES_TRANS_SQC", sequenceName = "IB_FINBIT_RES_TRANS_SQC")
	private int seqId;
	private String amount;
	private String balanceAfterTransaction;
	private String dateTime;
	private String description;
	private String firstLevelCategory;
	private String remark;
	private String secondLevelCategory;
	private String transactionNumber;
	private String type;
	private String valueDate;
	@ManyToOne
	@JoinColumn(name="EID",nullable=false)
	@JsonIgnore
	private FinbitResResult result;
	
	
	public FinbitResResult getResult() {
		return result;
	}
	public void setResult(FinbitResResult result) {
		this.result = result;
	}
	public int getSeqId() {
		return seqId;
	}
	public void setSeqId(int seqId) {
		this.seqId = seqId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBalanceAfterTransaction() {
		return balanceAfterTransaction;
	}
	public void setBalanceAfterTransaction(String balanceAfterTransaction) {
		this.balanceAfterTransaction = balanceAfterTransaction;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFirstLevelCategory() {
		return firstLevelCategory;
	}
	public void setFirstLevelCategory(String firstLevelCategory) {
		this.firstLevelCategory = firstLevelCategory;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSecondLevelCategory() {
		return secondLevelCategory;
	}
	public void setSecondLevelCategory(String secondLevelCategory) {
		this.secondLevelCategory = secondLevelCategory;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValueDate() {
		return valueDate;
	}
	public void setValueDate(String valueDate) {
		this.valueDate = valueDate;
	}
	@Override
	public String toString() {
		return "Transaction [amount=" + amount + ", balanceAfterTransaction=" + balanceAfterTransaction + ", dateTime="
				+ dateTime + ", description=" + description + ", firstLevelCategory=" + firstLevelCategory + ", remark="
				+ remark + ", secondLevelCategory=" + secondLevelCategory + ", transactionNumber=" + transactionNumber
				+ ", type=" + type + ", valueDate=" + valueDate + "]";
	}

}
