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
@Table(name = "IB_K_TELEPHONIC_KYC_RES")
public class TelResult2 implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @JsonIgnore
	private String uniqueId;
    @Column
    @JsonIgnore
	private String byte_Array;
    @Column
    @JsonIgnore
	private String corelationId;
    @Column
	private String category;
    @Column(name="telephone_No")
    private String telephoneNo;
    @Column
    private String name;
    @Column
    private String installation_Type;
    @Column
    private String address;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInstallation_Type() {
		return installation_Type;
	}

	public void setInstallation_Type(String installation_Type) {
		this.installation_Type = installation_Type;
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

	@Override
	public String toString() {
		return "TelResult2 [category=" + category + ", telephoneNo="
				+ telephoneNo + ", name=" + name + ", installation_Type="
				+ installation_Type + ", address=" + address + "]";
	}

}
