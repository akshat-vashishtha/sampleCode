package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_K_ELECTRICAL_KYC_RES")
public class ElectricityResult implements Serializable {

	private static final long serialVersionUID = -1487181621558070124L;
	
	@Id
	@JsonIgnore
	private String uniqueId;
	@Column
	@JsonIgnore
	private String corelationId;
	@Column(length=1000)
	private String byte_Array;
    @Embedded
	private ElecElectricity elec;
	@Transient
    private KarzaName name;

	public ElecElectricity getElec() {
		return elec;
	}

	public void setElec(ElecElectricity elec) {
		this.elec = elec;
	}

	@Override
	public String toString() {
		return "ElectricityResult [elec=" + elec + ", name=" + name + "]";
	}

	public KarzaName getName() {
		return name;
	}

	public void setName(KarzaName name) {
		this.name = name;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getCorelationId() {
		return corelationId;
	}

	public void setCorelationId(String corelationId) {
		this.corelationId = corelationId;
	}

	public String getByte_Array() {
		return byte_Array;
	}

	public void setByte_Array(String byte_Array) {
		this.byte_Array = byte_Array;
	}

}
