package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_MOBILE_PHONE_RES")
public class MobilePhoneRes implements Serializable {


	private static final long serialVersionUID = 5387667364464196072L;
	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_MOBILE_PHONE_SQC", allocationSize = 1)
	@JsonIgnore
	private int mbId;
	
	private String phonenumber;
	
	@OneToOne
	@JoinColumn(name = "inquiryId", nullable = false)
	@JsonIgnore
	private InquiryRequestInfoJsonRes inquiryInfoRes;

	public int getMbId() {
		return mbId;
	}

	public void setMbId(int mbId) {
		this.mbId = mbId;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public InquiryRequestInfoJsonRes getInquiryInfoRes() {
		return inquiryInfoRes;
	}

	public void setInquiryInfoRes(InquiryRequestInfoJsonRes inquiryInfoRes) {
		this.inquiryInfoRes = inquiryInfoRes;
	}

	@Override
	public String toString() {
		return "MobilePhoneRes [mbId=" + mbId + ", phonenumber=" + phonenumber + "]";
	}

}