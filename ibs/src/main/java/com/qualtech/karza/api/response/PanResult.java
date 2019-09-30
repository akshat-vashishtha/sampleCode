package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_PAN_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PanResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
	@Column
	private String name;
	@Id
	@JsonIgnore

	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_PAN_RES_SQC",allocationSize=1)
	private Long uniqueid;
	@Column
	@JsonIgnore
	private String correlation_id;
	@Column
	@JsonIgnore
	private String byte_array;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "PanResult [name=" + name + "]";
	}
	
	public String getByte_array() {
		return byte_array;
	}

	public void setByte_array(String byte_array) {
		this.byte_array = byte_array;
	}
   
	

	public Long getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(Long uniqueid) {
		this.uniqueid = uniqueid;
	}

	public String getCorrelation_id() {
		return correlation_id;
	}

	public void setCorrelation_id(String correlation_id) {
		this.correlation_id = correlation_id;
	}

	
}
