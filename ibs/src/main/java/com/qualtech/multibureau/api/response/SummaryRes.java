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
@Table(name = "IB_BUREAU_SUMMARY_RES")
public class SummaryRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_SUMMARY_SQC", allocationSize = 1)
	@JsonIgnore
	private int summaryId;
	private String noOfOtherMfis;
	private String noOfDefaultAccounts;
	private String noOfActiveAccounts;
	private String noOfOwnMfi;
	private String noOfClosedAccounts;
	
	private String status;
	private String totalResponses;

	@OneToOne
	@JoinColumn(name = "indResId", nullable = false)
	@JsonIgnore
	private INDVRes indvRes;

	public int getSummaryId() {
		return summaryId;
	}

	public void setSummaryId(int summaryId) {
		this.summaryId = summaryId;
	}

	
	public String getNoOfOtherMfis() {
		return noOfOtherMfis;
	}

	public void setNoOfOtherMfis(String noOfOtherMfis) {
		this.noOfOtherMfis = noOfOtherMfis;
	}

	public String getNoOfDefaultAccounts() {
		return noOfDefaultAccounts;
	}

	public void setNoOfDefaultAccounts(String noOfDefaultAccounts) {
		this.noOfDefaultAccounts = noOfDefaultAccounts;
	}

	public String getNoOfActiveAccounts() {
		return noOfActiveAccounts;
	}

	public void setNoOfActiveAccounts(String noOfActiveAccounts) {
		this.noOfActiveAccounts = noOfActiveAccounts;
	}

	public String getNoOfOwnMfi() {
		return noOfOwnMfi;
	}

	public void setNoOfOwnMfi(String noOfOwnMfi) {
		this.noOfOwnMfi = noOfOwnMfi;
	}

	public String getNoOfClosedAccounts() {
		return noOfClosedAccounts;
	}

	public void setNoOfClosedAccounts(String noOfClosedAccounts) {
		this.noOfClosedAccounts = noOfClosedAccounts;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTotalResponses() {
		return totalResponses;
	}

	public void setTotalResponses(String totalResponses) {
		this.totalResponses = totalResponses;
	}

	public INDVRes getIndvRes() {
		return indvRes;
	}

	public void setIndvRes(INDVRes indvRes) {
		this.indvRes = indvRes;
	}

	

	
	
}
