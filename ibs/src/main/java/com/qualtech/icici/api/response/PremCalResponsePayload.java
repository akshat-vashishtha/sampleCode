package com.qualtech.icici.api.response;

public class PremCalResponsePayload {
	private EbiResponse ebiResponse;

    public void setEbiResponse(EbiResponse ebiResponse){
        this.ebiResponse = ebiResponse;
    }
    public EbiResponse getEbiResponse(){
        return this.ebiResponse;
    }
}
