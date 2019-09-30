package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name="IB_K_NAMESIMILARITY_REQ")
public class NameSimilarityPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
	@Id
	@JsonIgnore
	@Column(name="UNIQUEID")
	private Long uniqueid;
	@Column(name="NAME1")
	private String name1;
	@Column(name="NAME2")
	 private String name2;
	@Column(name="TYPE")
	 private String type;
	@JsonIgnore
	@Column(name="CORELATIONID")
	 private String corelationid;
	 
	 
	 
	 
	public Long getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(Long uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "NameSimilarityPayload [name1=" + name1 + ", name2=" + name2 + ", type=" + type + "]";
	}
	
}
