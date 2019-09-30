<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>CONSUMER ID VERIFICATION REPORT</title>
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
	color: #43acf2;
	font-weight: 600;
	text-align: left;
}

.dataHead {
	font-family: segoe ui semibold;
	font-size: 12px;
	font-weight: 600;
	color: #43acf2;
	text-align: right;
	text-indent: 5px;
}

.mainHeader {
	font-family: segoe ui semibold;
	font-size: 16px;
	color: #FFFFFF;
	background: #43acf2;
	text-align: left;
	font-weight: 600;
	padding-bottom: 3px;
}

.subHeader {
	font-family: segoe ui semibold;
	font-size: 13px;
	color: #43acf2;
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
	color: #43acf2;
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
	color: #1675a3;
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
	color: #1675a3;
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
	color: #1675a3;
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
	color: #1675a3;
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
	background: #43acf2;
	font-weight: 600;
}

.AccHeader {
	font-family: segoe ui semibold;
	font-size: 13px;
	color: #43acf2;
	font-weight: 600;
	text-indent: 5px;
}

.subAccHeader {
	font-family: segoe ui semibold;
	font-size: 13px;
	color: #43acf2;
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
	-ms-writing-mode: tb-rl; *
	writing-mode: tb-rl;
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
	-ms-writing-mode: tb-rl; *
	writing-mode: tb-rl;
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
		<%@ page import="org.apache.log4j.Logger"%>
		<%
			Logger logger = Logger.getLogger("EquifaxUserFriendlyOutPutForEVDR.jsp");
		    logger.debug("start executing EquifaxUserFriendlyOutPutForEVDR.jsp");
		%>
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
												<td colspan="1" valign="top"><img
													src="images/equifax-logo.png" alt="Equifax Services"
													align="left" /></td>

												<td width="120"></td>
												<td align="left" width="380" valign="top">
													<table border="0" cellpadding="0" cellspacing="0">
														<tbody>
															<tr>
																<td align="left" class="reportHead"><b>Equifax Consumer Id Verification Report</b><br /> <br />
																</td>
															</tr>
															<tr valign="top">

															</tr>
														</tbody>
													</table>
												</td>
												<td width="70"></td>
												<td rowspan="2" align="right" valign="top" width="350">
													<table>
														<tbody>
															<tr>
																<td align="left" class="dataHeader"
																	style="color: #43acf2; font-family: 'Stardos Stencil'; font-size: 12px;">Date:</td>
																<td></td>
																<td class="dataValue1"
																	style="color: black; font-family: 'Stardos Stencil'; font-size: 12px;">
																	<%
																		try {
																	%> <%=new JSONObject(""+request.getAttribute("inquiryresponseheader")).get("date")%>
																	<%
																		} catch (Exception ec) {
																			logger.debug("We are in Exception while getting date key from responsetransaction"+ec);
																		}
																	%>
																</td>
															</tr>
															<tr>
																<td align="left" class="dataHeader"
																	style="color: #43acf2; font-family: 'Stardos Stencil'; font-size: 12px;">Time:</td>
																<td></td>
																<td class="dataValue1"
																	style="color: black; font-family: 'Stardos Stencil'; font-size: 12px;">
																	<%
																		try {
																	%> <%=new JSONObject("" + request.getAttribute("inquiryresponseheader")).get("time")%>
																	<%
																		} catch (Exception ec) {
																			logger.debug("We are in Exception while getting time key from responsetransaction"+ec);
																		}
																	%>
																</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
											<tr>
												<td align="left" width="110 px" class="dataHeader"
													style="color: #43acf2; font-family: 'Stardos Stencil'; font-size: 12px;">CLIENT
													ID:</td>
												<td></td>
											</tr>
											<tr>
												<td align="left" width="110 px" class="dataHeader"
													style="color: #43acf2; font-family: 'Stardos Stencil'; font-size: 12px;">REPORT
													ORDER NO:</td>

												<td class="dataValue1"
													style="color: black; font-family: 'Stardos Stencil'; font-size: 12px;">
													<%
														try {
													%> <%=new JSONObject("" + request.getAttribute("inquiryresponseheader")).get("reportorderno")%>
													<%
														} catch (Exception ec) {
															logger.debug("We are in Exception while getting reportorderno key from responsetransaction"+ec);
														}
													%>
												</td>
											</tr>
											<tr>
												<td align="left" width="110 px" class="dataHeader"
													style="color: #43acf2; font-family: 'Stardos Stencil'; font-size: 12px;">REFERENCE
													NUMBER:</td>
												<td></td>
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
		
				
			
			
	
		
			
			<!-- NSDL Information start-->
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
													<table align="center" bgcolor="#43acf2" border="0"
														width="1020px">
														<tbody>
															<tr height="20">
																<td width="10"></td>
																<td class="mainHeader">NSDL Information</td>

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
										cellspacing="0" width="1030px">
										<tbody>
											<tr>
												<td>
													<table align="center" border="0" width="1030px">
														<tbody>
															<tr>
																<td>
																	<%
																		try {
																			
																			JSONObject nsdldatajson=null;
																			String nsdldata = request.getAttribute("nsdldata").toString();
																			JSONArray jsonarray= new JSONArray(nsdldata);
																			for(int i=0; i < jsonarray.length(); i++) {
        																			 nsdldatajson = jsonarray.getJSONObject(i);
																		
																			%>
																	<table border="0" width="1030px">
																		<tbody>
																			<tr>
																				<td height="10px"></td>
																			</tr>
																			<tr>

																				<!-- Personal Information-->



																				<%
																					try {
																							
																							JSONObject nsdlrequestJson = nsdldatajson.getJSONObject("nsdlrequest");
																				%>

																				<td align="left" width="110 px" class="dataHeader">Source:</td>
																				<td align="left" width="270 px" class="dataValue">
																					<%
																						try {
																					%> <%=nsdlrequestJson.get("source").toString()%>
																					<%
																						} catch (Exception e) {
																							logger.debug("We are in Exception while getting  key source from nsdldata"+e);
																								}
																					%>
																				</td>
																				<td width="70 px" class="dataHeader">PAN
																					Number:</td>
																				<td width="190 px" class="dataValue">
																					<%
																						try {
																					%> <%=nsdlrequestJson.get("pannumber").toString()%>
																					<%
																						} catch (Exception ex) {
																							logger.debug("We are in Exception while getting  key pannumber from nsdldata"+ex);
																								}
																					%>
																				</td>

																				<%
																					} catch (Exception ec) {
																						logger.debug("We are in Exception while getting  nsdlrequest"+ec);
																						}
																				%>

																				<td align="left" width="110 px" class="dataHeader">Percentage
																					match:</td>

																				<td align="left" width="270 px" class="dataValue">
																					<%
																						try {
																					%><%=nsdldatajson.getJSONObject("nsdlresponse").get("percentmatch").toString()%>
																					<%
																						} catch (Exception e) {
																							logger.debug("We are in Exception while getting  key percentmatch from nsdlresponse"+e);
																							}
																					%>
																				</td>
																				<td align="left" width="110 px" class="dataHeader">Last
																					Updated Date:</td>
																				<td align="left" width="270 px" class="dataValue">
																					<%
																						try {
																					%><%=nsdldatajson.getJSONObject("nsdlresponse").get("lastupdateddate").toString()%>
																					<%
																						} catch (Exception e) {
																							logger.debug("We are in Exception while getting  key lastupdateddate from nsdlresponse"+e);
																							}
																					%>
																				</td>

																			</tr>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			<tr>

																				<td width="70 px" class="dataHeader">Return Code:</td>
																				<td width="200 px" class="dataValue">
																					<%
																						try {
																					%><%=nsdldatajson.getJSONObject("nsdlresponse").get("returncode").toString()%>
																					<%
																						} catch (Exception e) {
																							logger.debug("We are in Exception while getting  key returncode from nsdlresponse"+e);
																							}
																					%>
																				</td>
																				<td width="70 px" class="dataHeader">Nsdl
																					Response ID:</td>
																				<td width="200 px" class="dataValue">
																					<%
																						try {
																					%><%=nsdldatajson.getJSONObject("nsdlresponse").get("nsdlrespid").toString()%>
																					<%
																						} catch (Exception e) {
																							logger.debug("We are in Exception while getting  key nsdlrespid from nsdlresponse"+e);
																							}
																					%>
																				</td>
																				<td width="70 px" class="dataHeader">PAN Status:</td>
																				<td width="200 px" class="dataValue">
																					<%
																						try {
																					%><%=nsdldatajson.getJSONObject("nsdlresponse").get("panstatus").toString()%>
																					<%
																						} catch (Exception e) {
																							logger.debug("We are in Exception while getting  key panstatus from nsdlresponse"+e);
																							}
																					%>
																				</td>
																				
																				<td width="70 px" class="dataHeader">Name:</td>
																				<td width="200 px" class="dataValue">
																					<%
																						try {
																					%><%=nsdldatajson.getJSONObject("nsdlresponse").get("firstname").toString() + " "
																						+ nsdldatajson.getJSONObject("nsdlresponse").get("lastname").toString()%>
																					<%
																						} catch (Exception e) {
																							logger.debug("We are in Exception while getting  key firstname,lastname from nsdlresponse"+e);
																							}
																					%>
																				</td>
																			</tr>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			<tr>

																				<td width="70 px" class="dataHeader">Response
																					Description:</td>
																				<td width="200 px" class="dataValue">
																					<%
																						try {
																					%><%=nsdldatajson.getJSONObject("nsdlresponse").get("returncodedesc").toString()%>
																					<%
																						} catch (Exception e) {
																							logger.debug("We are in Exception while getting  key returncodedesc from nsdlresponse"+e);
																							}
																					%>
																				</td>

																			</tr>

																			<tr>
																				<td height="5px"></td>
																			</tr>
																		</tbody>
																	</table> 																			    
																			    
																			    
																			    
																	<%}
																			
																			//JSONObject nsdldatajson = new JSONObject(nsdldata);
																			
																	

 	} catch (Exception ec) {
 		logger.debug("We are in Exception while getting  nsdldata"+ec);
 	}
 %> <!-- Personal Information-->
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
			<tr height="20px">
			</tr>
			<tr>
				<td height="10">
					<hr size="3px" color="#1675a3" />
				</td>
			</tr>
		<!-- NSDL Information END-->
			
			<tr>
				<td><b class="dataHeader">Disclaimer:</b>
				<%try{ %><%=request.getAttribute("disclaimer")%>
				 <%}catch(Exception e)
				 {
					logger.debug("We are in Exception while getting  key idnumber from voterid"+e);
				 } %>
				</td>
			</tr>
		</tbody>
	</table>
	<%logger.debug("EquifaxUserFriendlyOutPutForEVDR.jsp executing fineshed"); %>
</body>
</html>