<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Book</title>
<%@ include file="LibraryHome.jsp" %>
<%@ page import="com.library.bean.BookBean" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
 <script type="text/javascript" src=" https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>


<style>
 td {
    padding: 10px;
    }
</style>

</head>
<script type="text/javascript">
var counter = 2;

function AddAuthor()
{	
	var id=counter-1;
	var author=document.getElementById(id).value;

	if (author=="")
	{
		alert("Enter an author name");
		return false;
	}

		$("#row").append('<br><input type="text" id='+counter+' name="author'+counter+'" /><br>');
		counter++;

   
} 
</script>


<script type="text/javascript">

function validate()
{
	
	
	var name=document.getElementById("name").value;
	if (name=="")
	{
		alert("Enter a name");
		return false;
	}
	
	
	var author=document.getElementById("author1").value;

	if (author=="")
	{
		alert("Enter an author name");
		return false;
	}
	
	var publisher=document.getElementById("publisher").value;
	if (publisher=="")
	{
		alert("Enter an publisher name");
		return false;
	}
	
	var category=document.getElementById("categorylist").value;
	if (category=="")
	{
		alert("Enter an category name");
		return false;
	}
	
	var cover= document.getElementById("cover").value;
	if (cover=="")
	{
		alert("Enter a cover photo");
		return false;
	}
	
	var quantity= document.getElementById("quantity").value;
	if (quantity=="")
	{
		alert("Enter a number in Quantity");
		return false;
	}
	else if (isNaN(quantity))
	{
		alert("Enter a number in Quantity");
		return false;
	}
	
}


</script>

<body >

<br><br>
<center>
<div class="container"  style=" border: 2px solid grey;border-radius: 8px;border-spacing: 7px;">
	<div class="row">
		<div class="col-xs-12 col-md-12 col-sm-12 col-lg-12 " >
			<center></center><h4><%= bundle.getString("EnterBookDetails")%></h4>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-1 col-md-1 col-sm-1 col-lg-1 " ></div>
		<div class="col-xs-10 col-md-10 col-sm-10 col-lg-10 " >
			<form  method="post" action="AddBookProcess" enctype="multipart/form-data" onsubmit="return validate();" >
				<table>
					<tr>
						<td><%= bundle.getString("Name")%>:</td>
						<td><input id="name" type="text" name="book_name"></td>
					</tr>
					<tr>
						<td><%= bundle.getString("Author")%>:</td>
						<td id="row">
							<input type="text" id="1" name="author1">	
                            <input type="button" id="add" value="Add Author" onclick="AddAuthor();" />
							<br>
						</td>
					</tr>
					<tr>
						<td><%= bundle.getString("Publisher")%>:</td>
						<td><input type="text" id="publisher" name="publisher"></td>
					</tr>
					<tr>
						<td><%= bundle.getString("Category")%>:</td>
						<td><input list="category" id="categorylist" name="category">
								<datalist id="category">
<%									List<String> category =(ArrayList<String>)request.getAttribute("category");
									if (category!=null)
									{	
										for (int i=0;i<category.size();i++)
										{ 
%>											<option value='<%=category.get(i)%>'></option>
<%										}
									}
%>								</datalist>	
						</td>
					</tr>
					<tr>
						<td><%= bundle.getString("CoverPhoto")%>:</td>
						<td><input type="file" name="cover" id="cover" accept="image/*"></td>
					</tr>
					<tr>
						<td><%= bundle.getString("Quantity")%>:</td>
						<td><input type="text" id="quantity" name="quantity" ></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="<%= bundle.getString("Add")%>" ></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="col-xs-1 col-md-1 col-sm-1 col-lg-1 " ></div>
	</div>	
</div>	


</center>	
</body>
</html>