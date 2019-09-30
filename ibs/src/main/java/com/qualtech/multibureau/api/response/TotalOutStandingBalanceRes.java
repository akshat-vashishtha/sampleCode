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
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_TTOUT_BAL_RES")
public class TotalOutStandingBalanceRes implements Serializable {

	private static final long serialVersionUID = -253021057991052198L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_TTOUT_BAL_SQC", allocationSize = 1)
	@JsonIgnore
	private int totalOutId;
	
	@JsonProperty("outstandingbalancesecuredpercentage")
	private String outstandingBalSecuredPer;
	private String outstandingbalanceall;
	
	@JsonProperty("outstandingbalanceunsecuredpercentage")
	private String outstandingBalUnsecuredPer;
	private String outstandingbalancesecured;
	private String outstandingbalanceunsecured;

	
	@OneToOne
	@JoinColumn(name = "caisSummaryId", nullable = false)
	@JsonIgnore
	private CaisSummaryRes caisSummaryRes;


	public int getTotalOutId() {
		return totalOutId;
	}


	public void setTotalOutId(int totalOutId) {
		this.totalOutId = totalOutId;
	}


	public String getOutstandingBalSecuredPer() {
		return outstandingBalSecuredPer;
	}


	public void setOutstandingBalSecuredPer(String outstandingBalSecuredPer) {
		this.outstandingBalSecuredPer = outstandingBalSecuredPer;
	}


	public String getOutstandingbalanceall() {
		return outstandingbalanceall;
	}


	public void setOutstandingbalanceall(String outstandingbalanceall) {
		this.outstandingbalanceall = outstandingbalanceall;
	}


	public String getOutstandingBalUnsecuredPer() {
		return outstandingBalUnsecuredPer;
	}


	public void setOutstandingBalUnsecuredPer(String outstandingBalUnsecuredPer) {
		this.outstandingBalUnsecuredPer = outstandingBalUnsecuredPer;
	}


	public String getOutstandingbalancesecured() {
		return outstandingbalancesecured;
	}


	public void setOutstandingbalancesecured(String outstandingbalancesecured) {
		this.outstandingbalancesecured = outstandingbalancesecured;
	}


	public String getOutstandingbalanceunsecured() {
		return outstandingbalanceunsecured;
	}


	public void setOutstandingbalanceunsecured(String outstandingbalanceunsecured) {
		this.outstandingbalanceunsecured = outstandingbalanceunsecured;
	}


	public CaisSummaryRes getCaisSummaryRes() {
		return caisSummaryRes;
	}


	public void setCaisSummaryRes(CaisSummaryRes caisSummaryRes) {
		this.caisSummaryRes = caisSummaryRes;
	}


	@Override
	public String toString() {
		return "TotalOutStandingBalanceRes [totalOutId=" + totalOutId + ", outstandingBalSecuredPer="
				+ outstandingBalSecuredPer + ", outstandingbalanceall=" + outstandingbalanceall
				+ ", outstandingBalUnsecuredPer=" + outstandingBalUnsecuredPer + ", outstandingbalancesecured="
				+ outstandingbalancesecured + ", outstandingbalanceunsecured=" + outstandingbalanceunsecured + "]";
	}

}