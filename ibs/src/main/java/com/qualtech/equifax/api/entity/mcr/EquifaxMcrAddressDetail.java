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
@Table(name = "IB_EQ_M_ADRS_DTLS")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EquifaxMcrAddressDetail{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_ADDRESS_SQC")
	@SequenceGenerator(name="IB_EQ_MCR_ADDRESS_SQC", sequenceName = "IB_EQ_MCR_ADDRESS_SQC", allocationSize = 1)
	@Column(name = "addressDetail_id")
	private int addressDetailId;
	@Column
	private String address;
	@Column
	private String seq;
	@Column
	private String state;
	@Column
	private String type;
	@Column(name = "REPORTED_DATE")
	private String reporteddate;
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxMcrAllDetaills  equifaxMcrDetailsLogs;
	
	public int getAddressDetailId() {
		return addressDetailId;
	}

	public void setAddressDetailId(int addressDetailId) {
		this.addressDetailId = addressDetailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
