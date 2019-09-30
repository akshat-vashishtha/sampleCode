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

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "IB_BUREAU_YEAR_RES")
public class YearRes implements Serializable {


	private static final long serialVersionUID = 612526037397077639L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_YEAR_SQC", allocationSize = 1)
	@JsonIgnore
	private int yrId;

	@OneToOne(fetch=FetchType.LAZY,mappedBy = "yearRes" ,cascade=CascadeType.ALL)
	private MayRes may;
	
	@OneToOne(fetch=FetchType.LAZY,mappedBy = "yearRes" ,cascade=CascadeType.ALL)
	private JuneRes jun;
	
	@OneToOne
	@JoinColumn(name = "historyMatrixId", nullable = false)
	@JsonIgnore
	private HistoryMatrixRes historyMatrixRes;

	public int getYrId() {
		return yrId;
	}

	public void setYrId(int yrId) {
		this.yrId = yrId;
	}

	public MayRes getMay() {
		if (this.may != null) {
			this.may.setYearRes(this);
		}
		return may;
	}

	public void setMay(MayRes may) {
		this.may = may;
	}

	public JuneRes getJun() {
		if (this.jun != null) {
			this.jun.setYearRes(this);
		}
		return jun;
	}

	public void setJun(JuneRes jun) {
		this.jun = jun;
	}

	public HistoryMatrixRes getHistoryMatrixRes() {
		return historyMatrixRes;
	}

	public void setHistoryMatrixRes(HistoryMatrixRes historyMatrixRes) {
		this.historyMatrixRes = historyMatrixRes;
	}

	@Override
	public String toString() {
		return "YearRes [yrId=" + yrId + ", may=" + may + ", jun=" + jun + "]";
	}

}
