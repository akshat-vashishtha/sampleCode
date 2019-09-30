package com.qualtech.experiankickoff.api.utils;

import com.qualtech.experiankickoff.api.common.dto.ExperianDto;

public interface PDFConverterInterface {

	ExperianDto convertHtmlToPDF(String htmlData, String pdfName)throws Exception;
}
