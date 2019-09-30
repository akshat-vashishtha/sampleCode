package com.qualtech.finbit.api.response;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown=true)
public class FinbitResponsePayload 
{

	private List<FinbitResResult> result;
	private String pdfPath;
	
	public List<FinbitResResult> getResult() {
		return result;
	}
	public void setResult(List<FinbitResResult> result) {
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
		return "FinbitResponsePayload [result=" + result + ", pdfPath=" + pdfPath + "]";
	}
	
}
