package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_TAN_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TanResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
	@Column
	private String name;
	@Id
	@JsonIgnore
	private String uniqueId;
	@Transient
	private String byte_Array;
	@Column
	@JsonIgnore
	private String correlation_Id;
	
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getByte_Array() {
		return byte_Array;
	}
	public void setByte_Array(String byte_Array) {
		this.byte_Array = byte_Array;
	}
	public String getCorrelation_Id() {
		return correlation_Id;
	}
	public void setCorrelation_Id(String correlation_Id) {
		this.correlation_Id = correlation_Id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TanResult [name=" + name + "]";
	}
	
}
