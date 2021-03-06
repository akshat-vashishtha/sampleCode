package com.qualtech.multibureau.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_PHONE_RES")
public class PhoneRes implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8679570251419195285L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_PHONE_SQC", allocationSize = 1)
	@JsonIgnore
	private int phnId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "phoneRes", cascade = CascadeType.ALL)
	private List<PhoneListRes> phone;

	@OneToOne
	@JoinColumn(name = "reqId", nullable = false)
	@JsonIgnore
	private RequestRes requestRes;

	public RequestRes getRequestRes() {
		return requestRes;
	}

	public void setRequestRes(RequestRes requestRes) {
		this.requestRes = requestRes;
	}

	public int getPhnId() {
		return phnId;
	}

	public void setPhnId(int phnId) {
		this.phnId = phnId;
	}

	public List<PhoneListRes> getPhone() {
		
		if(phone!=null)
		{
			for(PhoneListRes res:phone)
			{
				res.setPhoneRes(this);
			}
		}
		return phone;
	}

	public void setPhone(List<PhoneListRes> phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "PhoneRes [phnId=" + phnId + ", phone=" + phone + "]";
	}

}
