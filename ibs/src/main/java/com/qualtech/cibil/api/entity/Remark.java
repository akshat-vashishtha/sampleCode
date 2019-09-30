package com.qualtech.cibil.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_CBL_DISPUTEDETAILS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Remark implements Serializable
{
	private static final long serialVersionUID = 1L;
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CBL_DISPUTEDETAILS_SQC")
	@SequenceGenerator(name = "IB_CBL_DISPUTEDETAILS_SQC", sequenceName = "IB_CBL_DISPUTEDETAILS_SQC")
	private long	    disputeid;
	@Column
	@JsonIgnore
    private String	disputedate;
	@Column
	private String remark;
	@Column
	@JsonIgnore
	private String	  cibil_unique_id;
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Override
	public String toString() {
		return "Remark [remark=" + remark + "]";
	}
	public long getDisputeid() {
		return disputeid;
	}
	public void setDisputeid(long disputeid) {
		this.disputeid = disputeid;
	}
	public String getDisputedate() {
		return disputedate;
	}
	public void setDisputedate(String disputedate) {
		this.disputedate = disputedate;
	}
	public String getCibil_unique_id() {
		return cibil_unique_id;
	}
	public void setCibil_unique_id(String cibil_unique_id) {
		this.cibil_unique_id = cibil_unique_id;
	}
}
