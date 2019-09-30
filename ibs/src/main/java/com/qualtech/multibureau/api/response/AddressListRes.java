package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_ADDRESSLIST_RES")
public class AddressListRes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6643871142442458605L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ADDRESSLIST_SQC", allocationSize = 1)
	@JsonIgnore
	private int addListId;

	private String address;

	@ManyToOne
	@JoinColumn(name = "addId", nullable = false)
	@JsonIgnore
	private AddressRes addressRes;

	public int getAddListId() {
		return addListId;
	}

	public void setAddListId(int addListId) {
		this.addListId = addListId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public AddressRes getAddressRes() {
		return addressRes;
	}

	public void setAddressRes(AddressRes addressRes) {
		this.addressRes = addressRes;
	}

	@Override
	public String toString() {
		return "AddressListRes [addListId=" + addListId + ", address=" + address + "]";
	}
	
}
