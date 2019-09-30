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
@Table(name="IB_CBL_EMPLOYMENTDETAIL")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EmploymentDetail implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1433845498399835788L;
	private Long employeeId;
	private String accountType="";
	private String dateReportedandCertified="";
	private String occupationCode="";
	private String  income="0";
	private String grosssIncomeindicator="";
	private String  monthlyIncomeIndicator="";
	private String  errordateCode="";
	private String  errorcode="";
	private String dateOfEntryforremarksCode="";
	private String  cibilremarkscode="";
	private String  datedisputeRemarksCode="";
	private String remarkscode1="";
	private String remarkscode2="";
	@JsonIgnore
	private CibilResponsePayload cibilresponsepayload;
	@Column(name="ACCOUNTTYPE")
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	@Column(name="DATEREPORTEDANDCERTIFIED")
	public String getDateReportedandCertified() {
		return dateReportedandCertified;
	}
	public void setDateReportedandCertified(String dateReportedandCertified) {
		this.dateReportedandCertified = dateReportedandCertified;
	}
	@Column(name="OCCUPATIONCODE")
	public String getOccupationCode() {
		return occupationCode;
	}
	public void setOccupationCode(String occupationCode) {
		this.occupationCode = occupationCode;
	}
	@Column(name="INCOME")
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	@Column(name="GROSSSINCOMEINDICATOR")
	public String getGrosssIncomeindicator() {
		return grosssIncomeindicator;
	}
	public void setGrosssIncomeindicator(String grosssIncomeindicator) {
		this.grosssIncomeindicator = grosssIncomeindicator;
	}
	@Column(name="MONTHLYINCOMEINDICATOR")
	public String getMonthlyIncomeIndicator() {
		return monthlyIncomeIndicator;
	}
	public void setMonthlyIncomeIndicator(String monthlyIncomeIndicator) {
		this.monthlyIncomeIndicator = monthlyIncomeIndicator;
	}
	@Column(name="ERRORDATECODE")
	public String getErrordateCode() {
		return errordateCode;
	}
	public void setErrordateCode(String errordateCode) {
		this.errordateCode = errordateCode;
	}
	@Column(name="ERRORCODE")
	public String getErrorcode() {
		return errorcode;
	}
	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}
	@Column(name="DATEOFENTRYFORREMARKSCODE")
	public String getDateOfEntryforremarksCode() {
		return dateOfEntryforremarksCode;
	}
	public void setDateOfEntryforremarksCode(String dateOfEntryforremarksCode) {
		this.dateOfEntryforremarksCode = dateOfEntryforremarksCode;
	}
	@Column(name="CIBILREMARKSCODE")
	public String getCibilremarkscode() {
		return cibilremarkscode;
	}
	public void setCibilremarkscode(String cibilremarkscode) {
		this.cibilremarkscode = cibilremarkscode;
	}
	@Column(name="DATEDISPUTEREMARKSCODE")
	public String getDatedisputeRemarksCode() {
		return datedisputeRemarksCode;
	}
	public void setDatedisputeRemarksCode(String datedisputeRemarksCode) {
		this.datedisputeRemarksCode = datedisputeRemarksCode;
	}
	@Column(name="REMARKSCODE1")
	public String getRemarkscode1() {
		return remarkscode1;
	}
	public void setRemarkscode1(String remarkscode1) {
		this.remarkscode1 = remarkscode1;
	}
	@Column(name="REMARKSCODE2")
	public String getRemarkscode2() {
		return remarkscode2;
	}
	public void setRemarkscode2(String remarkscode2) {
		this.remarkscode2 = remarkscode2;
	}
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_EMP_SQC")
    @SequenceGenerator(name = "IB_CBL_EMP_SQC", sequenceName = "IB_CBL_EMP_SQC")
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	@Override
	public String toString() {
		return "EmploymentDetail [employeeId=" + employeeId + ", accountType=" + accountType
				+ ", dateReportedandCertified=" + dateReportedandCertified + ", occupationCode=" + occupationCode
				+ ", income=" + income + ", grosssIncomeindicator=" + grosssIncomeindicator
				+ ", monthlyIncomeIndicator=" + monthlyIncomeIndicator + ", errordateCode=" + errordateCode
				+ ", errorcode=" + errorcode + ", dateOfEntryforremarksCode=" + dateOfEntryforremarksCode
				+ ", cibilremarkscode=" + cibilremarkscode + ", datedisputeRemarksCode=" + datedisputeRemarksCode
				+ ", remarkscode1=" + remarkscode1 + ", remarkscode2=" + remarkscode2 + "]";
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
