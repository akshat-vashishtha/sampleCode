package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_ID_RES")
public class IDRes implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6643871142442458605L;


	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ID_RES_SQC", allocationSize = 1)
	@JsonIgnore
	private int responseId;

	
	private String value;
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "resId", nullable = false)
	@JsonIgnore
	private IDsRes idRes;

	public int getResponseId() {
		return responseId;
	}

	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public IDsRes getIdRes() {
		return idRes;
	}

	public void setIdRes(IDsRes idRes) {
		this.idRes = idRes;
	}

	@Override
	public String toString() {
		return "IDRes [responseId=" + responseId + ", value=" + value + ", type=" + type + "]";
	}
	

}
