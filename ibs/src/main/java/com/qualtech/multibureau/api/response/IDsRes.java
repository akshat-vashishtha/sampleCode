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
@Table(name = "IB_BUREAU_IDS_RES")
public class IDsRes implements Serializable {

	private static final long serialVersionUID = -3438668039683030193L;

	@Id
	@GeneratedValue(generator = "my_gen")
	@SequenceGenerator(name = "my_gen", sequenceName = "IB_BUREAU_IDS_RES_SQC", allocationSize = 1)
	@JsonIgnore
	private int resId;

	
	@OneToMany(fetch=FetchType.LAZY,mappedBy = "idRes" ,cascade=CascadeType.ALL)
	private List<IDRes> id;
	

	@OneToOne
	@JoinColumn(name = "reqId", nullable = false)
	@JsonIgnore
	private RequestRes requestRes;


	public int getResId() {
		return resId;
	}


	public void setResId(int resId) {
		this.resId = resId;
	}


	public List<IDRes> getId() {
		
		if(id!=null)
		{
			for(IDRes res:id)
			{
				res.setIdRes(this);
			}
		}
		return id;
	}


	public void setId(List<IDRes> id) {
		this.id = id;
	}


	public RequestRes getRequestRes() {
		return requestRes;
	}


	public void setRequestRes(RequestRes requestRes) {
		this.requestRes = requestRes;
	}


	@Override
	public String toString() {
		return "IDsRes [resId=" + resId + ", id=" + id + "]";
	}

	
}
