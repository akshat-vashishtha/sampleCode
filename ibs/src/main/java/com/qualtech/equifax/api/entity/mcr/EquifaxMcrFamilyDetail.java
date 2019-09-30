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
@Table(name="IB_EQ_M_FAMILY_DTLS")
@JsonIgnoreProperties(ignoreUnknown=true)
public class EquifaxMcrFamilyDetail {
	
	
	@Column(name="noofdependents")
	private String noofdependents ;
	@Column(name="additionalnametype")
	private String additionalnametype;
	@Column(name="additionalname")
	private String additionalname;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IB_EQ_MCR_FAMILY_SQC")
	@SequenceGenerator(name="IB_EQ_MCR_FAMILY_SQC", sequenceName = "IB_EQ_MCR_FAMILY_SQC", allocationSize = 1)
    @Column(name="FAMILYDETAIL_ID")
	private int familyDetailid;
	@ManyToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID", nullable=false)
	@JsonIgnore
	private EquifaxMcrAllDetaills  equifaxMcrDetailsLogs;
	
	
	public String getNoofdependents() {
		return noofdependents;
	}
	public void setNoofdependents(String noofdependents) {
		this.noofdependents = noofdependents;
	}
	public String getAdditionalnametype() {
		return additionalnametype;
	}
	public void setAdditionalnametype(String additionalnametype) {
		this.additionalnametype = additionalnametype;
	}
	public String getAdditionalname() {
		return additionalname;
	}
	public void setAdditionalname(String additionalname) {
		this.additionalname = additionalname;
	}
	public int getFamilyDetailid() {
		return familyDetailid;
	}
	public void setFamilyDetailid(int familyDetailid) {
		this.familyDetailid = familyDetailid;
	}
	public EquifaxMcrAllDetaills getEquifaxMcrDetailsLogs() {
		return equifaxMcrDetailsLogs;
	}
	public void setEquifaxMcrDetailsLogs(EquifaxMcrAllDetaills equifaxMcrDetailsLogs) {
		this.equifaxMcrDetailsLogs = equifaxMcrDetailsLogs;
	}
	
	
	
}
