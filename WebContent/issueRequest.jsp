<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Requests</title>
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

<div class="container">
	<div class="row">
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " ></div>
		<div class="col-xs-8 col-md-8 col-sm-8 col-lg-8 " >
			<table>
				<tr>
					<th>User Id</th>
					<th>Book Id</th>
					<th></th>
					<th></th>
				</tr>	
		</div>	
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " ></div>
	</div>		
	
<%		List<IssueBean> book_request =(ArrayList<IssueBean>)request.getAttribute("request");
		for(IssueBean bean:book_request)
		{
%>			
			<div class="row">
				<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " ></div>
				<div class="col-xs-8 col-md-8 col-sm-8 col-lg-8 " >
						<tr>
							<td><%= bean.getUserId() %></td>
							<td><%= bean.getBookId() %></td>
							<td><a href="<%=request.getContextPath()%>/IssueProcess?book_id=<%=bean.getBookId()%>&user_id=<%=bean.getUserId()%>">Issue</a></td>
							<td><a href="<%=request.getContextPath()%>/RejectProcess?book_id=<%=bean.getBookId()%>&user_id=<%=bean.getUserId()%>">Reject</a></td>
						</tr>
				</div>
				<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " ></div>
			</div>
<%		}
%>		
			</table>
	
</div>	
</div>
</body>
</html>