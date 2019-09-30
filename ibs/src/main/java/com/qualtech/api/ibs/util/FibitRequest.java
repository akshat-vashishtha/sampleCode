package com.qualtech.api.ibs.util;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Table
@Entity(name="IBS_FINBIT_ACCOUNTDETAIL")
@JsonIgnoreProperties(ignoreUnknown=true)
public class FibitRequest {
	 
	@Id
	@GeneratedValue(generator = "IBS_FINBIT_ACC_DTL_SQC")
	@SequenceGenerator(name = "IBS_FINBIT_ACC_DTL_SQC", sequenceName = "IBS_FINBIT_ACC_DTL_SQC")
	@JsonIgnore
	 private int id;
	@JsonIgnore
	 private String corelationid;
	
	 @Lob
	 private String bankstatement;
	 
     private String bankname;
     private String accounttype;
     private String emailaddress;
     private String password;
	
	
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
		return "FibitRequest [bankstatement=" + bankstatement + ", bankname=" + bankname + ", accounttype="
				+ accounttype + ", emailaddress=" + emailaddress + ", password=" + password + "]";
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBankstatement() {
		return bankstatement;
	}
	public void setBankstatement(String bankstatement) {
		this.bankstatement = bankstatement;
	}
	
     
}
