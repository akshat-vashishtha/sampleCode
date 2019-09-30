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
@Table(name = "IB_BUREAU_EXPERIAN_SCORE_RES")
public class ExperianScoreRes implements Serializable {

	private static final long serialVersionUID = 925018053430393766L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_EXP_SCORE_SQC", allocationSize = 1)
	@JsonIgnore
	private int expScoreId;

	private String creditrating;
	private String bureauscore;
	private String bureauscoreconfidlevel;

	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public int getExpScoreId() {
		return expScoreId;
	}

	public void setExpScoreId(int expScoreId) {
		this.expScoreId = expScoreId;
	}

	public String getCreditrating() {
		return creditrating;
	}

	public void setCreditrating(String creditrating) {
		this.creditrating = creditrating;
	}

	public String getBureauscore() {
		return bureauscore;
	}


	public void setBureauscore(String bureauscore) {
		this.bureauscore = bureauscore;
	}


	public String getBureauscoreconfidlevel() {
		return bureauscoreconfidlevel;
	}


	public void setBureauscoreconfidlevel(String bureauscoreconfidlevel) {
		this.bureauscoreconfidlevel = bureauscoreconfidlevel;
	}


	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}


	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}


	@Override
	public String toString() {
		return "ExperianScoreRes [expScoreId=" + expScoreId + ", creditrating=" + creditrating + ", bureauscore="
				+ bureauscore + ", bureauscoreconfidlevel=" + bureauscoreconfidlevel + "]";
	}

}
