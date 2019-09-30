package com.qualtech.equifax.api.response;

public class PanId {

	String reportedDate;
	String idNumber;
	String seq;
	public String getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	@Override
	public String toString() {
		return "PanId [reportedDate=" + reportedDate + ", idNumber=" + idNumber + ", seq=" + seq + "]";
	}
	
	
	
}
