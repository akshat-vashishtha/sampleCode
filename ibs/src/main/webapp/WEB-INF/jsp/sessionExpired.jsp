<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Session Expired</title>
</head>
<body>
<%
ServletContext context = (ServletContext)session.getServletContext();
session.invalidate();
context.removeAttribute("userIdMap");
%>
	<table align="center">
		<tr>
			<td align="center">
				<B><font color="red">Your Session Has Expired. Please Re-Login.</font></B>
			</td>
		</tr>
		<tr>
			<td align="center"><a href="login.jspx">Login</a></td>
		</tr>
	</table>	
</body>
</html>