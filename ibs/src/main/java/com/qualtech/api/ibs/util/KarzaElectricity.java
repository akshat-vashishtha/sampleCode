package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KarzaElectricity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2383223733884681926L;
	private String consumerId;
	private String serviceProvider;
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	public String getServiceProvider() {
		return serviceProvider;
	}
	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}
	@Override
	public String toString() {
		return "KarzaElectricity [consumerId=" + consumerId + ", serviceProvider=" + serviceProvider + "]";
	}
	
	

}
