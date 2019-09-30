package com.qualtech.karza.api.response;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_LLPIDFC_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyLLPIdentificationResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
    private String uniqueid;
	private String whetherListedOrNot;
	private String companyStatus;
	private String rocCode;
	private String companySubCategory;
	private String emailId;
	private String suspendedAtStockExchange;
	private String alternativeAddress;
	private String dateOfBalanceSheet;
	private String cin;
	private String authorisedCapital;
	private String companyCategory;
	private String dateOfLastAGM;
	private String companyName;
	private String paidUpCapital;
	private String registeredAddress;
	private String numberOfMembers;
	private String classOfCompany ;
	private String registrationNumber;
	private String dateofIncorporation;
	private String correlationid;
	@Column(name="WHETHER_LISTED_OR_NOT")
	public String getWhetherListedOrNot() {
		return whetherListedOrNot;
	}
	public void setWhetherListedOrNot(String whetherListedOrNot) {
		this.whetherListedOrNot = whetherListedOrNot;
	}
	@Column(name="COMPANY_STATUS")
	public String getCompanyStatus() {
		return companyStatus;
	}
	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}
	@Column(name="ROC_CODE")
	public String getRocCode() {
		return rocCode;
	}
	public void setRocCode(String rocCode) {
		this.rocCode = rocCode;
	}
	@Column(name="COMPANY_SUB_CATEGORY")
	public String getCompanySubCategory() {
		return companySubCategory;
	}
	public void setCompanySubCategory(String companySubCategory) {
		this.companySubCategory = companySubCategory;
	}
	@Column(name="EMAIL_ID")
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	@Column(name="SUSPENDED_AT_STOCK_EXCHANGE")
	public String getSuspendedAtStockExchange() {
		return suspendedAtStockExchange;
	}
	public void setSuspendedAtStockExchange(String suspendedAtStockExchange) {
		this.suspendedAtStockExchange = suspendedAtStockExchange;
	}
	@Column(name="ALTENATIVE_ADDRESS")
	public String getAlternativeAddress() {
		return alternativeAddress;
	}
	public void setAlternativeAddress(String alternativeAddress) {
		this.alternativeAddress = alternativeAddress;
	}
	@Column(name="DATE_OF_BALANCE_SHEET")
	public String getDateOfBalanceSheet() {
		return dateOfBalanceSheet;
	}
	public void setDateOfBalanceSheet(String dateOfBalanceSheet) {
		this.dateOfBalanceSheet = dateOfBalanceSheet;
	}
	@Column(name="CIN")
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	@Column(name="AUTHORISED_CAPITAL")
	public String getAuthorisedCapital() {
		return authorisedCapital;
	}
	public void setAuthorisedCapital(String authorisedCapital) {
		this.authorisedCapital = authorisedCapital;
	}
	@Column(name="COMPANY_CATEGORY")
	public String getCompanyCategory() {
		return companyCategory;
	}
	public void setCompanyCategory(String companyCategory) {
		this.companyCategory = companyCategory;
	}
	@Column(name="DATE_OF_LAST_AGM")
	public String getDateOfLastAGM() {
		return dateOfLastAGM;
	}
	public void setDateOfLastAGM(String dateOfLastAGM) {
		this.dateOfLastAGM = dateOfLastAGM;
	}
	@Column(name="COMAPANY_NAME")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Column(name="PAID_UP_CAPITAL")
	public String getPaidUpCapital() {
		return paidUpCapital;
	}
	public void setPaidUpCapital(String paidUpCapital) {
		this.paidUpCapital = paidUpCapital;
	}
	@Column(name="REGISTERED_ADDRESS")
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	@Column(name="NUMBER_OF_MEMBERS")
	public String getNumberOfMembers() {
		return numberOfMembers;
	}
	public void setNumberOfMembers(String numberOfMembers) {
		this.numberOfMembers = numberOfMembers;
	}
	@Column(name="CLASS_OF_COMPANY")
	public String getClassOfCompany() {
		return classOfCompany;
	}
	public void setClassOfCompany(String classOfCompany) {
		this.classOfCompany = classOfCompany;
	}
	@Column(name="REGISTRATION_NUMBER")
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	@Column(name="DATE_OF_INCORPORATION")
	public String getDateofIncorporation() {
		return dateofIncorporation;
	}
	public void setDateofIncorporation(String dateofIncorporation) {
		this.dateofIncorporation = dateofIncorporation;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "CompanyLLPIdentificationResult [whetherListedOrNot=" + whetherListedOrNot + ", companyStatus="
				+ companyStatus + ", rocCode=" + rocCode + ", companySubCategory=" + companySubCategory + ", emailId="
				+ emailId + ", suspendedAtStockExchange=" + suspendedAtStockExchange + ", alternativeAddress="
				+ alternativeAddress + ", dateOfBalanceSheet=" + dateOfBalanceSheet + ", cin=" + cin
				+ ", authorisedCapital=" + authorisedCapital + ", companyCategory=" + companyCategory
				+ ", dateOfLastAGM=" + dateOfLastAGM + ", companyName=" + companyName + ", paidUpCapital="
				+ paidUpCapital + ", registeredAddress=" + registeredAddress + ", numberOfMembers=" + numberOfMembers
				+ ", classOfCompany=" + classOfCompany + ", registrationNumber=" + registrationNumber
				+ ", dateofIncorporation=" + dateofIncorporation + "]";
	}
	@JsonIgnore
	@Id
	@Column(name="UNIQUEID")
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@JsonIgnore
	@Column(name="CORRELATION_ID")
	public String getCorrelationid() {
		return correlationid;
	}
	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}
	
	
		
}
