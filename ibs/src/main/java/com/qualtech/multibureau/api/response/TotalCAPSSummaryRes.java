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
@Table(name = "IB_BUREAU_TOTALCAPS_RES")
public class TotalCAPSSummaryRes implements Serializable {


	private static final long serialVersionUID = 612526037397077639L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_TOTALCAPS_SQC", allocationSize = 1)
	@JsonIgnore
	private int totalCapsId;

	private String totalcapslast180days;
	private String totalcapslast90days;
	private String totalcapslast30days;
	private String totalcapslast7days;
	
	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;



	public int getTotalCapsId() {
		return totalCapsId;
	}


	public void setTotalCapsId(int totalCapsId) {
		this.totalCapsId = totalCapsId;
	}



	public String getTotalcapslast180days() {
		return totalcapslast180days;
	}



	public void setTotalcapslast180days(String totalcapslast180days) {
		this.totalcapslast180days = totalcapslast180days;
	}



	public String getTotalcapslast90days() {
		return totalcapslast90days;
	}



	public void setTotalcapslast90days(String totalcapslast90days) {
		this.totalcapslast90days = totalcapslast90days;
	}



	public String getTotalcapslast30days() {
		return totalcapslast30days;
	}



	public void setTotalcapslast30days(String totalcapslast30days) {
		this.totalcapslast30days = totalcapslast30days;
	}



	public String getTotalcapslast7days() {
		return totalcapslast7days;
	}



	public void setTotalcapslast7days(String totalcapslast7days) {
		this.totalcapslast7days = totalcapslast7days;
	}



	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}



	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}



	@Override
	public String toString() {
		return "TotalCAPSSummaryRes [totalCapsId=" + totalCapsId + ", totalcapslast180days=" + totalcapslast180days
				+ ", totalcapslast90days=" + totalcapslast90days + ", totalcapslast30days=" + totalcapslast30days
				+ ", totalcapslast7days=" + totalcapslast7days + "]";
	}
	
	
}
