package com.qualtech.cibil.api.utils;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.axis.encoding.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.tidy.Tidy;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.cibil.api.entity.AccountDetails;
import com.qualtech.cibil.api.entity.AccountManualSummary;
import com.qualtech.cibil.api.entity.EnquiryDetails;
import com.qualtech.cibil.api.entity.EnquirySummary;
import com.qualtech.cibil.api.entity.PaymentHistoryUI;
import com.qualtech.cibil.api.interfaces.CibilUtilInt;
import com.qualtech.cibil.api.response.CibilResponsePayload;
@Service 
public class CibilUtil implements CibilUtilInt
{
	private static Logger logger = Logger.getLogger(CibilUtil.class);
	@Autowired  PropertyFile env;
	static int pageCount=0;
	public static final String HEADER1 = "<table align=\"center\" border=\"0\" cellpadding=\"0\" width=\"100%\"> 								<tr>  <td class=\"dataValue\" 	style=\"font-size: 35px; text-align: center;\">CIBIL</td> </tr></table>   ";
	public static final String HEADER2 = "<table align=\"center\" border=\"0\" cellpadding=\"0\" width=\"100%\"> 	<tr>  <td class=\"dataHeader\" 	style=\"color: #333; text-align: center; font-size: 14px;\">CREDIT INFORMATION REPORT</td> </tr></table>   ";
	public static final String HEADER3 = "<table align=\"center\" style=\"border-bottom:2px solid #1675a5;\" cellpadding=\"0\" width=\"100%\">  <tr>  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 14px; padding-left:10px;  font-family: Arial; background: #fff;	text-align: left; font-weight: normal;	padding-bottom: 3px;\">Consumer CIR</td> </tr> </table>";
	public static final String HEADER4 = "<table align=\"center\" border=\"0\" style=\"background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Consumer : </td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Date : </td> </tr> </table>";
	public static final String HEADER5 = "<table align=\"center\" border=\"0\" style=\"background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Member Id : </td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Time : </td> </tr> </table>";
	public static final String HEADER6 = "<table align=\"center\" style=\"border-bottom:2px solid #1675a5; background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Member Reference Number : </td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Control Number : </td> </tr> </table>";
	public static final String FOOTER1 = "<table width=\"100%\" style=\"border-top:2px solid #1675a5;\"><tr><td class=\"dataHeader\" 	style=\"color: #333; text-align: center; font-size: 12px;\">You Now have access to Cibil Market place</td> </tr></table>";//<td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Page No : PAGENODATA</td>
	public static final String FOOTER2 = "<table width=\"100%\"  border=\"0\"><tr><td class=\"dataHeader\" 	style=\"color: #333; text-align: center; font-size: 12px;\">Now Apply for loan and Credit cards Offer.Now check your credit eligibility from participating bank and financial institution in just a click</td></tr></table>";

