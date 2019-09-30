package com.qualtech.equifax.api.entity.pcs;

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

import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;

@Entity
@Table(name="IB_EQ_P_ADRS_DTLS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxPcsAddressDetails {

	 @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_PCS_ADDRESS_SQC")
	  @SequenceGenerator(name="IB_EQ_PCS_ADDRESS_SQC", sequenceName = "IB_EQ_PCS_ADDRESS_SQC", allocationSize = 1)
	  @Column(name="ADDRESSDETAIL_ID")
	private Long addressDetail_id;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="POSTAL")
	private String postal;
	@Column(name="SEQ")
	private String seq;
	@Column(name="STATE")
	private String state;
	@Column(name="TYPE")
	private String type;
	@Column(name="REPORTEDDATE")
	private String reportedDate;
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxPcsAllDetails   equifaxPcsAllDetails;
	
	public EquifaxPcsAllDetails getEquifaxPcsAllDetails() {
		return equifaxPcsAllDetails;
	}
	public void setEquifaxPcsAllDetails(EquifaxPcsAllDetails equifaxPcsAllDetails) {
		this.equifaxPcsAllDetails = equifaxPcsAllDetails;
	}
	public Long getAddressDetail_id() {
		return addressDetail_id;
	}
	public void setAddressDetail_id(Long addressDetail_id) {
		this.addressDetail_id = addressDetail_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostal() {
		return postal;
	}
	public void setPostal(String postal) {
		this.postal = postal;
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
	public String getReportedDate() {
		return reportedDate;
	}
	public void setReportedDate(String reportedDate) {
		this.reportedDate = reportedDate;
	}
	
	
	
}
