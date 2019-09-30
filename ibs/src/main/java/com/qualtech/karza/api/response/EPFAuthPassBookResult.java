package com.qualtech.karza.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@Entity
@Table(name="IB_K_EPF_AUTH_EMPDTL")
@JsonIgnoreProperties(ignoreUnknown = true)
public class EPFAuthPassBookResult implements Serializable{

	
	private static final long serialVersionUID = -8251784410816419882L;
	@Id
	@JsonIgnore
	private String uniqueid;
	
	
	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	@Embedded
	private EPFAuthPassBookEmpDetails employee_details;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="ePFAuthPassBookResult", cascade=CascadeType.ALL)
	private List<EPFAuthPassBookESTDetails> est_details;

	public EPFAuthPassBookEmpDetails getEmployee_details() {
		return employee_details;
	}

	public void setEmployee_details(EPFAuthPassBookEmpDetails employee_details) {
		this.employee_details = employee_details;
	}

	public List<EPFAuthPassBookESTDetails> getEst_details() {
		if(est_details!=null) {
			for (EPFAuthPassBookESTDetails epfAuthPassBookESTDetails : est_details) {
				epfAuthPassBookESTDetails.setePFAuthPassBookResult(this);
			}
			
		}
		return est_details;
	}

	public void setEst_details(List<EPFAuthPassBookESTDetails> est_details) {
		this.est_details = est_details;
	}
	


	
}
