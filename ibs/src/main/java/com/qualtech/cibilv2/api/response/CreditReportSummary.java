package com.qualtech.cibilv2.api.response;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.type.TrueFalseType;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreditReportSummary implements Serializable{

	private static final long serialVersionUID = -1596616286190446014L;
	@JsonIgnore
	private List<CreditReportSummaryInfoData> CrsInfoData;
	@JsonIgnore
	private String content[];
	
	
	public List<CreditReportSummaryInfoData> getCrsInfoData() {
		return CrsInfoData;
	}
	public void setCrsInfoData(List<CreditReportSummaryInfoData> crsInfoData) {
		CrsInfoData = crsInfoData;
	}
	public String[] getContent() {
		return content;
	}
	public void setContent(String[] content) {
		this.content = content;
	}
}
