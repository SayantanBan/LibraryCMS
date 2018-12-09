<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%@ page import="com.library.bean.MemberBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Locale" %>
<%@ page import="java.util.ResourceBundle" %>
<title>Home Page</title>
<style>
.dropbtn ,input[type='submit']
{
    padding: 10px;
    border: none;
    background-color:#F5F5F5;
}

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #f1f1f1;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
}

.dropdown-content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
}

.dropdown-content a:hover {background-color: #0D47A1}

.dropdown:hover .dropdown-content {
    display: block;
}

.dropdown:hover .dropbtn {
    background-color: #0D47A1;
}


</style>


<script>
$(document).ready(function()
{
	$("#book_name").on("input", function(e)
	{
		var val = $(this).val();
		if(val == "") 
			return;
		console.log(val);
		$.get("artservice.cfc?method=getart&returnformat=json", {term:val}, function(res) 
		{
			var dataList = $("#searchresults");
			dataList.empty();
			if(res.DATA.length)
			{
				for(var i=0, len=res.DATA.length; i<len; i++)
				{
					var opt = $("<option></option>").attr("value", res.DATA[i][0]);
					dataList.append(opt);
				}
			}
		},"json");
	});
})
</script>	
<% 	response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
 %>
 

</head>


<body>
<%
if (session.getAttribute("change")==null)
{
	Locale  locale =Locale.getDefault();
	ResourceBundle bundle =ResourceBundle.getBundle("TestBundle");

	session.setAttribute("locale",locale); 
	 session.setAttribute("bundle",bundle); 
}

Locale locale =(Locale)session.getAttribute("locale");
ResourceBundle bundle =(ResourceBundle)session.getAttribute("bundle");
%>
<%
	String currentUserName = (String)(session.getAttribute("currentUserName"));
	String currentRole = (String)(session.getAttribute("currentRole"));
	if (currentRole==null )
	{	
		response.sendRedirect("login.jsp");
	}
	else
	{	
%>



<div align="right">
	<form method="get" action="LanguageChange">
		<select id="language" name="language" onchange="submit()">
					<option  >Change Language</option>
		
			<option value="en"  >English</option>
			<option value="es" >Español</option>
		</select>
		<input type="hidden" name="page" value="home" />
	</form>
</div>




<div class="container" >
	<div class="row" style="background:#0D47A1;height:70px;">
		<div class="col-xs-3 col-md-3 col-sm-3 col-lg-3 " >
			<img src="<%=request.getContextPath()%>/test/logo.jpg" alt="image" width="70%" height="70%">
		</div>
		<div class="col-xs-7 col-md-7 col-sm-7 col-lg-7 " >
			<center><h1 style="color:white"><%= bundle.getString("Library")%></h1></center>
		</div>
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " >
			
		</div>
	</div>
</div>


<div class="container">
	
	<div class="row">
		<div class="col-xs-4 col-md-6 col-sm-6 col-lg-6 " align="left">
<%			if (currentUserName==null)
{%>				<h4> <%= bundle.getString("Welcome")%>  <%= bundle.getString("Guest")%></h4>
<%}			else				
{%>				<h4> <%= bundle.getString("Welcome")%> <%= currentUserName + " " +"("+ currentRole+")" %></h4>
<%}%>		</div>
		<div class="col-xs-8 col-md-6 col-sm-6 col-lg-6 " align="right">
			<form  method="get" action="SearchProcess" >
				<input type="hidden" name="page" value="1" />
				<input type="hidden" name="call" value="1" />
				<input type="text" name="book_name" id="book_name" placeholder="<%= bundle.getString("SearchBook")%>" list="searchresults" autocomplete="off">
				<datalist id="searchresults" >
<%					List<String> searchBooks =(ArrayList<String>)session.getAttribute("searchBooks");
					if (searchBooks!=null)
					{	
						for (int i=0;i<searchBooks.size();i++)
						{ 
%>							<option value='<%=searchBooks.get(i)%>' onclick="submit()"></option>
<%						}
					}
%>				</datalist>
				<input  type="submit" value="<%= bundle.getString("Search")%>">
			</form>
		</div>
	</div>
<br>
	
	
<%	if (currentRole.equals("Librarian"))
	{
%>		<div class="row" style="background-color:#F5F5F5; font-size:100%"  >
			<div class="col-xs-3 col-md-3 col-sm-3 col-lg-3 " >
				<form method="get" action="DirectHome">
					<input name="home" type="submit" value="<%= bundle.getString("HomePage")%>"> 
				</form> 
			</div>
			<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " >
				<div class="dropdown">
	  				<button class="dropbtn"><%= bundle.getString("Books")%></button>
	 					 <div class="dropdown-content">
	 					 	<a href="<%=request.getContextPath()%>/AddBookForm" ><%= bundle.getString("AddBook")%></a>
							<a href="<%=request.getContextPath()%>/CatalogueProcess?page=1"><%= bundle.getString("BookCatalogue")%></a><br>
							<a href="<%=request.getContextPath()%>/ShowIssued"><%= bundle.getString("IssuedBooks")%></a><br>
							<a href="<%=request.getContextPath()%>/TreeViewProcess"><%= bundle.getString("TreeView")%></a><br>
						 </div>
				</div>
			</div>
			<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " >
				<div class="dropdown">
	 				 <form method="get" action="<%=request.getContextPath()%>/ShowUsers"><input name="request" type="submit" value="<%= bundle.getString("Users")%>"></form>					 
				</div>
			</div>
			<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " >
				<form method="get"  action="<%=request.getContextPath()%>/RequestProcess"><input name="request" type="submit" value="<%= bundle.getString("Requests")%>" ></form>
			</div>
			<div class="col-xs-3 col-md-3 col-sm-3 col-lg-3 " >
				<form method="get" action="Logout"><input name="logout" type="submit" value="<%= bundle.getString("Logout")%>"> </form> 
			</div>
		</div>	
<%	
}
	else if (currentRole.equals("User"))
	{
%>			<div class="row" style="background-color:#F5F5F5; font-size:120%">
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " >
			<a href="<%=request.getContextPath()%>/DirectHome"><%= bundle.getString("HomePage")%></a>
		</div>
		<div class="col-xs-3 col-md-3 col-sm-3 col-lg-3 " >
			<a href="<%=request.getContextPath()%>/CatalogueProcess?page=1"><%= bundle.getString("BookCatalogue")%></a>
		</div>	
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " >
			<a href="<%=request.getContextPath()%>/ShowHistory"><%= bundle.getString("ShowHistory")%></a>
		</div>
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " >
			<a href="<%=request.getContextPath()%>/TreeViewProcess"><%= bundle.getString("TreeView")%></a><br>
		</div>
		<div class="col-xs-2 col-md-2 col-sm-2 col-lg-2 " >
			<a href="<%=request.getContextPath()%>/Logout"><%= bundle.getString("Logout")%></a>
		</div>
	</div>
<%	}
	else
	{		
%>		<div class="row" style="background-color:#F5F5F5; height:30px; font-size:120%" align="center">
	
		<div class="col-xs-3 col-md-3 col-sm-3 col-lg-3 " >
			<a href="<%=request.getContextPath()%>/DirectHome"><%= bundle.getString("HomePage")%></a>
		</div>
		<div class="col-xs-3 col-md-3 col-sm-3 col-lg-3 " >
			<a href="<%=request.getContextPath()%>/CatalogueProcess?page=1"><%= bundle.getString("BookCatalogue")%></a>
		</div>
		<div class="col-xs-3 col-md-3 col-sm-3 col-lg-3 " >
			<a href="<%=request.getContextPath()%>/TreeViewProcess"><%= bundle.getString("TreeView")%></a>
		</div>
		<div class="col-xs-3 col-md-3 col-sm-3 col-lg-3 " >
			<a href="<%=request.getContextPath()%>/Logout"><%= bundle.getString("Logout")%></a>
		</div>
			
	</div>
<%	}
%>
</div>
<%	}
%>


<%
	String slide=(String)(session.getAttribute("div"));
	
	if (slide.equals("show"))
	{
%>
<div id="slide">
<div class="container" style="height:50%;" >
	<div id="myCarousel" class="carousel slide" data-ride="carousel"  >
  		<div class="carousel-inner" style="height:500px;" align="center">
	      	<div class="item active">
	        	<img src="<%=request.getContextPath()%>/test/1001.jpg" alt="image" style="width:500px;height:500px;">
	     	</div>
			<div class="item">
	        	<img  src="<%=request.getContextPath()%>/test/1002.jpg" alt="image" style="width:500px;height:500px;">
	      	</div>
		    <div class="item">
	        	<img src="<%=request.getContextPath()%>/test/1003.jpg" alt="image" style="width:500px;height:500px;">
		    </div>
		    <div class="item">
	        	<img src="<%=request.getContextPath()%>/test/1004.jpg" alt="image" style="width:500px;height:500px;">
		    </div>
		    <div class="item">
	        	<img src="<%=request.getContextPath()%>/test/1005.jpg" alt="image" style="width:500px;height:500px;">
		    </div>
		</div>
	    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    		<span class="glyphicon glyphicon-chevron-left"></span>
		    <span class="sr-only">Previous</span>
		</a>
		<a class="right carousel-control" href="#myCarousel" data-slide="next">
		    <span class="glyphicon glyphicon-chevron-right"></span>
      		<span class="sr-only">Next</span>
   		</a>
  	</div>
</div>
</div>
<%} %>
</body>


</html>