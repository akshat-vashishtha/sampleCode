package com.qualtech.crif.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="IB_CRF_INDV_IDS")
@Entity
public class IndvIds 
{
	
   private String	id_type;
   private String id_value;
   private Long indv_uq_id;
   private IndvResponseDetails indvresponsedetails; 
   
   @ManyToOne
   @JoinColumn(name="INDV_RSP_DTL_ID", nullable=false)
    public IndvResponseDetails getIndvresponsedetails() {
	return indvresponsedetails;
	}
	public void setIndvresponsedetails(IndvResponseDetails indvresponsedetails) {
		this.indvresponsedetails = indvresponsedetails;
	}

	public String getId_type() {
		return id_type;
	}
	public void setId_type(String id_type) {
		this.id_type = id_type;
	}
	public String getId_value() {
		return id_value;
	}
	public void setId_value(String id_value) {
		this.id_value = id_value;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_INDV_UNQ_ID_SQC")
	@SequenceGenerator(name = "IB_CRF_INDV_UNQ_ID_SQC", sequenceName = "IB_CRF_INDV_UNQ_ID_SQC", allocationSize = 1)
	@Column(name="INDV_UQ_ID")
	public Long getIndv_uq_id() {
		return indv_uq_id;
	}
	public void setIndv_uq_id(Long indv_uq_id) {
		this.indv_uq_id = indv_uq_id;
	}
	

}
