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
@Table(name = "IB_BUREAU_CAIS_ACC_RES")
public class CaisAccountRes implements Serializable {


	private static final long serialVersionUID = -2687283033456955149L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAIS_ACC_SQC", allocationSize = 1)
	@JsonIgnore
	private int caisAccountId;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "caisAccountRes", cascade = CascadeType.ALL)
	private CaisSummaryRes caissummary;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "caisAccountRes" ,cascade=CascadeType.ALL)
	private List<CaisAccountDetailsRes> caisaccountdetails;

	@OneToOne
	@JoinColumn(name = "id", nullable = false)
	@JsonIgnore
	private JsonBureauRes jsonBureauRes;

	public List<CaisAccountDetailsRes> getCaisaccountdetails() {
		
		if(caisaccountdetails!=null)
		{
			for(CaisAccountDetailsRes res:caisaccountdetails)
			{
				res.setCaisAccountRes(this);
			}
		}
		return caisaccountdetails;
	}

	public void setCaisaccountdetails(List<CaisAccountDetailsRes> caisaccountdetails) {
		this.caisaccountdetails = caisaccountdetails;
	}

	public int getCaisAccountId() {
		return caisAccountId;
	}

	public void setCaisAccountId(int caisAccountId) {
		this.caisAccountId = caisAccountId;
	}

	public CaisSummaryRes getCaissummary() {
		if (this.caissummary != null) {
			this.caissummary.setCaisAccountRes(this);
		}
		return caissummary;
	}

	public void setCaissummary(CaisSummaryRes caissummary) {
		this.caissummary = caissummary;
	}

	public JsonBureauRes getJsonBureauRes() {
		return jsonBureauRes;
	}

	public void setJsonBureauRes(JsonBureauRes jsonBureauRes) {
		this.jsonBureauRes = jsonBureauRes;
	}

	@Override
	public String toString() {
		return "CaisAccountRes [caisAccountId=" + caisAccountId + ", caissummary=" + caissummary + "]";
	}

}
