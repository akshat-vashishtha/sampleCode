
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
											<tr height="10">
												<td></td>
											</tr>
											
                                                    <td colspan="2" valign="top" style=""><img
													src="http://www.qualtech-consultants.com//images/qc.jpg"
													alt="Equifax Service" align="left" width="166" height="72" /></td>
												<td width="120"></td>
												<td align="left" width="380" valign="top">
													<table border="0" cellpadding="0" cellspacing="0">
														<tbody>
															<tr>
																<td align="left" class="reportHead"><b>Equifax Micro Finance
																	Base & Trade Report </b><br></br>
																</td>
															</tr>
															<tr valign="top">
																<td class="dataHead" align="right" valign="top">
																	<%try{
																		JSONObject GeneralUserInfo=new JSONObject(request.getAttribute("GeneralUserInfo").toString());
																	%>
																	<%=GeneralUserInfo.get("fName").toString()+"  "+GeneralUserInfo.get("mName").toString()+" "+GeneralUserInfo.get("lName").toString() %>
																	<%      
																	}catch(Exception ec){ec.printStackTrace();} %>
																	
																	</td>
															</tr>
														</tbody>
													</table>
												</td>
												<td width="70"></td>
												<td rowspan="2" align="right" valign="top" width="350">
													<table>
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
									<table align="center" border="0" cellpadding="0"
										cellspacing="0" width="1030px">
										<tbody>
											<tr>
												<td>
													<table align="center" border="0" width="1030px">
														<tbody>
															<tr>
																<td>
																
																
																
																
																<%try{
																	
																JSONObject GeneralUserInfo=new JSONObject(request.getAttribute("GeneralUserInfo").toString());
															
																%>
																
																	<table border="0" width="1030px">
																		<tbody>
																			<tr>
																				<td height="10px"></td>
																			</tr>
																			<tr>
																				<td align="left" width="110 px" class="dataHeader">Name:</td>
																				<td align="left" width="270 px" class="dataValue">
																					<%=GeneralUserInfo.get("fName")%></td>
																				<td width="70 px" class="dataHeader">DOB/Age:</td>
																				<td width="190 px" class="dataValue"><%=GeneralUserInfo.get("dob")%>
																					</td>
																				<td width="70 px" class="dataHeader">Gender:</td>
																				<td width="200 px" class="dataValue"></td>
																			</tr>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			<tr>
																				<td align="left" width="100 px" class="dataHeader"><%=GeneralUserInfo.get("keyPersonType")%></td>
																				<td align="left" width="200 px" class="dataValue"><%=GeneralUserInfo.get("keyPersonName")%></td>
																				<td width="70 px" class="dataHeader">Spouse:</td>
																				<td width="100 px" class="dataValue"></td>
																				<td width="70 px" class="dataHeader">Mother:</td>
																				<td width="120 px" class="dataValue"></td>
																			</tr>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			<tr>
																			<%try{ 
																			JSONObject PhoneJSonObject=	new JSONObject(new JSONArray(GeneralUserInfo.get("phones").toString()).get(0).toString());
																				
																			%>
																				<td class="dataHeader" valign="top" width="100 px">Phone
																					Numbers:</td>
																				<td valign="top">
																					<table width="200px" cellpadding="0"
																						cellspacing="0">
																						<tr>
																							<td class="dataValue"><%=PhoneJSonObject.get("teleNo")%></td>
																						</tr>
																						<tr>
																							<td class="dataValue"></td>
																						</tr>
																						<tr>
																							<td class="dataValue"></td>
																						</tr>
																					</table>
																				</td>
																				<%}catch(Exception ec){ec.printStackTrace();} %>
																				<%try
																				{
																				JSONArray  IdsArray=new JSONArray(GeneralUserInfo.get("ids").toString());
																				JSONObject Ids=new JSONObject(IdsArray.get(0).toString());
																				
																				%>
																				<td class="dataHeader" valign="top">ID(s):</td>
																				<td valign="top">
																					<table width="200px" cellpadding="0"
																						cellspacing="0">
																						<tr>
																							<td class="dataValue"><%=Ids.get("idNo").toString() %> [<%=Ids.get("idName").toString() %> ]
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
																				<%}catch(Exception ec){ec.printStackTrace();} %>
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
																			<tr>
																				<td align="left" width="100 px" class="dataHeader">Entity
																					Id:</td>
																				<td align="left" width="200 px" class="dataValue"
																					colspan="5"></td>
																			</tr>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																		    <% 	try
																			{
																		    JSONArray	addresses=new JSONArray(GeneralUserInfo.get("addresses").toString());
																		    JSONObject  address=new JSONObject(addresses.get(0).toString());
																			%>
																			<tr>
																				<td align="left" width="100 px" class="dataHeader">Current
																					Address:</td>
																				<td align="left" width="200 px" class="dataValue"
																					colspan="5"><%=address.get("address") %></td>
																			</tr>
																			<% }
																			catch(Exception ec)
																			{
																			ec.printStackTrace();
																			}%>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			<tr>
																				<td align="left" width="100 px" class="dataHeader">Other
																					Address:</td>
																				<td align="left" width="200 px" class="dataValue"
																					colspan="5"></td>
																				</td>
																			</tr>
																		</tbody>
																	</table>
																	<%}catch(Exception ec){ec.printStackTrace();} %>
																	
																	
																	
																	
																</td>
															</tr>
														</tbody>
													</table>
												</td>
											</tr>
										</tbody>
									</table>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			
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
																<td class="mainHeader">Inquiry Response  Information</td>
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
																
																
																
																
																<%try{
																	
																	
														    	  JSONObject  personalinfo=new JSONObject(request.getAttribute("personalinfo").toString());
														    	   
														    	  JSONObject addressinfo=new JSONObject(request.getAttribute("addressinfo1").toString());
														    	    
														    	  JSONObject familydetails=new JSONObject( request.getAttribute("familydetails").toString());
														    	    
														    	  JSONObject  identityinfo=new JSONObject(request.getAttribute("identityinfo"));
															
																%>
																
																	<table border="0" width="1030px">
																		<tbody>
																			<tr>
																				<td height="10px"></td>
																			</tr>
																			<tr>
																				<td align="left" width="110 px" class="dataHeader">Name:</td>
																				<td align="left" width="270 px" class="dataValue"><%=personalinfo.getJSONObject("name").get("firstname").toString()+"  "+personalinfo.getJSONObject("name").get("middlename").toString()+"  "+personalinfo.getJSONObject("name").get("lastname").toString() %></td>
																				<td width="70 px" class="dataHeader">DOB/Age:</td>
																				<td width="190 px" class="dataValue"><%=personalinfo.get("dateofbirth").toString()+"/"+personalinfo.getJSONObject("age").get("age").toString()%></td>
																				<td width="70 px" class="dataHeader">Gender:</td>
																				<td width="200 px" class="dataValue"><%=personalinfo.get("gender").toString() %></td>
																			</tr>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			<tr>
																				<td align="left" width="100 px" class="dataHeader">Marital status</td>
																				<td align="left" width="200 px" class="dataValue"><%=personalinfo.get("maritalstatus").toString() %></td>
																				<td width="70 px" class="dataHeader">Gender:</td>
																				<td width="100 px" class="dataValue"><%=personalinfo.get("gender").toString() %></td>
																			</tr>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			
																			<%try{ 
																			
																			JSONObject addressinfo1=new JSONObject(request.getAttribute("addressinfo1").toString());
																			%>
																			<tr>
																				<td align="left" width="100 px" class="dataHeader">Address</td>
																				<td align="left" width="200 px" class="dataValue"><%=addressinfo1.get("address").toString()%></td>
																				<td width="70 px" class="dataHeader">Reported Date:</td>
																				<td width="100 px" class="dataValue"><%=addressinfo1.get("reporteddate").toString()%></td>
																				<td width="70 px" class="dataHeader">State:</td>
																				<td width="120 px" class="dataValue"><%=addressinfo1.get("state").toString()%></td>
																			</tr>
																			<%}catch(Exception ec) {ec.printStackTrace();}
																			%>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			<tr>
																				<td height="5px"></td>
																			</tr>
																			
                                                                          </tbody>
																	</table>
																	<%}catch(Exception ec){ec.printStackTrace();} %>
																	
																	
																	
																	
																</td>
															</tr>
														</tbody>
													</table>
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
<!-- 											<tr height="20"> -->
<!-- 												<td align="right" bgcolor="#FFFFFF" class="infoValue">Tip: -->
<!-- 													Current Balance & Disbursed Amount is considered ONLY for -->
<!-- 													ACTIVE accounts.</td> -->
<!-- 											</tr> -->
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<td align="right" bgcolor="#FFFFFF" class="infoValue"
									height="20"></td>
							</tr>
							<%try{ 
							JSONArray accounts=new JSONArray(request.getAttribute("account").toString());
							
							%>
							<tr>
								<td>
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
																							     <td width="70" class="subHeader1">Account number</td>
																								<td width="70" class="subHeader1">Date Sanctioned</td>
																								<td align="center" width="70"
																									class="subHeader1">Reported Date</td>
																								<td align="center" width="80"
																									class="subHeader1">Date Closed</td>
																								<td align="center" width="80"
																									class="subHeader1">Number Of Installments</td>
																								<td align="center" width="80"
																									class="subHeader1">Current Balance</td>	
																								<td align="right" width="80" class="subHeader1">Institution</td>
																								<td align="right" width="80" class="subHeader1">Id</td>
																								<td align="right" width="80" class="subHeader1">Dispersed Amount</td>
																								<td align="right" width="70" class="subHeader1">Date Reported</td>
																								<td align="right" width="80" class="subHeader1">Installment Amount</td>
																								<td align="right" width="80" class="subHeader1">Days Past Due</td>
																								<td align="right" width="80" class="subHeader1">sanction Amount</td>
																							</tr>
																							<%for(int i=0;i<accounts.length();i++)
																							{
																								JSONObject account=new JSONObject(accounts.get(i).toString()); %>
																							<%if(i%2==0){ %>
																							<tr height="20" bgcolor="e6e6ff">
																							    <%}else{ %>
																							    <tr height="20" >
																							    <% }%>
																							<td align="left" class="dataHeader"><%=account.get("accountnumber").toString()%></td>
																								<td align="left" class="dataHeader"><%=account.get("datesanctioned").toString()%></td>
																								<td align="center" class="AccValue"><%=account.get("reporteddate").toString()%></td>
																								<td align="center" class="AccValue"><%=account.get("dateclosed").toString()%></td>
																								<td align="center" class="AccValue"><%=account.get("noofinstallments").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("currentbalance").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("institution").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("id").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("disbursedamount").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("datereported").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("installmentamount").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("dayspastdue").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("sanctionamount").toString()%></td>
																							</tr>
																							<% }%>
																						</tbody>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="10"></td>
																			</tr>
																			<tr>
																				<td>
																				
																				
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
							<%}catch(Exception ec){ec.printStackTrace();}
							try{
							JSONArray accounts=new JSONArray(request.getAttribute("account").toString());
							
							%>
							<tr>
								<td align="right" bgcolor="#FFFFFF" class="infoValue"
									height="20"></td>
							</tr>
							<tr>
								<td>
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
																								<td width="150" class="subHeader1">Account Number</td>
																								<td align="center" width="175"
																									class="subHeader1">Repayment Tenure</td>
																								<td align="center" width="175"
																									class="subHeader1">kendra Aid Mfi</td>
																								<td align="center" width="175"
																									class="subHeader1">Date Opened</td>
																								<td align="right" width="175" class="subHeader1">Applied Amount</td>
																								<td align="right" width="175" class="subHeader1">Loan purpose</td>
																								<td align="right" width="5" class="subHeader1">BranchIdMfi</td>
																								<td align="right" width="5" class="subHeader1">Loan category</td>
																								<td align="right" width="5" class="subHeader1">Loan Cycle Id</td>
																							</tr>
																							<%for(int i=0;i<accounts.length();i++){	
																								JSONObject account=new JSONObject(accounts.get(i).toString()); %>
																								<%if(i%2==0){ %>
																							<tr height="20" bgcolor="e6e6ff">
																							    <%}else{ %>
																							    <tr height="20" >
																							    <% }%>
																							    <td align="center" class="AccValue"><%=account.get("accountnumber").toString()%></td>
																								<td align="left" class="dataHeader"><%=account.get("repaymenttenure").toString()%></td>
																								<td align="center" class="AccValue"><%=account.get("kendraidmfi").toString()%></td>
																								<td align="center" class="AccValue"><%=account.get("dateopened").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("appliedamount").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("loanpurpose").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("branchidmfi").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("loancategory").toString()%></td>
																								<td align="right" class="AccValue"><%=account.get("loancycleid").toString()%></td>
																							</tr>
																							<%} %>
																						</tbody>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="10"></td>
																			</tr>
																			<tr>
																				<td>
																					
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
						</tbody>
					</table>
				</td>
			</tr>
			<%
			}catch(Exception ec){ec.printStackTrace();} %>
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
																	nowrap="true">Account Type: <font
																	class="maroonFields" nowrap="true"></td>
																</font>
																<td align="left" width="330" class="AccHeader"
																	nowrap="nowrap">Credit Grantor: <font
																	color="#464646"> </font></td>
																<td align="left" nowrap="true" width="125"
																	class="AccHeader :">Account #: <font
																	color="#464646"></font></td>
																<td align="right" width="170" class="AccHeader">Info.
																	as of: <font color="#464646"> </font>
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
								
								
								<%try{
									JSONObject accountsummary=new JSONObject(request.getAttribute("accountsummary").toString());
									
									%>
									<table align="center" border="0" width="1000" cellpadding="0"
										cellspacing="0">
										<tr>
											<td class="container" width="30">
												<div class="headActive" width="30">
													<div class="vertActive" width="30" align="center"
														style="background: #ffe1dc; text-align: center;">
													</div>
												</div>
											</td>
											<td>
												<table align="center" border="0" cellpadding="0"
													cellspacing="0" width="1000px">
													<tbody>
														<tr height="10"></tr>
														<tr height="25">
															<td width="100" class="dataHeader">&nbsp;&nbsp;Total Balanced Amount</td>
															<td width="160" class="dataValue"><%=accountsummary.get("totalbalanceamount") %></td>
															<td width="20"></td>
															<td width="100" class="dataHeader">Number Of Past Due Accounts</td>
															<td width="80" class="dataValue"><%=accountsummary.get("noofpastdueaccounts") %></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">Total Monthly Payment Amounts</td>
															<td width="90" align="right" class="dataAmtValue"><%=accountsummary.get("totalmonthlypaymentamount") %>
															</td>
															<td width="20"></td>
														</tr>
														<tr height="25">
															<td width="100" class="dataHeader">&nbsp;&nbsp;Total Past Due:</td>
															<td width="160" class="dataValue"><%=accountsummary.get("totalpastdue") %></td>
															<td width="20"></td>
															<td width="100" class="dataHeader">Number Of Active Accounts:</td>
															<td width="80" class="dataValue"><%=accountsummary.get("noofactiveaccounts") %></td>
															<td width="20"></td>
															<td width="145" class="dataHeader">Total Written Off Amounts:</td>
															<td width="90" align="right" class="dataAmtValue"><%=accountsummary.get("totalwrittenoffamount") %>
															</td>
															<td width="20"></td>
														</tr>
													</tbody>
												</table>
											</td>
										</tr>
										</tbody>
									</table>
									<%}catch(Exception ec){ec.printStackTrace();} %>
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
				<td height="100px"></td>
			</tr>
			
			<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
			<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%try{
																		JSONObject GeneralUserInfo=new JSONObject(request.getAttribute("GeneralUserInfo").toString());
																	%>
																	<%=GeneralUserInfo.get("fName").toString()+"  "+GeneralUserInfo.get("mName").toString()+" "+GeneralUserInfo.get("lName").toString() %>
																	<%      
																	}catch(Exception ec){ec.printStackTrace();} %>
