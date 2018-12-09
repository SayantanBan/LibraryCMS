<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Catalogue</title>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.library.bean.BookBean" %>
<%@ page import="com.library.dao.LibraryDAO" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.io.OutputStream" %>
<%@ include file="LibraryHome.jsp" %>

</head>
<body>

<br>
<div class="container" >
	<div class="row">
		<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12 ">
			<center><h2><%= bundle.getString("BookCatalogue")%></h2></center>
		</div>
	</div>
</div>	
<br>

<div class="container" style=" border: 2px solid grey;border-radius: 8px;border-spacing: 7px;">
	<div class="row"><br>
		<div class="col-xs-1 col-md-1 col-sm-1 col-lg-1 "></div>
<%		List<String> books =(ArrayList<String>)request.getAttribute("catalogue");
		for(String bean:books)
		{
%>		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 ">
			<center>
				<a href="<%=request.getContextPath()%>/BookDetails?book_name=<%=bean%>">
					<img  alt="image" height="200px" width="150px" src="<%=request.getContextPath()%>/PictureDisplay?book_name=<%=bean%>">
				</a>
			</center>			
		</div>
<%		}
%>		<div class="col-xs-1 col-md-1 col-sm-1 col-lg-1 "></div>
	</div>
	<br>
	<div class="row">
		<div class="col-xs-1 col-md-1 col-sm-1 col-lg-1 "></div>
<%		for(String bean:books)
		{
%>			<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " style="text-align: center;">
				<%=bean%>			
			</div>
<%		}
%>		<div class="col-xs-1 col-md-1 col-sm-1 col-lg-1 "></div>
 	</div>		
<br>
	<div class="row">
		<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12 "><center>
<%
		int page_number=(int)request.getAttribute("pages");
		for (int i=1;i<=page_number;i++)
		{
%>
		<a href="<%=request.getContextPath()%>/CatalogueProcess?page=<%= i%>"><%= i%></a>
<%		}

%>

		</center></div>
	</div><br>
</div>		
	







</body>
</html>