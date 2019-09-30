package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_K_NAME_SIMILARITY_RES")
public class NameSimilarityResponsePayload implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@JsonIgnore
	@Column(name="EID")
	private Long eid;
	@Column(name="RESULT")
	private String result;
	@Column(name="STATUS_CODE")
	private int status_code;
	@Column(name="REQUEST_ID")
	private String request_id;
	@Column(name="STATUS_MSG")
	private String status_msg;
	@JsonIgnore
	@Column(name="CORELATIONID")
	private String corelationid;

	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getStatus_code() {
		return status_code;
	}

	public void setStatus_code(int status_code) {
		this.status_code = status_code;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getStatus_msg() {
		return status_msg;
	}

	public void setStatus_msg(String status_msg) {
		this.status_msg = status_msg;
	}
	@Override
	public String toString() {
		return "NameSimilarityResponsePayload [eid=" + eid + ", result=" + result + ", status_code=" + status_code
				+ ", request_id=" + request_id + ", status_msg=" + status_msg + ", corelationid=" + corelationid + "]";
	}
	
}
