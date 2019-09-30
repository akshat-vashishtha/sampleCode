package com.qualtech.equifax.api.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.StringTokenizer;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.qualtech.equifax.api.request.EquifaxApiRequest;
import com.qualtech.equifax.api.request.EquifaxRequest;
public class ConvertUrltoByteArray
{
	BufferedReader breader = null;

	private static Logger logger = Logger.getLogger(ConvertUrltoByteArray.class);
	//private ResourceBundle env = PropertyFile.getInstance().getResourceBundel();
	//@Autowired PropertyFile env;
	
	public static void main(String[] args) {
		String reqJson="{ \"header\": { \"msgVersion\": \"\", \"appId\": \"\", \"correlationId\": \"EquifaxDemo11July2017PCS\", \"userId\": \"\", \"password\": \"\", \"rollId\": \"\", \"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTUwMDU5MzEwNn0.UuyjkgVYa4aL9JP_XLLB4bXzosGIsawzxyOUcSlhGDKHxtTWsgohWEindPk8zfS20-osCeBeQsv7nk3iApNp6Q\" }, \"payload\": { \"transaction\": [ { \"fName\": \"NEERAJ KUMAR\", \"mName\": \"\", \"lName\": \"SHARMA\", \"dob\": \"1977-02-11\", \"requestType\": \"PCS\", \"inquiryPurpose\": \"02\", \"keyPersonName\": \"Vasu Kumar\", \"keyPersonType\": \"Father\", \"ids\": [ { \"idName\": \"Pan\", \"idNo\": \"ATCPS9170Q\" } ], \"relations\": [ { \"name\": \"Vasu Kumar\", \"relation\": \"Father\" } ], \"phones\": [ { \"teleNo\": \"9893688111\", \"type\": \"Mobile\" } ], \"addresses\": [ { \"city\": \"BANGALORE\", \"state\": \"MP\", \"pin\": \"451666\", \"address\": \"HOUSE NO 296 WARD NO 8 NIWALI ROAD SENDHWA BADWANI DIST MADHYA PRADESH\", \"type\": \"D01\" } ] } ] } }";
		getHtmlData(reqJson);
	}
	
