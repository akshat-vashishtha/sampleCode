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
@Table(name = "IB_BUREAU_ID_JSON_RES")
public class IDJsonRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_ID_JSON_RES_SQC", allocationSize = 1)
	@JsonIgnore
	private int jsonId;

	private String enrichedthroughtenquiry;
	
	private String idtype;
	
	private String idvalue;

	@ManyToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	public int getJsonId() {
		return jsonId;
	}

	public void setJsonId(int jsonId) {
		this.jsonId = jsonId;
	}

	public String getEnrichedthroughtenquiry() {
		return enrichedthroughtenquiry;
	}

	public void setEnrichedthroughtenquiry(String enrichedthroughtenquiry) {
		this.enrichedthroughtenquiry = enrichedthroughtenquiry;
	}

	public String getIdtype() {
		return idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype;
	}

	public String getIdvalue() {
		return idvalue;
	}

	public void setIdvalue(String idvalue) {
		this.idvalue = idvalue;
	}

	@Override
	public String toString() {
		return "IDJsonRes [jsonId=" + jsonId + ", enrichedthroughtenquiry=" + enrichedthroughtenquiry + ", idtype="
				+ idtype + ", idvalue=" + idvalue + "]";
	}

	
}
