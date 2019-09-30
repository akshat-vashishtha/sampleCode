package com.qualtech.equifax.api.entity.mcr;

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

import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;
@Entity
@Table(name="IB_EQ_M_Identity_DTLS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxMcrIdentityDetail  {
	
	
	 @Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_IDENTITY_SQC")
		@SequenceGenerator(name="IB_EQ_MCR_IDENTITY_SQC", sequenceName = "IB_EQ_MCR_IDENTITY_SQC", allocationSize = 1)
	    @Column(name="IDENTITY_INFO_ID")
	private int identityId;
	@Column
	private String idnumber; 
	@Column(name="ID_TYPE")
	private String seq;
	@Column(name="REPORTED_DATE")
	private String  reporteddate; 
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxMcrAllDetaills  equifaxMcrDetailsLogs;
	
	public int getIdentityId() {
		return identityId;
	}
	public void setIdentityId(int identityId) {
		this.identityId = identityId;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
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
	public EquifaxMcrAllDetaills getEquifaxMcrDetailsLogs() {
		return equifaxMcrDetailsLogs;
	}
	public void setEquifaxMcrDetailsLogs(EquifaxMcrAllDetaills equifaxMcrDetailsLogs) {
		this.equifaxMcrDetailsLogs = equifaxMcrDetailsLogs;
	}

	
	}
