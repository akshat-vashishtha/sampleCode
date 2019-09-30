package com.qualtech.multibureau.api.response;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BureauResponsePayload implements Serializable  {

	private static final long serialVersionUID = 3281992692425013856L;

	private BureauResult result;
	
	private List<String> byteArray;
	//private List<String> pdfPath;
	private String pdfPath;
	public List<String> getByteArray() {
		return byteArray;
	}
	public void setByteArray(List<String> byteArray) {
		this.byteArray = byteArray;
	}
	/*public List<String> getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(List<String> pdfPath) {
		this.pdfPath = pdfPath;
	}*/
	public BureauResult getResult() {
		return result;
	}
	public void setResult(BureauResult result) {
		this.result = result;
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	@Override
	public String toString() {
		return "BureauResponsePayload [result=" + result + ", byteArray=" + byteArray + ", pdfPath=" + pdfPath + "]";
	}
	
	
	/*@Override
	public String toString() {
		return "BureauResponsePayload [result=" + result + ", byteArray=" + byteArray + ", pdfPath=" + pdfPath + "]";
	}
*/
}
