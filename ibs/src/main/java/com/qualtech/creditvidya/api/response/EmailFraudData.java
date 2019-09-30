package com.qualtech.creditvidya.api.response;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_CREDIT_VRFY_DATA")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailFraudData implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7887882968252814612L;

	@Id
	@GeneratedValue(generator="IB_CREDIT_VRFY_DATA_SQC")
	@SequenceGenerator(name="IB_CREDIT_VRFY_DATA_SQC",sequenceName="IB_CREDIT_VRFY_DATA_SQC",allocationSize=1)
	@Column
	@JsonIgnore
	private Long eid;
	
	@Transient
	private DataOutput output;
	
	@Column(name="VALIDATION_STATUS")
	private String validationStatus;
	
	@Column(name="REQUEST_TIMESTAMP")
	private String requestTimestamp;
	
	@Column(name="LINK_OPEN_TIMESTAMP")
	private String linkopenTimestamp;
	
	@Column(name="FORM_SUBMIT_TIMESTAMP")
	private String formsubmitTimestamp;
	
	@Column(name="EMAIL_SENT_TIMESTAMP")
	private String emailsentTimestamp;
	
	@Column(name="EMAIL_BOUNCED_TIMESTAMP")
	private String emailbouncedTimestamp;
	
	@Column(name="RISK_FLAG")
	private boolean riskFlag;
	
	@Column(name="RISK_INFO")
	private String[] riskInfo;
	
	@Transient
	private EmailInfo emailInfo;
	
	@JsonIgnore
	@Column
	private String sid;
	

	
	@Transient
	private CompanyInfo companyInfo;
	@Transient
	private DomainInfo domainInfo;
	@Transient
	private VerifyInfo verifyInfo;
	@Transient
	private FormInfo formInfo;

	@Column(name="VERIFICATION_STATUS")
	private String verificationStatus;
	
	@Column(name="DOMAIN_NAME")
	private String domainName;
	
	@Column(name="RESPONSE_TIME")
	private String responseTime;
	
	@Column(name="OVER_ALL_STATUS")
	private String overallStatus;
	
	@Column(name="UNIQUEID")
	private String uniqueid;
	
	
	 
    public DataOutput getOutput() {
    	if(output!=null){
    		output.setEid(this.eid);
    	}
		return output;
	}
    public void setOutput(DataOutput output) {
		this.output = output;
	}
    
	
	public String getValidationStatus() {
		return validationStatus;
	}
	public void setValidationStatus(String validationStatus) {
		this.validationStatus = validationStatus;
	}
	
	public String getRequestTimestamp() {
		return requestTimestamp;
	}
	public void setRequestTimestamp(String requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}

	public String getLinkopenTimestamp() {
		return linkopenTimestamp;
	}
	public void setLinkopenTimestamp(String linkopenTimestamp) {
		this.linkopenTimestamp = linkopenTimestamp;
	}
	
	public String getFormsubmitTimestamp() {
		return formsubmitTimestamp;
	}
	public void setFormsubmitTimestamp(String formsubmitTimestamp) {
		this.formsubmitTimestamp = formsubmitTimestamp;
	}

	public String getEmailsentTimestamp() {
		return emailsentTimestamp;
	}
	public void setEmailsentTimestamp(String emailsentTimestamp) {
		this.emailsentTimestamp = emailsentTimestamp;
	}

	public String getEmailbouncedTimestamp() {
		return emailbouncedTimestamp;
	}
	public void setEmailbouncedTimestamp(String emailbouncedTimestamp) {
		this.emailbouncedTimestamp = emailbouncedTimestamp;
	}

	public boolean isRiskFlag() {
		return riskFlag;
	}
	public void setRiskFlag(boolean riskFlag) {
		this.riskFlag = riskFlag;
	}

	
	public String[] getRiskInfo() {
		return riskInfo;
	}
	public void setRiskInfo(String[] riskInfo) {
		this.riskInfo = riskInfo;
	}

	public EmailInfo getEmailInfo() {
		if(emailInfo!=null){
			emailInfo.setEid(this.eid);
		}
		return emailInfo;
	}
	public void setEmailInfo(EmailInfo emailInfo) {
		this.emailInfo = emailInfo;
	}

	 
	public CompanyInfo getCompanyInfo() {
		if(companyInfo!=null){
			companyInfo.setEid(this.eid);
		}
		return companyInfo;
	}
	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}
	public VerifyInfo getVerifyInfo() {
		if(verifyInfo!=null){
			verifyInfo.setEid(this.eid);
		}
		return verifyInfo;
	}
	public void setVerifyInfo(VerifyInfo verifyInfo) {
		this.verifyInfo = verifyInfo;
	}
	public FormInfo getFormInfo() {
		if(formInfo!=null){
			formInfo.setEid(this.eid);
		}
		return formInfo;
	}
	public void setFormInfo(FormInfo formInfo) {
		this.formInfo = formInfo;
	}
	
	public String getVerificationStatus() {
		return verificationStatus;
	}
	public void setVerificationStatus(String verificationStatus) {
		this.verificationStatus = verificationStatus;
	}
	
	public String getDomainName() {
		
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	public String getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(String responseTime) {
		this.responseTime = responseTime;
	}
	
	public String getOverallStatus() {
		return overallStatus;
	}
	public void setOverallStatus(String overallStatus) {
		this.overallStatus = overallStatus;
	}

	public DomainInfo getDomainInfo() {
		if(domainInfo!=null){
			domainInfo.setEid(this.eid);
		}
		return domainInfo;
	}

	public void setDomainInfo(DomainInfo domainInfo) {
		this.domainInfo = domainInfo;
	}
	
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	
	@Override
	public String toString() {
		return "EmailFraudData [output=" + output + ", validationStatus=" + validationStatus + ", requestTimestamp="
				+ requestTimestamp + ", linkopenTimestamp=" + linkopenTimestamp + ", formsubmitTimestamp="
				+ formsubmitTimestamp + ", emailsentTimestamp=" + emailsentTimestamp + ", emailbouncedTimestamp="
				+ emailbouncedTimestamp + ", riskFlag=" + riskFlag + ", riskInfo=" + Arrays.toString(riskInfo)
				+ ", emailInfo=" + emailInfo + ", companyInfo=" + companyInfo + ", domainInfo=" + domainInfo
				+ ", verifyInfo=" + verifyInfo + ", formInfo=" + formInfo + ", verificationStatus=" + verificationStatus
				+ ", domainName=" + domainName + ", responseTime=" + responseTime + ", overallStatus=" + overallStatus
				+ "]";
	}
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}


	
}
