<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.apache.taglibs.standard.tag.common.core.ForEachSupport"%>
    <html>

    <head>
        <%@page import="org.json.JSONArray"%>
            <%@page import="org.json.JSONObject"%>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                <title>Consumer Base Report</title>
                <style type="text/css">
                    @media print {
                        table {
                            page-break-after: auto;
                            -webkit-print-color-adjust: exact;
                        }
                        thead {
                            display: table-header-group;
                        }
                        tfoot {
                            display: table-footer-group;
                        }
                        body {
                            margin-top: 10px;
                            margin-bottom: 10px;
                            margin-right: 25px;
                            margin-left: 30px;
                        }
                    }
                    
                    .shading {
                        background-color: #e6e6ff;
                        background: #e6e6ff;
                    }
                    
                    .box {
                        background: #FFFFFF;
                        border-style: solid;
                        border-width: thin;
                        border-color: #FFFFFF;
                        border-collapse: collapse;
                        text-align: left;
                        -moz-box-shadow: 0px 0px 30px #DADADA;
                        -webkit-box-shadow: 0px 0px 30px #DADADA;
                        box-shadow: 0px 0px 30px #DADADA;
                    }
                    
                    .box1 {
                        background: #FFFFFF;
                        border-style: solid;
                        border-width: 0px;
                        border-collapse: collapse;
                        text-align: left;
                    }
                    
                    .tabStyle {
                        background: #FFFFFF;
                        border-style: inset;
                        border-width: thin;
                        border-color: black;
                        border-collapse: collapse;
                    }
                    
                    .rowStyle {
                        background: #FFFFFF;
                        border-style: solid;
                        border-width: thin;
                        border-color: grey;
                        border-collapse: collapse;
                    }
                    
                    .box1 tr:nt-child(even) {
                        background-color: white;
                    }
                    
                    .box1 tr:nth-child(odd) {
                        background-color: #F1F3F5;
                    }
                    
                    .style14 {
                        font-face: segoe ui semibold;
                        font-size: 2px;
                    }
                    
                    .summarytable {
                        background: #FFFFFF;
                        border-style: solid;
                        border-width: 0px;
                        border-collapse: collapse;
                        text-align: left;
                        border-left: none;
                        border-right: none;
                    }
                    
                    .reportHead {
                        font-family: segoe ui semibold;
                        font-size: 24px;
                        color: #0f3f6b;
                        font-weight: 600;
                        text-align: left;
                    }
                    
                    .dataHead {
                        font-family: segoe ui semibold;
                        font-size: 12px;
                        font-weight: 600;
                        color: #464646;
                        text-align: right;
                        text-indent: 5px;
                    }
                    
                    .mainHeader {
                        font-family: segoe ui semibold;
                        font-size: 16px;
                        color: #FFFFFF;
                        background: #0f3f6b;
                        text-align: left;
                        font-weight: 600;
                        padding-bottom: 3px;
                    }
                    
                    .subHeader {
                        font-family: segoe ui semibold;
                        font-size: 13px;
                        color: #0f3f6b;
                        text-align: left;
                        border-width: thin;
                        border-collapse: collapse;
                        border-bottom: 1px solid #A7CBE3;
                        border-left: 0px;
                        border-right: 0px;
                        border-top: 0px;
                        background: #FFFFFF;
                        text-indent: 5px;
                        font-weight: 600;
                    }
                    
                    .subHeader1 {
                        font-family: segoe ui semibold;
                        font-size: 13px;
                        color: #0f3f6b;
                        border-width: thin;
                        border-collapse: collapse;
                        border-bottom: 1px solid #A7CBE3;
                        border-left: 0px;
                        border-right: 0px;
                        border-top: 0px;
                        background: #FFFFFF;
                        text-indent: 5px;
                        font-weight: 600;
                    }
                    
                    .dataHeaderNone {
                        font-family: segoe ui semibold;
                        font-size: 14px;
                        color: #0f3f6b;
                        font-weight: 600;
                        text-align: center;
                        text-indent: 5px;
                        white-space: nowrap;
                        height: 23;
                        valign: middle
                    }
                    
                    .subHeader2 {
                        font-family: segoe ui semibold;
                        border-collapse: collapse;
                        border-bottom: 0px;
                        border-left: 1px solid #ffffff;
                        border-right: 0px;
                        border-top: 1px solid #ffffff;
                        background: #FFFFFF;
                        text-indent: 5px;
                        font-weight: 600;
                        white-space: nowrap;
                    }
                    
                    .dataHeader {
                        font-family: segoe ui semibold;
                        font-size: 13px;
                        color: #0f3f6b;
                        font-weight: 600;
                        text-align: left;
                        text-indent: 5px;
                        white-space: nowrap;
                        padding-top: 2px;
                    }
                    
                    .dataHeaderScore {
                        font-family: segoe ui semibold;
                        font-size: 12px;
                        color: #464646;
                        font-weight: 600;
                        text-align: left;
                        text-indent: 5px;
                        white-space: nowrap;
                        padding-top: 2px;
                    }
                    
                    .dataValueValue {
                        font-family: segoe ui semibold;
                        font-size: 25px;
                        font-weight: 600;
                        color: #464646;
                        text-align: left;
                        padding-left: 7px;
                        padding-top: 1px;
                    }
                    
                    .dataValuePerform {
                        font-family: segoe ui semibold;
                        font-size: 12px;
                        font-weight: 600;
                        color: #464646;
                        text-align: left;
                        padding-left: 7px;
                        padding-top: 1px;
                    }
                    
                    .dataValuePerform2 {
                        border-collapse: separate;
                        Color: #464646;
                        font-family: segoe ui semibold;
                        font-size: 12px;
                        font-weight: 280;
                    }
                    
                    .dataHeadern {
                        font-family: segoe ui semibold;
                        font-size: 13px;
                        color: #0f3f6b;
                        font-weight: 600;
                        text-align: left;
                        text-indent: 5px;
                        padding-top: 2px;
                    }
                    
                    .dataValue {
                        font-family: segoe ui semibold;
                        font-size: 14px;
                        font-weight: 600;
                        color: #464646;
                        text-align: left;
                        padding-left: 7px;
                        padding-top: 1px;
                    }
                    
                    .dataAmtValue {
                        font-family: segoe ui semibold;
                        font-size: 14px;
                        font-weight: 600;
                        color: #464646;
                        text-align: right;
                        padding-right: 7px;
                        padding-top: 1px;
                    }
                    
                    .dataHeader1 {
                        font-family: segoe ui semibold;
                        font-size: 12px;
                        color: #0f3f6b;
                        font-weight: 600;
                        text-align: left;
                        text-indent: 5px;
                    }
                    
                    .dataValue1 {
                        font-family: segoe ui semibold;
                        font-size: 12px;
                        font-weight: 600;
                        color: #464646;
                        text-align: left;
                        text-indent: 5px;
                    }
                    
                    .mainAccHeader {
                        font-family: segoe ui semibold;
                        font-size: 13px;
                        color: #FFFFFF;
                        background: #0f3f6b;
                        font-weight: 600;
                    }
                    
                    .AccHeader {
                        font-family: segoe ui semibold;
                        font-size: 13px;
                        color: #0f3f6b;
                        font-weight: 600;
                        text-indent: 5px;
                    }
                    
                    .subAccHeader {
                        font-family: segoe ui semibold;
                        font-size: 13px;
                        color: #0f3f6b;
                        background: #e6e6ff;
                        font-weight: 600;
                        border-width: thin;
                        border-bottom: 1px solid #A7CBE3;
                        border-left: 1px solid #A7CBE3;
                        border-right: 1px solid #A7CBE3;
                        border-top: 1px solid #A7CBE3;
                    }
                    
                    .AccValue {
                        font-family: segoe ui semibold;
                        font-size: 14px;
                        font-weight: 600;
                        color: #464646;
                        text-indent: 5px;
                    }
                    
                    .AccValue1 {
                        font-family: segoe ui semibold;
                        font-size: 12px;
                        font-weight: 600;
                        color: #464646;
                        text-indent: 5px;
                        border-width: thin;
                        border-bottom: 1px solid #A7CBE3;
                        border-left: 1px solid #A7CBE3;
                        border-right: 1px solid #A7CBE3;
                        border-top: 1px solid #A7CBE3;
                    }
                    
                    .AccSummaryTab {
                        border-width: thin;
                        border-collapse: collapse;
                        border-left: 1px solid #A7CBE3;
                        border-right: 1px solid #A7CBE3;
                        border-top: 1px solid #A7CBE3;
                        border-bottom: 0px;
                        text-indent: 5px;
                    }
                    
                    .disclaimerValue {
                        font-family: segoe ui semibold;
                        font-size: 12px;
                        font-weight: 500;
                        color: grey;
                    }
                    
                    .infoValue {
                        font-family: segoe ui semibold;
                        font-size: 12px;
                        font-weight: 500;
                        color: grey;
                        padding-right: 15px;
                        font-style: normal;
                    }
                    
                    .maroonFields {
                        color: Maroon;
                        font-family: segoe ui semibold;
                        font-size: 15px;
                        font-weight: 600;
                    }
                    
                    .AccValueComm2 {
                        font-family: segoe ui semibold;
                        font-size: 11px;
                        font-weight: 600;
                        color: #464646;
                        text-indent: 5px;
                        border-width: thin;
                        border-bottom: 1px solid #A7CBE3;
                        border-left: 1px solid #A7CBE3;
                        border-right: 1px solid #A7CBE3;
                        border-top: 1px solid #A7CBE3;
                    }
                    
                    .AccValue2 {
                        font-family: segoe ui semibold;
                        font-size: 11px;
                        font-weight: 600;
                        color: #464646;
                        text-indent: 5px;
                        border-width: thin;
                        border-bottom: 1px solid #A7CBE3;
                        border-left: 1px solid #A7CBE3;
                        border-right: 1px solid #A7CBE3;
                        border-top: 1px solid #A7CBE3;
                    }
                    
                    .container {
                        /* this will give container dimension, because floated child nodes don't give any */
                        /* if your child nodes are inline-blocked, then you don't have to set it */
                        overflow: auto;
                    }
                    
                    .container .headActive {
                        /* float your elements or inline-block them to display side by side */
                        float: left;
                        /* these are height and width dimensions of your header */
                        height: 10em;
                        width: 1.5em;
                        /* set to hidden so when there's too much vertical text it will be clipped. */
                        overflow: hidden;
                        /* these are not relevant and are here to better see the elements */
                        background: #ffe1dc;
                        color: #be0000;
                        margin-right: 1px;
                        font-family: segoe ui;
                        font-weight: bold;
                    }
                    
                    .container .headActive .vertActive {
                        /* line height should be equal to header width so text will be middle aligned */
                        line-height: 1.5em;
                        /* setting background may yield better results in IE text clear type rendering */
                        background: #ffe1dc;
                        color: #be0000;
                        display: block;
                        /* this will prevent it from wrapping too much text */
                        white-space: nowrap;
                        /* so it stays off the edge */
                        padding-left: 3px;
                        font-family: segoe ui;
                        font-weight: bold;
                        /* CSS3 specific totation code */
                        /* translate should have the same negative dimension as head height */
                        transform: rotate(-270deg) translate(1em, 0);
                        transform-origin: -5px 30px;
                        -moz-transform: rotate(-270deg) translate(1em, 0);
                        -moz-transform-origin: -5px 30px;
                        -webkit-transform: rotate(-270deg) translate(1em, 0);
                        -webkit-transform-origin: -5px 30px;
                        -ms-transform-origin: none;
                        -ms-transform: none;
                        -ms-writing-mode: tb-rl;
                        *writing-mode: tb-rl;
                    }
                    
                    .container .headClosed {
                        /* float your elements or inline-block them to display side by side */
                        float: left;
                        /* these are height and width dimensions of your header */
                        height: 10em;
                        width: 1.5em;
                        /* set to hidden so when there's too much vertical text it will be clipped. */
                        overflow: hidden;
                        /* these are not relevant and are here to better see the elements */
                        background: #e1f0be;
                        color: #415a05;
                        margin-right: 1px;
                        font-family: segoe ui;
                        font-weight: bold;
                    }
                    
                    .container .headClosed .vertClosed {
                        /* line height should be equal to header width so text will be middle aligned */
                        line-height: 1.5em;
                        /* setting background may yield better results in IE text clear type rendering */
                        background: #ffe1dc;
                        color: #415a05;
                        display: block;
                        /* this will prevent it from wrapping too much text */
                        white-space: nowrap;
                        /* so it stays off the edge */
                        padding-left: 3px;
                        font-family: segoe ui;
                        font-weight: bold;
                        /* CSS3 specific totation code */
                        /* translate should have the same negative dimension as head height */
                        transform: rotate(-270deg) translate(1em, 0);
                        transform-origin: -5px 30px;
                        -moz-transform: rotate(-270deg) translate(1em, 0);
                        -moz-transform-origin: -5px 30px;
                        -webkit-transform: rotate(-270deg) translate(1em, 0);
                        -webkit-transform-origin: -5px 30px;
                        -ms-transform-origin: none;
                        -ms-transform: none;
                        -ms-writing-mode: tb-rl;
                        *writing-mode: tb-rl;
                    }
                    
                    .infoValueNote {
                        font-family: segoe ui semibold;
                        font-size: 11px;
                        font-weight: 500;
                        color: grey;
                        padding-right: 15px;
                        font-style: normal;
                    }
                </style>
    </head>

    <body style="font-family: segoe ui semibold, arial, verdana;">
        <table class="box" align="center" border="0px" cellpadding="0" cellspacing="0" width="1050px">
            <thead>
                <tr>
                    <td>
                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                                <tr>
                                    <td>
                                        <table align="center" border="0" width="1020px">
                                            <tbody>
                                                <tr height="10">
                                                    <td></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="2" valign="top"><img src="images/qualtech.jpg" alt="CRIF HighMark Credit Information Services Pvt. Ltd." align="left" width="166" height="72" /></td>
                                                    <td width="120"></td>
                                                    <td align="left" width="380" valign="top">
                                                        <table border="0" cellpadding="0" cellspacing="0">
                                                            <tbody>
                                                                <tr>
                                                                    <td align="left" class="reportHead">CONSUMER BASE&trade; REPORT
                                                                        <br></br>
                                                                    </td>
                                                                </tr>
                                                                <tr valign="top">
                                                                    <td class="dataHead" align="right" valign="top">
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                    <td width="70"></td>
                                                    <td rowspan="2" align="right" valign="top" width="350">
                                                        <table>
                                                            <tbody>
                                                                <%try{ 
														    String payload=request.getAttribute("payload")+"";
														    %>
                                                                    <tr>
                                                                        <td class="dataHeader1">Enquiry Control Number</td>
                                                                        <td class="dataValue1">
                                                                            <%=new JSONObject(payload).get("enquiryControlNumber")%>
                                                                        </td>
                                                                    </tr>
                                                                    <%}catch(Exception ec){} %>
                                                                        <tr>
                                                                            <td class="dataHeader1">Prepared For:</td>
                                                                            <td class="dataValue1"></td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td class="dataHeader1"></td>
                                                                            <td class="dataValue1"></td>
                                                                        </tr>
                                                                        <%try{ 
																String payload=request.getAttribute("payload")+"";
																String processedDate=new JSONObject(payload).get("processedDate")+"";
																String day=processedDate.substring(0, 2);
																String month=processedDate.substring(2, 4);
																String year=processedDate.substring(4,8 );

															%>
                                                                            <tr>
                                                                                <td class="dataHeader1">Processed Date:</td>
                                                                                <td class="dataValue1">
                                                                                    <%=day+"-"+month+"-"+year%>
                                                                                </td>
                                                                            </tr>
                                                                            <%}catch(Exception ec){} %>
                                                                                <tr>
                                                                                    <td class="dataHeader1"></td>
                                                                                    <td class="dataValue1"></td>
                                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="10">
                                        <hr size="1" style="color: #C8C8C8;" />
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>
                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                            <tbody>
                                <tr>
                                    <td>
                                        <table align="center" border="0" cellpadding="0" cellspacing="0" width="1020px">
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <table align="center" bgcolor="#0f3f6b" border="0" width="1020px">
                                                            <tbody>
                                                                <tr height="20">
                                                                    <td width="10"></td>
                                                                    <td class="mainHeader">Inquiry Input Information</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <table align="center" border="0" cellpadding="0" cellspacing="0" width="1030px">
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <table align="center" border="0" width="1030px">
                                                            <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <%
																   	try{
		   															   String payload=request.getAttribute("payload")+"";
		   															   String firstName=new JSONObject(payload).get("name1")+" "+new JSONObject(payload).get("name2")+" "+new JSONObject(payload).get("name3")+" "+new JSONObject(payload).get("name4")+" "+new JSONObject(payload).get("name5");
		   															   String dateofBirth=new JSONObject(payload).get("dateofBirth")+"";
		   															   String day=dateofBirth.substring(0, 2);
		   															   String month=dateofBirth.substring(2, 4);
		   															   String year=dateofBirth.substring(4,8 );
		   															   String gendercode=new JSONObject(payload).get("gender")+"";
		   															   String gender="";
		   															    if ("1".equals(gendercode)) {
		   															    	gender="Female";
																   		} else if ("2".equals(gendercode)) {
																   			gender="Male";
																   		} else {
																   			gender="Transgender";
																   		}

		   															    JSONArray telephones=new JSONObject(payload+"").getJSONArray("telephones");

																   %>
                                                                            <table border="0" width="1030px">
                                                                                <tbody>
                                                                                    <tr>
                                                                                        <td height="10px"></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td align="left" width="110 px" class="dataHeader">Name:</td>
                                                                                        <td align="left" width="270 px" class="dataValue">
                                                                                            <%=firstName %>
                                                                                        </td>
                                                                                        <td width="70 px" class="dataHeader">DOB/Age:</td>
                                                                                        <td width="190 px" class="dataValue">
                                                                                            <%=day+"-"+month+"-"+year%>
                                                                                        </td>
                                                                                        <td width="70 px" class="dataHeader">Gender:</td>
                                                                                        <td width="200 px" class="dataValue">
                                                                                            <%=gender %>
                                                                                        </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td height="5px"></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td align="left" width="100 px" class="dataHeader">Father:</td>
                                                                                        <td align="left" width="200 px" class="dataValue"></td>
                                                                                        <td width="70 px" class="dataHeader">Spouse:</td>
                                                                                        <td width="100 px" class="dataValue"></td>
                                                                                        <td width="70 px" class="dataHeader">Mother:</td>
                                                                                        <td width="120 px" class="dataValue"></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td height="5px"></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td class="dataHeader" valign="top" width="100 px">Telephone Number:</td>

                                                                                        <%for(int i=0;i<telephones.length();i++){
																				JSONObject telephone=telephones.getJSONObject(i);
																				%>
                                                                                            <td valign="top">
                                                                                                <table width="200px" cellpadding="0" cellspacing="0">

                                                                                                    <tr>
                                                                                                        <td class="dataValue">
                                                                                                            <%=telephone.get("telephoneNumber") %>
                                                                                                        </td>
                                                                                                    </tr>

                                                                                                </table>
                                                                                            </td>
                                                                                            <%
																				break;
																				} %>

                                                                                                <td class="dataHeader" valign="top">ID(s):</td>
                                                                                                <td valign="top">
                                                                                                    <table width="200px" cellpadding="0" cellspacing="0">
                                                                                                        <tr>
                                                                                                            <td class="dataValue">
                                                                                                            </td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                            <td class="dataValue"></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                            <td class="dataValue"></td>
                                                                                                        </tr>
                                                                                                    </table>
                                                                                                </td>
                                                                                                <td class="dataHeader" valign="top"></td>
                                                                                                <td valign="top">
                                                                                                    <table width="200px" cellpadding="0" cellspacing="0">
                                                                                                        <tr>
                                                                                                            <td class="dataValue"></td>
                                                                                                        </tr>
                                                                                                        <tr>
                                                                                                            <td class="dataValue"></td>
                                                                                                        </tr>
                                                                                                    </table>
                                                                                                </td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td height="5px"></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td align="left" width="100 px" class="dataHeader"></td>
                                                                                        <td align="left" width="200 px" class="dataValue" colspan="5"></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td height="5px"></td>
                                                                                    </tr>

                                                                                    <tr>
                                                                                        <td align="left" width="100 px" class="dataHeader"></td>
                                                                                        <td align="left" width="200 px" class="dataValue" colspan="5"></td>
                                                                                    </tr>

                                                                                    <tr>
                                                                                        <td height="5px"></td>
                                                                                    </tr>
                                                                                    <tr>
                                                                                        <td align="left" width="100 px" class="dataHeader">Other Address:
                                                                                        </td>
                                                                                        <td align="left" width="200 px" class="dataValue" colspan="5"></td>
                                                                    </td>
                                                                    </tr>
                                                                    </tbody>
                                                                    </table>
                                                    </td>
                                                    </tr>
                                                    </tbody>
                                                    </table>

                                                    <%}catch(Exception ec){}%>
                                    </td>
                                    </tr>
                                    </tbody>
                                    </table>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="center" border="0" cellpadding="0" cellspacing="0" width="1020px">
                            <tbody>
                                <tr>
                                    <td>
                                        <table align="center" bgcolor="#0f3f6b" border="0" width="1020px">
                                            <tbody>
                                                <tr height="20">
                                                    <td width="10"></td>
                                                    <td class="mainHeader"></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td>
                        <table align="left" border="0" cellpadding="0" cellspacing="0" width="1020px">
                            <tbody>
                                <tr>
                                    <td>
                                        <table border="0" align="left" style="width: 1028px; padding-left: 8px;">
                                            <tbody>
                                                <tr>
                                                    <td height="10px" colspan="2"></td>
                                                </tr>

                                                <%try{ 
											  String payload=request.getAttribute("payload")+"";
											  String  scorecardName=new JSONObject(payload).get("scorecardName")+""; 
											  String scoredate=new JSONObject(payload).get("scoredate")+"";
											  String score=new JSONObject(payload).get("score")+"";
											  String day=scoredate.substring(0, 2);
											  String month=scoredate.substring(2, 4);
										      String year=scoredate.substring(4,8 );
											%>
                                                    <tr>
                                                        <td align="left" width="200 px" class="dataHeader" style="padding-left: 20px;">Score Card Name</td>
                                                        <td align="center" width="200 px" class="dataHeader" style="padding-left: 31px;">&nbsp;
                                                            <%=scorecardName%>
                                                        </td>
                                                        <td align="left" style="width: 485px;" class="dataHeader">Score Date</td>
                                                    </tr>

                                                    <tr class="shading">
                                                        <td align="left" width="200 px" class="dataHeaderScore" style="padding-left: 20px;">Score</td>
                                                        <td align="center" valign="top" class="dataValueValue" style="padding-left: 33px; width: 360px;">&nbsp;
                                                            <%=score%><span class="dataValuePerform2" style="width: 300px; vertical-align: middle; padding-left: 5px;"></span></td>
                                                        <td align="left" width="300 px">&nbsp;<span style="font-size: 14px; font-weight: bold;">&nbsp;</span><span class="dataValuePerform"><%=day+"-"+month+"-"+year %></span></td>
                                                    </tr>
                                                    <%}catch(Exception ec){} %>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                    <tr height="20">
                        <td class="infoValueNote" style="padding-right: 9px;" align="right" bgcolor="#FFFFFF"></td>
                    </tr>
                </tr>
                <tr>
                    <td></td>
                </tr>
                </td>
                </tr>
                <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
                <html>

                <head>
                    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
                    <title>Consumer Base Report</title>
                    <style type="text/css">
                        @media print {
                            table {
                                page-break-after: auto;
                                -webkit-print-color-adjust: exact;
                            }
                            thead {
                                display: table-header-group;
                            }
                            tfoot {
                                display: table-footer-group;
                            }
                            body {
                                margin-top: 10px;
                                margin-bottom: 10px;
                                margin-right: 25px;
                                margin-left: 30px;
                            }
                        }
                        
                        .shading {
                            background-color: #e6e6ff;
                            background: #e6e6ff;
                        }
                        
                        .box {
                            background: #FFFFFF;
                            border-style: solid;
                            border-width: thin;
                            border-color: #FFFFFF;
                            border-collapse: collapse;
                            text-align: left;
                            -moz-box-shadow: 0px 0px 30px #DADADA;
                            -webkit-box-shadow: 0px 0px 30px #DADADA;
                            box-shadow: 0px 0px 30px #DADADA;
                        }
                        
                        .box1 {
                            background: #FFFFFF;
                            border-style: solid;
                            border-width: 0px;
                            border-collapse: collapse;
                            text-align: left;
                        }
                        
                        .tabStyle {
                            background: #FFFFFF;
                            border-style: inset;
                            border-width: thin;
                            border-color: black;
                            border-collapse: collapse;
                        }
                        
                        .rowStyle {
                            background: #FFFFFF;
                            border-style: solid;
                            border-width: thin;
                            border-color: grey;
                            border-collapse: collapse;
                        }
                        
                        .box1 tr:nt-child(even) {
                            background-color: white;
                        }
                        
                        .box1 tr:nth-child(odd) {
                            background-color: #F1F3F5;
                        }
                        
                        .style14 {
                            font-face: segoe ui semibold;
                            font-size: 2px;
                        }
                        
                        .summarytable {
                            background: #FFFFFF;
                            border-style: solid;
                            border-width: 0px;
                            border-collapse: collapse;
                            text-align: left;
                            border-left: none;
                            border-right: none;
                        }
                        
                        .reportHead {
                            font-family: segoe ui semibold;
                            font-size: 24px;
                            color: #0f3f6b;
                            font-weight: 600;
                            text-align: left;
                        }
                        
                        .dataHead {
                            font-family: segoe ui semibold;
                            font-size: 12px;
                            font-weight: 600;
                            color: #464646;
                            text-align: right;
                            text-indent: 5px;
                        }
                        
                        .mainHeader {
                            font-family: segoe ui semibold;
                            font-size: 16px;
                            color: #FFFFFF;
                            background: #0f3f6b;
                            text-align: left;
                            font-weight: 600;
                            padding-bottom: 3px;
                        }
                        
                        .subHeader {
                            font-family: segoe ui semibold;
                            font-size: 13px;
                            color: #0f3f6b;
                            text-align: left;
                            border-width: thin;
                            border-collapse: collapse;
                            border-bottom: 1px solid #A7CBE3;
                            border-left: 0px;
                            border-right: 0px;
                            border-top: 0px;
                            background: #FFFFFF;
                            text-indent: 5px;
                            font-weight: 600;
                        }
                        
                        .subHeader1 {
                            font-family: segoe ui semibold;
                            font-size: 13px;
                            color: #0f3f6b;
                            border-width: thin;
                            border-collapse: collapse;
                            border-bottom: 1px solid #A7CBE3;
                            border-left: 0px;
                            border-right: 0px;
                            border-top: 0px;
                            background: #FFFFFF;
                            text-indent: 5px;
                            font-weight: 600;
                        }
                        
                        .dataHeaderNone {
                            font-family: segoe ui semibold;
                            font-size: 14px;
                            color: #0f3f6b;
                            font-weight: 600;
                            text-align: center;
                            text-indent: 5px;
                            white-space: nowrap;
                            height: 23;
                            valign: middle
                        }
                        
                        .subHeader2 {
                            font-family: segoe ui semibold;
                            border-collapse: collapse;
                            border-bottom: 0px;
                            border-left: 1px solid #ffffff;
                            border-right: 0px;
                            border-top: 1px solid #ffffff;
                            background: #FFFFFF;
                            text-indent: 5px;
                            font-weight: 600;
                            white-space: nowrap;
                        }
                        
                        .dataHeader {
                            font-family: segoe ui semibold;
                            font-size: 13px;
                            color: #0f3f6b;
                            font-weight: 600;
                            text-align: left;
                            text-indent: 5px;
                            white-space: nowrap;
                            padding-top: 2px;
                        }
                        
                        .dataHeaderScore {
                            font-family: segoe ui semibold;
                            font-size: 12px;
                            color: #464646;
                            font-weight: 600;
                            text-align: left;
                            text-indent: 5px;
                            white-space: nowrap;
                            padding-top: 2px;
                        }
                        
                        .dataValueValue {
                            font-family: segoe ui semibold;
                            font-size: 25px;
                            font-weight: 600;
                            color: #464646;
                            text-align: left;
                            padding-left: 7px;
                            padding-top: 1px;
                        }
                        
                        .dataValuePerform {
                            font-family: segoe ui semibold;
                            font-size: 12px;
                            font-weight: 600;
                            color: #464646;
                            text-align: left;
                            padding-left: 7px;
                            padding-top: 1px;
                        }
                        
                        .dataValuePerform2 {
                            border-collapse: separate;
                            Color: #464646;
                            font-family: segoe ui semibold;
                            font-size: 12px;
                            font-weight: 280;
                        }
                        
                        .dataHeadern {
                            font-family: segoe ui semibold;
                            font-size: 13px;
                            color: #0f3f6b;
                            font-weight: 600;
                            text-align: left;
                            text-indent: 5px;
                            padding-top: 2px;
                        }
                        
                        .dataValue {
                            font-family: segoe ui semibold;
                            font-size: 14px;
                            font-weight: 600;
                            color: #464646;
                            text-align: left;
                            padding-left: 7px;
                            padding-top: 1px;
                        }
                        
                        .dataAmtValue {
                            font-family: segoe ui semibold;
                            font-size: 14px;
                            font-weight: 600;
                            color: #464646;
                            text-align: right;
                            padding-right: 7px;
                            padding-top: 1px;
                        }
                        
                        .dataHeader1 {
                            font-family: segoe ui semibold;
                            font-size: 12px;
                            color: #0f3f6b;
                            font-weight: 600;
                            text-align: left;
                            text-indent: 5px;
                        }
                        
                        .dataValue1 {
                            font-family: segoe ui semibold;
                            font-size: 12px;
                            font-weight: 600;
                            color: #464646;
                            text-align: left;
                            text-indent: 5px;
                        }
                        
                        .mainAccHeader {
                            font-family: segoe ui semibold;
                            font-size: 13px;
                            color: #FFFFFF;
                            background: #0f3f6b;
                            font-weight: 600;
                        }
                        
                        .AccHeader {
                            font-family: segoe ui semibold;
                            font-size: 13px;
                            color: #0f3f6b;
                            font-weight: 600;
                            text-indent: 5px;
                        }
                        
                        .subAccHeader {
                            font-family: segoe ui semibold;
                            font-size: 13px;
                            color: #0f3f6b;
                            background: #e6e6ff;
                            font-weight: 600;
                            border-width: thin;
                            border-bottom: 1px solid #A7CBE3;
                            border-left: 1px solid #A7CBE3;
                            border-right: 1px solid #A7CBE3;
                            border-top: 1px solid #A7CBE3;
                        }
                        
                        .AccValue {
                            font-family: segoe ui semibold;
                            font-size: 14px;
                            font-weight: 600;
                            color: #464646;
                            text-indent: 5px;
                        }
                        
                        .AccValue1 {
                            font-family: segoe ui semibold;
                            font-size: 12px;
                            font-weight: 600;
                            color: #464646;
                            text-indent: 5px;
                            border-width: thin;
                            border-bottom: 1px solid #A7CBE3;
                            border-left: 1px solid #A7CBE3;
                            border-right: 1px solid #A7CBE3;
                            border-top: 1px solid #A7CBE3;
                        }
                        
                        .AccSummaryTab {
                            border-width: thin;
                            border-collapse: collapse;
                            border-left: 1px solid #A7CBE3;
                            border-right: 1px solid #A7CBE3;
                            border-top: 1px solid #A7CBE3;
                            border-bottom: 0px;
                            text-indent: 5px;
                        }
                        
                        .disclaimerValue {
                            font-family: segoe ui semibold;
                            font-size: 12px;
                            font-weight: 500;
                            color: grey;
                        }
                        
                        .infoValue {
                            font-family: segoe ui semibold;
                            font-size: 12px;
                            font-weight: 500;
                            color: grey;
                            padding-right: 15px;
                            font-style: normal;
                        }
                        
                        .maroonFields {
                            color: Maroon;
                            font-family: segoe ui semibold;
                            font-size: 15px;
                            font-weight: 600;
                        }
                        
                        .AccValueComm2 {
                            font-family: segoe ui semibold;
                            font-size: 11px;
                            font-weight: 600;
                            color: #464646;
                            text-indent: 5px;
                            border-width: thin;
                            border-bottom: 1px solid #A7CBE3;
                            border-left: 1px solid #A7CBE3;
                            border-right: 1px solid #A7CBE3;
                            border-top: 1px solid #A7CBE3;
                        }
                        
                        .AccValue2 {
                            font-family: segoe ui semibold;
                            font-size: 11px;
                            font-weight: 600;
                            color: #464646;
                            text-indent: 5px;
                            border-width: thin;
                            border-bottom: 1px solid #A7CBE3;
                            border-left: 1px solid #A7CBE3;
                            border-right: 1px solid #A7CBE3;
                            border-top: 1px solid #A7CBE3;
                        }
                        
                        .container {
                            /* this will give container dimension, because floated child nodes don't give any */
                            /* if your child nodes are inline-blocked, then you don't have to set it */
                            overflow: auto;
                        }
                        
                        .container .headActive {
                            /* float your elements or inline-block them to display side by side */
                            float: left;
                            /* these are height and width dimensions of your header */
                            height: 10em;
                            width: 1.5em;
                            /* set to hidden so when there's too much vertical text it will be clipped. */
                            overflow: hidden;
                            /* these are not relevant and are here to better see the elements */
                            background: #ffe1dc;
                            color: #be0000;
                            margin-right: 1px;
                            font-family: segoe ui;
                            font-weight: bold;
                        }
                        
                        .container .headActive .vertActive {
                            /* line height should be equal to header width so text will be middle aligned */
                            line-height: 1.5em;
                            /* setting background may yield better results in IE text clear type rendering */
                            background: #ffe1dc;
                            color: #be0000;
                            display: block;
                            /* this will prevent it from wrapping too much text */
                            white-space: nowrap;
                            /* so it stays off the edge */
                            padding-left: 3px;
                            font-family: segoe ui;
                            font-weight: bold;
                            /* CSS3 specific totation code */
                            /* translate should have the same negative dimension as head height */
                            transform: rotate(-270deg) translate(1em, 0);
                            transform-origin: -5px 30px;
                            -moz-transform: rotate(-270deg) translate(1em, 0);
                            -moz-transform-origin: -5px 30px;
                            -webkit-transform: rotate(-270deg) translate(1em, 0);
                            -webkit-transform-origin: -5px 30px;
                            -ms-transform-origin: none;
                            -ms-transform: none;
                            -ms-writing-mode: tb-rl;
                            *writing-mode: tb-rl;
                        }
                        
                        .container .headClosed {
                            /* float your elements or inline-block them to display side by side */
                            float: left;
                            /* these are height and width dimensions of your header */
                            height: 10em;
                            width: 1.5em;
                            /* set to hidden so when there's too much vertical text it will be clipped. */
                            overflow: hidden;
                            /* these are not relevant and are here to better see the elements */
                            background: #e1f0be;
                            color: #415a05;
                            margin-right: 1px;
                            font-family: segoe ui;
                            font-weight: bold;
                        }
                        
                        .container .headClosed .vertClosed {
                            /* line height should be equal to header width so text will be middle aligned */
                            line-height: 1.5em;
                            /* setting background may yield better results in IE text clear type rendering */
                            background: #ffe1dc;
                            color: #415a05;
                            display: block;
                            /* this will prevent it from wrapping too much text */
                            white-space: nowrap;
                            /* so it stays off the edge */
                            padding-left: 3px;
                            font-family: segoe ui;
                            font-weight: bold;
                            /* CSS3 specific totation code */
                            /* translate should have the same negative dimension as head height */
                            transform: rotate(-270deg) translate(1em, 0);
                            transform-origin: -5px 30px;
                            -moz-transform: rotate(-270deg) translate(1em, 0);
                            -moz-transform-origin: -5px 30px;
                            -webkit-transform: rotate(-270deg) translate(1em, 0);
                            -webkit-transform-origin: -5px 30px;
                            -ms-transform-origin: none;
                            -ms-transform: none;
                            -ms-writing-mode: tb-rl;
                            *writing-mode: tb-rl;
                        }
                        
                        .infoValueNote {
                            font-family: segoe ui semibold;
                            font-size: 11px;
                            font-weight: 500;
                            color: grey;
                            padding-right: 15px;
                            font-style: normal;
                        }
                    </style>
                </head>

                <body style="font-family: segoe ui semibold, arial, verdana;">
                    <table class="box" align="center" border="0px" cellpadding="0" cellspacing="0" width="1050px">
                        <tbody>
                            <tr>
                                <td></td>
                            </tr>
                            <tr>
                                <td>
                                    <table align="center" border="0" cellpadding="0" cellspacing="0" width="1020px">
                                        <tbody>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
          
                                <tr>
                                    <td height="20px"></td>
                                </tr>
                               
                                <tr>
                                    <td height="20px"></td>
                                </tr>
                                <tr>
                                    <td height="30px"></td>
                                </tr>
                                <tr>
                                    <td>
                                        <table cellpadding="0" cellspacing="0" align="center" border="0">
                                            <tbody>
                                                <tr height="10"></tr>
                                                <tr>
                                                    <td>
                                                        <table width="1020px" align="center" bgcolor="#0f3f6b" border="0">
                                                            <tbody>
                                                                <tr height="20">
                                                                    <td width="10"></td>
                                                                    <td class="mainHeader">Inquiries</td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr height="10"></tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="5"></td>
                                </tr>
                                <tr>
                                    <td>
                                        <table cellpadding="0" cellspacing="0" align="center" border="0">
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <table class="box1" cellpadding="0" cellspacing="0" width="1000px" align="center" border="0px">
                                                            <tbody>
                                                                <tr height="20">

                                                                    <td width="160" class="subHeader" align="right">Date of Inquiry
                                                                    </td>
                                                                    <td width="160" class="subHeader" align="left">Enquiry Short Name</td>
                                                                    <td width="160" class="subHeader" align="right">Enquiry Purpose</td>
                                                                    <td width="160" class="subHeader" align="left">Enquiry Amount</td>

                                                                </tr>
                                                                <%try{ 
											String payload=request.getAttribute("payload")+"";   
										   JSONArray enquiries=new JSONObject(payload).getJSONArray("enquiries");

											for(int i=0;i<enquiries.length();i++)
											{
												JSONObject enquiry=enquiries.getJSONObject(i);
												String dateOfEnquiry=enquiry.get("dateOfEnquiry")+"";
												String day=dateOfEnquiry.substring(0, 2);
												String month=dateOfEnquiry.substring(2, 4);
											    String year=dateOfEnquiry.substring(4,8 );
											try{	
										   %>
                                                                    <tr height="20">

                                                                        <td width="160" class="AccValue1" align="right">
                                                                            <%=day+"-"+month+"-"+year%>
                                                                        </td>
                                                                        <td width="160" class="AccValue1" align="left">
                                                                            <%=enquiry.get("enquiryShortName")%>
                                                                        </td>
                                                                        <td width="160" class="AccValue1" align="right">
                                                                            <%=enquiry.get("enquiryPurpose")%>
                                                                        </td>
                                                                        <td width="160" class="AccValue1" align="left">
                                                                            <%=enquiry.get("enquiryAmount")%>
                                                                        </td>

                                                                    </tr>
                                                                    <%
											}
											catch(Exception ec){}
											}
										  }catch(Exception ec){} %>

                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                                            <tbody>
                                                <tr>
                                                    <td>
                                                        <table width="1000px">
                                                            <tbody>
                                                                <tr>
                                                                    <td align="center" class="AccHeader">-END OF INDIVIDUAL REPORT-
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="10"></td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                                                            <tbody>
                                                                <tr height="10"></tr>
                                                                <tr>
                                                                    <td>
                                                                        <table align="center" bgcolor="#0f3f6b" border="0" width="1020px">
                                                                            <tbody>
                                                                                <tr height="20">
                                                                                    <td width="10"></td>
                                                                                    <td class="mainHeader">Appendix</td>
                                                                                </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                                <tr height="10"></tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="5"></td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table align="center" border="0" cellpadding="0" cellspacing="0">
                                                            <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <table class="box1" align="center" border="0px" cellpadding="0" cellspacing="0" width="1000px">
                                                                            <tbody>
                                                                                <tr height="20">
                                                                                    <td align="left" class="subHeader" width="250">Inquiry Pupose</td>

                                                                                    <td align="left" class="subHeader" width="480">Account type</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">01</td>
                                                                                    <td align="left" class="dataValue1" width="480">
                                                                                        Auto Loan (Personal) </td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">02</td>
                                                                                    <td align="left" class="dataValue1" width="480">
                                                                                        Housing Loan </td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">03</td>
                                                                                    <td align="left" class="dataValue1" width="480">
                                                                                        Property Loan </td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">04</td>
                                                                                    <td align="left" class="dataValue1" width="480">Loan Against Shares/Securities </td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">05</td>
                                                                                    <td align="left" class="dataValue1" width="480">Personal Loan</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">06</td>
                                                                                    <td align="left" class="dataValue1" width="480">Consumer Loan</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">07</td>
                                                                                    <td align="left" class="dataValue1" width="480">Gold Loan</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">08</td>
                                                                                    <td align="left" class="dataValue1" width="480">Education Loan</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">09</td>
                                                                                    <td align="left" class="dataValue1" width="480">Loan to Professional</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">10</td>
                                                                                    <td align="left" class="dataValue1" width="480">Credit Card</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">11</td>
                                                                                    <td align="left" class="dataValue1" width="480">Leasing
                                                                                    </td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">12</td>
                                                                                    <td align="left" class="dataValue1" width="480">Overdraft</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">13</td>
                                                                                    <td align="left" class="dataValue1" width="480">Two-wheeler Loan </td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">14</td>
                                                                                    <td align="left" class="dataValue1" width="480">Non-Funded Credit Facility</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">15</td>
                                                                                    <td align="left" class="dataValue1" width="480">Loan Against Bank Deposits</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">16</td>
                                                                                    <td align="left" class="dataValue1" width="480">Fleet Card </td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">17</td>
                                                                                    <td align="left" class="dataValue1" width="480">Commercial Vehicle Loan</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">18</td>
                                                                                    <td align="left" class="dataValue1" width="480">Telco - Wireless</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">19</td>
                                                                                    <td align="left" class="dataValue1" width="480">Telco - Broadband </td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">20</td>
                                                                                    <td align="left" class="dataValue1" width="480">Telco - Landline </td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">31</td>
                                                                                    <td align="left" class="dataValue1" width="480">Secured Credit Card </td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">32</td>
                                                                                    <td align="left" class="dataValue1" width="480">Used Car Loan</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">33</td>
                                                                                    <td align="left" class="dataValue1" width="480">Construction Equipment Loan </td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">34</td>
                                                                                    <td align="left" class="dataValue1" width="480">Tractor Loan</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">35</td>
                                                                                    <td align="left" class="dataValue1" width="480">Corporate Credit Card</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">36</td>
                                                                                    <td align="left" class="dataValue1" width="480">Kisan Credit Card</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">37</td>
                                                                                    <td align="left" class="dataValue1" width="480">Loan on Credit Card</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">38</td>
                                                                                    <td align="left" class="dataValue1" width="480">Prime Minister Jaan Dhan Yojana - Overdraft</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">39</td>
                                                                                    <td align="left" class="dataValue1" width="480">Mudra Loans - Shishu / Kishor / Tarun</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">40</td>
                                                                                    <td align="left" class="dataValue1" width="480">Microfinance - Business Loan</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">41</td>
                                                                                    <td align="left" class="dataValue1" width="480">Microfinance - Personal Loan</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">42</td>
                                                                                    <td align="left" class="dataValue1" width="480">Microfinance - Housing Loan</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">43</td>
                                                                                    <td align="left" class="dataValue1" width="480">Microfinance - Other</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">44</td>
                                                                                    <td align="left" class="dataValue1" width="480">Pradhan Mantri Awas Yojana - Credit Link Subsidy Scheme MAY CLSS</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">50</td>
                                                                                    <td align="left" class="dataValue1" width="480">Business Loan - Secured</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">51</td>
                                                                                    <td align="left" class="dataValue1" width="480">Business Loan - General</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">52</td>
                                                                                    <td align="left" class="dataValue1" width="480">Business Loan - Priority Sector - Small Business</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">53</td>
                                                                                    <td align="left" class="dataValue1" width="480">Business Loan - Priority Sector - Agriculture</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">54</td>
                                                                                    <td align="left" class="dataValue1" width="480">Business Loan - Priority Sector - Others</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">55</td>
                                                                                    <td align="left" class="dataValue1" width="480">Business Non-Funded Credit Facility - General</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">56</td>
                                                                                    <td align="left" class="dataValue1" width="480">Business Non-Funded Credit Facility - Priority Sector – Small Business</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">57</td>
                                                                                    <td align="left" class="dataValue1" width="480">Business Non-Funded Credit Facility - Priority Sector – Agriculture</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">58</td>
                                                                                    <td align="left" class="dataValue1" width="480">Business Non-Funded Credit Facility - Priority Sector-Others</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">59</td>
                                                                                    <td align="left" class="dataValue1" width="480">Business Loan Against Bank Deposits</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">61</td>
                                                                                    <td align="left" class="dataValue1" width="480">Business Loan - Unsecured</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">88</td>
                                                                                    <td align="left" class="dataValue1" width="480">Locate Plus for Insurance (Applicable to Enquiry Purpose only)</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">90</td>
                                                                                    <td align="left" class="dataValue1" width="480">Account Review (Applicable to Enquiry Purpose only)</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">91</td>
                                                                                    <td align="left" class="dataValue1" width="480">Retro Enquiry (Applicable to Enquiry Purpose only)</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">92</td>
                                                                                    <td align="left" class="dataValue1" width="480">Locate Plus (Applicable to Enquiry Purpose only)</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">97</td>
                                                                                    <td align="left" class="dataValue1" width="480">Adviser Liability (Applicable to Enquiry Purpose only)</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">00</td>
                                                                                    <td align="left" class="dataValue1" width="480">Others</td>
                                                                                </tr>
                                                                                <tr height="20" bgcolor="#F1F3F5">
                                                                                    <td align="left" class="dataValue1" width="250">98</td>
                                                                                    <td align="left" class="dataValue1" width="480">Secured (Account Group for Portfolio Review response)</td>
                                                                                </tr>
                                                                                <tr height="20">
                                                                                    <td align="left" class="dataValue1" width="250">99</td>
                                                                                    <td align="left" class="dataValue1" width="480">Unsecured (Account Group for Portfolio Review response)</td>
                                                                                </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="20"></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>
                                <tfoot>
                                    <tr>
                                        <td>
                                            <table summary="" align="center" border="0" cellpadding="0" cellspacing="0">
                                                <tbody>
                                                    <tr>
                                                        <td>
                                                            <table summary="" border="0" width="1020px">
                                                                <tbody>
                                                                    <tr height="10">
                                                                        <td colspan="5">
                                                                            <hr color="silver">
                                                                        </td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td color="#CCCCCC" valign="top" width="70" class="disclaimerValue"></td>
                                                                        <td colspan="4" class="disclaimerValue"></td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td>
                                                                            <br></br>
                                                                            <br></br>
                                                                        </td>
                                                                        <td color="#CCCCCC " align="left" width="300" class="disclaimerValue"></td>
                                                                        <td color="#CCCCCC " align="center" width="400" class="disclaimerValue"></td>
                                                                        <td color="#CCCCCC " align="right" width="300" class="disclaimerValue"></td>
                                                                        <td width="70">
                                                                            <br></br>
                                                                            <br></br>
                                                                        </td>
                                                                    </tr>
                                                                </tbody>
                                                            </table>
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </td>
                                    </tr>
                                </tfoot>
                        </tbody>
                    </table>
                </body>

                </html>
