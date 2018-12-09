<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%@ page import="com.library.bean.MemberBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="LibraryHome.jsp" %>

</head>
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
<div class="container">
	<div class="row">
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " ></div>
		<div class="col-xs-8 col-md-8 col-sm-8 col-lg-8 " >
			<table>
				<tr>
					<th>User Id</th>
					<th>Username</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Phone Number</th>
				</tr>
					
		</div>
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " ></div>
	
	</div>	<br>
	<div class="row">
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " ></div>
		<div class="col-xs-8 col-md-8 col-sm-8 col-lg-8 " >
		 
	
<%
	List<MemberBean> users =(ArrayList<MemberBean>)request.getAttribute("users");
	
	for(MemberBean bean:users)
	{
%>		<tr>
			<td><%= bean.getUserId() %></td>
			<td><%= bean.getUserName() %></td>
			<td><%= bean.getFirstName() %></td>
			<td><%= bean.getLastName() %></td>
			<td><%= bean.getPhoneNo() %></td>
		</tr>	
		
<%	}%>
</table>			
		</div>
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " ></div>
		
</div>

</div>
</center>

</body>
</html>