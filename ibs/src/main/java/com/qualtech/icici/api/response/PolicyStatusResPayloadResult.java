package com.qualtech.icici.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "IB_I_PS_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyStatusResPayloadResult implements Serializable {

	private static final long serialVersionUID = 8160320220556994132L;

	@JsonIgnore
	@Id
	private int eid;
	private String status;
	private String issuanceDate;
	private String riskCommDate;
	private String policyStatus;
	private String policyNo;
	private String premiumAmt;
	private String timestamp;
	private String message;
	private String code;
	private String details;

	public int getEid() {
		return eid;
	}

	public void setEid(int eid) {
		this.eid = eid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIssuanceDate() {
		return issuanceDate;
	}

	public void setIssuanceDate(String issuanceDate) {
		this.issuanceDate = issuanceDate;
	}

	public String getRiskCommDate() {
		return riskCommDate;
	}

	public void setRiskCommDate(String riskCommDate) {
		this.riskCommDate = riskCommDate;
	}

	public String getPolicyStatus() {
		return policyStatus;
	}

	public void setPolicyStatus(String policyStatus) {
		this.policyStatus = policyStatus;
	}

	public String getPolicyNo() {
		return policyNo;
	}

	public void setPolicyNo(String policyNo) {
		this.policyNo = policyNo;
	}

	public String getPremiumAmt() {
		return premiumAmt;
	}

	public void setPremiumAmt(String premiumAmt) {
		this.premiumAmt = premiumAmt;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "PolicyStatusResPayloadResult [eid=" + eid + ", status=" + status + ", issuanceDate=" + issuanceDate
				+ ", riskCommDate=" + riskCommDate + ", policyStatus=" + policyStatus + ", policyNo=" + policyNo
				+ ", premiumAmt=" + premiumAmt + ", timestamp=" + timestamp + ", message=" + message + ", code=" + code
				+ ", details=" + details + "]";
	}

}
