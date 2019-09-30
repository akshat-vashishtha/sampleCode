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
@Table(name="IB_K_FSSAI_RES")
public class FSSAILicenceResult implements Serializable {

	private static final long serialVersionUID = -8251784410816419882L;
	@Id
	@JsonIgnore
	private String uniqueId;
	@Column
	@JsonIgnore
	private String correlation_Id;
	@Column
	private String status;
	@Column(name = "LIC_TYPE")
	private String licType;
	@Column(name = "LIC_NO")
	private String licNo;
	@Column(name = "FIRM_NAME")
	private String firmName;
	@Column(name = "ADDRESS")
	private String address;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLicType() {
		return licType;
	}

	public void setLicType(String licType) {
		this.licType = licType;
	}

	public String getLicNo() {
		return licNo;
	}

	public void setLicNo(String licNo) {
		this.licNo = licNo;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
		return "FSSAILicenceResult [status=" + status + ", licType=" + licType
				+ ", licNo=" + licNo + ", firmName=" + firmName + ", address="
				+ address + "]";
	}

}
