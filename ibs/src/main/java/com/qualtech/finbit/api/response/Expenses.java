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
@Table(name="IB_FINBIT_RES_EXPENSES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Expenses 
{

	@Id
	@GeneratedValue(generator = "IB_FINBIT_RES_EXPENSES_SQC")
	@SequenceGenerator(name = "IB_FINBIT_RES_EXPENSES_SQC", sequenceName = "IB_FINBIT_RES_EXPENSES_SQC")
	private int seqId;
	private String amount;
	private String bank;
	private String category;
	private String date;
	private String description;
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
	@Override
	public String toString() {
		return "Expenses [amount=" + amount + ", bank=" + bank + ", category=" + category + ", date=" + date
				+ ", description=" + description + "]";
	}
	
}
