package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmailAuthData implements Serializable{


	private static final long serialVersionUID = -5680423386658469933L;
	
	private String disposable;
	private String webmail;
	private String result;
	private String accept_all;
	private String smtp_check;
	private String regexp;
	private String mx_records;
	private String email;

	
	@Column(name="DISPOSABLE")
	public String getDisposable() {
		return disposable;
	}
	
	public void setDisposable(String disposable) {
		this.disposable = disposable;
	}
	@Column(name="WEBMAIL")
	public String getWebmail() {
		return webmail;
	}
	public void setWebmail(String webmail) {
		this.webmail = webmail;
	}
	@Column(name="RESULT")
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Column(name="ACCEPT_ALL")
	public String getAccept_all() {
		return accept_all;
	}
	public void setAccept_all(String accept_all) {
		this.accept_all = accept_all;
	}
	@Column(name="SMTP_CHECK")
	public String getSmtp_check() {
		return smtp_check;
	}
	public void setSmtp_check(String smtp_check) {
		this.smtp_check = smtp_check;
	}
	@Column(name="REGEXP")
	public String getRegexp() {
		return regexp;
	}
	public void setRegexp(String regexp) {
		this.regexp = regexp;
	}
	@Column(name="MX_RECORDS")
	public String getMx_records() {
		return mx_records;
	}
	public void setMx_records(String mx_records) {
		this.mx_records = mx_records;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
    

	@Override
	public String toString() {
		return "EmailAuthData [disposable=" + disposable + ", webmail=" + webmail + ", result=" + result
				+ ", accept_all=" + accept_all + ", smtp_check=" + smtp_check + ", regexp=" + regexp + ", mx_records="
				+ mx_records + ", email=" + email + "]";
	}

   
}
