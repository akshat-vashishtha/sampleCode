package com.qualtech.cibilv2.api.utils;

import com.qualtech.cibilv2.api.request.CibilRequest2;
import com.qualtech.cibilv2.api.response.CibilAPIResponse2;

public interface PDFCibilConverterInt {

	public CibilDto getPdfByteArrayCibil(CibilRequest2 cibilapirequest, CibilAPIResponse2 apiResponse);
	
}
