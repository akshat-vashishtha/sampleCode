package com.qualtech.multibureau.api.response;

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
@Table(name = "IB_BUREAU_STATUS_DETAILS")
public class StatusDetailsRes  {


	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_STATUS_SQC", allocationSize = 1)
	@JsonIgnore
	private int statusDetailsId;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "statusDetailsRes", cascade = CascadeType.ALL)
	private List<HighMarkStatusRes> status;
	
	@OneToOne
	@JoinColumn(name = "indvId", nullable = false)
	@JsonIgnore
	private INDVReportRes indvReportRes;

	public int getStatusDetailsId() {
		return statusDetailsId;
	}

	public void setStatusDetailsId(int statusDetailsId) {
		this.statusDetailsId = statusDetailsId;
	}
	public List<HighMarkStatusRes> getStatus() {
		if(status!=null)
			{
				for(HighMarkStatusRes res:status)
				{
					res.setStatusDetailsRes(this);
				}
			}
		return status;
	}

	public void setStatus(List<HighMarkStatusRes> status) {
		this.status = status;
	}

	public INDVReportRes getIndvReportRes() {
		return indvReportRes;
	}

	public void setIndvReportRes(INDVReportRes indvReportRes) {
		this.indvReportRes = indvReportRes;
	}

	@Override
	public String toString() {
		return "StatusDetailsRes [statusDetailsId=" + statusDetailsId + ", status=" + status + "]";
	}

}
