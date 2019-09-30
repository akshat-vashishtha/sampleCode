package com.qualtech.karza.api.request;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="QCIB_K_KYC_OCR_REQ")
@JsonIgnoreProperties(ignoreUnknown=true)
public class KycOcrReqPayload {

	@Id
	@JsonIgnore
	@Column(name="UNIQUEID")
	private long uniqueid;
	@Lob
	@Column(name="FILE_BYTES")
	private String file;
	@JsonIgnore
	@Column(name="CORELATIONID")
	private String corelationid;
	
	
	

	public long getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(long uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getCorelationid() {
		return corelationid;
	}

	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "KycOcrReqPayload [file=" + file + "]";
	}

	
	
}
