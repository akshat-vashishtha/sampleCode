package com.qualtech.karza.api.response;


import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="IB_K_KYC_OCR_RES_FRONT")
public class KycOcrResResult implements Serializable {
    @Embedded
	private KycOcrResFront front;
    @Id
   	@Column(name="UNIQUEID")
	private Long uniqueid;
	@OneToOne(fetch=FetchType.LAZY,mappedBy="kycocrresresult",cascade=CascadeType.ALL)
	private KycOcrResFrontTop front_top;
	@OneToOne(fetch=FetchType.LAZY,mappedBy="kycocrresresult",cascade=CascadeType.ALL)
	private KycOcrResBack back;

	public Long getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(Long uniqueid) {
		this.uniqueid = uniqueid;
	}
	
	public KycOcrResFront getFront() {
		
		return front;
	}
	
	public void setFront(KycOcrResFront front) {
		this.front = front;
	}
	public KycOcrResFrontTop getFront_top() {
		front_top.setKycocrresresult(this);
		return front_top;
	}
	public void setFront_top(KycOcrResFrontTop front_top) {
		this.front_top = front_top;
	}
	public KycOcrResBack getBack() {
		back.setKycocrresresult(this);
		return back;
	}
	public void setBack(KycOcrResBack back) {
		this.back = back;
	}
	@Override
	public String toString() {
		return "KycOcrResResult [front=" + front + ", front_top=" + front_top + ", back=" + back + "]";
	}
	
	
}
