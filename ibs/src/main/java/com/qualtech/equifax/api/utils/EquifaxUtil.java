package com.qualtech.equifax.api.utils;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.axis.encoding.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.qualtech.api.db.PropertyFile;
import com.qualtech.equifax.api.common.dto.Address;
import com.qualtech.equifax.api.common.dto.Phone;
import com.qualtech.equifax.api.request.EquifaxApiRequest;
import com.qualtech.equifax.api.request.EquifaxRequest;
import com.qualtech.equifax.api.response.EquifaxCriffResponsePayload;
@Component
public class EquifaxUtil implements EquifaxUtilInt
{
	private static Logger logger = Logger.getLogger(EquifaxUtil.class);
	//private static ResourceBundle env = PropertyFile.getInstance().getResourceBundel();
	@Autowired PropertyFile env;
	public  String  convertHtmlToPDF(String htmlData,String fileName, EquifaxCriffResponsePayload equifaxresponsepayload)
	{
		
		String uniqueId = UniqueId.getUniqueId();
		fileName+=uniqueId;
		String returnString="";
		String filePath="";
		try 
		{
			try
			{
				filePath=env.getString("com.qc.equifax.pdf.savePath")+fileName+".pdf";
				OutputStream osfile = new FileOutputStream(new File(filePath));
				Document document = new Document();
				PdfWriter writer = PdfWriter.getInstance(document, osfile);
				document.open();
				try 
				{
				    //Image img = Image.getInstance(env.getString("com.qc.context.path")+File.separator+"images"+File.separator+"qualtech.jpg");
					Image img = Image.getInstance(env.getString("com.qc.equifax.pdf.savePath")+"qc.png");
					img.scalePercent(40);
					img.setAbsolutePosition(35,800);
					document.add(img);
				}
				catch (Exception e) 
				{
					logger.error("Error while setting qc logo : "+e);
				}
				logger.info("HTML Data : "+htmlData);
				InputStream is = new ByteArrayInputStream(htmlData.getBytes());
				//XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
				XMLWorkerHelper worker = XMLWorkerHelper.getInstance();
				String cssFile="<style type=\"text/css\"> @media print { 	table { 		page-break-after: auto; 		-webkit-print-color-adjust: exact; 	} 	thead { 		display: table-header-group; 	} 	tfoot { 		display: table-footer-group; 	} 	body { 		margin-top: 10px; 		margin-bottom: 10px; 		margin-right: 25px; 		margin-left: 30px; 	} }  .shading { 	background-color: #1675a5; }  .box { 	background: #FFFFFF; 	border-style: solid; 	border-width: thin; 	border-color: #FFFFFF; 	border-collapse: collapse; 	text-align: left; 	-moz-box-shadow: 0px 0px 30px #DADADA; 	-webkit-box-shadow: 0px 0px 30px #DADADA; 	box-shadow: 0px 0px 30px #DADADA; }  .box1 { 	background: #FFFFFF; 	border-style: solid; 	border-width: 0px; 	border-collapse: collapse; 	text-align: left; }  .tabStyle { 	background: #FFFFFF; 	border-style: inset; 	border-width: thin; 	border-color: #333; 	border-collapse: collapse; }  .rowStyle { 	background: #FFFFFF; 	border-style: solid; 	border-width: thin; 	border-color: grey; 	border-collapse: collapse; }  .box1 tr:nt-child(even) { 	background-color: white; }  .box1 tr:nth-child(odd) { 	background-color: #e5e5e5; }  .style14 { 	font-size: 2px; }  .summarytable { 	background: #FFFFFF; 	border-style: solid; 	border-width: 0px; 	border-collapse: collapse; 	text-align: left; 	border-left: none; 	border-right: none; }  .reportHead { 	font-family: Arial; 	font-size: 24px; 	color: #1675a5; font-weight:  normal; 	text-align: left; }  .dataHead { 	font-family: Arial; 	font-size: 12px; 	font-weight: normal; 	color: #464646; 	text-align: right; 	text-indent: 5px; }  .mainHeader { 	font-family: Arial; 	font-size: 13px; 	color: #1675a5; 	background: #fff;; 	text-align: left; 	font-weight: normal; 	padding-bottom: 3px; 	padding: 5px 0; }  hr.mainHeader { 	padding: 0; 	background: #1c82d0; 	height: 2px; 	border: none; }  .subHeader { 	font-family: Arial; 	font-size: 13px; 	color: #1675a5; text-align:  left; 	border-width: thin; 	border-collapse: collapse; 	border-bottom: 1px solid #A7CBE3; 	border-left: 0px; 	border-right: 0px; 	border-top: 0px; 	background: #FFFFFF; 	text-indent: 5px; 	font-weight: normal; }  .subHeader1 { 	font-family: Arial; 	font-size: 13px; 	color: #1675a5; border-width:  thin; 	border-collapse: collapse; 	border-bottom: 1px solid #A7CBE3; 	border-left: 0px; 	border-right: 0px; 	border-top: 0px; 	background: #FFFFFF; 	text-indent: 5px; 	font-weight: normal; }  .dataHeaderNone { 	font-family: Arial; 	font-size: 14px; 	color: #1675a5; 	font-weight: normal; 	text-align: center; 	text-indent: 5px; 	white-space: nowrap; 	height: 23; 	valign: middle }  .subHeader2 { 	font-family: Arial; 	border-collapse: collapse; 	border-bottom: 0px; 	border-left: 1px solid #ffffff; 	border-right: 0px; 	border-top: 1px solid #ffffff; 	background: #FFFFFF; 	text-indent: 5px; 	font-weight: normal; 	white-space: nowrap; }  /*.dataHeader { 	font-family: Arial; 	font-size: 16px; 	color: #1675a3; 	font-weight: normal; 	text-align: left; 	text-indent: 5px; 	white-space: nowrap; 	padding-top: 2px; }*/  .dataHeaderScore { 	font-family: Arial; 	font-size: 12px; 	color: #464646; 	font-weight: normal; 	text-align: left; 	text-indent: 5px; 	white-space: nowrap; 	padding-top: 2px; }  .dataValueValue { 	font-family: Arial; 	font-size: 25px; 	font-weight: normal; 	color: #464646; 	text-align: left; 	padding-left: 7px; 	padding-top: 1px; }  .dataValuePerform { 	font-family: Arial; 	font-size: 12px; 	font-weight: normal; 	color: #464646; 	text-align: left; 	padding-left: 7px; 	padding-top: 1px; }  .dataValuePerform2 { 	border-collapse: separate; 	Color: #464646; 	font-family: Arial; 	font-size: 12px; 	font-weight: 280; }  .dataHeadern { 	font-family: Arial; 	font-size: 13px; 	color: #1675a5; font-weight:  normal; 	text-align: left; 	text-indent: 5px; 	padding-top: 2px; }  /*.dataValue { 	font-family: Arial; 	font-size: 14px; 	font-weight: normal; 	color: #464646; 	text-align: left; 	padding-left: 0; 	padding-top: 1px; }*/  .dataAmtValue { 	font-family: Arial; 	font-size: 14px; 	font-weight: normal; 	color: #464646; 	text-align: right; 	padding-right: 7px; 	padding-top: 1px; }  .dataHeader1 { 	font-family: Arial; 	font-size: 12px; 	color: #1675a5; 	font-weight: normal; 	text-align: left; 	text-indent: 5px; }  .dataValue1 { 	font-family: Arial; 	font-size: 12px; 	font-weight: normal; 	color: #464646; 	text-align: left; 	text-indent: 5px; }  .mainAccHeader { 	font-family: Arial; 	font-size: 13px; 	color: #FFFFFF; 	background: #1675a5; font-weight:  normal; }  .AccHeader { 	font-family: Arial; 	font-size: 13px; 	color: #1675a5; 	font-weight: normal; 	text-indent: 5px; }  /*.subAccHeader { 	font-family: Arial; 	font-size: 13px; 	    color: #ffffff;     background: #1675a5; 	font-weight: normal; 	border-width: thin; 	border-bottom: 1px solid #A7CBE3; 	border-left: 1px solid #A7CBE3; 	border-right: 1px solid #A7CBE3; 	border-top: 1px solid #A7CBE3; }*/ .AccValue { 	font-family: Arial; 	font-size: 14px; 	font-weight: normal; 	color: #464646; 	text-indent: 5px; }  /*.AccValue1 { 	font-family: Arial; 	font-size: 12px; 	font-weight: normal; 	color: #464646; 	text-indent: 5px; 	border-width: thin; 	border-bottom: 1px solid #A7CBE3; 	border-left: 1px solid #A7CBE3; 	border-right: 1px solid #A7CBE3; 	border-top: 1px solid #A7CBE3; }*/ .AccSummaryTab { 	border-width: thin; 	border-collapse: collapse; 	border-left: 1px solid #A7CBE3; 	border-right: 1px solid #A7CBE3; 	border-top: 1px solid #A7CBE3; 	border-bottom: 0px; 	text-indent: 5px; }  .disclaimerValue { 	font-family: Arial; 	font-size: 12px; 	font-weight: 500; 	color: grey; }  .infoValue { 	font-family: Arial; 	font-size: 12px; 	font-weight: 500; 	color: grey; 	padding-right: 15px; 	font-style: normal; }  .maroonFields { 	color: Maroon; 	font-family: Arial; 	font-size: 15px; 	font-weight: normal; }  /*.AccValueComm2 { 	font-family: Arial; 	font-size: 12px; 	font-weight: normal; 	color: #464646; 	text-indent: 5px; 	border-width: thin; 	border-bottom: 1px solid #A7CBE3; 	border-left: 1px solid #A7CBE3; 	border-right: 1px solid #A7CBE3; 	border-top: 1px solid #A7CBE3; }*/ .AccValue2 { 	font-family: Arial; 	font-size: 12px; 	font-weight: normal; 	color: #464646; 	text-indent: 5px; 	border-width: thin; 	border-bottom: 1px solid #A7CBE3; 	border-left: 1px solid #A7CBE3; 	border-right: 1px solid #A7CBE3; 	border-top: 1px solid #A7CBE3; }  .container { 	/* this will give container dimension, because floated child nodes don't give any */ 	/* if your child nodes are inline-blocked, then you don't have to set it */ 	overflow: auto; }  .container .headActive { 	/* float your elements or inline-block them to display side by side */ 	float: left; /* these are height and width dimensions of your header */ 	height: 10em; 	width: 1.5em; 	/* set to hidden so when there's too much vertical text it will be clipped. */ 	overflow: hidden; 	/* these are not relevant and are here to better see the elements */ 	background: #ffe1dc; 	color: #be0000; 	margin-right: 1px; 	font-family: segoe ui; 	font-weight: normal; }  .container .headActive .vertActive { 	/* line height should be equal to header width so text will be middle aligned */ 	line-height: 1.5em; 	/* setting background may yield better results in IE text clear type rendering */ 	background: #ffe1dc; 	color: #be0000; 	display: block; /* this will prevent it from wrapping too much text */ 	white-space: nowrap; /* so it stays off the edge */ 	padding-left: 3px; 	font-family: segoe ui; 	font-weight: normal; /* CSS3 specific totation code */ 	/* translate should have the same negative dimension as head height */ 	transform: rotate(-270deg) translate(1em, 0); 	transform-origin: -5px 30px; 	-moz-transform: rotate(-270deg) translate(1em, 0); 	-moz-transform-origin: -5px 30px; 	-webkit-transform: rotate(-270deg) translate(1em, 0); 	-webkit-transform-origin: -5px 30px; 	-ms-transform-origin: none; 	-ms-transform: none; 	-ms-writing-mode: tb-rl; 	*writing-mode: tb-rl; }  .container .headClosed { 	/* float your elements or inline-block them to display side by side */ 	float: left; /* these are height and width dimensions of your header */ 	height: 10em; 	width: 1.5em; 	/* set to hidden so when there's too much vertical text it will be clipped. */ 	overflow: hidden; 	/* these are not relevant and are here to better see the elements */ 	background: #e1f0be; 	color: #415a05; 	margin-right: 1px; 	font-family: segoe ui; 	font-weight: normal; }  .container .headClosed .vertClosed { 	/* line height should be equal to header width so text will be middle aligned */ 	line-height: 1.5em; 	/* setting background may yield better results in IE text clear type rendering */ 	background: #ffe1dc; 	color: #415a05; 	display: block; /* this will prevent it from wrapping too much text */ 	white-space: nowrap; /* so it stays off the edge */ 	padding-left: 3px; 	font-family: segoe ui; 	font-weight: normal; /* CSS3 specific totation code */ 	/* translate should have the same negative dimension as head height */ 	transform: rotate(-270deg) translate(1em, 0); 	transform-origin: -5px 30px; 	-moz-transform: rotate(-270deg) translate(1em, 0); 	-moz-transform-origin: -5px 30px; 	-webkit-transform: rotate(-270deg) translate(1em, 0); 	-webkit-transform-origin: -5px 30px; 	-ms-transform-origin: none; 	-ms-transform: none; 	-ms-writing-mode: tb-rl; 	*writing-mode: tb-rl; }  .infoValueNote { 	font-family: Arial; 	font-size: 12px; 	font-weight: 500; 	color: grey; 	padding-right: 15px; 	font-style: normal; }  .grey-bg {background: #f0f2f4;} .subHeading {color: #333; font-size: 13px;} .italic-font {font-style: italic;} .fontWeight {font-weight: normal;} </style>  ";
				worker.parseXHtml(writer, document, is,new ByteArrayInputStream(cssFile.getBytes()));
				
				document.close();
				osfile.flush();
				osfile.close();
			}
			catch (Exception e)
			{
				logger.info("We are in Exception : "+e);
			}
			catch (Throwable t)
			{
				logger.info("We are in Throwable : "+t);
			}

			////////////////////////////////////////////////////   
			FileInputStream fis=new FileInputStream(new File(filePath));
			/*IOUtils.toByteArray(fis);*/
			returnString=Base64.encode(IOUtils.toByteArray(fis));
			equifaxresponsepayload.setPdfPath(filePath);
			fis.close();
		} 
		catch (Exception e) 
		{
			logger.error("There is error while converting html to pdf"+e);
		}
		finally
		{
			try
			{
				//if(file!=null)
				//file.delete();
			}
			catch (Exception ex) 
			{
				logger.error("ErrorInfo While Deleting Temp File ::",ex);
			}
		}
		return returnString;
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

	public static String responseDataForBlankPdf(EquifaxApiRequest equifaxapirequest,String reportno)
	{
		
		EquifaxRequest req = equifaxapirequest.getPayload().getTransaction().get(0);
		List<com.qualtech.equifax.api.common.dto.UniqueId> ids=req.getIds();
//		List<Relation> relations=req.getRelations();
		List<Phone> phones=req.getPhones();
		List<Address> addresses=req.getAddresses();
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
	    String strTime = sdf.format(cal.getTime());
	    sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String strDate = sdf.format(cal.getTime());
	    
		StringBuilder sb = new StringBuilder();
		sb.append("  {	 ");
		sb.append("    \"header\": {	 ");
		sb.append("      \"msgVersion\": \"\",	 ");
		sb.append("      \"appId\": \"\",	 ");
		sb.append("      \"correlationId\": \"BlankPdfcreation\"	 ");
		sb.append("    },	 ");
		sb.append("    \"payload\": {	 ");
		sb.append("      \"transaction\": {	 ");
		sb.append("        \"time\": \"").append(strTime).append("\",	 ");
		sb.append("        \"accountsummary\": {},	 ");
		sb.append("        \"accountdetails\": {},	 ");
		sb.append("        \"incomedetails\": [],	 ");
		sb.append("        \"idandcontactinfo\": {},	 ");
		sb.append("        \"disclaimer\": \"This report is to be used subject to and in compliance with the\\t\\t\\tMembership agreement entered into between the Member/Specified User\\t\\t\\tand Equifax Credit Information Services Private Limited (\\\"ECIS\\\"), in\\t\\t\\tthe case of Members/Specified Users of ECIS. In other cases, the use\\t\\t\\tof this report is governed by the terms and conditions of ECIS,\\t\\t\\tcontained in the Application form submitted by the customer/user.\\t\\t\\tThe information contained in this report is derived from various\\t\\t\\tMembers/sources which are not controlled by ECIS. ECIS provides this\\t\\t\\treport on a best effort basis and does not guarantee the timeliness,\\t\\t\\tcorrectness or completeness of the information contained\\t\\t\\ttherein,except as explicitly stated in the Membership agreement/terms\\t\\t\\tand conditions of ECIS, as the case may be.\",  ");
		sb.append("        \"inquiryrequestinfo\": {	 ");
		sb.append("          \"requestaccountdetails\": \"\",	 ");
		sb.append("          \"inquiryphones\": {	 ");
		sb.append("            \"inquiryphone\": [	 ");
		int i=1;
		for(Phone ph : phones)
		{
			sb.append("            {	 ");
			sb.append("              \"phonetype\": \""+ph.getType()+"\",	 ");
			sb.append("              \"seq\": "+i+",	 ");
			sb.append("              \"number\": "+ph.getTeleNo()+"	 ");
			sb.append("            }	 ,");
			i++;
		}
		i=1;
		sb.setLength(sb.length()-1);
		
		sb.append("           ]	 ");
		sb.append("          },	 ");
		
		sb.append("          \"inquirycommonaccountdetails\": {	 ");
		sb.append("            \"inquiryaccount\": {	 ");
		sb.append("              \"seq\": 1	 ");
		sb.append("            }	 ");
		sb.append("          },	 ");
	
		sb.append("          \"firstname\": \""+req.getfName()+"\",	 ");
		sb.append("          \"fullname\": \""+((req.getfName()+" "+req.getmName()).trim()+" "+ req.getlName()).trim()+"\",	 ");
		sb.append("          \"inquirypurpose\": \""+req.getInquiryPurpose()+"\",	 ");
		sb.append("          \"dob\": \""+req.getDob()+"\",	 ");
		sb.append("          \"mobilephone\": \"\",	 ");
		sb.append("          \"additionalsearchfield\": \"\",	 ");
		sb.append("          \"gender\": \"\",	 ");
		sb.append("          \"addrline1\": \"\",	 ");
		sb.append("          \"state\": \"\",	 ");
		sb.append("          \"postal\": \"\",	 ");
		
		for(com.qualtech.equifax.api.common.dto.UniqueId id : ids)
		{
			String idName = id.getIdName();
			
			if(idName!=null && (idName.startsWith("PAN")||idName.startsWith("pan")))
			{
				sb.append("          \"panid\": \""+id.getIdNo()+"\",	 ");
			}
			else if(idName!=null && (idName.startsWith("PASSPORT")||idName.startsWith("passport")))
			{
				sb.append("          \"passportid\": \""+id.getIdNo()+"\",	 ");
			}
			else if(idName!=null && (idName.startsWith("VOTERID")||idName.startsWith("voterid")))
			{
				sb.append("          \"voterid\": \""+id.getIdNo()+"\",	 ");
			}
			else if(idName!=null && (idName.startsWith("RATIONCARD")||idName.startsWith("rationcard")))
			{
				sb.append("          \"rationcard\": \""+id.getIdNo()+"\",	 ");
			}
			else if(idName!=null && (idName.startsWith("NATIONALID")||idName.startsWith("nationalid")))
			{
				sb.append("          \"nationalidcard\": \""+id.getIdNo()+"\",	 ");
			}
			else if(idName!=null && (idName.startsWith("DRIVERLICENSE")||idName.startsWith("driverlicense")))
			{
				sb.append("          \"driverlicense\": \""+id.getIdNo()+"\",	 ");
			}
		}
		
		sb.append("          \"familydetails\": {	 ");
		sb.append("            \"additionalnameinfo\": {	 ");
		sb.append("              \"seq\": 1,	 ");
		sb.append("              \"additionalnametype\": \"H\",	 ");
		sb.append("              \"additionalname\": \"DILIP\"	 ");
		sb.append("            }	 ");
		sb.append("          },	 ");
		
		sb.append("          \"inquiryaddresses\": {	 ");
		sb.append("            \"inquiryaddress\": [	 ");
		
		for(Address adr : addresses)
		{
			sb.append("           {	 ");
			sb.append("              \"postal\": "+adr.getPin()+",	 ");
			sb.append("              \"seq\": "+i+",	 ");
			sb.append("              \"state\": \""+adr.getState()+"\",	 ");
			sb.append("              \"addressline\": \""+adr.getAddress()+"\"	 ");
			sb.append("           }  	 ,");
			i++;
		}
		i=1;
		sb.setLength(sb.length()-1);
		
		sb.append("            ]	 ");
		
		sb.append("          }	 ");
		sb.append("        },	 ");
		sb.append("        \"enquiries\": [],	 ");
		sb.append("        \"date\": \"").append(strDate).append("\",	 ");
		sb.append("        \"reportorderno\": \""+reportno+"\"	 ");
		sb.append("      }	 ");
		sb.append("    }	 ");
		sb.append("  }	 ");

		return sb.toString();
	}
	
	public String blankPdfByteArray(EquifaxApiRequest equifaxapirequest,String servicetype, String reportno,String customerId,String memberNumber)
	{
		String pdfByteArr="";
		try
		{
			String url_temp=env.getString("com.qc.equifax.liveURL");
			if("PCS".equals(servicetype))
			{
				url_temp+="/equifaxPcrHtml";
			}
			else if("MCR".equals(servicetype))
			{
				url_temp+="/equifaxMfiHtml";
			}
			ObjectMapper  omapper=new ObjectMapper();
			String tempRequestJson=omapper.writeValueAsString(equifaxapirequest);
			String htmlData=new ConvertUrltoByteArray().getingByteArray1(url_temp,tempRequestJson,"requestJson",responseDataForBlankPdf(equifaxapirequest,reportno), "responseFromService",customerId,memberNumber);
			//pdfByteArr = convertHtmlToPDF(htmlData,"EquifaxReport"+servicetype);
		}
		catch (Exception e)
		{
			logger.error("Error while preparing blank PDF data : "+e);
		}
		
		return pdfByteArr;
	
	}
	
}
