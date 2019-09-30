<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.qualtech.equifax.api.entity.EquifaxPcsAllDetails"%>
<%@page
	import="com.qualtech.equifax.api.entity.pcs.EquifaxPcsPersonalDetails"%>
<%@page import="com.qualtech.equifax.api.entity.pcs.EquifaxPcsPhoneDetails"%>
<%@page
	import="com.qualtech.equifax.api.entity.pcs.EquifaxPcsAddressDetails"%>
<%@page import="com.qualtech.equifax.api.entity.pcs.EquifaxPcsPanDetails"%>
<%@page import="com.qualtech.equifax.api.entity.pcs.EquifaxPcsVoterDetails"%>
<%@page import="com.qualtech.equifax.api.entity.pcs.EquifaxPcsEmailDetails"%>
<%@page import="com.qualtech.equifax.api.entity.pcs.EquifaxPcsEnquiry"%>
<%@page import="com.qualtech.equifax.api.utils.EquifaxUtill"%>
<%@page	import="com.qualtech.equifax.api.entity.pcs.EquifaxPcsScoringElement"%>
<%@page	import="com.qualtech.equifax.api.entity.pcs.EquifaxPcsRecentActivities"%>
<%@page	import="com.qualtech.equifax.api.entity.common.EquifaxPcsInquiryRequestInfo"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.log4j.Logger"%>
<%@page import="com.qualtech.equifax.api.entity.common.EquifaxOtherKeyInd" %>
<%@page import="com.qualtech.equifax.api.common.dto.Relation" %>
<%
		Logger logger = Logger.getLogger("EquifaxPCRNew.jsp");
		logger.debug("start executing EquifaxPCRNew ");
%>
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Stardos Stencil'
	rel='stylesheet' />
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Equifax PreScreen Credit Report</title>

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
	color: #1675a5;
	font-weight: normal;
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
	color: #1675a5;
	text-align: left;
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
	color: #1675a5;
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
	background: none;
	font-family: Arial;
	font-size: 12px;
	color: #1675a5;
	font-weight: normal;
	text-align: left;
	/*text-indent: 5px;*/
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
	color: #1675a5;
	font-weight: normal;
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
	background: #1675a5;
	font-weight: normal;
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

.grey-bg {
	background: #f0f2f4;
}

.subHeading {
	color: #333;
	font-size: 13px;
}

.italic-font {
	font-style: italic;
}

.fontWeight {
	font-weight: normal;
	color:#333;
}
</style>

