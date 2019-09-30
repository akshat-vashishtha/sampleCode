package com.qualtech.finbit.api.response;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FinbitResponse implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5953671956011248962L;
	private List<AtmWithdrawls> atm_withdrawls;
	private List<AverageMonthlyBalance> averageMonthlyBalance;
	private List<AverageQuarterlyBalance> averageQuarterlyBalance;
	private BankAccount bank_account;
	private List<CashDeposit> cash_deposits;
	private List<CreditCardPayments> credit_card_payments;
	private List<Expenses> expenses;
	private List<HighValueTransaction> high_value_transactions;
	private List<Incomes> incomes;
	private List<InternalTransactionList> internalTransactionList;
	private List<Investments> investments;
	private List<MinimumBalance> minimum_balances;
	private List<MissingMonths> missingMonths;
	private List<MoneyReceivedTransactions> money_received_transactions;
	private List<RentPayments> rent_payments;
	private List<Transaction> transactions;
	
	public List<AtmWithdrawls> getAtm_withdrawls() {
		return atm_withdrawls;
	}
	public void setAtm_withdrawls(List<AtmWithdrawls> atm_withdrawls) {
		this.atm_withdrawls = atm_withdrawls;
	}
	public List<AverageMonthlyBalance> getAverageMonthlyBalance() {
		return averageMonthlyBalance;
	}
	public void setAverageMonthlyBalance(List<AverageMonthlyBalance> averageMonthlyBalance) {
		this.averageMonthlyBalance = averageMonthlyBalance;
	}
	public List<AverageQuarterlyBalance> getAverageQuarterlyBalance() {
		return averageQuarterlyBalance;
	}
	public void setAverageQuarterlyBalance(List<AverageQuarterlyBalance> averageQuarterlyBalance) {
		this.averageQuarterlyBalance = averageQuarterlyBalance;
	}
	public BankAccount getBank_account() {
		return bank_account;
	}
	public void setBank_account(BankAccount bank_account) {
		this.bank_account = bank_account;
	}
	public List<CashDeposit> getCash_deposits() {
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
		return expenses;
	}
	public void setExpenses(List<Expenses> expenses) {
		this.expenses = expenses;
	}
	public List<HighValueTransaction> getHigh_value_transactions() {
		return high_value_transactions;
	}
	public void setHigh_value_transactions(List<HighValueTransaction> high_value_transactions) {
		this.high_value_transactions = high_value_transactions;
	}
	public List<Incomes> getIncomes() {
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
		return minimum_balances;
	}
	public void setMinimum_balances(List<MinimumBalance> minimum_balances) {
		this.minimum_balances = minimum_balances;
	}
	public List<MissingMonths> getMissingMonths() {
		return missingMonths;
	}
	public void setMissingMonths(List<MissingMonths> missingMonths) {
		this.missingMonths = missingMonths;
	}
	public List<MoneyReceivedTransactions> getMoney_received_transactions() {
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
	
}
