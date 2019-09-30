package com.qualtech.multibureau.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_NONCR_CAPS_RES")
public class NonCreditCAPSRes implements Serializable {


	private static final long serialVersionUID = -2687283033456955149L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_NONCR_CAPS_SQC", allocationSize = 1)
	@JsonIgnore
	private int nonCreditCAPSId;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "nonCreditCAPSRes", cascade = CascadeType.ALL)
	private NonCreditCAPSSummaryRes noncreditcapssummary;

	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public int getNonCreditCAPSId() {
		return nonCreditCAPSId;
	}

	public void setNonCreditCAPSId(int nonCreditCAPSId) {
		this.nonCreditCAPSId = nonCreditCAPSId;
	}

	
	public NonCreditCAPSSummaryRes getNoncreditcapssummary() {
		if (this.noncreditcapssummary != null) {
			this.noncreditcapssummary.setNonCreditCAPSRes(this);
		}
		return noncreditcapssummary;
	}

	public void setNoncreditcapssummary(NonCreditCAPSSummaryRes noncreditcapssummary) {
		this.noncreditcapssummary = noncreditcapssummary;
	}

	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	@Override
	public String toString() {
		return "NonCreditCAPSRes [nonCreditCAPSId=" + nonCreditCAPSId + ", noncreditcapssummary=" + noncreditcapssummary
				+ "]";
	}

}
