package com.qualtech.api.ibs.util;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CibilIbsRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3027509764136848009L;
	private String scoreType;
	private CibilV2IBSRequest v2;
	

	public CibilV2IBSRequest getV2() {
		return v2;
	}

	public void setV2(CibilV2IBSRequest v2) {
		this.v2 = v2;
	}

	public String getScoreType() {
		return scoreType;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	@Override
	public String toString() {
		return "CibilIbsRequest [scoreType=" + scoreType + "]";
	}
	
	

}
