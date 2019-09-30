package com.qualtech.equifax.api.entity.evdr;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxEvdrAllDetails;

@Entity
@Table(name="IB_EQ_E_PAN_DTL")
@JsonIgnoreProperties
public class EquifaxEVDRPanDetails {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_E_PAN_SQC")
	@SequenceGenerator(name="IB_EQ_E_PAN_SQC", sequenceName = "IB_EQ_E_PAN_SQC", allocationSize = 1)
	@Column(name="PANDETAIL_ID")
	private Long pandetail_id;
	@Column(name="SEQ")
	private String panSeq;
	@Column(name="PANNUMBER")
	private String panNumber;
	@Column(name="REPORTEDDATE")
	private String panReportedDate;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	private EquifaxEvdrAllDetails  equifaxevdrdetails_logs;
	
	
	public String getPanSeq() {
		return panSeq;
	}
	public void setPanSeq(String panSeq) {
		this.panSeq = panSeq;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getPanReportedDate() {
		return panReportedDate;
	}
	public void setPanReportedDate(String panReportedDate) {
		this.panReportedDate = panReportedDate;
	}
	public void setPandetail_id(Long pandetail_id) {
		this.pandetail_id = pandetail_id;
	}
	public Long getPandetail_id() {
		return pandetail_id;
	}
	public EquifaxEvdrAllDetails getEquifaxevdrdetails_logs() {
		return equifaxevdrdetails_logs;
	}
	public void setEquifaxevdrdetails_logs(EquifaxEvdrAllDetails equifaxevdrdetails_logs) {
		this.equifaxevdrdetails_logs = equifaxevdrdetails_logs;
	}
	
}
