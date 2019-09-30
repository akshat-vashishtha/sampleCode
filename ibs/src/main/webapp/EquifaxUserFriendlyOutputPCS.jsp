
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
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
	font-weight: bold;
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
	font-weight: bold; /* CSS3 specific totation code */
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
	font-weight: bold;
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
	font-weight: bold; /* CSS3 specific totation code */
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
	<table class="box" align="center" border="0px" cellpadding="0"
		cellspacing="0" width="1050px">
		<thead>
			<tr>
				<td>
					<table align="center" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td>
									<table align="center" border="0" width="1020px">
										<tbody>
											<tr height="5">
												<td></td>
											</tr>
											<tr>

												<!-- 												<td colspan="2" valign="top"><img -->
												<!-- 													src="data:image/gif;base64,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" -->
												<!-- 													alt="CRIF HighMark Credit Information Services Pvt. Ltd." -->
												<!-- 													align="left" width="166" height="72" /></td> -->
												<td colspan="2" valign="top" style=""><img
													src="http://www.qualtech-consultants.com//images/qc.jpg"
													alt="Equifax Service" align="left" width="166" height="72" /></td>
												<td width="120"></td>
												<td align="left" width="380" valign="top">
													<table border="0" cellpadding="0" cellspacing="0">
														<tbody>
															<tr>
																<td align="left" class="reportHead"><b>Equiax Consumer
																	Base & Trade Report</b><br></br>
																</td>
															</tr>
															<tr valign="top">
																<td class="dataHead" align="right" valign="top">
																	<%
																		try {
																			String general = request.getAttribute("GeneralUserInfo").toString();
																			JSONObject GeneralInfo = new JSONObject(general);
																	%> <%=GeneralInfo.get("fName")%></td>

																<%
																	} catch (Exception ec) {
																		ec.printStackTrace();
																	}
																%>
															</tr>
														</tbody>
													</table>
												</td>
												<td width="70"></td>
												<td rowspan="2" align="right" valign="top" width="350">
													<table>
														<tbody>
															<!-- 															<tr> -->
															<!-- 																<td class="dataHeader1">CHM Ref #:</td> -->
															<!-- 																<td class="dataValue1"></td> -->
															<!-- 															</tr> -->
															<!-- 															<tr> -->
															<!-- 																<td class="dataHeader1">Prepared For:</td> -->
															<!-- 																<td class="dataValue1"></td> -->
															<!-- 															</tr> -->
															<!-- 															<tr> -->
															<!-- 																<td class="dataHeader1">Application ID:</td> -->
															<!-- 																<td class="dataValue1"></td> -->
															<!-- 															</tr> -->
															<!-- 															<tr> -->
															<!-- 																<td class="dataHeader1">Date of Request:</td> -->
															<!-- 																<td class="dataValue1"></td> -->
															<!-- 															</tr> -->
															<!-- 															<tr> -->
															<!-- 																<td class="dataHeader1">Date of Issue:</td> -->
															<!-- 																<td class="dataValue1"></td> -->
															<!-- 															</tr> -->
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
									<table align="center" border="0" cellpadding="0"
										cellspacing="0" width="1020px">
										<tbody>
											<tr>
												<td>
													<table align="center" bgcolor="#0f3f6b" border="0"
														width="1020px">
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
									<%
										try {
											String general = request.getAttribute("GeneralUserInfo").toString();
											JSONObject GeneralInfo = new JSONObject(general);
									%>
									<table align="center" border="0" cellpadding="0"
										cellspacing="0" width="1030px">
										<tbody>
											<tr>
												<td>
													<table align="center" border="0" width="1030px">
														<tbody>
															<tr>
																<td>
																	<table border="0" width="1030px">
																		<tbody>
																			<tr>
																				<td height="10px"></td>
																			</tr>
																			<tr>
																				<td align="left" width="110 px" class="dataHeader">Name:</td>
																				<td align="left" width="270 px" class="dataValue">
																					<%=GeneralInfo.get("fName") + " " + GeneralInfo.get("lName")%></td>
																				<td width="70 px" class="dataHeader">DOB/Age:</td>
																				<td width="190 px" class="dataValue"><%=GeneralInfo.get("dob")%></td>
																				<!-- 																				<td width="70 px" class="dataHeader">Gender:</td> -->
																				<!-- 																				<td width="200 px" class="dataValue"></td> -->
																			</tr>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			<tr>
																				<td align="left" width="100 px" class="dataHeader">Key
																					Person Name:</td>
																				<td align="left" width="200 px" class="dataValue"><%=GeneralInfo.get("keyPersonName")%></td>
																				<td width="70 px" class="dataHeader">Key Person
																					Type:</td>
																				<td width="100 px" class="dataValue"></td>
																				<td width="70 px" class="dataHeader"><%=GeneralInfo.get("keyPersonType")%></td>
																				<td width="120 px" class="dataValue"></td>
																			</tr>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			<tr>
																				<td class="dataHeader" valign="top" width="100 px">Phone
																					Numbers:</td>
																				<td valign="top">
																					<table width="200px" cellpadding="0"
																						cellspacing="0">
																						<tr>
																							<td class="dataValue"><%=new JSONObject((new JSONArray(GeneralInfo.get("phones").toString()).get(0).toString()))
						.get("teleNo").toString()%></td>
																						</tr>
																						<tr>
																							<td class="dataValue"></td>
																						</tr>
																						<tr>
																							<td class="dataValue"></td>
																						</tr>
																					</table>
																				</td>
																				<td class="dataHeader" valign="top">ID(s):</td>
																				<td valign="top">
																					<table width="200px" cellpadding="0"
																						cellspacing="0">
																						<tr>
																							<td class="dataValue"><%=new JSONObject(new JSONArray(GeneralInfo.get("ids").toString()).get(0).toString()).get("idName")
								+ "   "
								+ new JSONObject(new JSONArray(GeneralInfo.get("ids").toString()).get(0).toString())
										.get("idNo")%></td>
																						</tr>
																						<tr>
																							<td class="dataValue"></td>
																						</tr>
																						<tr>
																							<td class="dataValue"></td>
																						</tr>
																					</table>
																				</td>
																				<td class="dataHeader" valign="top">Email
																					ID(s):</td>
																				<td valign="top">
																					<table width="200px" cellpadding="0"
																						cellspacing="0">
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
																			<!-- 																			<tr> -->
																			<!-- 																				<td align="left" width="100 px" class="dataHeader">Entity -->
																			<!-- 																					Id:</td> -->
																			<!-- 																				<td align="left" width="200 px" class="dataValue" -->
																			<!-- 																					colspan="5"></td> -->
																			<!-- 																			</tr> -->
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			<tr>
																				<td align="left" width="100 px" class="dataHeader">Current
																					Address:</td>
																				<td align="left" width="200 px" class="dataValue"
																					colspan="5"><%=new JSONObject(new JSONArray(GeneralInfo.get("addresses").toString()).get(0).toString())
						.get("address").toString()%></td>
																			</tr>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			<!-- 																			<tr> -->
																			<!-- 																				<td align="left" width="100 px" class="dataHeader">Other -->
																			<!-- 																					Address:</td> -->
																			<!-- 																				<td align="left" width="200 px" class="dataValue" -->
																			<!-- 																					colspan="5"></td> -->
																			<!-- 																				</td> -->
																			<!-- 																			</tr> -->
																		</tbody>
																	</table>
																</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table> <%
 	} catch (Exception ec) {
 		ec.printStackTrace();
 	}
 %>
								
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table align="center" border="0" cellpadding="0" cellspacing="0"
						width="1020px">
						<tbody>
							<tr>
								<td>
									<table align="center" bgcolor="#0f3f6b" border="0"
										width="1020px">
										<tbody>
											<tr height="20">
												<td width="10"></td>
												<td class="mainHeader">Equifax Score:</td>
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
					<%
						try {
							JSONObject score = new JSONObject(request.getAttribute("score").toString());
					%>
					<table align="left" border="0" cellpadding="0" cellspacing="0"
						width="1020px">
						<tbody>
							<tr>
								<td>
									<table border="0" align="left"
										style="width: 1028px; padding-left: 8px;">
										<tbody>
											<tr>
												<td height="10px" colspan="2"></td>
											</tr>
											<tr>
												<td align="left" width="200 px" class="dataHeader"
													style="padding-left: 20px;">SCORE NAME</td>
												<td align="center" width="200 px" class="dataHeader"
													style="padding-left: 31px;">&nbsp;SCORE</td>
												<!-- 												<td align="left" style="width: 485px;" class="dataHeader">DESCRIPTION</td> -->
											</tr>
											<tr class="shading">
												<td align="left" width="200 px" class="dataHeaderScore"
													style="padding-left: 20px;"><%=score.get("name").toString()%></td>
												<td align="center" valign="top" class="dataValueValue"
													style="padding-left: 33px; width: 360px;">&nbsp;<%=score.get("value").toString()%>
													<!-- 												<span	class="dataValuePerform2" --> <!-- 													style="width: 300px; vertical-align: middle; padding-left: 5px;">Score -->
													<!-- 														Range : 300-900</span> -->
												</td>
												<!-- 												<td align="left" width="300 px">&nbsp;<span -->
												<!-- 													style="font-size: 14px; font-weight: bold;">&nbsp;</span><span -->
												<!-- 													class="dataValuePerform">Not Scored: No Updates -->
												<!-- 														available in last 36 months</span></td> -->
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table> <%
 	} catch (Exception ec) {
 		ec.printStackTrace();
 	}
 %>
				</td>
				<!-- 				<tr height="20"> -->
				<!-- 					<td class="infoValueNote" style="padding-right: 9px;" align="right" -->
				<!-- 						bgcolor="#FFFFFF">Tip: A-B: Very Low Risk ; C-D: Low Risk ; -->
				<!-- 						E-G: Medium Risk ; H-J: High Risk ; K-M: Very High Risk</td> -->
				<!-- 				</tr> -->
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
	font-weight: bold;
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
	font-weight: bold; /* CSS3 specific totation code */
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
	font-weight: bold;
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
	font-weight: bold; /* CSS3 specific totation code */
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
	<table class="box" align="center" border="0px" cellpadding="0"
		cellspacing="0" width="1050px">
		<tbody>
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<table align="center" border="0" cellpadding="0" cellspacing="0"
						width="1020px">
						<tbody>
							<tr>
								<td>
									<table align="center" border="0" cellpadding="0"
										cellspacing="0" width="1020px">
										<tbody>
											<tr>
												<td height="30px"></td>
											</tr>
											<tr>
												<td>
													<table align="center" bgcolor="#0f3f6b" border="0"
														width="1020px">
														<tbody>
															<tr height="20">
																<td width="10"></td>
																<td class="mainHeader">Personal Information -
																	Variations</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr height="20">
								<td align="right" bgcolor="#FFFFFF" class="infoValue">Tip:
									These are applicant's personal information variations as
									contributed by various financial institutions.</td>
							</tr>
							<tr>
								<td align="center">
									<%
										try {
											JSONObject idandcontactinfo = new JSONObject(request.getAttribute("idandcontactinfo").toString());
											JSONObject Name = idandcontactinfo.getJSONObject("personalinfo").getJSONObject("name");
									%>
									<table cellpadding="2" cellspacing="4" border="0px">
										<tbody>
											<tr>
												<td width="670" valign="top">
													<table cellpadding="0" cellspacing="4" border="0px"
														width="670">
														<tbody>
															<tr>
																<td bgcolor="#FFFFFF">
																	<table class="box1" border="0px"
																		bordercolor="lightgray" cellpadding="3"
																		cellspacing="0">
																		<tbody>
																			<tr height="20">
																				<td align="center" bgcolor="#FFFFFF" width="550px"
																					class="subHeader">Name Variations</td>
																				<td align="center" bgcolor="#FFFFFF" width="90px"
																					class="subHeader">Reported On</td>
																			</tr>
																			<tr height="20">
																				<td class="dataValue"><%=Name.get("firstname") + "  " + Name.get("middlename") + "  " + Name.get("lastname")%></td>
																				<td align="center" class="dataValue"></td>
																			</tr>
																		</tbody>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="5px"></td>
															</tr>
															<tr>
																<td bgcolor="#FFFFFF">
																	<table class="box1" border="0px"
																		bordercolor="lightgray" cellpadding="3"
																		cellspacing="0">
																		<tbody>
																			<tr height="20">
																				<td align="center" width="550px" class="subHeader">Address
																					Variations</td>
																				<td align="center" width="90px" class="subHeader">Reported
																					On</td>
																			</tr>

																			<%
																				try {
																						JSONArray addressvariations = new JSONArray(idandcontactinfo.get("addressinfo").toString());
																						for (int i = 0; i < addressvariations.length(); i++) {
																							JSONObject AddressVariation = addressvariations.getJSONObject(i);
																			%>
																			<tr height="20">
																				<td class="dataValue"><%=AddressVariation.get("address")%></td>
																				<td align="center" class="dataValue"><%=AddressVariation.get("reporteddate")%>
																				</td>
																			</tr>

																			<%
																				}
																					} catch (Exception ec) {
																						ec.printStackTrace();
																					}
																			%>
																		</tbody>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="5px"></td>
															</tr>
															<tr>
																<td bgcolor="#FFFFFF"></td>
															</tr>
														</tbody>
													</table>
												</td>
												<td width="330" valign="top">
													<table cellpadding="0" cellspacing="4" border="0px"
														width="330">
														<tbody>
															<tr>
																<td bgcolor="#FFFFFF">
																	<table class="box1" border="0px"
																		bordercolor="lightgray" cellpadding="3"
																		cellspacing="0">
																		<tbody>
																			<tr height="20">
																				<td align="center" width="230px" class="subHeader">DOB
																					Variations</td>
																				<td align="center" width="90px" class="subHeader">Reported
																					On</td>
																			</tr>
																			<tr height="20">
																				<td class="dataValue">01-07-2049</td>
																				<td align="center" class="dataValue">30-09-2011
																				</td>
																			</tr>
																			<tr height="20">
																				<td class="dataValue">01-07-1949</td>
																				<td align="center" class="dataValue">30-09-2011
																				</td>
																			</tr>
																		</tbody>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="5px"></td>
															</tr>
															<tr>
																<td bgcolor="#FFFFFF">
																	<table class="box1" border="0px"
																		bordercolor="lightgray" cellpadding="3"
																		cellspacing="0">
																		<tbody>
																			<tr height="20">
																				<td align="center" width="230px" class="subHeader">Phone
																					Variations</td>
																				<td align="center" width="90px" class="subHeader">Reported
																					On</td>
																			</tr>

																			<%
																				try {
																						JSONArray phoneInfo = new JSONArray(idandcontactinfo.get("phoneinfo").toString());
																						for (int i = 0; i < phoneInfo.length(); i++) {
																							JSONObject phonevaritaion = new JSONObject(phoneInfo.get(i).toString());
																			%>
																			<tr height="20">
																				<td class="dataValue"><%=phonevaritaion.get("number")%></td>
																				<td align="center" class="dataValue"><%=phonevaritaion.get("reporteddate")%></td>
																			</tr>
																			<%
																				}
																					} catch (Exception ec) {
																						ec.printStackTrace();
																					}
																			%>

																		</tbody>
																	</table>
																</td>
															</tr>
															<tr>
																<td height="5px"></td>
															</tr>
															<tr>
																<td bgcolor="#FFFFFF">
																	<table class="box1" border="0px"
																		bordercolor="lightgray" cellpadding="3"
																		cellspacing="0">
																		<tbody>
																			<%
																				try {
																						JSONObject identityinfo = idandcontactinfo.getJSONObject("identityinfo");
																						JSONObject panObject = identityinfo.getJSONObject("panid");
																						JSONObject voteridObject = identityinfo.getJSONObject("voterid");
																			%>
																			<tr height="20">
																				<td align="center" width="230px" class="subHeader">Pan Id
																					Variations</td>
																				<td align="center" width="90px" class="subHeader">Reported
																					On</td>
																			</tr>
																			<tr height="20">
																				<td class="dataValue"><%=panObject.get("idnumber")%></td>
																				<td align="center" class="dataValue"><%=panObject.get("reporteddate")%></td>
																			</tr>
																			<%
																				} catch (Exception ec) {
																						ec.printStackTrace();
																					}
																			%>
																		</tbody>
																	</table>
																</td>
															</tr>
															<tr>
																<td bgcolor="#FFFFFF">
																	<table class="box1" border="0px"
																		bordercolor="lightgray" cellpadding="3"
																		cellspacing="0">
																		<tbody>
																			<%
																				try {
																						JSONObject identityinfo = idandcontactinfo.getJSONObject("identityinfo");

																						JSONObject voteridObject = identityinfo.getJSONObject("voterid");
																			%>
																			<tr height="20">
																				<td align="center" width="230px" class="subHeader">Voter Id
																					Variations</td>
																				<td align="center" width="90px" class="subHeader">Reported
																					On</td>
																			</tr>
																			<tr height="20">
																				<td class="dataValue"><%=voteridObject.get("idnumber")%></td>
																				<td align="center" class="dataValue"><%=voteridObject.get("reporteddate")%></td>
																			</tr>
																			<%
																				} catch (Exception ec) {
																						ec.printStackTrace();
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
										</tbody>
									</table> <%
 	} catch (Exception ec) {
 		ec.printStackTrace();
 	}
 %>


								</td>
							</tr>
							<tr>
								<td>
									<table align="center" border="0" cellpadding="0"
										cellspacing="0">
										<tbody>
											<tr height="10">
												<td align="right" bgcolor="#FFFFFF" class="infoValue"></td>
											</tr>
											<tr height="20">
												<td align="right" bgcolor="#FFFFFF" class="infoValue">Tip:
													All amounts are in INR.</td>
											</tr>
											<tr></tr>
											<tr>
												<td>
													<table align="center" bgcolor="#0f3f6b" border="0"
														width="1020px">
														<tbody>
															<tr height="20">
																<td width="10"></td>
																<td class="mainHeader">Account Summary</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
											<tr height="20">
												<td align="right" bgcolor="#FFFFFF" class="infoValue">Tip:
													Current Balance & Disbursed Amount is considered ONLY for
													ACTIVE accounts.</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td align="right" bgcolor="#FFFFFF" class="infoValue"
									height="20"></td>
							</tr>
							<tr>
								<td>
									<%---Account Details --%> <%
 	try {
 		String AccountArray = request.getAttribute("account").toString();
 		JSONArray accounts = new JSONArray(AccountArray);
 %>
									<table align="center" border="0" cellpadding="0"
										cellspacing="0" width="1020px">
										<tbody>
											<tr>
												<td>
													<table align="center"
														style="border-collapse: collapse; border: 2px solid #A7CBE3;"
														cellspacing="0" cellpadding="2" width="1000px">
														<tbody>
															<tr height="20">
																<td>
																	<table align="center" border="0px" cellspacing="0"
																		cellpadding="0" width="1000px">
																		<tbody>
																			<tr>
																				<td width="center">
																					<table align="center" border="0px" cellspacing="0"
																						cellpadding="0" width="1000px">
																						<tbody>
																							<tr height="20">
																								<td width="150" class="subHeader1">Last
																									Payment</td>
																								<td align="center" width="80" class="subHeader1">Reported
																									Date</td>
																								<td align="center" width="80" class="subHeader1">Account
																									Type</td>
																								<td align="center" width="80" class="subHeader1">Institution</td>
																								<td align="right" width="80" class="subHeader1">Current
																									Balance</td>
																								<td align="right" width="80" class="subHeader1">Term
																									Frequency</td>
																								<td align="right" width="80" class="subHeader1">Account
																									Status</td>
																								<td align="right" width="80" class="subHeader1">Installment
																									amount</td>
																								<td align="right" width="80" class="subHeader1">Sanction
																									Amount amount</td>
																								<td align="right" width="80" class="subHeader1">Repayment
																									Tenure</td>
																								<td align="right" width="100" class="subHeader1">Date
																									Opened</td>
																								<td align="right" width="100" class="subHeader1">Last
																									Payment date</td>

																							</tr>
																							<%
																								for (int i = 0; i < accounts.length(); i++) {
																										JSONObject singleAccount = accounts.getJSONObject(i);
																										try {
																							%>
																							<tr height="20">
																								<%
																									try {
																								%>
																								<td align="left" class="dataHeader"><%=singleAccount.get("lastpayment")%></td>
																								<%
																									} catch (Exception ec) {
																													ec.getMessage();
																												}
																								%>
																								<%
																									try {
																								%>
																								<td align="center" class="AccValue"><%=singleAccount.get("reporteddate")%></td>
																								<%
																									} catch (Exception ec) {
																													ec.getMessage();
																												}
																								%>
																								<%
																									try {
																								%>
																								<td align="center" class="AccValue"><%=singleAccount.get("accounttype")%></td>
																								<%
																									} catch (Exception ec) {
																													ec.getMessage();
																												}
																								%>
																								<%
																									try {
																								%>
																								<td align="center" class="AccValue"><%=singleAccount.get("institution")%></td>
																								<%
																									} catch (Exception ec) {
																													ec.getMessage();
																												}
																								%>
																								<%
																									try {
																								%>
																								<td align="center" class="AccValue"><%=singleAccount.get("balance")%></td>
																								<%
																									} catch (Exception ec) {
																													ec.getMessage();
																												}
																								%>
																								<%
																									try {
																								%>
																								<td align="center" class="AccValue"><%=singleAccount.get("termfrequency")%></td>
																								<%
																									} catch (Exception ec) {
																													ec.getMessage();
																												}
																								%>
																								<%
																									try {
																								%>
																								<td align="center" class="AccValue"><%=singleAccount.get("accountstatus")%></td>
																								<%
																									} catch (Exception ec) {
																													ec.getMessage();
																												}
																								%>
																								<%
																									try {
																								%>
																								<td align="center" class="AccValue"><%=singleAccount.get("installmentamount")%></td>
																								<%
																									} catch (Exception ec) {
																													ec.getMessage();
																												}
																								%>
																								<%
																									try {
																								%>
																								<td align="center" class="AccValue"><%=singleAccount.get("sanctionamount")%></td>
																								<%
																									} catch (Exception ec) {
																													ec.getMessage();
																												}
																								%>
																								<%
																									try {
																								%>
																								<td align="center" class="AccValue"><%=singleAccount.get("repaymenttenure")%></td>
																								<%
																									} catch (Exception ec) {
																													ec.getMessage();
																												}
																								%>
																								<%
																									try {
																								%>
																								<td align="right" class="AccValue"><%=singleAccount.get("dateopened")%></td>
																								<%
																									} catch (Exception ec) {
																													ec.getMessage();
																												}
																								%>
																								<%
																									try {
																								%>
																								<td align="right" class="AccValue"><%=singleAccount.get("lastpaymentdate")%></td>
																								<%
																									} catch (Exception ec) {
																													ec.getMessage();
																												}
																								%>
																							</tr>
																							<%
																								} catch (Exception ec) {
																											ec.getMessage();
																										}
																									}
																							%>
																						</tbody>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="10"></td>
																			</tr>
																			<tr>
																				<td>
																					<table align="center" border="0px" cellspacing="0"
																						width="1000">
																						<tbody>
																							<tr bgcolor="#FFFFFF">
																								<td colspan="2" align="left" bgcolor="#FFFFFF"
																									width="300" class="AccHeader">Inquiries in
																									last 24 Months: <font color="#464646"></font>
																								</td>
																								<td colspan="2" align="center" bgcolor="#FFFFFF"
																									width="300" class="AccHeader">New
																									Account(s) in last 6 Months: <font
																									color="#464646"> 0 </font>
																								</td>
																								<td colspan="2" align="right" bgcolor="#FFFFFF"
																									width="300" class="AccHeader">New
																									Delinquent Account(s) in last 6 Months: <font
																									color="#464646">0 </font>
																								</td>
																								<td align="right" width="5" class="AccHeader"></td>
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
									</table> <%
 	} catch (Exception ec) {
 		ec.printStackTrace();
 	}
 %>

								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<tr>
						<td height="5"></td>
					</tr>
					<tr>
						<td>
							<table align="center" border="0" cellpadding="0" cellspacing="0">
								<tbody>
									<tr>
										<td>
											<table class="box1" align="center" border="0px"
												cellpadding="0" cellspacing="0" width="1000px">
												<tbody>
												</tbody>
											</table>
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td height="10"></td>
					</tr>
				</td>
			</tr>
			<tr>
				<td>
					<table align="center" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr height="30"></tr>
							<tr>
								<td>
									<table align="center" bgcolor="#0f3f6b" border="0"
										width="1020px">
										<tbody>
											<tr height="20">
												<td width="10"></td>
												<td class="mainHeader">Account Information</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table> <br></br>
				</td>
			</tr>
			<tr>
				<td>
					<table align="center" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td>
									<table align="center" border="0" width="1020px" cellpadding="0"
										cellspacing="0">
										<tbody>
											<tr height="20">
												<td align="center" class="mainAccHeader" width="20px">1
												</td>
												<td align="center">
													<table align="left" border="0" width="1000px"
														bgcolor="e6e6ff" cellpadding="2" cellspacing="0">
														<tbody>
															<tr height="20">
																<td align="left" width="400" class="AccHeader"
																	nowrap="true">Account Details: <font
																	class="maroonFields" nowrap="true"></td>

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
									<%
										try {
											JSONObject AccountSummary = new JSONObject(request.getAttribute("accountsummary").toString());
									%>
									<table align="center" border="0" width="1000" cellpadding="0"
										cellspacing="0">
										<tr>
											<td class="container" width="30">
												<!-- 												<div class="headActive" width="30"> --> <!-- 													<div class="vertActive" width="30" align="center" -->
												<!-- 														style="background: #ffe1dc; text-align: center;">ACTIVE -->
												<!-- 													</div> --> <!-- 												</div> -->
											</td>
											<td>
												<table align="center" border="0" cellpadding="0"
													cellspacing="0" width="1000px">
													<tbody>
														<tr height="10"></tr>
														<tr height="25">
															<td width="100" class="dataHeader">&nbsp;&nbsp;Oldest
																Account</td>
															<td width="160" class="dataValue"><%=AccountSummary.get("oldestaccount")%></td>
															<td width="20"></td>
															<td width="100" class="dataHeader">Number Of Zero
																Balance Account</td>
															<td width="80" class="dataValue"><%=AccountSummary.get("noofzerobalanceaccounts")%></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">Number Of
																Accounts</td>
															<td width="90" align="right" class="dataAmtValue"><%=AccountSummary.get("noofaccounts")%>
															</td>
															<td width="20"></td>
														</tr>
														<tr height="25">
															<td width="100" class="dataHeader">&nbsp;&nbsp;Total
																Sanctioned Amount:-</td>
															<td width="160" class="dataValue"><%=AccountSummary.get("totalsanctionamount")%></td>
															<td width="20"></td>
															<td width="100" class="dataHeader">Total Credit
																Limit:-</td>
															<td width="80" class="dataValue"><%=AccountSummary.get("totalcreditlimit")%></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">Recent Account:-</td>
															<td width="90" align="right" class="dataAmtValue"><%=AccountSummary.get("recentaccount")%>
															</td>
															<td width="20"></td>
														</tr>
														<tr height="25">
															<td width="100" class="dataHeader">&nbsp;&nbsp;Single
																Highest Sanction Amount:-</td>
															<td width="160" class="dataValue"><%=AccountSummary.get("singlehighestsanctionamount")%></td>
															<td width="20"></td>
															<td width="100" class="dataHeader">Total High
																Credit:-</td>
															<td width="80" class="dataValue"><%=AccountSummary.get("totalhighcredit")%></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">Total Balanced
																Amount:-</td>
															<td width="90" align="right" class="dataAmtValue"><%=AccountSummary.get("totalbalanceamount")%></td>
															<td width="20"></td>
														</tr>
														<tr height="25">
															<td width="100" class="dataHeadern">&nbsp;&nbsp;Number
																of Write Offs:-</td>
															<td width="160" class="dataValue"><%=AccountSummary.get("noofwriteoffs")%></td>
															<td width="20"></td>
															<td width="100" class="dataHeadern">Average Open
																Balance:-</td>
															<td width="80" class="dataValue"><%=AccountSummary.get("averageopenbalance")%></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">Total Monthly
																Payment Amount:-</td>
															<td width="90" align="right" class="dataAmtValue"><%=AccountSummary.get("totalmonthlypaymentamount")%>
															</td>
															<td width="20"></td>
														</tr>
														<tr height="25">
															<td width="100" class="dataHeadern">&nbsp;&nbsp;Single
																highest balance:-</td>
															<td width="160" class="dataValue"><%=AccountSummary.get("singlehighestbalance")%></td>
															<td width="20"></td>
															<td width="100" class="dataHeadern">Single Highest
																Credit:-</td>
															<td width="80" class="dataValue"><%=AccountSummary.get("singlehighestcredit")%></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">Number of Active
																Accounts:-</td>
															<td width="90" align="right" class="dataAmtValue"><%=AccountSummary.get("noofactiveaccounts")%>
															</td>
															<td width="20"></td>
														</tr>
														<tr height="25">
															<td width="100" class="dataHeadern">&nbsp;&nbsp;Number
																of Past Due Account:-</td>
															<td width="160" class="dataValue"><%=AccountSummary.get("noofpastdueaccounts")%></td>
															<td width="20"></td>
															<td width="100" class="dataHeadern">Total Past Due:-</td>
															<td width="80" class="dataValue"><%=AccountSummary.get("totalpastdue")%></td>
															<td width="20"></td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
										</tbody>
									</table> <%
 	} catch (Exception ec) {
 		ec.getMessage();
 	}
 %>


								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr>

			<tr>
				<td height="20px"></td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" align="center" border="0"
						width="1000px">
						<tr>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="20px"></td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" align="center" border="0"
						width="1000px">
						<tr>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="20px"></td>
			</tr>

			<tr>
				<td>
					<table align="center" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr height="30"></tr>
							<tr>
								<td>
									<table align="center" bgcolor="#0f3f6b" border="0"
										width="1020px">
										<tbody>
											<tr height="20">
												<td width="10"></td>
												<td class="mainHeader">Enquiries</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table> <br></br>
				</td>
			</tr>
			<tr>
				<td>
					<table align="center" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td>
									<table align="center" border="0" width="1020px" cellpadding="0"
										cellspacing="0">
										<tbody>
											<tr height="20">
												<td align="center" class="mainAccHeader" width="20px">1
												</td>
												<td align="center">
													<table align="left" border="0" width="1000px"
														bgcolor="e6e6ff" cellpadding="2" cellspacing="0">
														<tbody>
															<tr height="20">
																<td align="left" width="400" class="AccHeader"
																	nowrap="true">Enquiries done at Institution <font
																	class="maroonFields" nowrap="true"></td>
																</font>
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
									<%
										try {
											JSONArray enquiries = new JSONArray(request.getAttribute("enquiries").toString());
											for (int i = 0; i < enquiries.length(); i++) {
												JSONObject enquiry = new JSONObject(enquiries.get(i).toString());
									%>
									<table align="center" border="0" width="1000" cellpadding="0"
										cellspacing="0">
										<tr>
											<td class="container" width="30"></td>
											<td>
												<table align="center" border="0" cellpadding="0"
													cellspacing="0" width="1000px">
													<tbody>
														<tr height="10"></tr>
														<tr height="25">
															<td width="100" class="dataHeader">&nbsp;&nbsp;Date:-
															</td>
															<td width="160" class="dataValue"><%=enquiry.get("date").toString()%></td>
															<td width="20"></td>
															<td width="100" class="dataHeader">Institution:-</td>
															<td width="80" class="dataValue"><%=enquiry.get("institution").toString()%></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">Time:-</td>
															<td width="90" align="right" class="dataAmtValue"><%=enquiry.get("time").toString()%>
															</td>
															<td width="20"></td>
														</tr>
														<tr height="25">
															<td width="100" class="dataHeader">&nbsp;&nbsp;Request
																Purpose:-</td>
															<td width="160" class="dataValue"><%=enquiry.get("requestpurpose").toString()%></td>
															<td width="20"></td>
															<td width="20"></td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
										</tbody>
									</table> <%
 	}
 	} catch (Exception ec) {
 		ec.getMessage();
 	}
 %>


								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td height="20px"></td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" align="center" border="0"
						width="1000px">
						<tr>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="20px"></td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" align="center" border="0"
						width="1000px">
						<tr>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>


			<tr>
				<td>
					<table align="center" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr height="30"></tr>
							<tr>
								<td>
									<table align="center" bgcolor="#0f3f6b" border="0"
										width="1020px">
										<tbody>
											<tr height="20">
												<td width="10"></td>
												<td class="mainHeader">Enquiry Summary</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table> <br></br>
				</td>
			</tr>
			<tr>
				<td>
					<table align="center" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td>
									<table align="center" border="0" width="1020px" cellpadding="0"
										cellspacing="0">
										<tbody>
											<tr height="20">
												<td align="center" class="mainAccHeader" width="20px">1
												</td>
												<td align="center">
													<table align="left" border="0" width="1000px"
														bgcolor="e6e6ff" cellpadding="2" cellspacing="0">
														<tbody>
															<tr height="20">
																<td align="left" width="400" class="AccHeader"
																	nowrap="true">Brief Summary Of Enquiries:- <font
																	class="maroonFields" nowrap="true"></td>

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
									<%
										try {
											JSONObject enquirysummary = new JSONObject(request.getAttribute("enquirysummary").toString());
									%>
									<table align="center" border="0" width="1000" cellpadding="0"
										cellspacing="0">
										<tr>
											<td class="container" width="30"></td>
											<td>
												<table align="center" border="0" cellpadding="0"
													cellspacing="0" width="1000px">
													<tbody>
														<tr height="10"></tr>
														<tr height="25">
															<td width="100" class="dataHeader">&nbsp;&nbsp;Enquiries
																in Past 30 Days:-</td>
															<td width="160" class="dataValue"><%=enquirysummary.get("past30days")%></td>
															<td width="20"></td>
															<td width="100" class="dataHeader">Total Enquiries:-</td>
															<td width="80" class="dataValue"><%=enquirysummary.get("total")%></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">Purpose Of
																Enquiries:-</td>
															<td width="90" align="right" class="dataAmtValue"><%=enquirysummary.get("purpose")%>
															</td>
															<td width="20"></td>
														</tr>
														<tr height="25">
															<td width="100" class="dataHeader">&nbsp;&nbsp;Enquiries
																in Past 12 Month:-</td>
															<td width="160" class="dataValue"><%=enquirysummary.get("past12months")%></td>
															<td width="20"></td>
															<td width="100" class="dataHeader">Enquiries in Past
																24 months:-</td>
															<td width="80" class="dataValue"><%=enquirysummary.get("past24months")%></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">Date of Recent
																Enquiries:-</td>
															<td width="90" align="right" class="dataAmtValue"><%=enquirysummary.get("recent")%></td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
										</tbody>
									</table> <%
 	} catch (Exception ec) {
 		ec.getMessage();
 	}
 %>


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
							<tr height="30"></tr>
							<tr>
								<td>
									<table align="center" bgcolor="#0f3f6b" border="0"
										width="1020px">
										<tbody>
											<tr height="20">
												<td width="10"></td>
												<td class="mainHeader">Scoring Elements</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table> <br></br>
				</td>
			</tr>
			<tr>
				<td>
					<table align="center" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td>
									<table align="center" border="0" width="1020px" cellpadding="0"
										cellspacing="0">
										<tbody>
											<tr height="20">
												<td align="center" class="mainAccHeader" width="20px">1
												</td>
												<td align="center">
													<table align="left" border="0" width="1000px"
														bgcolor="e6e6ff" cellpadding="2" cellspacing="0">
														<tbody>
															<tr height="20">
																<td align="left" width="400" class="AccHeader"
																	nowrap="true">Brief Summary Of scores:- <font
																	class="maroonFields" nowrap="true"></td>

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
									
									<table align="center" border="0" width="1000" cellpadding="0"
										cellspacing="0">
										<tr>
											<td class="container" width="30"></td>
											<td>
												<table align="center" border="0" cellpadding="0"
													cellspacing="0" width="1000px">
													<tbody>
											<% 
											try
											{
											String scoringelements=request.getAttribute("scoringelements").toString();
											JSONArray scoringElements=new JSONArray(scoringelements);
											for(int k=0;k<scoringElements.length();k++)
											{
							                JSONObject Score = scoringElements.getJSONObject(k);
									        %>
														<tr height="10"></tr>
														
														<tr height="25">
															<td width="100" class="dataHeader">&nbsp;&nbsp;Code:-</td>
															<td width="160" class="dataValue"><%=Score.get("code").toString()%></td>
															<td width="20"></td>
															<td width="100" class="dataHeader">Discription:-</td>
															<td width="80" class="dataValue"><%=Score.get("description").toString()%></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">Type:-</td>
															<td width="90" align="right" class="dataAmtValue">
															<%=Score.get("type").toString()%>
															</td>
															<td width="20"></td>
														</tr>
											<% }
											}
											catch(Exception ec)
											{
												ec.printStackTrace();
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
							
			<!--  Other Key Details -->				
			<tr>
				<td>
					<table align="center" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr height="30"></tr>
							<tr>
								<td>
									<table align="center" bgcolor="#0f3f6b" border="0"
										width="1020px">
										<tbody>
											<tr height="20">
												<td width="10"></td>
												<td class="mainHeader">Other Key IND</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table> <br></br>
				</td>
			</tr>
			<tr>
				<td>
					<table align="center" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td>
									<table align="center" border="0" width="1020px" cellpadding="0"
										cellspacing="0">
										<tbody>
											<tr height="20">
												<td align="center" class="mainAccHeader" width="20px">1
												</td>
												<td align="center">
													<table align="left" border="0" width="1000px"
														bgcolor="e6e6ff" cellpadding="2" cellspacing="0">
														<tbody>
															<tr height="20">
																<td align="left" width="400" class="AccHeader"
																	nowrap="true">Brief Summary OtherKeyIndType:- <font
																	class="maroonFields" nowrap="true"></td>

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
									
									<table align="center" border="0" width="1000" cellpadding="0"
										cellspacing="0">
										<tr>
											<td class="container" width="30"></td>
											<td>
												<table align="center" border="0" cellpadding="0"
													cellspacing="0" width="1000px">
													<tbody>
											<% 
											try
											{
											String otherKeyInd=request.getAttribute("otherkeyind").toString();
											JSONObject otherKeyIndJSON=new JSONObject(otherKeyInd);
											
									        %>
														<tr height="10"></tr>
														
														<tr height="25">
															<td width="100" class="dataHeader">&nbsp;&nbsp;AgeOfOldestTrade:-</td>
															<td width="160" class="dataValue"><%=otherKeyIndJSON.get("ageofoldesttrade").toString()%></td>
															<td width="20"></td>
															<td width="100" class="dataHeader">NumberOfOpenTrades:-</td>
															<td width="80" class="dataValue"><%=otherKeyIndJSON.get("numberofopentrades").toString()%></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">AllLinesEVERWritten:-</td>
															<td width="90" align="right" class="dataAmtValue">
															<%=otherKeyIndJSON.get("alllineseverwritten").toString()%>
															</td>
															<td width="20"></td>
														</tr>
														<tr height="10"></tr>
														<tr height="25">
															<td width="100" class="dataHeader">Number of accounts written off within 9 months:-</td>
															<td width="160" class="dataValue"><%=otherKeyIndJSON.get("alllineseverwrittenin9months").toString()%></td>
															<td width="20"></td>
															<td width="100" class="dataHeader">Number of accounts written off within 6 months:-</td>
															<td width="80" class="dataValue"><%=otherKeyIndJSON.get("alllineseverwrittenin6months").toString()%></td>
															<td width="20"></td>
															<td width="20"></td>
														</tr>
											<% 
											}
											catch(Exception ec)
											{
												ec.printStackTrace();
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
							
							
							<!--Recent activity  -->
							<tr>
								<td>
									<table align="center" border="0" cellpadding="0" cellspacing="0">
										<tbody>
											<tr height="30"></tr>
											<tr>
												<td>
													<table align="center" bgcolor="#0f3f6b" border="0"
														width="1020px">
														<tbody>
															<tr height="20">
																<td width="10"></td>
																<td class="mainHeader">Recent Activity</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table> <br></br>
								</td>
							</tr>
			
							<tr>
				<td>
					<table align="center" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td>
									<table align="center" border="0" width="1020px" cellpadding="0"
										cellspacing="0">
										<tbody>
											<tr height="20">
												<td align="center" class="mainAccHeader" width="20px">1
												</td>
												<td align="center">
													<table align="left" border="0" width="1000px"
														bgcolor="e6e6ff" cellpadding="2" cellspacing="0">
														<tbody>
															<tr height="20">
																<td align="left" width="400" class="AccHeader"
																	nowrap="true">Brief Summary Recent Activity:- <font
																	class="maroonFields" nowrap="true"></td>

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
									
									<table align="center" border="0" width="1000" cellpadding="0"
										cellspacing="0">
										<tr>
											<td class="container" width="30"></td>
											<td>
												<table align="center" border="0" cellpadding="0"
													cellspacing="0" width="1000px">
													<tbody>
											<% 
											try
											{
											String recentactivities=request.getAttribute("recentactivities").toString();
											JSONObject recentactivitiesJSON=new JSONObject(recentactivities);
											
									        %>
														<tr height="10"></tr>
														
														<tr height="25">
															<td width="100" class="dataHeader">&nbsp;&nbsp;Total Enquiries:-</td>
															<td width="160" class="dataValue"><%=recentactivitiesJSON.get("totalinquiries").toString()%></td>
															<td width="20"></td>
															<td width="100" class="dataHeader">Accounts Updated:-</td>
															<td width="80" class="dataValue"><%=recentactivitiesJSON.get("accountsupdated").toString()%></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">Account Deliquent:-</td>
															<td width="90" align="right" class="dataAmtValue">
															<%=recentactivitiesJSON.get("accountsdeliquent").toString()%>
															</td>
															<td width="20"></td>
														</tr>
														<tr height="10"></tr>
														<tr height="25">
															<td width="100" class="dataHeader">Accounts Opened:-</td>
															<td width="160" class="dataValue"><%=recentactivitiesJSON.get("accountsopened").toString()%></td>
															<td width="20"></td>
														</tr>
											<% 
											}
											catch(Exception ec)
											{
												ec.printStackTrace();
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
							
							<!--Recent activity  -->
							
						</tbody>
					</table>
				</td>
			</tr>
			
			<tr>
				<td height="10px"></td>
			</tr>
			<tr>
				<td height="20px"></td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" align="center" border="0"
						width="1000px">
						<tr>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="20px"></td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" align="center" border="0"
						width="1000px">
						<tr>
							<td></td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
				<td>
					<table summary="" align="center" border="0" cellpadding="0"
						cellspacing="0">
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
												<td color="#CCCCCC" valign="top" width="70"
													class="disclaimerValue">Disclaimer:</td>
												<td colspan="4" class="disclaimerValue">
													<%
														try {
															String disclaimer = request.getAttribute("disclaimer").toString();
													%> <%=disclaimer%> <%
 	} catch (Exception ec) {
 		ec.printStackTrace();
 	}
 %>


												</td>
											</tr>
											<tr>
												<td><br><br> <br><br></td>
												<td color="#CCCCCC " align="left" width="300"
													class="disclaimerValue">Copyrights reserved (c) 2016</td>
												<td color="#CCCCCC " align="center" width="400"
													class="disclaimerValue">Equifax Information Services
													Pvt. Ltd</td>
												<td color="#CCCCCC " align="right" width="300"
													class="disclaimerValue">Company Confidential Data</td>
												<td width="70"><br><br> <br><br></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			</html>