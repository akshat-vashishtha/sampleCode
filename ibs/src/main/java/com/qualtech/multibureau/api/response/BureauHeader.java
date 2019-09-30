package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Embeddable;
@Embeddable
public class BureauHeader implements Serializable {

	private static final long serialVersionUID = 2675175362722104834L;

	private String application_id;
	private String response_type;
	private String cust_id;
	private String request_received_time;
	public String getApplication_id() {
		return application_id;
	}
	public void setApplication_id(String application_id) {
		this.application_id = application_id;
	}
	public String getResponse_type() {
		return response_type;
	}
	public void setResponse_type(String response_type) {
		this.response_type = response_type;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getRequest_received_time() {
		return request_received_time;
	}
	public void setRequest_received_time(String request_received_time) {
		this.request_received_time = request_received_time;
	}
	@Override
	public String toString() {
		return "BureauHeader [application_id=" + application_id + ", response_type=" + response_type + ", cust_id="
				+ cust_id + ", request_received_time=" + request_received_time + "]";
	}
	
	
}

