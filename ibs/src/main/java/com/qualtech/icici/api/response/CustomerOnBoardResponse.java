package com.qualtech.icici.api.response;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.qualtech.icici.api.common.Header;
import com.qualtech.icici.api.common.MsgInfo;

@JsonIgnoreProperties(ignoreUnknown=true) 
public class CustomerOnBoardResponse implements Serializable{

	private static final long serialVersionUID = 1L;
	private Header header;
    private MsgInfo msgInfo;
    private CustomerOnBoardPayload payload;

    public CustomerOnBoardResponse() {	}

	public CustomerOnBoardResponse(Header header, MsgInfo msgInfo, CustomerOnBoardPayload payload) {
		this.header = header;
		this.msgInfo = msgInfo;
		this.payload = payload;
	}

	public CustomerOnBoardPayload getPayload ()
    {
        return payload;
    }

    public void setPayload (CustomerOnBoardPayload payload)
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
		return "CustomerOnBoardResponse [header=" + header + ", msgInfo=" + msgInfo + ", payload=" + payload + "]";
	}

}
