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
@Table(name="IB_EQ_E_EMAIL_DTLS")
@JsonIgnoreProperties(ignoreUnknown=true)

public class EquifaxEVDREmailDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_E_EMAIL_SQC")
	@SequenceGenerator(name="IB_EQ_E_EMAIL_SQC", sequenceName = "IB_EQ_E_EMAIL_SQC", allocationSize = 1)
	@Column(name="EMAILDETAILS_ID")
	private Long emailDetails_id;
	@Column(name="EMAILADDRESS")
	private String emailaddress;
	@Column(name="SEQ")
	private String seq;
	@Column(name="REPORTEDDATE")
	private String reporteddate;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	private EquifaxEvdrAllDetails  equifaxevdrdetails_logs;
	
	public Long getEmailDetails_id() {
		return emailDetails_id;
	}
	public void setEmailDetails_id(Long emailDetails_id) {
		this.emailDetails_id = emailDetails_id;
	}
	public String getEmailaddress() {
		return emailaddress;
	}
	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getReporteddate() {
		return reporteddate;
	}
	public void setReporteddate(String reporteddate) {
		this.reporteddate = reporteddate;
	}
	public EquifaxEvdrAllDetails getEquifaxevdrdetails_logs() {
		return equifaxevdrdetails_logs;
	}
	public void setEquifaxevdrdetails_logs(EquifaxEvdrAllDetails equifaxevdrdetails_logs) {
		this.equifaxevdrdetails_logs = equifaxevdrdetails_logs;
	}
	
}
