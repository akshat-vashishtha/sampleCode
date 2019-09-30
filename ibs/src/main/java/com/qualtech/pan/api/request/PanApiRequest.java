package com.qualtech.pan.api.request;

import java.io.Serializable;

public class PanApiRequest  implements Serializable
{
	private static final long serialVersionUID = -5479132850566839608L;
	private Header header;
	private PanRequestPayload payload;
	public PanApiRequest() {
		super();
	}
	public PanApiRequest(Header header, PanRequestPayload payload) {
		super();
		this.header = header;
		this.payload = payload;
	}
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public PanRequestPayload getPayload() {
		return payload;
	}
	public void setPayload(PanRequestPayload payload) {
		this.payload = payload;
	}
	@Override
	public String toString() {
		return "PanApiRequest [header=" + header + ", payload=" + payload + "]";
	}
}
