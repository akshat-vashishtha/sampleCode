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
@Table(name = "IB_BUREAU_ADDRESS_RES")
public class AddressRes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8679570251419195285L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ADDRESS_SQC", allocationSize = 1)
	@JsonIgnore
	private int addId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "addressRes", cascade = CascadeType.ALL)
	private List<AddressListRes> address;

	@OneToOne
	@JoinColumn(name = "reqId", nullable = false)
	@JsonIgnore
	private RequestRes requestRes;

	public int getAddId() {
		return addId;
	}

	public void setAddId(int addId) {
		this.addId = addId;
	}

	public List<AddressListRes> getAddress() {
		
		if(address!=null)
		{
			for(AddressListRes res:address)
			{
				res.setAddressRes(this);
			}
		}
		return address;
	}

	public void setAddress(List<AddressListRes> address) {
		this.address = address;
	}

	public RequestRes getRequestRes() {
		return requestRes;
	}

	public void setRequestRes(RequestRes requestRes) {
		this.requestRes = requestRes;
	}

	@Override
	public String toString() {
		return "AddressRes [addId=" + addId + ", address=" + address + "]";
	}

}
