package com.qualtech.creditvidya.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_EMAIL_VRFY_EMAIL_INFO")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmailInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	private Long eid;


	private String emailStatus;
	private boolean mailboxExists;
	private boolean genericEmail;
	private boolean disposableEmail;
	private boolean gibberishEmail;
	private boolean contactusTypeEmail;
	@JsonIgnore
	private String sid;
	
	
	@Id
	@GeneratedValue(generator="IB_CREDIT_VRFY_EMAIL_SQC")
	@SequenceGenerator(name="IB_CREDIT_VRFY_EMAIL_SQC",sequenceName="IB_CREDIT_VRFY_EMAIL_SQC",allocationSize=1)
	@Column
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	
	@Column(name="EMAIL_STATUS")
	public String getEmailStatus() {
		return emailStatus;
	}
	
	public void setEmailStatus(String emailStatus) {
		this.emailStatus = emailStatus;
	}
	 
	@Column(name="MAILBOX_EXISTS")
	public boolean isMailboxExists() {
		return mailboxExists;
	}
	public void setMailboxExists(boolean mailboxExists) {
		this.mailboxExists = mailboxExists;
	}
	@Column(name="GENERIC_EMAIL")
	public boolean isGenericEmail() {
		return genericEmail;
	}
	public void setGenericEmail(boolean genericEmail) {
		this.genericEmail = genericEmail;
	}
	@Column(name="DISPOSABLE_EMAIL")
	public boolean isDisposableEmail() {
		return disposableEmail;
	}
	public void setDisposableEmail(boolean disposableEmail) {
		this.disposableEmail = disposableEmail;
	}
	@Column(name="GIBBERISH_EMAIL")
	public boolean isGibberishEmail() {
		return gibberishEmail;
	}
	public void setGibberishEmail(boolean gibberishEmail) {
		this.gibberishEmail = gibberishEmail;
	}
	@Column(name="CONTACTUS_TYPE_EMAIL")
	public boolean isContactusTypeEmail() {
		return contactusTypeEmail;
	}
	public void setContactusTypeEmail(boolean contactusTypeEmail) {
		this.contactusTypeEmail = contactusTypeEmail;
	}

	@Override
	public String toString() {
		return "EmailInfo [emailStatus=" + emailStatus + ", mailboxExists=" + mailboxExists + ", genericEmail="
				+ genericEmail + ", disposableEmail=" + disposableEmail + ", gibberishEmail=" + gibberishEmail
				+ ", contactusTypeEmail=" + contactusTypeEmail + "]";
	}
	
	@Column
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}

}
