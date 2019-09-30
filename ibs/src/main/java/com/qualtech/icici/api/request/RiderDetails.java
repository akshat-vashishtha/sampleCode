package com.qualtech.icici.api.request;


import java.io.Serializable;
import java.util.List;

public class RiderDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7433662687551803685L;
	
	private List<Rider> rider;

	public List<Rider> getRider() {
		return rider;
	}

	public void setRider(List<Rider> rider) {
		this.rider = rider;
	}

	@Override
	public String toString() {
		return "RiderDetails [rider=" + rider + "]";
	}

}
