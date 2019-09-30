package com.qualtech.kotak.api.request;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_KOTAK_INST_ENRICHMENT")
public class KotakPayEnrichmentSet implements Serializable {

	private static final long serialVersionUID = -6215989502872539729L;
	
	@OneToOne
	@JoinColumn(name="UNIQUEID",nullable=false)
	@JsonIgnore
	private KotakPayInstrumentList enchSet;
	
	
	@Column(name="ENRICHMENT")
	private String enrichment;
	
	
	@Id
	@GeneratedValue(generator = "KOTAK_SQC")
	@SequenceGenerator(name = "KOTAK_SQC", sequenceName = "IB_KOTAK_ENRICH_SQC")
	private int KOTK_INST_ENRICH_ID;
	
	
	public int getKOTK_INST_ENRICH_ID() {
		return KOTK_INST_ENRICH_ID;
	}
	public void setKOTK_INST_ENRICH_ID(int kOTK_INST_ENRICH_ID) {
		KOTK_INST_ENRICH_ID = kOTK_INST_ENRICH_ID;
	}
	
	public KotakPayInstrumentList getEnchSet() {
		return enchSet;
	}
	public void setEnchSet(KotakPayInstrumentList enchSet) {
		this.enchSet = enchSet;
	}
	public String getEnrichment() {
		return enrichment;
	}
	public void setEnrichment(String enrichment) {
		this.enrichment = enrichment;
	}
	@Override
	public String toString() {
		return "KotakPayEnrichmentSet [enrichment=" + enrichment
				+ ", KOTK_INST_ENRICH_ID=" + KOTK_INST_ENRICH_ID
				+ "]";
	}
	
	
}