	public class HeaderFooter extends PdfPageEventHelper {
		protected ElementList header1;
		protected ElementList header2;
		protected ElementList header3;
		protected ElementList header4;
		protected ElementList header5;
		protected ElementList header6;
		protected ElementList footer1;
		protected ElementList footer2;
		String FOOTER1STR="";
		// private PdfTemplate t;
		int i=1;
		Image img=null;
		public HeaderFooter() throws IOException {
			header1 = XMLWorkerHelper.parseToElementList(HEADER1, null);
			header2 = XMLWorkerHelper.parseToElementList(HEADER2, null);
			header3 = XMLWorkerHelper.parseToElementList(HEADER3, null);
			header4 = XMLWorkerHelper.parseToElementList(HEADER4, null);
			header5 = XMLWorkerHelper.parseToElementList(HEADER5, null);
			header6 = XMLWorkerHelper.parseToElementList(HEADER6, null);
			footer1 = XMLWorkerHelper.parseToElementList(FOOTER1, null);
			footer2 = XMLWorkerHelper.parseToElementList(FOOTER2, null);
		}
		public HeaderFooter(String HEADER4,String HEADER5,String HEADER6) throws IOException {
			header1 = XMLWorkerHelper.parseToElementList(HEADER1, null);
			header2 = XMLWorkerHelper.parseToElementList(HEADER2, null);
			header3 = XMLWorkerHelper.parseToElementList(HEADER3, null);
			header4 = XMLWorkerHelper.parseToElementList(HEADER4, null);
			header5 = XMLWorkerHelper.parseToElementList(HEADER5, null);
			header6 = XMLWorkerHelper.parseToElementList(HEADER6, null);
			footer1 = XMLWorkerHelper.parseToElementList(FOOTER1, null);
			footer2 = XMLWorkerHelper.parseToElementList(FOOTER2, null);
		}
		
		
		
		
		@Override
		public void onEndPage(PdfWriter writer, Document document) 
		{
			try
			{
				img = Image.getInstance(env.getString("com.qc.context.path")+File.separator+"images"+File.separator+"qualtech.jpg");
				img.scalePercent(40);
				img.setAbsolutePosition(50,1130);
				document.add(img);
			} catch (Exception e) 
			{
				logger.error("CibilUtil || inner class HeaderFooter || onEndPage() || accessing Qualtech Logo :: "+e);
			}
			try 
			{ 	
				ColumnText ct = new ColumnText(writer.getDirectContent());
				ct.setSimpleColumn(new Rectangle(36, 1160, 804, 1135));
				for (Element e : header1) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 1130, 804, 1110));
				for (Element e : header2) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 1105, 804, 1085));
				for (Element e : header3) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 1085, 804, 1074));
				for (Element e : header4) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 1074, 804, 1063));
				for (Element e : header5) {
					ct.addElement(e);
				}
				ct.go();
				
				ct.setSimpleColumn(new Rectangle(36, 1063, 804, 1052));
				for (Element e : header6) {
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
			} catch (DocumentException de) 
			{
				logger.error("CibilUtil || inner class HeaderFooter || onEndPage() || setting header footer positions "+de);
				throw new ExceptionConverter(de);
			}
		}
		
		
		
	}


	public  void convertHtmlToPDF(String htmlData,String fileName, CibilResponsePayload cibilresponsepayload)
	{
		fileName+=com.qualtech.cibil.api.utils.UniqueId.getUniqueId();
		File file=null;
		String returnString="";
		//byte bar[]=null;
		try {
			fileName=env.getString("com.qc.cibil.pdf.savePath")+fileName+".pdf";
			htmlData=htmlData.replaceAll("\"670\"", "\"500\"");
			htmlData=htmlData.replaceAll("px\"", "\"");
			Tidy T = new Tidy();
			T.setMakeClean(true);
			T.setPrintBodyOnly(false);
			T.setBreakBeforeBR(false);
			T.setFixBackslash(false);
			T.setXHTML(true);
			T.setIndentAttributes(false);
			T.setEncloseBlockText(false);
			T.setXmlPi(false);
			T.setXmlSpace(false);
			T.setDropProprietaryAttributes(true);
			T.setEmacs(true);
			T.setQuiet(true);
			T.setSmartIndent(true);
			ByteArrayOutputStream FOS = new ByteArrayOutputStream();
			/*org.w3c.dom.Document D =*/ T.parseDOM(new ByteArrayInputStream(htmlData.getBytes()), FOS);
			String lasthtml = new String(FOS.toByteArray(),"UTF-8");
				
			
			Document document = new Document(PageSize.A3, 36, 36, 150, 75);
			
			PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(fileName));
			
			
			String consumerName = cibilresponsepayload.getName1()+" "+cibilresponsepayload.getName2()+" "+cibilresponsepayload.getName3()+" "+cibilresponsepayload.getName4()+" "+cibilresponsepayload.getName5();
			String HEADER4 = "<table align=\"center\" border=\"0\" style=\"background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Consumer : "+consumerName.trim()+"</td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Date : "+cibilresponsepayload.getProcessedDate()+"</td> </tr> </table>";
			String HEADER5 = "<table align=\"center\" border=\"0\" style=\"background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Member Id : "+cibilresponsepayload.getMemberId()+"</td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Time : "+cibilresponsepayload.getTimeProceesed()+"</td> </tr> </table>";
			String HEADER6 = "<table align=\"center\" style=\"border-bottom:2px solid #1675a5; background: #e5e5e5;\" cellpadding=\"0\" width=\"100%\">  <tr style=\"background: #e5e5e5;\">  <td class=\"mainHeader\" 	style=\"color: #1675a5; text-align: left; font-size: 12px; padding-left:10px;  font-family: Arial; text-indent: 5px;	text-align: left; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Member Reference Number : "+cibilresponsepayload.getMemberRefNo()+"</td><td style=\"color: #1675a5; text-align: right; font-size: 12px; padding-right:10px;  font-family: Arial; text-indent: 5px;	text-align: right; font-weight: normal;	padding-bottom: 5px; padding-top: 5px;\">Control Number : "+cibilresponsepayload.getEnquiryControlNumber()+"</td> </tr> </table>";
			
			
			
			pdfWriter.setPageEvent(new CibilUtil().new HeaderFooter(HEADER4,HEADER5,HEADER6));
			document.open();

			XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
			String cssFile="<style type=\"text/css\">@media print{ table { page-break-after:auto; -webkit-print-color-adjust:exact;} thead { display:table-header-group; } tfoot { display:table-footer-group; } body	{	margin-top:10px;	margin-bottom:10px;	margin-right:25px;	margin-left:30px;	}}.shading{	background-color: #e6e6ff;	background:#e6e6ff;}.box { background: #FFFFFF;	border-style: solid;	border-width: thin;	border-color: #FFFFFF;	border-collapse: collapse;	text-align: left;	-moz-box-shadow: 0px 0px 30px #DADADA;	-webkit-box-shadow: 0px 0px 30px #DADADA;	box-shadow: 0px 0px 30px #DADADA;}.box1 {	background: #FFFFFF;	border-style: solid;	border-width: 0px;	border-collapse: collapse;	text-align: left;}.tabStyle {	background: #FFFFFF;	border-style: inset;	border-width: thin;	border-color: black;	border-collapse: collapse;}.rowStyle {	background: #FFFFFF;	border-style: solid;	border-width: thin;	border-color: grey;	border-collapse: collapse;}.box1 tr:nt-child(even) {	background-color: white;}.box1 tr:nth-child(odd) {	background-color: #F1F3F5;}.style14 {	font-face: segoe ui semibold;	font-size: 2px;}.summarytable {	background: #FFFFFF;	border-style: solid;	border-width: 0px;	border-collapse: collapse;	text-align: left;	border-left: none;	border-right: none;}.reportHead {	font-family: segoe ui semibold;	font-size: 24px;	color: #0f3f6b;	font-weight: 600;	text-align: left;}.dataHead {	font-family: segoe ui semibold;	font-size: 12px;	font-weight: 600;	color: #464646;	text-align: right;	text-indent: 5px;}.mainHeader {	font-family: segoe ui semibold;	font-size: 16px;	color: #FFFFFF;	background: #0f3f6b;	text-align: left;	font-weight: 600;	padding-bottom: 3px;}.subHeader {	font-family: segoe ui semibold;	font-size: 13px;	color: #0f3f6b;	text-align: left;	border-width: thin;	border-collapse: collapse;	border-bottom: 1px solid #A7CBE3;	border-left: 0px;	border-right: 0px;	border-top: 0px;	background: #FFFFFF;	text-indent: 5px;	font-weight: 600;}.subHeader1 {	font-family: segoe ui semibold;	font-size: 13px;	color: #0f3f6b;	border-width: thin;	border-collapse: collapse;	border-bottom: 1px solid #A7CBE3;	border-left: 0px;	border-right: 0px;	border-top: 0px; background: #FFFFFF;	text-indent: 5px;	font-weight: 600;}.dataHeaderNone {	font-family: segoe ui semibold;	font-size: 14px;	color: #0f3f6b;	font-weight: 600;	text-align: center;	text-indent: 5px;	white-space: nowrap;	height : 23; valign:middle}.subHeader2 {	font-family: segoe ui semibold;	border-collapse: collapse;	border-bottom: 0px;	border-left: 1px solid #ffffff;	border-right: 0px;	border-top: 1px solid #ffffff;	background: #FFFFFF;	text-indent: 5px; font-weight: 600;	white-space: nowrap;}.dataHeader {	font-family: segoe ui semibold;	font-size: 13px;	color: #0f3f6b; font-weight: 600;	text-align: left;	text-indent: 5px;	white-space: nowrap;	padding-top: 2px;}.dataHeaderScore {	font-family: segoe ui semibold;	font-size: 12px;	color: #464646;	font-weight: 600;	text-align: left;	text-indent: 5px; white-space: nowrap;	padding-top: 2px;}.dataValueValue {	font-family: segoe ui semibold;	font-size: 25px;	font-weight: 600;	color: #464646;	text-align: left;	padding-left: 7px;	padding-top: 1px;}.dataValuePerform {	font-family: segoe ui semibold;	font-size: 12px;	font-weight: 600;	color: #464646;	text-align: left;	padding-left: 7px;	padding-top: 1px;}.dataValuePerform2 {	border-collapse: separate; Color: #464646; font-family: segoe ui semibold; font-size: 12px; font-weight: 280;}.dataHeadern {	font-family: segoe ui semibold;	font-size: 13px;	color: #0f3f6b;	font-weight: 600; text-align: left;	text-indent: 5px;	padding-top: 2px;}.dataValue {	font-family: segoe ui semibold;	font-size: 14px; font-weight: 600;	color: #464646;	text-align: left;	padding-left: 7px;	padding-top: 1px;}.dataAmtValue {	font-family: segoe ui semibold;	font-size: 14px;	font-weight: 600;	color: #464646;	text-align: right;	padding-right: 7px;	padding-top: 1px;}.dataHeader1 {	font-family: segoe ui semibold;	font-size: 12px;	color: #0f3f6b;	font-weight: 600;	text-align: left;	text-indent: 5px;}.dataValue1 {	font-family: segoe ui semibold;	font-size: 12px;	font-weight: 600;	color: #464646;	text-align: left;	text-indent: 5px;}.mainAccHeader {	font-family: segoe ui semibold;	font-size: 13px;	color: #FFFFFF;	background: #0f3f6b;	font-weight: 600;}.AccHeader {	font-family: segoe ui semibold;	font-size: 13px;	color: #0f3f6b;	font-weight: 600;	text-indent: 5px;}.subAccHeader {	font-family: segoe ui semibold;	font-size: 13px;	color: #0f3f6b;	background: #e6e6ff;	font-weight: 600;	border-width: thin;	border-bottom: 1px solid #A7CBE3;	border-left: 1px solid #A7CBE3;	border-right: 1px solid #A7CBE3;	border-top: 1px solid #A7CBE3;	}.AccValue {	font-family: segoe ui semibold;	font-size: 14px;	font-weight: 600;	color: #464646;	text-indent: 5px;}.AccValue1 {	font-family: segoe ui semibold;	font-size: 12px;	font-weight: 600;	color: #464646;	text-indent: 5px;	border-width: thin;	border-bottom: 1px solid #A7CBE3;	border-left: 1px solid #A7CBE3;	border-right: 1px solid #A7CBE3;	border-top: 1px solid #A7CBE3;}.AccSummaryTab {	border-width: thin;	border-collapse: collapse;	border-left: 1px solid #A7CBE3;	border-right: 1px solid #A7CBE3;	border-top: 1px solid #A7CBE3;	border-bottom: 0px;	text-indent: 5px;}.disclaimerValue {	font-family: segoe ui semibold;	font-size: 12px;	font-weight: 500;	color: grey;}.infoValue {	font-family: segoe ui semibold;	font-size: 12px;	font-weight: 500;	color: grey;	padding-right: 15px;	font-style: normal;}.maroonFields { color: Maroon;	font-family: segoe ui semibold;	font-size: 15px;	font-weight: 600;}.AccValueComm2 {	font-family: segoe ui semibold;	font-size: 11px;	font-weight: 600;	color: #464646;	text-indent: 5px;	border-width: thin;	border-bottom: 1px solid #A7CBE3;	border-left: 1px solid #A7CBE3;	border-right: 1px solid #A7CBE3;	border-top: 1px solid #A7CBE3;}.AccValue2 {	font-family: segoe ui semibold;	font-size: 11px;	font-weight: 600;	color: #464646;	text-indent: 5px;	border-width: thin;	border-bottom: 1px solid #A7CBE3;	border-left: 1px solid #A7CBE3;	border-right: 1px solid #A7CBE3;	border-top: 1px solid #A7CBE3;	}.container {	/* this will give container dimension, because floated child nodes don't give any */	/* if your child nodes are inline-blocked, then you don't have to set it */	overflow: auto;}.container .headActive {	/* float your elements or inline-block them to display side by side */	float: left;	/* these are height and width dimensions of your header */	height: 10em;	width: 1.5em;	/* set to hidden so when there's too much vertical text it will be clipped. */	overflow: hidden;	/* these are not relevant and are here to better see the elements */	background: #ffe1dc;	color: #be0000;	margin-right: 1px;	font-family: segoe ui ;	font-weight:bold;}.container .headActive .vertActive {	/* line height should be equal to header width so text will be middle aligned */	line-height: 1.5em;	/* setting background may yield better results in IE text clear type rendering */	background: #ffe1dc;	color: #be0000;	display: block;	/* this will prevent it from wrapping too much text */	white-space: nowrap;	/* so it stays off the edge */	padding-left: 3px;	font-family: segoe ui ;	font-weight:bold;	/* CSS3 specific totation code */	/* translate should have the same negative dimension as head height */	transform: rotate(-270deg) translate(1em, 0);	transform-origin: -5px 30px;	-moz-transform: rotate(-270deg) translate(1em, 0);	-moz-transform-origin: -5px 30px;	-webkit-transform: rotate(-270deg) translate(1em, 0);	-webkit-transform-origin: -5px 30px;	-ms-transform-origin:none;-ms-transform:none;-ms-writing-mode:tb-rl;*writing-mode:tb-rl;}.container .headClosed {	/* float your elements or inline-block them to display side by side */	float: left;	/* these are height and width dimensions of your header */	height: 10em;	width: 1.5em;	/* set to hidden so when there's too much vertical text it will be clipped. */	overflow: hidden;	/* these are not relevant and are here to better see the elements */	background: #e1f0be;	color: #415a05;	margin-right: 1px;	font-family: segoe ui ;	font-weight:bold;}.container .headClosed .vertClosed {	/* line height should be equal to header width so text will be middle aligned */	line-height: 1.5em;	/* setting background may yield better results in IE text clear type rendering */	background: #ffe1dc;	color: #415a05; display: block;	/* this will prevent it from wrapping too much text */	white-space: nowrap;	/* so it stays off the edge */	padding-left: 3px;	font-family: segoe ui ;	font-weight:bold;	/* CSS3 specific totation code */	/* translate should have the same negative dimension as head height */	transform: rotate(-270deg) translate(1em, 0);	transform-origin: -5px 30px;	-moz-transform: rotate(-270deg) translate(1em, 0);	-moz-transform-origin: -5px 30px;	-webkit-transform: rotate(-270deg) translate(1em, 0);	-webkit-transform-origin: -5px 30px;	-ms-transform-origin:none;-ms-transform:none;-ms-writing-mode:tb-rl;*writing-mode:tb-rl;}.infoValueNote {	font-family: segoe ui semibold;	font-size: 11px;	font-weight: 500;	color: grey;	padding-right: 15px;	font-style: normal;}</style>";
			worker.parseXHtml(pdfWriter, document, new ByteArrayInputStream(lasthtml.getBytes()),new ByteArrayInputStream(cssFile.getBytes()));
			document.close();

			file = new File(fileName);
			FileInputStream fis=new FileInputStream(file);
			returnString=Base64.encode(IOUtils.toByteArray(fis));
			fis.close();
			cibilresponsepayload.setPdfByteArray(returnString);
			cibilresponsepayload.setPdfPath(fileName);
		} 
		catch (Exception e) 
		{
			logger.error("Exception in CibilUtil || convertHtmlToPDF() || PDF creation issue "+e);
		}
		finally
		{
			try
			{
				//				if(file!=null)
				//					file.delete();
			}
			catch (Exception ex) 
			{
				logger.error("Exception in CibilUtil || convertHtmlToPDF() || Deleting Temp File ::",ex);
			}
		}
		//return returnString;
	}

	public static PdfPCell createImageCell(String path) throws DocumentException, IOException {
		Image img = Image.getInstance(path);
		PdfPCell cell = new PdfPCell(img, true);
		return cell;
	}

	public static PdfPCell createTextCell(String text) throws DocumentException, IOException {
		PdfPCell cell = new PdfPCell();
		Paragraph p = new Paragraph(text);
		p.setAlignment(Element.ALIGN_RIGHT);
		cell.addElement(p);
		cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
		cell.setBorder(Rectangle.NO_BORDER);
		return cell;
	}


	/*Added on 13-july by Anuj*/
	public  Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
		Map<String, Object> retMap = new HashMap<String, Object>();

		if(json != JSONObject.NULL) {
			retMap = toMap(json);
		}
		return retMap;
	}

	public  Map<String, Object> toMap(JSONObject object) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		@SuppressWarnings("unchecked")
		Iterator<String> keysItr = object.keys();
		while(keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}

	public  List<Object> toList(JSONArray array) throws JSONException {
		List<Object> list = new ArrayList<Object>();
		for(int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if(value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if(value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}
	/*END*/

	public static EnquirySummary getEnquirySummary(List<EnquiryDetails> enquiryDetails)
	{
		EnquirySummary enquirySummary = new EnquirySummary();
		String recentDate="";
		int past30DayCount=0;
		int past90DayCount=0;
		int past12MonthCount=0;
		int past24MonthCount=0;
		try
		{
			if(enquiryDetails!=null && !enquiryDetails.isEmpty())
			{
				enquirySummary.setTotal(enquiryDetails.size()+"");
				for(EnquiryDetails details:enquiryDetails)
				{
					recentDate = getLetestDate(recentDate , details.getDateOfEnquiry());
					if(isExistInPastDays(31, details.getDateOfEnquiry()))
					{
						past30DayCount++;
						past90DayCount++;
						past12MonthCount++;
						past24MonthCount++;
					}
					else if(isExistInPastDays(91, details.getDateOfEnquiry()))
					{
						past90DayCount++;
						past12MonthCount++;
						past24MonthCount++;
					}
					else if(isExistInPastDays(366, details.getDateOfEnquiry()))
					{
						past12MonthCount++;
						//past24MonthCount++;
					}
					else if(isExistInPastDays((365*2)+1, details.getDateOfEnquiry()))
					{
						past24MonthCount++;
					}
				}
			}
		}
		catch (Exception e)
		{
			
			logger.error("Exception in CibilUtil || getEnquirySummary() ||  calculating enquirySummary : "+e);
		}
		enquirySummary.setRecent(recentDate);
		enquirySummary.setPast30Days(past30DayCount+"");
		enquirySummary.setPast90Days(past90DayCount+"");
		enquirySummary.setPast12Months(past12MonthCount+"");
		enquirySummary.setPast24Months(past24MonthCount+"");
		return enquirySummary;
	}

	public static EnquirySummary getEnquirySummarySpecific(List<EnquiryDetails> enquiryDetails, String enqPurpose, CibilResponsePayload cibilresponsepayload)
	{
		EnquirySummary enquirySummary = new EnquirySummary();
		String recentDate="";
		int total=0;
		int past30DayCount=0;
		int past90DayCount=0;
		int past12MonthCount=0;
		int past24MonthCount=0;
		try
		{
			if(enquiryDetails!=null && !enquiryDetails.isEmpty())
			{
				for(EnquiryDetails details:enquiryDetails)
				{
					if(details.getEnquiryPurpose().equalsIgnoreCase(enqPurpose))
					{
						total++;
						recentDate = getLetestDate(recentDate , details.getDateOfEnquiry());
						if(isExistInPastDays(31, details.getDateOfEnquiry()))
						{
							past30DayCount++;
							past90DayCount++;
							past12MonthCount++;
							past24MonthCount++;
						}
						else if(isExistInPastDays(91, details.getDateOfEnquiry()))
						{
							past90DayCount++;
							past12MonthCount++;
							past24MonthCount++;
						}
						else if(isExistInPastDays(366, details.getDateOfEnquiry()))
						{
							past12MonthCount++;
							past24MonthCount++;
						}
						else if(isExistInPastDays((365*2)+1, details.getDateOfEnquiry()))
						{
							past24MonthCount++;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			logger.error("Exception in CibilUtil || getEnquirySummarySpecific() || "+e);
		}
		enquirySummary.setEnquiryPurpose(enqPurpose);
		enquirySummary.setTotal(total+"");
		enquirySummary.setRecent(recentDate);
		enquirySummary.setPast30Days(past30DayCount+"");
		enquirySummary.setPast90Days(past90DayCount+"");
		enquirySummary.setPast12Months(past12MonthCount+"");
		enquirySummary.setPast24Months(past24MonthCount+"");
		enquirySummary.setCibilresponsepayload(cibilresponsepayload);
		
		
		return enquirySummary;
	}


	public static String getLetestDate(String date1 , String date2)
	{
		try 
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

			Calendar cal1 = Calendar.getInstance(); 
			cal1.setTime(sdf.parse(date1.trim()+" 00:00:00"));

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(sdf.parse(date2.trim()+" 00:00:00"));

			if(cal1.before(cal2))
			{
				return date2;
			}
			else
			{
				return date1;
			}
		}
		catch (ParseException e) 
		{
			logger.debug("Exception in CibilUtil || getLetestDate() || check date parsing : "+e);
		}
		if(date1.equals("") || date1.equalsIgnoreCase("NA"))
		{
			return date2;
		}
		else
		{
			return date1;
		}
	}
	public static String getOldestDate(String date1 , String date2)
	{
		try 
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

			Calendar cal1 = Calendar.getInstance(); 
			cal1.setTime(sdf.parse(date1.trim()+" 00:00:00"));

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(sdf.parse(date2.trim()+" 00:00:00"));

			if(cal1.before(cal2))
			{
				return date1;
			}
			else
			{
				return date2;
			}
		}
		catch (ParseException e) 
		{
			logger.debug("Exception in CibilUtil || getOldestDate() || check date parsing : "+e);
		}
		if(date1.equals("") || date1.equalsIgnoreCase("NA"))
		{
			return date2;
		}
		else
		{
			return date1;
		}
	}
	public static boolean isExistInPastDays(int pastDayCount , String date2)
	{
		try 
		{
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

			Calendar cal1 = Calendar.getInstance(); 
			cal1.setTime(new Date());
			cal1.set(Calendar.MILLISECOND, 0);
			cal1.set(Calendar.SECOND, 0);
			cal1.set(Calendar.MINUTE, 0);
			cal1.set(Calendar.HOUR, 0);
			cal1.add(Calendar.DAY_OF_MONTH, -pastDayCount);

			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(sdf.parse(date2.trim()+" 00:00:00"));

			if(cal1.before(cal2))
			{
				return true;
			}
		}
		catch (ParseException e) 
		{
			logger.debug("Exception in CibilUtil || isExistInPastDays() || check date parsing past : "+e);
		}
		return false;
	}
	public static Map<String,String> getApendixAData()
	{
		Map<String,String> setupMap = new HashMap<String,String>();
		setupMap.put("1","Auto Loan (Personal)");
		setupMap.put("2","Housing Loan");
		setupMap.put("3","Property Loan");
		setupMap.put("4","Loan Against Shares/Securities");
		setupMap.put("5","Personal Loan");
		setupMap.put("6","Consumer Loan");
		setupMap.put("7","Gold Loan");
		setupMap.put("8","Education Loan");
		setupMap.put("9","Loan to Professional");
		setupMap.put("10","Credit Card");
		setupMap.put("11","Leasing");
		setupMap.put("12","Overdraft");
		setupMap.put("13","Two-wheeler Loan");
		setupMap.put("14","Non-Funded Credit Facility");
		setupMap.put("15","Loan Against Bank Deposits");
		setupMap.put("16","Fleet Card");
		setupMap.put("17","Commercial Vehicle Loan");
		setupMap.put("18","Telco - Wireless");
		setupMap.put("19","Telco - Broadband");
		setupMap.put("20","Telco - Landline");
		setupMap.put("31","Secured Credit Card");
		setupMap.put("32","Used Car Loan");
		setupMap.put("33","Construction Equipment Loan");
		setupMap.put("34","Tractor Loan");
		setupMap.put("35","Corporate Credit Card");
		setupMap.put("36","Kisan Credit Card");
		setupMap.put("37","Loan on Credit Card");
		setupMap.put("38","Prime Minister Jaan Dhan Yojana - Overdraft");
		setupMap.put("39","Mudra Loans - Shishu / Kishor / Tarun");
		setupMap.put("40","Microfinance - Business Loan");
		setupMap.put("41","Microfinance - Personal Loan");
		setupMap.put("42","Microfinance - Housing Loan");
		setupMap.put("43","Microfinance - Other");
		setupMap.put("44","Pradhan Mantri Awas Yojana - Credit Link Subsidy Scheme MAY CLSS");
		setupMap.put("50","Business Loan - Secured");
		setupMap.put("51","Business Loan - General");
		setupMap.put("52","Business Loan - Priority Sector - Small Business");
		setupMap.put("53","Business Loan - Priority Sector - Agriculture");
		setupMap.put("54","Business Loan - Priority Sector - Others");
		setupMap.put("55","Business Non-Funded Credit Facility - General");
		setupMap.put("56","Business Non-Funded Credit Facility - Priority Sector - Small Business");
		setupMap.put("57","Business Non-Funded Credit Facility - Priority Sector - Agriculture");
		setupMap.put("58","Business Non-Funded Credit Facility - Priority Sector-Others");
		setupMap.put("59","Business Loan Against Bank Deposits");
		setupMap.put("61","Business Loan - Unsecured");
		setupMap.put("80","Microfinance Detailed Report (Applicable to Enquiry Purpose only)");
		setupMap.put("81","Summary Report (Applicable to Enquiry Purpose only)");
		setupMap.put("88","Locate Plus for Insurance (Applicable to Enquiry Purpose only)");
		setupMap.put("90","Account Review (Applicable to Enquiry Purpose only)");
		setupMap.put("91","Retro Enquiry (Applicable to Enquiry Purpose only)");
		setupMap.put("92","Locate Plus (Applicable to Enquiry Purpose only)");
		setupMap.put("97","Adviser Liability (Applicable to Enquiry Purpose only)");
		setupMap.put("0","Other");
		setupMap.put("98","Secured (Account Group for Portfolio Review response)");
		setupMap.put("99","Unsecured (Account Group for Portfolio Review response)");
		return setupMap;
	}

	public static Map<String,String> getApndxGErrorCodeForEM()
	{
		Map<String,String> map=new HashMap<String,String>();
		map.put("000","No Dispute");
		map.put("003","Occupation Code in Dispute");
		map.put("004","Income in Dispute");
		map.put("005","Net/Gross Income Indicator in Dispute");
		map.put("006","Monthly/Annual Income Indicator in Dispute");
		map.put("203","Occupation Code - Dispute accepted - Pending corrections by the Member");
		map.put("204","Income - Dispute accepted - Pending corrections by the Member");
		map.put("205","Net/Gross Income Indicator - Dispute accepted - Pending corrections by the Member");
		map.put("206","Monthly/Annual Income Indicator - Dispute accepted - Pending corrections by the Member");
		map.put("998","Multiple Disputes - Dispute accepted - Pending corrections by the Member");
		map.put("999","Multiple Disputes");
		return map;
	}

	public static Map<String,String> getApndxGErrorCodeForAccountTL(){
		Map<String,String> map=new HashMap<String,String>();
		map.put("000","No Disputes");
		map.put("003","Account Number in Dispute");
		map.put("004","Account Type in Dispute");
		map.put("005","Ownership Indicator in Dispute");
		map.put("008","Date Opened/Disbursed in Dispute");
		map.put("009","Date of Last Payment in Dispute");
		map.put("010","Date Closed in Dispute");
		map.put("011","Date Reported and Certified in Dispute");
		map.put("012","High Credit/Sanctioned Amount in Dispute");
		map.put("013","Current Balance in Dispute");
		map.put("014","Amount Overdue in Dispute");
		map.put("030","Payment History Start Date in Dispute");
		map.put("031","Payment History End Date in Dispute");
		map.put("032","Suit Filed / Wilful Default in Dispute");
		map.put("033","Written-off and Settled Status in Dispute");
		map.put("034","Value of Collateral in Dispute");
		map.put("035","Type of Collateral in Dispute");
		map.put("036","Credit Limit in Dispute");
		map.put("037","Cash Limit in Dispute");
		map.put("038","Rate Of Interest in Dispute");
		map.put("039","Repayment Tenure in Dispute");
		map.put("040","EMI Amount in Dispute");
		map.put("0141","Written-off Amount (Total) in Dispute");
		map.put("042","Written-off Amount (Principal) in Dispute");
		map.put("043","Settlement Amount in Dispute");
		map.put("044","Payment Frequency in Dispute");
		map.put("045","Actual Payment Amount in Dispute");
		map.put("101","Payment History 1 in Dispute");
		map.put("102","Payment History 2 in Dispute");
		map.put("103","Payment History 3 in Dispute");
		map.put("104","Payment History 4 in Dispute");
		map.put("105","Payment History 5 in Dispute");
		map.put("106","Payment History 6 in Dispute");
		map.put("107","Payment History 7 in Dispute");
		map.put("108","Payment History 8 in Dispute");
		map.put("109","Payment History 9 in Dispute");
		map.put("110","Payment History 10 in Dispute");
		map.put("111","Payment History 11 in Dispute");
		map.put("112","Payment History 12 in Dispute");
		map.put("113","Payment History 13 in Dispute");
		map.put("114","Payment History 14 in Dispute");
		map.put("115","Payment History 15 in Dispute");
		map.put("116","Payment History 16 in Dispute");
		map.put("117","Payment History 17 in Dispute");
		map.put("118","Payment History 18 in Dispute");
		map.put("119","Payment History 19 in Dispute");
		map.put("120","Payment History 20 in Dispute");
		map.put("121","Payment History 21 in Dispute");
		map.put("122","Payment History 22 in Dispute");
		map.put("123","Payment History 23 in Dispute");
		map.put("124","Payment History 24 in Dispute");
		map.put("125","Payment History 25 in Dispute");
		map.put("126","Payment History 26 in Dispute");
		map.put("127","Payment History 27 in Dispute");
		map.put("128","Payment History 28 in Dispute");
		map.put("129","Payment History 29 in Dispute");
		map.put("130","Payment History 30 in Dispute");
		map.put("131","Payment History 31 in Dispute");
		map.put("132","Payment History 32 in Dispute");
		map.put("133","Payment History 33 in Dispute");
		map.put("134","Payment History 34 in Dispute");
		map.put("135","Payment History 35 in Dispute");
		map.put("136","Payment History 36 in Dispute");
		map.put("203","Account Number - Dispute accepted - Pending corrections by the Member");
		map.put("204","Account Type - Dispute accepted - Pending corrections by the Member");
		map.put("205","Ownership Indicator - Dispute accepted - Pending corrections by the Member");
		map.put("208","Date Opened/Disbursed - Dispute accepted - Pending corrections by the Member");
		map.put("209","Date of Last Payment - Dispute accepted - Pending corrections by the Member");
		map.put("210","Date Closed - Dispute accepted - Pending corrections by the Member");
		map.put("211","Date Reported and Certified - Dispute accepted - Pending corrections by the Member");
		map.put("212","High Credit/Sanctioned Amount - Dispute accepted - Pending corrections by the Member");
		map.put("213","Current Balance - Dispute accepted - Pending corrections by the Member");
		map.put("214","Amount Overdue - Dispute accepted - Pending corrections by the Member");
		map.put("230","Payment History Start Date - Dispute accepted - Pending corrections by the Member");
		map.put("231","Payment History End Date - Dispute accepted - Pending corrections by the Member");
		map.put("232","Suit Filed / Wilful Default - Dispute accepted - Pending corrections by the Member");
		map.put("233","Written-off and Settled Status - Dispute accepted - Pending corrections by the Member");
		map.put("234","Value of Collateral - Dispute accepted - Pending corrections by the Member");
		map.put("235","Type of Collateral - Dispute accepted - Pending corrections by the Member");
		map.put("236","Credit Limit - Dispute accepted - Pending corrections by the Member");
		map.put("237","Cash Limit - Dispute accepted - Pending corrections by the Member");
		map.put("238","Rate Of Interest - Dispute accepted - Pending corrections by the Member");
		map.put("239","Repayment Tenure - Dispute accepted - Pending corrections by the Member");
		map.put("240","EMI Amount - Dispute accepted - Pending corrections by the Member");
		map.put("241","Written-off Amount (Total) - Dispute accepted - Pending corrections by the Member");
		map.put("242","Written-off Amount (Principal) - Dispute accepted - Pending corrections by the Member");
		map.put("243","Settlement Amount - Dispute accepted - Pending corrections by the Member");
		map.put("244","Payment Frequency - Dispute accepted - Pending corrections by the Member");
		map.put("245","Actual Payment Amount - Dispute accepted - Pending corrections by the Member");
		map.put("301","Payment History 1 - Dispute accepted - Pending corrections by the Member");
		map.put("302","Payment History 2 - Dispute accepted - Pending corrections by the Member");
		map.put("303","Payment History 3 - Dispute accepted - Pending corrections by the Member");
		map.put("304","Payment History 4 - Dispute accepted - Pending corrections by the Member");
		map.put("305","Payment History 5 - Dispute accepted - Pending corrections by the Member");
		map.put("306","Payment History 6 - Dispute accepted - Pending corrections by the Member");
		map.put("307","Payment History 7 - Dispute accepted - Pending corrections by the Member");
		map.put("308","Payment History 8 - Dispute accepted - Pending corrections by the Member");
		map.put("309","Payment History 9 - Dispute accepted - Pending corrections by the Member");
		map.put("310","Payment History 10 - Dispute accepted - Pending corrections by the Member");
		map.put("311","Payment History 11 - Dispute accepted - Pending corrections by the Member");
		map.put("312","Payment History 12 - Dispute accepted - Pending corrections by the Member");
		map.put("313","Payment History 13 - Dispute accepted - Pending corrections by the Member");
		map.put("314","Payment History 14 - Dispute accepted - Pending corrections by the Member");
		map.put("315","Payment History 15 - Dispute accepted - Pending corrections by the Member");
		map.put("316","Payment History 16 - Dispute accepted - Pending corrections by the Member");
		map.put("317","Payment History 17 - Dispute accepted - Pending corrections by the Member");
		map.put("318","Payment History 18 - Dispute accepted - Pending corrections by the Member");
		map.put("319","Payment History 19 - Dispute accepted - Pending corrections by the Member");
		map.put("320","Payment History 20 - Dispute accepted - Pending corrections by the Member");
		map.put("321","Payment History 21 - Dispute accepted - Pending corrections by the Member");
		map.put("322","Payment History 22 - Dispute accepted - Pending corrections by the Member");
		map.put("323","Payment History 23 - Dispute accepted - Pending corrections by the Member");
		map.put("324","Payment History 24 - Dispute accepted - Pending corrections by the Member");
		map.put("325","Payment History 25 - Dispute accepted - Pending corrections by the Member");
		map.put("326","Payment History 26 - Dispute accepted - Pending corrections by the Member");
		map.put("327","Payment History 27 - Dispute accepted - Pending corrections by the Member");
		map.put("328","Payment History 28 - Dispute accepted - Pending corrections by the Member");
		map.put("329","Payment History 29 - Dispute accepted - Pending corrections by the Member");
		map.put("330","Payment History 30 - Dispute accepted - Pending corrections by the Member");
		map.put("331","Payment History 31 - Dispute accepted - Pending corrections by the Member");
		map.put("332","Payment History 32 - Dispute accepted - Pending corrections by the Member");
		map.put("333","Payment History 33 - Dispute accepted - Pending corrections by the Member");
		map.put("334","Payment History 34 - Dispute accepted - Pending corrections by the Member");
		map.put("335","Payment History 35 - Dispute accepted - Pending corrections by the Member");
		map.put("336","Payment History 36 - Dispute accepted - Pending corrections by the Member");
		map.put("885","Duplicate Account - Dispute accepted - Pending corrections by the Member");
		map.put("886","Duplicate Account");
		map.put("887","Account Ownership Error - Dispute accepted - Pending corrections by the Member");
		map.put("888","Account Ownership Error");
		map.put("998","Multiple Disputes - Dispute accepted - Pending corrections by the Member");
		map.put("999","Multiple Disputes");

		return map;
	}

	public static Map<String,String> getApndxHErrorCode(){
		Map<String,String> map=new HashMap<String,String>();
		map.put("000001","Disputed accepted - under investigation");
		return map;
	}


	public static Map<String,String> getApndxIErrorCodeForTL()
	{
		Map<String,String> map=new HashMap<String,String>();
		map.put("PN0001","Certain information under Personal / Contract / Enquiry information section has been disputed by the consumer.");
		map.put("PN1001","Consumer Name in Dispute");
		map.put("PN1007","Date of Birth in Dispute");
		map.put("PN1008","Gender in Dispute");
		map.put("PN1999","Multiple Disputes in Name (PN) Segment");
		map.put("ID1001","ID Type in Dispute");
		map.put("ID1002","ID Number in Dispute");
		map.put("ID1003","Issue Date in Dispute");
		map.put("ID1004","Expiration Date in Dispute");
		map.put("ID1999","Multiple Disputes in Identification (ID) Segment");
		map.put("PT1001","Telephone Number in Dispute");
		map.put("PT1002","Telephone Extension in Dispute");
		map.put("PT1003","Telephone Type in Dispute");
		map.put("PT1999","Multiple Disputes in Telephone (PT) Segment");
		map.put("EC1001","E-Mail ID in Dispute");
		map.put("EC1999","Multiple Disputes in Email Contact (EC) Segment");
		map.put("EM0001","Certain information under Employment information section has been disputed by the consumer.");
		map.put("EM1003","Occupation Code in Dispute");
		map.put("EM1004","Income in Dispute");
		map.put("EM1005","Net/Gross Income Indicator in Dispute");
		map.put("EM1006","Monthly/Annual Income Indicator in Dispute");
		map.put("EM1999","Multiple Disputes in Employment (EM) Segment");
		map.put("PA1001","Address Line (except State Code and PIN Code) in Dispute");
		map.put("PA1006","State in Dispute");
		map.put("PA1007","PIN Code in Dispute");
		map.put("PA1008","Address Category in Dispute");
		map.put("PA1009","Residence Code in Dispute");
		map.put("PA1999","Multiple Disputes in Address (PA) Segment");
		map.put("TL0001","Certain information for this account has been disputed by the consumer.");
		map.put("TL1003","Account Number in Dispute");
		map.put("TL1004","Account Type in Dispute");
		map.put("TL1005","Ownership Indicator in Dispute");
		map.put("TL1008","Date Opened/Disbursed in Dispute");
		map.put("TL1009","Date of Last Payment in Dispute");
		map.put("TL1010","Date Closed in Dispute");
		map.put("TL1011","Date Reported and Certified in Dispute");
		map.put("TL1012","High Credit/Sanctioned Amount in Dispute");
		map.put("TL1013","Current Balance in Dispute");
		map.put("TL1014","Amount Overdue in Dispute");
		map.put("TL1030","Payment History Start Date in Dispute");
		map.put("TL1031","Payment History End Date in Dispute");
		map.put("TL1032","Suit Filed / Wilful Default in Dispute");
		map.put("TL1033","Written-off and Settled Status in Dispute");
		map.put("TL1034","Value of Collateral in Dispute");
		map.put("TL1035","Type of Collateral in Dispute");
		map.put("TL1036","Credit Limit in Dispute");
		map.put("TL1037","Cash Limit in Dispute");
		map.put("TL1038","Rate Of Interest in Dispute");
		map.put("TL1039","Repayment Tenure in Dispute");
		map.put("TL1040","EMI Amount in Dispute");
		map.put("TL1041","Written-off Amount (Total) in Dispute");
		map.put("TL1042","Written-off Amount (Principal) in Dispute");
		map.put("TL1043","Settlement Amount in Dispute");
		map.put("TL1044","Payment Frequency in Dispute");
		map.put("TL1045","Actual Payment Amount in Dispute");
		map.put("TL1101","Payment History 1 in Dispute");
		map.put("TL1102","Payment History 2 in Dispute");
		map.put("TL1103","Payment History 3 in Dispute");
		map.put("TL1104","Payment History 4 in Dispute");
		map.put("TL1105","Payment History 5 in Dispute");
		map.put("TL1106","Payment History 6 in Dispute");
		map.put("TL1107","Payment History 7 in Dispute");
		map.put("TL1108","Payment History 8 in Dispute");
		map.put("TL1109","Payment History 9 in Dispute");
		map.put("TL1110","Payment History 10 in Dispute");
		map.put("TL1111","Payment History 11 in Dispute");
		map.put("TL1112","Payment History 12 in Dispute");
		map.put("TL1113","Payment History 13 in Dispute");
		map.put("TL1114","Payment History 14 in Dispute");
		map.put("TL1115","Payment History 15 in Dispute");
		map.put("TL1116","Payment History 16 in Dispute");
		map.put("TL1117","Payment History 17 in Dispute");
		map.put("TL1118","Payment History 18 in Dispute");
		map.put("TL1119","Payment History 19 in Dispute");
		map.put("TL1120","Payment History 20 in Dispute");
		map.put("TL1121","Payment History 21 in Dispute");
		map.put("TL1122","Payment History 22 in Dispute");
		map.put("TL1123","Payment History 23 in Dispute");
		map.put("TL1124","Payment History 24 in Dispute");
		map.put("TL1125","Payment History 25 in Dispute");
		map.put("TL1126","Payment History 26 in Dispute");
		map.put("TL1127","Payment History 27 in Dispute");
		map.put("TL1128","Payment History 28 in Dispute");
		map.put("TL1129","Payment History 29 in Dispute");
		map.put("TL1130","Payment History 30 in Dispute");
		map.put("TL1131","Payment History 31 in Dispute");
		map.put("TL1132","Payment History 32 in Dispute");
		map.put("TL1133","Payment History 33 in Dispute");
		map.put("TL1134","Payment History 34 in Dispute");
		map.put("TL1135","Payment History 35 in Dispute");
		map.put("TL1136","Payment History 36 in Dispute");
		map.put("TL1886","Duplicate Account");
		map.put("TL1888","Account Ownership Error");
		map.put("TL1999","Multiple Disputes in Account (TL) Segment");
		map.put("IQ1001","Enquiry Purpose in Dispute");
		map.put("IQ1006","Enquiry Amount in Dispute");
		map.put("IQ1888","Enquiry Ownership Error");
		map.put("IQ1999","Multiple Disputes in Enquiry (IQ) Segment");
		map.put("000001","One or more Members have not responded to your Dispute");
		map.put("000002","Dispute accepted - pending correction by the Member");
		map.put("ZZ0999","Multiple Disputes in multiple segments");
		return map;
	}

	public static String getCibilDate(String date)
	{
		try 
		{
			if(date!=null && date.length()==8)
			{
				date=date.substring(0, 2) + "-"+ date.substring(2, 4) + "-"+ date.substring(4, 8);
			}
		} 
		catch (Exception e)
		{
			logger.info("Exception in CibilUtil || getCibilDate() || Date Beautify  :: "+e);
		}
		return date;
	}

	public static List<PaymentHistoryUI> pastDueAssetClassificationData(String date , String paymentHistory, AccountDetails accountdetails, CibilResponsePayload cibilresponsepayload)
	{
		List<PaymentHistoryUI> paymentHistoryUIs=null;
		try 
		{
			paymentHistoryUIs= new ArrayList<PaymentHistoryUI>();
			Calendar cal = Calendar.getInstance(); 
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			cal.setTime(sdf.parse(date.trim()+" 00:00:00"));

			for(int i=0;i<paymentHistory.length() && (i+3)<=paymentHistory.length();i++)
			{
				PaymentHistoryUI historyUI= new PaymentHistoryUI(); 

				historyUI.setNdpdac(paymentHistory.substring(i,i+3));

				String month=""+(cal.get(Calendar.MONTH)+1);
				String year=""+cal.get(Calendar.YEAR);
				String data=(month.length()==1?"0"+month:month)  +"-"+  (year.substring(2, year.length()));
				historyUI.setNdpdacMonYear(data);
				cal.add(Calendar.MONTH, -1);

				i=i+2;
				historyUI.setAccountdetails(accountdetails);
				historyUI.setCibilresponsepayload(cibilresponsepayload);
				paymentHistoryUIs.add(historyUI);
			}
		}
		catch (ParseException e) 
		{
			logger.error("Exception in CibilUtil || pastDueAssetClassificationData() || Calculating : DAYS PAST DUE/ASSET CLASSIFICATION (UP TO 36 MONTHS) : "+e);
		}
		return paymentHistoryUIs;
	}

	public static AccountManualSummary getAccountmanualSumaryData(List<AccountDetails> accountsSegment)
	{
		AccountManualSummary accountManSummary= new AccountManualSummary();	
		if(accountsSegment!=null && !accountsSegment.isEmpty())
		{
			accountManSummary.setAccountTotal(accountsSegment.size()+"");
			int accountOverdue=0;
			int accountZeroBalance=0;
			int suitfiled_wilfulCount=0;
			int writtenOffAndSettleCount=0;
			Double advanceHighCRSancAmt=0D;
			Double currentBal=0D;
			//String currentBalStr="";
			Double overDueBal=0D;
			//String overDueBalStr="";
			String recentDate="";
			String oldestDate="";
			String advanceHighCRSancAmtString="";
			for(AccountDetails accdetail: accountsSegment)
			{
				//suitfiled_wilful
				if(accdetail.getSuitfilledDefault()!=null 
						&& !(accdetail.getSuitfilledDefault().equals("")
								|| accdetail.getSuitfilledDefault().equalsIgnoreCase("NA")
								)
						)
				{
					suitfiled_wilfulCount++;
				}
				//writtenOffAndSettle
				if(accdetail.getWrittenOffStatus()!=null 
						&& !(accdetail.getWrittenOffStatus().equals("")
								|| accdetail.getWrittenOffStatus().equalsIgnoreCase("NA")
								)
						)
				{
					writtenOffAndSettleCount++;
				}

				/////accountOverdue
				if(accdetail.getAmountOverdue()!=null 
						&& !(accdetail.getCurrntBalance().equals("")
								|| accdetail.getAmountOverdue().equalsIgnoreCase("NA")
								|| accdetail.getAmountOverdue().equalsIgnoreCase("0")
								)
						)
				{
					accountOverdue++;
					try
					{
						overDueBal+=Double.parseDouble(accdetail.getAmountOverdue());
						/*if(overDueBal!=null && overDueBal>0){
							overDueBalStr=commaSeprated(String.valueOf(overDueBal));
						}*/
					}
					catch(Exception ex)
					{
						logger.error("Exception in CibilUtil || getAccountmanualSumaryData() || overDueBal : Parse Exception : "+ex);
					}
				}

				/////Current balance
				if(accdetail.getCurrntBalance()!=null 
						&& !(accdetail.getCurrntBalance().equals("")
								|| accdetail.getCurrntBalance().equalsIgnoreCase("NA")
								|| accdetail.getCurrntBalance().equalsIgnoreCase("0")
								)
						)
				{
					try
					{
						currentBal+=Double.parseDouble(accdetail.getCurrntBalance());
						/*if(currentBal!=null && currentBal>0){
							currentBalStr=commaSeprated(String.valueOf(currentBal));
						}*/
					}
					catch(Exception ex)
					{
						logger.error("Exception in CibilUtil || getAccountmanualSumaryData() || currentBal : Parse Exception : "+ex);
					}
				}
				else
				{
					accountZeroBalance++;
				}

				/////advanceHighCRSancAmt
				if(accdetail.getSanctionAmount()!=null 
						&& !(accdetail.getSanctionAmount().equals("")
								|| accdetail.getSanctionAmount().equalsIgnoreCase("NA")
								|| accdetail.getSanctionAmount().equalsIgnoreCase("0")
								)
						)
				{
					try
					{
						advanceHighCRSancAmt+=Double.parseDouble(accdetail.getSanctionAmount());
						if(advanceHighCRSancAmt!=null && advanceHighCRSancAmt>0 && advanceHighCRSancAmt.toString().contains("E")){
							BigDecimal bd = new BigDecimal(advanceHighCRSancAmt.toString());
							//advanceHighCRSancAmtString=commaSeprated(String.valueOf(bd.longValue()));
							advanceHighCRSancAmtString=String.valueOf(bd.longValue());
						}else{
							advanceHighCRSancAmtString=String.valueOf(advanceHighCRSancAmt);
						}
					}
					catch(Exception ex)
					{
						logger.error("Exception in CibilUtil || getAccountmanualSumaryData() || advanceHighCRSancAmt : Parse Exception : "+ex);
					}
				}

				try
				{
					recentDate = getLetestDate(recentDate , accdetail.getDateOpened());
				}
				catch(Exception ex)
				{
					logger.error("Exception in CibilUtil || getAccountmanualSumaryData() || getLetestDate : Parse Exception : "+ex);
				}
				try
				{
					oldestDate = getOldestDate(oldestDate , accdetail.getDateOpened());
				}
				catch(Exception ex)
				{
					logger.error("Exception in CibilUtil || getAccountmanualSumaryData() || getOldestDate : Parse Exception : "+ex);
				}

			}

			accountManSummary.setSuitfiled_wilfulCount(suitfiled_wilfulCount+"");
			accountManSummary.setWrittenOffAndSettleCount(writtenOffAndSettleCount+"");
			accountManSummary.setAccountOverdue(accountOverdue+"");
			accountManSummary.setAccountZeroBalance(accountZeroBalance+"");
			accountManSummary.setAdvanceHighCRSancAmt(advanceHighCRSancAmtString);
			accountManSummary.setCurrentBalance(currentBal+"");
			accountManSummary.setOverdueBalance(overDueBal+"");
			accountManSummary.setRecentDate(recentDate);
			accountManSummary.setOldestDate(oldestDate);
		}
		return accountManSummary;
	}

	public static String commaSeprated(String str) {
		
		double d = 0;
		DecimalFormat twoDForm = new DecimalFormat(",##");
		DecimalFormat twoDForm3 = new DecimalFormat(",###");
		DecimalFormat twoDForm1 = new DecimalFormat("000");
		try
		{
			if((str==null) || str.isEmpty() )
			{
				d  = 0;
			}
			else
			{
				d = Double.parseDouble(str);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			logger.error("Exception in CibilUtil || commaSeprated() || "+ex);
		}

		if(d==0D)
		{
			return "Not Available";
		}
		else if(d < 1000) 
		{
			return twoDForm3.format(d);
		}
		else 
		{
			double hundreds = d % 1000;
			long other = (long) (d / 1000);
			return twoDForm.format(other)+','+twoDForm1.format(hundreds);
		}

	}
	public static String getApendixFdata(String code, String scoreName)
	{
		String scoreFactor="";
		if(code !=null && scoreName!=null)
		{
			if(code.equalsIgnoreCase("01"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Too many tradelines 91+ days delinquent in the past 6 months.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Not enough credit card debt experience.";
				}
			}
			else if(code.equalsIgnoreCase("02"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Presence of a tradeline 91+ days delinquent in the past 6 months.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Length of time since most recent account delinquency is too short.";
				}
			}
			else if(code.equalsIgnoreCase("03"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Credit card balances are too high in proportion to High Credit Amount.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Too many two-wheeler accounts.";
				}
			}
			else if(code.equalsIgnoreCase("04"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Too many tradelines with worst status in the past 6 months.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Too many business loans.";
				}
			}
			else if(code.equalsIgnoreCase("05"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Presence of severe delinquency in the past 6 months.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Credit card account balances too high in proportion to high credit amount.";
				}
			}
			else if(code.equalsIgnoreCase("06"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Presence of a minor delinquency in the past 6 months.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Maximum amount on mortgage loan is low.";
				}
			}
			else if(code.equalsIgnoreCase("07"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Presence of a tradeline with worst status in the past 6 months.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Total amount past due is too high.";
				}
			}
			else if(code.equalsIgnoreCase("08"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Credit card balances are high in proportion to High Credit Amount.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Not enough mortgage debt experience.";
				}
			}
			else if(code.equalsIgnoreCase("09"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="High number of trades with low proportion of satisfactory trades.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Too much change of indebtedness on non-mortgage trades over the past 24 months.";
				}
			}
			else if(code.equalsIgnoreCase("10"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Low proportion of satisfactory trades.";
				}
				else if(scoreName.equalsIgnoreCase("PLSCORE"))
				{
					scoreFactor="Low proportion of satisfactory trades.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Insufficient improvement in delinquency status.";
				}
			}
			else if(code.equalsIgnoreCase("11"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="No presence of a revolving tradeline.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Too much increase of indebtedness on non-mortgage trades over the past 12 months.";
				}
			}
			else if(code.equalsIgnoreCase("12"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Presence of a tradeline 91+ days delinquent 7 to 12 months ago.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Too many enquiries.";
				}
			}
			else if(code.equalsIgnoreCase("13"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Low average trade age.";
				}
				else if(scoreName.equalsIgnoreCase("PLSCORE"))
				{
					scoreFactor="Low average trade age.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Too many accounts with a balance.";
				}
			}
			else if(code.equalsIgnoreCase("14"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Presence of a tradeline 91+ days delinquent 13 or more months ago.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Length of time accounts have been established is too short.";
				}
			}
			else if(code.equalsIgnoreCase("15"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Presence of a minor delinquency 7 to 12 months ago.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Not enough debt experience.";
				}
			}
			else if(code.equalsIgnoreCase("16"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Presence of a severe delinquency 7 to 12 months ago.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Too many credit card accounts.";
				}
			}
			else if(code.equalsIgnoreCase("17"))
			{
				if(scoreName.equalsIgnoreCase("CIBILTUSCR"))
				{
					scoreFactor="Presence of a high number of enquiries.";
				}
				else  if(scoreName.equalsIgnoreCase("PLSCORE"))
				{
					scoreFactor="Presence of a high number of enquiries.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Presence of a high number of enquiries.";
				}
			}
			else if(code.equalsIgnoreCase("18"))
			{
				if(scoreName.equalsIgnoreCase("PLSCORE"))
				{
					scoreFactor="Over due amount too high";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Number of active trades with a balance too high in proportion to total trades.";
				}
			}
			else if(code.equalsIgnoreCase("19"))
			{
				if(scoreName.equalsIgnoreCase("PLSCORE"))
				{
					scoreFactor="Not enough available credit.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Too much change of indebtedness on credit cards over the past 24 months.";
				}
			}
			else if(code.equalsIgnoreCase("20"))
			{
				if(scoreName.equalsIgnoreCase("PLSCORE"))
				{
					scoreFactor="Too few satisfactory bankcard accounts.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Credit card balance too high.";
				}
			}
			else if(code.equalsIgnoreCase("21"))
			{
				if(scoreName.equalsIgnoreCase("PLSCORE"))
				{
					scoreFactor="Total balance of delinquencies is too high.";
				}
				else  if(scoreName.equalsIgnoreCase("CIBILTUSC2"))
				{
					scoreFactor="Proportion of delinquent trades too high.";
				}
			}
			else if(code.equalsIgnoreCase("22"))
			{
				if(scoreName.equalsIgnoreCase("PLSCORE"))
				{
					scoreFactor="Presence of delinquency.";
				}
			}
			else if(code.equalsIgnoreCase("23"))
			{
				if(scoreName.equalsIgnoreCase("PLSCORE"))
				{
					scoreFactor="Total high credit of delinquencies is too high";
				}
			}
			else if(code.equalsIgnoreCase("24"))
			{
				if(scoreName.equalsIgnoreCase("PLSCORE"))
				{
					scoreFactor="Presence of a minor delinquency on personal loan";
				}
			}
		}
		return scoreFactor;
	}

	public static String getSubString(int start, int end, String str)
	{
		if(str!=null && start<=end && str.length()>=end)
		{
			return str.substring(start,end);
		}
		return "";
	}

	public static String getStringOfStringInt(String intStr)
	{
		try
		{
			return Integer.parseInt(intStr.trim())+"";
		}
		catch(Exception ex)
		{
			return intStr;
		}
	}

	public static void main (String ...arr)
	{
		//String str ="999999999999999";
		String str ="9999999999999999";
		double d = 0;
		DecimalFormat twoDForm = new DecimalFormat(",##");
		DecimalFormat twoDForm3 = new DecimalFormat(",###");
		DecimalFormat twoDForm1 = new DecimalFormat("000");
		try
		{
			if((str == null) || (str.equals("")) || (str.equals("")))
			{
				d  = 0;
			}
			else
				d = Double.parseDouble(str);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}

		if(d>999999999999999d)
		{
//			System.out.println(str);
		}


		if(d < 1000) {
//			System.out.println(twoDForm3.format(d));
			//	            return format("###", value);
		} else {
			double hundreds = d % 1000;
			long other = (long) (d / 1000);
//			System.out.println(twoDForm.format(other)+','+twoDForm1.format(hundreds));
			//	            return format(",##", other) + ',' + format("000", hundreds);
		}


//		System.out.println(twoDForm3.format(d));
	}
	
	public static String getApndxCStateCodeMAP(String stateCode)
	{
		Map<String,String> map=new HashMap<String,String>();
		map.put("01","Jammu & Kashmir");
		map.put("02","Himachal Pradesh");
		map.put("03","Punjab");
		map.put("04","Chandigarh");
		map.put("05","Uttaranchal");
		map.put("06","Haryana");
		map.put("07","Delhi");
		map.put("08","Rajasthan");
		map.put("09","Uttar Pradesh");
		map.put("10","Bihar");
		map.put("11","Sikkim");
		map.put("12","Arunachal Pradesh");
		map.put("13","Nagaland");
		map.put("14","Manipur");
		map.put("15","Mizoram");
		map.put("16","Tripura");
		map.put("17","Meghalaya");
		map.put("18","Assam");
		map.put("19","West Bengal");
		map.put("20","Jharkhand");
		map.put("21","Orissa");
		map.put("22","Chhattisgarh");
		map.put("23","Madhya Pradesh");
		map.put("24","Gujarat");
		map.put("25","Daman & Diu");
		map.put("26","Dadra & Nagar Haveli");
		map.put("27","Maharashtra");
		map.put("28","Andhra Pradesh");
		map.put("29","Karnataka");
		map.put("30","Goa");
		map.put("31","Lakshadweep");
		map.put("32","Kerala");
		map.put("33","Tamil Nadu");
		map.put("34","Pondicherry");
		map.put("35","Andaman & Nicobar Islands");
		map.put("36","Telangana");
		map.put("99","APO Address");
		
		String state="";
		try
		{
			state = map.get(stateCode);
		}
		catch(Exception ex)
		{
			state="NA";
		}
		return state;
	}
}
