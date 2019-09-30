package com.qualtech.icici.api.response;

public class PremCalResponse {
	private ResponseHeader header;

    private MsgInfo msgInfo;

    private PremCalResponsePayload payload;

    public void setHeader(ResponseHeader header){
        this.header = header;
    }
    public ResponseHeader getHeader(){
        return this.header;
    }
    public void setMsgInfo(MsgInfo msgInfo){
        this.msgInfo = msgInfo;
    }
    public MsgInfo getMsgInfo(){
        return this.msgInfo;
    }
    public void setPayload(PremCalResponsePayload payload){
        this.payload = payload;
    }
    public PremCalResponsePayload getPayload(){
        return this.payload;
    }
}
