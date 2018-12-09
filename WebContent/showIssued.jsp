<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ page import="com.library.bean.IssueBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="LibraryHome.jsp" %>

<style>

td,th {
  text-align: center;
  vertical-align: middle;
      padding: 20px;
  
}
</style>
</head>
<body>
<br>
<center>

<div class="container">
	<br>
	<div class="row">
		<div class="col-xs-1 col-md-1 col-sm-1 col-lg-1 " ></div>
		<div class="col-xs-10 col-md-10 col-sm-10 col-lg-10 " >
			<table>
				<tr>
					<th>User Id</th>
					<th>Book Id</th>
					<th>Issue Date</th>
					<th>Return Date</th>
					<th></th>
				</tr>	
<%
@SuppressWarnings("unchecked")
List<IssueBean> issued_book =(ArrayList<IssueBean>)request.getAttribute("issued");
	
	for(IssueBean bean:issued_book)
	{
%>
		<tr>
			<td><%= bean.getUserId() %></td>
			<td><%= bean.getBookId() %></td>
			<td><%= bean.getIssueDate() %></td>
			
		
<%			if ( bean.getReturnDate()==null)
			{
	
%>				<td>Not Returned</td>
				<td><a href="<%=request.getContextPath()%>/ReturnProcess?user_id=<%= bean.getUserId() %>&book_id=<%= bean.getBookId() %>" style="text-decoration: none; ">Mark Returned</a></td>
<%			}
			else
			{
%>				<td><%= bean.getReturnDate() %></td>
				<td></td>				
<%			}%>
		</tr>
<%		
	}
	
%>
</table>	
</div>
<div class="col-xs-1 col-md-1 col-sm-1 col-lg-1 " ></div>
</div>
</div>
</center>
		
</body>
</html>


