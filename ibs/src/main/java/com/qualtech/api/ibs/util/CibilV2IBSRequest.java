package com.qualtech.api.ibs.util;

public class CibilV2IBSRequest {
	private String enquiryAmount;
	private String loanPurpose;
	private String reportType;
	private String futureUse;
	private String alternateName;
	private String account1;
	private String gstStateCode;
	private String branchReferenceNo;
	private String centreReferenceNo;

	public String getEnquiryAmount() {
		return enquiryAmount;
	}

	public void setEnquiryAmount(String enquiryAmount) {
		this.enquiryAmount = enquiryAmount;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	public String getFutureUse() {
		return futureUse;
	}

	public void setFutureUse(String futureUse) {
		this.futureUse = futureUse;
	}

	public String getAlternateName() {
		return alternateName;
	}

	public void setAlternateName(String alternateName) {
		this.alternateName = alternateName;
	}

	public String getAccount1() {
		return account1;
	}

	public void setAccount1(String account1) {
		this.account1 = account1;
	}

	public String getGstStateCode() {
		return gstStateCode;
	}

	public void setGstStateCode(String gstStateCode) {
		this.gstStateCode = gstStateCode;
	}

	public String getBranchReferenceNo() {
		return branchReferenceNo;
	}

	public void setBranchReferenceNo(String branchReferenceNo) {
		this.branchReferenceNo = branchReferenceNo;
	}

	public String getCentreReferenceNo() {
		return centreReferenceNo;
	}

	public void setCentreReferenceNo(String centreReferenceNo) {
		this.centreReferenceNo = centreReferenceNo;
	}

	@Override
	public String toString() {
		return "CibilV2IBSRequest [enquiryAmount=" + enquiryAmount + ", loanPurpose=" + loanPurpose + ", reportType="
				+ reportType + ", futureUse=" + futureUse + ", alternateName=" + alternateName + ", account1="
				+ account1 + ", gstStateCode=" + gstStateCode + ", branchReferenceNo=" + branchReferenceNo
				+ ", centreReferenceNo=" + centreReferenceNo + "]";
	}
}
