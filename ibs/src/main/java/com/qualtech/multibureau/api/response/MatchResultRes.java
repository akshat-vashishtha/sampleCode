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
@Table(name = "IB_BUREAU_MATCH_RESULT_RES")
public class MatchResultRes implements Serializable {


	private static final long serialVersionUID = 612526037397077639L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_MTCH_RESULT_SQC", allocationSize = 1)
	@JsonIgnore
	private int matchResultId;

	private String exactmatch;

	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;
	
	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	public int getMatchResultId() {
		return matchResultId;
	}

	public void setMatchResultId(int matchResultId) {
		this.matchResultId = matchResultId;
	}

	public String getExactmatch() {
		return exactmatch;
	}

	public void setExactmatch(String exactmatch) {
		this.exactmatch = exactmatch;
	}

	@Override
	public String toString() {
		return "MatchResultRes [matchResultId=" + matchResultId + ", exactmatch=" + exactmatch + "]";
	}

		
}
