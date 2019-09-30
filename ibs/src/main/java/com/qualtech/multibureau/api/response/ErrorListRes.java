package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_ERROR_LIST_RES")
public class ErrorListRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ERROR_LIST_SQC", allocationSize = 1)
	@JsonIgnore
	private int errorListId;

	private String details;
	private String errorcode;
	private String errormsg;

	@ManyToOne
	@JoinColumn(name = "reportDataId", nullable = false)
	@JsonIgnore
	private ReportDataRes reportDataRes;

	public int getErrorListId() {
		return errorListId;
	}

	public void setErrorListId(int errorListId) {
		this.errorListId = errorListId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	public ReportDataRes getReportDataRes() {
		return reportDataRes;
	}

	public void setReportDataRes(ReportDataRes reportDataRes) {
		this.reportDataRes = reportDataRes;
	}

	@Override
	public String toString() {
		return "ErrorListRes [errorListId=" + errorListId + ", details=" + details + ", errorcode=" + errorcode
				+ ", errormsg=" + errormsg + "]";
	}
}
