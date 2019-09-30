<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMCRAccountSummary"%>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrEnquiry"%>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMCRIncomeDetails" %>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrAccountDetail" %>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrFamilyDetail" %>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrIdentityDetail" %>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrAddressDetail" %>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrPersonalDetail" %>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrPhoneDetails" %>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrAdditionalMFIDetail" %>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrHistory24MonthsDetail"%>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrKeyPersonDetail"%>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrNomineeDetail" %>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrAcntDtlMfiAddress" %>
<%@page import="com.qualtech.equifax.api.entity.mcr.EquifaxMcrAcntDtlMfiIdentification" %>
<%@page import="com.qualtech.equifax.api.entity.common.EquifaxOtherKeyInd" %>
<%@page import="com.qualtech.equifax.api.entity.common.PreviousName" %>
<%@page import="com.qualtech.equifax.api.utils.EquifaxUtill"%>
<%@page    import="com.qualtech.equifax.api.entity.common.EquifaxPcsInquiryRequestInfo"%>
<%@page import="com.qualtech.equifax.api.common.dto.Relation" %>

<%@page import="java.util.List"%>
<%@page import="org.apache.log4j.Logger"%>
<%
    Logger logger = Logger.getLogger("EquifaxMFINew.jsp");
        logger.debug("start executing EquifaxMFINew ");
%>
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Stardos Stencil'
    rel='stylesheet' />
<meta http-equiv="Content-Type"
    content="text/html; charset=windows-1252" />
<title>MFI BASIC CREDIT REPORT V1.0</title>

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
    background-color: #1675a5;
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
    border-color: #333;
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
    background-color: #e5e5e5;
}

.style14 {
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
    font-family: Arial;
    font-size: 24px;
    color: #1675a5; font-weight:  normal;
    text-align: left;
}

.dataHead {
    font-family: Arial;
    font-size: 12px;
    font-weight: normal;
    color: #464646;
    text-align: right;
    text-indent: 5px;
}

.mainHeader {
    font-family: Arial;
    font-size: 13px;
    color: #1675a5;
    background: #fff;;
    text-align: left;
    font-weight: normal;
    padding-bottom: 3px;
    padding: 5px 0;
}

hr.mainHeader {
    padding: 0;
    background: #1c82d0;
    height: 2px;
    border: none;
}

.subHeader {
    font-family: Arial;
    font-size: 13px;
    color: #1675a5; text-align:  left;
    border-width: thin;
    border-collapse: collapse;
    border-bottom: 1px solid #A7CBE3;
    border-left: 0px;
    border-right: 0px;
    border-top: 0px;
    background: #FFFFFF;
    text-indent: 5px;
    font-weight: normal;
}

.subHeader1 {
    font-family: Arial;
    font-size: 13px;
    color: #1675a5; border-width:  thin;
    border-collapse: collapse;
    border-bottom: 1px solid #A7CBE3;
    border-left: 0px;
    border-right: 0px;
    border-top: 0px;
    background: #FFFFFF;
    text-indent: 5px;
    font-weight: normal;
}

.dataHeaderNone {
    font-family: Arial;
    font-size: 14px;
    color: #1675a5;
    font-weight: normal;
    text-align: center;
    text-indent: 5px;
    white-space: nowrap;
    height: 23;
    valign: middle
}

.subHeader2 {
    font-family: Arial;
    border-collapse: collapse;
    border-bottom: 0px;
    border-left: 1px solid #ffffff;
    border-right: 0px;
    border-top: 1px solid #ffffff;
    background: #FFFFFF;
    text-indent: 5px;
    font-weight: normal;
    white-space: nowrap;
}

.dataHeader {
    background:none;
    font-family: Arial;
    font-size: 12px;
    color: #1675a5;
    font-weight: normal;
    text-align: left;
    text-indent: 5px;
    white-space: nowrap;
    padding: 5px 0;
    text-transform: capitalize;
}

.grey-label {color:#666;}

.dataHeaderScore {
    font-family: Arial;
    font-size: 12px;
    color: #464646;
    font-weight: normal;
    text-align: left;
    text-indent: 5px;
    white-space: nowrap;
    padding-top: 2px;
}

.dataValueValue {
    font-family: Arial;
    font-size: 25px;
    font-weight: normal;
    color: #464646;
    text-align: left;
    padding-left: 7px;
    padding-top: 1px;
}

.dataValuePerform {
    font-family: Arial;
    font-size: 12px;
    font-weight: normal;
    color: #464646;
    text-align: left;
    padding-left: 7px;
    padding-top: 1px;
}

.dataValuePerform2 {
    border-collapse: separate;
    Color: #464646;
    font-family: Arial;
    font-size: 12px;
    font-weight: 280;
}

.dataHeadern {
    font-family: Arial;
    font-size: 13px;
    color: #1675a5; font-weight:  normal;
    text-align: left;
    text-indent: 5px;
    padding-top: 2px;
}

.dataValue {
    font-family: Arial;
    font-size: 11px;
    font-weight: 400;
    color: #666;
    text-align: left;
    padding: 5px 0;
    text-transform: capitalize;
}

.dataAmtValue {
    font-family: Arial;
    font-size: 14px;
    font-weight: normal;
    color: #464646;
    text-align: right;
    padding-right: 7px;
    padding-top: 1px;
}

.dataHeader1 {
    font-family: Arial;
    font-size: 12px;
    color: #1675a5;
    font-weight: normal;
    text-align: left;
    text-indent: 5px;
}

.dataValue1 {
    font-family: Arial;
    font-size: 12px;
    font-weight: normal;
    color: #464646;
    text-align: left;
    text-indent: 5px;
}

.mainAccHeader {
    font-family: Arial;
    font-size: 13px;
    color: #FFFFFF;
    background: #1675a5; font-weight:  normal;
}

.AccHeader {
    font-family: Arial;
    font-size: 13px;
    color: #1675a5;
    font-weight: normal;
    text-indent: 5px;
}

/*.subAccHeader {
    font-family: Arial;
    font-size: 13px;
        color: #ffffff;
    background: #1675a5;
    font-weight: normal;
    border-width: thin;
    border-bottom: 1px solid #A7CBE3;
    border-left: 1px solid #A7CBE3;
    border-right: 1px solid #A7CBE3;
    border-top: 1px solid #A7CBE3;
}*/
.AccValue {
    font-family: Arial;
    font-size: 14px;
    font-weight: normal;
    color: #464646;
    text-indent: 5px;
}

/*.AccValue1 {
    font-family: Arial;
    font-size: 12px;
    font-weight: normal;
    color: #464646;
    text-indent: 5px;
    border-width: thin;
    border-bottom: 1px solid #A7CBE3;
    border-left: 1px solid #A7CBE3;
    border-right: 1px solid #A7CBE3;
    border-top: 1px solid #A7CBE3;
}*/
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
    font-family: Arial;
    font-size: 11px;
    font-weight: normal;
    color: #999;
}

.infoValue {
    font-family: Arial;
    font-size: 12px;
    font-weight: 500;
    color: grey;
    padding-right: 15px;
    font-style: normal;
}

.maroonFields {
    color: Maroon;
    font-family: Arial;
    font-size: 15px;
    font-weight: normal;
}

/*.AccValueComm2 {
    font-family: Arial;
    font-size: 12px;
    font-weight: normal;
    color: #464646;
    text-indent: 5px;
    border-width: thin;
    border-bottom: 1px solid #A7CBE3;
    border-left: 1px solid #A7CBE3;
    border-right: 1px solid #A7CBE3;
    border-top: 1px solid #A7CBE3;
}*/
.AccValue2 {
    font-family: Arial;
    font-size: 12px;
    font-weight: normal;
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
    float: left; /* these are height and width dimensions of your header */
    height: 10em;
    width: 1.5em;
    /* set to hidden so when there's too much vertical text it will be clipped. */
    overflow: hidden;
    /* these are not relevant and are here to better see the elements */
    background: #ffe1dc;
    color: #be0000;
    margin-right: 1px;
    font-family: segoe ui;
    font-weight: normal;
}

.container .headActive .vertActive {
    /* line height should be equal to header width so text will be middle aligned */
    line-height: 1.5em;
    /* setting background may yield better results in IE text clear type rendering */
    background: #ffe1dc;
    color: #be0000;
    display: block; /* this will prevent it from wrapping too much text */
    white-space: nowrap; /* so it stays off the edge */
    padding-left: 3px;
    font-family: segoe ui;
    font-weight: normal; /* CSS3 specific totation code */
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
    float: left; /* these are height and width dimensions of your header */
    height: 10em;
    width: 1.5em;
    /* set to hidden so when there's too much vertical text it will be clipped. */
    overflow: hidden;
    /* these are not relevant and are here to better see the elements */
    background: #e1f0be;
    color: #415a05;
    margin-right: 1px;
    font-family: segoe ui;
    font-weight: normal;
}

.container .headClosed .vertClosed {
    /* line height should be equal to header width so text will be middle aligned */
    line-height: 1.5em;
    /* setting background may yield better results in IE text clear type rendering */
    background: #ffe1dc;
    color: #415a05;
    display: block; /* this will prevent it from wrapping too much text */
    white-space: nowrap; /* so it stays off the edge */
    padding-left: 3px;
    font-family: segoe ui;
    font-weight: normal; /* CSS3 specific totation code */
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
    font-family: Arial;
    font-size: 12px;
    font-weight: 500;
    color: grey;
    padding-right: 15px;
    font-style: normal;
}

