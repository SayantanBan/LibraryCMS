<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Tree View</title>

<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.library.bean.BookBean" %>
<%@ include file="LibraryHome.jsp" %>

</head>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

<script>
$(function()
	{  
	  $('#tree-view').jstree();
	});
	
	
</script>
<script>


$(document).ready(function() {
    $("#tree-view").jstree({ "plugins" : ["themes","html_data","ui"] });
    $("#tree-view li").on("click", "a", 
        function() {
            document.location.href = this;
        }
    );
});
</script>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/themes/default/style.min.css" />
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jstree/3.2.1/jstree.min.js"></script>

<body>
<br><br>
<div class="container" >
	<div class="row">
		<div class="col-xs-4 col-md-4 col-sm-4 col-lg-4 " ></div>
		<div class="col-xs-4 col-md-4 col-sm-4 col-lg-4 " >
		
<%
		List<BookBean> book_history =(ArrayList<BookBean>)request.getAttribute("treeview");

%>
		<div id="tree-view" >
		<ul>
			<li> 
				<span>Book Categories</span>
					<ul>
		<%				for(BookBean bean:book_history)
						{
		%>   				 <li>
					    		<span> <%=bean.getCategory()%> </span>
		<%
								String[] book_name =(String[])bean.getBookNames();
								
		%>      					<ul id="book_names">
		<%  							for (String book: book_name)
			 							{
		%>									<li class="name">
		      									<a href="<%=request.getContextPath()%>/BookDetails?book_name=<%=book%>">
		      									<%=book%>
		      									</a>	
		        							</li>
		<%								}
		%>      
		       						 </ul>
		   					 </li>
		<%				}
		%>			</ul>
			</li>
		</ul>			
		</div>
	
		</div>
		<div class="col-xs-4 col-md-4 col-sm-4 col-lg-4 " ></div>
	</div>
</div>		

</body>
</html>