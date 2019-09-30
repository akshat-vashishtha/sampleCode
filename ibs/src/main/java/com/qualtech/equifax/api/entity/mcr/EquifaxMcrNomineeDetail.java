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
@Table(name="IB_EQ_M_NOMINEE")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxMcrNomineeDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_NOMINEE_SQC")
	@SequenceGenerator(name="IB_EQ_MCR_NOMINEE_SQC", sequenceName = "IB_EQ_MCR_NOMINEE_SQC", allocationSize = 1)
	@Column(name="nomineeId")
	private int nomineeId;
	@Column(name="relationtype")
	private String relationtype;
	@Column(name="name")
	private String name;
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxMcrAllDetaills  equifaxMcrDetailsLogs;
	
	
	public EquifaxMcrAllDetaills getEquifaxMcrDetailsLogs() {
		return equifaxMcrDetailsLogs;
	}
	public void setEquifaxMcrDetailsLogs(EquifaxMcrAllDetaills equifaxMcrDetailsLogs) {
		this.equifaxMcrDetailsLogs = equifaxMcrDetailsLogs;
	}
	public int getNomineeId() {
		return nomineeId;
	}
	public void setNomineeId(int nomineeId) {
		this.nomineeId = nomineeId;
	}
	public String getRelationtype() {
		return relationtype;
	}
	public void setRelationtype(String relationtype) {
		this.relationtype = relationtype;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
