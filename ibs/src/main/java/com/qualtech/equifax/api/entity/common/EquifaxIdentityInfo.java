package com.qualtech.equifax.api.entity.common;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.equifax.api.entity.EquifaxMcrAllDetaills;
import com.qualtech.equifax.api.entity.mcr.EquifaxMCRIdAndContactDetails;
@Entity 
@Table(name="IB_EQ_M_ID_CNTCT_DTLS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxIdentityInfo 
{
	
	@Transient
	private String idother;
	//@Id
	@Column(name="IDENTITY_SEQ")
	private String seq;
	@Column(name="ID_NUMBER")
	private String  idnumber;
	@Column(name="REPORTED_DATE")
	private String  reporteddate;
	
	@Embedded
	private EquifaxMCRIdAndContactDetails idAndContactDetails;
	@Embedded
	private EquifaxPersonalInfo equifaxPersonalInfo;
	@Id
	private int request_unique_id;
	//@Id
	@OneToOne
	@MapsId
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxMcrAllDetaills  equifaxMcrDetailsLogs;
	
	
	
	
	public EquifaxMcrAllDetaills getEquifaxMcrDetailsLogs() {
		return equifaxMcrDetailsLogs;
	}
	public void setEquifaxMcrDetailsLogs(EquifaxMcrAllDetaills equifaxMcrDetailsLogs) {
		this.equifaxMcrDetailsLogs = equifaxMcrDetailsLogs;
	}
	public EquifaxMCRIdAndContactDetails getIdAndContactDetails() {
		return idAndContactDetails;
	}
	public void setIdAndContactDetails(EquifaxMCRIdAndContactDetails idAndContactDetails) {
		this.idAndContactDetails = idAndContactDetails;
	}
	public EquifaxPersonalInfo getEquifaxPersonalInfo() {
		return equifaxPersonalInfo;
	}
	public void setEquifaxPersonalInfo(EquifaxPersonalInfo equifaxPersonalInfo) {
		this.equifaxPersonalInfo = equifaxPersonalInfo;
	}
	public String getIdother() {
		return idother;
	}
	public void setIdother(String idother) {
		this.idother = idother;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public String getReporteddate() {
		return reporteddate;
	}
	public void setReporteddate(String reporteddate) {
		this.reporteddate = reporteddate;
	}
	public int getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(int request_unique_id) {
		this.request_unique_id = request_unique_id;
	}

	
	
}
