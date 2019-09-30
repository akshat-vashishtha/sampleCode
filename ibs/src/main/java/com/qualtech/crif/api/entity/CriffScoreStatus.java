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

@Entity
@Table(name="IB_CRF_SCORE_STATUS")
public class CriffScoreStatus 
{
	private String	option_satus;
  	private String	errors;
  	private String options;
  	private Long criff_score_status_id;
    private CriffDetailLogs crifdetaillogs;
  	
    @ManyToOne
  	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
    public CriffDetailLogs getCrifdetaillogs() {
		return crifdetaillogs;
	}
	public void setCrifdetaillogs(CriffDetailLogs crifdetaillogs) {
		this.crifdetaillogs = crifdetaillogs;
	}
	
	@Column(name="OPTIONS")
  	public String getOptions() {
		return options;
	}
	
	public void setOptions(String options) {
		this.options = options;
	}
	
	@Column(name="OPTION_SATUS")
	public String getOption_satus() {
		return option_satus;
	}
	public void setOption_satus(String option_satus) {
		this.option_satus = option_satus;
	}
	@Column(name="ERRORS")
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_CRF_SATATUS_SQC")
	@SequenceGenerator(name="IB_CRF_SATATUS_SQC", sequenceName = "IB_CRF_SATATUS_SQC", allocationSize = 1)
	@Column(name="CRIFF_SCORE_STATUS_ID")
	public Long getCriff_score_status_id() {
		return criff_score_status_id;
	}
	public void setCriff_score_status_id(Long criff_score_status_id) {
		this.criff_score_status_id = criff_score_status_id;
	}
	
}
