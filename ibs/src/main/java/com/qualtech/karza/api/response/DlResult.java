package com.qualtech.karza.api.response;

import java.io.Serializable;

public class DlResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3301762465977079433L;
	private DlDrivingLicense dl;
	private KarzaName name;
	public DlDrivingLicense getDl() {
		return dl;
	}
	public void setDl(DlDrivingLicense dl) {
		this.dl = dl;
	}
	public KarzaName getName() {
		return name;
	}
	@Override
	public String toString() {
		return "DlResult [dl=" + dl + ", name=" + name + "]";
	}
	public void setName(KarzaName name) {
		this.name = name;
	}
	
}
