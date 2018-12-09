<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script><meta charset="ISO-8859-1">

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ page import="com.library.bean.BookBean" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.io.OutputStream" %>
<%@ page import="com.library.bean.MemberBean" %>
<%@ include file="LibraryHome.jsp" %>

</head>
<%
	BookBean book=(BookBean)request.getAttribute("book");
%>	

<style>

td,th {
  text-align: center;
  vertical-align: middle;
      padding: 10px;
  
}
</style>


<body>

	

<br><br>
<center>

<div class="container" style="border-padding:10px;border: 2px solid grey;border-radius: 8px;border-spacing: 10px;">
	<br><div class="row">
		<div class="col-xs-6 col-md-6 col-sm-6 col-lg-6 ">
				<img alt="image" height="300px" width="200px" src="<%=request.getContextPath()%>/PictureDisplay?book_name=<%= book.getBookName() %>">
		</div>
		<div class="col-xs-6 col-md-6 col-sm-6 col-lg-6 ">
			<div class="row">
				<table>
					<tr>
						<td colspan="2"> <center><h3><%= book.getBookName()%></h3></center></td>
					</tr>
				
<%				if (currentRole.equals("Librarian"))
				{
%>					<tr>
						<td><b>Book Id:</b></td>
						<td><%= book.getBookId() %></td>
					</tr>	
		
<%				}%>
					<tr>
						<td><b>Author:</b></td>
						<td>
<%						List<String> authors=book.getAuthor();
						int len=authors.size();
						int j=0;
						while (j<len)
						{
%>							<%=authors.get(j)%>
<%							j++;
							if(j<len)
%>								,
<%						} 
%>					</td></tr>	
					<tr>
						<td><b>Publisher:</b></td>
						<td><%= book.getPublisher() %></td>
					</tr>
					<tr>
						<td><b>Category:</b></td>
						<td><%= book.getCategory() %></td>
					</tr>
<%			if (currentRole.equals("Librarian"))
				{
%>					<tr>
						<td><b>Available Quantity:</b></td>
						<td><%= book.getQuantity() %></td>
					</tr>	
<%				}
%>
<%			if (currentRole.equals("User"))
				{
%>					<tr>
						<td colspan="2"><center><a href="<%=request.getContextPath()%>/IssueRequest?book_id=<%= book.getBookId()%>" style="text-decoration: none;">ISSUE</a></center></td>
					</tr>
					
		
<%				}
%>			

				</table>
			</div>
		</div>
	</div>
	<br>
</div>			


</center>
</body>
</html>