</title>
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

.shading {
	background-color: #e6e6ff;
	background: #e6e6ff;
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

.dataValuePerform2 {
	border-collapse: separate;
	Color: #464646;
	font-family: segoe ui semibold;
	font-size: 12px;
	font-weight: 280;
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
	font-variant: small-caps;
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

.dataValue {
	font-family: segoe ui semibold;
	font-size: 14px;
	font-weight: 600;
	color: #464646;
	text-align: left;
	padding-left: 7px;
	padding-top: 1px;
	word-wrap: break-word;
}

.dataValue2 {
	font-family: segoe ui semibold;
	font-size: 13px;
	font-weight: 600;
	color: #464646;
	text-align: left;
	padding-left: 7px;
	padding-top: 1px;
	word-wrap: break-word;
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

.dataHeader2 {
	font-family: segoe ui semibold;
	font-size: 13px;
	color: #0f3f6b;
	font-weight: 600;
	text-indent: 5px;
	white-space: nowrap;
	padding-top: 2px;
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
	text-align: center;
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
	text-align: center;
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

.infoValueNote {
	font-family: segoe ui semibold;
	font-size: 11px;
	font-weight: 500;
	color: grey;
	padding-right: 15px;
	font-style: normal;
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
}
</style>
</head>

	
		
		<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
		<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title><%try{
			JSONObject GeneralUserInfo=new JSONObject(request.getAttribute("GeneralUserInfo").toString());
		%>
		<%=GeneralUserInfo.get("fName").toString()+"  "+GeneralUserInfo.get("mName").toString()+" "+GeneralUserInfo.get("lName").toString() %>
		<%      
		}catch(Exception ec){ec.printStackTrace();} %>
		</title>
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
	font-variant: small-caps;
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

.dataValue {
	font-family: segoe ui semibold;
	font-size: 14px;
	font-weight: 600;
	color: #464646;
	text-align: left;
	padding-left: 7px;
	padding-top: 1px;
	word-wrap: break-word;
}

.dataValue2 {
	font-family: segoe ui semibold;
	font-size: 13px;
	font-weight: 600;
	color: #464646;
	text-align: left;
	padding-left: 7px;
	padding-top: 1px;
	word-wrap: break-word;
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

.dataHeader2 {
	font-family: segoe ui semibold;
	font-size: 13px;
	color: #0f3f6b;
	font-weight: 600;
	text-indent: 5px;
	white-space: nowrap;
	padding-top: 2px;
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
	text-align: center;
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
	text-align: center;
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
	transform-origin: 0 30px;
	-moz-transform: rotate(-270deg) translate(1em, 0);
	-moz-transform-origin: 0 30px;
	-webkit-transform: rotate(-270deg) translate(1em, 0);
	-webkit-transform-origin: 0 30px;
	-ms-transform-origin: none;
	-ms-transform: none;
	-ms-writing-mode: tb-rl;
	*writing-mode: tb-rl;
}
</style>
</head>
<body style="font-family: segoe ui semibold, arial, verdana;">
	<table class="box" align="center" border="0px" cellpadding="0"
		cellspacing="0" width="1050">
		<thead>
			<tr>
				<td>
					
				</td>
			</tr>
		</thead>
		<tr>
			<td>
				<table align="center" border="0" cellpadding="0" cellspacing="0">
					<tbody>
						<tr height="10">
							<td align="right" bgcolor="#FFFFFF" class="infoValue"></td>
						</tr>
						<tr height="20">
							<td align="right" bgcolor="#FFFFFF" class="infoValue">Tip:
								All amounts are in INR</td>
						</tr>
						<tr></tr>
						<tr>
							<td>
								<table align="center" bgcolor="#0f3f6b" border="0"
									width="1020px">
									<tbody>
										<tr height="20">
											<td width="10"></td>
											<td class="mainHeader">Summary</td>
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
			<td height="25" align="right" bgcolor="#FFFFFF" class="infoValue">Tip:
				Current Balance, Disbursed Amount & Instalment Amount is considered
				ONLY for ACTIVE account &nbsp;&nbsp;</td>
		</tr>
		<tr>
			<td height="15"></td>
		</tr>
		<tr>
			<td>
				<table align="center" border="0" cellpadding="0" cellspacing="0"
					width="1050">
					<tbody>
						<tr>
							<td>
								<table align="center"
									style="border-collapse: collapse; border: 2px solid #A7CBE3;"
									cellspacing="0" cellpadding="2" width="1000">
									<tbody>
										<tr height="20">
											<td>
												<table align="center" border="0px" cellspacing="0"
													cellpadding="0" width="1000">
													<tbody>
														<tr>
															<td width="center">
																<table align="center" border="0px" cellspacing="0"
																	cellpadding="0" width="1000">
																	<thead>
																		<tr height="25">
																			<td width="150" class="subHeader1" rowspan="2">Monthly Income</td>
																			<td align="center" width="175" scope="colgroup"
																				colspan="2" class="subHeader1">Occupation</td>
																			<td align="center" width="175" scope="colgroup"
																				colspan="3" class="subHeader1">Monthly Expense</td>
																			<td align="right" width="175" scope="colgroup"
																				colspan="2" class="subHeader1">Reported Date</td>
																			<td align="right" width="5"></td>
																		</tr>
																	</thead>
																	<tbody>
																	<%try{ 
																   JSONArray incomedetails=new JSONArray(request.getAttribute("incomedetails").toString());
																	for(int i=0;i<incomedetails.length();i++)
																	{
																	JSONObject incomeDetail=new JSONObject(incomedetails.get(i).toString());	
																	%>
																	    <tr height="8"></tr>
																		<tr height="20">
																		  <td align="left"  style="border:none" class="subHeader1" colspan="2"><%=incomeDetail.get("monthlyincome").toString() %></td>
																		  <td align="left" style="border:none" class="subHeader1" colspan="2"><%=incomeDetail.get("occupation").toString() %></td>
																		  <td align="left" style="border:none" class="subHeader1" colspan="2"><%=incomeDetail.get("monthlyexpense").toString() %></td>
																		  <td align="right" style="border:none" class="subHeader1" colspan="2"><%=incomeDetail.get("reporteddate").toString() %></td>
																		</tr>
																	<%}
																	}catch(Exception ec){ec.printStackTrace();} %>	
																	</tbody>
																</table>
															</td>
														</tr>
														<tr>
															<td height="10"></td>
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
			<td height="30px"></td>
		</tr>
		<tr>
			<td></td>
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
								<table width="1020px" align="center" bgcolor="#0f3f6b"
									border="0">
									<tbody>
										<tr height="20">
											<td width="10"></td>
											<td class="mainHeader">Inquiries (reported for past 24
												months)</td>
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
								<table class="box1" cellpadding="0" cellspacing="0"
									width="1000px" align="center" border="0px">
									<tbody>
										<tr height="20">
											<td height="20" width="200" class="subHeader" align="left">Date</td>
											<td width="200" class="subHeader" align="right">Institution</td>
											<td width="200" class="subHeader" align="left">Time</td>
											<td width="200" class="subHeader" align="right">Request Purpose</td>
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
				<table align="center" border="0" cellpadding="0" cellspacing="0">
					<tbody>
						<tr>
							<td>
								<table width="1000px">
									<tbody>
									   <%try{ 
									   JSONArray enquiries=new JSONArray(request.getAttribute("enquiries").toString());
									   for(int i=0;i<enquiries.length();i++)
									   {
										   JSONObject Enquiry=enquiries.getJSONObject(i);
									   if(i%2!=0)
									   {  
									   %>
									   <tr bgcolor="#F1F3F5">
										<%}else{ %>
										<tr >
										<%}%>
											<td height="20" width="200" class="dataValue1" align="left"><%=Enquiry.get("date")%></td>
											<td width="200" class="dataValue1" align="right"><%=Enquiry.get("institution")%></td>
											<td width="200" class="dataValue1" align="left"><%=Enquiry.get("time")%></td>
											<td width="200" class="dataValue1" align="right"><%=Enquiry.get("requestpurpose")%></td>
										</tr>
									<%}
									}catch(Exception ec){ec.printStackTrace();}%>
									
									
									</tbody>
								</table>
							</td>
						</tr>
						<tr>
							<td height="10"></td>
						</tr>
						
						<tr>
							<td height="5"></td>
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
												<%=request.getAttribute("disclaimer").toString()%>
												</td>
											</tr>
											<tr>
												<td><br></br> <br></br></td>
												<td color="#CCCCCC " align="left" width="300"
													class="disclaimerValue">Copyrights reserved (c) 2016</td>
												<td color="#CCCCCC " align="center" width="400"
													class="disclaimerValue">Equifax
													Information Services Pvt. Ltd</td>
												<td color="#CCCCCC " align="right" width="300"
													class="disclaimerValue">Company Confidential Data</td>
												<td width="70"><br></br> <br></br></td>
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