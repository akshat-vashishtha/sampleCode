package com.qualtech.karza.api.response;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name="IB_K_NREGAINCOME_RES")
public class NREGAIncomeDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7293280812015612931L;
	@Id
	@Column
	@JsonIgnore
	@GeneratedValue(generator="my_gen")
	@SequenceGenerator(name="my_gen",sequenceName="IB_K_NREGAINCOME_SQC")
	private int  sequence_Id;
	@Column
	private String income_for_2017_2018;
	@Column
	private String income_for_2010_2011;
	@Column
	private String income_for_2013_2014;
	@Column
	private String income_for_2016_2017;
	@Column
	private String income_for_2008_2009;
	@Column
	private String income_for_2009_2010;
	@Column
	private String income_for_2014_2015;
	@ManyToOne
	@JoinColumn(name="uniqueId", nullable=false)
	@JsonIgnore
	private NREGAResult nregaResult;
	
	public String getIncome_for_2017_2018() {
		return income_for_2017_2018;
	}
	public void setIncome_for_2017_2018(String income_for_2017_2018) {
		this.income_for_2017_2018 = income_for_2017_2018;
	}
	public String getIncome_for_2010_2011() {
		return income_for_2010_2011;
	}
	public void setIncome_for_2010_2011(String income_for_2010_2011) {
		this.income_for_2010_2011 = income_for_2010_2011;
	}
	public String getIncome_for_2013_2014() {
		return income_for_2013_2014;
	}
	public void setIncome_for_2013_2014(String income_for_2013_2014) {
		this.income_for_2013_2014 = income_for_2013_2014;
	}
	public String getIncome_for_2016_2017() {
		return income_for_2016_2017;
	}
	public void setIncome_for_2016_2017(String income_for_2016_2017) {
		this.income_for_2016_2017 = income_for_2016_2017;
	}
	public String getIncome_for_2008_2009() {
		return income_for_2008_2009;
	}
	public void setIncome_for_2008_2009(String income_for_2008_2009) {
		this.income_for_2008_2009 = income_for_2008_2009;
	}
	public String getIncome_for_2009_2010() {
		return income_for_2009_2010;
	}
	public void setIncome_for_2009_2010(String income_for_2009_2010) {
		this.income_for_2009_2010 = income_for_2009_2010;
	}
	public String getIncome_for_2014_2015() {
		return income_for_2014_2015;
	}
	public void setIncome_for_2014_2015(String income_for_2014_2015) {
		this.income_for_2014_2015 = income_for_2014_2015;
	}
	
	public int getSequence_Id() {
		return sequence_Id;
	}
	public void setSequence_Id(int sequence_Id) {
		this.sequence_Id = sequence_Id;
	}
	@Override
	public String toString() {
		return "NREGAIncomeDetail [income_for_2017_2018=" + income_for_2017_2018 + ", income_for_2010_2011="
				+ income_for_2010_2011 + ", income_for_2013_2014=" + income_for_2013_2014 + ", income_for_2016_2017="
				+ income_for_2016_2017 + ", income_for_2008_2009=" + income_for_2008_2009 + ", income_for_2009_2010="
				+ income_for_2009_2010 + ", income_for_2014_2015=" + income_for_2014_2015 + "]";
	}
	public NREGAResult getNregaResult() {
		return nregaResult;
	}
	public void setNregaResult(NREGAResult nregaResult) {
		this.nregaResult = nregaResult;
	}

	
	
}