	public static  String getHtmlData(String requestjson){
		
		ObjectMapper  om=new ObjectMapper();
		  FileWriter fw=null;
		try {
			EquifaxApiRequest equifaxapirequest=om.readValue(requestjson, EquifaxApiRequest.class);
			EquifaxRequest request=equifaxapirequest.getPayload().getTransaction().get(0);
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		    String strTime = sdf.format(cal.getTime());
		    sdf = new SimpleDateFormat("yyyy-MM-dd");
		    String strDate = sdf.format(cal.getTime());
			
		StringBuilder htmlData=new StringBuilder();
		
	
						
		htmlData=new StringBuilder();
		htmlData.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
		htmlData.append("");
		htmlData.append("");
		htmlData.append("<html>");
		htmlData.append("<head>");
		htmlData.append("<link href='https://fonts.googleapis.com/css?family=Stardos Stencil'");
		htmlData.append("	rel='stylesheet' />");
		htmlData.append("<meta http-equiv='Content-Type'");
		htmlData.append("	content='text/html; charset=windows-1252' />");
		htmlData.append("<title>Equifax PreScreen Credit Report</title>");
		htmlData.append("");
		htmlData.append("<style type='text/css'>");
		htmlData.append("@media print {");
		htmlData.append("	table {");
		htmlData.append("		page-break-after: auto;");
		htmlData.append("		-webkit-print-color-adjust: exact;");
		htmlData.append("	}");
		htmlData.append("	thead {");
		htmlData.append("		display: table-header-group;");
		htmlData.append("	}");
		htmlData.append("	tfoot {");
		htmlData.append("		display: table-footer-group;");
		htmlData.append("	}");
		htmlData.append("	body {");
		htmlData.append("		margin-top: 10px;");
		htmlData.append("		margin-bottom: 10px;");
		htmlData.append("		margin-right: 25px;");
		htmlData.append("		margin-left: 30px;");
		htmlData.append("	}");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".shading {");
		htmlData.append("	background-color: #1675a5;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".box {");
		htmlData.append("	background: #FFFFFF;");
		htmlData.append("	border-style: solid;");
		htmlData.append("	border-width: thin;");
		htmlData.append("	border-color: #FFFFFF;");
		htmlData.append("	border-collapse: collapse;");
		htmlData.append("	text-align: left;");
		htmlData.append("	-moz-box-shadow: 0px 0px 30px #DADADA;");
		htmlData.append("	-webkit-box-shadow: 0px 0px 30px #DADADA;");
		htmlData.append("	box-shadow: 0px 0px 30px #DADADA;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".box1 {");
		htmlData.append("	background: #FFFFFF;");
		htmlData.append("	border-style: solid;");
		htmlData.append("	border-width: 0px;");
		htmlData.append("	border-collapse: collapse;");
		htmlData.append("	text-align: left;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".tabStyle {");
		htmlData.append("	background: #FFFFFF;");
		htmlData.append("	border-style: inset;");
		htmlData.append("	border-width: thin;");
		htmlData.append("	border-color: #333;");
		htmlData.append("	border-collapse: collapse;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".rowStyle {");
		htmlData.append("	background: #FFFFFF;");
		htmlData.append("	border-style: solid;");
		htmlData.append("	border-width: thin;");
		htmlData.append("	border-color: grey;");
		htmlData.append("	border-collapse: collapse;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".box1 tr:nt-child(even) {");
		htmlData.append("	background-color: white;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".box1 tr:nth-child(odd) {");
		htmlData.append("	background-color: #e5e5e5;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".style14 {");
		htmlData.append("	font-size: 2px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".summarytable {");
		htmlData.append("	background: #FFFFFF;");
		htmlData.append("	border-style: solid;");
		htmlData.append("	border-width: 0px;");
		htmlData.append("	border-collapse: collapse;");
		htmlData.append("	text-align: left;");
		htmlData.append("	border-left: none;");
		htmlData.append("	border-right: none;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".reportHead {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 24px;");
		htmlData.append("	color: #1675a5;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	text-align: left;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".dataHead {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 12px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	color: #464646;");
		htmlData.append("	text-align: right;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".mainHeader {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 13px;");
		htmlData.append("	color: #1675a5;");
		htmlData.append("	background: #fff;;");
		htmlData.append("	text-align: left;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	padding-bottom: 3px;");
		htmlData.append("	padding: 5px 0;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append("hr.mainHeader {");
		htmlData.append("	padding: 0;");
		htmlData.append("	background: #1c82d0;");
		htmlData.append("	height: 2px;");
		htmlData.append("	border: none;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".subHeader {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 13px;");
		htmlData.append("	color: #1675a5;");
		htmlData.append("	text-align: left;");
		htmlData.append("	border-width: thin;");
		htmlData.append("	border-collapse: collapse;");
		htmlData.append("	border-bottom: 1px solid #A7CBE3;");
		htmlData.append("	border-left: 0px;");
		htmlData.append("	border-right: 0px;");
		htmlData.append("	border-top: 0px;");
		htmlData.append("	background: #FFFFFF;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".subHeader1 {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 13px;");
		htmlData.append("	color: #1675a5;");
		htmlData.append("	border-width: thin;");
		htmlData.append("	border-collapse: collapse;");
		htmlData.append("	border-bottom: 1px solid #A7CBE3;");
		htmlData.append("	border-left: 0px;");
		htmlData.append("	border-right: 0px;");
		htmlData.append("	border-top: 0px;");
		htmlData.append("	background: #FFFFFF;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".dataHeaderNone {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 14px;");
		htmlData.append("	color: #1675a5;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	text-align: center;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("	white-space: nowrap;");
		htmlData.append("	height: 23;");
		htmlData.append("	valign: middle");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".subHeader2 {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	border-collapse: collapse;");
		htmlData.append("	border-bottom: 0px;");
		htmlData.append("	border-left: 1px solid #ffffff;");
		htmlData.append("	border-right: 0px;");
		htmlData.append("	border-top: 1px solid #ffffff;");
		htmlData.append("	background: #FFFFFF;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	white-space: nowrap;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".dataHeader {");
		htmlData.append("	background: none;");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 12px;");
		htmlData.append("	color: #1675a5;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	text-align: left;");
		htmlData.append("	/*text-indent: 5px;*/");
		htmlData.append("	white-space: nowrap;");
		htmlData.append("	padding: 5px 0;");
		htmlData.append("	text-transform: capitalize;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".grey-label {color:#666;}");
		htmlData.append("");
		htmlData.append(".dataHeaderScore {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 12px;");
		htmlData.append("	color: #464646;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	text-align: left;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("	white-space: nowrap;");
		htmlData.append("	padding-top: 2px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".dataValueValue {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 25px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	color: #464646;");
		htmlData.append("	text-align: left;");
		htmlData.append("	padding-left: 7px;");
		htmlData.append("	padding-top: 1px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".dataValuePerform {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 12px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	color: #464646;");
		htmlData.append("	text-align: left;");
		htmlData.append("	padding-left: 7px;");
		htmlData.append("	padding-top: 1px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".dataValuePerform2 {");
		htmlData.append("	border-collapse: separate;");
		htmlData.append("	Color: #464646;");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 12px;");
		htmlData.append("	font-weight: 280;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".dataHeadern {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 13px;");
		htmlData.append("	color: #1675a5;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	text-align: left;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("	padding-top: 2px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".dataValue {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 11px;");
		htmlData.append("	font-weight: 400;");
		htmlData.append("	color: #666;");
		htmlData.append("	text-align: left;");
		htmlData.append("	padding: 5px 0;");
		htmlData.append("	text-transform: capitalize;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".dataAmtValue {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 14px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	color: #464646;");
		htmlData.append("	text-align: right;");
		htmlData.append("	padding-right: 7px;");
		htmlData.append("	padding-top: 1px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".dataHeader1 {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 12px;");
		htmlData.append("	color: #1675a5;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	text-align: left;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".dataValue1 {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 12px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	color: #464646;");
		htmlData.append("	text-align: left;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".mainAccHeader {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 13px;");
		htmlData.append("	color: #FFFFFF;");
		htmlData.append("	background: #1675a5;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".AccHeader {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 13px;");
		htmlData.append("	color: #1675a5;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append("/*.subAccHeader {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 13px;");
		htmlData.append("	    color: #ffffff;");
		htmlData.append("    background: #1675a5;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	border-width: thin;");
		htmlData.append("	border-bottom: 1px solid #A7CBE3;");
		htmlData.append("	border-left: 1px solid #A7CBE3;");
		htmlData.append("	border-right: 1px solid #A7CBE3;");
		htmlData.append("	border-top: 1px solid #A7CBE3;");
		htmlData.append("}*/");
		htmlData.append(".AccValue {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 14px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	color: #464646;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append("/*.AccValue1 {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 12px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	color: #464646;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("	border-width: thin;");
		htmlData.append("	border-bottom: 1px solid #A7CBE3;");
		htmlData.append("	border-left: 1px solid #A7CBE3;");
		htmlData.append("	border-right: 1px solid #A7CBE3;");
		htmlData.append("	border-top: 1px solid #A7CBE3;");
		htmlData.append("}*/");
		htmlData.append(".AccSummaryTab {");
		htmlData.append("	border-width: thin;");
		htmlData.append("	border-collapse: collapse;");
		htmlData.append("	border-left: 1px solid #A7CBE3;");
		htmlData.append("	border-right: 1px solid #A7CBE3;");
		htmlData.append("	border-top: 1px solid #A7CBE3;");
		htmlData.append("	border-bottom: 0px;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".disclaimerValue {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 11px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	color: #999;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".infoValue {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 12px;");
		htmlData.append("	font-weight: 500;");
		htmlData.append("	color: grey;");
		htmlData.append("	padding-right: 15px;");
		htmlData.append("	font-style: normal;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".maroonFields {");
		htmlData.append("	color: Maroon;");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 15px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append("/*.AccValueComm2 {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 12px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	color: #464646;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("	border-width: thin;");
		htmlData.append("	border-bottom: 1px solid #A7CBE3;");
		htmlData.append("	border-left: 1px solid #A7CBE3;");
		htmlData.append("	border-right: 1px solid #A7CBE3;");
		htmlData.append("	border-top: 1px solid #A7CBE3;");
		htmlData.append("}*/");
		htmlData.append(".AccValue2 {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 12px;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	color: #464646;");
		htmlData.append("	text-indent: 5px;");
		htmlData.append("	border-width: thin;");
		htmlData.append("	border-bottom: 1px solid #A7CBE3;");
		htmlData.append("	border-left: 1px solid #A7CBE3;");
		htmlData.append("	border-right: 1px solid #A7CBE3;");
		htmlData.append("	border-top: 1px solid #A7CBE3;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".container {");
		htmlData.append("	/* this will give container dimension, because floated child nodes don't give any */");
		htmlData.append("	/* if your child nodes are inline-blocked, then you don't have to set it */");
		htmlData.append("	overflow: auto;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".container .headActive {");
		htmlData.append("	/* float your elements or inline-block them to display side by side */");
		htmlData.append("	float: left; /* these are height and width dimensions of your header */");
		htmlData.append("	height: 10em;");
		htmlData.append("	width: 1.5em;");
		htmlData.append("	/* set to hidden so when there's too much vertical text it will be clipped. */");
		htmlData.append("	overflow: hidden;");
		htmlData.append("	/* these are not relevant and are here to better see the elements */");
		htmlData.append("	background: #ffe1dc;");
		htmlData.append("	color: #be0000;");
		htmlData.append("	margin-right: 1px;");
		htmlData.append("	font-family: segoe ui;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".container .headActive .vertActive {");
		htmlData.append("	/* line height should be equal to header width so text will be middle aligned */");
		htmlData.append("	line-height: 1.5em;");
		htmlData.append("	/* setting background may yield better results in IE text clear type rendering */");
		htmlData.append("	background: #ffe1dc;");
		htmlData.append("	color: #be0000;");
		htmlData.append("	display: block; /* this will prevent it from wrapping too much text */");
		htmlData.append("	white-space: nowrap; /* so it stays off the edge */");
		htmlData.append("	padding-left: 3px;");
		htmlData.append("	font-family: segoe ui;");
		htmlData.append("	font-weight: normal; /* CSS3 specific totation code */");
		htmlData.append("	/* translate should have the same negative dimension as head height */");
		htmlData.append("	transform: rotate(-270deg) translate(1em, 0);");
		htmlData.append("	transform-origin: -5px 30px;");
		htmlData.append("	-moz-transform: rotate(-270deg) translate(1em, 0);");
		htmlData.append("	-moz-transform-origin: -5px 30px;");
		htmlData.append("	-webkit-transform: rotate(-270deg) translate(1em, 0);");
		htmlData.append("	-webkit-transform-origin: -5px 30px;");
		htmlData.append("	-ms-transform-origin: none;");
		htmlData.append("	-ms-transform: none;");
		htmlData.append("	-ms-writing-mode: tb-rl;");
		htmlData.append("	*writing-mode: tb-rl;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".container .headClosed {");
		htmlData.append("	/* float your elements or inline-block them to display side by side */");
		htmlData.append("	float: left; /* these are height and width dimensions of your header */");
		htmlData.append("	height: 10em;");
		htmlData.append("	width: 1.5em;");
		htmlData.append("	/* set to hidden so when there's too much vertical text it will be clipped. */");
		htmlData.append("	overflow: hidden;");
		htmlData.append("	/* these are not relevant and are here to better see the elements */");
		htmlData.append("	background: #e1f0be;");
		htmlData.append("	color: #415a05;");
		htmlData.append("	margin-right: 1px;");
		htmlData.append("	font-family: segoe ui;");
		htmlData.append("	font-weight: normal;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".container .headClosed .vertClosed {");
		htmlData.append("	/* line height should be equal to header width so text will be middle aligned */");
		htmlData.append("	line-height: 1.5em;");
		htmlData.append("	/* setting background may yield better results in IE text clear type rendering */");
		htmlData.append("	background: #ffe1dc;");
		htmlData.append("	color: #415a05;");
		htmlData.append("	display: block; /* this will prevent it from wrapping too much text */");
		htmlData.append("	white-space: nowrap; /* so it stays off the edge */");
		htmlData.append("	padding-left: 3px;");
		htmlData.append("	font-family: segoe ui;");
		htmlData.append("	font-weight: normal; /* CSS3 specific totation code */");
		htmlData.append("	/* translate should have the same negative dimension as head height */");
		htmlData.append("	transform: rotate(-270deg) translate(1em, 0);");
		htmlData.append("	transform-origin: -5px 30px;");
		htmlData.append("	-moz-transform: rotate(-270deg) translate(1em, 0);");
		htmlData.append("	-moz-transform-origin: -5px 30px;");
		htmlData.append("	-webkit-transform: rotate(-270deg) translate(1em, 0);");
		htmlData.append("	-webkit-transform-origin: -5px 30px;");
		htmlData.append("	-ms-transform-origin: none;");
		htmlData.append("	-ms-transform: none;");
		htmlData.append("	-ms-writing-mode: tb-rl;");
		htmlData.append("	*writing-mode: tb-rl;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".infoValueNote {");
		htmlData.append("	font-family: Arial;");
		htmlData.append("	font-size: 12px;");
		htmlData.append("	font-weight: 500;");
		htmlData.append("	color: grey;");
		htmlData.append("	padding-right: 15px;");
		htmlData.append("	font-style: normal;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".grey-bg {");
		htmlData.append("	background: #f0f2f4;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".subHeading {");
		htmlData.append("	color: #333;");
		htmlData.append("	font-size: 13px;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".italic-font {");
		htmlData.append("	font-style: italic;");
		htmlData.append("}");
		htmlData.append("");
		htmlData.append(".fontWeight {");
		htmlData.append("	font-weight: normal;");
		htmlData.append("	color:#333;");
		htmlData.append("}");
		htmlData.append("</style>");
		htmlData.append("");
		htmlData.append("</head>");
		htmlData.append("<body style='font-family: segoe ui semibold, arial, verdana;'>");
		htmlData.append("");
		htmlData.append("	<table class='box' align='center' border='0px' cellspacing='0'");
		htmlData.append("		width='100%'>");
		htmlData.append("		<thead>");
		htmlData.append("			<tr>");
		htmlData.append("				<td>");
		htmlData.append("					<table align='center' border='0' width='100%' cellspacing='0'>");
		htmlData.append("						<tbody>");
		htmlData.append("							<tr style='height: 10px'>");
		htmlData.append("								<td></td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td width='15%' style='padding-left: 10px;'><img");
		htmlData.append("									src='images/equifax-logo.png' alt='Equifax Services'");
		htmlData.append("									align='left' style='float: none; display: inline-block;' /></td>");
		htmlData.append("								<td align='center' width='70%' valign='top'>");
		htmlData.append("									<table border='0' cellspacing='0' style='width: 100%;'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td align='center' class='reportHead'");
		htmlData.append("													style='color: #333; text-align: center; font-size: 14px;'>");
		htmlData.append("													<b>Equifax Prescreen Credit Report</b></td>");
		htmlData.append("");
		htmlData.append("											</tr>");
		htmlData.append("											<tr valign='top'>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("								<td align='right' valign='top' width='15%'></td>");
		htmlData.append("							</tr>");
		htmlData.append("						</tbody>");
		htmlData.append("					</table>");
		htmlData.append("				</td>");
		htmlData.append("			</tr>");
		htmlData.append("		</thead>");
		htmlData.append("");
		htmlData.append("");
		htmlData.append("		<tbody>");
		htmlData.append("			<tr>");
		htmlData.append("				<td>");
		htmlData.append("					<table align='left' border='0' cellspacing='0' width='100%'>");
		htmlData.append("						<tbody>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='left' border='0' cellspacing='0' width='100%'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td");
		htmlData.append("													style='border-bottom: 2px solid #1675a5; color: #1675a5;'>");
		htmlData.append("													<table align='left' style='border-radius: 0px;' border='0'");
		htmlData.append("														width='100%;'>");
		htmlData.append("														<tbody>");
		htmlData.append("															<tr height='20'>");
		htmlData.append("																<td class='mainHeader'");
		htmlData.append("																	style='font-size: 14px; padding-left: 10px;'>Consumer");
		htmlData.append("																	CIR</td>");
		htmlData.append("															</tr>");
		htmlData.append("														</tbody>");
		htmlData.append("													</table>");
		htmlData.append("												</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='center' border='0' cellpadding='0'");
		htmlData.append("										cellspacing='0' width='100%'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td>");
		htmlData.append("													<table align='center' border='0' width='100%'>");
		htmlData.append("														<tbody>");
		htmlData.append("															<tr>");
		htmlData.append("																<td>");
		htmlData.append("																	<table border='0' width='100%'>");
		htmlData.append("																		<tbody>");
		htmlData.append("																			<tr style='background: #e5e5e5;'>");
		htmlData.append("																				<td align='left' width='144 px'");
		htmlData.append("																					style='font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;'>");
		htmlData.append("																					<span class='dataHeader'");
		htmlData.append("																					style='font-size: 12px; padding-left: 10px'>Consumer Name");
		htmlData.append("																						:</span> <span class='grey-label'>Neeraj Kumar</span>");//db
		htmlData.append("																				</td>");
		htmlData.append("																				<td align='left' width='144 px'");
		htmlData.append("																					style='font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;'>");
		htmlData.append("																					<span class='dataHeader'");
		htmlData.append("																					style='font-size: 12px; padding-left: 10px'>Date");
		htmlData.append("																						:</span> <span class='grey-label'>15-5-2018</span>");//db
		htmlData.append("																				</td>");
		htmlData.append("																			</tr>");
		htmlData.append("																			<tr style='background: #e5e5e5;'>");
		htmlData.append("																				<td align='left' width='171px'");
		htmlData.append("																					style='font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;'>");
		htmlData.append("																					<span class='dataHeader'");
		htmlData.append("																					style='font-size: 12px; padding-left: 10px'>Client");
		htmlData.append("																						Id :</span> <span class='grey-label'>21</span>");//db
		htmlData.append("																				</td>");
		htmlData.append("																				<td align='left' width='171px'");
		htmlData.append("																					style='font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;'>");
		htmlData.append("																					<span class='dataHeader'");
		htmlData.append("																					style='font-size: 12px; padding-left: 10px'>Time");
		htmlData.append("																						: </span><span class='grey-label'> <%try {%> <%=new JSONObject(request.getAttribute('responsetransaction') + '').get('time')%>");
		htmlData.append("																					<%} catch (Exception e) {logger.debug('We are in exception while getting key of time from responsetransaction' + e);} %></span>");
		htmlData.append("																				</td>");
		htmlData.append("																			</tr>");
		htmlData.append("																			<tr style='background: #e5e5e5;'>");
		htmlData.append("																				<td align='left' width='114px'");
		htmlData.append("																					style='font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;'>");
		htmlData.append("																					<span class='dataHeader'");
		htmlData.append("																					style='font-size: 12px; padding-left: 10px'>");
		htmlData.append("																						Reference Number :</span> <span class='grey-label'>999AA00007</span>");//db
		htmlData.append("																				</td>");
		htmlData.append("																				<td align='left' width='114px'");
		htmlData.append("																					style='font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;'>");
		htmlData.append("																					<span class='dataHeader'");
		htmlData.append("																					style='font-size: 12px; padding-left: 10px'></span>");
		htmlData.append("																					<span class='grey-label'></span>");
		htmlData.append("																				</td>");
		htmlData.append("																			</tr>");
		htmlData.append("																			<tr>");//////////////////////////////////////////////////////////****************************************************////////////////////////
		htmlData.append("																				<td height='20px'></td>");
		htmlData.append("																			</tr>");
		htmlData.append("																		</tbody>");
		htmlData.append("																	</table>");
		htmlData.append("");
		htmlData.append("																</td>");
		htmlData.append("															</tr>");
		htmlData.append("														</tbody>");
		htmlData.append("													</table>");
		htmlData.append("												</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("						</tbody>");
		htmlData.append("					</table>");
		htmlData.append("				</td>");
		htmlData.append("			</tr>");
		htmlData.append("");
		htmlData.append("			<tr>");
		htmlData.append("				<td>");
		htmlData.append("					<table align='center' border='0' cellspacing='0' width='100%'>");
		htmlData.append("						<tbody>");
		htmlData.append("");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table");
		htmlData.append("										style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("										align='left' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td height='15px'></td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr>");
		htmlData.append("												<td class='mainHeader' style='padding-left: 10px;'>Consumer");
		htmlData.append("													Score Details</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='center' border='0' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr style='height: 20px;'>");
		htmlData.append("												<td class='dataHeader' width='25%'");
		htmlData.append("													style='padding-left: 10px;'>Score</td>");
		htmlData.append("												<td class='dataHeader' width='75%'>Scoring Factors</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='left' border='0' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr style='background: #e5e5e5;'>");
		htmlData.append("												<td width='25%'");
		htmlData.append("													style='text-align: center; font-size: 40px; font-weight: 500;'");
		htmlData.append("													class='dataValue'>");
		htmlData.append("												</td>");
		htmlData.append("												<td class='dataValue' width='75%'>");
		htmlData.append("													<table width='100%' cellspacing='0'>");
		htmlData.append("														<tbody>");
		htmlData.append("															<tr>");
		htmlData.append("																<td class='dataValue'>");
	/*	htmlData.append("																	<%try {%> <span class='fontWeight'><%=i+1%>: </span> <%=scoreInfo.getDescription()%>");*/
		htmlData.append("																</td>");
		htmlData.append("															</tr>");
		htmlData.append("														</tbody>");
		htmlData.append("													</table>");
		htmlData.append("												</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("");
		htmlData.append("						</tbody>");
		htmlData.append("					</table>");
		htmlData.append("				</td>");
		htmlData.append("			</tr>");
		htmlData.append("");
		htmlData.append("			<tr>");
		htmlData.append("				<td>");
		htmlData.append("					<!-- Personal Info -->");
		htmlData.append("					<table");
		htmlData.append("						style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("						align='left' width='100%' cellspacing='0'>");
		htmlData.append("						<tbody>");
		htmlData.append("							<tr>");
		htmlData.append("								<td height='15px'></td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr height='20'>");
		htmlData.append("								<td class='mainHeader' style='padding-left: 10px;'>Personal");
		htmlData.append("									Information</td>");
		htmlData.append("							</tr>");
		htmlData.append("						</tbody>");
		htmlData.append("					</table>");
		htmlData.append("					<table width='100%' border='0' cellspacing='0'>");
		htmlData.append("						<tbody>");
		htmlData.append("							<tr>");
		htmlData.append("								<td class='dataHeader' style='padding-left: 10px;' width='30%'>Name</td>");
		htmlData.append("								<td class='dataHeader' width='30%'>Alias Name</td>");
		htmlData.append("								<td class='dataHeader' width='20%'>DOB</td>");
		htmlData.append("								<td class='dataHeader' width='20%'>Age</td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='background: #e5e5e5;'>");
		htmlData.append("								<td class='dataValue' style='padding-left: 10px;' width='30%'>");
		htmlData.append("									<span> Neeraj Kumar Sharma");
		htmlData.append("								</span>");
		htmlData.append("								</td>");
		htmlData.append("								<td class='dataValue' width='30%'><span></span></td>");
		htmlData.append("								<td class='dataValue' width='20%'><span>");
/*		htmlData.append("											try {");
		htmlData.append("											%> <%=request.getAttribute('dateofbirth')==null?'':(String)request.getAttribute('dateofbirth')%>");
		htmlData.append("										<%} catch (Exception ec) {} %>");*/
		htmlData.append("								</span></td>");
		htmlData.append("								<td class='dataValue' width='20%'><span>");/*
		htmlData.append("											try {");
		htmlData.append("											%> <%=request.getAttribute('age')==null?'':(String)request.getAttribute('age')%>");
		htmlData.append("										<%");
		htmlData.append("											} catch (Exception ec) {");
		htmlData.append("													logger.debug('We are in exception while getting age from personalInfo ' + ec);");
		htmlData.append("											}");
		htmlData.append("										%>");*/
		htmlData.append("								</span></td>");
		htmlData.append("							</tr>");
		htmlData.append("");
		htmlData.append("							<tr>");
		htmlData.append("								<td class='dataHeader' style='padding-left: 10px;' width='25%'>Gender</td>");
		htmlData.append("								<td class='dataHeader' width='25%'>Total Income</td>");
		htmlData.append("								<td class='dataHeader' width='25%'>Occupation</td>");
		htmlData.append("								<td width='25%'></td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='background: #e5e5e5;'>");
		htmlData.append("								<td class='dataValue' style='padding-left: 10px;' width='25%'>");
		htmlData.append("									<span> ");/*
		htmlData.append("											try {");
		htmlData.append("											%> <%=request.getAttribute('gender')==null?'':(String)request.getAttribute('gender')%>");
		htmlData.append("										<%} catch (Exception ec) {} %>");*/
		htmlData.append("								</span>");
		htmlData.append("								</td>");
		htmlData.append("								<td class='dataValue' width='25%'><span>");
		/*htmlData.append("											try {");
		htmlData.append("											%> <%=request.getAttribute('totalincome')==null?'':(String)request.getAttribute('totalincome')%>");
		htmlData.append("										<%} catch (Exception ec) {} %>");*/
		htmlData.append("								</span></td>");
		htmlData.append("								<td class='dataValue' width='25%'><span>");/*
		htmlData.append("											try {");
		htmlData.append("										%> <%=request.getAttribute('occupation')==null?'':(String)request.getAttribute('occupation')%>");
		htmlData.append("										<%} catch (Exception ec) {} %>");*/
		htmlData.append("								</span></td>");
		htmlData.append("								<td width='25%'></td>");
		htmlData.append("							</tr>");
		htmlData.append("						</tbody>");
		htmlData.append("					</table> <!-- Personal Info Ends Here -->");
		htmlData.append("				</td>");
		htmlData.append("			</tr>");
		htmlData.append("");
		htmlData.append("			<tr>");
		htmlData.append("				<td>");
		htmlData.append("");
		htmlData.append("					<table align='center' border='0' cellspacing='0' width='100%'>");
		htmlData.append("						<tbody>");
		htmlData.append("							<tr style='height: 10px;'></tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table");
		htmlData.append("										style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("										align='left' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td height='15px'></td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr height='20'>");
		htmlData.append("												<td class='mainHeader' style='padding-left: 10px;'>Consumer");
		htmlData.append("													Phone Details</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='center' border='0' cellspacing='0' width='100%'>");
		htmlData.append("										<tbody>");
		htmlData.append("");
		htmlData.append("											<tr style='height: 20px;'>");
		htmlData.append("												<td class='dataHeader' width='30%'>Number</td>");
		htmlData.append("												<td class='dataHeader' width='30%'>Type Code</td>");
		htmlData.append("												<td class='dataHeader' width='40%'>Date Reported</td>");
		htmlData.append("											</tr>");
		htmlData.append("");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='left' border='0' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr style='height: 1px;'>");
		htmlData.append("												<td></td>");
		htmlData.append("											</tr>");
		/*htmlData.append("											<%");
		htmlData.append("													try {");
		htmlData.append("														if(request.getAttribute('phoneDetails')!=null){");
		htmlData.append("															List<EquifaxPcsPhoneDetails> pcsPhoneDetails=(ArrayList<EquifaxPcsPhoneDetails>) request.getAttribute('phoneDetails');");
		htmlData.append("																				if(!pcsPhoneDetails.isEmpty()){");
		htmlData.append("																					int i=-1;	");
		htmlData.append("																				for(EquifaxPcsPhoneDetails phoneInfo:pcsPhoneDetails){i++;%>");
		htmlData.append("");
		htmlData.append("											<%");*/
/*		htmlData.append("												if(i%2==0)");
		htmlData.append("												{");
		htmlData.append("												%>");*/
		htmlData.append("											<tr style='background: #e5e5e5'>");
		/*htmlData.append("												<%");
		htmlData.append("												}");
		htmlData.append("												else");
		htmlData.append("												{");
		htmlData.append("												%>");*/
		htmlData.append("												<tr>");
/*		htmlData.append("													<%");
		htmlData.append("												} ");
		htmlData.append("												%>");*/
		htmlData.append("													<td class='dataValue' width='30%'");
		htmlData.append("														style='word-wrap: break-word;'>");
		/*htmlData.append("														<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=phoneInfo.getPhoneNumber()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("													</td>");
		htmlData.append("");
		htmlData.append("													<td class='dataValue' width='30%' style=''>");
		/*htmlData.append("														<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=phoneInfo.getPhoneTypecode()%> <%} catch (Exception ec) {} %>");
		*/htmlData.append("													</td>");
		htmlData.append("													<td class='dataValue' width='40%' style=''>");
		/*htmlData.append("														<%try {%> <%=phoneInfo.getPhoneReportedDate()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("													</td>");
		htmlData.append("");
		htmlData.append("												</tr>");/*
		htmlData.append("												<%");
		htmlData.append("												}");
		htmlData.append("												}}} catch (Exception ec) {");
		htmlData.append("													logger.debug('We are in exception while getting error of addressinfo ' + ec);");
		htmlData.append("												}");
		htmlData.append("											%>");
		htmlData.append("											");*/
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("");
		htmlData.append("						</tbody>");
		htmlData.append("					</table>");
		htmlData.append("				</td>");
		htmlData.append("			</tr>");
		htmlData.append("");
		htmlData.append("			<tr>");
		htmlData.append("				<td>");
		htmlData.append("					<table align='center' border='0' cellspacing='0' width='100%'>");
		htmlData.append("						<tbody>");
		htmlData.append("							<tr style='height: 10px;'></tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table");
		htmlData.append("										style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("										align='center' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td height='15px'></td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr height='20'>");
		htmlData.append("												<td class='mainHeader' style='padding-left: 10px;'>Consumer");
		htmlData.append("													Address</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='center' border='0' cellspacing='0' width='100%'>");
		htmlData.append("										<tbody>");
		htmlData.append("");
		htmlData.append("											<tr style='height: 20px;'>");
		htmlData.append("												<td class='dataHeader' width='10%'");
		htmlData.append("													style='padding-left: 10px;'>Type</td>");
		htmlData.append("												<td class='dataHeader' width='60%'>Address</td>");
		htmlData.append("												<td class='dataHeader' width='5%'>State</td>");
		htmlData.append("												<td class='dataHeader' width='10%'>Postal</td>");
		htmlData.append("												<td class='dataHeader' width='15%'>Date Reported</td>");
		htmlData.append("											</tr>");
		htmlData.append("");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='center' border='0' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr style='height: 1px;'>");
		htmlData.append("												<td></td>");
		htmlData.append("											</tr>");
		/*htmlData.append("											<%");
		htmlData.append("													try {");
		htmlData.append("														if(request.getAttribute('addressDetails')!=null){");
		htmlData.append("															List<EquifaxPcsAddressDetails> pcsAddressDetails=(ArrayList<EquifaxPcsAddressDetails>) request.getAttribute('addressDetails');");
		htmlData.append("																if(!pcsAddressDetails.isEmpty()){");
		htmlData.append("																	int i=-1;");
		htmlData.append("																for(EquifaxPcsAddressDetails singleAddressInfo:pcsAddressDetails){i++;%>");
		
		htmlData.append("												{");
		htmlData.append("												%>")*/
		htmlData.append("											<tr style='background: #e5e5e5'>");
		/*htmlData.append("												<%");
		htmlData.append("												}");
		htmlData.append("												else");
		htmlData.append("												{");
		htmlData.append("												%>");
		*/htmlData.append("												<tr>");
		/*htmlData.append("													<%");
		htmlData.append("												} ");
		htmlData.append("												%>");*/
		htmlData.append("													<td class='dataValue' width='10%'");
		htmlData.append("														style='padding-left: 10px;'>");
		/*htmlData.append("														<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=singleAddressInfo.getType()%> <%} catch (Exception ec) {} %>");
		*/htmlData.append("													</td>");
		htmlData.append("");
		htmlData.append("													<td class='dataValue' width='60%'");
		htmlData.append("														style='word-wrap: break-word;'>");/*
		htmlData.append("														<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=singleAddressInfo.getAddress()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("													</td>");
		htmlData.append("");
		htmlData.append("													<td class='dataValue' width='5%' style=''>");/*
		htmlData.append("														<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=singleAddressInfo.getState()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("													</td>");
		htmlData.append("");
		htmlData.append("													<td class='dataValue' width='10%' style=''>");/*
		htmlData.append("														<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=singleAddressInfo.getPostal()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("													</td>");
		htmlData.append("");
		htmlData.append("													<td class='dataValue' width='15%' style=''>");/*
		htmlData.append("														<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=singleAddressInfo.getReportedDate()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("													</td>");
		htmlData.append("												</tr>");/*
		htmlData.append("												<%");
		htmlData.append("												}");
		htmlData.append("												}}} catch (Exception ec) {");
		htmlData.append("													logger.debug('We are in exception while getting error of addressinfo ' + ec);");
		htmlData.append("												}");
		htmlData.append("											%>");
		htmlData.append("											");*/
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("");
		htmlData.append("						</tbody>");
		htmlData.append("					</table>");
		htmlData.append("				</td>");
		htmlData.append("			</tr>");
		htmlData.append("");
		htmlData.append("");
		htmlData.append("			<tr>");
		htmlData.append("				<td>");
		htmlData.append("					<table align='center' border='0' cellspacing='0' width='100%'>");
		htmlData.append("						<tbody>");
		htmlData.append("							<tr style='height: 10px;'></tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table");
		htmlData.append("										style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("										align='left' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td height='15px'></td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr height='20'>");
		htmlData.append("												<td class='mainHeader' style='padding-left: 10px;'>");
		htmlData.append("													Identification</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='center' border='0' cellspacing='0' width='100%'>");
		htmlData.append("										<tbody>");
		htmlData.append("");
		htmlData.append("											<tr style='height: 20px;'>");
		htmlData.append("												<td class='dataHeader' width='33%'");
		htmlData.append("													style='padding-left: 10px;'>ID Name</td>");
		htmlData.append("												<td class='dataHeader' width='33%'>Number</td>");
		htmlData.append("												<td class='dataHeader' width='33%'>Date Reported</td>");
		htmlData.append("											</tr>");
		htmlData.append("");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='center' border='0' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr style='height: 1px;'>");
		htmlData.append("												<td></td>");
		htmlData.append("											</tr>");/*
		htmlData.append("											<%");
		htmlData.append("													try {");
		htmlData.append("														if(request.getAttribute('panDetails')!=null){");
		htmlData.append("															List<EquifaxPcsPanDetails> pcsPanDetails=(ArrayList<EquifaxPcsPanDetails>) request.getAttribute('panDetails');");
		htmlData.append("																if(!pcsPanDetails.isEmpty()){");
		htmlData.append("																	int i=-1;");
		htmlData.append("																for(EquifaxPcsPanDetails panInfo:pcsPanDetails){i++;%>");
		htmlData.append("											<%");
		htmlData.append("												if(i%2==0)");
		htmlData.append("												{");
		htmlData.append("												%>");*/
		htmlData.append("											<tr style='background: #e5e5e5'>");/*
		htmlData.append("												<%");
		htmlData.append("												}");
		htmlData.append("												else");
		htmlData.append("												{");
		htmlData.append("												%>");*/
		htmlData.append("												<tr>");/*
		htmlData.append("													<%");
		htmlData.append("												} ");
		htmlData.append("												%>");*/
		htmlData.append("													<td class='dataValue' width='33%'");
		htmlData.append("														style='padding-left: 10px;'>");/*
		htmlData.append("														<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=panInfo.getPanSeq()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("													</td>");
		htmlData.append("");
		htmlData.append("													<td class='dataValue' width='33%'");
		htmlData.append("														style='word-wrap: break-word;'>");/*
		htmlData.append("														<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=panInfo.getPanNumber()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("													</td>");
		htmlData.append("");
		htmlData.append("");
		htmlData.append("													<td class='dataValue' width='33%' style=''>");/*
		htmlData.append("														<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=panInfo.getPanReportedDate()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("													</td>");
		htmlData.append("												</tr>");/*
		htmlData.append("												<%");
		htmlData.append("												}");
		htmlData.append("												}}} catch (Exception ec) {");
		htmlData.append("													logger.debug('We are in exception while getting error of panInfo ' + ec);");
		htmlData.append("												}");
		htmlData.append("											%>");*/
		htmlData.append("											");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("");
		htmlData.append("						</tbody>");
		htmlData.append("					</table>");
		htmlData.append("				</td>");
		htmlData.append("			</tr>");
		htmlData.append("");
		htmlData.append("			<tr>");
		htmlData.append("				<td>");
		htmlData.append("					<table align='center' border='0' cellspacing='0' width='100%'>");
		htmlData.append("						<tbody>");
		htmlData.append("							<tr style='height: 10px;'></tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table");
		htmlData.append("										style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("										align='left' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td height='15px'></td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr height='20'>");
		htmlData.append("												<td class='mainHeader' style='padding-left: 10px;'>Consumer");
		htmlData.append("													Email Details</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='center' border='0' cellspacing='0' width='100%'>");
		htmlData.append("										<tbody>");
		htmlData.append("");
		htmlData.append("											<tr style='height: 20px;'>");
		htmlData.append("												<td class='dataHeader' width='70%'>Email Id</td>");
		htmlData.append("												<td class='dataHeader' width='30%'>Date Reported</td>");
		htmlData.append("											</tr>");
		htmlData.append("");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='center' border='0' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr style='height: 1px;'>");
		htmlData.append("												<td></td>");
		htmlData.append("											</tr>");/*
		htmlData.append("											<%");
		htmlData.append("													try {");
		htmlData.append("														if(request.getAttribute('emailsDetails')!=null){");
		htmlData.append("															List<EquifaxPcsEmailDetails> pcsEmailDetails=(ArrayList<EquifaxPcsEmailDetails>) request.getAttribute('emailsDetails');");
		htmlData.append("																if(!pcsEmailDetails.isEmpty()){");
		htmlData.append("																	int i=-1;");
		htmlData.append("																for(EquifaxPcsEmailDetails emailInfo:pcsEmailDetails){i++;%>");
		htmlData.append("											<%");
		htmlData.append("												if(i%2==0)");
		htmlData.append("												{");
		htmlData.append("												%>");*/
		htmlData.append("											<tr style='background: #e5e5e5'>");/*
		htmlData.append("												<%");
		htmlData.append("												}");
		htmlData.append("												else");
		htmlData.append("												{");
		htmlData.append("												%>");
		htmlData.append("												<tr>");
		htmlData.append("													<%");
		htmlData.append("												} ");
		htmlData.append("												%>");*/
		htmlData.append("													<td class='dataValue' width='70%'");
		htmlData.append("														style='word-wrap: break-word;'>");/*
		htmlData.append("														<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=emailInfo.getEmailaddress()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("													</td>");
		htmlData.append("");
		htmlData.append("");
		htmlData.append("													<td class='dataValue' width='30%' style=''>");/*
		htmlData.append("														<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=emailInfo.getReporteddate()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("													</td>");
		htmlData.append("												</tr>");/*
		htmlData.append("												<%");
		htmlData.append("												}");
		htmlData.append("												}}} catch (Exception ec) {");
		htmlData.append("													logger.debug('We are in exception while getting error of emailInfo ' + ec);");
		htmlData.append("												}");
		htmlData.append("											%>");*/
		htmlData.append("											");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("");
		htmlData.append("						</tbody>");
		htmlData.append("					</table>");
		htmlData.append("				</td>");
		htmlData.append("			</tr>");
		htmlData.append("<!-- OtherKeyInd start -->");
		htmlData.append("							");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("							</tr>");
		htmlData.append("							");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table align='left' border='0' width='100%'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr style='background: #e5e5e5;'>");
		htmlData.append("												<td class='dataHeader' style='padding-left:10px;'>OtherKeyInd Report</td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr style='background: #e5e5e5;'>");
		htmlData.append("												<td>");
		htmlData.append("												");/*
		htmlData.append("													<%try {");
		htmlData.append("													if(request.getAttribute('otherkeyInd')!=null){");
		htmlData.append("														EquifaxOtherKeyInd otherKeyInd=(EquifaxOtherKeyInd) request.getAttribute('otherkeyInd');");
		htmlData.append("															if(otherKeyInd!=null){%> ");*/
		htmlData.append("													");
		htmlData.append("");
		htmlData.append("													<table border='0' width='100%'>");
		htmlData.append("														<tbody>");
		htmlData.append("															<tr>");
		htmlData.append("																<td width='33%' class='dataValue' style='padding-left: 10px;'>");
		htmlData.append("																	<span class='fontWeight'>Age Of Oldest Trade : </span>");
		htmlData.append("");/*
		htmlData.append("																	<%");
		htmlData.append("																		try {");
		htmlData.append("																	%> <%=otherKeyInd.getAgeOfOldestTrade()%> <%");
		htmlData.append("																	} catch (Exception ec) {}");
		htmlData.append("																	%>");*/
		htmlData.append("																</td>");
		htmlData.append("");
		htmlData.append("																<td width='33%' class='dataValue'>");
		htmlData.append("																	<span class='fontWeight'>Number Of Open Trades : </span>");/*
		htmlData.append("																	<%");
		htmlData.append("																		try {");
		htmlData.append("																	%> <%=otherKeyInd.getNumberOfOpenTrades()%> <%");
		htmlData.append("																	 	} catch (Exception ec) {}");
		htmlData.append("																	%>");*/
		htmlData.append("																</td>");
		htmlData.append("");
		htmlData.append("																<td width='33%' class='dataValue'>");
		htmlData.append("																	<span class='fontWeight'>All Lines Ever Written : </span>");
		htmlData.append("");/*
		htmlData.append("																	<%");
		htmlData.append("																		try {");
		htmlData.append("																	%> <%=otherKeyInd.getAllLinesEVERWritten()%> <%");
		htmlData.append("															 		} catch (Exception ec) {}");
		htmlData.append("															 		%>");*/
		htmlData.append("																</td>");
		htmlData.append("");
		htmlData.append("															</tr>");
		htmlData.append("															<tr>");
		htmlData.append("															");
		htmlData.append("																<td width='33%' class='dataValue' style='padding-left: 10px;'>");
		htmlData.append("																	<span class='fontWeight'>All Lines Ever Written In 6 Months : </span>");/*
		htmlData.append("																	<%");
		htmlData.append("																		try {");
		htmlData.append("																	%> <%=otherKeyInd.getAllLinesEVERWrittenIn6Months()%> <%");
		htmlData.append("																 	} catch (Exception ec) {}");
		htmlData.append("																 	%>");*/
		htmlData.append("																</td>");
		htmlData.append("																");
		htmlData.append("																<td width='33%' class='dataValue'>");
		htmlData.append("																	<span class='fontWeight'>All Lines Ever Written In 9 Months : </span>");/*
		htmlData.append("																	<%");
		htmlData.append("																		try {");
		htmlData.append("																	%> <%=otherKeyInd.getAllLinesEVERWrittenIn9Months()%> <%");
		htmlData.append("																	 	} catch (Exception ec) {}");
		htmlData.append("																 	%>");*/
		htmlData.append("																</td>");
		htmlData.append("																");
		htmlData.append("																<td width='33%' class='dataValue'></td>");
		htmlData.append("															</tr>");
		htmlData.append("														</tbody>");
		htmlData.append("													</table> ");
		htmlData.append("												</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("							<!-- OtherKeyInd end -->");
		htmlData.append("			<tr>");
		htmlData.append("				<td>");
		htmlData.append("					<table align='center' border='0' cellspacing='0' width='100%'>");
		htmlData.append("						<tbody>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table");
		htmlData.append("										style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("										align='center' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td height='15px'></td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr height='20'>");
		htmlData.append("												<td class='mainHeader' style='padding-left: 10px;'>Recent");
		htmlData.append("													Activity</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table border='0' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td class='dataHeader' colspan='4'");
		htmlData.append("													style='padding-left: 10px;'>Recent Activity (last 90");
		htmlData.append("													days)</td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr style='background: #e5e5e5;'>");/*
		htmlData.append("												<%");
		htmlData.append("													try {");
		htmlData.append("													if(request.getAttribute('recentactivities')!=null){");
		htmlData.append("														EquifaxPcsRecentActivities recentactivities=(EquifaxPcsRecentActivities) request.getAttribute('recentactivities');");
		htmlData.append("															if(recentactivities!=null){");
		htmlData.append("												%>");*/
		htmlData.append("");
		htmlData.append("												<td align='left' width='25%' class='dataValue'");
		htmlData.append("													style='padding-left: 10px;'><span align='left'");
		htmlData.append("													class='fontWeight'>Total Inquiries : </span>");/*
		htmlData.append("														try {");
		htmlData.append("													%> <%=recentactivities.getTotal_enquiries()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("												</td>");
		htmlData.append("												<td width='25%' class='dataValue'><span align='left'");
		htmlData.append("													class='fontWeight'>Accounts Opened : </span>");/*
		htmlData.append("														try {");
		htmlData.append("													%> <%=recentactivities.getAccounts_opened()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("												</td>");
		htmlData.append("												<td width='25%' class='dataValue'><span align='left'");
		htmlData.append("													class='fontWeight'>Accounts Updated : </span>");/*
		htmlData.append("														try {");
		htmlData.append("													%> <%=recentactivities.getAccounts_updated()%> <%} catch (Exception ec) {} %>");
		htmlData.append("");*/
		htmlData.append("												</td>");
		htmlData.append("												<td width='25%' class='dataValue'><span align='left'");
		htmlData.append("													class='fontWeight'>Accounts Delinquent : </span>");/*<% try {%> <%=recentactivities.getAccounts_deliquent()%>*/
/*		htmlData.append("													<%} catch (Exception ec) {} %></td>");
		htmlData.append("												<%");
		htmlData.append("													}}} catch (Exception ec) {");
		htmlData.append("														logger.debug('We are in exception while getting response from recentactivities' + ec);");
		htmlData.append("													}");
		htmlData.append("												%>");*/
		htmlData.append("											</tr>");
		htmlData.append("");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table");
		htmlData.append("										style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("										align='center' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td height='15px'></td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr height='20'>");
		htmlData.append("												<td class='mainHeader' style='padding-left: 10px;'>Account");
		htmlData.append("													Summary");
		htmlData.append("												</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");/*
		htmlData.append("									<%");
		htmlData.append("									try ");
		htmlData.append("									{");
		htmlData.append("										if(request.getAttribute('accountsummary')!=null)");
		htmlData.append("										{");
		htmlData.append("										JSONObject account = new JSONObject(request.getAttribute('accountsummary') + '');");
		htmlData.append("								%>");*/
		htmlData.append("								<table width='100%'>");
		htmlData.append("								<tbody>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataHeader' width='20%' style='padding-left: 10px;'>Accounts</td>");
		htmlData.append("										<td class='dataHeader' width='20%'>Balances</td>");
		htmlData.append("										<td class='dataHeader' width='25%'>Amounts</td>");
		htmlData.append("										<td class='dataHeader' width='35%'>Others</td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='20%' style='padding-left: 10px;'><span class='fontWeight'>Number of Accounts : </span>NA</td>");
		htmlData.append("										<td class='dataValue' width='20%'><span class='fontWeight'>Single High Sanction Amount : </span>NA</td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Single High Sanction Credit : </span>NA</td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Recent Account : </span> NA</td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='20%' style='padding-left: 10px;'><span class='fontWeight'>Active Accounts :</span>NA</td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Average Open Balance : </span>NA</td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Total High Credit : </span>NA</td>");
		htmlData.append("										<td class='dataValue' width='30%'><span class='fontWeight'>Oldest Account : </span>NA</td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='20%' style='padding-left: 10px;'><span class='fontWeight'>Write-Off Accounts :</span>NA</td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Single Highest Balance : </span>NA</td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Total Sanction Amount : </span>NA</td>");
		htmlData.append("										<td class='dataValue' width='30%'><span class='fontWeight'>Most Severe Status With-In 24 Months : </span>NA</td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='20%' style='padding-left: 10px;'><span class='fontWeight'>Past Due Accounts : </span>NA</td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Total Balance Amount : </span>NA</td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Total Monthly payment Amount : </span>NA</td>");
		htmlData.append("										<td class='dataValue' width='30%'><span class='fontWeight'></span>NA</td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='20%' style='padding-left: 10px;'><span class='fontWeight'>Zero-Balance Account :</span>NA</td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Total Past Due Amount : </span>NA</td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Total Credit Limit : </span>NA</td>");
		htmlData.append("										<td class='dataValue' width='30%'><span class='fontWeight'></span>NA</td>");
		htmlData.append("									</tr>");
		htmlData.append("								</tbody>");
		htmlData.append("							</table>");/*
		htmlData.append("								<%}}catch(Exception ec){} %>");
		htmlData.append("									");*/
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("");
		htmlData.append("							<tr>");
		htmlData.append("								<td height='20px'></td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table");
		htmlData.append("										style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("										align='left' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td height='15px'></td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr height='20'>");
		htmlData.append("												<td class='mainHeader' style='padding-left: 10px;'>Account");
		htmlData.append("													Details*</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");/*
		htmlData.append("							<%");
		htmlData.append("								try {");
		htmlData.append("								if(request.getAttribute('account')!=null)");
		htmlData.append("								{");
		htmlData.append("									JSONArray accountsarray = new JSONArray(request.getAttribute('account') + '');");
		htmlData.append("									");
		htmlData.append("									for (int i = 0; i < accountsarray.length(); i++) {");
		htmlData.append("");
		htmlData.append("										JSONObject account = accountsarray.getJSONObject(i);");
		htmlData.append("							%>");*/
		htmlData.append("							<tr>");
		htmlData.append("								<td height='10px' class='dataValue' style='padding-left: 10px;'>");
		htmlData.append("									<span class='fontWeight'> </span>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("							<% if (i % 2 == 0) { %>");
		htmlData.append("							<!-- <tr style='background: #e5e5e5'> -->");
		htmlData.append("							<tr style='background: #e5e5e5'>");
		htmlData.append("								<%");
		htmlData.append("									} else {");
		htmlData.append("								%>");
		htmlData.append("								<tr>");
		htmlData.append("									<td height='10px' class='dataValue' style='padding-left: 10px;'>");
		htmlData.append("										<span class='fontWeight'> </span>");
		htmlData.append("									</td>");
		htmlData.append("								</tr>");
		htmlData.append("								<tr>");
		htmlData.append("									<%");
		htmlData.append("										}");
		htmlData.append("									%>");
		htmlData.append("									<td>");
		htmlData.append("										<table border='0' width='100%' cellspacing='0'>");
		htmlData.append("											<tbody>");
		htmlData.append("												<tr>");
		htmlData.append("													<td width='20%' class='dataValue'");
		htmlData.append("														style='padding-left: 10px;'><span class='fontWeight'>Acct #");
		htmlData.append("															 </span>NA");
		htmlData.append("													</td>");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Balance : </span>NA");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Open : </span> NA");
		htmlData.append("														</td>");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Date Reported : </span> NA");
		htmlData.append("														</td>");
		htmlData.append("");
		htmlData.append("												</tr>");
		htmlData.append("												<tr>");
		htmlData.append("													<td width='20%' class='dataValue'");
		htmlData.append("														style='padding-left: 10px;'><span class='fontWeight'>Institution");
		htmlData.append("															: </span>NA</td>");
		htmlData.append("");
		htmlData.append("													<td width='20%' class='dataValue'>");
		htmlData.append("													<span class='fontWeight'>Past Due Amount : </span>");
		htmlData.append("													NA</td>");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Interest Rate : </span> NA");
		htmlData.append("														</td>");
		htmlData.append("");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Date Closed : </span> ");
		htmlData.append("														NA</td>");
		htmlData.append("												</tr>");
		htmlData.append("												<tr>");
		htmlData.append("													<td width='20%' class='dataValue'");
		htmlData.append("														style='padding-left: 10px;'><span class='fontWeight'>Account Type");
		htmlData.append("															: </span>NA</td>");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Last Payment : </span> NA");
		htmlData.append("														</td>");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Last Payment Date : </span>");
		htmlData.append("														NA</td>");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>High Credit : </span> NA</td>");
		htmlData.append("												</tr>");
		htmlData.append("												<tr>");
		htmlData.append("													<td width='20%' class='dataValue'");
		htmlData.append("														style='padding-left: 10px;'><span class='fontWeight'>Ownership");
		htmlData.append("															Type : </span> NA");
		htmlData.append("														</td>");
		htmlData.append("");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Write Off Amount : </span>NA</td>");
		htmlData.append("");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Sanction Amount : </span> NA");
		htmlData.append("														</td>");
		htmlData.append("");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Reason : </span> NA");
		htmlData.append("														</td>");
		htmlData.append("												</tr>");
		htmlData.append("");
		htmlData.append("												<tr>");
		htmlData.append("													<td width='20%' class='dataValue'");
		htmlData.append("														style='padding-left: 10px;'><span class='fontWeight'>Repayment");
		htmlData.append("															Tenure : </span> ");
		htmlData.append("															NA</td>");
		htmlData.append("");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Open : </span>");
		htmlData.append("														NA</td>");
		htmlData.append("");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Credit Limit : </span>");
		htmlData.append("														NA</td>");
		htmlData.append("");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Collateral Value : </span> NA");
		htmlData.append("														</td>");
		htmlData.append("												</tr>");
		htmlData.append("												<tr>");
		htmlData.append("													<td width='20%' class='dataValue'");
		htmlData.append("														style='padding-left: 10px;'><span class='fontWeight'>Dispute");
		htmlData.append("															Code : </span> NA");
		htmlData.append("														</td>");
		htmlData.append("");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Term Frequency : </span> NA");
		htmlData.append("														</td>");
		htmlData.append("													<td width='20%' class='dataValue'>");
		htmlData.append("													<span class='fontWeight'>Installment Amount : </span>");
		htmlData.append("														NA</td>");
		htmlData.append("");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Collateral Type : </span> NA");
		htmlData.append("														</td>");
		htmlData.append("												</tr>");
		htmlData.append("												");
		htmlData.append("												<tr>");
		htmlData.append("													<td width='20%' class='dataValue'");
		htmlData.append("														style='padding-left: 10px;'><span class='fontWeight'>Account Status : </span> NA");
		htmlData.append("														</td>");
		htmlData.append("");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Asset Classification : </span> ");
		htmlData.append("														NA</td>");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'>Suit Filed Status : </span>");
		htmlData.append("														NA</td>");
		htmlData.append("													<td width='20%' class='dataValue'><span");
		htmlData.append("														class='fontWeight'></span> NA</td>");
		htmlData.append("												</tr>");
		htmlData.append("												<tr>");
		htmlData.append("													<td width='100%' colspan='4' class='dataValue'");
		htmlData.append("														style='padding-left: 10px;'><span class='fontWeight'");
		htmlData.append("														style='width: 20%;'>History : </span> NA");/*
		htmlData.append("															try {");
		htmlData.append("																		JSONArray history48month = null;");
		htmlData.append("																		JSONObject jsonObject = null;");
		htmlData.append("																		");
		htmlData.append("																		if(account.has('history48months')){");
		htmlData.append("																		if(account.getJSONObject('history48months').has('month'))");
		htmlData.append("																		{");
		htmlData.append("																			if (account.getJSONObject('history48months').get('month') instanceof JSONArray)");
		htmlData.append("																			{");
		htmlData.append("																				history48month = account.getJSONObject('history48months').getJSONArray('month');");
		htmlData.append("																			}");
		htmlData.append("																			if (account.getJSONObject('history48months').get('month') instanceof JSONObject)");
		htmlData.append("																			{");
		htmlData.append("																				jsonObject = account.getJSONObject('history48months').getJSONObject('month');");
		htmlData.append("																				history48month = new JSONArray();");
		htmlData.append("																				history48month.put(jsonObject);");
		htmlData.append("																			}");
		htmlData.append("														%>");*/
		htmlData.append("														<table cellspacing='0'");
		htmlData.append("															style='margin-top: 15px; border: 0.5px solid #000; border-spacing: 0; '");
		htmlData.append("															width='100%' >");
		htmlData.append("");
		htmlData.append("															<tr>");
		htmlData.append("																<td class='dataValue'");
		htmlData.append("																	style='padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;'><span");
		htmlData.append("																	class='fontWeight'> Asset Classification Status");
		htmlData.append("																</span>");
		htmlData.append("																</td>");/*
		htmlData.append("																<% try {for (int j = 0; j < (history48month.length() < 24 ? history48month.length() : 24); j++) {");
		htmlData.append("																		JSONObject singleHistory = history48month.getJSONObject(j);%>");
		htmlData.append("																<td class='dataValue'");
		htmlData.append("																	style='text-align: center; padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;'>");
		htmlData.append("																	<%try {%> <%=singleHistory.get('assetclassificationstatus')%>");
		htmlData.append("																	<%} catch (Exception ec) {}%>");
		htmlData.append("																</td>");
		htmlData.append("																<%}} catch (Exception ec) {}%>");*/
		htmlData.append("															</tr>");
		htmlData.append("");
		htmlData.append("															<tr>");
		htmlData.append("																<td class='dataValue'");
		htmlData.append("																	style='padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;'>Suit");
		htmlData.append("																	Filed Status</td>");
		/*htmlData.append("																		JSONObject singleHistory = history48month.getJSONObject(j);%>");*///<% try {for (int j = 0; j < (history48month.length() < 24 ? history48month.length() : 24); j++) {
		htmlData.append("");
		htmlData.append("																<td class='dataValue'");
		htmlData.append("																	style='text-align: center; padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal'>");
		htmlData.append("																	NA");
		htmlData.append("																</td>");
		htmlData.append("");
		htmlData.append("															</tr>");
		htmlData.append("");
		htmlData.append("															<tr>");
		htmlData.append("																<td class='dataValue'");
		htmlData.append("																	style='padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;'><span");
		htmlData.append("																	class='fontWeight'>Payment Status</span></td>");
		htmlData.append("																NA");
		htmlData.append("																<td class='dataValue'");
		htmlData.append("																	style='text-align: center; padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;'>");
		htmlData.append("																</td>");
		htmlData.append("");
		htmlData.append("															</tr>");
		htmlData.append("");
		htmlData.append("															<tr>");
		htmlData.append("																<td class='dataValue'");
		htmlData.append("																	style='padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;'><span");
		htmlData.append("																	class='fontWeight'> Date</span></td>");
		htmlData.append("");
		htmlData.append("																<td class='dataValue'");
		htmlData.append("																	style='text-align: center; padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;'>");
		htmlData.append("																	NA");
		htmlData.append("																</td>");
		htmlData.append("");
		htmlData.append("																");
		htmlData.append("															</tr>");
		htmlData.append("														</table> ");
		htmlData.append("														</td>");
		htmlData.append("												</tr>");
		htmlData.append("												<tr>");
		htmlData.append("													<td width='20%' class='dataValue'");
		htmlData.append("														style='padding-left: 10px;'><span class='fontWeight'>");
		htmlData.append("													</span></td>");
		htmlData.append("												</tr>");
		htmlData.append("											</tbody>");
		htmlData.append("										</table>");
		htmlData.append("									</td>");
		htmlData.append("								</tr>");
		htmlData.append("");
/*		htmlData.append("								<%");
		htmlData.append("									}");
		htmlData.append("									}} catch (Exception ec) {");
		htmlData.append("										logger.debug('We are in exception while getting response of account' + ec);");
		htmlData.append("									}");
		htmlData.append("								%>");*/
		htmlData.append("");
		htmlData.append("								<tr>");
		htmlData.append("									<td>");
		htmlData.append("										<table");
		htmlData.append("											style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("											align='left' width='100%' cellspacing='0'>");
		htmlData.append("											<tbody>");
		htmlData.append("												<tr>");
		htmlData.append("													<td height='15px'></td>");
		htmlData.append("												</tr>");
		htmlData.append("												<tr height='20'>");
		htmlData.append("													<td class='mainHeader' style='padding-left: 10px;'>Enquiry");
		htmlData.append("														Summary</td>");
		htmlData.append("												</tr>");
		htmlData.append("											</tbody>");
		htmlData.append("										</table>");
		htmlData.append("");
		htmlData.append("										<table align='left' border='0' cellspacing='0' width='100%'>");
		htmlData.append("											<tbody>");
		htmlData.append("												<tr>");
		htmlData.append("													<td>");
		htmlData.append("														<table align='left' border='0' width='100%'");
		htmlData.append("															cellspacing='0'>");
		htmlData.append("															<tbody>");
		htmlData.append("																<tr style='height: 20px;'>");
		htmlData.append("																	<td class='dataHeader' width='20%'");
		htmlData.append("																		style='padding-left: 10px;'>Purpose</td>");
		htmlData.append("																	<td class='dataHeader' width='15%'>Total</td>");
		htmlData.append("																	<td class='dataHeader' width='15%'>Past 30 Days</td>");
		htmlData.append("																	<td class='dataHeader' width='15%'>Past 12 Months</td>");
		htmlData.append("																	<td class='dataHeader' width='15%'>Past 24 Months</td>");
		htmlData.append("																	<td class='dataHeader' width='20%'>Recent</td>");
		htmlData.append("																</tr>");
		htmlData.append("															</tbody>");
		htmlData.append("														</table>");
		htmlData.append("													</td>");
		htmlData.append("												</tr>");
		htmlData.append("");
		htmlData.append("												<tr>");/*
		htmlData.append("													<%");
		htmlData.append("														try {");
		htmlData.append("															if(request.getAttribute('enquirysummary')!=null ){");
		htmlData.append("															JSONObject enquirysummary = new JSONObject(request.getAttribute('enquirysummary') + '');");
		htmlData.append("													%>");*/
		htmlData.append("													<td>");
		htmlData.append("														<table align='left'");
		htmlData.append("															style='table-layout: fixed; border: 0;' cellspacing='0'");
		htmlData.append("															width='100%' class='custom-content'>");
		htmlData.append("															<tbody>");
		htmlData.append("																<tr style='height: 20px;'>");
		htmlData.append("																	<td class='dataValue' width='20%'");
		htmlData.append("																		style='padding-left: 10px;'>");/*
		htmlData.append("																		<%");
		htmlData.append("																			try {");
		htmlData.append("																		%> <%=enquirysummary.get('purpose')%> <%} catch (Exception ec) {}%>");*/
		htmlData.append("																	</td>");
		htmlData.append("");
		htmlData.append("																	<td class='dataValue' width='15%'>");/*
		htmlData.append("																		<%");
		htmlData.append("																			try {");
		htmlData.append("																		%> <%=enquirysummary.get('total')%> <%} catch (Exception ec) {}%>");*/
		htmlData.append("																	</td>");
		htmlData.append("");
		htmlData.append("																	<td class='dataValue' width='15%'>");/*
		htmlData.append("																		<%");
		htmlData.append("																			try {");
		htmlData.append("																		%> <%=enquirysummary.get('past30days')%> <%} catch (Exception ec) {}%>");*/
		htmlData.append("																	</td>");
		htmlData.append("																	<td class='dataValue' width='15%'>");/*
		htmlData.append("																		<%");
		htmlData.append("																			try {");
		htmlData.append("																		%> <%=enquirysummary.get('past12months')%> <%} catch (Exception ec) {}%>");*/
		htmlData.append("																	</td>");
		htmlData.append("																	<td class='dataValue' width='15%'>");/*
		htmlData.append("																		<%");
		htmlData.append("																			try {");
		htmlData.append("																		%> <%=enquirysummary.get('past24months')%> <%} catch (Exception ec) {}%>");*/
		htmlData.append("																	</td>");
		htmlData.append("																	<td class='dataValue' width='20%'>");/*
		htmlData.append("																		<%");
		htmlData.append("																			try {");
		htmlData.append("																		%> <%=enquirysummary.get('recent')%> <%} catch (Exception ec) {}%>");*/
		htmlData.append("																	</td>");
		htmlData.append("																</tr>");
		htmlData.append("															</tbody>");
		htmlData.append("														</table>");
		htmlData.append("													</td>");/*
		htmlData.append("													<%");
		htmlData.append("														}} catch (Exception ec) {");
		htmlData.append("															logger.debug('We are in exception while getting response of enquirysummary' + ec);");
		htmlData.append("														}");
		htmlData.append("													%>");*/
		htmlData.append("												</tr>");
		htmlData.append("");
		htmlData.append("												<tr>");
		htmlData.append("													<td>");
		htmlData.append("														<table");
		htmlData.append("															style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("															align='center' width='100%' cellspacing='0'>");
		htmlData.append("															<tbody>");
		htmlData.append("																<tr>");
		htmlData.append("																	<td height='15px'></td>");
		htmlData.append("																</tr>");
		htmlData.append("																<tr height='20'>");
		htmlData.append("																	<td class='mainHeader' style='padding-left: 10px;'>Enquiries");
		htmlData.append("																	</td>");
		htmlData.append("																</tr>");
		htmlData.append("															</tbody>");
		htmlData.append("														</table>");
		htmlData.append("");
		htmlData.append("														<table align='left' border='0' cellspacing='0'");
		htmlData.append("															width='100%'>");
		htmlData.append("															<tbody>");
		htmlData.append("																<tr>");
		htmlData.append("																	<td>");
		htmlData.append("																		<table align='left' border='0' width='100%'");
		htmlData.append("																			cellspacing='0'>");
		htmlData.append("																			<tbody>");
		htmlData.append("																				<tr style='height: 20px;'>");
		htmlData.append("																					<td class='dataHeader' width='28%'");
		htmlData.append("																						style='padding-left: 10px;'>Institution</td>");
		htmlData.append("																					<td class='dataHeader' width='18%'>Date</td>");
		htmlData.append("																					<td class='dataHeader' width='18%'>Time</td>");
		htmlData.append("																					<td class='dataHeader' align='right' width='18%'>Purpose</td>");
		htmlData.append("																					<td class='dataHeader' width='18%'>Amount</td>");
		htmlData.append("																				</tr>");
		htmlData.append("																			</tbody>");
		htmlData.append("																		</table>");
		htmlData.append("																	</td>");
		htmlData.append("																</tr>");
		htmlData.append("");
		htmlData.append("");/*
		htmlData.append("																<%");
		htmlData.append("																try {");
		htmlData.append("																	if(request.getAttribute('enquiries')!=null){");
		htmlData.append("																List<EquifaxPcsEnquiry> pcsEnquiresDetails=(ArrayList<EquifaxPcsEnquiry>)request.getAttribute('enquiries');");
		htmlData.append("																if(!pcsEnquiresDetails.isEmpty()){");
		htmlData.append("																for(EquifaxPcsEnquiry enquiry:pcsEnquiresDetails){%>");*/
		htmlData.append("");
		htmlData.append("																<tr>");
		htmlData.append("																	<td>");
		htmlData.append("																		<table align='center' cellspacing='0'");
		htmlData.append("																			style='table-layout: fixed; border-collapse: collapse; border-spacing: 0; border: 0;'");
		htmlData.append("																			width='100%' class='custom-content'>");
		htmlData.append("																			<tbody>");
		htmlData.append("																				<tr style='height: 20px;'>");
		htmlData.append("																					<td class='dataValue' width='28%'");
		htmlData.append("																						style='padding-left: 10px;'>");/*
		htmlData.append("																						<%");
		htmlData.append("																							try {");
		htmlData.append("																						%> <%=enquiry.getInstitution()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("																					</td>");
		htmlData.append("");
		htmlData.append("																					<td class='dataValue' width='18%'>");/*
		htmlData.append("																						<%");
		htmlData.append("																							try {");
		htmlData.append("																						%> <%=enquiry.getEnquiry_date()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("																					</td>");
		htmlData.append("");
		htmlData.append("																					<td class='dataValue' width='18%'>");/*
		htmlData.append("																						<%");
		htmlData.append("																							try {");
		htmlData.append("																						%> <%=enquiry.getEnquiry_time()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("																					</td>");
		htmlData.append("");
		htmlData.append("																					<td class='dataValue' width='18%' align='right'>");/*
		htmlData.append("																						<%try {%> <%=enquiry.getRequest_purpose()%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("																					</td>");
		htmlData.append("																					<td class='dataValue' width='18%'>");
		/*htmlData.append("																					<%try {%> <%=EquifaxUtill.commaSeprated(''+enquiry.getAmount())%> <%} catch (Exception ec) {} %>");*/
		htmlData.append("																					</td>");
		htmlData.append("																				</tr>");
		htmlData.append("																			</tbody>");
		htmlData.append("																		</table>");
		htmlData.append("																	</td>");
		htmlData.append("																</tr>");/*
		htmlData.append("																<%");
		htmlData.append("																	}");
		htmlData.append("																	}}} catch (Exception ec) {");
		htmlData.append("																		logger.debug('We are in exception while getting response of enquiries' + ec);");
		htmlData.append("																	}");
		htmlData.append("																%>");*/
		htmlData.append("																<tr style='height: 20px;'>");
		htmlData.append("																</tr>");
		htmlData.append("");
		htmlData.append("															</tbody>");
		htmlData.append("														</table>");
		htmlData.append("													</td>");
		htmlData.append("												</tr>");
		htmlData.append("												");
		htmlData.append("												");
		htmlData.append("																	<tr style='height: 20px;'>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table");
		htmlData.append("										style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("										align='center' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td height='15px'></td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr height='20'>");
		htmlData.append("												<td class='mainHeader' style='padding-left: 10px;'>Input Enquiry");
		htmlData.append("												</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");/*
		htmlData.append("									<%");
		htmlData.append("									try");
		htmlData.append("									{");
		htmlData.append("										if(request.getAttribute('inquiryrequestinfoBean')!=null)");
		htmlData.append("										{");
		htmlData.append("										EquifaxPcsInquiryRequestInfo bean=(EquifaxPcsInquiryRequestInfo)request.getAttribute('inquiryrequestinfoBean');");
		htmlData.append("										if(bean!=null)");
		htmlData.append("										{");
		htmlData.append("									%>");*/
		htmlData.append("								<table width='100%'>");
		htmlData.append("								<tbody>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataHeader' width='35%' style='padding-left: 10px;'>Personal & Account Information</td>");
		htmlData.append("										<td class='dataHeader' width='30%'>ID & Phone Numbers</td>");
		htmlData.append("										<td class='dataHeader' width='35%'>Contact Details</td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Consumer's First Name : </span>");

		
		String consumer_first_name=null;
		consumer_first_name=request.getfName();
		htmlData.append(consumer_first_name);
		htmlData.append("</td>");
		htmlData.append("										<td class='dataValue' width='30%'><span class='fontWeight'>Pan : </span>");
		String pan=null,voterid=null,passportid=null,uid=null,drivers_license=null;
		for(com.qualtech.equifax.api.common.dto.UniqueId id: request.getIds()){
			if(id.getIdName().toLowerCase().trim().contains("pan")){
				pan=id.getIdNo();
				continue;
			}
			else if(id.getIdName().toLowerCase().trim().contains("voter")){
				voterid=id.getIdNo();
				continue;
			}
			else if(id.getIdName().toLowerCase().trim().contains("passport")){
				passportid=id.getIdNo();
				continue;
			}
			else if(id.getIdName().toLowerCase().trim().contains("uid")){
				uid=id.getIdNo();
				continue;
			}
			else if(id.getIdName().toLowerCase().trim().contains("drivers_license")){
				drivers_license=id.getIdNo();
				continue;
			}
			else{}
		}
		
		if(pan!=null && !pan.equals(""))
		{
			htmlData.append(pan);
		}
		else
		{
			htmlData.append("NA");
		}
		htmlData.append("</td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Address Information 1 : </span>");
		htmlData.append("</td>");
		htmlData.append("									</tr>");
		
		
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Consumer's Family Name :</span>");
		String mname=request.getmName();
		if(mname!=null&& !mname.equals(""))
		htmlData.append(mname);
		else 
		htmlData.append("NA");
		htmlData.append("</td>");
		htmlData.append("										<td class='dataValue' width='30%'><span class='fontWeight'>Voter ID : </span>");
				if(voterid!=null && !voterid.equals(""))
				{
					htmlData.append(voterid);
				}
				else
				{
					htmlData.append("NA");
				}	
		htmlData.append("</td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Address : </span>");
		String addrs="",state="",postal="";
		if(request.getAddresses()!=null && request.getAddresses().size()>0 && request.getAddresses().get(0)!=null) {
			addrs=request.getAddresses().get(0).getAddress()!=null?request.getAddresses().get(0).getAddress():"";
    		state=request.getAddresses().get(0).getState()!=null?request.getAddresses().get(0).getState():"";
    		postal=request.getAddresses().get(0).getPin()!=null?request.getAddresses().get(0).getPin():"";
		}
		htmlData.append(addrs);  
		htmlData.append("</td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>DOB :</span>");
		String dob=request.getDob();
		if(dob!=null && !dob.equals(""))
		{
			htmlData.append(dob);
		}
		else
		{
			htmlData.append("NA");
		}	
		htmlData.append("</td>");
		htmlData.append("										<td class='dataValue' width='30%'><span class='fontWeight'>Passport ID : </span>");
		if(passportid!=null && !passportid.equals(""))
		{
			htmlData.append(passportid);
		}
		else
		{
			htmlData.append("NA");
		}
		
		htmlData.append("</td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>State : </span>");
		htmlData.append(state);
		htmlData.append("</td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Gender : </span>");
/*
		   if(request.getGender().trim().equals("1")||request.getGender().trim().toLowerCase().equals("f"))
		                  {
		                	  htmlData.append("Female");
		                  }
		                  else if(request.getGender().trim().equals("2")||request.getGender().trim().toLowerCase().equals("m"))
		                  {
		                	  htmlData.append("Male");
		                  }	
		                  else
		                  {}*/
		htmlData.append("</td>");
		htmlData.append("										<td class='dataValue' width='30%'><span class='fontWeight'>UID : </span>");
				if(uid!=null && !uid.equals(""))
				{
					htmlData.append(uid);
				}
				else
				{
					htmlData.append("NA");
				}
		htmlData.append("</td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Postal : </span>");
		htmlData.append(postal);
		htmlData.append("</td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Inquiry / Request Purpose :</span><%try {%> <%=bean.getInquirypurpose()%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Driver's License : </span>");
				if(drivers_license!=null && !drivers_license.equals(""))
				{
					htmlData.append(drivers_license);
				}
				else
				{
					htmlData.append("NA");
				}
				
		htmlData.append("</td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Address Information 2 : </span>");
		/*String addrs1="",state1="",postal1="";
		if(request.getAddresses()!=null && request.getAddresses().size()>1 && request.getAddresses().get(1)!=null) {
			addrs1=request.getAddresses().get(1).getAddress()!=null?request.getAddresses().get(1).getAddress():"";
    		state1=request.getAddresses().get(1).getState()!=null?request.getAddresses().get(1).getState():"";
    		postal1=request.getAddresses().get(1).getPin()!=null?request.getAddresses().get(1).getPin():"";
		}
		htmlData.append(addrs1);*/ 
		htmlData.append("</td>");
		htmlData.append("									</tr>");
		htmlData.append("									");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Transaction Amount :</span>");
		htmlData.append("0");
		htmlData.append("</td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Home Phone : </span>");

		
		htmlData.append("</td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Address : </span><%try {%> <%=bean.getAddress().get(1).getAddress()%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Inquiry Account 1 :</span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Mobile Phone : </span><%try {%> <%=bean.getmPhone()%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>State : </span><%try {%> <%=bean.getAddress().get(1).getState()%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Inquiry Account 2 :</span><%try {%> <%} catch (Exception ec) {}%></td> ");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Other Phone : </span><%try {%> <%=bean.getoPhone()%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Postal : </span><%try {%> <%=bean.getAddress().get(1).getPostal()%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Inquiry Account 3 :</span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'></span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Address Information 3 : </span><%try {%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Inquiry Account 4 :</span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'></span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Address : </span><%try {%> <%=bean.getAddress().get(2).getAddress()%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'></span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'></span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>State : </span><%try {%> <%=bean.getAddress().get(2).getState()%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'></span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'></span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Postal : </span><%try {%> <%=bean.getAddress().get(2).getPostal()%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("								</tbody>");
		htmlData.append("							</table>");
		htmlData.append("								<%}} } catch (Exception ec) {} %>");
		htmlData.append("									");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("												");
		htmlData.append("												");
		htmlData.append("												");
		htmlData.append("												");
		htmlData.append("												");
		htmlData.append("							<%-- 					<tr style='height: 20px;'>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table");
		htmlData.append("										style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("										align='center' width='100%' cellspacing='0'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr>");
		htmlData.append("												<td height='15px'></td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr height='20'>");
		htmlData.append("												<td class='mainHeader' style='padding-left: 10px;'>Input Enquiry");
		htmlData.append("												</td>");
		htmlData.append("											</tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("									<%");
		htmlData.append("									try ");
		htmlData.append("										{");
		htmlData.append("											JSONObject idandContactInfo = new JSONObject(request.getAttribute('idandcontactinfo')+ '');");
		htmlData.append("											JSONObject personalInfo = idandContactInfo.getJSONObject('personalinfo');");
		htmlData.append("											JSONObject identityinfo = idandContactInfo.getJSONObject('identityinfo');");
		htmlData.append("											JSONArray addressinfo = idandContactInfo.getJSONArray('addressinfo');");
		htmlData.append("											JSONArray phoneinfo = idandContactInfo.getJSONArray('phoneinfo');");
		htmlData.append("											String homeNo='';");
		htmlData.append("											String mobileNo='';");
		htmlData.append("											String otherNo='';");
		htmlData.append("											if(phoneinfo!=null && phoneinfo.length()>0)");
		htmlData.append("											{");
		htmlData.append("												for(int i=0;i<phoneinfo.length();i++)");
		htmlData.append("												{");
		htmlData.append("													JSONObject phone = phoneinfo.getJSONObject(i);");
		htmlData.append("													String typecode = ''+phone.getString('typecode');");
		htmlData.append("													if(typecode.equalsIgnoreCase('H') || typecode.equalsIgnoreCase('Home'))");
		htmlData.append("													{");
		htmlData.append("														homeNo=''+phone.getString('number');");
		htmlData.append("													}");
		htmlData.append("													else if(typecode.equalsIgnoreCase('M') || typecode.equalsIgnoreCase('Mobile'))");
		htmlData.append("													{");
		htmlData.append("														mobileNo=''+phone.getString('number');");
		htmlData.append("													}");
		htmlData.append("													else if(!typecode.equalsIgnoreCase(''))");
		htmlData.append("													{");
		htmlData.append("														otherNo=''+phone.getString('number');");
		htmlData.append("													}");
		htmlData.append("												}");
		htmlData.append("											}");
		htmlData.append("											");
		htmlData.append("									%>");
		htmlData.append("								<table width='100%'>");
		htmlData.append("								<tbody>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataHeader' width='35%' style='padding-left: 10px;'>Personal & Account Information</td>");
		htmlData.append("										<td class='dataHeader' width='30%'>ID & Phone Numbers</td>");
		htmlData.append("										<td class='dataHeader' width='35%'>Contact Details</td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Consumer's First Name : </span><%try {%><%=personalInfo.getJSONObject('name').get('firstname')+ ' '+ personalInfo.getJSONObject('name').get('middlename')+ ' '+ personalInfo.getJSONObject('name').get('lastname')%><%} catch (Exception ec) {} %></td>");
		htmlData.append("										<td class='dataValue' width='30%'><span class='fontWeight'>Pan : </span><%try {%><%=identityinfo.getJSONObject('panid').get('idnumber')%><%} catch (Exception ec) {} %></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>address Information 1 : </span><%try {%> <%} catch (Exception ec) {} %></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Consumer's Family Name :</span><%try {%> <%=personalInfo.getJSONObject('name').get('lastname')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='30%'><span class='fontWeight'>Voter ID : </span><%try {%> <%=identityinfo.getJSONObject('voterid').get('idnumber')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Address : </span><%try {%> <%=addressinfo.getJSONObject(0).get('address')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>DOB :</span><%try {%> <%=personalInfo.getJSONObject('dateofbirth')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='30%'><span class='fontWeight'>Passport ID : </span><%try {%> <%=identityinfo.getJSONObject('passportid').get('idnumber')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>State : </span><%try {%> <%=addressinfo.getJSONObject(0).get('state')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Gender : </span><%try {%> <%=personalInfo.getJSONObject('gender')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='30%'><span class='fontWeight'>UID : </span><%try {%> <%=identityinfo.getJSONObject('nationalidcard').get('idnumber')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Postal : </span><%try {%> <%=addressinfo.getJSONObject(0).get('postal')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Inquiry / Request Purpose :</span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Driver's License : </span><%try {%> <%=identityinfo.getJSONObject('driverlicence').get('idnumber')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Address Information 2 : </span><%try {%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Transaction Amount :</span><%try {%> <%=EquifaxUtill.commaSeprated(''+personalInfo.getJSONObject('totalincome'))%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Home Phone : </span><%try {%> <%=homeNo%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Address : </span><%try {%> <%=addressinfo.getJSONObject(1).get('address')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Inquiry Account 1 :</span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Mobile Phone : </span><%try {%> <%=mobileNo%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>State : </span><%try {%> <%=addressinfo.getJSONObject(1).get('state')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Inquiry Account 2 :</span><%try {%> <%} catch (Exception ec) {}%></td> ");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'>Other Phone : </span><%try {%> <%=otherNo%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Postal : </span><%try {%> <%=addressinfo.getJSONObject(1).get('postal')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Inquiry Account 3 :</span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'></span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Address Information 3 : </span><%try {%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'>Inquiry Account 4 :</span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'></span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Address : </span><%try {%> <%=addressinfo.getJSONObject(2).get('address')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'></span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'></span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>State : </span><%try {%> <%=addressinfo.getJSONObject(2).get('state')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("									<tr style='background: #e5e5e5;'>");
		htmlData.append("										<td class='dataValue' width='35%' style='padding-left: 10px;'><span class='fontWeight'></span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='25%'><span class='fontWeight'></span><%try {%>  <%} catch (Exception ec) {}%></td>");
		htmlData.append("										<td class='dataValue' width='35%'><span class='fontWeight'>Postal : </span><%try {%> <%=addressinfo.getJSONObject(2).get('postal')%> <%} catch (Exception ec) {}%></td>");
		htmlData.append("									</tr>");
		htmlData.append("								</tbody>");
		htmlData.append("							</table>");
		htmlData.append("								<% } catch (Exception ec) {} %>");
		htmlData.append("									");
		htmlData.append("								</td>");
		htmlData.append("							</tr> --%>");
		htmlData.append("								");
		htmlData.append("												");
		htmlData.append("												");
		htmlData.append("												");
		htmlData.append("											</tbody>");
		htmlData.append("										</table>");
		htmlData.append("									</td>");
		htmlData.append("								</tr>");
		htmlData.append("						</tbody>");
		htmlData.append("					</table>");
		htmlData.append("				</td>");
		htmlData.append("			</tr>");
		htmlData.append("		</tbody>");
		htmlData.append("	</table>");
		htmlData.append("");
		htmlData.append("	<!-- before done -->");
		htmlData.append("");
		htmlData.append("");
		htmlData.append("");
		htmlData.append("	<table class='box' align='center' border='0px' cellspacing='0'");
		htmlData.append("		width='100%'>");
		htmlData.append("");
		htmlData.append("		<!-- <tbody>");
		htmlData.append("			<tr>");
		htmlData.append("				<td>");
		htmlData.append("					<table");
		htmlData.append("						style='border-radius: 0px; border-bottom: 2px solid #1675a5;'");
		htmlData.append("						align='center' width='100%' cellspacing='0'>");
		htmlData.append("						<tbody>");
		htmlData.append("							<tr>");
		htmlData.append("								<td height='15px'></td>");
		htmlData.append("							</tr>");
		htmlData.append("							<tr height='20'>");
		htmlData.append("								<td class='mainHeader' style='padding-left: 10px;'>Glossary,");
		htmlData.append("									Terms and Explanations</td>");
		htmlData.append("							</tr>");
		htmlData.append("						</tbody>");
		htmlData.append("					</table>");
		htmlData.append("				</td>");
		htmlData.append("			</tr>");
		htmlData.append("");
		htmlData.append("			<tr>");
		htmlData.append("				<td>");
		htmlData.append("					<table class='box1' align='left' border='0px' cellspacing='0'");
		htmlData.append("						width='100%'>");
		htmlData.append("						<tbody>");
		htmlData.append("						</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataHeader' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>Code</td>");
		htmlData.append("								<td align='left' class='dataHeader' width='80%' style=''>Description</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>000</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Current");
		htmlData.append("									Account</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>CLSD</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Paid");
		htmlData.append("									or closed account/zero balance</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>NEW</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>New");
		htmlData.append("									Account</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>LNSB</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Loan");
		htmlData.append("									Submitted</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>LAND</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Loan");
		htmlData.append("									Approved - Not yet disbursed-</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>INAC</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Account");
		htmlData.append("									is Inactive</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>CON</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Contact");
		htmlData.append("									Member for Status</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px; background: #F1F3F5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>01+</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>1-30");
		htmlData.append("									days past due</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>31+</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>31-60");
		htmlData.append("									days past due</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>DEC</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Loan");
		htmlData.append("									Declined</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>61+</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>61-90");
		htmlData.append("									days past due</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>SPM</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Special");
		htmlData.append("									Mention</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>SUB</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Sub-standard</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>RES</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Restructured");
		htmlData.append("									Loan</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>RGM</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Restructured");
		htmlData.append("									Loan - Govt Mandate</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>RNC</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Restructured");
		htmlData.append("									Loan - Natural Calamity</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>SET</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Settled</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>SF</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Suit");
		htmlData.append("									Filed</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>91+</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>91-120");
		htmlData.append("									days past due</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>121+</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>P121");
		htmlData.append("									- 179 days past due</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>181+</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>180");
		htmlData.append("									or more days past due</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>FPD</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>First");
		htmlData.append("									Payment Default</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>WDF</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Willful");
		htmlData.append("									Default</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>PWOS</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Post");
		htmlData.append("									Written Off Settled</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>STD</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Standard</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>SUB</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Sub-standard</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>DBT</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Doubtful</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>LOSS</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Loss</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>SPM</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Special");
		htmlData.append("									Mention Account</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>SFR</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Suit");
		htmlData.append("									Filed-Restructured</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>SF</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Suit");
		htmlData.append("									Filed</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>WDF</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Willful");
		htmlData.append("									Default</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>SFWD</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Suit");
		htmlData.append("									Filed-Willful Default</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>WOF</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Written");
		htmlData.append("									Off</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>SFWO</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Suit");
		htmlData.append("									Filed and Written Off</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>WDWO</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Willful");
		htmlData.append("									Default and Written Off</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>SWDW</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Suit");
		htmlData.append("									Filed, Willful Default and Written Off</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("							<tr style='height: 20px;' style='background:#e5e5e5;'>");
		htmlData.append("								<td align='left' class='dataValue' width='20%'");
		htmlData.append("									style='padding-left: 10px;'>SET</td>");
		htmlData.append("								<td align='left' class='dataValue' width='80%' style=''>Settled</td>");
		htmlData.append("");
		htmlData.append("							</tr>");
		htmlData.append("						</tbody>");
		htmlData.append("					</table>");
		htmlData.append("				</td>");
		htmlData.append("			</tr>");
		htmlData.append("			<tr>");
		htmlData.append("				<td style='height: 20px;'></td>");
		htmlData.append("			</tr>");
		htmlData.append("");
		htmlData.append("");
		htmlData.append("		</tbody> -->");
		htmlData.append("		<tfoot>");
		htmlData.append("			<tr>");
		htmlData.append("				<td>");
		htmlData.append("					<table width='100%' summary='' align='center' border='0'");
		htmlData.append("						cellspacing='0'>");
		htmlData.append("						<tbody>");
		htmlData.append("							<tr>");
		htmlData.append("								<td>");
		htmlData.append("									<table cellspacing='0' summary='' border='0' width='100%'>");
		htmlData.append("										<tbody>");
		htmlData.append("											<tr style='height: 5px;'>");
		htmlData.append("												<td colspan='10'></td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr>");
		htmlData.append("											<td valign='top' class='disclaimerValue' width='10%' style='color: #333; padding-left: 10px;'>Disclaimer : </td>");
		htmlData.append("											<td class='disclaimerValue' width='90%' style=''>");
		htmlData.append("													<%");
		htmlData.append("														try {");
		htmlData.append("													%> <%=request.getAttribute('disclaimer')==null?'':(String)request.getAttribute('disclaimer')%>");
		htmlData.append("													<%");
		htmlData.append("													 	} catch (Exception ec) {");
		htmlData.append("													 				logger.debug('We are in exception while getting disclaimer Info ' + ec);");
		htmlData.append("													 			}");
		htmlData.append("													 %>");
		htmlData.append("");
		htmlData.append("												</td>");
		htmlData.append("											</tr>");
		htmlData.append("											<tr><td height='20px'></td></tr>");
		htmlData.append("											<tr><td height='20px'></td></tr>");
		htmlData.append("										</tbody>");
		htmlData.append("									</table>");
		htmlData.append("								</td>");
		htmlData.append("							</tr>");
		htmlData.append("						</tbody>");
		htmlData.append("					</table>");
		htmlData.append("				</td>");
		htmlData.append("			</tr>");
		htmlData.append("		</tfoot>");
		htmlData.append("	</table>");
		htmlData.append("");
		htmlData.append("</body>");
		htmlData.append("");
		htmlData.append("</html>");
		/*File file = new File("D:\\opt\\Temp.html");
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		StringBuffer stringBuffer = new StringBuffer();
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringBuffer.append("htmlData.append(\""+line+"\");");
			stringBuffer.append("\n");
		}
		fileReader.close();  */  
			
            fw=new FileWriter("D:\\opt\\kk.html");    
            fw.write(htmlData.toString());    
            
            
		} catch (Exception e1) {
			logger.error("error while creating html");
		} 
		finally {
			if(fw!=null) {
			try {
				fw.close();
			} catch (IOException e) {
			}   
			}
		}
		return "";
	}
/*
	public String getingByteArray1(String url_temp,String requestjson,String key,String reponseJson,String key1)
	{
		String urlParameters;
		try {
			urlParameters = key+"=" + URLEncoder.encode(requestjson, "UTF-8")+"&"+key1+"=" + URLEncoder.encode(reponseJson, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpServletRequest request = new ServletRequestAttributes();
		//MockHttpServletRequest request = new MockHttpServletRequest();
		//HttpServletRequest request=new ;
		request.setAttribute("requestJson", requestjson);
		request.setAttribute("responseFromService", reponseJson);
		
		HttpServletResponse response;
		
		if(url_temp.indexOf("equifaxPcrHtml")!=-1)
		{
			EquifaxMVCService  equifaxmvcservice = new EquifaxMVCServiceImpl();
			equifaxmvcservice.doPostforEquifaxPCS(request, response);
			RequestDispatcher rd = request.getRequestDispatcher("EquifaxPCRNew.jsp");
			rd.forward(request, response);
		}
		
		
		
		StringBuffer htmldata=new StringBuffer();	 
		OutputStream out=null;
		try 
		{
			URL    url            = new URL( url_temp );
			HttpURLConnection conn= (HttpURLConnection) url.openConnection();      
			conn.setDoOutput( true );
			conn.setInstanceFollowRedirects( false );
			conn.setRequestMethod( "POST" );
			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
			conn.setRequestProperty( "charset", "utf-8");
			conn.setRequestProperty("Content-Length", "" +Integer.toString(urlParameters.getBytes().length));
			conn.setUseCaches( false );
			out=conn.getOutputStream();
			out.write(urlParameters.getBytes());
			out.flush();
			out.close();
			conn.connect();
			InputStream ins=conn.getInputStream();
			BufferedReader br= new BufferedReader(new InputStreamReader(ins));
			String line=null;
			while((line =br.readLine())!=null)
			{
				htmldata.append(line);
			} 
		}
		catch(Exception ec)
		{
			logger.error("error while creating byte array");
		}
		return htmldata.toString();
	}
	*/
	
	public String getingByteArray1(String url_temp,String requestjson,String key,String reponseJson,String key1,String customerId,String memberNumber)
	
	{
		StringBuffer htmldata=new StringBuffer();	 
		OutputStream out=null;
		try 
		{
			String urlParameters =key+"=" + URLEncoder.encode(requestjson, "UTF-8")+"&"+key1+"=" + URLEncoder.encode(reponseJson, "UTF-8")+"&CustomerId="+customerId+"&MemberNumber="+memberNumber;
			//String urlParameters =key+"=" + URLEncoder.encode(requestjson, "UTF-8")+"&"+key1+"=" + URLEncoder.encode(reponseJson, "UTF-8");
			URL    url            = new URL( url_temp );
			HttpURLConnection conn= (HttpURLConnection) url.openConnection();      
			conn.setDoOutput( true );
			conn.setInstanceFollowRedirects( false );
			conn.setRequestMethod( "POST" );
			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
			conn.setRequestProperty( "charset", "utf-8");
			conn.setRequestProperty("Content-Length", "" +Integer.toString(urlParameters.getBytes().length));
			conn.setUseCaches( false );
			out=conn.getOutputStream();
			out.write(urlParameters.getBytes());
			out.flush();
			out.close();
			conn.connect();
			InputStream ins=conn.getInputStream();
			BufferedReader br= new BufferedReader(new InputStreamReader(ins));
			String line=null;
			while((line =br.readLine())!=null)
			{
				htmldata.append(line);
			} 
		}
		catch(Exception ec)
		{
			logger.error("error while creating byte array");
		}
		return htmldata.toString();
	}
	
	public String getingByteArray(String url_temp,String requestjson,String key)
	{
		StringBuffer htmldata=new StringBuffer();	 
		OutputStream out=null;
		try 
		{
			//----------------------- HTML CREATTION ------------------------
			String urlParameters =key+"=" + URLEncoder.encode(requestjson, "UTF-8");

			//		  String urlParameters  = key+"="+requestjson;
			//		  byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
			//		  int    postDataLength = postData.length;
			URL    url            = new URL( url_temp );
			HttpURLConnection conn= (HttpURLConnection) url.openConnection();      
			//		  conn.setRequestProperty(key, requestjson);
			conn.setDoOutput( true );
			conn.setInstanceFollowRedirects( false );
			conn.setRequestMethod( "POST" );
			conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
			conn.setRequestProperty( "charset", "utf-8");
			//		  conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
			conn.setRequestProperty("Content-Length", "" +Integer.toString(urlParameters.getBytes().length));
			conn.setUseCaches( false );


			/*CHanged By Anuj */  
			out=conn.getOutputStream();
			out.write(urlParameters.getBytes());
			out.flush();
			out.close();

			/* DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
		     wr.writeBytes(urlParameters );
		     wr.flush();
		     wr.close();
		  }*/
			/*End*/
			conn.connect();
			InputStream ins=conn.getInputStream();
			BufferedReader br= new BufferedReader(new InputStreamReader(ins));
			String line=null;
			while((line =br.readLine())!=null){
				htmldata.append(line);

			} 
		}
		catch(Exception ec)
		{
			logger.error("error while creating byte array");
		}

		return htmldata.toString();
	}
	
	@SuppressWarnings("static-access")
	public String getingByteArray2(String url_temp,String requestjson,String key,HttpServletRequest request)
	{
		URL url = null;
		HttpURLConnection httpsConnection = null;
		HttpServletResponse response =null;
		try 
		{
			url = new URL(url_temp);
		} 
		catch (MalformedURLException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String param = null;
		String requestJson = requestjson;

		if (null != requestJson && !"".equals(requestJson)) 
		{
			param = requestJson;
		}
		if (param == null) 
		{
			try
			{
				//				request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
				InputStream ins = request.getInputStream();
				InputStreamReader insreader = new InputStreamReader(ins);
				BufferedReader breader = new BufferedReader(insreader);
				//			List uriResult = URLEncodedUtils.parse(breader.readLine(), Charset.defaultCharset());
				List uriResult = URLEncodedUtils.parse(new URI(breader.readLine()),"UTF-8");
				String completeRequest = uriResult.get(0).toString();
				StringTokenizer stk = new StringTokenizer(completeRequest, "=");
				stk.nextToken();
				param = stk.nextToken();

			}
			catch (Exception ec) 
			{
				logger.error("Error in Exception " + ec);
			}
		}

		XTrustProvider xTrustProvider = new XTrustProvider();
		xTrustProvider.install();
		try {
			httpsConnection = (HttpURLConnection) url.openConnection();
			HttpsURLConnection.setFollowRedirects(true);
			httpsConnection.setRequestMethod("POST");
			httpsConnection.setDoInput(true);
			httpsConnection.setDoOutput(true);
			httpsConnection.setRequestProperty("Content-Type", "application/json");
			DataOutputStream wr = new DataOutputStream(httpsConnection.getOutputStream());
			wr.writeBytes(param);
			wr.flush();
			wr.close();
			logger.info("content type -->" + httpsConnection.getContentType());
			logger.info("http status code " + httpsConnection.getResponseCode());

			InputStream inputStream = httpsConnection.getInputStream();
			logger.info("inputStream ==" + inputStream.toString());
			byte[] _extData = null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}


}