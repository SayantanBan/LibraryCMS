<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>History</title>
<%@ page import="com.library.bean.ShowHistoryBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="LibraryHome.jsp" %>
<style>

td,th {
  text-align: center;
  vertical-align: middle;
      padding: 10px;
  
}
</style>
</head>
<body>
<br>
<center>
			
<div class="container">
	<br>
	<div class="row">
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " ></div>
		<div class="col-xs-8 col-md-8 col-sm-8 col-lg-8 " >
			<table>
				<tr>
					<th>Book Name</th>
					<th>Issue Date</th>
					<th>Return Date</th>
					<th>Status</th>
					
				</tr>	
<%
	List<ShowHistoryBean> book_history =(ArrayList<ShowHistoryBean>)request.getAttribute("history");
	
	for(ShowHistoryBean bean:book_history)
	{
		if (bean.getIssueStatus().equals("Pending") || bean.getIssueStatus().equals("Rejected"))
		{
%>			<tr>
				<td><%= bean.getBookName() %></td>
				<td>-</td>
				<td>-</td>
				<td><%= bean.getIssueStatus()%></td>
			</tr>	
<%		}
		else
		{
%>		<tr>
			<td><%= bean.getBookName() %></td>
			<td><%= bean.getIssueDate() %></td>
<%				if ( bean.getReturnDate()==null)
				{
%>					<td>Not Returned</td>
					<td>Issued</td>
<%				}
				else
				{
%>					<td><%= bean.getReturnDate() %></td>
					<td>Returned</td>
<%				}
%>		
		</tr>
		
<%		}
	}
%>
</table>
</div>
<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " ></div>
</div>
</div>
</center>

</body>
</html>