package com.qualtech.crif.api.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Table(name="IB_CRF_INDV_RELATION")
@Entity
public class IndvRelation 
{
	private String relation_name;
	private String  relation_type;
	private Long  indv_relation_id;
	private IndvResponseDetails indvresponsedetails;
	
	@JoinColumn(name="INDV_RSP_DTL_ID", nullable=false)
	@ManyToOne
	public IndvResponseDetails getIndvresponsedetails() {
		return indvresponsedetails;
	}
	public void setIndvresponsedetails(IndvResponseDetails indvresponsedetails) {
		this.indvresponsedetails = indvresponsedetails;
	}
	
	public String getRelation_name() {
		return relation_name;
	}
	public void setRelation_name(String relation_name) {
		this.relation_name = relation_name;
	}
	public String getRelation_type() {
		return relation_type;
	}
	public void setRelation_type(String relation_type) {
		this.relation_type = relation_type;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_INDV_RELATION_SQC")
	@SequenceGenerator(name = "IB_CRF_INDV_RELATION_SQC", sequenceName = "IB_CRF_INDV_RELATION_SQC", allocationSize = 1)
	public Long getIndv_relation_id() {
		return indv_relation_id;
	}
	public void setIndv_relation_id(Long indv_relation_id) {
		this.indv_relation_id = indv_relation_id;
	}
	
}