.grey-bg {background: #f0f2f4;}
.subHeading {color: #333; font-size: 13px;}
.italic-font {font-style: italic;}
.fontWeight {font-weight: normal;color:#333;}

</style>

</head>
<body style="font-family: segoe ui semibold, arial, verdana;">

    <table class="box" align="center" border="0px"
        cellspacing="0" width="100%">
        <thead>
            <tr>
                <td>
                    <table align="center" border="0" width="100%" cellspacing="0">
                        <tbody>
                            <tr style="height: 10px">
                                <td></td>
                            </tr>
                            <tr>
                                <td width="15%" style="padding-left: 10px;"><img
                                    src="images/equifax-logo.png" alt="Equifax Services"
                                    align="left" style="float: none; display: inline-block;" /></td>
                                <td align="center" width="70%" valign="top">
                                    <table border="0" cellspacing="0" style="width: 100%;">
                                        <tbody>
                                            <tr>
                                                <td align="center" class="reportHead"
                                                    style="color: #333; text-align: center; font-size: 14px;"><b>Equifax MFI Basic Credit Report</b></td>
                                            </tr>
                                            <tr valign="top">
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                                <td align="right" valign="top" width="15%"></td>
                            </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        </thead>


        <tbody>
        <tr>
                <td>
                    <table align="left" border="0" cellspacing="0" width="100%">
                        <tbody>
                            <tr>
                                <td>
                                    <table align="left" border="0" cellspacing="0" width="100%">
                                        <tbody>
                                            <tr>
                                                <td
                                                    style="border-bottom: 2px solid #1675a5; color: #1675a5;">
                                                    <table align="left" style="border-radius: 0px;" border="0"
                                                        width="100%;">
                                                        <tbody>
                                                            <tr height="20">
                                                                <td class="mainHeader"
                                                                    style="font-size: 14px; padding-left: 10px;">Consumer
                                                                    CIR</td>
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
                                    <table align="center" border="0" cellpadding="0"
                                        cellspacing="0" width="100%">
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <table align="center" border="0" width="100%">
                                                        <tbody>
                                                            <tr>
                                                                <td>
                                                                    <table border="0" width="100%">
                                                                        <tbody>
                                                                            <tr style="background: #e5e5e5;">
                                                                                <td align="left" width="144 px"
                                                                                    style="font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;">
                                                                                    <span class="dataHeader"
                                                                                    style="font-size: 12px; padding-left: 10px">Consumer Name
                                                                                        :</span> <span class="grey-label"><%try { %> <%= request.getAttribute("fullName")%>
                                                                                    <%} catch (Exception ec) {}%></span>
                                                                                </td>
                                                                                <td align="left" width="144 px"
                                                                                    style="font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;">
                                                                                    <span class="dataHeader"
                                                                                    style="font-size: 12px; padding-left: 10px">Date
                                                                                        :</span> <span class="grey-label"><%try {%> <%=new JSONObject(request.getAttribute("responsetransaction") + "").get("date")%>
                                                                                    <%} catch (Exception e) {logger.debug("We are in exception while getting key of date from responsetransaction" + e);}%></span>
                                                                                </td>
                                                                            </tr>
                                                                            <tr style="background: #e5e5e5;">
                                                                                <td align="left" width="171px"
                                                                                    style="font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;">
                                                                                    <span class="dataHeader"
                                                                                    style="font-size: 12px; padding-left: 10px">Client
                                                                                        Id :</span> <span class="grey-label"><%try {%><%=request.getAttribute("CustomerId")%><%} catch (Exception ec) {}%></span>
                                                                                </td>
                                                                                <td align="left" width="171px"
                                                                                    style="font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;">
                                                                                    <span class="dataHeader"
                                                                                    style="font-size: 12px; padding-left: 10px">Time
                                                                                        : </span> <span class="grey-label"><%try {%> <%=new JSONObject(request.getAttribute("responsetransaction") + "").get("time")%></span>
                                                                                    <%} catch (Exception e) {logger.debug("We are in exception while getting key of time from responsetransaction" + e);} %>
                                                                                </td>
                                                                            </tr>
                                                                            <tr style="background: #e5e5e5;">
                                                                                <td align="left" width="114px"
                                                                                    style="font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;">
                                                                                    <span class="dataHeader"
                                                                                    style="font-size: 12px; padding-left: 10px">
                                                                                        Customer Id :</span> <span class="grey-label"><%try { %><%=new JSONObject(request.getAttribute("GeneralUserInfo") + "").get("consumerId")%> <%} catch (Exception ec) {}%></span>
                                                                                </td>
                                                                                <td align="left" width="114px"
                                                                                    style="font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;">
                                                                                    <span class="dataHeader"
                                                                                    style="font-size: 12px; padding-left: 10px">Report Order No. :</span>
                                                                                    <%-- <%-- <span class="grey-label"><%try {%> <%=request.getAttribute("reportorderno")%><%} catch (Exception ec) {}%></span> --%>
                                                                                    <span class="grey-label"><%try {%><%=new JSONObject(request.getAttribute("responsetransaction") + "").get("reportorderno")%><%} catch (Exception ec) {}%></span>
                                                                                </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td height="20px"></td>
                                                                            </tr>
                                                                        </tbody>
                                                                    </table>

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
                        </tbody>
                    </table>
                </td>
            </tr>
            <tr>
                <td>
                    <table width="100%">
                        <tbody>
                            <tr><td><hr style="margin:0; width: 100%;" size="1" color="#c8c8c8" /></td></tr>
                            <tr style="background: #e5e5e5;">
                                <td align="left" class="dataHeader" style="padding-left: 10px;">Consumer Name : 
                                <span align="left" class="dataValue">
                                        
                                    <%
                                        try 
                                        {
                                            if(request.getAttribute("personalDetails")!=null)
                                            {
                                            List<EquifaxMcrPersonalDetail> mcrPersonalDetails=(List<EquifaxMcrPersonalDetail>) request.getAttribute("personalDetails");
                                                    if(!mcrPersonalDetails.isEmpty())
                                                    {
                                                        for(EquifaxMcrPersonalDetail personalDetail:mcrPersonalDetails)
                                                        {
                                                        String fullName="";
                                                        String fName=" ";
                                                        String mName=" ";
                                                        String lName=" "; 
                                                        if(personalDetail.getFirst_name()!=null 
                                                                && !personalDetail.getFirst_name().equals("")
                                                                && !personalDetail.getFirst_name().equalsIgnoreCase("null")
                                                                && !personalDetail.getFirst_name().equalsIgnoreCase("NA")
                                                                )
                                                        {
                                                            fName=personalDetail.getFirst_name().trim()+" ";
                                                        }
                                                        if(personalDetail.getMiddle_name()!=null
                                                            && !personalDetail.getMiddle_name().equals("")
                                                            && !personalDetail.getMiddle_name().equalsIgnoreCase("null")
                                                            && !personalDetail.getMiddle_name().equalsIgnoreCase("NA")
                                                            )
                                                        {
                                                            mName=personalDetail.getMiddle_name().trim()+" ";
                                                        }
                                                        if(personalDetail.getLast_name()!=null
                                                                && !personalDetail.getLast_name().equals("")
                                                                && !personalDetail.getLast_name().equalsIgnoreCase("null")
                                                                && !personalDetail.getLast_name().equalsIgnoreCase("NA")
                                                                )
                                                        {
                                                            lName=personalDetail.getLast_name().trim();
                                                        }
                                                        fullName=((fName+mName).trim()+" "+lName).trim();
                                    %> 
                                        <%=fullName%> 
                                        <%
                                             }}} }catch (Exception ec) {}
                                         %>
                                </span>
                                </td>
                            </tr>
                            
                        </tbody>
                    </table>
                    
                    <!-- Personal Info -->
                    <table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="left"
                        width="100%" cellspacing="0">
                        <tbody>
                            <tr>
                                <td height="15px"></td>
                            </tr>
                            <tr height="20">
                                <td class="mainHeader" style="font-size: 14px; padding-left:10px;">Personal Information </td>    
                            </tr>
                        </tbody>
                    </table>
                    <table width="100%" border="0" cellspacing="0">
                        <tbody>
                            <tr>
                                <td class="dataHeader" style="padding-left: 10px;" width="25%">Previous Name : </td>
                                <td class="dataHeader" width="25%">Additional MiddleName : </td>
                                <td class="dataHeader" width="25%">Alias Name : </td>
                                <td class="dataHeader" width="25%">DOB : </td>
                                
                            </tr>
                            <tr style="background: #e5e5e5;">
                                <td class="dataValue" style="padding-left: 10px;" width="25%">
                                    <span>
                                        <%
                                            try {
                                                    String fullName="";
                                                    if(request.getAttribute("fullName")!=null){
                                                    fullName=(String)request.getAttribute("fullName");
                                                }
                                        %> <%=fullName%> <%
     } catch (Exception ec) {
                                         logger.debug("We are in exception while getting Name from personalInfo " + ec);
                                      }
 %>
                                    </span>
                                </td>
                                <td class="dataValue" width="25%">
                                    <span>
                                        <%
                                            try {
                                        %> <%=request.getAttribute("additionalmiddlename")==null?"":(String)request.getAttribute("additionalmiddlename")%> <%
     } catch (Exception ec) {
                                              logger.debug("We are in exception while getting additionalmiddlename from personalInfo " + ec);
                                      }
 %>
                                    </span>
                                </td>
                                <td class="dataValue" width="25%">
                                    <span>
                                        <%
                                            try {
                                        %> <%=request.getAttribute("aliasname")==null?"":(String)request.getAttribute("aliasname")%> <%
     } catch (Exception ec) {
                                              logger.debug("We are in exception while getting aliasname from personalInfo " + ec);
                                      }
 %>
                                
                                    </span>
                                </td>
                                <td class="dataValue" width="25%">
                                    <span>
                                        <%
                                            try {
                                        %> <%=request.getAttribute("dateofbirth")==null?"":(String)request.getAttribute("dateofbirth")%> <%
     } catch (Exception ec) {
                                              logger.debug("We are in exception while getting dateofbirth from personalInfo " + ec);
                                      }
 %>
                                    </span>
                                </td>
                                
                            </tr>
                            <tr>
                                <td class="dataHeader" style="padding-left: 10px;" width="25%">Gender : </td>
                                <td class="dataHeader" width="25%">Marital Status : </td> 
                                <td class="dataHeader" width="25%">Reported Date : </td> 
                                <td class="dataHeader" width="25%">Age : </td>
                            </tr>
                            <tr style="background: #e5e5e5;"> 
                                <td class="dataValue" style="padding-left: 10px;" width="25%">
                                    <span>
                                        <%
                                            try {
                                        %> <%=request.getAttribute("gender")==null?"":(String)request.getAttribute("gender")%> <%
     } catch (Exception ec) {
                                             logger.debug("We are in exception while getting gender from personalInfo " + ec);
                                     }
 %>
                                    </span>
                                </td>
                                 <td class="dataValue" width="25%">
                                    <span>
                                        <%
                                            try {
                                        %> <%=request.getAttribute("maritalstatus")==null?"":(String)request.getAttribute("maritalstatus")%> <%
     } catch (Exception ec) {
                                             logger.debug("We are in exception while getting maritalstatus from personalInfo " + ec);
                                     }
 %>
                                    </span>
                                </td> 
                                <td class="dataValue" width="25%">
                                    <span>
                                        <%
                                            try {
                                        %> <%=request.getAttribute("reporteddate")==null?"":(String)request.getAttribute("reporteddate")%> <%
     } catch (Exception ec) {
                                             logger.debug("We are in exception while getting reporteddate from personalInfo " + ec);
                                     }
 %>
                                    </span>
                                </td> 
                                
                                <td class="dataValue" width="25%"> 
                                    <span>
                                        <%
                                            try {
                                        %> <%=request.getAttribute("age")==null?"":(String)request.getAttribute("age")%> <%
     } catch (Exception ec) {
                                             logger.debug("We are in exception while getting age from personalInfo " + ec);
                                     }
 %>
                                    </span>
                                </td>
                            </tr>

                            
                        </tbody>
                    </table>
                    </td>
                    </tr>
                    <!-- Personal Info Ends Here -->
        <!--Phone details Start  -->
            <tr>
                <td>
                    
                    <table align="center" border="0" cellspacing="0" width="100%">
                        <tbody>
                            <tr style="height:10px;"></tr>
                            <tr>
                                <td>
                                    <table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="left"
                                        width="100%" cellspacing="0">
                                        <tbody>
                                            <tr>
                                                <td height="15px"></td>
                                            </tr>
                                            <tr height="20">
                                                <td class="mainHeader" style="font-size: 14px; padding-left:10px;">Consumer Phone Details
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>                            
                            </tr>
                            <tr>
                                <td>
                                    <table align="center" border="0" 
                                        width="100%">
                                        <tbody>

                                            <tr style="height:20px;">
                                                <td class="dataHeader" width="25%" style="padding-left: 10px;">Seq</td>
                                                <td class="dataHeader" width="25%">Number</td>
                                                <td class="dataHeader" width="25%">Type Code</td>
                                                <td class="dataHeader" width="25%">Date Reported</td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>
                                    <table align="left" border="0" width="100%">
                                        <tbody>
                                        <tr style="height:1px;"><td></td></tr>
                                            <%
                                                try {
                                                                                            if(request.getAttribute("phoneDetails")!=null){
                                                                                                List<EquifaxMcrPhoneDetails> mcrPhoneDetails=(List<EquifaxMcrPhoneDetails>) request.getAttribute("phoneDetails");
                                                                                                                    if(!mcrPhoneDetails.isEmpty()){
                                                                                                                        int i=-1;    
                                                                                                                    for(EquifaxMcrPhoneDetails phoneInfo:mcrPhoneDetails){i++;
                                            %> 
                                                                                    
                                                <%
                                                                                                                                         if(i%2==0)
                                                                                                                                                                             {
                                                                                                                                     %>
                                                <tr style="background: #e5e5e5">
                                                <%
                                                    }
                                                                                        else
                                                                                        {
                                                %>
                                                <tr>
                                                <%
                                                    }
                                                %>                                    
                                                <td class="dataValue" width="25%"
                                                    style="  padding-left: 10px;">
                                                    <%=i+1%>
                                                </td>

                                                <td class="dataValue" width="25%"
                                                    style="word-wrap: break-word; ">
                                                    <%
                                                        try {
                                                    %> <%=phoneInfo.getPhoneNumber()%> <%
     } catch (Exception ec) {
                                                              logger.debug("We are in exception while getting of type from phoneInfo " + ec);
                                                          }
 %>
                                                </td>

                                                <td class="dataValue" width="25%" style=" ">
                                                    <%
                                                        try {
                                                    %> <%=phoneInfo.getPhoneTypecode()%> <%
     } catch (Exception ec) {
                                                              logger.debug("We are in exception while getting of type from phoneInfo " + ec);
                                                          }
 %>
                                                </td>
                                                <td class="dataValue" width="25%">
                                                    <%
                                                        try {
                                                    %> <%=phoneInfo.getPhoneReportedDate()%> <%
     } catch (Exception ec) {
                                                              logger.debug("We are in exception while getting of type from phoneInfo " + ec);
                                                          }
 %>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                                                                    }}} catch (Exception ec) {
                                                                                        logger.debug("We are in exception while getting error of phoneInfo " + ec);
                                                                                    }
                                            %>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            <tr style="height: 20px;"></tr>
                        </tbody>
                    </table>
                </td>
            </tr>
            
            
            <!--Phone details end  -->
            <!--Address details Start  -->
            <tr>
                <td>
                    <table align="center" border="0" cellspacing="0" width="100%">
                        <tbody>
                            <tr style="height:10px;"></tr>
                            <tr>
                                <td>
                                    <table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="center"
                                        width="100%" cellspacing="0">
                                        <tbody>
                                            <tr>
                                                <td height="15px"></td>
                                            </tr>
                                            <tr height="20">
                                                <td class="mainHeader" style="font-size: 14px; padding-left:10px;">Consumer Address
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>                            
                            </tr>
                            <tr>
                                <td>
                                    <table align="center" border="0" 
                                        width="100%">
                                        <tbody>

                                            <tr style="height:20px;">
                                                <td class="dataHeader" width="10%" style="padding-left: 10px;">Type</td>
                                                <td class="dataHeader" width="65%">Address</td>
                                                <td class="dataHeader" width="10%">State</td>
                                                <!-- <td class="dataHeader" width="10%">Postal</td> -->
                                                <td class="dataHeader" width="15%">Date Reported</td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>
                                    <table align="center" border="0" width="100%">
                                        <tbody>
                                        <tr style="height:1px;"><td></td></tr>
                                            <%
                                                try {
                                                                                            if(request.getAttribute("addressDetails")!=null){
                                                                                                List<EquifaxMcrAddressDetail> mcrAddressDetails=(List<EquifaxMcrAddressDetail>) request.getAttribute("addressDetails");
                                                                                                    if(!mcrAddressDetails.isEmpty()){
                                                                                                        int i=-1;
                                                                                                    for(EquifaxMcrAddressDetail singleAddressInfo:mcrAddressDetails){i++;
                                            %> 
                                                <%
                                                     if(i%2==0)
                                                                                         {
                                                 %>
                                                <tr style="background: #e5e5e5">
                                                <%
                                                    }
                                                                                        else
                                                                                        {
                                                %>
                                                <tr>
                                                <%
                                                    }
                                                %>                            
                                                <td class="dataValue" width="10%"
                                                    style="padding-left: 10px;">
                                                    <%
                                                        try {
                                                    %> <%=singleAddressInfo.getType()%> <%
     } catch (Exception ec) {
                                                                  logger.debug("We are in exception while getting of type from singleAddressInfo " + ec);
                                                              }
 %>
                                                </td>

                                                <td class="dataValue" width="65%"
                                                    style="word-wrap: break-word; ">
                                                    <%
                                                        try {
                                                    %> <%=singleAddressInfo.getAddress()%> <%
     } catch (Exception ec) {
                                                          logger.debug("We are in exception while getting of address from singleAddressInfo " + ec);
                                                      }
 %>
                                                </td>

                                                <td class="dataValue" width="10%" style=" ">
                                                    <%
                                                        try {
                                                    %> <%=singleAddressInfo.getState()%> <%
     } catch (Exception ec) {
                                                      logger.debug("We are in exception while getting of state from singleAddressInfo " + ec);
                                                  }
 %>
                                                </td>
 
                                                <td class="dataValue" width="15%">
                                                    <%
                                                        try {
                                                    %> <%=singleAddressInfo.getReporteddate()%> <%
     } catch (Exception ec) {
                                                      logger.debug("We are in exception while getting of reporteddate from singleAddressInfo " + ec);
                                                  }
 %>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                                                                    }}} catch (Exception ec) {
                                                                                        logger.debug("We are in exception while getting error of addressinfo " + ec);
                                                                                    }
                                            %>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            <tr style="height: 20px;"></tr>
                        </tbody>
                    </table>
                </td>
            </tr>
                <!--Address details end  -->
                <!--Income details Start  -->
             <tr>
                <td>
                    <table align="center" border="0" cellspacing="0" width="100%">
                        <tbody>
                            <tr style="height:10px;"></tr>
                            <tr>
                                <td>
                                    <table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="left"
                                        width="100%" cellspacing="0">
                                        <tbody>
                                            <tr>
                                                <td height="15px"></td>
                                            </tr>
                                            <tr height="20">
                                                <td class="mainHeader" style="font-size: 14px; padding-left:10px;">Consumer Income Details
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>                            
                            </tr>
                            <tr>
                                <td>
                                    <table align="center" border="0" 
                                        width="100%">
                                        <tbody>

                                            <tr style="height:20px;">
                                                <td class="dataHeader" width="10%" style="padding-left: 10px;">Seq</td>
                                                <td class="dataHeader" width="25%">Occupation</td>
                                                <td class="dataHeader" width="20%">Monthly Income</td>
                                                <td class="dataHeader" width="20%">Monthly Expense</td>
                                                <td class="dataHeader" width="25%">Date Reported</td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>
                                    <table align="center" border="0" width="100%">
                                        <tbody>
                                        <tr style="height:1px;"><td></td></tr>
                                            <%
                                                try {
                                                                                            if(request.getAttribute("incomedetails")!=null){
                                                                                                List<EquifaxMCRIncomeDetails> incomedetails=(List<EquifaxMCRIncomeDetails>) request.getAttribute("incomedetails");
                                                                                                    if(!incomedetails.isEmpty()){
                                                                                                        int i=-1;
                                                                                                    for(EquifaxMCRIncomeDetails incomeDetail:incomedetails){i++;
                                            %> 
                                                <%
                                                     if(i%2==0)
                                                                                         {
                                                 %>
                                                <tr style="background: #e5e5e5">
                                                <%
                                                    }
                                                                                        else
                                                                                        {
                                                %>
                                                <tr>
                                                <%
                                                    }
                                                %>                    
                                                <td class="dataValue" width="10%"
                                                    style="  padding-left: 10px;">
                                                    <%=i+1%>
                                                </td>
                                                <td class="dataValue" width="25%">
                                                    <%
                                                        try {
                                                    %> <%=incomeDetail.getOccupation()%> <%
     } catch (Exception ec) {
                                                              logger.debug("We are in exception while getting of occupation from incomeDetail " + ec);
                                                          }
 %>
                                                </td>
                                                <td class="dataValue" width="20%">
                                                    <%
                                                        try {
                                                    %> <%=EquifaxUtill.commaSeprated(""+incomeDetail.getMonthly_income())%> <%
     } catch (Exception ec) {
                                                              logger.debug("We are in exception while getting of monthly income from incomeDetail " + ec);
                                                          }
 %>
                                                </td>
                                                <td class="dataValue" width="20%">
                                                    <%
                                                        try {
                                                    %> <%=EquifaxUtill.commaSeprated(""+incomeDetail.getMonthly_expense())%> <%
     } catch (Exception ec) {
                                                              logger.debug("We are in exception while getting of Monthly_expense from incomeDetail " + ec);
                                                          }
 %>
                                                </td>
                                                <td class="dataValue" width="25%">
                                                    <%
                                                        try {
                                                    %> <%=incomeDetail.getReported_date()%> <%
     } catch (Exception ec) {
                                                              logger.debug("We are in exception while getting of Reported_date from incomeDetail " + ec);
                                                          }
 %>
                                                </td>
                                                
                                            </tr>
                                            <%
                                                }
                                                                                    }}} catch (Exception ec) {
                                                                                        logger.debug("We are in exception while getting error of incomeDetail " + ec);
                                                                                    }
                                            %>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            <tr style="height: 20px;"></tr>
                        </tbody>
                    </table>
                </td>
            </tr>
            
            <!--Income details End  -->
            <!--family details Start  -->
            
            <tr>
                <td>
                    <table align="center" border="0" cellspacing="0" width="100%">
                        <tbody>
                            <tr style="height:10px;"></tr>
                            <tr>
                                <td>
                                    <table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="left"
                                        width="100%" cellspacing="0">
                                        <tbody>
                                            <tr>
                                                <td height="15px"></td>
                                            </tr>
                                            <tr height="20">
                                                <td class="mainHeader" style="font-size: 14px; padding-left:10px;">Consumer Family Details
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>                            
                            </tr>
                            <tr>
                                <td>
                                    <table align="center" border="0" 
                                        width="100%">
                                        <tbody>

                                            <tr style="height:20px;">
                                                <td class="dataHeader" width="10%" style="padding-left: 10px;">Seq</td>
                                                <td class="dataHeader" width="20%">No of Dependents</td>
                                                <td class="dataHeader" width="40%">Additional Name</td>
                                                <td class="dataHeader" width="30%">Additional Name Type</td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>
                                    <table align="center" border="0" width="100%">
                                        <tbody>
                                        <tr style="height:1px;"><td></td></tr>
                                            <%
                                                try {
                                                                                            if(request.getAttribute("familydetails")!=null){
                                                                                                List<EquifaxMcrFamilyDetail> familydetails=(List<EquifaxMcrFamilyDetail>) request.getAttribute("familydetails");
                                                                                                    if(!familydetails.isEmpty()){
                                                                                                        int i=-1;
                                                                                                    for(EquifaxMcrFamilyDetail familydetail:familydetails){i++;
                                            %> 
                                                <%
                                                     if(i%2==0)
                                                                                         {
                                                 %>
                                                <tr style="background: #e5e5e5">
                                                <%
                                                    }
                                                                                        else
                                                                                        {
                                                %>
                                                <tr>
                                                <%
                                                    }
                                                %>                    
                                                <td class="dataValue" width="10%"
                                                    style="  padding-left: 10px;">
                                                    <%=i+1%>
                                                </td>
                                                <td class="dataValue" width="20%">
                                                    <%
                                                        try {
                                                    %> <%=familydetail.getNoofdependents()%> <%
     } catch (Exception ec) {
                                                              logger.debug("We are in exception while getting of getNoofdependents from familyDetails " + ec);
                                                          }
 %>
                                                </td>
                                                <td class="dataValue" width="40%">
                                                    <%
                                                        try {
                                                    %> <%=familydetail.getAdditionalname()%> <%
     } catch (Exception ec) {
                                                              logger.debug("We are in exception while getting of additional Name from familyDetails " + ec);
                                                          }
 %>
                                                </td>
                                                <td class="dataValue" width="30%">
                                                    <%
                                                        try {
                                                    %> <%=familydetail.getAdditionalnametype()%> <%
     } catch (Exception ec) {
                                                              logger.debug("We are in exception while getting of additional name type from familyDetails " + ec);
                                                          }
 %>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                                                                    }}} catch (Exception ec) {
                                                                                        logger.debug("We are in exception while getting error of family Details " + ec);
                                                                                    }
                                            %>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            <tr style="height: 20px;"></tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        <!--family details End  -->
        <!--identity details Start  -->
        <tr>
                <td>
                    <table align="center" border="0" cellspacing="0" width="100%">
                        <tbody>
                            <tr style="height:10px;"></tr>
                            <tr>
                                <td>
                                    <table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="left"
                                        width="100%" cellspacing="0">
                                        <tbody>
                                            <tr>
                                                <td height="15px"></td>
                                            </tr>
                                            <tr height="20">
                                                <td class="mainHeader" style="font-size: 14px; padding-left:10px;">Consumer Identity Details
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>                            
                            </tr>
                            <tr>
                                <td>
                                    <table align="center" border="0" 
                                        width="100%">
                                        <tbody>

                                            <tr style="height:20px;">
                                                <td class="dataHeader" width="33%" style="padding-left: 10px;">Seq</td>
                                                <td class="dataHeader" width="33%">Id Number</td>
                                                <td class="dataHeader" width="33%">Reported Date</td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            
                            <tr>
                                <td>
                                    <table align="center" border="0" width="100%">
                                        <tbody>
                                        <tr style="height:1px;"><td></td></tr>
                                        
                                        
                                            <%
                                                                                                                                try {
                                                                                                                                                                            if(request.getAttribute("identityinfo")!=null){
                                                                                                                                                                                List<EquifaxMcrIdentityDetail> identityinfo=(List<EquifaxMcrIdentityDetail>) request.getAttribute("identityinfo");
                                                                                                                                                                                    if(!identityinfo.isEmpty()){
                                                                                                                                                                                        int i=-1;
                                                                                                                                                                                    for(EquifaxMcrIdentityDetail idDetails:identityinfo){i++;
                                                                                                                            %> 
                                                <%
                                                     if(i%2==0)
                                                                                         {
                                                 %>
                                                <tr style="background: #e5e5e5">
                                                <%
                                                    }
                                                                                        else
                                                                                        {
                                                %>
                                                <tr>
                                                <%
                                                    }
                                                %>                    
                                                <td class="dataValue" width="33%"
                                                    style="  padding-left: 10px;">
                                                    <%=i+1%>
                                                </td>
                                                <td class="dataValue" width="33%">
                                                    <%
                                                        try {
                                                    %> <%=idDetails.getIdnumber()%> <%
     } catch (Exception ec) {
                                                              logger.debug("We are in exception while getting of id Number from Identity Info " + ec);
                                                          }
 %>
                                                </td>
                                                <td class="dataValue" width="33%">
                                                    <%
                                                        try {
                                                    %> <%=idDetails.getReporteddate()%> <%
     } catch (Exception ec) {
                                                              logger.debug("We are in exception while getting reported date from Identity Info " + ec);
                                                          }
 %>
                                                </td>
                                                
                                            </tr>
                                            <%
                                                }
                                                                                    }}} catch (Exception ec) {
                                                                                        logger.debug("We are in exception while getting error of identity info " + ec);
                                                                                    }
                                            %>
                                            
                                            
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            <tr style="height: 20px;"></tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        
        <!--identity details End  -->
    
            <tr>
                <td>
                    <table align="center" border="0" cellspacing="0"
                        width="100%">
                        <tbody>
                            <tr style="height: 20px;">
                            </tr>
                            <tr>
                                <td>
                                    <table
                                        style="border-radius: 0px; border-bottom: 2px solid #1675a5;"
                                        align="center" width="100%" cellspacing="0">
                                        <tbody>
                                            <tr>
                                                <td height="15px"></td>
                                            </tr>
                                            <tr height="20">
                                                <td class="mainHeader" style="padding-left: 10px;">Account
                                                    Summary
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <%
                                    try 
                                    {
                                        if(request.getAttribute("accountsummarymfi")!=null)
                                        {
                                        JSONObject account = new JSONObject(request.getAttribute("accountsummarymfi") + "");
                                %>
                                <table width="100%">
                                <tbody>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataHeader" width="20%" style="padding-left: 10px;">Accounts</td>
                                        <td class="dataHeader" width="20%">Balances</td>
                                        <td class="dataHeader" width="25%">Amounts</td>
                                        <td class="dataHeader" width="35%">Others</td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="20%" style="padding-left: 10px;"><span class="fontWeight">Total Account : </span><%try {%> <%=account.get("noofaccounts")%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="20%"><span class="fontWeight">Single High Sanction Amount : </span><%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("singlehighestsanctionamount"))%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight">Single High Sanction Credit : </span><%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("singlehighestcredit"))%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">Recent Account : </span> <%try {%><%=account.get("recentaccount")%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="20%" style="padding-left: 10px;"><span class="fontWeight">Active Accounts :</span><%try {%> <%=account.get("noofactiveaccounts")%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight">Average Open Balance : </span><%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("averageopenbalance"))%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight">Total High Credit : </span><%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("totalhighcredit"))%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="30%"><span class="fontWeight">Oldest Account : </span><%try {%> <%=account.get("oldestaccount")%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="20%" style="padding-left: 10px;"><span class="fontWeight">Write-Off Accounts :</span><%try {%> <%=account.get("noofwriteoffs")%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight">Single Highest Balance : </span><%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("singlehighestbalance"))%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight">Total Sanction Amount : </span><%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("totalsanctionamount"))%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="30%"><span class="fontWeight">Most Severe Status With-In 24 Months : </span><%try {%> <%=account.get("mostseverestatuswithin24")%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="20%" style="padding-left: 10px;"><span class="fontWeight">Past Due Accounts : </span><%try {%> <%=account.get("noofpastdueaccounts")%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight">Total Balance Amount : </span><%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("totalbalanceamount"))%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight">Total Monthly payment Amount : </span><%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("totalmonthlypaymentamount"))%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="30%"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="20%" style="padding-left: 10px;"><span class="fontWeight">Zero-Balance Account :</span><%try {%> <%=account.get("noofzerobalanceaccounts")%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight">Total Past Due Amount : </span><%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("totalpastdue"))%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight">Total Credit Limit : </span><%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("totalcreditlimit"))%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="30%"><span class="fontWeight"></span><%try {%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                </tbody>
                            </table>
                                <%}}catch(Exception ec){} %>
                                    
                                </td>
                            </tr>
                            
                            <!-- OtherKeyInd start -->
                            
                            <tr style="height: 20px;">
                            </tr>
                            
                            <tr>
                                <td>
                                    <table align="left" border="0" width="100%">
                                        <tbody>
                                            <tr style="background: #e5e5e5;">
                                                <td class="dataHeader" style="padding-left:10px;">OtherKeyInd Report</td>
                                            </tr>
                                            <tr style="background: #e5e5e5;">
                                                <td>
                                                
                                                    <%try {
                                                    if(request.getAttribute("otherkeyInd")!=null){
                                                        EquifaxOtherKeyInd otherKeyInd=(EquifaxOtherKeyInd) request.getAttribute("otherkeyInd");
                                                            if(otherKeyInd!=null){%> 
                                                    

                                                    <table border="0" width="100%">
                                                        <tbody>
                                                            <tr>
                                                                <td width="33%" class="dataValue" style="padding-left: 10px;">
                                                                    <span class="fontWeight">Age Of Oldest Trade : </span>

                                                                    <%
                                                                        try {
                                                                    %> <%=otherKeyInd.getAgeOfOldestTrade()%> <%
                                                                    } catch (Exception ec) {}
                                                                    %>
                                                                </td>

                                                                <td width="33%" class="dataValue">
                                                                    <span class="fontWeight">Number Of Open Trades : </span>
                                                                    <%
                                                                        try {
                                                                    %> <%=otherKeyInd.getNumberOfOpenTrades()%> <%
                                                                         } catch (Exception ec) {}
                                                                    %>
                                                                </td>

                                                                <td width="33%" class="dataValue">
                                                                    <span class="fontWeight">All Lines Ever Written : </span>

                                                                    <%
                                                                        try {
                                                                    %> <%=otherKeyInd.getAllLinesEVERWritten()%> <%
                                                                     } catch (Exception ec) {}
                                                                     %>
                                                                </td>

                                                            </tr>
                                                            <tr>
                                                            
                                                                <td width="33%" class="dataValue" style="padding-left: 10px;">
                                                                    <span class="fontWeight">All Lines Ever Written In 6 Months : </span>
                                                                    <%
                                                                        try {
                                                                    %> <%=otherKeyInd.getAllLinesEVERWrittenIn6Months()%> <%
                                                                     } catch (Exception ec) {}
                                                                     %>
                                                                </td>
                                                                
                                                                <td width="33%" class="dataValue">
                                                                    <span class="fontWeight">All Lines Ever Written In 9 Months : </span>
                                                                    <%
                                                                        try {
                                                                    %> <%=otherKeyInd.getAllLinesEVERWrittenIn9Months()%> <%
                                                                         } catch (Exception ec) {}
                                                                     %>
                                                                </td>
                                                                
                                                                <td width="33%" class="dataValue"></td>
                                                            </tr>
                                                        </tbody>
                                                    </table> 
                                                    <%
                                                        }}} catch (Exception ec) {}
                                                    %>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            <!-- OtherKeyInd end -->
                            
                            
                            <tr>
                                <td>
                                    <table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="left"
                                        width="100%" cellspacing="0">
                                        <tbody>
                                            <tr>
                                                <td height="15px"></td>
                                            </tr>
                                            <tr height="20">
                                                <td class="mainHeader" style="font-size: 14px; padding-left:10px;">Account Details* 
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                            
                            <!--Account  details Start  -->
                                <%try {
                                        if(request.getAttribute("account")!=null){
                                            List<EquifaxMcrAccountDetail> accountDetails=(List<EquifaxMcrAccountDetail>)request.getAttribute("account");
                                            if(!accountDetails.isEmpty()){
                                            int i=-1;
                                            for(EquifaxMcrAccountDetail account:accountDetails){
                                            if (i % 2 == 0) {i++;
                                            %> 
                            <tr>
                                <%
                                    } else {
                                %>

                                <tr>
                                    <%
                                        }
                                    %>
                                    <td>
                                        <table border="0" width="100%">
                                            <tbody>
                                                <tr>
                                                    <td class="dataHeader" colspan="4" style="padding-left: 10px;">Account KeyPerson Details </td>
                                                </tr>
                                                
                                                <!--Account KeyPerson details Start  -->
                                                
                                                <%
                                                                try {
                                                                    if(account.getEquifaxMcrKeyPersonDetails()!=null){
                                                                List<EquifaxMcrKeyPersonDetail> keyPersonDetails=(List<EquifaxMcrKeyPersonDetail>)account.getEquifaxMcrKeyPersonDetails();
                                                                if(!keyPersonDetails.isEmpty()){
                                                                int j=-1;
                                                                    for(EquifaxMcrKeyPersonDetail keyperson:keyPersonDetails){j++;%> 
                                                
                                                    <tr>
                                                            <td class="dataValue" style="padding-left: 10px;">
                                                            <span class="fontWeight"><%=j+1 %>. Name : </span></td>
                                                            <td width="190 px" class="dataValue">
                                                            <%
                                                                try {
                                                            %> <%=keyperson.getName()%>
                                                            <%
                                                                } catch (Exception ec) {
                                                                            }
                                                            %>
                                                            </td>
                                                            <td class="dataValue">
                                                            <span class="fontWeight">Relation Type : </span></td>

                                                            <td width="190 px" class="dataValue">
                                                            <%
                                                                try {
                                                            %> <%=keyperson.getRelationtype()%>
                                                            <%
                                                                } catch (Exception ec) {
                                                                }
                                                            %>
                                                            </td>
                                                </tr>
                                                <%}
                                                  }
                                                 }
                                                } catch (Exception ec) {
                                                    logger.debug("We are in exception while getting response of keyPersonDetails" + ec);
                                            }
                                        %>
                                                    
                                                <!--Account KeyPerson details End  -->
                                                
                                                <!--Account Nominiee details start  -->
                                                
                                                    <tr>
                                                    <td class="dataHeader" colspan="4" style="padding-left: 10px;">Nominee Details </td>
                                                </tr>
                                                
                                                <%
                                                                try {
                                                                    if(account.getEquifaxMcrNomineeDetails()!=null){
                                                                List<EquifaxMcrNomineeDetail> nomineeDetails=(List<EquifaxMcrNomineeDetail>)account.getEquifaxMcrNomineeDetails();
                                                                if(!nomineeDetails.isEmpty()){
                                                                int j=-1;
                                                                    for(EquifaxMcrNomineeDetail nom:nomineeDetails){j++;%> 
                                                
                                                    <tr>
                                                            <td class="dataValue" style="padding-left: 10px;">
                                                            <span class="fontWeight"><%=j+1 %>. Name : </span></td>
                                                            <td class="dataValue">
                                                            <%
                                                                try {
                                                            %> <%=nom.getName()%>
                                                            <%
                                                                } catch (Exception ec) {
                                                                            }
                                                            %>
                                                            </td>
                                                            <td class="dataValue">
                                                            <span class="fontWeight">Relation Type : </span></td>
                                                            <td width="190 px" class="dataValue">
                                                            <%
                                                                try {
                                                            %> <%=nom.getRelationtype()%>
                                                            <%
                                                                } catch (Exception ec) {
                                                                }
                                                            %>
                                                            </td>
                                                </tr>
                                                <%}
                                                  }
                                                 }
                                                } catch (Exception ec) {
                                                    logger.debug("We are in exception while getting response of nomineeDetails" + ec);
                                            }
                                        %>
                                                
                                                
                                                <!--Account Nominee detail end -->
                                                
                                                <!-- Account  detail continute -->
                                                <tr>
                                                    <td width="25%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight">Account Number : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getAccount_number()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of accountnumber from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Open : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getOpen()%> <%
                                                        } catch (Exception ec) {

                                                        logger.debug("We are in exception while getting of dateopened from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Date Reported : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getDate_reported()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug("We are in exception while getting of datereported from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    
                                                </tr>
                                                <tr>
                                                    <td width="25%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight">Institution : </span>
                                                        <%
                                                            try {
                                                                %> <%=account.getInstitution()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug("We are in exception while getting of institution from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>

                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Past Due Amount : </span>
                                                        <%
                                                            try {
                                                                %> <%=EquifaxUtill.commaSeprated(""+account.getPast_due_amount())%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug("We are in exception while getting of past due amount from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Interest Rate : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getInterestrate()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug("We are in exception while getting of interestrate from response of account" + ec);
                                                        }
                                                        %>
                                                    </td> 

                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Date Opened : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getDate_opened()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug("We are in exception while getting of dateopened from response of account" + ec);
                                                        }
                                                        %>

                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="25%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight">Type : </span>
                                                    </td>
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Last Payment : </span>
                                                        <%
                                                            try {
                                                        %> <%=EquifaxUtill.commaSeprated(""+account.getLast_payment())%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug("We are in exception while getting of lastpayment from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Last Payment Date : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getLastPaymentDate()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of lastpaymentdate from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Date Closed : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getDate_closed()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getDate_closed from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="25%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight">Ownership Type : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getOwner_ship_type()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of ownershiptype from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Write Off Amount : </span>
                                                        <%
                                                            try {
                                                        %> <%=EquifaxUtill.commaSeprated(""+account.getWriteOffAmount())%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getWriteOffAmount from response of account" + ec);
                                                        }
                                                        %>
                                                        
                                                    </td>
                                                    
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Sanction Amount : </span>
                                                        <%
                                                            try {
                                                        %> <%=EquifaxUtill.commaSeprated(""+account.getSanction_amount())%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of sanctionamount from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Disburse Amount : </span>
                                                        <%
                                                            try {
                                                        %> <%=EquifaxUtill.commaSeprated(""+account.getDisbursed_ammount())%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getDisbursed_ammount from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    
                                                </tr>
                                                <tr>
                                                    <td width="25%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight">Loan Category : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getLoan_category()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getLoan_category from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Current Balance : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getCurrent_balance()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getCurrent_balance from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Branch Id MFI : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getBranch_id_mfi()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getBranch_id_mfi from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Installment Amount : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getInstallment_amount()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getInstallment_amount from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                            <!--         <td width="25%" class="dataValue"></td> -->
                                                </tr>
                                                <tr>
                                                    <td width="25%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight">Loan Purpose : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getLoan_purpose()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getLoan_purpose from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Loan Cycle Id : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getLoan_cycle_id()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getLoan_cycle_id from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Number of Installment : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getNo_of_installments()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getNo_of_installments from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>

                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Reason : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getReason()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug("We are in exception while getting of reason from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                </tr>
                                                
                                                <tr>
                                                    <td width="25%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight">Repayment Tenure : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getRepayment_tenure()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of repaymenttenure from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Applied Amount : </span>
                                                        <%
                                                            try {
                                                        %> <%=EquifaxUtill.commaSeprated(""+account.getApplied_amount())%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of repaymenttenure from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Credit Limit : </span>
                                                        
                                                        <%
                                                            try {
                                                        %> <%=account.getCreditLimit()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getCreditLimit from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>

                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Collateral Value : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getCollateral_value()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of collateralvalue from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="25%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight">Dispute Code : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getDispute_code()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug("We are in exception while getting of disputecode from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Term Frequency : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getTerm_frequency()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of termfrequency from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">  </span>
                                                    </td>

                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight">Collateral Type : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getCollateral_type()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of collateraltype from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="25%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight">Balance : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getBalance()%> <%
                                                        } catch (Exception ec) {

                                                        logger.debug("We are in exception while getting of balance from response of account" + ec);
                                                        }
                                                        %>
                                                    </td> 
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight"> Date Sanctioned : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getDate_sanctioned()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getDate_sanctioned from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    <td width="25%" class="dataValue">
                                                        <span class="fontWeight"> Kendra AID MFI : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getKendra_aid_mfi()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of getKendra_aid_mfi from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                    <tr><td width="25%"></td></tr>
                                                </tr>
                                                <tr>
                                                    <td colspan="10" >
                                                        <hr size="1" style="color: #c8c8c8;"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="20%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight">Account Status : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getAccount_status()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of accountnumber from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="20%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight">Asset Classification : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getAsset_classification()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of accountnumber from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="20%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight">Suit Filed Status : </span>
                                                        <%
                                                            try {
                                                        %> <%=account.getSuit_filed_status()%> <%
                                                        } catch (Exception ec) {
                                                        logger.debug(
                                                        "We are in exception while getting of suitfiledstatus from response of account" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td colspan="10">
                                                        <hr size="1" style="color: #c8c8c8;" />
                                                    </td>
                                                </tr>

                                                <!--Account month 24 details End  -->
                                                <tr>
                                                    <td width="100%" colspan="5" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight" style="width:20%;">History : </span>
                                                        <table  cellspacing="0" style="margin-top:15px; border: 0.5px solid #c8c8c8; border-spacing: 0;" width="100%"> 
                                                            
                                                            <tr>
                                                                <td class="dataValue" style="padding:2px; border: 0.5px solid #c8c8c8; font-size:7px;font-weight:normal;"><span class="fontWeight"> Asset Classification Status </span></td>
                                                                <%
                                                                try {
                                                                    if(account.getHistory24MonthsDetails()!=null){
                                                                List<EquifaxMcrHistory24MonthsDetail> historyDetails=(List<EquifaxMcrHistory24MonthsDetail>)account.getHistory24MonthsDetails();
                                                                if(!historyDetails.isEmpty()){
                                                                for(EquifaxMcrHistory24MonthsDetail month:historyDetails){ %>
                                                                <td class="dataValue" style="text-align: center; padding:2px; border: 0.5px solid #c8c8c8;font-size:7px;font-weight:normal;">
                                                                    <%=month.getAssetClassificationStatus()%>
                                                                </td>
                                                                <%    }}}} catch (Exception ec) {logger.debug("We are in exception while getting  from response of history" + ec);} %>
                                                            </tr>

                                                            <tr>
                                                                <td class="dataValue" style="font-size: 10px;  padding:2px; border: 0.5px solid #c8c8c8;font-size:7px;font-weight:normal;"><span class="fontWeight"> Suit Filed
                                                                    Status</span></td>
                                                                <%
                                                                try {
                                                                    if(account.getHistory24MonthsDetails()!=null){
                                                                List<EquifaxMcrHistory24MonthsDetail> historyDetails=(List<EquifaxMcrHistory24MonthsDetail>)account.getHistory24MonthsDetails();
                                                                if(!historyDetails.isEmpty()){
                                                                for(EquifaxMcrHistory24MonthsDetail month:historyDetails){ %>
                                                                <td class="dataValue" style="text-align: center; padding:2px; border: 0.5px solid #c8c8c8;font-size:7px;font-weight:normal;">
                                                                    <%=month.getSuitFiledStatus()%>
                                                                    
                                                                </td>

                                                                <%    }}}} catch (Exception ec) {logger.debug("We are in exception while getting  from response of history" + ec);} %>
                                                            </tr>

                                                            <tr>
                                                                <td class="dataValue" style="padding:2px; border: 0.5px solid #c8c8c8;font-size:7px;font-weight:normal;"><span class="fontWeight">Payment Status</span></td>
                                                                <%
                                                                try {
                                                                    if(account.getHistory24MonthsDetails()!=null){
                                                                List<EquifaxMcrHistory24MonthsDetail> historyDetails=(List<EquifaxMcrHistory24MonthsDetail>)account.getHistory24MonthsDetails();
                                                                if(!historyDetails.isEmpty()){
                                                                for(EquifaxMcrHistory24MonthsDetail month:historyDetails){ %>
                                                                <td class="dataValue" style="text-align: center; padding:2px; border: 0.5px solid #c8c8c8;font-size:7px;font-weight:normal;">
                                                                <%=month.getPaymentstatus()%>    
                                                                </td>
                                                                <%    }}}} catch (Exception ec) {logger.debug("We are in exception while getting  from response of history" + ec);} %>
                                                            </tr>

                                                            <tr>
                                                                <td class="dataValue" style=" padding:2px; border: 0.5px solid #c8c8c8;font-size:7px;font-weight:normal;"><span class="fontWeight"> Date</span></td>
                                                                <%
                                                                try {
                                                                    if(account.getHistory24MonthsDetails()!=null){
                                                                List<EquifaxMcrHistory24MonthsDetail> historyDetails=(List<EquifaxMcrHistory24MonthsDetail>)account.getHistory24MonthsDetails();
                                                                if(!historyDetails.isEmpty()){
                                                                for(EquifaxMcrHistory24MonthsDetail month:historyDetails){ %> 
                                                                <td class="dataValue" style="text-align: center; padding:2px; border: 0.5px solid #c8c8c8;font-size:7px;font-weight:normal;">
                                                                    <%=month.getKey()%>
                                                                </td>
                                                                <%    }}}} catch (Exception ec) {logger.debug("We are in exception while getting  from response of history" + ec);} %>
                                                            </tr>
                                                        </table> 
                                                    </td>
                                                </tr>
                                                
                                                <!--Account month 24 details End  -->
                                                <!--Account AddionalMFI details start  -->
                                                <tr>
                                                    <td width="100%" colspan="5" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight" style="width:20%; display: inline-block; margin-bottom: 10px;">Addional MFI Details : </span>
                                                            <%
                                                                try {
                                                                    if(account.getEquifaxMcrAdditionalMFIDetails()!=null){
                                                                List<EquifaxMcrAdditionalMFIDetail> addionalMFiDetails=(List<EquifaxMcrAdditionalMFIDetail>)account.getEquifaxMcrAdditionalMFIDetails();
                                                                if(!addionalMFiDetails.isEmpty()){
                                                                for(EquifaxMcrAdditionalMFIDetail mfidetail:addionalMFiDetails){%> 
                                                            <table style="border: 0.5px solid #c8c8c8; border-spacing: 0;margin-top:15px;" width="100%"> 
                                                            <tr>
                                                                <td class="dataValue" style="width: 25%; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                <span class="fontWeight">ID</span></td>
                                                                <td class="dataValue" style="width: 75%; text-align: left; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                <%
                                                                        try {
                                                                            %> <%=mfidetail.getId()%> <%
                                                                                 } catch (Exception ec) {logger.debug("We are in exception while getting of id from response of Additional MFi" + ec);
                                                                        } %>
                                                                </td>
                                                            </tr>

                                                            <tr>
                                                                <td class="dataValue" style="width: 25%; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                <span class="fontWeight">Client Id</span></td>
                                                                <td class="dataValue" style="width: 75%; text-align: left; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                    <%
                                                                        try {
                                                                            %> <%=mfidetail.getMemberid()%> <%
                                                                                 } catch (Exception ec) {logger.debug("We are in exception while getting of Member ID from response of Additional MFi" + ec);
                                                                        } %>
                                                                </td>
                                                            </tr>

                                                            <tr>
                                                                <td class="dataValue" style="width: 25%; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                <span class="fontWeight">MFI Client Full Name</span></td>
                                                                
                                                                <td class="dataValue" style="width: 75%; text-align: left; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                    <%
                                                                        try {
                                                                            %> <%=mfidetail.getMficlientfullname()%> <%
                                                                                 } catch (Exception ec) {logger.debug("We are in exception while getting of MFI Client Full Name from response of Additional MFi" + ec);
                                                                        } %>
                                                                </td>

                                                            </tr>

                                                            <tr>
                                                                <td class="dataValue" style="width: 25%; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                <span class="fontWeight">MFI DOB</span></td>
                                                                <td class="dataValue" style="width: 75%; text-align: left; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                    <%
                                                                        try {
                                                                            %> <%=mfidetail.getMfidob()%> <%
                                                                                 } catch (Exception ec) {logger.debug("We are in exception while getting of MFI DOB from response of Additional MFi" + ec);
                                                                        } %>
                                                                    
                                                                </td>

                                                            </tr>
                                                            <tr>
                                                                <td class="dataValue" style="width: 25%; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                <span class="fontWeight">MFI Gender</span></td>
                                                                <td class="dataValue" style="width: 75%; text-align: left; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                    <%
                                                                        try {
                                                                            %> <%=mfidetail.getMfigender()%> <%
                                                                                 } catch (Exception ec) {logger.debug("We are in exception while getting of MFI Gender from response of Additional MFi" + ec);
                                                                        } %>
                                                                    
                                                                </td>

                                                            </tr>
                                                            <%if(mfidetail.getMcrAcntDtlMfiAddress().getMfiaddressline()!=null)%> <%{%>
                                                            <tr>
                                                                <td class="dataValue" style="width: 25%; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                <span class="fontWeight">MFI Address Line</span></td>
                                                                <td class="dataValue" style="width: 75%; text-align: left; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                    <%
                                                                        try {
                                                                            %> <%=mfidetail.getMcrAcntDtlMfiAddress().getMfiaddressline()%> <%
                                                                                 } catch (Exception ec) {logger.debug("We are in exception while getting of MFI Address Line from response of Additional MFi" + ec);
                                                                        } %>
                                                                    
                                                                </td>
                                                            </tr>

                                                            <tr>
                                                                <td class="dataValue" style="width: 25%; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                <span class="fontWeight">MFI Postal PIN</span></td>
                                                                <td class="dataValue" style="width: 75%; text-align: left; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                    <%
                                                                        try {
                                                                            %> <%=mfidetail.getMcrAcntDtlMfiAddress().getMfipostalpin()%> <%
                                                                                 } catch (Exception ec) {logger.debug("We are in exception while getting of getMfipostalpin from response of Additional MFi" + ec);
                                                                        } %>
                                                                    
                                                                </td>

                                                            </tr>
                                                            <tr>
                                                                <td class="dataValue" style="width: 25%; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                <span class="fontWeight">MFI State</span></td>
                                                                <td class="dataValue" style="width: 75%; text-align: left; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                    <%
                                                                        try {
                                                                            %> <%=mfidetail.getMcrAcntDtlMfiAddress().getMfistate()%> <%
                                                                                 } catch (Exception ec) {logger.debug("We are in exception while getting of getMfistate from response of Additional MFi" + ec);
                                                                        } %>
                                                                    
                                                                </td>

                                                            </tr>
                                                            <%}%>
                                                            <%if(mfidetail.getMcrAcntDtlMfiAddress().getMfiaddressline()!=null)%> <%{%>
                                                            <tr>
                                                                <td class="dataValue" style="width: 25%; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                <span class="fontWeight">MFI Other Id</span></td>
                                                                <td class="dataValue" style="width: 75%; text-align: left; padding:5px; border: 0.5px solid #c8c8c8;">
                                                                    <%
                                                                        try {
                                                                            %> <%=mfidetail.getMcrAcntDtlMfiIdentification().getMfiOtherId()%> <%
                                                                                 } catch (Exception ec) {logger.debug("We are in exception while getting of getMfiOtherId from response of Additional MFi" + ec);
                                                                        } %>
                                                                    
                                                                </td>

                                                            </tr>
                                                            <%}%>
                                                        </table>
                                                            
                                                            
                                                        <%    }}}} catch (Exception ec) {
                                                                logger.debug("We are in exception while getting  from response of history" + ec);
                                                        }
                                                        %>
                                                    </td>
                                                </tr>
                                                
                                            <!--Account AddionalMFI details end  -->


                                                <tr>
                                                    <td width="20%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight"> </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="20%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight"> </span>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td width="20%" class="dataValue" style="padding-left: 10px;">
                                                        <span class="fontWeight"> </span>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </td>
                                </tr>

                                <%
                                    }
                                    }}} catch (Exception ec) {
                                        logger.debug("We are in exception while getting response of account" + ec);
                                    }
                                %>


                    <!--Account  details End  -->
                                <tr>
                                    <td>

                                        <table align="left" border="0" cellspacing="0" width="100%">
                                            <tbody>
                                                
                                                <tr>
                                                    <td style="height: 10px;"></td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        <table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="center"
                                                            width="100%" cellspacing="0">
                                                            <tbody>
                                                                <tr>
                                                                    <td height="15px"></td>
                                                                </tr>
                                                                <tr height="20">
                                                                    <td class="mainHeader" style="font-size: 14px; padding-left:10px;">Enquiries </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>

                                                        <table align="left" border="0"
                                                            cellspacing="0" width="100%">
                                                            <tbody>
                                                                <tr> 
                                                                    <td>
                                                                        <table align="left"  border="0" width="100%">
                                                                            <tbody>
                                                                                <tr style="height:20px;"> 
                                                                                    <td class="dataHeader" width="40%" style="padding-left: 10px;">Institution</td>
                                                                                    <td class="dataHeader" width="15%">Date</td>
                                                                                    <td class="dataHeader" width="15%">Time</td>
                                                                                    <td class="dataHeader" width="15%">Purpose</td>
                                                                                    <td class="dataHeader" width="15%">Amount</td>
                                                                                </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </td>
                                                                </tr>


                                                                <%
                                                                try {
                                                                    if(request.getAttribute("enquiries")!=null){
                                                                List<EquifaxMcrEnquiry> mcrEnquiresDetails=(List<EquifaxMcrEnquiry>)request.getAttribute("enquiries");
                                                                if(!mcrEnquiresDetails.isEmpty()){
                                                                for(EquifaxMcrEnquiry enquiry:mcrEnquiresDetails){%> 
                                                                
                                                                <tr>
                                                                    <td>
                                                                        <table align="center"
                                                                            style="table-layout: fixed; border-collapse: collapse; border-spacing: 0; border: 0;"
                                                                            width="100%" class="custom-content">
                                                                            <tbody>
                                                                                <tr style="height:20px;">    
                                                                                    <td class="dataValue" width="40%" style="padding-left: 10px;">
                                                                                        <%
                                                                                            try {
                                                                                        %> <%=enquiry.getInstitution()%> <%
                                                                                         } catch (Exception ec) {logger.debug("We are in exception while getting of institution from response of enquiries" + ec);
                                                                                    } %>
                                                                                    </td>

                                                                                    <td class="dataValue" width="15%">
                                                                                        <%
                                                                                            try {
                                                                                        %> <%=enquiry.getInquiry_date()%> <%
                                                                                             } catch (Exception ec) {
                                                                                                         logger.debug("We are in exception while getting of date from response of enquiries" + ec);
                                                                                                     }
                                                                                         %>
                                                                                    </td>

                                                                                    <td class="dataValue" width="15%">
                                                                                        <%
                                                                                            try {
                                                                                        %> <%=enquiry.getInquiry_time()%> <%
                                                                                             } catch (Exception ec) {
                                                                                                         logger.debug("We are in exception while getting of time from response of enquiries" + ec);
                                                                                                     }
                                                                                         %>
                                                                                    </td>

                                                                                    <td class="dataValue" width="15%">
                                                                                        <%
                                                                                            try {
                                                                                        %> <%=enquiry.getRequest_purpose()%> <%
                                                                                             } catch (Exception ec) {
                                                                                                         logger.debug(
                                                                                                                 "We are in exception while getting of requestpurpose from response of enquiries" + ec);
                                                                                                     }
                                                                                         %>
                                                                                    </td>

                                                                                    <td class="dataValue" width="15%">
                                                                                        <%
                                                                                            try {
                                                                                        %> <%=EquifaxUtill.commaSeprated(""+enquiry.getAmount())%> <%
                                                                                             } catch (Exception ec) {
                                                                                                         logger.debug(
                                                                                                                 "We are in exception while getting of getAmount from response of enquiries" + ec);
                                                                                                     }
                                                                                         %>
                                                                                    
                                                                                    </td>
                                                                                </tr>
                                                                            </tbody>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                                <%
                                                                    }
                                                                    }}} catch (Exception ec) {
                                                                        logger.debug("We are in exception while getting response of enquiries" + ec);
                                                                    }
                                                                %>
                                                                <tr style="height: 20px;">
                                                                </tr>

                                                            </tbody>
                                                        </table>
                                                    </td>
                                                </tr>
                                                
                                                
                                                
                                                
                                                            
                                                                    <tr style="height: 20px;">
                            </tr>
                            <tr>
                                <td>
                                    <table
                                        style="border-radius: 0px; border-bottom: 2px solid #1675a5;"
                                        align="center" width="100%" cellspacing="0">
                                        <tbody>
                                            <tr>
                                                <td height="15px"></td>
                                            </tr>
                                            <tr height="20">
                                                <td class="mainHeader" style="padding-left: 10px;">Input Enquiry
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                    <%
                                    try
                                    {
                                        if(request.getAttribute("inquiryrequestinfoBean")!=null)
                                        {
                                        EquifaxPcsInquiryRequestInfo bean=(EquifaxPcsInquiryRequestInfo)request.getAttribute("inquiryrequestinfoBean");
                                        if(bean!=null)
                                        {
                                    %>
                                <table width="100%">
                                <tbody>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataHeader" width="35%" style="padding-left: 10px;">Personal & Account Information</td>
                                        <td class="dataHeader" width="30%">ID & Phone Numbers</td>
                                        <td class="dataHeader" width="35%">Contact Details</td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Consumer's First Name : </span><%try {%><%=bean.getfName()%><%} catch (Exception ec) {} %></td>
                                        <td class="dataValue" width="30%"><span class="fontWeight">Pan : </span><%try {%><%=bean.getPan()%><%} catch (Exception ec) {} %></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">Address Information 1 : </span><%try {%> <%} catch (Exception ec) {} %></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Consumer's Family Name :</span><%try {%> <%=bean.getFamilyName()%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="30%"><span class="fontWeight">Voter ID : </span><%try {%> <%=bean.getVoterID()%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">Address : </span><%try {%> <%=bean.getAddress().get(0).getAddress()%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">DOB :</span><%try {%> <%=bean.getDob()%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="30%"><span class="fontWeight">Passport ID : </span><%try {%> <%=bean.getPassportId()%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">State : </span><%try {%> <%=bean.getAddress().get(0).getState()%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Gender : </span><%try {%> <%=bean.getGender()%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="30%"><span class="fontWeight">UID : </span><%try {%> <%=bean.getUid()%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">Postal : </span><%try {%> <%=bean.getAddress().get(0).getPostal()%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Inquiry / Request Purpose :</span><%try {%> <%=bean.getInquirypurpose()%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight">Driver's License : </span><%try {%> <%=bean.getDriverLicense()%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">Address Information 2 : </span><%try {%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Transaction Amount :</span><%try {%> <%=EquifaxUtill.commaSeprated(bean.getTransactionAmount())%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight">Home Phone : </span><%try {%> <%=bean.gethPhone()%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">Address : </span><%try {%> <%=bean.getAddress().get(1).getAddress()%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Inquiry Account 1 :</span><%try {%>  <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight">Mobile Phone : </span><%try {%> <%=bean.getmPhone()%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">State : </span><%try {%> <%=bean.getAddress().get(1).getState()%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Inquiry Account 2 :</span><%try {%> <%} catch (Exception ec) {}%></td> 
                                        <td class="dataValue" width="25%"><span class="fontWeight">Other Phone : </span><%try {%> <%=bean.getoPhone()%> <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">Postal : </span><%try {%> <%=bean.getAddress().get(1).getPostal()%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Inquiry Account 3 :</span><%try {%>  <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">Address Information 3 : </span><%try {%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Inquiry Account 4 :</span><%try {%>  <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">Address : </span><%try {%> <%=bean.getAddress().get(2).getAddress()%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">State : </span><%try {%> <%=bean.getAddress().get(2).getState()%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    <tr style="background: #e5e5e5;">
                                        <td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="25%"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
                                        <td class="dataValue" width="35%"><span class="fontWeight">Postal : </span><%try {%> <%=bean.getAddress().get(2).getPostal()%> <%} catch (Exception ec) {}%></td>
                                    </tr>
                                    
                                    <%try {
                                        List<Relation> relations=bean.getRelations();
                                        if(relations!=null && relations.size()>0){
                                            int z=1;
                                            for(Relation rel:relations){
                                                if(z%3==1){%>
                                                    <tr style="background: #e5e5e5;">
                                                        <td class="dataValue" width="35%"  style="padding-left: 10px;"><span class="fontWeight"><%try {%> <%=rel.getRelation()%> <%} catch (Exception ec) {}%> Name : </span><%try {%> <%=rel.getName()%> <%} catch (Exception ec) {}%></td>
                                                        
                                                    <%
                                                }if(z%3==2){%>
                                                        <td class="dataValue" width="25%" ><span class="fontWeight"><%try {%> <%=rel.getRelation()%> <%} catch (Exception ec) {}%> Name : </span><%try {%> <%=rel.getName()%> <%} catch (Exception ec) {}%></td>
                                                    
                                              <%}if(z%3==0){%>
                                                        <td class="dataValue" width="35%" ><span class="fontWeight"><%try {%> <%=rel.getRelation()%> <%} catch (Exception ec) {}%> Name : </span><%try {%> <%=rel.getName()%> <%} catch (Exception ec) {}%></td>
                                                    </tr>
                                                <%
                                                }
                                            z++;
                                            }
                                            if(relations.size()%3==1){%>
                                                <td class="dataValue" width="25%"><span class="fontWeight"></span></td>
                                                <td class="dataValue" width="35%"><span class="fontWeight"></span></td>
                                                </tr>
                                            <%}
                                        if(relations.size()%3==2){%>
                                                <td class="dataValue" width="35%"><span class="fontWeight"></span></td>
                                                </tr>
                                            <%}
                                        }
                                    } catch (Exception ec) {}%>
                                    
                                    
                                    
                                </tbody>
                            </table>
                                <%}} } catch (Exception ec) {} %>
                                    
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


        </tbody>
    </table>

    <!-- before done -->



    <table class="box" align="center" border="0px"
        cellspacing="0" width="100%">
        
        <tbody>
            <tr>
                <td>
                    <table style="border-radius: 0px;border-bottom: 2px solid #1675a5;" align="center"
                        width="100%" cellspacing="0">
                        <tbody>
                            <tr>
                                <td height="15px"></td>
                            </tr>
                            <tr height="20">
                                <td class="mainHeader" style="font-size: 14px; padding-left:10px;">Glossary, Terms and Explanations
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
            
            <tr>
                <td>
                    <table class="box1" align="left" border="0px"
                    cellspacing="0" width="100%">
                        <tbody>
                            <tr style="height:20px;">
                                <td align="left" class="dataHeader" width="20%" style="padding-left: 10px;">Code</td>
                                <td align="left" class="dataHeader" width="80%">Description</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">000</td>
                                <td align="left" class="dataValue" width="80%">Current
                                    Account</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">CLSD</td>
                                <td align="left" class="dataValue" width="80%">Paid or
                                    closed account/zero balance</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">NEW</td>
                                <td align="left" class="dataValue" width="80%">New
                                    Account</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">LNSB</td>
                                <td align="left" class="dataValue" width="80%">Loan
                                    Submitted</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">LAND</td>
                                <td align="left" class="dataValue" width="80%">Loan
                                    Approved - Not yet disbursed-</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">INAC</td>
                                <td align="left" class="dataValue" width="80%">Account
                                    is Inactive</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">CON</td>
                                <td align="left" class="dataValue" width="80%">Contact
                                    Member for Status</td>

                            </tr>
                            <tr style="height:20px;background:#e5e5e5;" >
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">01+</td>
                                <td align="left" class="dataValue" width="80%">1-30
                                    days past due</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">31+</td>
                                <td align="left" class="dataValue" width="80%">31-60
                                    days past due</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">DEC</td>
                                <td align="left" class="dataValue" width="80%">Loan
                                    Declined</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">61+</td>
                                <td align="left" class="dataValue" width="80%">61-90
                                    days past due</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">SPM</td>
                                <td align="left" class="dataValue" width="80%">Special
                                    Mention</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">SUB</td>
                                <td align="left" class="dataValue" width="80%">Sub-standard</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">RES</td>
                                <td align="left" class="dataValue" width="80%">Restructured
                                    Loan</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">RGM</td>
                                <td align="left" class="dataValue" width="80%">Restructured
                                    Loan - Govt Mandate</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">RNC</td>
                                <td align="left" class="dataValue" width="80%">Restructured
                                    Loan - Natural Calamity</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">List</td>
                                <td align="left" class="dataValue" width="80%">Settled</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">SF</td>
                                <td align="left" class="dataValue" width="80%">Suit
                                    Filed</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">91+</td>
                                <td align="left" class="dataValue" width="80%">91-120
                                    days past due</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">121+</td>
                                <td align="left" class="dataValue" width="80%">P121 -
                                    179 days past due</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">181+</td>
                                <td align="left" class="dataValue" width="80%">180 or
                                    more days past due</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">FPD</td>
                                <td align="left" class="dataValue" width="80%">First
                                    Payment Default</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">WDF</td>
                                <td align="left" class="dataValue" width="80%">Willful
                                    Default</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">PWOS</td>
                                <td align="left" class="dataValue" width="80%">Post
                                    Written Off Settled</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">STD</td>
                                <td align="left" class="dataValue" width="80%">Standard</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">SUB</td>
                                <td align="left" class="dataValue" width="80%">Sub-standard</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">DBT</td>
                                <td align="left" class="dataValue" width="80%">Doubtful</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">LOSS</td>
                                <td align="left" class="dataValue" width="80%">Loss</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">SPM</td>
                                <td align="left" class="dataValue" width="80%">Special
                                    Mention Account</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">SFR</td>
                                <td align="left" class="dataValue" width="80%">Suit
                                    Filed-Restructured</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">SF</td>
                                <td align="left" class="dataValue" width="80%">Suit
                                    Filed</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">WDF</td>
                                <td align="left" class="dataValue" width="80%">Willful
                                    Default</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">SFWD</td>
                                <td align="left" class="dataValue" width="80%">Suit
                                    Filed-Willful Default</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">WOF</td>
                                <td align="left" class="dataValue" width="80%">Written
                                    Off</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">SFWO</td>
                                <td align="left" class="dataValue" width="80%">Suit
                                    Filed and Written Off</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">WDWO</td>
                                <td align="left" class="dataValue" width="80%">Willful
                                    Default and Written Off</td>

                            </tr>
                            <tr style="height:20px;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">SWDW</td>
                                <td align="left" class="dataValue" width="80%">Suit
                                    Filed, Willful Default and Written Off</td>

                            </tr>
                            <tr style="height:20px;" style="background:#e5e5e5;">
                                <td align="left" class="dataValue" width="20%" style="padding-left: 10px;">Set</td>
                                <td align="left" class="dataValue" width="80%">Settled</td>

                            </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
            <tr>
                <td style="height: 20px;"></td>
            </tr>
            

        </tbody>
        <tfoot>
            <tr>
                <td>
                    <table width="100%" summary="" align="center" border="0"
                        cellspacing="0">
                        <tbody>
                            <tr>
                                <td>
                                    <table summary="" border="0" width="100%">
                                        <tbody>
                                            <tr style="height:10px;">
                                                <td colspan="10">
                                                    <hr  size="1px" style="width: 100%;color:#c8c8c8" />
                                                </td>
                                            </tr>

                                            <tr style="height:50px;">
                                                <td class="disclaimerValue" width="15%" style="text-align: left;padding-left: 10px;">Contact
                                                    Us</td>
                                                <td class="disclaimerValue" width="20%"style="text-align: left;">Phone:
                                                    1800 209 3247</td>
                                                <td class="disclaimerValue" width="20%" style="text-align: left;">Fax:
                                                    +91-22-6112-7950</td>
                                                <td class="disclaimerValue" width="25%" style="text-align: right;">Email:
                                                    ecissupport@equifaxindia.com</td>
                                            </tr>

                                            <tr style="height:10px;">
                                                <td colspan="10">
                                                    <hr  size="1px" style="width: 100%;color: #c8c8c8;" />
                                                </td>
                                            </tr>

                                            <tr style="height:5px;">
                                                <td colspan="10"></td>
                                            </tr>
                                            <tr>
                                                <td valign="top" class="disclaimerValue" style="color: #333;width: 50px; padding-left: 10px;">Disclaimer:</td>
                                                <td colspan="5" class="disclaimerValue">
                                                <%
                                                        try {
                                                    %> <%=request.getAttribute("disclaimer")==null?"":(String)request.getAttribute("disclaimer")%> <%
                                                         } catch (Exception ec) {
                                                                     logger.debug("We are in exception while getting disclaimer Info " + ec);
                                                                 }
                                                     %>
                                                
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
    </table>

</body>

</html>
