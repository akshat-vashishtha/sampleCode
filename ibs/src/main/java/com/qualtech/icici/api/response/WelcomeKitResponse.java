package com.qualtech.icici.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.icici.api.common.Header;
import com.qualtech.icici.api.common.MsgInfo;

@JsonIgnoreProperties(ignoreUnknown=true) 
public class WelcomeKitResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private Header header;
    private MsgInfo msgInfo;
    private WelcomeKitResponsePayload payload;

    public WelcomeKitResponse() {	}

	public WelcomeKitResponse(Header header, MsgInfo msgInfo, WelcomeKitResponsePayload payload) {
		this.header = header;
		this.msgInfo = msgInfo;
		this.payload = payload;
	}

	public WelcomeKitResponsePayload getPayload ()
    {
        return payload;
    }

    public void setPayload (WelcomeKitResponsePayload payload)
    {
        this.payload = payload;
    }

    public Header getHeader ()
    {
        return header;
    }

    public void setHeader (Header header)
    {
        this.header = header;
    }

    public MsgInfo getMsgInfo ()
    {
        return msgInfo;
    }

    public void setMsgInfo (MsgInfo msgInfo)
    {
        this.msgInfo = msgInfo;
    }

	@Override
	public String toString() {
		return "WelcomeKitResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}


	
}
