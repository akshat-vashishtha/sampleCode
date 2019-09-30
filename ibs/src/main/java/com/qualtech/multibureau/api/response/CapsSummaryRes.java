package com.qualtech.multibureau.api.response;

import java.io.Serializable;

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
@Table(name = "IB_BUREAU_CAPS_SUMMARY_RES")
public class CapsSummaryRes implements Serializable {

	private static final long serialVersionUID = 1874759760615069623L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAPS_SUMM_SQC", allocationSize = 1)
	@JsonIgnore
	private int capsSummaryId;

	private String capslast180days;
	private String capslast7days;
	private String capslast90days;
	private String capslast30days;

	@OneToOne
	@JoinColumn(name = "capsId", nullable = false)
	@JsonIgnore
	private CapsRes capsRes;

	public int getCapsSummaryId() {
		return capsSummaryId;
	}

	public void setCapsSummaryId(int capsSummaryId) {
		this.capsSummaryId = capsSummaryId;
	}

	public String getCapslast180days() {
		return capslast180days;
	}

	public void setCapslast180days(String capslast180days) {
		this.capslast180days = capslast180days;
	}

	public String getCapslast7days() {
		return capslast7days;
	}

	public void setCapslast7days(String capslast7days) {
		this.capslast7days = capslast7days;
	}

	public String getCapslast90days() {
		return capslast90days;
	}

	public void setCapslast90days(String capslast90days) {
		this.capslast90days = capslast90days;
	}

	public String getCapslast30days() {
		return capslast30days;
	}

	public void setCapslast30days(String capslast30days) {
		this.capslast30days = capslast30days;
	}

	public CapsRes getCapsRes() {
		return capsRes;
	}

	public void setCapsRes(CapsRes capsRes) {
		this.capsRes = capsRes;
	}

	@Override
	public String toString() {
		return "CapsSummaryRes [capsSummaryId=" + capsSummaryId + ", capslast180days=" + capslast180days
				+ ", capslast7days=" + capslast7days + ", capslast90days=" + capslast90days + ", capslast30days="
				+ capslast30days + "]";
	}

	
}
