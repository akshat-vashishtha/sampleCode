package com.qualtech.equifax.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="IB_EQ_V_DTLS_LOGS")
public class EquifaxVidAllDetails 
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_V_SQC")
	@SequenceGenerator(name="IB_EQ_V_SQC", sequenceName = "IB_EQ_V_SQC", allocationSize = 1)
	@Column(name="REQUEST_UNIQUE_ID")                                 
	private Long request_unique_id;
	@Column(name="TRACKER_ID")
	private String tracker_id;
	@Column(name="UPDATED_TIME")
	private Date updated_time;
	@Column(name="CREATED_TIME")
	private Date created_time;

	@Column(name="FIRST_NAME")
	private String first_name;
	@Column(name="PERCENTAGE_MATCH")
	private String percentage_match;
	@Column(name="LAST_UPDATED_DATE")
	private String last_updated_date;
	@Column(name="RETURN_CODE")
	private String return_code;
	@Column(name="NSDL_RESPONSE_ID")
	private String nsdl_response_id;
	@Column(name="TITLE")
	private String title;
	@Column(name="IDENTITIFICATION_ID")
	private String identitification_id;
	@Column(name="RETURN_CODE_DESCRIPTION")
	private String return_code_description;
	@Column(name="LAST_NAME")
	private String last_name;
	
	@Column(name="REMARKS")
	private String remarks;

	@Column(name="RESPONSE_DATE")
	private String response_date;
	@Column(name="RESPONSE_ORDER_NO")
	private String response_order_no;
	@Column(name="RESPONSE_TIME")
	private String response_time;
	
	@Column(name="RESPONSE_XML")
	@Lob
    private String response_xml;
	@Column(name="REQUEST_XML")
	@Lob
	private String request_xml;
	@Column(name="REQUEST_INFO_JSON")
	@Lob
	private String request_info_json;
	@Column(name="RESPONSE_HEADER_INFO_JSON")
	@Lob
	private String response_header_info_json;
	@Column(name="RESPONSE_ERROR_INFO_JSON")
	@Lob
	private String response_error_info_json;
	@Column(name="DISCLAIMER")
	@Lob
	private String disclaimer;
	@Column(name="REQUEST_JSON")
	@Lob
	private String request_json;
	@Column(name="RESPONSE_JSON")
	@Lob
	private String response_json;
	@Column(name="HTMLDATA")
	@Lob
	private String htmlData;
	@Column(name="PDF_BYTE_ARRAY")
	@Lob
	private String pdf_byte_Array;



	public String getRequest_json() {
		return request_json;
	}
	public void setRequest_json(String request_json) {
		this.request_json = request_json;
	}
	public String getResponse_json() {
		return response_json;
	}
	public void setResponse_json(String response_json) {
		this.response_json = response_json;
	}
	public String getHtmlData() {
		return htmlData;
	}
	public void setHtmlData(String htmlData) {
		this.htmlData = htmlData;
	}
	public String getPdf_byte_Array() {
		return pdf_byte_Array;
	}
	public void setPdf_byte_Array(String pdf_byte_Array) {
		this.pdf_byte_Array = pdf_byte_Array;
	}
	

	public Long getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(Long request_unique_id) {
		this.request_unique_id = request_unique_id;
	}
	

	public String getResponse_date() {
		return response_date;
	}
	public void setResponse_date(String response_date) {
		this.response_date = response_date;
	}
	public String getResponse_order_no() {
		return response_order_no;
	}
	public void setResponse_order_no(String response_order_no) {
		this.response_order_no = response_order_no;
	}
	public String getResponse_time() {
		return response_time;
	}
	public void setResponse_time(String response_time) {
		this.response_time = response_time;
	}
	
	
	public String getTracker_id() {
		return tracker_id;
	}
	public void setTracker_id(String tracker_id) {
		this.tracker_id = tracker_id;
	}
	public Date getUpdated_time() {
		return updated_time;
	}
	public void setUpdated_time(Date updated_time) {
		this.updated_time = updated_time;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	public String getResponse_xml() {
		return response_xml;
	}
	public void setResponse_xml(String response_xml) {
		this.response_xml = response_xml;
	}
	public String getRequest_xml() {
		return request_xml;
	}
	public void setRequest_xml(String request_xml) {
		this.request_xml = request_xml;
	}
	public String getRequest_info_json() {
		return request_info_json;
	}
	public void setRequest_info_json(String request_info_json) {
		this.request_info_json = request_info_json;
	}
	public String getResponse_header_info_json() {
		return response_header_info_json;
	}
	public void setResponse_header_info_json(String response_header_info_json) {
		this.response_header_info_json = response_header_info_json;
	}
	public String getResponse_error_info_json() {
		return response_error_info_json;
	}
	public void setResponse_error_info_json(String response_error_info_json) {
		this.response_error_info_json = response_error_info_json;
	}
	public String getFirst_name() {
		return first_name;
	}
	
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getPercentage_match() {
		return percentage_match;
	}
	public void setPercentage_match(String percentage_match) {
		this.percentage_match = percentage_match;
	}
	public String getLast_updated_date() {
		return last_updated_date;
	}
	public void setLast_updated_date(String last_updated_date) {
		this.last_updated_date = last_updated_date;
	}
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getNsdl_response_id() {
		return nsdl_response_id;
	}
	public void setNsdl_response_id(String nsdl_response_id) {
		this.nsdl_response_id = nsdl_response_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIdentitification_id() {
		return identitification_id;
	}
	public void setIdentitification_id(String identitification_id) {
		this.identitification_id = identitification_id;
	}
	public String getReturn_code_description() {
		return return_code_description;
	}
	public void setReturn_code_description(String return_code_description) {
		this.return_code_description = return_code_description;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getDisclaimer() {
		return disclaimer;
	}
	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	@Override
	public String toString() {
		return "EquifaxVidAllDetails [request_unique_id=" + request_unique_id + ", tracker_id=" + tracker_id
				+ ", updated_time=" + updated_time + ", created_time=" + created_time + ", response_xml=" + response_xml
				+ ", request_xml=" + request_xml + ", request_info_json=" + request_info_json
				+ ", response_header_info_json=" + response_header_info_json + ", response_error_info_json="
				+ response_error_info_json + ", first_name=" + first_name + ", percentage_match=" + percentage_match
				+ ", last_updated_date=" + last_updated_date + ", return_code=" + return_code + ", nsdl_response_id="
				+ nsdl_response_id + ", title=" + title + ", identitification_id=" + identitification_id
				+ ", return_code_description=" + return_code_description + ", last_name=" + last_name + ", disclaimer="
				+ disclaimer + ", remarks=" + remarks + ", response_date=" + response_date + ", response_order_no="
				+ response_order_no + ", response_time=" + response_time + ", request_json=" + request_json
				+ ", response_json=" + response_json + ", htmlData=" + htmlData + ", pdf_byte_Array=" + pdf_byte_Array
				+ "]";
	}
	
}
