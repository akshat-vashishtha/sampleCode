package com.qualtech.finbit.utils;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.qualtech.api.db.PropertyFile;

public class PDFConvertor
{
	

	static Logger logger = Logger.getLogger(PDFConvertor.class.getName());
	static int pageCount=0;
	Font font;
	static PdfTemplate t;
	public static final String HEADER1 = "<table align=\"center\" border=\"0\" cellpadding=\"0\" width=\"100%\"> 								<tr>  <td class=\"dataValue\" 	style=\"font-size: 35px; text-align: center;\">Finbit Report</td> </tr></table>   ";
		
	public static final String FOOTER1 = "<table width=\"100%\" style=\"border-top:2px solid #1675a5;\"><tr><td class=\"dataHeader\" 	style=\"color: #333; text-align: center; font-size: 12px;\"><b>Qualtech . ALL RIGHTS RESERVED.</b></td> </tr></table>";//<td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Page No : PAGENODATA</td>
	public static final String FOOTER2 = "<table width=\"100%\"  border=\"0\"><tr><td class=\"dataHeader\" 	style=\"color: #333; text-align: center; font-size: 12px;\">For more information please visit our website at www.cibil.com. To log a dispute, please email us at support@cibil.com</td></tr></table>";

	public class HeaderFooter extends PdfPageEventHelper 
	{
		protected ElementList header1;
		protected ElementList header2;
		protected ElementList header3;
		protected ElementList header4;
		protected ElementList header5;
		protected ElementList header6;
		protected ElementList header7;
		protected ElementList footer1;
		protected ElementList footer2;
		private PropertyFile resProp;
		
		String FOOTER1STR="";
		 
		int i=1;
		Image img=null;
		
		public HeaderFooter(PropertyFile resProp) {
			this.resProp = resProp;
		}

		public HeaderFooter() throws IOException 
		{
			header1 = XMLWorkerHelper.parseToElementList(HEADER1, null);
		
			footer1 = XMLWorkerHelper.parseToElementList(FOOTER1, null);
			footer2 = XMLWorkerHelper.parseToElementList(FOOTER2, null);
		}
		public HeaderFooter(String HEADER2,String HEADER3,String HEADER4,String HEADER5,String HEADER6,String HEADER7) throws IOException
		{
			header1 = XMLWorkerHelper.parseToElementList(HEADER1, null);
			header2 = XMLWorkerHelper.parseToElementList(HEADER2, null);
			header3 = XMLWorkerHelper.parseToElementList(HEADER3, null);
			header4 = XMLWorkerHelper.parseToElementList(HEADER4, null);
			header5 = XMLWorkerHelper.parseToElementList(HEADER5, null);
			header6 = XMLWorkerHelper.parseToElementList(HEADER6, null);
			header7 = XMLWorkerHelper.parseToElementList(HEADER7, null);
			footer1 = XMLWorkerHelper.parseToElementList(FOOTER1, null);
			footer2 = XMLWorkerHelper.parseToElementList(FOOTER2, null);
		}
	}
	
	public static String getPath(String filePath) 
	{
		String path=null;
		String downloadFolder = filePath;
		Calendar cal=Calendar.getInstance();

		File file = new File(downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
		if (file.exists()) {
			path=downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
		}else{
			new File(downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR)).mkdir();
			path=downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
		}
		return path;
	}
	
	public static String generatePdf(String htmlCode, PropertyFile resProp) throws IOException, DocumentException
	{
		String newPath="";
		String accountUID=null;
		String finalPathPdf=null;
		try
		{
			Document document = new Document();
			
			finalPathPdf=getPath(resProp.getString("com.qualtech.finbit.pdfpath"));//getfilepath
			accountUID="lm8nsodfkspfdvnb83029jg52p";
			newPath=finalPathPdf +File.separator+"finbit_"+accountUID +".pdf";
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(newPath));
			document.setPageSize(PageSize.A3);
	        document.setMargins(20, 20, 20, 20);
	        document.getPageNumber();
	 
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, bStream);
			document.open();
	
			HTMLWorker worker = new HTMLWorker(document); 
			worker.parse(new StringReader(htmlCode.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			

		
		}
		catch(Exception eexception)
		{
			logger.error(" PDFConvertor || generatePdf() || Exception :: "+eexception);
		}
		return newPath;
	}

