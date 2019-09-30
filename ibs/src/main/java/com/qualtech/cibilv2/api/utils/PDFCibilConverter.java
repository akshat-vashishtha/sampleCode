package com.qualtech.cibilv2.api.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.cibilv2.api.request.CibilRequest2;
import com.qualtech.cibilv2.api.response.Account;
import com.qualtech.cibilv2.api.response.AccountInformation;
import com.qualtech.cibilv2.api.response.Alerts;
import com.qualtech.cibilv2.api.response.AlertsField;
import com.qualtech.cibilv2.api.response.AlertsInformationData;
import com.qualtech.cibilv2.api.response.Amount;
import com.qualtech.cibilv2.api.response.Borrower;
import com.qualtech.cibilv2.api.response.CibilAPIResponse2;
import com.qualtech.cibilv2.api.response.CibilResponsePayload2;
import com.qualtech.cibilv2.api.response.ConsumerInformation;
import com.qualtech.cibilv2.api.response.ConsumerInformationData;
import com.qualtech.cibilv2.api.response.ConsumerInformationDataField;
import com.qualtech.cibilv2.api.response.CrReportSummInfoDataField;
import com.qualtech.cibilv2.api.response.CreditReportSummary;
import com.qualtech.cibilv2.api.response.CreditReportSummaryInfoData;
import com.qualtech.cibilv2.api.response.Dates;
import com.qualtech.cibilv2.api.response.Dpd;
import com.qualtech.cibilv2.api.response.Field;
import com.qualtech.cibilv2.api.response.HeaderInformation;
import com.qualtech.cibilv2.api.response.HeaderInformationData;
import com.qualtech.cibilv2.api.response.HeaderInformationField;
import com.qualtech.cibilv2.api.response.InformationData;
import com.qualtech.cibilv2.api.response.Mfi;
import com.qualtech.cibilv2.api.response.MfiEmploymentInformation;
import com.qualtech.cibilv2.api.response.OtherInformation;
import com.qualtech.cibilv2.api.response.PlScore;
import com.qualtech.cibilv2.api.response.RecentEnquiry;
import com.qualtech.cibilv2.api.response.RecentEnquiryField;
import com.qualtech.cibilv2.api.response.RecentEnquiryInfoData;
import com.qualtech.cibilv2.api.response.ReportType;
import com.qualtech.cibilv2.api.response.ReportTypeField;
import com.qualtech.cibilv2.api.response.ReportTypeInformationData;
import com.qualtech.cibilv2.api.response.Root;
import com.qualtech.cibilv2.api.response.Score;
import com.qualtech.cibilv2.api.response.SearchInformation;
import com.qualtech.cibilv2.api.response.SearchInformationData;
import com.qualtech.cibilv2.api.response.SearchInformationField;
import com.qualtech.cibilv2.api.response.Status;


@Service
public class PDFCibilConverter implements PDFCibilConverterInt
{
	static Logger logger=Logger.getLogger(PDFCibilConverter.class);
	//public static ResourceBundle resBundle = PropertyFile.getInstance().getResourceBundel();
	@Autowired PropertyFile env;
	//static ResourceBundle resBundle = ResourceBundle.getBundle("mfi");

	////////   Header Part Start
	static int pageCount=0;
	public static final String HEADER1 = "<table align=\"center\" border=\"0\" cellpadding=\"0\" width=\"100%\"> 								<tr>  <td class=\"dataValue\" 	style=\"font-size: 35px; text-align: center;\">CIBIL</td> </tr></table>   ";
	public static final String HEADER2 = "<table align=\"center\" border=\"0\" cellpadding=\"0\" width=\"100%\"> 	<tr>  <td class=\"dataHeader\" 	style=\"color: #333; text-align: center; font-size: 14px;\">CREDIT INFORMATION REPORT</td> </tr></table>   ";
	public static final String HEADER3 = "<table align=\"center\" style=\"border-bottom:2px solid #1675a5;\" cellpadding=\"0\" width=\"100%\">  <tr>  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 14px; padding-left:10px;  font-family: Arial; background: #fff;	text-align: left; font-weight: normal;	padding-bottom: 3px;\">Consumer CIR</td> </tr> </table>";
	public static final String HEADER4 = "<table align=\"center\" border=\"0\" style=\"background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Consumer : </td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Date : </td> </tr> </table>";
	public static final String HEADER5 = "<table align=\"center\" border=\"0\" style=\"background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Member Id : </td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Time : </td> </tr> </table>";
	public static final String HEADER6 = "<table align=\"center\" border=\"0\" style=\"background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Member Reference Number : </td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Control Number : </td> </tr> </table>";
	public static final String HEADER7 = "<table align=\"center\" style=\"border-bottom:2px solid #1675a5; background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">BRANCH ID : </td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">KENDRA / CENTRE: </td> </tr> </table>";
	
