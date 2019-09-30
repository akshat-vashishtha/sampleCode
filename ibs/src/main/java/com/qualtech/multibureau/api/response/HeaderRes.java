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
@Table(name = "IB_BUREAU_HEADER_RES")
public class HeaderRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_HEADER_SQC", allocationSize = 1)
	@JsonIgnore
	private int headerId;

	
	private String dateofrequest;
	private String batchid;
	private String reportid;
	private String dateofissue;
	private String preparedforid;
	private String preparedfor;
	
	@OneToOne
	@JoinColumn(name = "indvId", nullable = false)
	@JsonIgnore
	private INDVReportRes indvReportRes;

	public int getHeaderId() {
		return headerId;
	}

	public void setHeaderId(int headerId) {
		this.headerId = headerId;
	}

	public String getDateofrequest() {
		return dateofrequest;
	}

	public void setDateofrequest(String dateofrequest) {
		this.dateofrequest = dateofrequest;
	}

	public String getBatchid() {
		return batchid;
	}

	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}

	public String getReportid() {
		return reportid;
	}

	public void setReportid(String reportid) {
		this.reportid = reportid;
	}

	public String getDateofissue() {
		return dateofissue;
	}

	public void setDateofissue(String dateofissue) {
		this.dateofissue = dateofissue;
	}

	public String getPreparedforid() {
		return preparedforid;
	}

	public void setPreparedforid(String preparedforid) {
		this.preparedforid = preparedforid;
	}

	public String getPreparedfor() {
		return preparedfor;
	}

	public void setPreparedfor(String preparedfor) {
		this.preparedfor = preparedfor;
	}

	public INDVReportRes getIndvReportRes() {
		return indvReportRes;
	}

	public void setIndvReportRes(INDVReportRes indvReportRes) {
		this.indvReportRes = indvReportRes;
	}

	@Override
	public String toString() {
		return "HeaderRes [headerId=" + headerId + ", dateofrequest=" + dateofrequest + ", batchid=" + batchid
				+ ", reportid=" + reportid + ", dateofissue=" + dateofissue + ", preparedforid=" + preparedforid
				+ ", preparedfor=" + preparedfor + "]";
	}

}
