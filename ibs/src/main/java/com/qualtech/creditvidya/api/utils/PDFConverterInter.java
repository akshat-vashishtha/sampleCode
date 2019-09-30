package com.qualtech.creditvidya.api.utils;

import com.qualtech.creditvidya.api.common.dto.CreditDto;

public interface PDFConverterInter {

	public CreditDto  convertToPdf(String string, String correlationid);
}
