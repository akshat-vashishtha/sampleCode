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
@Table(name = "IB_BUREAU_ENQUIRY_LIST_RES")
public class EnquirylistRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ENQUIRY_LIST_SQC", allocationSize = 1)
	@JsonIgnore
	private int enquiryId;

	private String enquirypurpose;
	private String reportingmembershortname;
	private String enquiryamount;
	private String datereported;
	
	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public int getEnquiryId() {
		return enquiryId;
	}

	public void setEnquiryId(int enquiryId) {
		this.enquiryId = enquiryId;
	}

	public String getEnquirypurpose() {
		return enquirypurpose;
	}

	public void setEnquirypurpose(String enquirypurpose) {
		this.enquirypurpose = enquirypurpose;
	}

	public String getReportingmembershortname() {
		return reportingmembershortname;
	}

	public void setReportingmembershortname(String reportingmembershortname) {
		this.reportingmembershortname = reportingmembershortname;
	}

	public String getEnquiryamount() {
		return enquiryamount;
	}

	public void setEnquiryamount(String enquiryamount) {
		this.enquiryamount = enquiryamount;
	}

	public String getDatereported() {
		return datereported;
	}

	public void setDatereported(String datereported) {
		this.datereported = datereported;
	}

	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	@Override
	public String toString() {
		return "EnquirylistRes [enquiryId=" + enquiryId + ", enquirypurpose=" + enquirypurpose
				+ ", reportingmembershortname=" + reportingmembershortname + ", enquiryamount=" + enquiryamount
				+ ", datereported=" + datereported + "]";
	}

	
}
