package com.qualtech.experiankickoff.api.common.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.experiankickoff.api.request.ExperianKickOffPayload;
import com.qualtech.experiankickoff.api.request.Header;


@JsonIgnoreProperties(ignoreUnknown = true)
public class ExperianRequest implements Serializable{
	private static final long serialVersionUID = -1607073637990650355L;
	private Header header;
	private ExperianKickOffPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public ExperianKickOffPayload getPayload() {
		return payload;
	}
	
	public void setPayload(ExperianKickOffPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "ExperianRequest [header=" + header + ", payload=" + payload + "]";
	}
	

}
