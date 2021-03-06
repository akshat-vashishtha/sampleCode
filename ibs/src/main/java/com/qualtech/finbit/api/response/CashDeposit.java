package com.qualtech.finbit.api.response;

import javax.persistence.Column;
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
@Table(name="IB_FINBIT_RES_CASH_DEPO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CashDeposit 
{

	@Id
	@GeneratedValue(generator = "IB_FINBIT_RES_CASH_DEPO_SQC")
	@SequenceGenerator(name = "IB_FINBIT_RES_CASH_DEPO_SQC", sequenceName = "IB_FINBIT_RES_CASH_DEPO_SQC")
	private int seqId;
	private String amount;
	private String balanceAfterTransaction;
	private String bank;
	private String category;
	@Column(name="DATE_")
	private String date;
	private String description;
	private String total;
	private String transactionType;
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
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	@Override
	public String toString() {
		return "CashDeposit [amount=" + amount + ", balanceAfterTransaction=" + balanceAfterTransaction + ", bank="
				+ bank + ", category=" + category + ", date=" + date + ", description=" + description + ", total="
				+ total + ", transactionType=" + transactionType + "]";
	}
	
	
	
}
