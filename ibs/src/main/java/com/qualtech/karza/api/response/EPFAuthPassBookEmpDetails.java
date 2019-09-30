package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class EPFAuthPassBookEmpDetails implements Serializable {

	private static final long serialVersionUID = -8792345872536656995L;
	
	private String corelationid;
	private String dob;
	private String father_name;
	private String member_name;
	
	/*@ManyToOne
	@JoinColumn(name="UNIQUEID",nullable=false)
	@JsonIgnore
	private EPFAuthPassBookResult epfAuthPassBookResult;
	*/
	
	@Column(name="DOB")
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	@Column(name="FATHER_NAME")
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	@Column(name="MEMBER_NAME")
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "EPFAuthPassBookEmpDetails [dob=" + dob + ", father_name=" + father_name + ", member_name=" + member_name
				+ "]";
	}

    @JsonIgnore
	@Column(name="CORELATIONID")
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	
	
}
