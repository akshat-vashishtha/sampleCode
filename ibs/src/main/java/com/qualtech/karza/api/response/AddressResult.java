package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name="IB_K_ADDRESS_SCORE_RES")
public class AddressResult implements Serializable{

	private static final long serialVersionUID = -8251784410816419882L;
	
	@OneToOne(fetch=FetchType.LAZY, mappedBy="addressResult", cascade=CascadeType.ALL)
	private AddressData1 address1;
	@OneToOne(fetch=FetchType.LAZY, mappedBy="addressResult", cascade=CascadeType.ALL)
	private AddressData2 address2;
	@Id
	@Column(name="UNIQUEID")
	private Long uniqueid;
	@JsonIgnore
	@Column(name="CORRELATION_ID")
	private String corelationid;
	@Column(name="SCORE")
	private String score;
	@Column(name="MATCH")
	private String match;
	public AddressData1 getAddress1() {
		address1.setAddressResult(this);
		return address1;
	}
	public void setAddress1(AddressData1 address1) {
		this.address1 = address1;
	}
	public AddressData2 getAddress2() {
		address2.setAddressResult(this);
		return address2;
	}
	public void setAddress2(AddressData2 address2) {
		this.address2 = address2;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getMatch() {
		return match;
	}
	public void setMatch(String match) {
		this.match = match;
	}
	@Override
	public String toString() {
		return "AddressResult [address1=" + address1 + ", address2=" + address2 + ", score=" + score + ", match="
				+ match + "]";
	}
	
	
}
