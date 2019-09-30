package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_CREDIT_HEADER_RES")
public class CreditProfileHeaderRes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2687283033456955149L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CR_HEAD_SQC", allocationSize = 1)
	@JsonIgnore
	private int creditHeaderId;


	private String reportnumber;
	private String reporttime;
	private String subscribername;
	private String reportdate;
	private String subscriber;
	private String enquiryusername;
	private String version;
	
	
	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;


	public int getCreditHeaderId() {
		return creditHeaderId;
	}


	public void setCreditHeaderId(int creditHeaderId) {
		this.creditHeaderId = creditHeaderId;
	}


	public String getReportnumber() {
		return reportnumber;
	}


	public void setReportnumber(String reportnumber) {
		this.reportnumber = reportnumber;
	}


	public String getReporttime() {
		return reporttime;
	}


	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}


	public String getSubscribername() {
		return subscribername;
	}


	public void setSubscribername(String subscribername) {
		this.subscribername = subscribername;
	}


	public String getReportdate() {
		return reportdate;
	}


	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}


	public String getSubscriber() {
		return subscriber;
	}


	public void setSubscriber(String subscriber) {
		this.subscriber = subscriber;
	}


	public String getEnquiryusername() {
		return enquiryusername;
	}


	public void setEnquiryusername(String enquiryusername) {
		this.enquiryusername = enquiryusername;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}


	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}


	@Override
	public String toString() {
		return "CreditProfileHeaderRes [creditHeaderId=" + creditHeaderId + ", reportnumber=" + reportnumber
				+ ", reporttime=" + reporttime + ", subscribername=" + subscribername + ", reportdate=" + reportdate
				+ ", subscriber=" + subscriber + ", enquiryusername=" + enquiryusername + ", version=" + version + "]";
	}

}
