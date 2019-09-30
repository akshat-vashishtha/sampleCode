package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_AADHAR_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AadharResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
	@Id
	@JsonIgnore
	private String uniqueId;
	@Column
	@JsonIgnore
	private String correlation_id;
	@Column
	private String success;
	@Column
	private String aadhaar_reference_code;
	@Column
	private String aadhaar_status_code;
	@Column
	@Lob
	private String info;
	@Column
	private String pid_timestamp;
	
	
	public String getAadhaar_status_code() {
		return aadhaar_status_code;
	}
	public void setAadhaar_status_code(String aadhaar_status_code) {
		this.aadhaar_status_code = aadhaar_status_code;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getAadhaar_reference_code() {
		return aadhaar_reference_code;
	}
	public void setAadhaar_reference_code(String aadhaar_reference_code) {
		this.aadhaar_reference_code = aadhaar_reference_code;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getPid_timestamp() {
		return pid_timestamp;
	}
	public void setPid_timestamp(String pid_timestamp) {
		this.pid_timestamp = pid_timestamp;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getCorrelation_id() {
		return correlation_id;
	}
	public void setCorrelation_id(String correlation_id) {
		this.correlation_id = correlation_id;
	}
	@Override
	public String toString() {
		return "AadharResult [success=" + success + ", aadhaar_reference_code=" + aadhaar_reference_code
				+ ", aadhaar_status_code=" + aadhaar_status_code + ", info=" + info + ", pid_timestamp=" + pid_timestamp
				+ "]";
	}


	
}
