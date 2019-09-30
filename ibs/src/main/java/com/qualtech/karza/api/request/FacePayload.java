package com.qualtech.karza.api.request;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class FacePayload implements Serializable {

	private static final long serialVersionUID = 3981289275049382443L;
	 private String file1;
	 private String file2;
	public String getFile1() {
		return file1;
	}
	public void setFile1(String file1) {
		this.file1 = file1;
	}
	public String getFile2() {
		return file2;
	}
	public void setFile2(String file2) {
		this.file2 = file2;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "FacePayload [file1=" + file1 + ", file2=" + file2 + "]";
	}
	
}