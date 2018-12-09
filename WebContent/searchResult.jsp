<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Result</title>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.library.bean.BookBean" %>
<%@ page import="com.library.dao.LibraryDAO" %>
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
	<div class="row">
		<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12 " >
			<h4><%= bundle.getString("SearchResult")%></h4>
		</div>
	</div>
	<br><br>
	<div class="row"> 
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-4 " ></div>
		<div class="col-xs-8 col-md-8 col-sm-8 col-lg-4 " >
		<table  border="1">
<%			List<BookBean> book_history =(ArrayList<BookBean>)request.getAttribute("search");
			for(BookBean bean:book_history)
			{
%>				<tr>
					<td >
						<img alt="image" height="100px" width="100px" src="<%=request.getContextPath()%>/PictureDisplay?book_name=<%= bean.getBookName() %>">
					</td >
					<td align="left">
						<a href="<%=request.getContextPath()%>/BookDetails?book_name=<%=bean.getBookName()%>" style="text-decoration:none">
							<%= bean.getBookName() %>
						</a>
					</td>
				</tr>	
<%			}
%>		
		</table>
		</div>
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-4 " ></div>
	</div>
</div>

<br>


<%
	int page_number=(int)request.getAttribute("page_number");
	String book_name=(String)(session.getAttribute("search_string"));

	for (int i=1;i<=page_number;i++)
	{
		
%>

		<a href="<%=request.getContextPath()%>/SearchProcess?page=<%= i%>&call=0"><%= i%></a>
<%	}

%>



</center>

</body>
</html>