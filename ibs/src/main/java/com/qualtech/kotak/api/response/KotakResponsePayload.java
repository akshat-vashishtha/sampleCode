package com.qualtech.kotak.api.response;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_KOTAK_ACK_HEDR")
public class KotakResponsePayload implements Serializable 
{
	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
	private long uniqueid;
	@Embedded
	private KotakResponseAckHeader ackHeader;
	@Embedded
	private KotakFaultList faultList;
	@Transient
	private String byteArray;
	@Transient
	private String pdfPath;
	@Transient
	private int status_code;
	@Transient
	private String request_id;
	@Transient
	private String status_msg;
	
	

	public long getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(long uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getByteArray() {
		return byteArray;
	}
	public void setByteArray(String byteArray) {
		this.byteArray = byteArray;
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public KotakResponseAckHeader getAckHeader() {
		return ackHeader;
	}
	public void setAckHeader(KotakResponseAckHeader ackHeader) {
		this.ackHeader = ackHeader;
	}
	public KotakFaultList getFaultList() {
		if(faultList!=null) {
			faultList.setKotakResponsePayload(this);
		}
		return faultList;
	}
	public void setFaultList(KotakFaultList faultList) {
		this.faultList = faultList;
	}
	@Override
	public String toString() {
		return "KotakResponsePayload [ackHeader=" + ackHeader + ", faultList=" + faultList + ", byteArray=" + byteArray
				+ ", pdfPath=" + pdfPath + ", status_code=" + status_code + ", request_id=" + request_id
				+ ", status_msg=" + status_msg + "]";
	}
	
	
	
	
}
