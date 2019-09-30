package com.qualtech.experiankickoff.api.utils;

import java.awt.Insets;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.axis.encoding.Base64;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zefer.pd4ml.PD4Constants;
import org.zefer.pd4ml.PD4ML;

import com.qualtech.api.db.PropertyFile;
import com.qualtech.experiankickoff.api.common.dto.ExperianDto;



@Service
public class KickOffPDFConverter implements PDFConverterInterface
{

	private static Logger logger = Logger.getLogger(KickOffPDFConverter.class.getClass());
	
	@Autowired
	private PropertyFile resProp;

	
	public  ExperianDto  convertHtmlToPDF(String htmlData,String pdfName)
	{
		ExperianDto experiandto = new ExperianDto();
		String fileName="";
		java.io.FileOutputStream fos =null;
		FileInputStream fis = null;
		if(htmlData!=null && !htmlData.equals("")){
			try {
					logger.info("Inside HtmlToPdf Convertor ");
					htmlData=htmlData.replaceAll("\\\\\"","'");
					htmlData=htmlData.replaceAll("\\\\r\\\\n","");
					String str=htmlData.replaceAll("\\\\t","");
					
					String header = str.substring(0,str.indexOf("</pd4ml:page.header>")+20);
					String footer = str.substring(str.indexOf("<pd4ml:page.footer"),str.indexOf("</pd4ml:page.footer>")+20);
					String content = str.substring(str.indexOf("</pd4ml:page.footer>")+20,str.indexOf("</body>"));
					
					String finalhtmldata=header+content+footer+"</body></html>";
					finalhtmldata=finalhtmldata.replaceAll("pd4ml:page.","");
					finalhtmldata=finalhtmldata.replaceAll("color: #FFF;","color: #000;");
					
					
					ByteArrayInputStream inputStream = new ByteArrayInputStream(finalhtmldata.getBytes());
					InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
					fileName=resProp.getString("com.kickoff.pdf.save")+pdfName+".pdf";
					File output = new File(fileName);
				    fos= new java.io.FileOutputStream(output);
			
					PD4ML pd4ml = new PD4ML();
						
					pd4ml.setHtmlWidth(1000); // set frame width of "virtual web browser" 
						
					// choose target paper format and "rotate" it to landscape orientation
					pd4ml.setPageSize(pd4ml.changePageOrientation(PD4Constants.A3)); 
						
					// define PDF page margins
					pd4ml.setPageInsetsMM(new Insets(0,0,0,0)); 
			
					// source HTML document also may have margins, could be suppressed this way 
					// (PD4ML *Pro* feature):
					pd4ml.addStyle("BODY {margin: 0}", true);
						
					// If built-in basic PDF fonts are not sufficient or 
					// if you need to output non-Latin texts,
					// TTF embedding feature should help (PD4ML *Pro*)
					pd4ml.useTTF("c:/windows/fonts", true);
			
					//pd4ml.render(/*new URL(url)*/ url, fos); // actual document conversion from URL to file
					logger.info("Generating Pdf ");
					
					pd4ml.render(inputStreamReader, fos);
					
					
					logger.info("Pdf generated at:  "+fileName);
					//byte[] array = Files.readAllBytes(output.toPath());
					  byte[] array = new byte[(int) output.length()]; 

					  fis=new FileInputStream(output);
					  fis.read(array); //read file into bytes[]
					 
			       experiandto.setByteArray(Base64.encode(array));
			       experiandto.setFilePath(fileName);
			} catch (Exception e) {
				logger.error("KickOffPDFConverter || convertHtmlToPDF() || Error in Generating Pdf : "+e);
			}
			finally {
				if(fos!=null) {
					try {
						fos.close();
					} catch (IOException e) {
						logger.error("KickOffPDFConverter || convertHtmlToPDF() || closing  FileOutputStream Stream: "+e);
					}
				}
                if(fis!=null) {
                	 try {
						fis.close();
					} catch (IOException e) {
						logger.error("KickOffPDFConverter || convertHtmlToPDF() || closing  FileInputStream Stream : "+e);	
					}
				}
			}
		}
		return experiandto;
	}


}