</head>
<body style="font-family: segoe ui semibold, arial, verdana;">

	<table class="box" align="center" border="0px" cellspacing="0"
		width="100%">
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
													style="color: #333; text-align: center; font-size: 14px;">
													<b>Equifax Prescreen Credit Report</b></td>

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
																						: </span><span class="grey-label"> <%try {%> <%=new JSONObject(request.getAttribute("responsetransaction") + "").get("time")%>
																					<%} catch (Exception e) {logger.debug("We are in exception while getting key of time from responsetransaction" + e);} %></span>
																				</td>
																			</tr>
																		  <tr style="background: #e5e5e5;">
																				<td align="left" width="114px"
																					style="font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;">
																					<span class="dataHeader"
																					style="font-size: 12px; padding-left: 10px">
																						Customer ID :</span> <span class="grey-label"><%try { %><%=new JSONObject(request.getAttribute("GeneralUserInfo") + "").get("consumerId")%><%} catch (Exception ec) {}%></span>
																				</td>
																				<td align="left" width="114px"
																					style="font-size: 12px; padding-left: 10px; padding-bottom: 5px; padding-top: 5px;">
																					<span class="dataHeader"
																					style="font-size: 12px; padding-left: 10px">Report Order No :</span>
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
					<table align="center" border="0" cellspacing="0" width="100%">
						<tbody>

							<tr>
								<td>
									<table
										style="border-radius: 0px; border-bottom: 2px solid #1675a5;"
										align="left" width="100%" cellspacing="0">
										<tbody>
											<tr>
												<td height="15px"></td>
											</tr>
											<tr>
												<td class="mainHeader" style="padding-left: 10px;">Consumer
													Score Details</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table align="center" border="0" width="100%" cellspacing="0">
										<tbody>
											<tr style="height: 20px;">
												<td class="dataHeader" width="25%"
													style="padding-left: 10px;">Score</td>
												<td class="dataHeader" width="75%">Scoring Factors</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>

							<tr>
								<td>
									<table align="left" border="0" width="100%" cellspacing="0">
										<tbody>
											<tr style="background: #e5e5e5;">
												<td width="25%"
													style="text-align: center; font-size: 40px; font-weight: 500;"
													class="dataValue">
													<%
													try {
															String payload = request.getAttribute("score")+"";
															String score = new JSONObject(request.getAttribute("score")+"").get("value")+"";
												%> <%=score%> <%} catch (Exception ec) {}%>
												</td>
												<td class="dataValue" width="75%">
													<table width="100%" cellspacing="0">
														<tbody>
														<tr><td class="dataValue"></td></tr>
															<%
																try
																{
																if(request.getAttribute("scoringelements")!=null)
																{
																	List<EquifaxPcsScoringElement> pcsScoringDetails=(ArrayList<EquifaxPcsScoringElement>) request.getAttribute("scoringelements");
																		if(!pcsScoringDetails.isEmpty())
																		{
																		int i = -1;
																		for(EquifaxPcsScoringElement scoreInfo:pcsScoringDetails)
																		{
																		i++;
																%>
															<tr>
																<td class="dataValue">
																	<%try {%> <span class="fontWeight"><%=i+1%>: </span> <%=scoreInfo.getDescription()%>
																	<%} catch (Exception ec) {logger.debug("We are in exception while getting of description from scoreInfo " + ec);}%>
																</td>
															</tr>
															<%}}}} catch (Exception ec) {logger.debug("We are in exception while getting error of scoreInfo" + ec);}%>
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
					<!-- Personal Info -->
					<table
						style="border-radius: 0px; border-bottom: 2px solid #1675a5;"
						align="left" width="100%" cellspacing="0">
						<tbody>
							<tr>
								<td height="15px"></td>
							</tr>
							<tr height="20">
								<td class="mainHeader" style="padding-left: 10px;">Personal
									Information</td>
							</tr>
						</tbody>
					</table>
					<table width="100%" border="0" cellspacing="0">
						<tbody>
							<tr>
								<td class="dataHeader" style="padding-left: 10px;" width="30%">Name</td>
								<td class="dataHeader" width="30%">Alias Name</td>
								<td class="dataHeader" width="20%">DOB</td>
								<td class="dataHeader" width="20%">Age</td>
							</tr>
							<tr style="background: #e5e5e5;">
								<td class="dataValue" style="padding-left: 10px;" width="30%">
									<span> <%
											try {
												String fullName="";
												if(request.getAttribute("fullName")!=null){
									        	fullName=(String)request.getAttribute("fullName");
											}
											%> <%=fullName%> <%} catch (Exception ec) {} %>
								</span>
								</td>
								<td class="dataValue" width="30%"><span></span></td>
								<td class="dataValue" width="20%"><span> <%
											try {
											%> <%=request.getAttribute("dateofbirth")==null?"":(String)request.getAttribute("dateofbirth")%>
										<%} catch (Exception ec) {} %>
								</span></td>
								<td class="dataValue" width="20%"><span> <%
											try {
											%> <%=request.getAttribute("age")==null?"":(String)request.getAttribute("age")%>
										<%
											} catch (Exception ec) {
													logger.debug("We are in exception while getting age from personalInfo " + ec);
											}
										%>
								</span></td>
							</tr>

							<tr>
								<td class="dataHeader" style="padding-left: 10px;" width="25%">Gender</td>
								<td class="dataHeader" width="25%">Total Income</td>
								<td class="dataHeader" width="25%">Occupation</td>
								<td width="25%"></td>
							</tr>
							<tr style="background: #e5e5e5;">
								<td class="dataValue" style="padding-left: 10px;" width="25%">
									<span> <%
											try {
											%> <%=request.getAttribute("gender")==null?"":(String)request.getAttribute("gender")%>
										<%} catch (Exception ec) {} %>
								</span>
								</td>
								<td class="dataValue" width="25%"><span>
										<%
											try {
											%> <%=request.getAttribute("totalincome")==null?"":(String)request.getAttribute("totalincome")%>
										<%} catch (Exception ec) {} %>
								</span></td>
								<td class="dataValue" width="25%"><span> <%
											try {
										%> <%=request.getAttribute("occupation")==null?"":(String)request.getAttribute("occupation")%>
										<%} catch (Exception ec) {} %>
								</span></td>
								<td width="25%"></td>
							</tr>
						</tbody>
					</table> <!-- Personal Info Ends Here -->
				</td>
			</tr>

			<tr>
				<td>

					<table align="center" border="0" cellspacing="0" width="100%">
						<tbody>
							<tr style="height: 10px;"></tr>
							<tr>
								<td>
									<table
										style="border-radius: 0px; border-bottom: 2px solid #1675a5;"
										align="left" width="100%" cellspacing="0">
										<tbody>
											<tr>
												<td height="15px"></td>
											</tr>
											<tr height="20">
												<td class="mainHeader" style="padding-left: 10px;">Consumer
													Phone Details</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table align="center" border="0" cellspacing="0" width="100%">
										<tbody>

											<tr style="height: 20px;">
												<td class="dataHeader" width="30%">Number</td>
												<td class="dataHeader" width="30%">Type Code</td>
												<td class="dataHeader" width="40%">Date Reported</td>
											</tr>

										</tbody>
									</table>
								</td>
							</tr>

							<tr>
								<td>
									<table align="left" border="0" width="100%" cellspacing="0">
										<tbody>
											<tr style="height: 1px;">
												<td></td>
											</tr>
											<%
													try {
														if(request.getAttribute("phoneDetails")!=null){
															List<EquifaxPcsPhoneDetails> pcsPhoneDetails=(ArrayList<EquifaxPcsPhoneDetails>) request.getAttribute("phoneDetails");
																				if(!pcsPhoneDetails.isEmpty()){
																					int i=-1;	
																				for(EquifaxPcsPhoneDetails phoneInfo:pcsPhoneDetails){i++;%>

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
													<td class="dataValue" width="30%"
														style="word-wrap: break-word;">
														<%
														try {
													%> <%=phoneInfo.getPhoneNumber()%> <%} catch (Exception ec) {} %>
													</td>

													<td class="dataValue" width="30%" style="">
														<%
														try {
													%> <%=phoneInfo.getPhoneTypecode()%> <%} catch (Exception ec) {} %>
													</td>
													<td class="dataValue" width="40%" style="">
														<%try {%> <%=phoneInfo.getPhoneReportedDate()%> <%} catch (Exception ec) {} %>
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

						</tbody>
					</table>
				</td>
			</tr>

			<tr>
				<td>
					<table align="center" border="0" cellspacing="0" width="100%">
						<tbody>
							<tr style="height: 10px;"></tr>
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
												<td class="mainHeader" style="padding-left: 10px;">Consumer
													Address</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table align="center" border="0" cellspacing="0" width="100%">
										<tbody>

											<tr style="height: 20px;">
												<td class="dataHeader" width="10%"
													style="padding-left: 10px;">Type</td>
												<td class="dataHeader" width="60%">Address</td>
												<td class="dataHeader" width="5%">State</td>
												<td class="dataHeader" width="10%">Postal</td>
												<td class="dataHeader" width="15%">Date Reported</td>
											</tr>

										</tbody>
									</table>
								</td>
							</tr>

							<tr>
								<td>
									<table align="center" border="0" width="100%" cellspacing="0">
										<tbody>
											<tr style="height: 1px;">
												<td></td>
											</tr>
											<%
													try {
														if(request.getAttribute("addressDetails")!=null){
															List<EquifaxPcsAddressDetails> pcsAddressDetails=(ArrayList<EquifaxPcsAddressDetails>) request.getAttribute("addressDetails");
																if(!pcsAddressDetails.isEmpty()){
																	int i=-1;
																for(EquifaxPcsAddressDetails singleAddressInfo:pcsAddressDetails){i++;%>
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
													%> <%=singleAddressInfo.getType()%> <%} catch (Exception ec) {} %>
													</td>

													<td class="dataValue" width="60%"
														style="word-wrap: break-word;">
														<%
														try {
													%> <%=singleAddressInfo.getAddress()%> <%} catch (Exception ec) {} %>
													</td>

													<td class="dataValue" width="5%" style="">
														<%
														try {
													%> <%=singleAddressInfo.getState()%> <%} catch (Exception ec) {} %>
													</td>

													<td class="dataValue" width="10%" style="">
														<%
														try {
													%> <%=singleAddressInfo.getPostal()%> <%} catch (Exception ec) {} %>
													</td>

													<td class="dataValue" width="15%" style="">
														<%
														try {
													%> <%=singleAddressInfo.getReportedDate()%> <%} catch (Exception ec) {} %>
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

						</tbody>
					</table>
				</td>
			</tr>


			<tr>
				<td>
					<table align="center" border="0" cellspacing="0" width="100%">
						<tbody>
							<tr style="height: 10px;"></tr>
							<tr>
								<td>
									<table
										style="border-radius: 0px; border-bottom: 2px solid #1675a5;"
										align="left" width="100%" cellspacing="0">
										<tbody>
											<tr>
												<td height="15px"></td>
											</tr>
											<tr height="20">
												<td class="mainHeader" style="padding-left: 10px;">
													Identification</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table align="center" border="0" cellspacing="0" width="100%">
										<tbody>

											<tr style="height: 20px;">
												<td class="dataHeader" width="33%"
													style="padding-left: 10px;">ID Name</td>
												<td class="dataHeader" width="33%">Number</td>
												<td class="dataHeader" width="33%">Date Reported</td>
											</tr>

										</tbody>
									</table>
								</td>
							</tr>

							<tr>
								<td>
									<table align="center" border="0" width="100%" cellspacing="0">
										<tbody>
											<tr style="height: 1px;">
												<td></td>
											</tr>
											<%
													try {
														if(request.getAttribute("panDetails")!=null){
															List<EquifaxPcsPanDetails> pcsPanDetails=(ArrayList<EquifaxPcsPanDetails>) request.getAttribute("panDetails");
																if(!pcsPanDetails.isEmpty()){
																	int i=-1;
																for(EquifaxPcsPanDetails panInfo:pcsPanDetails){i++;%>
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
														style="padding-left: 10px;">
														<%
														try {
													%> <%=panInfo.getPanSeq()%> <%} catch (Exception ec) {} %>
													</td>

													<td class="dataValue" width="33%"
														style="word-wrap: break-word;">
														<%
														try {
													%> <%=panInfo.getPanNumber()%> <%} catch (Exception ec) {} %>
													</td>


													<td class="dataValue" width="33%" style="">
														<%
														try {
													%> <%=panInfo.getPanReportedDate()%> <%} catch (Exception ec) {} %>
													</td>
												</tr>
												<%
												}
												}}} catch (Exception ec) {
													logger.debug("We are in exception while getting error of panInfo " + ec);
												}
											%>
											
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
					<table align="center" border="0" cellspacing="0" width="100%">
						<tbody>
							<tr style="height: 10px;"></tr>
							<tr>
								<td>
									<table
										style="border-radius: 0px; border-bottom: 2px solid #1675a5;"
										align="left" width="100%" cellspacing="0">
										<tbody>
											<tr>
												<td height="15px"></td>
											</tr>
											<tr height="20">
												<td class="mainHeader" style="padding-left: 10px;">Consumer
													Email Details</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table align="center" border="0" cellspacing="0" width="100%">
										<tbody>

											<tr style="height: 20px;">
												<td class="dataHeader" width="70%">Email Id</td>
												<td class="dataHeader" width="30%">Date Reported</td>
											</tr>

										</tbody>
									</table>
								</td>
							</tr>

							<tr>
								<td>
									<table align="center" border="0" width="100%" cellspacing="0">
										<tbody>
											<tr style="height: 1px;">
												<td></td>
											</tr>
											<%
													try {
														if(request.getAttribute("emailsDetails")!=null){
															List<EquifaxPcsEmailDetails> pcsEmailDetails=(ArrayList<EquifaxPcsEmailDetails>) request.getAttribute("emailsDetails");
																if(!pcsEmailDetails.isEmpty()){
																	int i=-1;
																for(EquifaxPcsEmailDetails emailInfo:pcsEmailDetails){i++;%>
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
													<td class="dataValue" width="70%"
														style="word-wrap: break-word;">
														<%
														try {
													%> <%=emailInfo.getEmailaddress()%> <%} catch (Exception ec) {} %>
													</td>


													<td class="dataValue" width="30%" style="">
														<%
														try {
													%> <%=emailInfo.getReporteddate()%> <%} catch (Exception ec) {} %>
													</td>
												</tr>
												<%
												}
												}}} catch (Exception ec) {
													logger.debug("We are in exception while getting error of emailInfo " + ec);
												}
											%>
											
										</tbody>
									</table>
								</td>
							</tr>

						</tbody>
					</table>
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
					<table align="center" border="0" cellspacing="0" width="100%">
						<tbody>
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
												<td class="mainHeader" style="padding-left: 10px;">Recent
													Activity</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table border="0" width="100%" cellspacing="0">
										<tbody>
											<tr>
												<td class="dataHeader" colspan="4"
													style="padding-left: 10px;">Recent Activity (last 90
													days)</td>
											</tr>
											<tr style="background: #e5e5e5;">
												<%
													try {
													if(request.getAttribute("recentactivities")!=null){
														EquifaxPcsRecentActivities recentactivities=(EquifaxPcsRecentActivities) request.getAttribute("recentactivities");
															if(recentactivities!=null){
												%>

												<td align="left" width="25%" class="dataValue"
													style="padding-left: 10px;"><span align="left"
													class="fontWeight">Total Inquiries : </span> <%
														try {
													%> <%=recentactivities.getTotal_enquiries()%> <%} catch (Exception ec) {} %>
												</td>
												<td width="25%" class="dataValue"><span align="left"
													class="fontWeight">Accounts Opened : </span> <%
														try {
													%> <%=recentactivities.getAccounts_opened()%> <%} catch (Exception ec) {} %>
												</td>
												<td width="25%" class="dataValue"><span align="left"
													class="fontWeight">Accounts Updated : </span> <%
														try {
													%> <%=recentactivities.getAccounts_updated()%> <%} catch (Exception ec) {} %>

												</td>
												<td width="25%" class="dataValue"><span align="left"
													class="fontWeight">Accounts Delinquent : </span> <% try {%> <%=recentactivities.getAccounts_deliquent()%>
													<%} catch (Exception ec) {} %></td>
												<%
													}}} catch (Exception ec) {
														logger.debug("We are in exception while getting response from recentactivities" + ec);
													}
												%>
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
												<td class="mainHeader" style="padding-left: 10px;">Account
													Summary
												</td>
											</tr>
										</tbody>
									</table>
									<%
									try 
									{
										if(request.getAttribute("accountsummary")!=null)
										{
										JSONObject account = new JSONObject(request.getAttribute("accountsummary") + "");
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
										<td class="dataValue" width="20%" style="padding-left: 10px;"><span class="fontWeight">Number of Accounts : </span><%try {%> <%=account.get("noofaccounts")%> <%} catch (Exception ec) {}%></td>
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

							<tr>
								<td height="20px"></td>
							</tr>
							<tr>
								<td>
									<table
										style="border-radius: 0px; border-bottom: 2px solid #1675a5;"
										align="left" width="100%" cellspacing="0">
										<tbody>
											<tr>
												<td height="15px"></td>
											</tr>
											<tr height="20">
												<td class="mainHeader" style="padding-left: 10px;">Account
													Details*</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<%
								try {
								if(request.getAttribute("account")!=null)
								{
									JSONArray accountsarray = new JSONArray(request.getAttribute("account") + "");
									
									for (int i = 0; i < accountsarray.length(); i++) 
									{
										JSONObject account = accountsarray.getJSONObject(i);
										
							%>
							<tr>
								<td height="10px" class="dataValue" style="padding-left: 10px;">
									<span class="fontWeight"> </span>
								</td>
							</tr>
							<% if (i % 2 == 0) { %>
							<!-- <tr style="background: #e5e5e5"> -->
							<tr style="background: #e5e5e5">
								<%
									} else {
								%>
								<tr>
									<td height="10px" class="dataValue" style="padding-left: 10px;">
										<span class="fontWeight"> </span>
									</td>
								</tr>
								<tr>
									<%
										}
									%>
									<td>
										<table border="0" width="100%" cellspacing="0">
											<tbody>
												<tr>
													<td width="20%" class="dataValue"
														style="padding-left: 10px;"><span class="fontWeight">Acct #
															 </span> <%try {%> <%=account.get("accountnumber")%> <%} catch (Exception ec) {}%>
													</td>
													<td width="20%" class="dataValue"><span
														class="fontWeight">Balance : </span> <%
															try {
														%> <%=EquifaxUtill.commaSeprated(""+account.get("balance"))%> <%} catch (Exception ec) {}%></td>
													<td width="20%" class="dataValue"><span
														class="fontWeight">Open : </span> <%
															try {
														%> <%=account.get("dateopened")%> <%} catch (Exception ec) {}%></td>
													<td width="20%" class="dataValue"><span
														class="fontWeight">Date Reported : </span> <%
															try {
														%> <%=account.get("datereported")%> <%} catch (Exception ec) {}%></td>

												</tr>
												<tr>
													<td width="20%" class="dataValue"
														style="padding-left: 10px;"><span class="fontWeight">Institution
															: </span> <%try {%> <%=account.get("institution")%> <%} catch (Exception ec) {}%></td>

													<td width="20%" class="dataValue">
													<span class="fontWeight">Past Due Amount : </span>
													<%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("pastdueamount"))%> <%} catch (Exception ec) {}%></td>
													<td width="20%" class="dataValue"><span
														class="fontWeight">Interest Rate : </span> <%
															try {
														%> <%=account.get("interestrate")%> <%} catch (Exception ec) {}%></td>

													<td width="20%" class="dataValue"><span
														class="fontWeight">Date Closed : </span> 
														<%try {%> <%=account.get("dateclosed")%> <%} catch (Exception ec) {}%></td>
												</tr>
												<tr>
													<td width="20%" class="dataValue"
														style="padding-left: 10px;"><span class="fontWeight">Account Type
															: </span><%try {%> <%=account.get("accounttype")%> <%} catch (Exception ec) {}%></td>
													<td width="20%" class="dataValue"><span
														class="fontWeight">Last Payment : </span> <%
															try {
														%> <%=EquifaxUtill.commaSeprated(""+account.get("lastpayment"))%> <%} catch (Exception ec) {}%></td>
													<td width="20%" class="dataValue"><span
														class="fontWeight">Last Payment Date : </span>
														<%try {%> <%=account.get("lastpaymentdate")%> <%} catch (Exception ec) {}%></td>
													<td width="20%" class="dataValue"><span
														class="fontWeight">High Credit : </span><%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("highcredit"))%> <%} catch (Exception ec) {}%></td>
												</tr>
												<tr>
													<td width="20%" class="dataValue"
														style="padding-left: 10px;"><span class="fontWeight">Ownership
															Type : </span> <%
															try {
														%> <%=account.get("ownershiptype")%> <%} catch (Exception ec) {}%></td>

													<td width="20%" class="dataValue"><span
														class="fontWeight">Write Off Amount : </span><%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("writeoffamount"))%> <%} catch (Exception ec) {}%></td>

													<td width="20%" class="dataValue"><span
														class="fontWeight">Sanction Amount : </span> <%
															try {
														%> <%=EquifaxUtill.commaSeprated(""+account.get("sanctionamount"))%> <%} catch (Exception ec) {}%></td>

													<td width="20%" class="dataValue"><span
														class="fontWeight">Reason : </span> <%
															try {
														%> <%=account.get("reason")%> <%} catch (Exception ec) {}%></td>
												</tr>

												<tr>
													<td width="20%" class="dataValue"
														style="padding-left: 10px;"><span class="fontWeight">Repayment
															Tenure : </span> 
															<%try {%> <%=account.get("repaymenttenure")%> <%} catch (Exception ec) {}%></td>

													<td width="20%" class="dataValue"><span
														class="fontWeight">Open : </span>
														<%try {%> <%=account.get("open")%> <%} catch (Exception ec) {}%></td>

													<td width="20%" class="dataValue"><span
														class="fontWeight">Credit Limit : </span>
														<%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("creditlimit"))%> <%} catch (Exception ec) {}%></td>

													<td width="20%" class="dataValue"><span
														class="fontWeight">Collateral Value : </span> <%
															try {
														%> <%=account.get("collateralvalue")%> <%} catch (Exception ec) {}%></td>
												</tr>
												<tr>
													<td width="20%" class="dataValue"
														style="padding-left: 10px;"><span class="fontWeight">Dispute
															Code : </span> <%try {%>
														<%=account.get("disputecode")%> <%} catch (Exception ec) {}%></td>

													<td width="20%" class="dataValue"><span
														class="fontWeight">Term Frequency : </span> <%
															try {
														%> <%=account.get("termfrequency")%> <%} catch (Exception ec) {}%></td>
													<td width="20%" class="dataValue">
													<span class="fontWeight">Installment Amount : </span>
														<%try {%> <%=EquifaxUtill.commaSeprated(""+account.get("installmentamount"))%> <%} catch (Exception ec) {}%></td>

													<td width="20%" class="dataValue"><span
														class="fontWeight">Collateral Type : </span> 
														<%try {%>
														<%=account.get("collateraltype")%> <%} catch (Exception ec) {}%></td>
												</tr>
												
												<tr>
													<td width="20%" class="dataValue"
														style="padding-left: 10px;"><span class="fontWeight">Account Status : </span> <%
															try {
														%> <%=account.get("accountstatus")%> <%} catch (Exception ec) {}%></td>

													<td width="20%" class="dataValue"><span
														class="fontWeight">Asset Classification : </span> 
														<%try {%> <%=account.get("assetclassification")%> <%} catch (Exception ec) {}%></td>
													<td width="20%" class="dataValue"><span
														class="fontWeight">Suit Filed Status : </span>
														<%try {%> <%=account.get("suitfiledstatus")%> <%} catch (Exception ec) {}%></td>
													<td width="20%" class="dataValue"><span
														class="fontWeight"></span> <%try {%> <%} catch (Exception ec) {}%></td>
												</tr>
												<tr>
													<td width="100%" colspan="4" class="dataValue"
														style="padding-left: 10px;"><span class="fontWeight"
														style="width: 20%;">History : </span> <%
															try {
																		JSONArray history48month = null;
																		JSONObject jsonObject = null;
																		
																		if(account.has("history48months")){
																		if(account.getJSONObject("history48months").has("month"))
																		{
																			if (account.getJSONObject("history48months").get("month") instanceof JSONArray)
																			{
																				history48month = account.getJSONObject("history48months").getJSONArray("month");
																			}
																			if (account.getJSONObject("history48months").get("month") instanceof JSONObject)
																			{
																				jsonObject = account.getJSONObject("history48months").getJSONObject("month");
																				history48month = new JSONArray();
																				history48month.put(jsonObject);
																			}
														%>
														<table cellspacing="0"
															style="margin-top: 15px; border: 0.5px solid #000; border-spacing: 0; "
															width="100%" >

															<tr>
																<td class="dataValue"
																	style="padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;"><span
																	class="fontWeight"> Asset Classification Status
																</span>
																</td>
																<% try {for (int j = 0; j < (history48month.length() < 24 ? history48month.length() : 24); j++) {
																		JSONObject singleHistory = history48month.getJSONObject(j);%>
																<td class="dataValue"
																	style="text-align: center; padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;">
																	<%try {%> <%=singleHistory.get("assetclassificationstatus")%>
																	<%} catch (Exception ec) {}%>
																</td>
																<%}} catch (Exception ec) {}%>
															</tr>

															<tr>
																<td class="dataValue"
																	style="padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;">Suit
																	Filed Status</td>
																<% try {for (int j = 0; j < (history48month.length() < 24 ? history48month.length() : 24); j++) {
																		JSONObject singleHistory = history48month.getJSONObject(j);%>

																<td class="dataValue"
																	style="text-align: center; padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal">
																	<%try {%> <%=singleHistory.get("suitfiledstatus")%> <%} catch (Exception ec) {}%>
																</td>

																<%}} catch (Exception ec) {}%>
															</tr>

															<tr>
																<td class="dataValue"
																	style="padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;"><span
																	class="fontWeight">Payment Status</span></td>
																<% try {for (int j = 0; j < (history48month.length() < 24 ? history48month.length() : 24); j++) {
																		JSONObject singleHistory = history48month.getJSONObject(j);%>
																<td class="dataValue"
																	style="text-align: center; padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;">
																	<%try {%> <%=singleHistory.get("paymentstatus")%> <%} catch (Exception ec) {}%>
																</td>

																<%}} catch (Exception ec) {}%>
															</tr>

															<tr>
																<td class="dataValue"
																	style="padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;"><span
																	class="fontWeight"> Date</span></td>
																<% try {for (int j = 0; j < (history48month.length() < 24 ? history48month.length() : 24); j++) {
																		JSONObject singleHistory = history48month.getJSONObject(j);%>

																<td class="dataValue"
																	style="text-align: center; padding: 2px; border: 0.5px solid #000;font-size:7px;font-weight:normal;">
																	<%try {%> <%=singleHistory.get("key")%> <%} catch (Exception ec) {}%>
																</td>

																<%}} catch (Exception ec) {}%>
															</tr>
														</table> <%
														}}} catch (Exception ec) {
														logger.debug("We are in exception while getting  from response of history" + ec);
														}
														%></td>
												</tr>
												<tr>
													<td width="20%" class="dataValue"
														style="padding-left: 10px;"><span class="fontWeight">
													</span></td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>

								<%
									}
									}} catch (Exception ec) {
										logger.debug("We are in exception while getting response of account" + ec);
									}
								%>

								<tr>
									<td>
										<table
											style="border-radius: 0px; border-bottom: 2px solid #1675a5;"
											align="left" width="100%" cellspacing="0">
											<tbody>
												<tr>
													<td height="15px"></td>
												</tr>
												<tr height="20">
													<td class="mainHeader" style="padding-left: 10px;">Enquiry
														Summary</td>
												</tr>
											</tbody>
										</table>

										<table align="left" border="0" cellspacing="0" width="100%">
											<tbody>
												<tr>
													<td>
														<table align="left" border="0" width="100%"
															cellspacing="0">
															<tbody>
																<tr style="height: 20px;">
																	<td class="dataHeader" width="20%"
																		style="padding-left: 10px;">Purpose</td>
																	<td class="dataHeader" width="15%">Total</td>
																	<td class="dataHeader" width="15%">Past 30 Days</td>
																	<td class="dataHeader" width="15%">Past 12 Months</td>
																	<td class="dataHeader" width="15%">Past 24 Months</td>
																	<td class="dataHeader" width="20%">Recent</td>
																</tr>
															</tbody>
														</table>
													</td>
												</tr>

												<tr>
													<%
														try {
															if(request.getAttribute("enquirysummary")!=null ){
															JSONObject enquirysummary = new JSONObject(request.getAttribute("enquirysummary") + "");
													%>
													<td>
														<table align="left"
															style="table-layout: fixed; border: 0;" cellspacing="0"
															width="100%" class="custom-content">
															<tbody>
																<tr style="height: 20px;">
																	<td class="dataValue" width="20%"
																		style="padding-left: 10px;">
																		<%
																			try {
																		%> <%=enquirysummary.get("purpose")%> <%} catch (Exception ec) {}%>
																	</td>

																	<td class="dataValue" width="15%">
																		<%
																			try {
																		%> <%=enquirysummary.get("total")%> <%} catch (Exception ec) {}%>
																	</td>

																	<td class="dataValue" width="15%">
																		<%
																			try {
																		%> <%=enquirysummary.get("past30days")%> <%} catch (Exception ec) {}%>
																	</td>
																	<td class="dataValue" width="15%">
																		<%
																			try {
																		%> <%=enquirysummary.get("past12months")%> <%} catch (Exception ec) {}%>
																	</td>
																	<td class="dataValue" width="15%">
																		<%
																			try {
																		%> <%=enquirysummary.get("past24months")%> <%} catch (Exception ec) {}%>
																	</td>
																	<td class="dataValue" width="20%">
																		<%
																			try {
																		%> <%=enquirysummary.get("recent")%> <%} catch (Exception ec) {}%>
																	</td>
																</tr>
															</tbody>
														</table>
													</td>
													<%
														}} catch (Exception ec) {
															logger.debug("We are in exception while getting response of enquirysummary" + ec);
														}
													%>
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
																	<td class="mainHeader" style="padding-left: 10px;">Enquiries
																	</td>
																</tr>
															</tbody>
														</table>

														<table align="left" border="0" cellspacing="0"
															width="100%">
															<tbody>
																<tr>
																	<td>
																		<table align="left" border="0" width="100%"
																			cellspacing="0">
																			<tbody>
																				<tr style="height: 20px;">
																					<td class="dataHeader" width="28%"
																						style="padding-left: 10px;">Institution</td>
																					<td class="dataHeader" width="18%">Date</td>
																					<td class="dataHeader" width="18%">Time</td>
																					<td class="dataHeader" align="right" width="18%">Purpose</td>
																					<td class="dataHeader" width="18%">Amount</td>
																				</tr>
																			</tbody>
																		</table>
																	</td>
																</tr>


																<%
																try {
																	if(request.getAttribute("enquiries")!=null){
																List<EquifaxPcsEnquiry> pcsEnquiresDetails=(ArrayList<EquifaxPcsEnquiry>)request.getAttribute("enquiries");
																if(!pcsEnquiresDetails.isEmpty()){
																for(EquifaxPcsEnquiry enquiry:pcsEnquiresDetails){%>

																<tr>
																	<td>
																		<table align="center" cellspacing="0"
																			style="table-layout: fixed; border-collapse: collapse; border-spacing: 0; border: 0;"
																			width="100%" class="custom-content">
																			<tbody>
																				<tr style="height: 20px;">
																					<td class="dataValue" width="28%"
																						style="padding-left: 10px;">
																						<%
																							try {
																						%> <%=enquiry.getInstitution()%> <%} catch (Exception ec) {} %>
																					</td>

																					<td class="dataValue" width="18%">
																						<%
																							try {
																						%> <%=enquiry.getEnquiry_date()%> <%} catch (Exception ec) {} %>
																					</td>

																					<td class="dataValue" width="18%">
																						<%
																							try {
																						%> <%=enquiry.getEnquiry_time()%> <%} catch (Exception ec) {} %>
																					</td>

																					<td class="dataValue" width="18%" align="right">
																						<%try {%> <%=enquiry.getRequest_purpose()%> <%} catch (Exception ec) {} %>
																					</td>
																					<td class="dataValue" width="18%">
																					<%try {%> <%=EquifaxUtill.commaSeprated(""+enquiry.getAmount())%> <%} catch (Exception ec) {} %>
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
												
												
												
												
												
							<%-- 					<tr style="height: 20px;">
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
											JSONObject idandContactInfo = new JSONObject(request.getAttribute("idandcontactinfo")+ "");
											JSONObject personalInfo = idandContactInfo.getJSONObject("personalinfo");
											JSONObject identityinfo = idandContactInfo.getJSONObject("identityinfo");
											JSONArray addressinfo = idandContactInfo.getJSONArray("addressinfo");
											JSONArray phoneinfo = idandContactInfo.getJSONArray("phoneinfo");
											String homeNo="";
											String mobileNo="";
											String otherNo="";
											if(phoneinfo!=null && phoneinfo.length()>0)
											{
												for(int i=0;i<phoneinfo.length();i++)
												{
													JSONObject phone = phoneinfo.getJSONObject(i);
													String typecode = ""+phone.getString("typecode");
													if(typecode.equalsIgnoreCase("H") || typecode.equalsIgnoreCase("Home"))
													{
														homeNo=""+phone.getString("number");
													}
													else if(typecode.equalsIgnoreCase("M") || typecode.equalsIgnoreCase("Mobile"))
													{
														mobileNo=""+phone.getString("number");
													}
													else if(!typecode.equalsIgnoreCase(""))
													{
														otherNo=""+phone.getString("number");
													}
												}
											}
											
									%>
								<table width="100%">
								<tbody>
									<tr style="background: #e5e5e5;">
										<td class="dataHeader" width="35%" style="padding-left: 10px;">Personal & Account Information</td>
										<td class="dataHeader" width="30%">ID & Phone Numbers</td>
										<td class="dataHeader" width="35%">Contact Details</td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Consumer's First Name : </span><%try {%><%=personalInfo.getJSONObject("name").get("firstname")+ " "+ personalInfo.getJSONObject("name").get("middlename")+ " "+ personalInfo.getJSONObject("name").get("lastname")%><%} catch (Exception ec) {} %></td>
										<td class="dataValue" width="30%"><span class="fontWeight">Pan : </span><%try {%><%=identityinfo.getJSONObject("panid").get("idnumber")%><%} catch (Exception ec) {} %></td>
										<td class="dataValue" width="35%"><span class="fontWeight">address Information 1 : </span><%try {%> <%} catch (Exception ec) {} %></td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Consumer's Family Name :</span><%try {%> <%=personalInfo.getJSONObject("name").get("lastname")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="30%"><span class="fontWeight">Voter ID : </span><%try {%> <%=identityinfo.getJSONObject("voterid").get("idnumber")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="35%"><span class="fontWeight">Address : </span><%try {%> <%=addressinfo.getJSONObject(0).get("address")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">DOB :</span><%try {%> <%=personalInfo.getJSONObject("dateofbirth")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="30%"><span class="fontWeight">Passport ID : </span><%try {%> <%=identityinfo.getJSONObject("passportid").get("idnumber")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="35%"><span class="fontWeight">State : </span><%try {%> <%=addressinfo.getJSONObject(0).get("state")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Gender : </span><%try {%> <%=personalInfo.getJSONObject("gender")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="30%"><span class="fontWeight">UID : </span><%try {%> <%=identityinfo.getJSONObject("nationalidcard").get("idnumber")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="35%"><span class="fontWeight">Postal : </span><%try {%> <%=addressinfo.getJSONObject(0).get("postal")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Inquiry / Request Purpose :</span><%try {%>  <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Driver's License : </span><%try {%> <%=identityinfo.getJSONObject("driverlicence").get("idnumber")%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="35%"><span class="fontWeight">Address Information 2 : </span><%try {%> <%} catch (Exception ec) {}%></td>
									</tr>
									
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Transaction Amount :</span><%try {%> <%=EquifaxUtill.commaSeprated(""+personalInfo.getJSONObject("totalincome"))%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Home Phone : </span><%try {%> <%=homeNo%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="35%"><span class="fontWeight">Address : </span><%try {%> <%=addressinfo.getJSONObject(1).get("address")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Inquiry Account 1 :</span><%try {%>  <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight">Mobile Phone : </span><%try {%> <%=mobileNo%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="35%"><span class="fontWeight">State : </span><%try {%> <%=addressinfo.getJSONObject(1).get("state")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Inquiry Account 2 :</span><%try {%> <%} catch (Exception ec) {}%></td> 
										<td class="dataValue" width="25%"><span class="fontWeight">Other Phone : </span><%try {%> <%=otherNo%> <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="35%"><span class="fontWeight">Postal : </span><%try {%> <%=addressinfo.getJSONObject(1).get("postal")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Inquiry Account 3 :</span><%try {%>  <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="35%"><span class="fontWeight">Address Information 3 : </span><%try {%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight">Inquiry Account 4 :</span><%try {%>  <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="35%"><span class="fontWeight">Address : </span><%try {%> <%=addressinfo.getJSONObject(2).get("address")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="35%"><span class="fontWeight">State : </span><%try {%> <%=addressinfo.getJSONObject(2).get("state")%> <%} catch (Exception ec) {}%></td>
									</tr>
									<tr style="background: #e5e5e5;">
										<td class="dataValue" width="35%" style="padding-left: 10px;"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="25%"><span class="fontWeight"></span><%try {%>  <%} catch (Exception ec) {}%></td>
										<td class="dataValue" width="35%"><span class="fontWeight">Postal : </span><%try {%> <%=addressinfo.getJSONObject(2).get("postal")%> <%} catch (Exception ec) {}%></td>
									</tr>
								</tbody>
							</table>
								<% } catch (Exception ec) {} %>
									
								</td>
							</tr> --%>
								
												
												
												
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



	<table class="box" align="center" border="0px" cellspacing="0"
		width="100%">

		<!-- <tbody>
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
								<td class="mainHeader" style="padding-left: 10px;">Glossary,
									Terms and Explanations</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>

			<tr>
				<td>
					<table class="box1" align="left" border="0px" cellspacing="0"
						width="100%">
						<tbody>
						</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataHeader" width="20%"
									style="padding-left: 10px;">Code</td>
								<td align="left" class="dataHeader" width="80%" style="">Description</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">000</td>
								<td align="left" class="dataValue" width="80%" style="">Current
									Account</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">CLSD</td>
								<td align="left" class="dataValue" width="80%" style="">Paid
									or closed account/zero balance</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">NEW</td>
								<td align="left" class="dataValue" width="80%" style="">New
									Account</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">LNSB</td>
								<td align="left" class="dataValue" width="80%" style="">Loan
									Submitted</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">LAND</td>
								<td align="left" class="dataValue" width="80%" style="">Loan
									Approved - Not yet disbursed-</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">INAC</td>
								<td align="left" class="dataValue" width="80%" style="">Account
									is Inactive</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">CON</td>
								<td align="left" class="dataValue" width="80%" style="">Contact
									Member for Status</td>

							</tr>
							<tr style="height: 20px; background: #F1F3F5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">01+</td>
								<td align="left" class="dataValue" width="80%" style="">1-30
									days past due</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">31+</td>
								<td align="left" class="dataValue" width="80%" style="">31-60
									days past due</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">DEC</td>
								<td align="left" class="dataValue" width="80%" style="">Loan
									Declined</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">61+</td>
								<td align="left" class="dataValue" width="80%" style="">61-90
									days past due</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">SPM</td>
								<td align="left" class="dataValue" width="80%" style="">Special
									Mention</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">SUB</td>
								<td align="left" class="dataValue" width="80%" style="">Sub-standard</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">RES</td>
								<td align="left" class="dataValue" width="80%" style="">Restructured
									Loan</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">RGM</td>
								<td align="left" class="dataValue" width="80%" style="">Restructured
									Loan - Govt Mandate</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">RNC</td>
								<td align="left" class="dataValue" width="80%" style="">Restructured
									Loan - Natural Calamity</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">SET</td>
								<td align="left" class="dataValue" width="80%" style="">Settled</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">SF</td>
								<td align="left" class="dataValue" width="80%" style="">Suit
									Filed</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">91+</td>
								<td align="left" class="dataValue" width="80%" style="">91-120
									days past due</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">121+</td>
								<td align="left" class="dataValue" width="80%" style="">P121
									- 179 days past due</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">181+</td>
								<td align="left" class="dataValue" width="80%" style="">180
									or more days past due</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">FPD</td>
								<td align="left" class="dataValue" width="80%" style="">First
									Payment Default</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">WDF</td>
								<td align="left" class="dataValue" width="80%" style="">Willful
									Default</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">PWOS</td>
								<td align="left" class="dataValue" width="80%" style="">Post
									Written Off Settled</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">STD</td>
								<td align="left" class="dataValue" width="80%" style="">Standard</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">SUB</td>
								<td align="left" class="dataValue" width="80%" style="">Sub-standard</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">DBT</td>
								<td align="left" class="dataValue" width="80%" style="">Doubtful</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">LOSS</td>
								<td align="left" class="dataValue" width="80%" style="">Loss</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">SPM</td>
								<td align="left" class="dataValue" width="80%" style="">Special
									Mention Account</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">SFR</td>
								<td align="left" class="dataValue" width="80%" style="">Suit
									Filed-Restructured</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">SF</td>
								<td align="left" class="dataValue" width="80%" style="">Suit
									Filed</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">WDF</td>
								<td align="left" class="dataValue" width="80%" style="">Willful
									Default</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">SFWD</td>
								<td align="left" class="dataValue" width="80%" style="">Suit
									Filed-Willful Default</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">WOF</td>
								<td align="left" class="dataValue" width="80%" style="">Written
									Off</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">SFWO</td>
								<td align="left" class="dataValue" width="80%" style="">Suit
									Filed and Written Off</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">WDWO</td>
								<td align="left" class="dataValue" width="80%" style="">Willful
									Default and Written Off</td>

							</tr>
							<tr style="height: 20px;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">SWDW</td>
								<td align="left" class="dataValue" width="80%" style="">Suit
									Filed, Willful Default and Written Off</td>

							</tr>
							<tr style="height: 20px;" style="background:#e5e5e5;">
								<td align="left" class="dataValue" width="20%"
									style="padding-left: 10px;">SET</td>
								<td align="left" class="dataValue" width="80%" style="">Settled</td>

							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td style="height: 20px;"></td>
			</tr>


		</tbody> -->
		<tfoot>
			<tr>
				<td>
					<table width="100%" summary="" align="center" border="0"
						cellspacing="0">
						<tbody>
							<tr>
								<td>
									<table cellspacing="0" summary="" border="0" width="100%">
										<tbody>
											<tr style="height: 5px;">
												<td colspan="10"></td>
											</tr>
											<tr>
											<td valign="top" class="disclaimerValue" width="10%" style="color: #333; padding-left: 10px;">Disclaimer : </td>
											<td class="disclaimerValue" width="90%" style="">
													<%
														try {
													%> <%=request.getAttribute("disclaimer")==null?"":(String)request.getAttribute("disclaimer")%>
													<%
													 	} catch (Exception ec) {
													 				logger.debug("We are in exception while getting disclaimer Info " + ec);
													 			}
													 %>

												</td>
											</tr>
											<tr><td height="20px"></td></tr>
											<tr><td height="20px"></td></tr>
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