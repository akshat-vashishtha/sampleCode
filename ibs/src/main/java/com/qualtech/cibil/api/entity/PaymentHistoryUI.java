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
@Table(name="IB_CBL_PAYMENTHISTORY")
@JsonIgnoreProperties(ignoreUnknown=true)
public class PaymentHistoryUI implements Serializable
{
	private static final long serialVersionUID = 1L;
	private long paymenthistoryid;
	private String ndpdac;
	private String ndpdacMonYear;
	@JsonIgnore
	private AccountDetails accountdetails;
	@JsonIgnore
	private CibilResponsePayload cibilresponsepayload;
    
	@Column(name="NDPDAC")
	public String getNdpdac() {
		return ndpdac;
	}
	public void setNdpdac(String ndpdac) {
		this.ndpdac = ndpdac;
	}
	@Column(name="NDPDACMONYEAR")
	public String getNdpdacMonYear() {
		return ndpdacMonYear;
	}
	public void setNdpdacMonYear(String ndpdacMonYear) {
		this.ndpdacMonYear = ndpdacMonYear;
	}
	@Override
	public String toString() {
		return "PaymentHistoryUI [ndpdac=" + ndpdac + ", ndpdacMonYear=" + ndpdacMonYear + "]";
	}
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_PAYMENTHISTORY_SQC")
	@SequenceGenerator(name = "IB_CBL_PAYMENTHISTORY_SQC", sequenceName = "IB_CBL_PAYMENTHISTORY_SQC")
	public long getPaymenthistoryid() {
		return paymenthistoryid;
	}
	public void setPaymenthistoryid(long paymenthistoryid) {
		this.paymenthistoryid = paymenthistoryid;
	}

	@ManyToOne
	@JoinColumn(name="CIBIL_UNIQUE_ID")
	public CibilResponsePayload getCibilresponsepayload() {
		return cibilresponsepayload;
	}
	public void setCibilresponsepayload(CibilResponsePayload cibilresponsepayload) {
		this.cibilresponsepayload = cibilresponsepayload;
	}
	@ManyToOne
	@JoinColumn(name="ACCOUNTID")
	public AccountDetails getAccountdetails() {
		return accountdetails;
	}
	public void setAccountdetails(AccountDetails accountdetails) {
		this.accountdetails = accountdetails;
	}

}
