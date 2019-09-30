package com.qualtech.karza.api.common.dto;
import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import com.qualtech.karza.api.request.Header;
import com.qualtech.karza.api.request.TanPayload;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TanRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Header header;
	private TanPayload payload;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public TanPayload getPayload() {
		return payload;
	}
	
	public void setPayload(TanPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "TanRequest [header=" + header + ", payload=" + payload + "]";
	}
	
	
}