	/*
	static Logger logger = Logger.getLogger(PDFConvertor.class.getName());
	
	/// New Code
	

	////////   Header Part Start
	static int pageCount=0;
	public static final String HEADER1 = "<table align=\"center\" border=\"0\" cellpadding=\"0\" width=\"100%\"> 								<tr>  <td class=\"dataValue\" 	style=\"font-size: 35px; text-align: center;\">Finbit Report</td> </tr></table>   ";
		
	public static final String FOOTER1 = "<table width=\"100%\" style=\"border-top:2px solid #1675a5;\"><tr><td class=\"dataHeader\" 	style=\"color: #333; text-align: center; font-size: 12px;\"><b>Qualtech . ALL RIGHTS RESERVED.</b></td> </tr></table>";//<td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Page No : PAGENODATA</td>
	public static final String FOOTER2 = "<table width=\"100%\"  border=\"0\"><tr><td class=\"dataHeader\" 	style=\"color: #333; text-align: center; font-size: 12px;\">For more information please visit our website at www.cibil.com. To log a dispute, please email us at support@cibil.com</td></tr></table>";

	public class HeaderFooter extends PdfPageEventHelper 
	{
		protected ElementList header1;
		protected ElementList header2;
		protected ElementList header3;
		protected ElementList header4;
		protected ElementList header5;
		protected ElementList header6;
		protected ElementList header7;
		protected ElementList footer1;
		protected ElementList footer2;
		
		private PropertyFile resProp;
		
		String FOOTER1STR="";
		 private PdfTemplate t;
		int i=1;
		Image img=null;
		
		public HeaderFooter(PropertyFile resProp) {
			this.resProp = resProp;

		}

		public HeaderFooter() throws IOException 
		{
			header1 = XMLWorkerHelper.parseToElementList(HEADER1, null);
		
			footer1 = XMLWorkerHelper.parseToElementList(FOOTER1, null);
			footer2 = XMLWorkerHelper.parseToElementList(FOOTER2, null);
		}
		public HeaderFooter(String HEADER2,String HEADER3,String HEADER4,String HEADER5,String HEADER6,String HEADER7) throws IOException
		{
			header1 = XMLWorkerHelper.parseToElementList(HEADER1, null);
			header2 = XMLWorkerHelper.parseToElementList(HEADER2, null);
			header3 = XMLWorkerHelper.parseToElementList(HEADER3, null);
			header4 = XMLWorkerHelper.parseToElementList(HEADER4, null);
			header5 = XMLWorkerHelper.parseToElementList(HEADER5, null);
			header6 = XMLWorkerHelper.parseToElementList(HEADER6, null);
			header7 = XMLWorkerHelper.parseToElementList(HEADER7, null);
			footer1 = XMLWorkerHelper.parseToElementList(FOOTER1, null);
			footer2 = XMLWorkerHelper.parseToElementList(FOOTER2, null);
		}
		
		
		@Override
		public void onEndPage(PdfWriter writer, Document document) 
		{
			try
			{
				//img = Image.getInstance(env.getString("com.qc.context.path")+File.separator+"images"+File.separator+"qualtech.jpg");
				String qualtechLogo = resProp.getString("com.qualtech.finbit.logo");//logopath
				img = Image.getInstance(qualtechLogo);
				img.scalePercent(40);
				img.setAbsolutePosition(50,1130);
				document.add(img);
			} catch (Exception e) {
				e.printStackTrace();
			}
			try 
			{ 	
				ColumnText ct = new ColumnText(writer.getDirectContent());
				ct.setSimpleColumn(new Rectangle(36, 1160, 804, 1135));
				for (Element e : header1) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 1130, 804, 1120));
				for (Element e : header2) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 1110, 804, 1101)); // (36, 1096, 804, 1085));
				for (Element e : header3) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 1101, 804, 1092)); //(36, 1085, 804, 1074))
				for (Element e : header4) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 1092, 804, 1083)); // (36, 1074, 804, 1063)
				for (Element e : header5) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 1083, 804, 1072)); //(36, 1063, 804, 1052)
				for (Element e : header6) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 1072, 804, 1061));//(36, 1052, 804, 1041)
				for (Element e : header7) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 35, 804, 55));
				for (Element e : footer1) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 18, 804, 35));
				for (Element e : footer2) {
					ct.addElement(e);
				}
				ct.go();
				Phrase phrase = new Phrase();
//				phrase.add("Page No : "+String.format("%d", writer.getPageNumber())+" of "+String.format("%d",writer.getCurrentPageNumber()));
				phrase.add("Page No : "+String.format("%d", writer.getPageNumber()));
				ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_RIGHT,phrase, 804,document.bottom()-60, 0);
			} catch (DocumentException de) {
				de.printStackTrace();
			}
		}
	}
	
	
	/// END 
	public String getPath(String filePath) 
	{
		String path=null;
		String downloadFolder = filePath;
		Calendar cal=Calendar.getInstance();

		File file = new File(downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
		if (file.exists()) {
			path=downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
		}else{
			new File(downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR)).mkdir();
			path=downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
		}
		return path;
	}
	
	
	public String generatePdf(String htmlCode, PropertyFile resProp,AccountList accountList2) throws IOException, DocumentException
	{
		
		String newPath="";
		String accountUID=null;
		String finalPathPdf=null;
		try
		{
			finalPathPdf=getPath(resProp.getString("com.qualtech.finbit.pdfpath"));//getfilepath
			
			Document document = new Document(PageSize.A3, 36, 36, 150, 75);
			accountUID=accountList2.getAccountUID();
			newPath=finalPathPdf +File.separator+"finbit_"+accountUID +".pdf";
			// Delete 12345 and use corelation Id
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(newPath));
			
			// new
			
			//pdfWriter.setPageEvent(new PDFConvertor().new HeaderFooter(resProp));
			//End
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, bStream);
			document.open();
	
			HTMLWorker worker = new HTMLWorker(document); 
			worker.parse(new StringReader(htmlCode.toString()));
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			
			XMLWorkerHelper.getInstance().parseXHtml(pdfWriter, document, new ByteArrayInputStream(htmlCode.getBytes()));
			// step 5
			document.close();
			String base64 = Base64.encodeBytes(bStream.toByteArray());
			
		}
		catch(Exception eexception)
		{
			logger.error(" PDFConvertor || generatePdf() || Exception :: "+eexception);
		}
		return newPath;
		
		try
		{
			Path path = Paths.get(newPath);
			Files.delete(path);
		}
		catch(Exception eexception)
		{
			eexception.printStackTrace();
			logger.error(" PDFConvertor || generatePdf() || Exception while deleting path :: "+eexception);
		}
		
	}
	
*/}