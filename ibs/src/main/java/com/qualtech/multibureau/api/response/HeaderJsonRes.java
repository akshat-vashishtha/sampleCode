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
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_HEADER_JSON_RES")
public class HeaderJsonRes implements Serializable {

	
	private static final long serialVersionUID = -3551880037013898777L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_HEADER_JSON_RES_SQC", allocationSize = 1)
	@JsonIgnore
	private int headerId;

	private String dateproceed;
	private String subjectreturncode;
	private String memberreferencenumber;
	private String enquirycontrolnumber;
	private String enquirymemberuserid;
	private String serialversionuid;
	private String timeproceed;
	private String version;
	
	//experian bureau header
	private String reporttime;
	private String messagetext;
	private String systemcode;
	private String reportdate;

	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public int getHeaderId() {
		return headerId;
	}

	public void setHeaderId(int headerId) {
		this.headerId = headerId;
	}

	public String getDateproceed() {
		return dateproceed;
	}

	public void setDateproceed(String dateproceed) {
		this.dateproceed = dateproceed;
	}

	public String getSubjectreturncode() {
		return subjectreturncode;
	}

	public void setSubjectreturncode(String subjectreturncode) {
		this.subjectreturncode = subjectreturncode;
	}

	public String getMemberreferencenumber() {
		return memberreferencenumber;
	}

	public void setMemberreferencenumber(String memberreferencenumber) {
		this.memberreferencenumber = memberreferencenumber;
	}

	public String getEnquirycontrolnumber() {
		return enquirycontrolnumber;
	}

	public void setEnquirycontrolnumber(String enquirycontrolnumber) {
		this.enquirycontrolnumber = enquirycontrolnumber;
	}

	public String getEnquirymemberuserid() {
		return enquirymemberuserid;
	}

	public void setEnquirymemberuserid(String enquirymemberuserid) {
		this.enquirymemberuserid = enquirymemberuserid;
	}

	public String getSerialversionuid() {
		return serialversionuid;
	}

	public void setSerialversionuid(String serialversionuid) {
		this.serialversionuid = serialversionuid;
	}

	public String getTimeproceed() {
		return timeproceed;
	}

	public void setTimeproceed(String timeproceed) {
		this.timeproceed = timeproceed;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getReporttime() {
		return reporttime;
	}

	public void setReporttime(String reporttime) {
		this.reporttime = reporttime;
	}

	public String getMessagetext() {
		return messagetext;
	}

	public void setMessagetext(String messagetext) {
		this.messagetext = messagetext;
	}

	public String getSystemcode() {
		return systemcode;
	}

	public void setSystemcode(String systemcode) {
		this.systemcode = systemcode;
	}

	public String getReportdate() {
		return reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	@Override
	public String toString() {
		return "HeaderJsonRes [headerId=" + headerId + ", dateproceed=" + dateproceed + ", subjectreturncode="
				+ subjectreturncode + ", memberreferencenumber=" + memberreferencenumber + ", enquirycontrolnumber="
				+ enquirycontrolnumber + ", enquirymemberuserid=" + enquirymemberuserid + ", serialversionuid="
				+ serialversionuid + ", timeproceed=" + timeproceed + ", version=" + version + ", reporttime="
				+ reporttime + ", messagetext=" + messagetext + ", systemcode=" + systemcode + ", reportdate="
				+ reportdate + "]";
	}

}
