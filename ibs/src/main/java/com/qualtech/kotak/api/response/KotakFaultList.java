package com.qualtech.kotak.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Embeddable
public class KotakFaultList implements Serializable {

	private static final long serialVersionUID = -5571572574988966738L;
	@JsonIgnore
	@Transient
	private KotakResponsePayload kotakResponsePayload;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "kotakResponsePayload", cascade = CascadeType.ALL)
	private List<KotakFault> fault;
	
	
	

	public KotakResponsePayload getKotakResponsePayload() {
		return kotakResponsePayload;
	}

	public void setKotakResponsePayload(KotakResponsePayload kotakResponsePayload) {
		this.kotakResponsePayload = kotakResponsePayload;
	}

	public List<KotakFault> getFault() {
		if(fault!=null) {
			for(KotakFault kotakFault :fault) {
				kotakFault.setKotakResponsePayload(kotakResponsePayload);
		}
			}
		return fault;
	}

	public void setFault(List<KotakFault> fault) {
		this.fault = fault;
	}

	@Override
	public String toString() {
		return "KotakFaultList [fault=" + fault + "]";
	}

}
