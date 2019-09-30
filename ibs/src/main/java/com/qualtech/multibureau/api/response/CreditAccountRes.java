package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_CR_ACC_RES")
public class CreditAccountRes implements Serializable {

	private static final long serialVersionUID = -253021057991052198L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CR_ACC_SQC", allocationSize = 1)
	@JsonIgnore
	private int creditAccountId;
	
	private String cadsuitfiledcurrentbalance;
	private String creditaccountdefault;
	private String creditaccounttotal;
	private String creditaccountactive;
	private String creditaccountclosed;

	
	@OneToOne
	@JoinColumn(name = "caisSummaryId", nullable = false)
	@JsonIgnore
	private CaisSummaryRes caisSummaryRes;


	public int getCreditAccountId() {
		return creditAccountId;
	}


	public void setCreditAccountId(int creditAccountId) {
		this.creditAccountId = creditAccountId;
	}


	public String getCadsuitfiledcurrentbalance() {
		return cadsuitfiledcurrentbalance;
	}


	public void setCadsuitfiledcurrentbalance(String cadsuitfiledcurrentbalance) {
		this.cadsuitfiledcurrentbalance = cadsuitfiledcurrentbalance;
	}


	public String getCreditaccountdefault() {
		return creditaccountdefault;
	}


	public void setCreditaccountdefault(String creditaccountdefault) {
		this.creditaccountdefault = creditaccountdefault;
	}


	public String getCreditaccounttotal() {
		return creditaccounttotal;
	}


	public void setCreditaccounttotal(String creditaccounttotal) {
		this.creditaccounttotal = creditaccounttotal;
	}


	public String getCreditaccountactive() {
		return creditaccountactive;
	}


	public void setCreditaccountactive(String creditaccountactive) {
		this.creditaccountactive = creditaccountactive;
	}


	public String getCreditaccountclosed() {
		return creditaccountclosed;
	}


	public void setCreditaccountclosed(String creditaccountclosed) {
		this.creditaccountclosed = creditaccountclosed;
	}


	public CaisSummaryRes getCaisSummaryRes() {
		return caisSummaryRes;
	}


	public void setCaisSummaryRes(CaisSummaryRes caisSummaryRes) {
		this.caisSummaryRes = caisSummaryRes;
	}


	@Override
	public String toString() {
		return "CreditAccountRes [creditAccountId=" + creditAccountId + ", cadsuitfiledcurrentbalance="
				+ cadsuitfiledcurrentbalance + ", creditaccountdefault=" + creditaccountdefault
				+ ", creditaccounttotal=" + creditaccounttotal + ", creditaccountactive=" + creditaccountactive
				+ ", creditaccountclosed=" + creditaccountclosed + "]";
	}

}