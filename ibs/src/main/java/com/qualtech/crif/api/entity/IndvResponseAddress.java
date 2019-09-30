package com.qualtech.crif.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="IB_CRF_INDV_RESPONSE_ADDRESS")
public class IndvResponseAddress 
{
private Long indvaddressid;
private String address;
private IndvResponseDetails  indvresponsedetails;


@OneToOne
@JoinColumn(name="INDV_RSP_DTL_ID", nullable=false)
public IndvResponseDetails getIndvresponsedetails() {
	return indvresponsedetails;
}
public void setIndvresponsedetails(IndvResponseDetails indvresponsedetails) {
	this.indvresponsedetails = indvresponsedetails;
}


@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_INDV_RPS_ADDRESS_SQC")
@SequenceGenerator(name = "IB_CRF_INDV_RPS_ADDRESS_SQC", sequenceName = "IB_CRF_INDV_RPS_ADDRESS_SQC", allocationSize = 1)
public Long getIndvaddressid() {
	return indvaddressid;
}
public void setIndvaddressid(Long indvaddressid) {
	this.indvaddressid = indvaddressid;
}



public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

	
}
