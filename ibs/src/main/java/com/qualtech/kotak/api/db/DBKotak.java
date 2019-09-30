package com.qualtech.kotak.api.db;


import com.qualtech.api.db.AuthValidator;
import com.qualtech.kotak.api.dto.KotakRequest;
import com.qualtech.kotak.api.request.Header;
import com.qualtech.kotak.api.request.KotakReqRes;
import com.qualtech.kotak.api.request.KotakReversalPayload;
import com.qualtech.kotak.api.response.KotakResponse;
import com.qualtech.kotak.api.response.KotakReversalResponse;

public interface DBKotak {
	
	//public int insertkotakReversalRequest(KotakRequestReversal kotakRequest, Long uId);
	public int insertkotakPaymentRequest(KotakRequest kotakRequest, Long uId);
    public int insertKotakRequest(KotakReqRes rq);
    public int insertKotakReversalResponse(KotakReversalResponse kotkResponse, Long RequestUniqueId);
    public int updateInternalKotakReqResforPending(Long uniqueId, String pendingStatus); 
    public int insertKotakPaymentResponse(KotakResponse kotakResponse, Long uniqueId);
    public int insertKotakReversalRequestResponse(KotakFULLRequestResponse kotkrqres);
    public int insertKotakPaymentRequestResponse(KotakFULLRequestResponse kotkrqres);
    public AuthValidator validateAuth(Header header);
	int insertkotakReversalRequest(KotakReversalPayload KotkRevPayload, Long UniqueId);
   
  
}
