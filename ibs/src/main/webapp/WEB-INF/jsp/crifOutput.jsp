
<%if(request.getAttribute("htmlformat")==null){ %>
<html>
<body>
<h1>There is Technical Issue in Crif Service</h1>
<h2><%=request.getAttribute("Error-description")%></h2>
<form action="crifResponse">
      <input type="submit" value="Back">
</form>
</body>
</html>
<%}else{%>
<%=request.getAttribute("htmlformat") %>
<html>
<body>
</body>
</html>
<%}%>
