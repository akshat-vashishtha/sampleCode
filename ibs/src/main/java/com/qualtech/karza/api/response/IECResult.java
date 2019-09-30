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
@Table(name="IB_K_IEC_RES")
public class IECResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
	@Id
	@JsonIgnore
	private String uniqueId;
	@Column
	@JsonIgnore
	private String  correlation_Id;
	@Column
	private String name;
	@Column
	private String ieCode;
	@Column
	private String address;
	@Column
	private String noOfBranches;
	@Column
	private String iecStatus;
	@Column
	private String pan;
	@Column
	private String del_status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIeCode() {
		return ieCode;
	}
	public void setIeCode(String ieCode) {
		this.ieCode = ieCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNoOfBranches() {
		return noOfBranches;
	}
	public void setNoOfBranches(String noOfBranches) {
		this.noOfBranches = noOfBranches;
	}
	public String getIecStatus() {
		return iecStatus;
	}
	public void setIecStatus(String iecStatus) {
		this.iecStatus = iecStatus;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getDel_status() {
		return del_status;
	}
	public void setDel_status(String del_status) {
		this.del_status = del_status;
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
		return "IECResult [name=" + name + ", ieCode=" + ieCode + ", address=" + address + ", noOfBranches="
				+ noOfBranches + ", iecStatus=" + iecStatus + ", pan=" + pan + ", del_status=" + del_status + "]";
	}
	
	
}
