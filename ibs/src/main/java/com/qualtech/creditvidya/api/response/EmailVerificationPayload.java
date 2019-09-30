package com.qualtech.creditvidya.api.response;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.context.annotation.Primary;



@Entity
@Table(name="IB_EMAIL_VRFY_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailVerificationPayload implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private Long eid;
   
	private String mobileNumber;
	private String city;
	private String uniqueId;
	private String email;
	private String firstName;
	private String middleName;
	private String lastName;
	private EmailVerifiClientReference clientReference;
	private EmailFraudData data;
	private String companyName;
	private String[] errorList;
	private String byteArray;
	private String pdfPath;
	
	@JsonIgnore
	private String corelationid;
	
	@JsonIgnore
	private CompanyInfo companyInfo;
	@JsonIgnore
	private DomainInfo domainInfo;
	@JsonIgnore
	private VerifyInfo verifyInfo;
	@JsonIgnore
	private FormInfo formInfo;
	@JsonIgnore
	private EmailInfo emailInfo;
	
	
	@Override
	public String toString() {
		return "EmailVerificationPayload [eid=" + eid + ", mobileNumber=" + mobileNumber + ", city=" + city
				+ ", uniqueId=" + uniqueId + ", email=" + email + ", firstName=" + firstName + ", middleName="
				+ middleName + ", lastName=" + lastName + ", clientReference=" + clientReference + ", data=" + data
				+ ", companyName=" + companyName + ", errorList=" + Arrays.toString(errorList) + ", byteArray="
				+ byteArray + "]";
	}
	@Id
	@Primary
	@Column(name="SID")
	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}
	@Column(name="COM_NAME")
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Column(name="MOBILE")
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Column(name="CITY")
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="UNIQUEID")
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="F_NAME")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Column(name="M_NAME")
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	@Column(name="L_NAME")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
    
	@Column(name="ERRORLIST")
	public String[] getErrorList() {
		return errorList;
	}
	public void setErrorList(String[] errorList) {
		this.errorList = errorList;
	}
	@Lob
	@Column(name="BYTE_ARRAY")
	public String getByteArray() {
		return byteArray;
	}
	public void setByteArray(String byteArray) {
		this.byteArray = byteArray;
	}
	
	@OneToOne
	@JoinColumn(name = "CLIENT_REF_ID")
	public EmailVerifiClientReference getClientReference() {
		if(clientReference!=null){
			clientReference.setEid(this.eid);
		}
		return clientReference;
	}
	public void setClientReference(EmailVerifiClientReference clientReference) {
		this.clientReference = clientReference;
	}
	
	@OneToOne
	@JoinColumn(name = "DATA_ID")
	 public EmailFraudData getData() {
		if(data!=null){
			data.setEid(this.eid);
		}
		return data;
	 }
	public void setData(EmailFraudData data) {
		this.data = data;
	}
	
	@OneToOne
	@JoinColumn(name = "EMAIL_INFO_ID")
	public EmailInfo getEmailInfo() {
		return emailInfo;
	}
	public void setEmailInfo(EmailInfo emailInfo) {
		this.emailInfo = emailInfo;
	}

	@OneToOne
	@JoinColumn(name = "COM_INFO_ID")
	public CompanyInfo getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(CompanyInfo companyInfo) {
		this.companyInfo = companyInfo;
	}
	
	@OneToOne
	@JoinColumn(name = "DOMAIN_INFO_ID")
	public DomainInfo getDomainInfo() {
		return domainInfo;
	}
	public void setDomainInfo(DomainInfo domainInfo) {
		this.domainInfo = domainInfo;
	}
	
	@OneToOne
	@JoinColumn(name = "VERIFY_INFO_ID")
	public VerifyInfo getVerifyInfo() {
		return verifyInfo;
	}
	public void setVerifyInfo(VerifyInfo verifyInfo) {
		this.verifyInfo = verifyInfo;
	}
	
	@OneToOne
	@JoinColumn(name = "FORM_INFO_ID")
	public FormInfo getFormInfo() {
		return formInfo;
	}
	public void setFormInfo(FormInfo formInfo) {
		this.formInfo = formInfo;
	}
	
	@Column
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

