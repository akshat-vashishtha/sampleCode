package com.qualtech.finbit.api.response;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_FINBIT_RES_PAYLOAD")
@JsonIgnoreProperties(ignoreUnknown=true)
public class FinbitResResult {

	@Id
	@GeneratedValue(generator = "IB_FINBIT_RES_PAYLOAD_SQC")
	@SequenceGenerator(name = "IB_FINBIT_RES_PAYLOAD_SQC", sequenceName = "IB_FINBIT_RES_PAYLOAD_SQC")
	private int seqId;
	
	private int eid;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "result" ,cascade=CascadeType.ALL)
	private List<AtmWithdrawls> atm_withdrawls;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "result" ,cascade=CascadeType.ALL)
	private List<AverageMonthlyBalance> averageMonthlyBalance;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "result" ,cascade=CascadeType.ALL)
	private List<AverageQuarterlyBalance> averageQuarterlyBalance;
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "result" ,cascade=CascadeType.ALL) 
	private BankAccount bank_account;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "result" ,cascade=CascadeType.ALL)
	private List<CashDeposit> cash_deposits;
	@Transient
	private List<CreditCardPayments> credit_card_payments;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "result" ,cascade=CascadeType.ALL)
	private List<Expenses> expenses;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "result" ,cascade=CascadeType.ALL)
	private List<HighValueTransaction> high_value_transactions;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "result" ,cascade=CascadeType.ALL)
	private List<Incomes> incomes;
	@Transient
	private List<InternalTransactionList> internalTransactionList;
	@Transient
	private List<Investments> investments;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "result" ,cascade=CascadeType.ALL)
	private List<MinimumBalance> minimum_balances;
	private String[] missingMonths;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "result" ,cascade=CascadeType.ALL)
	private List<MoneyReceivedTransactions> money_received_transactions;
	@Transient
	private List<RentPayments> rent_payments;
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "result" ,cascade=CascadeType.ALL)
	private List<Transaction> transactions;
	
	
	public int getSeqId() {
		return seqId;
	}
	public void setSeqId(int seqId) {
		this.seqId = seqId;
	}
	
	public List<AtmWithdrawls> getAtm_withdrawls() {
		if(this.atm_withdrawls!=null) {
			for(AtmWithdrawls at: atm_withdrawls) {
				at.setResult(this);
			}
		}
		return atm_withdrawls;
	}
	public void setAtm_withdrawls(List<AtmWithdrawls> atm_withdrawls) {
		this.atm_withdrawls = atm_withdrawls;
	}
	public List<AverageMonthlyBalance> getAverageMonthlyBalance() {
		if(this.averageMonthlyBalance!=null) {
			for(AverageMonthlyBalance at: averageMonthlyBalance) {
				at.setResult(this);
			}
		}
		return averageMonthlyBalance;
	}
	public void setAverageMonthlyBalance(List<AverageMonthlyBalance> averageMonthlyBalance) {
		this.averageMonthlyBalance = averageMonthlyBalance;
	}
	public List<AverageQuarterlyBalance> getAverageQuarterlyBalance() {
		if(this.averageQuarterlyBalance!=null) {
			for(AverageQuarterlyBalance at: averageQuarterlyBalance) {
				at.setResult(this);
			}
		}
		return averageQuarterlyBalance;
	}
	public void setAverageQuarterlyBalance(List<AverageQuarterlyBalance> averageQuarterlyBalance) {
		this.averageQuarterlyBalance = averageQuarterlyBalance;
	}
	public BankAccount getBank_account() {
		bank_account.setResult(this);
		return bank_account;
	}
	public void setBank_account(BankAccount bank_account) {
		this.bank_account = bank_account;
	}
	public List<CashDeposit> getCash_deposits() {
		if(this.cash_deposits!=null) {
			for(CashDeposit at: cash_deposits) {
				at.setResult(this);
			}
		}
		return cash_deposits;
	}
	public void setCash_deposits(List<CashDeposit> cash_deposits) {
		this.cash_deposits = cash_deposits;
	}
	public List<CreditCardPayments> getCredit_card_payments() {
		return credit_card_payments;
	}
	public void setCredit_card_payments(List<CreditCardPayments> credit_card_payments) {
		this.credit_card_payments = credit_card_payments;
	}
	public List<Expenses> getExpenses() {
		if(this.expenses!=null) {
			for(Expenses at: expenses) {
				at.setResult(this);
			}
		}
		return expenses;
	}
	public void setExpenses(List<Expenses> expenses) {
		this.expenses = expenses;
	}
	public List<HighValueTransaction> getHigh_value_transactions() {
		if(this.high_value_transactions!=null) {
			for(HighValueTransaction at: high_value_transactions) {
				at.setResult(this);
			}
		}
		return high_value_transactions;
	}
	public void setHigh_value_transactions(List<HighValueTransaction> high_value_transactions) {
		this.high_value_transactions = high_value_transactions;
	}
	public List<Incomes> getIncomes() {
		if(this.incomes!=null) {
			for(Incomes at: incomes) {
				at.setResult(this);
			}
		}
		return incomes;
	}
	public void setIncomes(List<Incomes> incomes) {
		this.incomes = incomes;
	}
	public List<InternalTransactionList> getInternalTransactionList() {
		
		return internalTransactionList;
	}
	public void setInternalTransactionList(List<InternalTransactionList> internalTransactionList) {
		this.internalTransactionList = internalTransactionList;
	}
	public List<Investments> getInvestments() {
		return investments;
	}
	public void setInvestments(List<Investments> investments) {
		this.investments = investments;
	}
	public List<MinimumBalance> getMinimum_balances() {
		if(this.minimum_balances!=null) {
			for(MinimumBalance at: minimum_balances) {
				at.setResult(this);
			}
		}
		return minimum_balances;
	}
	public void setMinimum_balances(List<MinimumBalance> minimum_balances) {
		this.minimum_balances = minimum_balances;
	}
	
	public String[] getMissingMonths() {
		return missingMonths;
	}
	public void setMissingMonths(String[] missingMonths) {
		this.missingMonths = missingMonths;
	}
	public List<MoneyReceivedTransactions> getMoney_received_transactions() {
		if(this.money_received_transactions!=null) {
			for(MoneyReceivedTransactions at: money_received_transactions) {
				at.setResult(this);
			}
		}
		return money_received_transactions;
	}
	public void setMoney_received_transactions(List<MoneyReceivedTransactions> money_received_transactions) {
		this.money_received_transactions = money_received_transactions;
	}
	public List<RentPayments> getRent_payments() {
		return rent_payments;
	}
	public void setRent_payments(List<RentPayments> rent_payments) {
		this.rent_payments = rent_payments;
	}
	public List<Transaction> getTransactions() {
		if(this.transactions!=null) {
			for(Transaction at: transactions) {
				at.setResult(this);
			}
		}
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	@Override
	public String toString() {
		return "FinbitResponse [atm_withdrawls=" + atm_withdrawls + ", averageMonthlyBalance=" + averageMonthlyBalance
				+ ", averageQuarterlyBalance=" + averageQuarterlyBalance + ", bank_account=" + bank_account
				+ ", cash_deposits=" + cash_deposits + ", credit_card_payments=" + credit_card_payments + ", expenses="
				+ expenses + ", high_value_transactions=" + high_value_transactions + ", incomes=" + incomes
				+ ", internalTransactionList=" + internalTransactionList + ", investments=" + investments
				+ ", minimum_balances=" + minimum_balances + ", missingMonths=" + missingMonths
				+ ", money_received_transactions=" + money_received_transactions + ", rent_payments=" + rent_payments
				+ ", transactions=" + transactions + "]";
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	


}
