package com.qualtech.hdfc.api.response;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ApiResponseHdfcPayload {

	private MetaDataRes metaData;
	private String pdfPath;

	
	
	
	public String getPdfPath() {
		return pdfPath;
	}

	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}

	public MetaDataRes getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaDataRes metaData) {
		this.metaData = metaData;
	}

	@Override
	public String toString() {
		return "ApiResponseHdfcPayload [metaData=" + metaData + "]";
	}
	
	
	
	
}
