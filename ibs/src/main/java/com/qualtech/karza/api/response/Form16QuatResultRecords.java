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
@Table(name="IB_K_FORM16QUAT_RES")
public class Form16QuatResultRecords implements Serializable{

	
	 private static final long serialVersionUID = -8251784410816419882L;
	 @Id
	 @JsonIgnore
	 private String uniqueId;
	 @Column
	 @JsonIgnore
	 private String correlation_Id;
	 @Column
	 private String q1;
	 @Column
	 private String q2;
	 @Column
	 private String q3;
	 @Column
	 private String q4;
	public String getQ1() {
		return q1;
	}
	public void setQ1(String q1) {
		this.q1 = q1;
	}
	public String getQ2() {
		return q2;
	}
	public void setQ2(String q2) {
		this.q2 = q2;
	}
	public String getQ3() {
		return q3;
	}
	public void setQ3(String q3) {
		this.q3 = q3;
	}
	public String getQ4() {
		return q4;
	}
	public void setQ4(String q4) {
		this.q4 = q4;
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
	public String getCorrelation_Id() {
		return correlation_Id;
	}
	public void setCorrelation_Id(String correlation_Id) {
		this.correlation_Id = correlation_Id;
	}
	@Override
	public String toString() {
		return "Form16QuatResultRecords [q1=" + q1 + ", q2=" + q2 + ", q3=" + q3 + ", q4=" + q4 + "]";
	}
}
