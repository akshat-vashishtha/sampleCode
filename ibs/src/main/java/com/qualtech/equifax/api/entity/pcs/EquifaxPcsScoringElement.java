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
@Table(name="IB_EQ_P_SCORING_ELEMENTS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxPcsScoringElement 
{
	
	
	@Column(name="CODE")
	private String code;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="TYPE")
	private String type;
	@Column(name="SEQUENCE")
	private String sequence;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_P_SCORINGELEMENT_SQC")
	@SequenceGenerator(name="IB_EQ_P_SCORINGELEMENT_SQC", sequenceName = "IB_EQ_P_SCORINGELEMENT_SQC", allocationSize = 1)
	@Column(name="SCORING_ELEMENT_KEY")
	private Long scoring_element_key;

	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxPcsAllDetails equifaxPcsAllDetails;
	
	public EquifaxPcsAllDetails getEquifaxPcsAllDetails() {
		return equifaxPcsAllDetails;
	}
	public void setEquifaxPcsAllDetails(EquifaxPcsAllDetails equifaxPcsAllDetails) {
		this.equifaxPcsAllDetails = equifaxPcsAllDetails;
	}
	public EquifaxPcsAllDetails getEquifaxPcsDetailsLogs() {
		return equifaxPcsAllDetails;
	}
	public void setEquifaxPcsDetailsLogs(EquifaxPcsAllDetails equifaxPcsAllDetails) {
		this.equifaxPcsAllDetails = equifaxPcsAllDetails;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	
	public Long getScoring_element_key() {
		return scoring_element_key;
	}
	public void setScoring_element_key(Long scoring_element_key) {
		this.scoring_element_key = scoring_element_key;
	}
}
