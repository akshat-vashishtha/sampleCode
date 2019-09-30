package com.qualtech.kotak.api.utils;

import com.qualtech.kotak.api.dto.KotakDto;
import com.qualtech.kotak.api.dto.KotakRequest;
import com.qualtech.kotak.api.dto.KotakRequestReversal;
import com.qualtech.kotak.api.response.KotakResponse;
import com.qualtech.kotak.api.response.KotakReversalResponse;

public interface PDFConverterInt {
	
	public KotakDto getPdfByteArrayRevarsal(KotakReversalResponse kotkResponse, KotakRequestReversal kotakRequest);

	public KotakDto getPdfByteArrayPayment(KotakResponse kotkResponse,
			KotakRequest kotakRequest);

}
