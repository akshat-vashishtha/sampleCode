package com.qualtech.finbit.api.request;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="IB_FINBIT_REQ_ACC_DTL")
public class AccountDetail {
	
	@Id
	@GeneratedValue(generator = "IB_FINBIT_REQ_ACC_DTL_SQC")
	@SequenceGenerator(name = "IB_FINBIT_REQ_ACC_DTL_SQC", sequenceName = "IB_FINBIT_REQ_ACC_DTL_SQC")
	private int seqId;
	private String correlationId;
	private int eid;
	@Lob
	private String bankstatement;
	private String bankname;
	private String accounttype;
	private String emailaddress;
	private String password;
	
	public int getSeqId() {
		return seqId;
	}
	public void setSeqId(int seqId) {
		this.seqId = seqId;
	}
	public String getBankstatement() {
		return bankstatement;
	}
	public void setBankstatement(String bankstatement) {
		this.bankstatement = bankstatement;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getAccounttype() {
		return accounttype;
	}
	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "AccountDetail [bankstatement=" + bankstatement + ", bankname=" + bankname + ", accounttype="
				+ accounttype + ", emailaddress=" + emailaddress + ", password=" + password + "]";
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}

	
	
	
	
}
