package com.qualtech.multibureau.api.response;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@Entity
@Table(name="IB_BUREAU_RES")
@JsonIgnoreProperties(ignoreUnknown = true)
public class BureauResult {
	
	
	@Id
	@JsonIgnore
	private int uniqueid;
	@Column
	@JsonIgnore
	private String corelationid;
	
	private String acknowledgement_id;
	@Embedded
	private BureauHeader header;
	private String status;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "bureauRes" ,cascade=CascadeType.ALL)
	private List<FinishedBureauRes> finished;

	@OneToMany(fetch=FetchType.LAZY,mappedBy = "bureauRes" ,cascade=CascadeType.ALL)
	private List<InProcessBureauRes> in_process;
	
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "bureauRes" ,cascade=CascadeType.ALL)
	private List<RejectBureauRes> reject;
	
	
	
	public List<InProcessBureauRes> getIn_process() {
		
		if (in_process!=null)
		{
			for(InProcessBureauRes res:in_process)
			{
				res.setBureauRes(this);
			}
		}
		return in_process;
	}
	public void setIn_process(List<InProcessBureauRes> in_process) {
		this.in_process = in_process;
	}

	public int getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(int uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getCorelationid() {
		return corelationid;
	}
	public void setCorelationid(String corelationid) {
		this.corelationid = corelationid;
	}
	public String getAcknowledgement_id() {
		return acknowledgement_id;
	}
	public void setAcknowledgement_id(String acknowledgement_id) {
		this.acknowledgement_id = acknowledgement_id;
	}
	public BureauHeader getHeader() {
		return header;
	}
	public void setHeader(BureauHeader header) {
		this.header = header;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<FinishedBureauRes> getFinished() {
		
		if(finished!=null)
		{
			for(FinishedBureauRes res:finished)
			{
				res.setBureauRes(this);
			}
		}
		return finished;
	}
	public void setFinished(List<FinishedBureauRes> finished) {
		this.finished = finished;
	}
	public List<RejectBureauRes> getReject() {
		
		if (reject!=null)
		{
			for(RejectBureauRes res:reject)
			{
				res.setBureauRes(this);
			}
		}
		return reject;
	}
	public void setReject(List<RejectBureauRes> reject) {
		this.reject = reject;
	}
	@Override
	public String toString() {
		return "BureauResult [uniqueid=" + uniqueid + ", corelationid=" + corelationid + ", acknowledgement_id="
				+ acknowledgement_id + ", header=" + header + ", status=" + status + ", finished=" + finished
				+ ", in_process=" + in_process + ", reject=" + reject + "]";
	}

}
