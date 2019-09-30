package com.qualtech.creditvidya.api.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.creditvidya.api.common.dto.CreditDto;

import sun.misc.BASE64Decoder;

@Service
public class PDFConverter implements PDFConverterInter{
	private static  Logger logger = Logger.getLogger(PDFConverter.class.getClass());
	
	@Autowired
	private PropertyFile resProp ;
	
	@Override
	public  CreditDto  convertToPdf(String string, String correlationid) {
		CreditDto dto=new CreditDto();
		FileOutputStream fop = null;
		try {
			 String filePath=resProp.getString("com.credit.email.save.path");
			 String finalPathPdf=getPath(filePath);
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] decodedBytes = decoder.decodeBuffer(string);
			
			File file = new File(finalPathPdf +File.separator+"EMAIL_"+correlationid+".pdf");
			fop = new FileOutputStream(file);

			fop.write(decodedBytes);
			dto.setFilePath(finalPathPdf+File.separator+"EMAIL_"+correlationid+".pdf");
			
		} catch (Exception e) {
           logger.error("PDFConverter || convertToPdf() || Exception in PDF Creation :: " +e);
		}
		finally {
			if(fop!=null) {
			try {
				fop.close();
				}
			catch (IOException e) {
				logger.error("PDFConverter || convertToPdf() || Exception in closing FileOutputStream   :: " +e);
			}
		}
		}
		return dto;
	}
	private  String getPath(String filePath) {
		String path=null;
		 String downloadFolder = filePath;
		 Calendar cal=Calendar.getInstance();
		 File file = new File(downloadFolder+cal.get(Calendar.MONTH+1)+"-"+cal.get(Calendar.YEAR));
		 if (file.exists()) {
		    path=downloadFolder+cal.get(Calendar.MONTH+1)+"-"+cal.get(Calendar.YEAR);
		 }else{
			 new File(downloadFolder+cal.get(Calendar.MONTH+1)+"-"+cal.get(Calendar.YEAR)).mkdir();
			 path=downloadFolder+cal.get(Calendar.MONTH+1)+"-"+cal.get(Calendar.YEAR);
		 }
		return path;
	}


}
