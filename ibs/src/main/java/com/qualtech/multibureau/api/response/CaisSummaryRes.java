package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "IB_BUREAU_CAIS_SUMM_RES")
public class CaisSummaryRes implements Serializable {

	private static final long serialVersionUID = -253021057991052198L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAIS_SUMM_SQC", allocationSize = 1)
	@JsonIgnore
	private int caisSummaryId;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "caisSummaryRes", cascade = CascadeType.ALL)
	private CreditAccountRes creditaccount;
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "caisSummaryRes", cascade = CascadeType.ALL)
	private TotalOutStandingBalanceRes totaloutstandingbalance;
	
	@OneToOne
	@JoinColumn(name = "caisAccountId", nullable = false)
	@JsonIgnore
	private CaisAccountRes caisAccountRes;

	public int getCaisSummaryId() {
		return caisSummaryId;
	}

	public void setCaisSummaryId(int caisSummaryId) {
		this.caisSummaryId = caisSummaryId;
	}

	public CreditAccountRes getCreditaccount() {
		if (this.creditaccount != null) {
			this.creditaccount.setCaisSummaryRes(this);
		}
		return creditaccount;
	}

	public void setCreditaccount(CreditAccountRes creditaccount) {
		this.creditaccount = creditaccount;
	}

	public TotalOutStandingBalanceRes getTotaloutstandingbalance() {
		if (this.totaloutstandingbalance != null) {
			this.totaloutstandingbalance.setCaisSummaryRes(this);
		}
		return totaloutstandingbalance;
	}

	public void setTotaloutstandingbalance(TotalOutStandingBalanceRes totaloutstandingbalance) {
		this.totaloutstandingbalance = totaloutstandingbalance;
	}

	public CaisAccountRes getCaisAccountRes() {
		return caisAccountRes;
	}

	public void setCaisAccountRes(CaisAccountRes caisAccountRes) {
		this.caisAccountRes = caisAccountRes;
	}

	@Override
	public String toString() {
		return "CaisSummaryRes [caisSummaryId=" + caisSummaryId + ", creditaccount=" + creditaccount
				+ ", totaloutstandingbalance=" + totaloutstandingbalance + "]";
	}

}