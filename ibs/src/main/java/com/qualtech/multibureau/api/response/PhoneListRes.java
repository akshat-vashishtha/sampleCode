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
@Table(name = "IB_BUREAU_PHONELIST_RES")
public class PhoneListRes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6643871142442458605L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_PHONELIST_SQC", allocationSize = 1)
	@JsonIgnore
	private int phnListId;

	private String phone;

	@ManyToOne
	@JoinColumn(name = "phnId", nullable = false)
	@JsonIgnore
	private PhoneRes phoneRes;

	public int getPhnListId() {
		return phnListId;
	}

	public void setPhnListId(int phnListId) {
		this.phnListId = phnListId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public PhoneRes getPhoneRes() {
		return phoneRes;
	}

	public void setPhoneRes(PhoneRes phoneRes) {
		this.phoneRes = phoneRes;
	}

	@Override
	public String toString() {
		return "PhoneListRes [phnListId=" + phnListId + ", phone=" + phone + "]";
	}
	
}
