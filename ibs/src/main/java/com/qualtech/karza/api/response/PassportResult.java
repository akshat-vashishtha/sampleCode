package com.qualtech.karza.api.response;

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
@Table(name="IB_K_PASSPORT_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PassportResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;

    private String uniqueid;
    private String correlationid;
	private String string1;
	private String string2;
	@Column(name="STRING1")
	public String getString1() {
		return string1;
	}
	public void setString1(String string1) {
		this.string1 = string1;
	}
	@Column(name="STRING2",columnDefinition="CLOB") 
	@Lob
	//@Column(name="STRING2")
	public String getString2() {
		return string2;
	}
	public void setString2(String string2) {
		this.string2 = string2;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "PassportResult [string1=" + string1 + ", string2=" + string2 + "]";
	}
	@JsonIgnore
	@Column(name="CORRELATION_ID")
	public String getCorrelationid() {
		return correlationid;
	}
	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}
	@JsonIgnore
	@Id
	@Column(name="UNIQUEID")
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_PASSPORT_RES_SQC",allocationSize=1)
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	
	

	
}
