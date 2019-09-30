package com.qualtech.kotak.api.request;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_KOTAK_REQ_PAY_HDR")
public class KotakPayment implements Serializable
{


	private static final long serialVersionUID = 5785686061312194919L;
	@Id
	@Column
	private long request_unique_id;
	
	
	
	@Embedded
	private KotakPayRequestHeader payheader;
	@OneToOne (fetch=FetchType.LAZY,mappedBy="enchInstrumentList", cascade=CascadeType.ALL)
	private KotakPayInstrumentList instrumentList;
	
	public long getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(long request_unique_id) {
		this.request_unique_id = request_unique_id;
	}
	
	public KotakPayRequestHeader getPayheader() {
		return payheader;
	}
	public void setPayheader(KotakPayRequestHeader payheader) {
		this.payheader = payheader;
	}
	public KotakPayInstrumentList getInstrumentList() {
		if(instrumentList!=null) {
			instrumentList.setEnchInstrumentList(this);
		}
		return instrumentList;
	}
	public void setInstrumentList(KotakPayInstrumentList instrumentList) {
		this.instrumentList = instrumentList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "KotakPayment [payheader=" + payheader + ", instrumentList=" + instrumentList + "]";
	}
	
	
}
