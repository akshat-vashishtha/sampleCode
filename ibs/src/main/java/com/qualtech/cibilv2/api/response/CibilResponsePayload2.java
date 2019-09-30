package com.qualtech.cibilv2.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CibilResponsePayload2 implements Serializable
{

	private static final long serialVersionUID = -5670159666669645374L;
	
	private Root root;
	private String byteArray;
	private String pdfPath;
	
	public String getByteArray() {
		return byteArray;
	}

	public void setByteArray(String byteArray) {
		this.byteArray = byteArray;
	}

	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public Root getRoot() {
		return root;
	}

	public void setRoot(Root root) {
		this.root = root;
	}
		
}
