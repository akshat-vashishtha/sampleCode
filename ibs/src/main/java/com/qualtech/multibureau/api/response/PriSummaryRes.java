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
@Table(name = "IB_BUREAU_SECSUMMARY_RES")
public class PriSummaryRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_SECSUMMARY_SQC", allocationSize = 1)
	@JsonIgnore
	private int secSummaryId;

	private String noofothermfis;
	private String noofdefaultaccounts;
	private String noofactiveaccounts;
	private String noofownmfi;
	private String noofclosedaccounts;
	private String totalresponses;

	@OneToOne
	@JoinColumn(name = "indResId", nullable = false)
	@JsonIgnore
	private INDVRes indvRes;

	public int getSecSummaryId() {
		return secSummaryId;
	}

	public void setSecSummaryId(int secSummaryId) {
		this.secSummaryId = secSummaryId;
	}

	public String getNoofothermfis() {
		return noofothermfis;
	}

	public void setNoofothermfis(String noofothermfis) {
		this.noofothermfis = noofothermfis;
	}

	public String getNoofdefaultaccounts() {
		return noofdefaultaccounts;
	}

	public void setNoofdefaultaccounts(String noofdefaultaccounts) {
		this.noofdefaultaccounts = noofdefaultaccounts;
	}

	public String getNoofactiveaccounts() {
		return noofactiveaccounts;
	}

	public void setNoofactiveaccounts(String noofactiveaccounts) {
		this.noofactiveaccounts = noofactiveaccounts;
	}

	public String getNoofownmfi() {
		return noofownmfi;
	}

	public void setNoofownmfi(String noofownmfi) {
		this.noofownmfi = noofownmfi;
	}

	public String getNoofclosedaccounts() {
		return noofclosedaccounts;
	}

	public void setNoofclosedaccounts(String noofclosedaccounts) {
		this.noofclosedaccounts = noofclosedaccounts;
	}

	public String getTotalresponses() {
		return totalresponses;
	}

	public void setTotalresponses(String totalresponses) {
		this.totalresponses = totalresponses;
	}

	public INDVRes getIndvRes() {
		return indvRes;
	}

	public void setIndvRes(INDVRes indvRes) {
		this.indvRes = indvRes;
	}

	@Override
	public String toString() {
		return "PriSummaryRes [secSummaryId=" + secSummaryId + ", noofothermfis=" + noofothermfis
				+ ", noofdefaultaccounts=" + noofdefaultaccounts + ", noofactiveaccounts=" + noofactiveaccounts
				+ ", noofownmfi=" + noofownmfi + ", noofclosedaccounts=" + noofclosedaccounts + ", totalresponses="
				+ totalresponses + "]";
	}

}
