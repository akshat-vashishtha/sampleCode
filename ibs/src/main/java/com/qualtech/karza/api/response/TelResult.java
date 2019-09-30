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
@Table(name = "IB_K_TELEPHONIC_KYC_RES")
public class TelResult implements Serializable {

	/**
	 * 
	 */
    @Id
    @JsonIgnore
	private String uniqueId;
    @Column
    @JsonIgnore
	private String byte_Array;
    @Column
    @JsonIgnore
	private String corelationId;
	@Embedded
	private TeleTelephone tele;
	@Transient
	private KarzaName name;

	public TeleTelephone getTele() {
		return tele;
	}

	@Override
	public String toString() {
		return "TelResult [tele=" + tele + ", name=" + name + "]";
	}

	public void setTele(TeleTelephone tele) {
		this.tele = tele;
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

	public String getByte_Array() {
		return byte_Array;
	}

	public void setByte_Array(String byte_Array) {
		this.byte_Array = byte_Array;
	}

	public String getCorelationId() {
		return corelationId;
	}

	public void setCorelationId(String corelationId) {
		this.corelationId = corelationId;
	}

}
