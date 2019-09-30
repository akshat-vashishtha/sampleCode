package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_PHN_JSON_RES")
public class PhoneJsonRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_PHN_JSON_RES_SQC", allocationSize = 1)
	@JsonIgnore
	private int phoneId;

	private String telephonenumber;

	private String telephonetype;
	
	private String enrichenquiryforphone;

	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	public int getPhoneId() {
		return phoneId;
	}

	public void setPhoneId(int phoneId) {
		this.phoneId = phoneId;
	}

	public String getTelephonenumber() {
		return telephonenumber;
	}

	public void setTelephonenumber(String telephonenumber) {
		this.telephonenumber = telephonenumber;
	}

	public String getTelephonetype() {
		return telephonetype;
	}

	public void setTelephonetype(String telephonetype) {
		this.telephonetype = telephonetype;
	}

	public String getEnrichenquiryforphone() {
		return enrichenquiryforphone;
	}

	public void setEnrichenquiryforphone(String enrichenquiryforphone) {
		this.enrichenquiryforphone = enrichenquiryforphone;
	}

	@Override
	public String toString() {
		return "PhoneJsonRes [phoneId=" + phoneId + ", telephonenumber=" + telephonenumber + ", telephonetype="
				+ telephonetype + ", enrichenquiryforphone=" + enrichenquiryforphone + "]";
	}
	
}
