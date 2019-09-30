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
@Table(name="IB_EQ_P_PHN_DTLS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxPcsPhoneDetails {
	 @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_PCS_PHONE_SQC")
	  @SequenceGenerator(name="IB_EQ_PCS_PHONE_SQC", sequenceName = "IB_EQ_PCS_PHONE_SQC", allocationSize = 1)
	  @Column(name="PHONEDETAIL_ID")
	private Long phoneDetail_id;
	@Column(name="TYPE_CODE")
	private String phoneTypecode;
	@Column(name="SEQ")
	private String phoneSeq;
	@Column(name="PHONENUMBER")
	private String phoneNumber;
	@Column(name="REPORTEDDATE")
	private String phoneReportedDate;
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
	public Long getPhoneDetail_id() {
		return phoneDetail_id;
	}
	public void setPhoneDetail_id(Long phoneDetail_id) {
		this.phoneDetail_id = phoneDetail_id;
	}
	public String getPhoneTypecode() {
		return phoneTypecode;
	}
	public void setPhoneTypecode(String phoneTypecode) {
		this.phoneTypecode = phoneTypecode;
	}
	public String getPhoneSeq() {
		return phoneSeq;
	}
	public void setPhoneSeq(String phoneSeq) {
		this.phoneSeq = phoneSeq;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPhoneReportedDate() {
		return phoneReportedDate;
	}
	public void setPhoneReportedDate(String phoneReportedDate) {
		this.phoneReportedDate = phoneReportedDate;
	}
	
}
