package com.qualtech.kotak.api.response;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name="IB_KOTAK_FAULT")
public class KotakFault implements Serializable {

       
	private static final long serialVersionUID = -4430173650984316128L;
	
	@ManyToOne
	@JoinColumn(name="UNIQUEID",nullable=false)
	@JsonIgnore
	private KotakResponsePayload kotakResponsePayload;
	
	@Column(name="CODE")
	private String code;
	@Column(name="REASON")
	private String reason;
	@Id
	@GeneratedValue(generator = "IB_KOTAK_FAULT")
	@SequenceGenerator(name = "IB_KOTAK_FAULT", sequenceName = "IB_KOTAK_FAULT_SQC")
	private int fault_id;
	
	
	
	/*@Column(name="UNIQUEID")
	private long request_unique_id;*/
	
	
	
	
	public int getFault_id() {
		return fault_id;
	}
	public KotakResponsePayload getKotakResponsePayload() {
		return kotakResponsePayload;
	}
	public void setKotakResponsePayload(KotakResponsePayload kotakResponsePayload) {
		this.kotakResponsePayload = kotakResponsePayload;
	}
	public void setFault_id(int fault_id) {
		this.fault_id = fault_id;
	}
	/*public long getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(long request_unique_id) {
		this.request_unique_id = request_unique_id;
	}*/
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "KotakFault [code=" + code + ", reason=" + reason
				+ ", fault_id=" + fault_id + "]";
	}
	
	
	
}
