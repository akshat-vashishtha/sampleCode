package com.qualtech.crif.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="IB_CRF_INDV_LOAN_DETAILS")
@Entity
public class IndvLoanDetails 
{

 private String	current_balance;
 private String	installment_amount;
 private String frequency;
 private String dispursed_amount;
 private String overdue_amount;
 private String acct_type;
 private String write_off_amount;
 private String inq_cnt;
 private String account_number;
 private String dpd;
 private String dispursed_date;
 private String loancycle_id;
 private String combined_payment_history;
 private String infosan;
 private String status;
 private Long indv_loan_dtl_id;
 private IndvResponseDetails  indvresponsedetails; 

@JoinColumn(name="INDV_RSP_DTL_ID", nullable=false) 
@OneToOne
public IndvResponseDetails getIndvresponsedetails() {
	return indvresponsedetails;
}
public void setIndvresponsedetails(IndvResponseDetails indvresponsedetails) {
	this.indvresponsedetails = indvresponsedetails;
}

public String getCurrent_balance() {
	return current_balance;
}
public void setCurrent_balance(String current_balance) {
	this.current_balance = current_balance;
}
public String getInstallment_amount() {
	return installment_amount;
}
public void setInstallment_amount(String installment_amount) {
	this.installment_amount = installment_amount;
}
public String getFrequency() {
	return frequency;
}
public void setFrequency(String frequency) {
	this.frequency = frequency;
}
public String getDispursed_amount() {
	return dispursed_amount;
}
public void setDispursed_amount(String dispursed_amount) {
	this.dispursed_amount = dispursed_amount;
}
public String getOverdue_amount() {
	return overdue_amount;
}
public void setOverdue_amount(String overdue_amount) {
	this.overdue_amount = overdue_amount;
}
public String getAcct_type() {
	return acct_type;
}
public void setAcct_type(String acct_type) {
	this.acct_type = acct_type;
}
public String getWrite_off_amount() {
	return write_off_amount;
}
public void setWrite_off_amount(String write_off_amount) {
	this.write_off_amount = write_off_amount;
}
public String getInq_cnt() {
	return inq_cnt;
}
public void setInq_cnt(String inq_cnt) {
	this.inq_cnt = inq_cnt;
}
public String getAccount_number() {
	return account_number;
}
public void setAccount_number(String account_number) {
	this.account_number = account_number;
}
public String getDpd() {
	return dpd;
}
public void setDpd(String dpd) {
	this.dpd = dpd;
}
public String getDispursed_date() {
	return dispursed_date;
}
public void setDispursed_date(String dispursed_date) {
	this.dispursed_date = dispursed_date;
}
public String getLoancycle_id() {
	return loancycle_id;
}
public void setLoancycle_id(String loancycle_id) {
	this.loancycle_id = loancycle_id;
}
public String getCombined_payment_history() {
	return combined_payment_history;
}
public void setCombined_payment_history(String combined_payment_history) {
	this.combined_payment_history = combined_payment_history;
}
public String getInfosan() {
	return infosan;
}
public void setInfosan(String infosan) {
	this.infosan = infosan;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_INDV_LOAN_DETAILS_SQC")
@SequenceGenerator(name = "IB_CRF_INDV_LOAN_DETAILS_SQC", sequenceName = "IB_CRF_INDV_LOAN_DETAILS_SQC", allocationSize = 1)
public Long getIndv_loan_dtl_id() {
	return indv_loan_dtl_id;
}
public void setIndv_loan_dtl_id(Long indv_loan_dtl_id) {
	this.indv_loan_dtl_id = indv_loan_dtl_id;
}
	
	
	
}
