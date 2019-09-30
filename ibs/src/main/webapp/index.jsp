<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	</head><%-- action="${pageContext.request.contextPath}/ib/ibs/api/getRequest"  --%>
	<body>
	 <form action="${pageContext.request.contextPath}/ib/ibs/api/holdRequest" id="usrform" method="POST"	  enctype="multipart/form-data">
   <textarea name="request" id="tId" placeholder="enter your json here"  rows="15" cols="70"></textarea>
    <input type="submit" value="Submit Details">
</form>
		<%--  <jsp:forward page="welcome.jsp"></jsp:forward>  --%>
	</body>
</html>