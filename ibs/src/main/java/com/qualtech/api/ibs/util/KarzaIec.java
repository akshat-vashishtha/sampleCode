package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KarzaIec implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -7267655936736420594L;
private String iec;

public String getIec() {
	return iec;
}

public void setIec(String iec) {
	this.iec = iec;
}

@Override
public String toString() {
	return "KarzaIec [iec=" + iec + "]";
}


}
