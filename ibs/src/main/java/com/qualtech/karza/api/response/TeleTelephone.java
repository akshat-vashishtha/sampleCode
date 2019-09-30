package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class TeleTelephone implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column
	private String category;
	@Column(name="TELEPHONE_NO")
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
	@Override
	public String toString() {
		return "TelResult [category=" + category + ", telephoneNo=" + telephoneNo + ", name=" + name
				+ ", installation_Type=" + installation_Type + ", address=" + address + "]";
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

	
}
