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
@Table(name = "IB_BUREAU_ACCOUNT_SUMMARY_JSON")
public class AccountSumary implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ACCOUNT_SUMMARY_SQC", allocationSize = 1)
	@JsonIgnore
	private int accountSumId;

	
	@OneToOne
	@JoinColumn(name = "reportDataId", nullable = false)
	@JsonIgnore
	private ReportDataRes reportDataRes;
	
	public int getAccountSumId() {
		return accountSumId;
	}

	public void setAccountSumId(int accountSumId) {
		this.accountSumId = accountSumId;
	}

	public ReportDataRes getReportDataRes() {
		return reportDataRes;
	}

	public void setReportDataRes(ReportDataRes reportDataRes) {
		this.reportDataRes = reportDataRes;
	}

	@Override
	public String toString() {
		return "AccountSummary [accountSumId=" + accountSumId + "]";
	}


	
}
