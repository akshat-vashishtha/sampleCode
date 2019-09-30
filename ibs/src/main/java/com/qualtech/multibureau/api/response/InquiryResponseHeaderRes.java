package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "IB_BUREAU_INQY_RES_HEADER")
public class InquiryResponseHeaderRes implements Serializable {

	private static final long serialVersionUID = -487772733524459318L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_INQY_RES_HEAD_SQC", allocationSize = 1)
	@JsonIgnore
	private int inquiryHeaderId;

	private String customername;
	private String successcode;
	private String hitcode;
	@Column(name="TIME_")
	private String time;
	private String productcode;
	private String matchtype;
	private String customercode;
	private String clientid;
	private String custreffield;
	@Column(name="DATE_")
	private String date;
	private String productversion;
	private String reportorderno;

	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public int getInquiryHeaderId() {
		return inquiryHeaderId;
	}

	public void setInquiryHeaderId(int inquiryHeaderId) {
		this.inquiryHeaderId = inquiryHeaderId;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getSuccesscode() {
		return successcode;
	}

	public void setSuccesscode(String successcode) {
		this.successcode = successcode;
	}

	public String getHitcode() {
		return hitcode;
	}

	public void setHitcode(String hitcode) {
		this.hitcode = hitcode;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	public String getMatchtype() {
		return matchtype;
	}

	public void setMatchtype(String matchtype) {
		this.matchtype = matchtype;
	}

	public String getCustomercode() {
		return customercode;
	}

	public void setCustomercode(String customercode) {
		this.customercode = customercode;
	}

	public String getClientid() {
		return clientid;
	}

	public void setClientid(String clientid) {
		this.clientid = clientid;
	}

	public String getCustreffield() {
		return custreffield;
	}

	public void setCustreffield(String custreffield) {
		this.custreffield = custreffield;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getProductversion() {
		return productversion;
	}

	public void setProductversion(String productversion) {
		this.productversion = productversion;
	}

	public String getReportorderno() {
		return reportorderno;
	}

	public void setReportorderno(String reportorderno) {
		this.reportorderno = reportorderno;
	}

	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	@Override
	public String toString() {
		return "InquiryResponseHeaderRes [inquiryHeaderId=" + inquiryHeaderId + ", customername=" + customername
				+ ", successcode=" + successcode + ", hitcode=" + hitcode + ", time=" + time + ", productcode="
				+ productcode + ", matchtype=" + matchtype + ", customercode=" + customercode + ", clientid=" + clientid
				+ ", custreffield=" + custreffield + ", date=" + date + ", productversion=" + productversion
				+ ", reportorderno=" + reportorderno + "]";
	}

}
