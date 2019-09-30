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
@Table(name="IB_K_ADDRESS_REQ")
public class AddressPayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
	
	@Id
	@JsonIgnore
	@Column(name="UNIQUEID")
	private Long uniqueid;
	@Column(name="ADDRESS1")
	 private String address1;
	@Column(name="ADDRESS2")
	 private String address2;
	
	@Column(name="CORELATIONID")
	@JsonIgnore
	 private String corelationid;
	
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
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
	
	
	@Override
	public String toString() {
		return "AddressPayload [address1=" + address1 + ", address2=" + address2 + "]";
	}
	
}

/*
@GeneratedValue(generator="my_gen")
@SequenceGenerator(name="my_gen",sequenceName="QCIB_K_ADDRESS_REQ_SQC")*/