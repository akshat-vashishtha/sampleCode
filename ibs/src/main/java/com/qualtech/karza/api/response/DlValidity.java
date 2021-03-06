package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name = "IB_K_DL_KYC_VAL_DTL")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DlValidity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
   
    @Id
    @GeneratedValue(generator="KARZA_DL_VAL_SQC")
	@SequenceGenerator(name="KARZA_DL_VAL_SQC",sequenceName="IB_K_DL_VAL_SQC",allocationSize=1)
    @JsonIgnore
	private int kid;
	@Column(name="NON_TRANSPORT")
	private String non_transport;
	@Column(name="TRANSPORT")
	private String transport;
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="uniqueId", nullable=false)
	private DlDrivingLicense dlDrivingLicense;
	/*
	 * private int kid; private String uniqueId;
	 */

	public String getNon_transport() {
		return non_transport;
	}

	public void setNon_transport(String non_transport) {
		this.non_transport = non_transport;
	}

	public String getTransport() {
		return transport;
	}

	

	public int getKid() {
		return kid;
	}

	public void setKid(int kid) {
		this.kid = kid;
	}

	@Override
	public String toString() {
		return "DlValidity [non_transport=" + non_transport + ", transport="
				+ transport + "]";
	}

	public void setTransport(String transport) {
		this.transport = transport;
	}

	
	public DlDrivingLicense getDlDrivingLicense() {
		return dlDrivingLicense;
	}
	public void setDlDrivingLicense(DlDrivingLicense dlDrivingLicense) {
		this.dlDrivingLicense = dlDrivingLicense;
	}
	
}
