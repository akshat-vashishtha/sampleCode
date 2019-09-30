package com.qualtech.cibilv2.api.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.codehaus.jackson.annotate.JsonIgnore;
//@Entity
public class InformationData implements Serializable{

	private static final long serialVersionUID = 760968693731909064L;
	
	
	//@OneToMany(fetch=FetchType.LAZY,mappedBy = "informationData" ,cascade=CascadeType.ALL)
	private List<Field> field;

	public List<Field> getField() {
		return field;
	}

	public void setField(List<Field> field) {
		this.field = field;
	}

	/*table: IB_C2_RES_RPTTYP   
	IB_Res_RptTyp*/
}
