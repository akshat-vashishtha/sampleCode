package com.qualtech.kotak.api.request;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name="IB_KOTAK_PAY_INSTRUMENT")
public class KotakPayInstrumentList implements Serializable {

	private static final long serialVersionUID = 532662517325133694L;
	@MapsId
	@OneToOne
	@JoinColumn(name="REQUEST_UNIQUE_ID",nullable=false)
	@JsonIgnore
	private KotakPayment enchInstrumentList;
	@Embedded
	private KotakPayInstrument instrument;
	
	@JsonIgnore
	@OneToOne (fetch=FetchType.LAZY,mappedBy="enchSet", cascade=CascadeType.ALL)
	private KotakPayEnrichmentSet enrichmentSetCopy;
	
	@Id
	private long request_unique_id;
	
	public long getRequest_unique_id() {
		return request_unique_id;
	}
	public void setRequest_unique_id(long request_unique_id) {
		this.request_unique_id = request_unique_id;
	}
	public KotakPayEnrichmentSet getEnrichmentSetCopy() {
		enrichmentSetCopy.setEnchSet(this);
		return enrichmentSetCopy;
	}
	public void setEnrichmentSetCopy(KotakPayEnrichmentSet enrichmentSetCopy) {
		this.enrichmentSetCopy = enrichmentSetCopy;
	}
	public KotakPayment getEnchInstrumentList() {
		return enchInstrumentList;
	}
	public void setEnchInstrumentList(KotakPayment enchInstrumentList) {
		this.enchInstrumentList = enchInstrumentList;
	}
	public KotakPayInstrument getInstrument() {
		return instrument;
	}
	public void setInstrument(KotakPayInstrument instrument) {
		this.instrument = instrument;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "KotakPayInstrumentList [instrument=" + instrument + "]";
	}
	
	

}
