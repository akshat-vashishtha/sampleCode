package com.qualtech.api.db;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="IBS_SERVICE_MASTER_TABLE")
//@Subselect("select * from IB_SERVICE_MASTER")
public class ServiceMaster{

	@Id
	private Long id;
	private String service_provider;
	private String service_name;
	private String product_name;
	private String query_status;
	private String description;
	@Lob
	private String query;
	private String pdf_name;
	private String service_request_uri;
	private String service_tag;
	private String main_table;
	private String sub_table;
	private String success_price;
	private String failure_price;
	private String sample_query;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getService_provider() {
		return service_provider;
	}
	public void setService_provider(String service_provider) {
		this.service_provider = service_provider;
	}
	public String getService_name() {
		return service_name;
	}
	public void setService_name(String service_name) {
		this.service_name = service_name;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getQuery_status() {
		return query_status;
	}
	public void setQuery_status(String query_status) {
		this.query_status = query_status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public String getPdf_name() {
		return pdf_name;
	}
	public void setPdf_name(String pdf_name) {
		this.pdf_name = pdf_name;
	}
	public String getService_request_uri() {
		return service_request_uri;
	}
	public void setService_request_uri(String service_request_uri) {
		this.service_request_uri = service_request_uri;
	}
	public String getService_tag() {
		return service_tag;
	}
	public void setService_tag(String service_tag) {
		this.service_tag = service_tag;
	}
	public String getMain_table() {
		return main_table;
	}
	public void setMain_table(String main_table) {
		this.main_table = main_table;
	}
	public String getSub_table() {
		return sub_table;
	}
	public void setSub_table(String sub_table) {
		this.sub_table = sub_table;
	}
	public String getSuccess_price() {
		return success_price;
	}
	public void setSuccess_price(String success_price) {
		this.success_price = success_price;
	}
	public String getFailure_price() {
		return failure_price;
	}
	public void setFailure_price(String failure_price) {
		this.failure_price = failure_price;
	}
	public String getSample_query() {
		return sample_query;
	}
	public void setSample_query(String sample_query) {
		this.sample_query = sample_query;
	}
}