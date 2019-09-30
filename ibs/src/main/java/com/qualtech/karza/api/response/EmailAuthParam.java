package com.qualtech.karza.api.response;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmailAuthParam  {

	
	private String email;
	private String corelationid;
	/**
	 * @return the email
	 */
	@Column
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="CORELATIONID")
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	

	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EmailAuthParam [email=" + email + "]";
	}
	}