	public static final String FOOTER1 = "<table width=\"100%\" style=\"border-top:2px solid #1675a5;\"><tr><td class=\"dataHeader\" 	style=\"color: #333; text-align: center; font-size: 12px;\"><b>© COPYRIGHT 2016 TransUnion CIBIL Limited. ALL RIGHTS RESERVED.</b></td> </tr></table>";//<td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Page No : PAGENODATA</td>
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
		String FOOTER1STR="";
		 private PdfTemplate t;
		int i=1;
		Image img=null;
		public HeaderFooter() throws IOException 
		{
			header1 = XMLWorkerHelper.parseToElementList(HEADER1, null);
			header2 = XMLWorkerHelper.parseToElementList(HEADER2, null);
			header3 = XMLWorkerHelper.parseToElementList(HEADER3, null);
			header4 = XMLWorkerHelper.parseToElementList(HEADER4, null);
			header5 = XMLWorkerHelper.parseToElementList(HEADER5, null);
			header6 = XMLWorkerHelper.parseToElementList(HEADER6, null);
			header7 = XMLWorkerHelper.parseToElementList(HEADER6, null);
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
				String qualtechLogo = env.getString("com.qc.cibil.images");
				img = Image.getInstance(qualtechLogo);
				img.scalePercent(40);
				img.setAbsolutePosition(50,1130);
				document.add(img);
			} catch (Exception e) {
				logger.error(" Could not resolved Logo path: "+e);
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
				logger.error(" Document has exception : "+de);
			}
		}
	}
	///////  Header Part End
	
	private static String getPath(String filePath) 
	{
		String path=null;
		String downloadFolder = filePath;
		Calendar cal=Calendar.getInstance();
		//System.out.println(cal.get(Calendar.MONTH)+1);

		File file = new File(downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR));
		if (file.exists()) {
			path=downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
		}else{
			new File(downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR)).mkdir();
			path=downloadFolder+(cal.get(Calendar.MONTH)+1)+"-"+cal.get(Calendar.YEAR);
		}
		return path;
	}

	public CibilDto getPdfByteArrayCibil(/*String apiResponse2,*/CibilRequest2 cibilapirequest, CibilAPIResponse2 apiResponse) {
		try 
		{
			String HEADER1 = null;
			String HEADER2 = null;
			String HEADER3 = null;
			String HEADER4 = null;
			String HEADER5 = null;
			String HEADER6 = null;
			String logoPath="";
			CibilDto dto=new CibilDto();
			String filePath= env.getString("com.qc.cibil.pdf.savePath"); 
			try
			{
				logoPath=env.getString("com.qc.cibil.images");//resProp.getString("com.karza.logo.qc");
			}
			catch(Exception e)
			{
				logger.error(" Could not resolved Logo path: "+e);
			}

			String newPath=null;
			StringBuffer sb = new StringBuffer();

			sb.append("<html>");
			sb.append("<head>");
			sb.append("<title>");
			sb.append("</title>");
			sb.append("</head>");

			sb.append("<body>");
			// JSON instances
			if (apiResponse!=null && !apiResponse.equals(""))
			{
				CibilResponsePayload2 payload = apiResponse.getPayload();
				if(payload!=null && !payload.equals(""))
				{
					Root root = apiResponse.getPayload().getRoot();
					if(root!=null && !root.equals(""))
					{
						//Report type
						ReportType reportType = root.getReportType();
						// header
						OtherInformation otherInfo = root.getOtherInformation();
						//Other Information
						HeaderInformation headerInformation = root.getHeaderInformation();
						
							List<String> headers = header(logoPath,headerInformation,otherInfo,reportType);
							 HEADER1 = headers.get(0);
							 HEADER2 = headers.get(1);
							 HEADER3 = headers.get(2);
							 HEADER4 = headers.get(3);
							 HEADER5 = headers.get(4);
							 HEADER6 = headers.get(5);
						
						
						// Search information
						SearchInformation searchInformation = root.getSearchInformation();
						
							sb.append(searchInfo(searchInformation));
						
						//Consumer Response
						//ConsumerResponseStatus consumerResponseStatus = root.getConsumerResponseStatus();
						
						//Consumer Information
						ConsumerInformation consumerInformation= root.getConsumerInformation();
						
							sb.append(consumerInfo(consumerInformation));
						
						//Credit Report Summary
						CreditReportSummary creditReportSummary = root.getCreditReportSummary();
						
							sb.append(creditSummary(creditReportSummary));
						
						//Alerts
						Alerts alerts = root.getAlerts();
						
							sb.append(alerts(alerts));
						
						//Score and Plscore
						Score scoreTag = root.getScore();
						PlScore plScore = root.getPlScore();
						
							sb.append(score(scoreTag,plScore));

							
						
						//MFI Employment Information
						MfiEmploymentInformation mfiEmpInfo = root.getMfiEmploymentInformation();
						
							sb.append(employmentInformation(mfiEmpInfo));
							
						//MFI
						List<Mfi> mfi = root.getMfi();
						
							sb.append(memberName(mfi)); 
							
						//String coborrower = root.getCoborrower();
						//Recent Enquiry
						RecentEnquiry recentEnquiry = root.getRecentEnquiry();
						
							sb.append(recentEnquiries(recentEnquiry));
						
						//ErrorResponse errorResponse = root.getErrorResponse();
						
					}
					
					
				}
			}
			
			logger.info(" PdfByteArrayCibil || JSON Instanciation done || Html Generation Starts ");
			

			sb.append("<table>");
			sb.append("<tr>");
			sb.append("<td align='left'><font size='0.5' face='arial'><font color='ff3339'></font>");
			sb.append("All information contained in this credit report has been collated by TransUnion CIBIL Limited based on information provided by its various members (\"Members\").\n" + 
					"Consequently TransUnion CIBIL Limited does not accept any responsibility on the accuracy, completeness, and veracity of any and all such information as provided. The \n" + 
					"information is current and up to date to such extent as provided by its Members. Any information contained herein does not reflect the views of TransUnion CIBIL Limited or\n" + 
					"its directors or employees. The use of this report is governed by the terms and conditions of the Operating Rules for TransUnion CIBIL Limited and its Members.");
			sb.append("</font></td> ");
			sb.append("</tr>");
			sb.append("</table>");

			sb.append("</body>");
			sb.append("</html>");

			//String cssFile = "<style type='text/css'>@media print { table { border-collapse: separate; border: solid black 1px; border-radius: 7px; -moz-border-radius: 10px; } td, th { border-left: solid black 1px; border-top: solid black 1px; } th { background-color: blue; border-top: none; } td:first-child, th:first-child { border-left: none; }</style>";

			String finalPathPdf=getPath(filePath);
			Document document = new Document(PageSize.A3, 36, 36, 150, 75);
			File file=new File(finalPathPdf +File.separator+"Cibil_"+ apiResponse.getHeader().getCorrelationId() +".pdf");
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file));
			
		/*	List<String> headers = header(logoPath,headerInformation,otherInfo,reportType);
			String HEADER1 = headers.get(0);
			String HEADER2 = headers.get(1);
			String HEADER3 = headers.get(2);
			String HEADER4 = headers.get(3);
			String HEADER5 = headers.get(4);
			String HEADER6 = headers.get(5);*/
			
			pdfWriter.setPageEvent(new PDFCibilConverter().new HeaderFooter(HEADER1,HEADER2,HEADER3,HEADER4,HEADER5,HEADER6));
			
			ByteArrayOutputStream bStream = new ByteArrayOutputStream();
			PdfWriter.getInstance(document, bStream);
			document.open();

			HTMLWorker worker = new HTMLWorker(document); 
			worker.parse(new StringReader(sb.toString())); 
			document.close();
			
			FileInputStream inputStream = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1)
			{
				baos.write(buffer, 0, bytesRead);
			}
			String base64 = Base64.encodeBytes(baos.toByteArray()/*bStream.toByteArray()*/);
			dto.setByteArray(base64);
			dto.setFilePath(finalPathPdf +File.separator+"Cibil_"+ apiResponse.getHeader().getCorrelationId() +".pdf");
			inputStream.close();
			logger.info("Bytearray for pdf ::" + base64);
			try
			{
				/*Path path = Paths.get(newPath);
				Files.delete(path);*/
				new File(newPath).delete();
			}
			catch(Exception e)
			{
				logger.error("error while deleting the img file:"+e);
			}
			
			return dto;
		}
		catch (Exception e)
		{
			logger.error("PDFConverter || getPdfByteArrayCibil || error :: "+e);
			return null;
		}
	}


	public static List<String> header(String logoPath,HeaderInformation headerInformation, OtherInformation otherInfo, ReportType reportType) 
	{
		List<String> headers = new ArrayList<String>();
		String cName = "";
		String eName = "";
		String cValue = "";
		String eValue = "";
		String cColor = "";
		String eColor = "";
		String reportName = "";
		String consumerName = "";
		String date = "";
		String enquiryId = "";
		String time = "";
		String memberRefNum = "";
		String controlNum = "";
		String branchId = "";
		String kendraCentre = "";
		try 
		{
			if(headerInformation!=null && !headerInformation.equals(""))
			{
				HeaderInformationData info=headerInformation.getHeaderInformationData();
				//InformationData info = headerInformation.getInformationData();
				if(info!=null && !info.equals(""))
				{
					List<HeaderInformationField> listField = info.getHeaderInformationfield();

					if(listField!=null && !listField.equals("") && listField.size()>0)
					{
						for(HeaderInformationField field:listField) 
						{	
							if(field.getName().equalsIgnoreCase("CONSUMER NAME:"))
							{
								consumerName = field.getValue();
							}

							if(field.getName().equalsIgnoreCase("DATE:"))
							{
								date = field.getValue();
							}

							if(field.getName().equalsIgnoreCase("ENQUIRY ID:"))
							{
								enquiryId = field.getValue();
							}

							if(field.getName().equalsIgnoreCase("TIME:"))
							{
								time = field.getValue();
							}

							if(field.getName().equalsIgnoreCase("MEMBER REFERENCE NO:"))
							{
								memberRefNum = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("CONTROL NUMBER:"))
							{
								controlNum = field.getValue();
							}

							if(field.getName().equalsIgnoreCase("BRANCH ID:"))
							{
								branchId = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("KENDRA / CENTRE:"))
							{
								kendraCentre = field.getValue();
							}
						}
					}
						
				}
			}
			
			if(otherInfo!=null && !otherInfo.equals(""))
			{
				InformationData info = otherInfo.getInformationData();
				if(info!=null && !info.equals(""))
				{
					List<Field> otherInfoField = info.getField();
					if(!otherInfoField.equals("") && otherInfoField!=null && otherInfoField.size()>0)
					{
						cName = otherInfoField.get(0).getName();
						eName = otherInfoField.get(1).getName();


						cValue = otherInfoField.get(0).getValue();
						eValue = otherInfoField.get(1).getValue();

						cColor = otherInfoField.get(0).getColor();
						eColor = otherInfoField.get(1).getColor();
					}
				}
			}
			
			if(reportType!=null && !reportType.equals(""))
			{
				ReportTypeInformationData info = reportType.getInformationData();
				if(info!=null && !info.equals(""))
				{
					List<ReportTypeField> reportName1 = info.getField();
					if(!reportName1.equals("") && reportName1!=null && reportName1.size()>0)
					{
						reportName = reportName1.get(0).getValue();
					}
				}
			}
			String HEADER2 = "<table align=\"center\" border=\"0\" cellpadding=\"0\" width=\"100%\"> 	<tr>  <td class=\"dataHeader\" 	style=\"color: #333; text-align: center; font-size: 14px;\">"+(reportName!=null?reportName:"")+"</td> </tr></table>   ";
			String HEADER3 = "<table align=\"center\" border=\"0\" style=\"background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\"><b>CONSUMER NAME: </b> "+(consumerName!=null?consumerName:"")+"</td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\"><b>Date : </b>"+(date!=null?date:"")+"</td> </tr> </table>";
			String HEADER4 = "<table align=\"center\" border=\"0\" style=\"background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\"><b>ENQUIRY ID:</b> "+(enquiryId!=null?enquiryId:"")+"</td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\"><b>Time : </b>"+(time!=null?time:"")+"</td> </tr> </table>";
			String HEADER5 = "<table align=\"center\" border=\"0\" style=\"background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\"><b>Member Reference Number : </b>"+(memberRefNum!=null?memberRefNum:"")+"</td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\"><b>Control Number : </b>"+(controlNum!=null?controlNum:"")+"</td> </tr> </table>";
			String HEADER6 = "<table align=\"center\" border=\"0\" style=\"background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\"><b>BRANCH ID </b>:"+(branchId!=null?branchId:"")+" </td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\"><b>KENDRA / CENTRE: </b>"+(kendraCentre!=null?kendraCentre:"")+"</td> </tr> </table>";
			String HEADER7 = "<table align=\"center\" style=\"border-bottom:2px solid #1675a5; background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: "+cColor+"; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">"+(eName!=null?eName:"")+(eValue!=null?eValue:"")+"</td><td style=\"color: "+eColor+"; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\"> "+(cName!=null?cName:"")+(cValue!=null?cValue:"") +" </td> </tr> </table> <br> </br>";
			
			headers.add(HEADER2);
			headers.add(HEADER3);
			headers.add(HEADER4);
			headers.add(HEADER5);
			headers.add(HEADER6);
			headers.add(HEADER7);
		
		}
		catch(Exception exception) {

			logger.error("Exception occurs while iterating Header Information");
			logger.error("PDFConverter || Header || Error " + exception);
		}

		return headers;
	}


	public static StringBuilder searchInfo(SearchInformation searchInformation) {

		StringBuilder sb = new StringBuilder();

		try
		{
			sb.append("<table width='100%' cellspacing='0' cellpadding='0' bgcolor='#039BD1'  border-top-left-radius: 60px; >");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'><font size='3' face='arial' color='#ffffff' ><b>SEARCH INFORMATION</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(searchInformation!=null && !searchInformation.equals(""))
			{
				SearchInformationData info = searchInformation.getSearchInformationData();
				if(info!=null && !info.equals(""))
				{
					List<SearchInformationField> listField = info.getSearchInformationField();			
					if(listField!=null && !listField.isEmpty() &&  listField.size()>0)
					{
						String name = "";
						String pan = "";
						String keyPersonName = "";
						String dob = "";
						String gender = "";
						String currentAddress = "";

						for(SearchInformationField field:listField) 
						{

							if(field.getName().equalsIgnoreCase("NAME"))
							{
								name = field.getValue();
							}	
							if(field.getName().equalsIgnoreCase("PAN"))
							{
								pan = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("KEY PERSON NAME"))
							{
								keyPersonName = field.getValue();
							}	

							if(field.getName().equalsIgnoreCase("DOB"))
							{
								dob = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("GENDER"))
							{
								gender = field.getValue();	
							}
							if(field.getName().equalsIgnoreCase("CURRENT ADDRESS"))
							{
								currentAddress = field.getValue();	
							}
						}

						sb.append("<table border='1' bgcolor='#f0f0f0' width='100%' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");
						// Name row
						sb.append("<tr>");
						sb.append("<td  align='left'><font size='0.5' face='arial'><b>NAME:</b> </font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>"+(name!=null?name:"")+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>PAN:</b> </font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'> "+(pan!=null?pan:"")+"</font></td>");
						sb.append("</tr>");

						//KEY PERSON NAME row
						sb.append("<tr>");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>KEY PERSON NAME:</b> </font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(keyPersonName!=null?keyPersonName:"")+"</font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>DOB:</b> </font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(dob!=null?dob:"")+"</font></td>");
						sb.append("</tr>");

						// Gender Row
						sb.append("<tr>");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>GENDER :</b> </font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(gender!=null?gender:"")+"</font></td> ");
						sb.append("<td colspan='2' align='left'><font size='0.5' face='arial'>"+"</font></td> ");
						sb.append("</tr>");

						//Current Address
						sb.append("<tr>");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>CURRENT ADDRESS :</b> </font></td> ");
						sb.append("<td colspan='3' align='left'><font size='0.5' face='arial'>"+(currentAddress!=null?currentAddress:"")+"</font></td> ");
						sb.append("</tr>");

						sb.append("</table>");
					}
					else
					{
						logger.info(" Search Information Data is not found");
					}
				}
				else
				{
					logger.info(" Search Information Data is not found");
				}
			}
			
			else
			{
				logger.info(" Search Information Data is not found");
			}
		}
		catch(Exception exception)
		{
			logger.error("Exception occurs while iterating Search Information");
			logger.error("PDFConverter || SearchInfo || Exception " + exception);
		}
		sb.append("<br></br>");
		return sb;

	}

	public static StringBuilder consumerInfo(ConsumerInformation consumerInformation)
	{
		StringBuilder sb = new StringBuilder();

		try {
			
			sb.append("<table width='100%' cellspacing='0' cellpadding='0' bgcolor='#039BD1'  border-top-left-radius: 60px; >");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'><font size='3' face='arial' color='#ffffff' ><b>CONSUMER INFORMATION</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(consumerInformation!=null && !consumerInformation.equals(""))
			{
				ConsumerInformationData info = consumerInformation.getConsumerInformationData();
				if(info!=null && !info.equals(""))
				{
					List<ConsumerInformationDataField> listField = info.getConsumerInformationDataField();			
					if(listField!=null && !listField.isEmpty()  && listField.size()>0)
					{
						String name = "";
						String pan = "";
						String home = "";
						String dob = "";
						String voterId = "";
						String mobile = "";
						String gender = "";
						String office = "";
						String address1 = "";
						String dateReported1 = "";
						String category1 = "";
						String address2 = "";
						String dateReported2 = "";
						String category2 = "";
						String address3 = "";
						String dateReported3 = "";
						String category3 = "";


						for(ConsumerInformationDataField field:listField) 
						{
							if(field.getName().equalsIgnoreCase("NAME"))
							{
								name = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("PAN"))
							{	
								pan = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("HOME"))
							{
								home = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("DOB"))
							{
								dob = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("VOTER ID"))
							{
								voterId = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("MOBILE"))
							{
								mobile = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("GENDER"))
							{
								gender = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("OFFICE"))
							{
								office = field.getValue();	
							}	
							if(field.getName().equalsIgnoreCase("ADDRESS1"))
							{
								address1 = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("DATE REPORTED1"))
							{
								dateReported1 = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("CATEGORY1"))
							{
								category1 = field.getValue();
							}	
							if(field.getName().equalsIgnoreCase("ADDRESS2"))
							{
								address2 = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("DATE REPORTED2"))
							{
								dateReported2 = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("CATEGORY2"))
							{
								category2 = field.getValue();
							}	
							if(field.getName().equalsIgnoreCase("ADDRESS3"))
							{
								address3 = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("DATE REPORTED3"))
							{
								dateReported3 = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("CATEGORY3"))
							{
								category3 = field.getValue();
							}	
						}

						sb.append("<table border='1' bgcolor='#f0f0f0' width='100%' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");	

						sb.append("<tr  bgcolor='yellow' >");
						sb.append("<td colspan='2'  align='left'><font size='0.5' face='arial'><b>BORROWER DETAILS</b></font></td> ");
						sb.append("<td colspan='2' align='left'><font size='0.5' face='arial'><b>IDENTIFICATION</b> </font></td>");
						sb.append("<td  colspan='2' align='left'><font size='0.5' face='arial'><b>TELEPHONE DETAILS</b></font></td>");
						sb.append("</tr>");


						//Name row
						sb.append("<tr>");

						sb.append("<td align='left'><font size='0.5' face='arial'><b>NAME:</b> "+"</font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(name!=null?name:"")+"</font></td> ");

						sb.append("<td align='left'><font size='0.5' face='arial'><b>PAN:</b> "+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'> "+(pan!=null?pan:"")+"</font></td>");


						sb.append("<td align='left'><font size='0.5' face='arial'><b>HOME:</b> "+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(home!=null?home:"")+"</font></td>");

						sb.append("</tr>");

						// DOB
						sb.append("<tr>");

						sb.append("<td align='left'><font size='0.5' face='arial'><b>DOB:</b> "+"</font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(dob!=null?dob:"")+"</font></td> ");

						sb.append("<td align='left'><font size='0.5' face='arial'><b>VOTER ID:</b> "+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'> "+(voterId!=null?voterId:"")+"</font></td>");

						sb.append("<td align='left'><font size='0.5' face='arial'><b>MOBILE:</b> "+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(mobile!=null?mobile:"")+"</font></td>");

						sb.append("</tr>");

						//Gender
						sb.append("<tr>");

						sb.append("<td align='left'><font size='0.5' face='arial'><b>GENDER:</b> "+"</font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(gender!=null?gender:"")+"</font></td> ");


						sb.append("<td align='left'><font size='0.5' face='arial'></font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'> </font></td>");


						sb.append("<td align='left'><font size='0.5' face='arial'><b>OFFICE:</b> "+"</font></td>");	
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(office!=null?office:"")+"</font></td>");

						sb.append("</tr>");

						// Address - 1
						sb.append("<tr>");

						sb.append("<td align='left'><font size='0.5' face='arial'><b>ADDRESS</b> "+"</font></td> ");
						sb.append("<td colspan='3' align='left'><font size='0.5' face='arial'>"+(address1!=null?address1:"")+"</font></td> ");

						sb.append("<td align='left'><font size='0.5' face='arial'><b>DATE REPORTED</b> "+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(dateReported1!=null?dateReported1:"")+"</font></td>");

						sb.append("</tr>");

						// Category - 1
						sb.append("<tr>");

						sb.append("<td  align='left'><font size='0.5' face='arial'><b>CATEGORY</b> "+"</font></td> ");
						sb.append("<td colspan='3' align='left'><font size='0.5' face='arial'>"+(category1!=null?category1:"")+"</font></td> ");

						sb.append("<td align='left'><font size='0.5' face='arial'>"+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'></font></td>");

						sb.append("</tr>");

						// Address - 2
						sb.append("<tr>");

						sb.append("<td  align='left'><font size='0.5' face='arial'><b>ADDRESS</b> "+"</font></td> ");
						sb.append("<td colspan='3' align='left'><font size='0.5' face='arial'>"+(address2!=null?address2:"")+"</font></td> ");

						sb.append("<td align='left'><font size='0.5' face='arial'><b>DATE REPORTED</b> "+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(dateReported2!=null?dateReported2:"")+"</font></td>");

						sb.append("</tr>");

						// Category - 2
						sb.append("<tr>");
						sb.append("<td  align='left'><font size='0.5' face='arial'><b>CATEGORY</b> "+"</font></td> ");
						sb.append("<td colspan='3' align='left'><font size='0.5' face='arial'>"+(category2!=null?category2:"")+"</font></td> ");

						sb.append("<td align='left'><font size='0.5' face='arial'>"+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'></font></td>");	
						sb.append("</tr>");

						// Address - 3
						sb.append("<tr>");
						sb.append("<td  align='left'><font size='0.5' face='arial'><b>ADDRESS</b> "+"</font></td> ");
						sb.append("<td colspan='3' align='left'><font size='0.5' face='arial'>"+(address3!=null?address3:"")+"</font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>DATE REPORTED</b> "+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(dateReported3!=null?dateReported3:"")+"</font></td>");
						sb.append("</tr>");

						// Category - 3
						sb.append("<tr>");
						sb.append("<td  align='left'><font size='0.5' face='arial'><b>CATEGORY</b> "+"</font></td> ");
						sb.append("<td colspan='5' align='left'><font size='0.5' face='arial'>"+(category3!=null?category3:"")+"</font></td> ");
						sb.append("</tr>");

						sb.append("</table>");

					}
					else
					{
						logger.info(" Consumer Information Data not available");
					}
				}
				else
				{
					logger.info(" Consumer Information Data not available");
				}
			}
			
		
			else
			{
				logger.info(" Consumer Information Data not available");
			}
		
		}
		catch(Exception exception)
		{
			logger.error("Exception occurs while iterating Search Information");
			logger.error("PDFConverter || SearchInfo || Error " + exception);
		}
		sb.append("<br></br>");
		return sb;

	}

	public static StringBuilder employmentInformation(MfiEmploymentInformation mfiEmpInfo)
	{
		StringBuilder sb = new StringBuilder();
		String asOn = mfiEmpInfo.getAsOn();

		try
		{
			sb.append("<table width='100%' cellspacing='0' cellpadding='0' bgcolor='#039BD1'  border-top-left-radius: 60px; >");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'><font size='3' face='arial' color='#ffffff' ><b>EMPLOYMENT INFORMATION AS ON "+(asOn!=null?asOn:"")+"</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(mfiEmpInfo!=null && !mfiEmpInfo.equals(""))
			{
				InformationData info = mfiEmpInfo.getInformationData();
				if(info!=null && !info.equals(""))
				{
					List<Field> listField = info.getField();
					if(listField!=null && !listField.isEmpty() && listField.size()>0)
					{
						String occupation = "";
						String familyIncome = "";
						String familyExpenses = "";
						String bankAccount = "";
						String povertyIndex = "";
						String assetOwnership = "";

						for(Field field : listField)
						{
							if(field.getName().equalsIgnoreCase("Occupation"))
							{
								occupation = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("Monthly Family Income"))
							{
								familyIncome = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("Monthly Family Expenses"))
							{
								familyExpenses = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("Bank Account"))
							{
								bankAccount = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("Poverty Index"))
							{
								povertyIndex = field.getValue();
							}
							if(field.getName().equalsIgnoreCase("Asset Ownership"))
							{
								assetOwnership = field.getValue();
							}
						}

						//EMPLOYMENT INFORMATION AS On

						// Occupation row
						sb.append("<table border='1' bgcolor='#f0f0f0' width='100%' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");	
						sb.append("<tr  bgcolor='yellow' >");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Occupation</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Monthly Family Income</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Monthly Family Expenses</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Bank Account</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Poverty Index</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Asset Ownership</b></font></td> ");
						sb.append("</tr>");
						// Values below Occupation row
						sb.append("<tr>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(occupation!=null?occupation:"")+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(familyIncome!=null?familyIncome:"")+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(familyExpenses!=null?familyExpenses:"")+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(bankAccount!=null?bankAccount:"")+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(povertyIndex!=null?povertyIndex:"")+"</font></td>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(assetOwnership!=null?assetOwnership:"")+"</font></td>");
						sb.append("</tr>");

						sb.append("</table>");
					
					}
					else
					{
						logger.info(" Employment Information Data not available");
					}
				}
				else
				{
					logger.info(" Employment Information Data not available");
				}
			}
		
			else
			{
				logger.info(" Employment Information Data not available");
			}
		}
		catch(Exception exception)
		{
			logger.error("Exception occurs while iterating Employment Information");
			logger.error("PDFConverter || employmentInformation || Error " + exception);
		}
		sb.append("<br></br>");
		return sb;

	}

	public static StringBuilder creditSummary(CreditReportSummary creditReportSummary) {

		StringBuilder sb = new StringBuilder();
		try {
			sb.append("<table width='100%' cellspacing='0' cellpadding='0' bgcolor='#039BD1'  border-top-left-radius: 60px; >");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'><font size='3' face='arial' color='#ffffff' ><b>CREDIT REPORT SUMMARY</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(creditReportSummary!=null && !creditReportSummary.equals(""))
			{
				List<CreditReportSummaryInfoData> listField = creditReportSummary.getCrsInfoData();
				if(listField!=null && !listField.isEmpty() && listField.size()>0)
				{
					for (int index = 1; index < listField.size(); index++) {

						List<CrReportSummInfoDataField> fields = listField.get(index).getCrReportSummInfoDataField();

						String mfiInstitutionName = "";
						String recentDateReported = "";
						String oldestDateReported = "";
						String totalAccount = "";
						String liveAccount = "";
						String closedAccount = "";
						String overdueAccount = "";
						String writtenOffAccount = "";
						String totalSanctionedAmount = "";
						String totalCurrentBalance = "";
						String totalOverdueAmount = "";
						String totalInstallmentAmount = "";
						String totalWrittenOffAmount = "";

						for (CrReportSummInfoDataField field : fields)
						{
							if (field.getName().equalsIgnoreCase("name of mfi institution")) {
								mfiInstitutionName = field.getValue();
							}
							if (field.getName().equalsIgnoreCase("recent date reported")) {
								recentDateReported = field.getValue();
							}
							if (field.getName().equalsIgnoreCase("oldest date reported")) {
								oldestDateReported = field.getValue();
							}
							if (field.getName().equalsIgnoreCase("total account")) {
								totalAccount = field.getValue();
							}
							if (field.getName().equalsIgnoreCase("live account")) {
								liveAccount = field.getValue();
							}
							if (field.getName().equalsIgnoreCase("closed account")) {
								closedAccount = field.getValue();
							}
							if (field.getName().equalsIgnoreCase("overdue account")) {
								overdueAccount = field.getValue();
							}
							if (field.getName().equalsIgnoreCase("written off account")) {
								writtenOffAccount = field.getValue();
							}
							if (field.getName().equalsIgnoreCase("total sanctioned amount")) {
								totalSanctionedAmount = field.getValue();
							}
							if (field.getName().equalsIgnoreCase("total current balance")) {
								totalCurrentBalance = field.getValue();
							}
							if (field.getName().equalsIgnoreCase("total overdue amount")) {
								totalOverdueAmount = field.getValue();
							}
							if (field.getName().equalsIgnoreCase("total installment amount")) {
								totalInstallmentAmount = field.getValue();
							}
							if (field.getName().equalsIgnoreCase("total written off amount")) {
								totalWrittenOffAmount = field.getValue();
							}

						}

						sb.append("<table border='1' bgcolor='#f0f0f0' width='100%' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");
						// Name of MFI Institution Row
						sb.append("<tr  bgcolor='yellow' >");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Name of MFI Institution</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Recent Date Reported</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Oldest Date Reported</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Total Account</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Live Account</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Closed Account</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Overdue Account</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Written off Account</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Total Sanctioned Amount</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Total current balance</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Total overdue amount</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Total installment amount</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Total Written Off Amount</b></font></td> ");
						sb.append("</tr>");
						
						sb.append("<tr>");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (mfiInstitutionName != null ? mfiInstitutionName : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (recentDateReported != null ? recentDateReported : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (oldestDateReported != null ? oldestDateReported : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (totalAccount != null ? totalAccount : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (liveAccount != null ? liveAccount : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (closedAccount != null ? closedAccount : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (overdueAccount != null ? overdueAccount : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (writtenOffAccount != null ? writtenOffAccount : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (totalSanctionedAmount != null ? totalSanctionedAmount : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (totalCurrentBalance != null ? totalCurrentBalance : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (totalOverdueAmount != null ? totalOverdueAmount : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (totalInstallmentAmount != null ? totalInstallmentAmount : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (totalAccount != null ? totalAccount : "") + "</font></td> ");
						sb.append("<td  align='left'><font size='0.5' face='arial'>" + (totalWrittenOffAmount != null ? totalWrittenOffAmount : "") + "</font></td> ");
						sb.append("</tr>");
					}

					sb.append("</table>");

					sb.append("<table>");
					sb.append("<tr>");
					sb.append("<td align='left'><font size='0.5' face='arial'><font color='ff3339'></font> *Amounts, Balances and Written-off status are only for live accounts </font></td> ");
					sb.append("</tr>");
					sb.append("</table>");
				}
				else
				{
					logger.info(" Credit Report Summary Information Data not available <br></br>");
				}
			}
				
			else
			{
				logger.info(" Credit Report Summary Information Data not available ");
			}
		}
		catch(Exception exception)
		{
			logger.error("Exception occurs while iterating Credit Report Summary");
			logger.error("PDFConverter || CreditReportSummary || Exception " + exception);
		}

		sb.append("<br></br>");
		return sb;

	}

	public static StringBuilder alerts(Alerts alerts) 
	{
		StringBuilder sb = new StringBuilder();

		try
		{
			sb.append("<table width='100%' cellspacing='0' cellpadding='0' bgcolor='#039BD1'  border-top-left-radius: 60px; >");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'><font size='3' face='arial' color='#ffffff' ><b>ALERTS</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(alerts!=null && !alerts.equals(""))
			{
				AlertsInformationData info = alerts.getAlertsinformationData();
				if(info!=null && !info.equals(""))
				{
					List<AlertsField> listField = info.getField();
					if(listField!=null && !listField.isEmpty() && listField.size()>0)
					{
						String alerts1 = "";

						sb.append("<table width='100%' cellspacing='0' cellpadding='2' ");

						for(AlertsField field : listField)
						{
							alerts1 = field.getValue();

							sb.append("<tr>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+" •"+(alerts1 != null ? alerts1 : "")+"</font></td> ");
							sb.append("</tr>");
						}

						sb.append("</table>");
					}
					else
					{
						logger.info("Alerts Information Data not available");
					}
				}
				else
				{
					logger.info("Alerts Information Data not available");
				}
			}
		
			else
			{
				logger.info("Alerts Information Data not available");
			}
		}
		catch(Exception exception)
		{
			logger.error("Exception occurs while iterating alerts");
			logger.error("PDFConverter || alerts || Exception " + exception);
		}

		sb.append("<br></br>");

		return sb;
	}


	public static StringBuilder score(Score score, PlScore plScore)
	{
		StringBuilder sb = new StringBuilder();

		try 
		{
			String flag = "false";
			String scoreName = "";
			String scoreCount = "";
			String scoreFactor = "";
			
			String plScoreName = "";
			String plScoreCount = "";
			String plScoreFactor = "";

			sb.append("<table width='100%' cellspacing='0' cellpadding='0' bgcolor='#039BD1'  border-top-left-radius: 60px; >");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'><font size='3' face='arial' color='#ffffff' ><b>SCORE</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");
			
			if(score!=null && !score.equals(""))
			{
				InformationData info = score.getInformationData();
				if(info!=null && !info.equals(""))
				{
					List<Field> fields = info.getField();
 
					if(fields!=null && !fields.isEmpty() && fields.size()>0)
					{
						flag = "true";
						for (Field field : fields)
						{
							if (field.getName().equalsIgnoreCase("Score Name")) {
								scoreName = field.getValue();
							}

							if (field.getName().equalsIgnoreCase("Score")) {
								scoreCount = field.getValue();
							}

							if (field.getName().equalsIgnoreCase("Scoring Factors")) {
								scoreFactor = field.getValue();
							}
						}

						sb.append("<table border='1' bgcolor='#f0f0f0' width='100%'  style='display:inline;' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");
						sb.append("<tr  bgcolor='yellow' >");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Score Name</b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Score </b></font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'><b>Scoring Factors</b></font></td> ");
						sb.append("</tr>");

						sb.append("<tr>");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(scoreName != null ? scoreName : "")+"</font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(scoreCount != null ? scoreCount : "")+"</font></td> ");
						sb.append("<td align='left'><font size='0.5' face='arial'>"+(scoreFactor != null ? scoreFactor : "")+"</font></td> ");
						sb.append("</tr>");
						sb.append("</table>");
					}
					
				}
			}
			if(plScore!=null && !plScore.equals(""))
			{
				InformationData plinfo = plScore.getInformationData();
				if(plinfo!=null && !plinfo.equals(""))
				{
					List<Field> fields = plinfo.getField();

					if(fields!=null && !fields.isEmpty() && fields.size()>0)
					{
						flag = "true";
					for (Field field : fields)
					{
						if (field.getName().equalsIgnoreCase("Score Name")) {
							plScoreName = field.getValue();
						}

						if (field.getName().equalsIgnoreCase("Score")) {
							plScoreCount = field.getValue();
						}

						if (field.getName().equalsIgnoreCase("Scoring Factors")) {
							plScoreFactor = field.getValue();
						}
					}
					
					sb.append("<table border='1' bgcolor='#f0f0f0' width='100%'  style='display:inline;' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");
					sb.append("<tr  bgcolor='yellow' >");
					sb.append("<td align='left'><font size='0.5' face='arial'><b>Score Name</b></font></td> ");
					sb.append("<td align='left'><font size='0.5' face='arial'><b>Score </b></font></td> ");
					sb.append("<td align='left'><font size='0.5' face='arial'><b>Scoring Factors</b></font></td> ");
					sb.append("</tr>");

					sb.append("<tr>");
					sb.append("<td align='left'><font size='0.5' face='arial'>"+(plScoreName != null ? plScoreName : "")+"</font></td> ");
					sb.append("<td align='left'><font size='0.5' face='arial'>"+(plScoreCount != null ? plScoreCount : "")+"</font></td> ");
					sb.append("<td align='left'><font size='0.5' face='arial'>"+(plScoreFactor != null ? plScoreFactor : "")+"</font></td> ");
					sb.append("</tr>");
					sb.append("</table>");
					}
				}
			}
			
			if(flag=="false")
			{
				logger.info("Score Data not available");
			}
			
		}
		catch(Exception exception)
		{
			logger.error("Exception occurs while iterating Score");
			logger.error("PDFConverter || Score || Exception " + exception);
		}

		sb.append("<br></br>");
		return sb;
	}

	public static StringBuilder memberName(List<Mfi> mfi) 
	{
		StringBuilder sb = new StringBuilder();

		try 
		{
			if(mfi!=null && !mfi.isEmpty() && mfi.size()>0)
			{
				for(Mfi mfiArray : mfi)
				{
					String bankName = mfiArray.getName();


					// Amount Fields
					String sanctionedAmount = "";
					String repaymentTenure = "";
					String installment = "";
					String currentBalance = "";
					String pmtFrequency = "";
					String overdue = "";
					String interestRate = "";

					String disbursedAmount = "";
					String writtenOffAmount = "";
					String AmountCurrentBalance = "";
					String lastPaymentAmount = "";
					String installmentAmount = "";

					// Account Information Fields
					String accountState = "";

					String memberName = "";
					String type = "";
					String ownership = "";

					String accountType = "";
					String creditGrantor = "";
					String noOfIinstallments = "";
					String accountNumber = "";
					String loanPurpose = "";
					String repaymentFrequency = "";
					String branchId = "";
					String kendraId = "";

					// Borrower Fields
					String name = "";
					String dob = "";
					String ageAsOn = "";
					String gender = "";
					String maritalStatus = "";
					String pan = "";
					//String uid = "";
					String voterId = "";
					String noOfDependents = "";
					String father = "";
					String mother = "";
					String address1 = "";
					String reportedDate1 = "";
					String address2 = "";
					String reportedDate2 = "";

					// Dates Field
					String openDisbursedDate = "";
					String writtenOffDate = "";
					String closedDate = "";
					String sanctionedDate = "";
					String lastPaymentDate = "";
					String reportedDates = ""; // Date's'
					String reportedAndCertified = "";
					String pmtHistoryStart = "";
					String pmtHistoryEnd = "";

					// Status Field
					String accountStatus = "";
					String reason = "";
					String writtenOffSettled = "";
					String writtenOffTotal = "";
					String writtenOffPrincipal = "";
					String suitFiled_WilfulDefault = "";

					List<Account> account  = null;

					if((mfiArray.getAccount()!=null) && !mfiArray.getAccount().isEmpty())
					{
						account= mfiArray.getAccount();
					}

					if((mfiArray.getIcrsaccount()!=null) && !mfiArray.getIcrsaccount().isEmpty())
					{
						account= mfiArray.getIcrsaccount();
					}

					if(account!=null && !account.isEmpty()) 
					{

						Amount amountObj = null;
						AccountInformation accountInfo = null;
						Dpd dpd = null;
						Dates dates = null;
						Status status = null;
						Borrower borrower = null;

						for(Account accountArray : account)
						{
							amountObj = accountArray.getAmount();
							if((amountObj!=null)) 
							{
								for(Field field : amountObj.getField()) {

									if (field.getName().equalsIgnoreCase("sanctioned amount")) {
										sanctionedAmount = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("repayment tenure")) {
										repaymentTenure = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("installment")) {
										installment = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("current balance")) {
										currentBalance = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("pmt frequency")) {
										pmtFrequency = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("overdue")) {
										overdue = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("interest rate")) {
										interestRate = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("disbursed amount")) {
										disbursedAmount = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("written-off amount")) {
										writtenOffAmount = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("current balance")) {
										AmountCurrentBalance = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("last payment amount")) {
										lastPaymentAmount = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("installment amount")) {
										installmentAmount = field.getValue();
									}

								}
							}

							accountInfo = accountArray.getAccountInformation();
							if((accountInfo!=null)) 
							{
								for(Field field : accountInfo.getField()) 
								{
									if (field.getName().equalsIgnoreCase("member name")) {
										memberName = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("type")) {
										type = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("ownership")) {
										ownership = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("account type")) {
										accountType = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("credit grantor")) {
										creditGrantor = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("account no.")) {
										accountNumber = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("no. of installments")) {
										noOfIinstallments = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("loan purpose")) {
										loanPurpose = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("repayment frequency")) {
										repaymentFrequency = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("branch id")) {
										repaymentFrequency = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("kendra id")) {
										repaymentFrequency = field.getValue();
									}
								}

							}

							borrower = accountArray.getBorrower();
							if((borrower!=null)) 
							{
								for(Field field : borrower.getField()) {

									if (field.getName().equalsIgnoreCase("name")) {
										name = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("dob")) {
										dob = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("age (as on)")) {
										ageAsOn = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("gender")) {
										gender = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("marital status")) {
										maritalStatus = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("pan")) {
										pan = field.getValue();
									}
									/*if (field.getName().equalsIgnoreCase("uid")) {
										uid = field.getValue();
									}*/
									if (field.getName().equalsIgnoreCase("voter id")) {
										voterId = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("no. of dependents")) {
										noOfDependents = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("father")) {
										father = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("mother")) {
										mother = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("address")) {
										address1 = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("reported date")) {
										reportedDate1 = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("address")) {
										address2 = field.getValue();
									}

								}

							}

							// Pending Logic for Days pass Due
							dpd = accountArray.getDpd();

							dates = accountArray.getDates();
							if((dates!=null)) 
							{
								for(Field field : dates.getField()) {

									if (field.getName().equalsIgnoreCase("open / disbursed date")) {
										openDisbursedDate = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("written-off date")) {
										writtenOffDate = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("closed date")) {
										closedDate = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("sanctioned date")) {
										sanctionedDate = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("last payment date")) {
										lastPaymentDate = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("reported date")) {
										reportedDates = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("reported and certified")) {
										reportedAndCertified = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("pmt history start")) {
										pmtHistoryStart = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("pmt history end")) {
										pmtHistoryEnd = field.getValue();
									}
								}

							}

							status = accountArray.getStatus();
							if((status!=null)) 
							{
								for(Field field : status.getField()) 
								{
									accountState = status.getName();
									if (field.getName().equalsIgnoreCase("account status")) {
										accountStatus = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("reason")) {
										reason = field.getValue();
									}

									if (field.getName().equalsIgnoreCase("WRITTEN-OFF / SETTLED")) {
										writtenOffSettled = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("WRITTEN-OFF (TOTAL)")) {
										writtenOffTotal = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("WRITTEN-OFF (PRINCIPAL)")) {
										writtenOffPrincipal = field.getValue();
									}
									if (field.getName().equalsIgnoreCase("SUIT FILED / WILFUL DEFAULT")) {
										suitFiled_WilfulDefault = field.getValue();
									}

								}

							}

							// new Html Code for seprate table

							sb.append("<table width='100%' cellspacing='0' cellpadding='0' bgcolor='#039BD1'  border-top-left-radius: 60px; >");
							sb.append("<tr>");
							sb.append("<td align='center' height='10'>");
							sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td align='left' width='50%'><font size='3' face='arial' color='#ffffff' ><b>MEMBER NAME : "+bankName+"</b></font>");
							sb.append("</td>");
							sb.append("</tr>");
							sb.append("</table>");

							//BORROWER DETAILS Row
							sb.append("<table border='1' bgcolor='#f0f0f0' width='100%' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");	
							sb.append("<tr  bgcolor='yellow' >");
							sb.append("<td colspan='2'  align='left'><font size='0.5' face='arial'><b>BORROWER DETAILS</b></font></td> ");
							sb.append("<td colspan='2' align='left'><font size='0.5' face='arial'><b>IDENTIFICATION</b> </font></td>");
							sb.append("<td  colspan='2' align='left'><font size='0.5' face='arial'><b>FAMILY DETAILS</b></font></td>");
							sb.append("</tr>");

							// Name Row
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>NAME:</b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(name!=null?name:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>PAN:</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(pan!=null?pan:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>NO. OF DEPENDENTS:</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(noOfDependents!=null?noOfDependents:"")+"</font></td>");
							sb.append("</tr>");
							// DOB Row
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>DOB:</b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(dob!=null?dob:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>VOTER ID:</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(voterId!=null?voterId:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>FATHER:</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(father!=null?father:"")+"</font></td>");
							sb.append("</tr>");
							// AGE(As On) Row
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>AGE(AS ON):</b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(ageAsOn!=null?ageAsOn:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'></font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> </font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>MOTHER:</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(mother!=null?mother:"")+"</font></td>");
							sb.append("</tr>");
							// Gender Row		
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>GENDER:</b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(gender!=null?gender:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'></font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> </font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+/*<b>BROTHER:</b>*/ "</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+/*(brother!=null?brother:"")+"*/"</font></td>");
							sb.append("</tr>");
							// Marital Status Row
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>Marital STATUS</b> "+"</font></td> ");
							sb.append("<td colspan='3' align='left'><font size='0.5' face='arial'>"+(maritalStatus!=null?maritalStatus:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'></font></td>");
							sb.append("</tr>");
							// Address - 1 Row
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>ADDRESS</b> "+"</font></td> ");
							sb.append("<td colspan='3' align='left'><font size='0.5' face='arial'>"+(address1!=null?address1:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>DATE REPORTED</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(reportedDate1!=null?reportedDate1:"")+"</font></td>");
							sb.append("</tr>");
							// Address - 2 Row		
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>ADDRESS</b> "+"</font></td> ");
							sb.append("<td colspan='3' align='left'><font size='0.5' face='arial'>"+(address2!=null?address2:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>DATE REPORTED</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(reportedDate2!=null?reportedDate2:"")+"</font></td>");
							sb.append("</tr>");
							sb.append("</table>");

							sb.append("<br></br>");

							// Second Html Code

							sb.append("<table width='35%' cellspacing='0' cellpadding='0' bgcolor='orange'  border-top-left-radius: 60px; >");
							sb.append("<tr>");
							sb.append("<td align='center' height='10'>");
							sb.append("</td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td align='left' width='50%'><font size='3' face='arial' color='#ffffff' ><b>ACCOUNT STATUS:"+accountState+"</b></font>");
							sb.append("</td>");
							sb.append("</tr>");
							sb.append("</table>");

							// Account Information
							sb.append("<table border='1' bgcolor='#f0f0f0' width='100%' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");	
							sb.append("<tr  bgcolor='yellow' >");
							sb.append("<td colspan='6'  align='left'><font size='0.5' face='arial'><b>ACCOUNT INFORMATION</b></font></td> ");
							sb.append("</tr>");

							sb.append("<tr>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>MEMBER NAME </b> "+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(memberName!=null?memberName:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>TYPE </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(type!=null?type:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>OWNERSHIP </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(ownership!=null?ownership:"")+"</font></td>");
							sb.append("</tr>");

							// Account Type Row
							sb.append("<tr>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>ACCOUNT TYPE </b> "+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(accountType!=null?accountType:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>CREDIT GRANTOR </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(creditGrantor!=null?creditGrantor:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>NO. OF INSTALLMENTS </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(noOfIinstallments!=null?noOfIinstallments:"")+"</font></td>");
							sb.append("</tr>");

							// Account No Row
							sb.append("<tr>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>ACCOUNT NO</b> "+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(accountNumber!=null?accountNumber:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>LOAN PURPOSE </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(loanPurpose!=null?loanPurpose:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>REPAYMENT FREQUENCY </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(repaymentFrequency!=null?repaymentFrequency:"")+"</font></td>");
							sb.append("</tr>");
							// Branch Id Row
							sb.append("<tr>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>BRANCH ID  </b> "+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(branchId!=null?branchId:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>KENDRA ID </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(kendraId!=null?kendraId:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'></font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'></font></td>");
							sb.append("</tr>");
							sb.append("</table>");

							sb.append("<br></br>");

							sb.append("<table border='1' bgcolor='#f0f0f0' width='100%' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");	
							sb.append("<tr  bgcolor='yellow' >");
							sb.append("<td colspan='6'  align='left'><font size='0.5' face='arial'><b>DATES</b></font></td> ");
							sb.append("</tr>");

							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>REPORTED AND CERTIFIED</b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(reportedAndCertified!=null?reportedAndCertified:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>PMT HISTORY START</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(pmtHistoryStart!=null?pmtHistoryStart:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>PMT HISTORY END</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(pmtHistoryEnd!=null?pmtHistoryEnd:"")+"</font></td>");
							sb.append("</tr>");
							// DISBURSED Date Row
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>OPEN / DISBURSED DATE  </b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(openDisbursedDate!=null?openDisbursedDate:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>WRITTEN-OFF DATE </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(writtenOffDate!=null?writtenOffDate:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>CLOSED DATE</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(closedDate!=null?closedDate:"")+"</font></td>");
							sb.append("</tr>");
							// SANCTIONED DATE Row
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>SANCTIONED DATE</b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(sanctionedDate!=null?sanctionedDate:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>LAST PAYMENT DATE</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(lastPaymentDate!=null?lastPaymentDate:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>REPORTED DATE </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(reportedDates!=null?reportedDates:"")+"</font></td>");
							sb.append("</tr>");
							sb.append("</table>");

							sb.append("<br></br>");

							//Amount
							sb.append("<table border='1' bgcolor='#f0f0f0' width='100%' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");	
							sb.append("<tr  bgcolor='yellow' >");
							sb.append("<td colspan='6'  align='left'><font size='0.5' face='arial'><b>AMOUNT</b></font></td> ");
							sb.append("</tr>");


							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>SANCTIONED AMOUNT  </b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(sanctionedAmount!=null?sanctionedAmount:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>REPAYMENT TENURE </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(repaymentTenure!=null?repaymentTenure:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>INSTALLMENT</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(installment!=null?installment:"")+"</font></td>");
							sb.append("</tr>"); 

							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>CURRENT BALANCE  </b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(currentBalance!=null?currentBalance:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>PMT FREQUENCY</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(pmtFrequency!=null?pmtFrequency:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>OVERDUE</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(overdue!=null?overdue:"")+"</font></td>");
							sb.append("</tr>");
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>INTEREST RATE</b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(interestRate!=null?interestRate:"")+"</font></td> ");
							sb.append("</tr>");

							// DISBURSED Amount Row
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>DISBURSED AMOUNT  </b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(disbursedAmount!=null?disbursedAmount:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>WRITTEN-OFF AMOUNT </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(writtenOffAmount!=null?writtenOffAmount:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>CURRENT BALANCE </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(currentBalance!=null?currentBalance:"")+"</font></td>");
							sb.append("</tr>"); 
							// SANCTIONED AMOUNT Row
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>SANCTIONED AMOUNT</b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(sanctionedAmount!=null?sanctionedAmount:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>LAST PAYMENT AMOUNT</b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> "+(lastPaymentAmount!=null?lastPaymentAmount:"")+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>OVERDUE </b> "+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(overdue!=null?overdue:"")+"</font></td>");
							sb.append("</tr>");
							// INSTALLMENT AMOUNT Row
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>INSTALLMENT AMOUNT </b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(installmentAmount!=null?installmentAmount:"")+"</font></td> ");
							sb.append("<td align='left'><font size='0.5' face='arial'></font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'> </font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'></font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'></font></td>");
							sb.append("</tr>");
							sb.append("</table>");

							sb.append("<br></br>");

							// Account Status 
							sb.append("<table border='1' bgcolor='#f0f0f0' width='40%' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");	
							sb.append("<tr  bgcolor='yellow' >");
							sb.append("<td colspan='2'  align='left'><font size='0.5' face='arial'><b>ACCOUNT STATUS</b></font></td> ");
							sb.append("</tr>");

							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>ACCOUNT STATUS </b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(accountStatus!=null?accountStatus:"")+"</font></td> ");
							sb.append("</tr>");

							sb.append("<tr>");
							sb.append("<td align='left'><font size='0.5' face='arial'><b>REASON</b>"+"</font></td>");
							sb.append("<td align='left'><font size='0.5' face='arial'>"+(reason!=null?reason:"")+" </font></td>");
							sb.append("</tr>");

							// new Field
							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>WRITTEN-OFF / SETTLED</b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(writtenOffSettled!=null?writtenOffSettled:"")+"</font></td> ");
							sb.append("</tr>");

							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>WRITTEN-OFF (TOTAL)</b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(writtenOffTotal!=null?writtenOffTotal:"")+"</font></td> ");
							sb.append("</tr>");

							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>WRITTEN-OFF (PRINCIPAL)</b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(writtenOffPrincipal!=null?writtenOffPrincipal:"")+"</font></td> ");
							sb.append("</tr>");

							sb.append("<tr>");
							sb.append("<td  align='left'><font size='0.5' face='arial'><b>SUIT FILED / WILFUL DEFAULT</b> "+"</font></td> ");
							sb.append("<td  align='left'><font size='0.5' face='arial'>"+(suitFiled_WilfulDefault !=null?suitFiled_WilfulDefault:"")+"</font></td> ");
							sb.append("</tr>");

							sb.append("</table>");

							sb.append("<br></br>");

							// Days Pass Due


							if((dpd!=null)) 
							{

								sb.append("<table border='1'  width='100%' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");	
								sb.append("<tr bgcolor='yellow' >");
								sb.append("<td colspan='13'  align='left'><font size='0.5' face='arial'><b>DAYS PAST DUE</b></font></td> ");
								sb.append("</tr>");	
								sb.append("<tr>");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>YEAR/MONTH</b> "+"</font></td> ");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>DEC </b> "+"</font></td> ");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>NOV </b> "+"</font></td> ");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>OCT </b> "+"</font></td> ");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>SEP </b> "+"</font></td> ");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>AUG</b> "+"</font></td> ");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>JUL </b> "+"</font></td> ");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>JUN </b> "+"</font></td> ");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>MAY </b> "+"</font></td> ");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>APR </b> "+"</font></td> ");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>MAR </b> "+"</font></td> ");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>FEB </b> "+"</font></td> ");
								sb.append("<td  align='left'><font size='0.5' face='arial'><b>JAN </b> "+"</font></td> ");
								sb.append("</tr>");

								sb.append("<tr>");
								//					for(Field field : dpd.getField()) 

								int size = dpd.getField().size();



								for(int index=0 ; index<size; index++ )
								{
									String value = dpd.getField().get(index).getValue();
									String color = dpd.getField().get(index).getBackgroundColor();

									if(index==13)
									{
										sb.append("</tr>");
										sb.append("<tr>");
									}
									sb.append("<td  align='left' bgcolor="+(color!=null?color:"") +"><font size='0.5' face='arial'><b>"+(value!=null?value:"")+"</b> "+"</font></td> ");


								}		
								sb.append("</tr>");
								sb.append("</table>");
							}



						} // Accounts array for loop ends


					} // Account if ends

				

				} //MFI for Loop ends


			}

			else
			{
				logger.info(" Memeber Name Data not available");
			}

		}
		catch(Exception exception)
		{
			logger.error("Exception occurs while iterating Memeber Name Information");
			logger.error("PDFConverter || MemeberName || Exception " + exception);
		}
		sb.append("<br></br>");
		return sb;
	}

	

	public static StringBuilder recentEnquiries(RecentEnquiry recentEnquiry) {

		StringBuilder sb = new StringBuilder();

		try
		{
			sb.append("<table width='100%' cellspacing='0' cellpadding='0' bgcolor='#039BD1'  border-top-left-radius: 60px; >");
			sb.append("<tr>");
			sb.append("<td align='center' height='10'>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("<tr>");
			sb.append("<td align='left' width='50%'><font size='3' face='arial' color='#ffffff' ><b>RECENT ENQUIRIES</b></font>");
			sb.append("</td>");
			sb.append("</tr>");
			sb.append("</table>");

		if(recentEnquiry!=null && !recentEnquiry.equals(""))
		{
		//	RecentEnquiryInfoData info = recentEnquiry.getInformationData();
			if(recentEnquiry.getInformationData()!=null )
			{
				List<RecentEnquiryField> listField = recentEnquiry.getInformationData();//info.getRecentEnquiryfield();
			if(listField!=null && !listField.equals("") && listField.size()>0)
			{
				for(int index=0; index<listField.size(); index++)
				{

					/*List<Field> fields = listField.get(index).getField();
					if(fields!=null && !fields.equals("") && fields.size()>0)
					{*/
					String creditGrantor = "";
					String dateOfInquiry = "";
					String purpose = "";
					String amount = "";

					for(RecentEnquiryField field:listField) 
					{
						if(field.getName().equalsIgnoreCase("CREDIT GRANTOR"))
						{
							creditGrantor = field.getValue();
						}
						if(field.getName().equalsIgnoreCase("DATE OF INQUIRY"))
						{
							dateOfInquiry = field.getValue();
						}
						if(field.getName().equalsIgnoreCase("PURPOSE"))
						{
							purpose = field.getValue();
						}
						if(field.getName().equalsIgnoreCase("AMOUNT"))
						{
							amount = field.getValue();
						}	
					}
					// CREDIT GRANTOR Row
					sb.append("<table border='1' bgcolor='#f0f0f0' width='100%' cellspacing='0' cellpadding='2'  bordercolor='#ccc'>");	
					sb.append("<tr  bgcolor='yellow' >");
					sb.append("<td  align='left'><font size='0.5' face='arial'><b>CREDIT GRANTOR</b></font></td> ");
					sb.append("<td  align='left'><font size='0.5' face='arial'><b>DATE OF INQUIRY</b> </font></td>");
					sb.append("<td  align='left'><font size='0.5' face='arial'><b>PURPOSE</b></font></td>");
					sb.append("<td  align='left'><font size='0.5' face='arial'><b>AMOUNT</b></font></td>");
					sb.append("</tr>");

					sb.append("<tr>");
					sb.append("<td  align='left'><font size='0.5' face='arial'>"+(creditGrantor!=null?creditGrantor:"")+"</font></td> ");
					sb.append("<td  align='left'><font size='0.5' face='arial'>"+(dateOfInquiry!=null?dateOfInquiry:"")+"</font></td> ");
					sb.append("<td  align='left'><font size='0.5' face='arial'>"+(purpose!=null?purpose:"")+"</font></td> ");
					sb.append("<td  align='left'><font size='0.5' face='arial'>"+(amount!=null?amount:"")+"</font></td> ");
					sb.append("</tr>");
					
					sb.append("</table>");
				}	
				}
			}
		}
			else
			{
				logger.info(" Recent Enquiry Data not available");
			}
		}
		catch(Exception exception)
		{
			logger.error("Exception occurs while iterating RecentEnquiry");
			logger.error("PDFConverter || RecentEnquiries || Error " + exception);
		}
		sb.append("<br></br>");
		return sb;
	}
	
}