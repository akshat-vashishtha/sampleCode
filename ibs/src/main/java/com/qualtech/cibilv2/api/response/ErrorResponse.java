package com.qualtech.cibilv2.api.response;

import java.io.Serializable;
import java.util.List;

public class ErrorResponse implements Serializable{

	private static final long serialVersionUID = -6670165760610962699L;
	
	
	private List<InformationData> informationData;


	public List<InformationData> getInformationData() {
		return informationData;
	}


	public void setInformationData(List<InformationData> informationData) {
		this.informationData = informationData;
	}

		

}
