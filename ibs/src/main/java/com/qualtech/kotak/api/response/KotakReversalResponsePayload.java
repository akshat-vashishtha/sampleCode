package com.qualtech.kotak.api.response;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.kotak.api.request.KotakReversalHeader;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_KOTAK_REV_HDR")
public class KotakReversalResponsePayload implements Serializable 
{
	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
    private long request_unique_id;
	@Embedded
	private KotakReversalHeader reversalHeader;
	@Embedded
	private KotakReversalDetails reversalDetails;
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
	
	public long getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(long request_unique_id) {
		this.request_unique_id = request_unique_id;
	}

	public KotakReversalHeader getReversalHeader() {
		return reversalHeader;
	}
	public void setReversalHeader(KotakReversalHeader reversalHeader) {
		this.reversalHeader = reversalHeader;
	}
	public KotakReversalDetails getReversalDetails() {
		if(reversalDetails!=null)
		reversalDetails.setKotakReversalResponsePayload(this);
		return reversalDetails;
	}
	public void setReversalDetails(KotakReversalDetails reversalDetails) {
		this.reversalDetails = reversalDetails;
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
	@Override
	public String toString() {
		return "KotakReversalResponsePayload [reversalHeader=" + reversalHeader + ", reversalDetails=" + reversalDetails
				+ ", pdfPath=" + pdfPath + ", status_code=" + status_code + ", request_id="
				+ request_id + ", status_msg=" + status_msg + "]";
	}


	
	
	
}
