package com.qualtech.api.ibs.util;

import java.io.Serializable;

public class KarzaMCASignature implements Serializable{
private String cin;

public String getCin() {
	return cin;
}

public void setCin(String cin) {
	this.cin = cin;
}

@Override
public String toString() {
	return "KarzaMCASignature [cin=" + cin + "]";
}


}
