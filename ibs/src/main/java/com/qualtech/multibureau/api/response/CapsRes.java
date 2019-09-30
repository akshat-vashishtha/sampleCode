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
@Table(name = "IB_BUREAU_CAPS_RES")
public class CapsRes implements Serializable {


	private static final long serialVersionUID = 612526037397077639L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAPS_SQC", allocationSize = 1)
	@JsonIgnore
	private int capsId;

	@OneToOne(fetch=FetchType.LAZY,mappedBy = "capsRes" ,cascade=CascadeType.ALL)
	private CapsSummaryRes capssummary;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "capsRes" ,cascade=CascadeType.ALL)
	private List<CapsApplicationDetailListRes> capsapplicationdetaillist;
	
	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public int getCapsId() {
		return capsId;
	}

	public void setCapsId(int capsId) {
		this.capsId = capsId;
	}

	public CapsSummaryRes getCapssummary() {
		if (this.capssummary != null) {
			this.capssummary.setCapsRes(this);
		}
		return capssummary;
	}

	public void setCapssummary(CapsSummaryRes capssummary) {
		this.capssummary = capssummary;
	}

	public List<CapsApplicationDetailListRes> getCapsapplicationdetaillist() {
		
		if(capsapplicationdetaillist!=null)
		{
			for(CapsApplicationDetailListRes res:capsapplicationdetaillist)
			{
				res.setCapsRes(this);
			}
		}
		return capsapplicationdetaillist;
	}

	public void setCapsapplicationdetaillist(List<CapsApplicationDetailListRes> capsapplicationdetaillist) {
		this.capsapplicationdetaillist = capsapplicationdetaillist;
	}

	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	@Override
	public String toString() {
		return "CapsRes [capsId=" + capsId + ", capssummary=" + capssummary + ", capsapplicationdetaillist="
				+ capsapplicationdetaillist + "]";
	}

}
