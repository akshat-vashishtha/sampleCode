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
@Table(name="IB_K_FORM16_REQ")
public class Form16Result implements Serializable{

	
	 private static final long serialVersionUID = -8251784410816419882L;
	 @Id
	 @JsonIgnore
	 private String	uniqueId;	 	    
	 @Column
	 @JsonIgnore
	 private String	 corelationId ;
	 @Column(name="policyLink")
	 private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getCorelationId() {
		return corelationId;
	}
	public void setCorelationId(String corelationId) {
		this.corelationId = corelationId;
	}
	@Override
	public String toString() {
		return "Form16Result [status=" + status + "]";
	}
	 
	 
}
