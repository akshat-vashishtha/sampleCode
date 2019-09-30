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

import com.qualtech.equifax.api.entity.EquifaxPcsAllDetails;

@Entity
@Table(name="IB_EQ_P_VOTER_DTLS")
public class EquifaxPcsVoterDetails {
	 @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_PCS_VOTER_SQC")
	  @SequenceGenerator(name="IB_EQ_PCS_VOTER_SQC", sequenceName = "IB_EQ_PCS_VOTER_SQC", allocationSize = 1)
	  @Column(name="VOTERDETAIL_ID")
	private Long voterdetail_id;
	@Column(name="SEQ")
	private String voterIdSeq;
	@Column(name="VOTERIDNUM")
	private String voterIdNum;
	@Column(name="REPORTEDDATE")
	private String voterIdreRorteddate;
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
	public Long getVoterdetail_id() {
		return voterdetail_id;
	}
	public void setVoterdetail_id(Long voterdetail_id) {
		this.voterdetail_id = voterdetail_id;
	}
	public String getVoterIdSeq() {
		return voterIdSeq;
	}
	public void setVoterIdSeq(String voterIdSeq) {
		this.voterIdSeq = voterIdSeq;
	}
	public String getVoterIdNum() {
		return voterIdNum;
	}
	public void setVoterIdNum(String voterIdNum) {
		this.voterIdNum = voterIdNum;
	}
	public String getVoterIdreRorteddate() {
		return voterIdreRorteddate;
	}
	public void setVoterIdreRorteddate(String voterIdreRorteddate) {
		this.voterIdreRorteddate = voterIdreRorteddate;
	}
	
	
	
}
