package com.qualtech.karza.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
@Entity
@Table(name="IB_K_ITRAUTH_REQ")
public class ITRAuthPayload implements Serializable{


	private static final long serialVersionUID = -6914504164196462220L;
	private String uniqueid;
	private String correlationid;
	private String consent;
	private String pan;
	private String ack;
    @Column(name="CONSENT")
	public String getConsent() {
		return consent;
	}
	public void setConsent(String consent) {
		this.consent = consent;
	}
	@Column(name="PAN")
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	@Column(name="ACK")
	public String getAck() {
		return ack;
	}
	public void setAck(String ack) {
		this.ack = ack;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@JsonIgnore
	@Id
	@Column(name="UNIQUEID")
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_ITRAUTh_REQ_SQC",allocationSize=1)
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@JsonIgnore
	@Column(name="CORRELATION_ID")
	public String getCorrelationid() {
		return correlationid;
	}
	public void setCorrelationid(String correlationid) {
		this.correlationid = correlationid;
	}
	@Override
	public String toString() {
		return "ITRAuthPayload [consent=" + consent + ", pan=" + pan + ", ack=" + ack + "]";
	}
	
	
	
	
}
