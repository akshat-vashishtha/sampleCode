package com.qualtech.creditvidya.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_CREDIT_VRFY_OUTPUT")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataOutput implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonIgnore
	private Long eid;
	private String contentType;
	private String content;

	@JsonIgnore
	private String sid;
	
	@Column(name="CONTENT_TYPE")
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@Id
	@GeneratedValue(generator="IB_CREDIT_VRFY_OUTPUT_SQC")
	@SequenceGenerator(name="IB_CREDIT_VRFY_OUTPUT_SQC",sequenceName="IB_CREDIT_VRFY_OUTPUT_SQC",allocationSize=1)
	@Column(name="EID")
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	@Lob
	@Column(name="CONTENT")
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
   
	@Override
	public String toString() {
		return "DataOutput [contentType=" + contentType + ", content=" + content + "]";
	}
	
	@Column
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	
	
}
