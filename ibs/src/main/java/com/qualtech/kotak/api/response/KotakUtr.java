package com.qualtech.kotak.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class KotakUtr implements Serializable {

	private static final long serialVersionUID = -2432533057270877575L;
	private boolean nameSpaceNil;
	private String nameSpace;

	
	public boolean isNameSpaceNil() {
		return nameSpaceNil;
	}
	public void setNameSpaceNil(boolean nameSpaceNil) {
		this.nameSpaceNil = nameSpaceNil;
	}
	public String getNameSpace() {
		return nameSpace;
	}
	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "KotakUtr [nameSpaceNil=" + nameSpaceNil + ", nameSpace=" + nameSpace + "]";
	}
	
	
}
