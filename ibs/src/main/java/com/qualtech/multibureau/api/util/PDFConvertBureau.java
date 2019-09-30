package com.qualtech.multibureau.api.util;

import com.qualtech.multibureau.api.common.dto.AllBureauDto;
import com.qualtech.multibureau.api.common.dto.BureauRequest;
import com.qualtech.multibureau.api.response.BureauResponse;

public interface PDFConvertBureau {
	
	AllBureauDto getPdfByteArrayQC(BureauResponse dlRes, BureauRequest dlRequest, String string);
}