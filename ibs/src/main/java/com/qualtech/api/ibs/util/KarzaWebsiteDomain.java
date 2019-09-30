package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KarzaWebsiteDomain implements Serializable{

	private String domain;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	public String toString() {
		return "KarzaWebsiteDomain [domain=" + domain + "]";
	}
	
}
