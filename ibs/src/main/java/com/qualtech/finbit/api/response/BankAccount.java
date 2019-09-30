package com.qualtech.finbit.api.response;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_FINBIT_RES_BANK_ACC")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankAccount
{
	@Id
	@GeneratedValue(generator = "IB_FINBIT_RES_BANK_ACC_SQC")
	@SequenceGenerator(name = "IB_FINBIT_RES_BANK_ACC_SQC", sequenceName = "IB_FINBIT_RES_BANK_ACC_SQC")
	private int seqId;
	@Column(name="ACC_DTL_LIST_PAGE")
	private String accountDetailsForAccountListPage;
    private String accountHolder;
    private String accountLimit;
    private String accountNo; 
    @Embedded
    private AccountType accountType;
	private String address;
    private String availableCashLimit;
    private String availableCreditLimit;
    @Embedded
    private Bank bank;
    private String bankAccountUID; 
    private String bankAddress;
    private String bankCredentialCO;
    @Transient
    private List<BankTransactionList> bankTransactionList;
    private String branchAddress;
    private String cifNumber;
    private String client;
    private String creditCardNo;
    private String creditLimit;
    private String crnNo;
    private String currentCashAdvance;
    private String currentPurchaseCharges;
    private String customer;
    private String customerID;
    private String customerPhoneNo;
    private String dueDate;
    private String email;
    private String fromDate;
    private String ifsCode;
    private String isValidBankStatement;
    private String lastPaymentReceived;
    private String micrCode;
    private String minAmountDue;
    private String openingBalance;
    private String pan;
    private String pointsEarned;
    private String prevBalance;
    private String relationshipWithBank;
    private String toDate;
    private String totalAmountDue;
    private String uploadBankStatementCO;
    @OneToOne
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
	public String getAccountDetailsForAccountListPage() {
		return accountDetailsForAccountListPage;
	}
	public void setAccountDetailsForAccountListPage(String accountDetailsForAccountListPage) {
		this.accountDetailsForAccountListPage = accountDetailsForAccountListPage;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getAccountLimit() {
		return accountLimit;
	}
	public void setAccountLimit(String accountLimit) {
		this.accountLimit = accountLimit;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAvailableCashLimit() {
		return availableCashLimit;
	}
	public void setAvailableCashLimit(String availableCashLimit) {
		this.availableCashLimit = availableCashLimit;
	}
	public String getAvailableCreditLimit() {
		return availableCreditLimit;
	}
	public void setAvailableCreditLimit(String availableCreditLimit) {
		this.availableCreditLimit = availableCreditLimit;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	public String getBankAccountUID() {
		return bankAccountUID;
	}
	public void setBankAccountUID(String bankAccountUID) {
		this.bankAccountUID = bankAccountUID;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
	public String getBankCredentialCO() {
		return bankCredentialCO;
	}
	public void setBankCredentialCO(String bankCredentialCO) {
		this.bankCredentialCO = bankCredentialCO;
	}
	
	public List<BankTransactionList> getBankTransactionList() {
		return bankTransactionList;
	}
	public void setBankTransactionList(List<BankTransactionList> bankTransactionList) {
		this.bankTransactionList = bankTransactionList;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	public String getCifNumber() {
		return cifNumber;
	}
	public void setCifNumber(String cifNumber) {
		this.cifNumber = cifNumber;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getCreditCardNo() {
		return creditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}
	public String getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(String creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getCrnNo() {
		return crnNo;
	}
	public void setCrnNo(String crnNo) {
		this.crnNo = crnNo;
	}
	public String getCurrentCashAdvance() {
		return currentCashAdvance;
	}
	public void setCurrentCashAdvance(String currentCashAdvance) {
		this.currentCashAdvance = currentCashAdvance;
	}
	public String getCurrentPurchaseCharges() {
		return currentPurchaseCharges;
	}
	public void setCurrentPurchaseCharges(String currentPurchaseCharges) {
		this.currentPurchaseCharges = currentPurchaseCharges;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerPhoneNo() {
		return customerPhoneNo;
	}
	public void setCustomerPhoneNo(String customerPhoneNo) {
		this.customerPhoneNo = customerPhoneNo;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getIfsCode() {
		return ifsCode;
	}
	public void setIfsCode(String ifsCode) {
		this.ifsCode = ifsCode;
	}
	public String getIsValidBankStatement() {
		return isValidBankStatement;
	}
	public void setIsValidBankStatement(String isValidBankStatement) {
		this.isValidBankStatement = isValidBankStatement;
	}
	public String getLastPaymentReceived() {
		return lastPaymentReceived;
	}
	public void setLastPaymentReceived(String lastPaymentReceived) {
		this.lastPaymentReceived = lastPaymentReceived;
	}
	public String getMicrCode() {
		return micrCode;
	}
	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}
	public String getMinAmountDue() {
		return minAmountDue;
	}
	public void setMinAmountDue(String minAmountDue) {
		this.minAmountDue = minAmountDue;
	}
	public String getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(String openingBalance) {
		this.openingBalance = openingBalance;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPointsEarned() {
		return pointsEarned;
	}
	public void setPointsEarned(String pointsEarned) {
		this.pointsEarned = pointsEarned;
	}
	public String getPrevBalance() {
		return prevBalance;
	}
	public void setPrevBalance(String prevBalance) {
		this.prevBalance = prevBalance;
	}
	public String getRelationshipWithBank() {
		return relationshipWithBank;
	}
	public void setRelationshipWithBank(String relationshipWithBank) {
		this.relationshipWithBank = relationshipWithBank;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getTotalAmountDue() {
		return totalAmountDue;
	}
	public void setTotalAmountDue(String totalAmountDue) {
		this.totalAmountDue = totalAmountDue;
	}
	public String getUploadBankStatementCO() {
		return uploadBankStatementCO;
	}
	public void setUploadBankStatementCO(String uploadBankStatementCO) {
		this.uploadBankStatementCO = uploadBankStatementCO;
	}
	@Override
	public String toString() {
		return "BankAccount [accountDetailsForAccountListPage=" + accountDetailsForAccountListPage + ", accountHolder="
				+ accountHolder + ", accountLimit=" + accountLimit + ", accountNo=" + accountNo + ", accountType="
				+ accountType + ", address=" + address + ", availableCashLimit=" + availableCashLimit
				+ ", availableCreditLimit=" + availableCreditLimit + ", bank=" + bank + ", bankAccountUID="
				+ bankAccountUID + ", bankAddress=" + bankAddress + ", bankCredentialCO=" + bankCredentialCO
				+ ", bankTransactionList=" + bankTransactionList + ", branchAddress=" + branchAddress + ", cifNumber="
				+ cifNumber + ", client=" + client + ", creditCardNo=" + creditCardNo + ", creditLimit=" + creditLimit
				+ ", crnNo=" + crnNo + ", currentCashAdvance=" + currentCashAdvance + ", currentPurchaseCharges="
				+ currentPurchaseCharges + ", customer=" + customer + ", customerID=" + customerID
				+ ", customerPhoneNo=" + customerPhoneNo + ", dueDate=" + dueDate + ", email=" + email + ", fromDate="
				+ fromDate + ", ifsCode=" + ifsCode + ", isValidBankStatement=" + isValidBankStatement
				+ ", lastPaymentReceived=" + lastPaymentReceived + ", micrCode=" + micrCode + ", minAmountDue="
				+ minAmountDue + ", openingBalance=" + openingBalance + ", pan=" + pan + ", pointsEarned="
				+ pointsEarned + ", prevBalance=" + prevBalance + ", relationshipWithBank=" + relationshipWithBank
				+ ", toDate=" + toDate + ", totalAmountDue=" + totalAmountDue + ", uploadBankStatementCO="
				+ uploadBankStatementCO + "]";
	}
    
    
    
}
