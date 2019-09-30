package com.qualtech.multibureau.api.response;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_HSTRY_MTRIX_ID_RES")
public class HistoryMatrixRes implements Serializable {


	private static final long serialVersionUID = 612526037397077639L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_CAIS_HSTRY_MTRIX_SQC", allocationSize = 1)
	@JsonIgnore
	private int historyMatrixId;

	@OneToOne(fetch=FetchType.LAZY,mappedBy = "historyMatrixRes" ,cascade=CascadeType.ALL)
	@JsonProperty("2017")
	private YearRes year_2017;
	
	@OneToOne
	@JoinColumn(name = "caisAccDetailsId", nullable = false)
	@JsonIgnore
	private CaisAccountDetailsRes caisAccountDetailsRes;

	public int getHistoryMatrixId() {
		return historyMatrixId;
	}

	public void setHistoryMatrixId(int historyMatrixId) {
		this.historyMatrixId = historyMatrixId;
	}

	public YearRes getYear_2017() {
		if (this.year_2017 != null) {
			this.year_2017.setHistoryMatrixRes(this);
		}
		return year_2017;
	}

	public void setYear_2017(YearRes year_2017) {
		this.year_2017 = year_2017;
	}

	public CaisAccountDetailsRes getCaisAccountDetailsRes() {
		return caisAccountDetailsRes;
	}

	public void setCaisAccountDetailsRes(CaisAccountDetailsRes caisAccountDetailsRes) {
		this.caisAccountDetailsRes = caisAccountDetailsRes;
	}

	@Override
	public String toString() {
		return "HistoryMatrixRes [historyMatrixId=" + historyMatrixId + ", year_2017=" + year_2017 + "]";
	}

}
