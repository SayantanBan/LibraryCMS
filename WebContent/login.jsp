<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.* "%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<title>Login Page</title>
<style type="text/css">

	td 
	{ 
   		 padding: 5px;
	}
	body
	{
	    background-image: url(<%=request.getContextPath()%>/test/frontpic.jpg);
  		background-repeat: no-repeat;
    	background-size: cover;
    	height: 100%; 
	}
	input[type=text],input[type=password]
	{
		 width: 100%;
		 height:30px;
	    padding: 12px;
	    border: 1px solid #ccc;
	    border-radius: 4px;
	    resize: vertical;
		
	}
	
</style>


</head>

<script type="text/javascript">

	function rvalidate()
	{
		var rfirst_name=document.getElementById("rfirst_name").value;
		if (rfirst_name=="")
		{
			alert("Please enter first name");
			return false;
		}
		var rlast_name=document.getElementById("rlast_name").value;
		if (rlast_name=="")
		{
			alert("Please enter last name");
			return false;
		}
		var remail=document.getElementById("remail").value;
		if (!/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(remail))
		{
			alert("Please enter valid email");
			return false;
		}
		var rusername=document.getElementById("ruser_name").value;
		if (rusername=="")
		{
			alert("Please enter a username");
			return false;
		}
		var rphone_no=document.getElementById("rphone_no").value;
		if (rphone_no=="")
		{
			alert("Please enter a phone number");
			return false;
		}
		if (isNaN(rphone_no))
		{
			alert("Please enter a valid phone number");
			return false;
		}
		var rpassword=document.getElementById("rpassword").value;
		if (rpassword=="")
		{
			alert("Please Enter password");
			return false;
		}
		var rcpassword=document.getElementById("rcpassword").value;
		if (rcpassword=="")
		{
			alert("Please confirm password");
			return false;
		}
		if (rpassword!=rcpassword)
		{
			alert("Password does not match!!");
			return false;

		}	
	}
</script>	
	<script type="text/javascript">
	
	function lvalidate()
	{	
		var lusername=document.getElementById("luser_name").value;
		if (lusername=="")
		{
			alert("Please enter a username");
			return false;
		}
		
		var lpassword=document.getElementById("lpassword").value;
		if(lpassword=="")
		{
			alert("Please Enter password");
			return false;
		}

	}

	
</script>	
	<script type="text/javascript">
	
	function lang()
	{
		
		var lang=request.getParameterById("language");
		if(lang.equals("es")) 
		{
	    	 Locale locale = new Locale("es","ES");
	    	 ResourceBundle bundle=ResourceBundle.getBundle("TestBundle",locale);
	    	 session.setAttribute("locale",locale); 
	 		session.setAttribute("bundle",bundle); 
	 		
	    } 
		else if(lang.equals("en")) 
		{
	    	 Locale locale = new Locale("en","US");
	    	 ResourceBundle  bundle=ResourceBundle.getBundle("TestBundle",locale);
	    	 session.setAttribute("locale",locale); 
	 		 session.setAttribute("bundle",bundle); 

	    } 
			
	
	}
</script>	

<body >
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
<div align="right">
	<form method="get" action="LanguageChange">
		<select id="language" name="language" onchange="submit()">
			<option  >Change Language</option>
			<option value="en"  >English</option>
			<option value="es" >Español</option>
		</select>
		<input type="hidden" name="page" value="login" />
	</form>
</div>

<div class="container" >
	<div class="row" style="background:#0D47A1;height:70px;">
		<div class="col-xs-3 col-md-3 col-sm-3 col-lg-3 " >
			<img src="<%=request.getContextPath()%>/test/logo.jpg" alt="image" width="70%" height="70%">
		</div>
		<div class="col-xs-5 col-md-6 col-sm-6 col-lg-6 " >
			<center><h1 style="color:white" id="heading" ><%= bundle.getString("Library")%></h1></center>
		</div>
		<div class="col-xs-4 col-md-3 col-sm-3 col-lg-3 " align="right" >
		<br>
			<form method="post" action="LoginProcess">
				<input type="hidden" name="user" value="guest" />
				<input type="submit" value="<%= bundle.getString("Loginasaguest")%>" style="color:white;background-color:#0D47A1;border:#0D47A1;">
					
			</form>	
			
		</div>
	</div>
</div>
<br>

<br>
<div class="container" >
	<div class="row">
		<div class="col-xs-5 col-md-4 col-sm-5 col-lg-4  "align="right" style="background-color: rgba(0,0,0,0.5);">
			<center><h3><font color="white"><%= bundle.getString("Login")%></font></h3></center>
			<form method="post" action="LoginProcess" onsubmit="return lvalidate();">
				<table>
					<tr>
						<td><b><font color="white"><%= bundle.getString("Username")%></font></b></td>
						<td><input type="text" id="luser_name" name="user_name"></td>
					</tr>
					<tr>
						<td><b><font color="white"><%= bundle.getString("Password")%></font></b></td>
						<td><input type="password" id="lpassword" name="password"></td>
					</tr>	
					<tr>
						<td><input type="hidden" id ="user" name="user" value="lu" /></td>
						<td><input type="submit" value="<%= bundle.getString("Login")%>" ></td>
					</tr>
				</table>
			</form>
		</div>
		<div class="col-xs-1 col-md-2 col-sm-1 col-lg-2 "></div>
		
		<div class="col-xs-6 col-md-6 col-sm-6 col-lg-6  "align="center" style=" background-color: rgba(0,0,0,0.5);">
			<center><h3><font color="white"><%= bundle.getString("RegisterNow")%></font></h3></center>
			
			<form method="post" action="RegisterProcess" onsubmit="return rvalidate();">
				<table>
					<tr>
						<td><b><font color="white"><%= bundle.getString("FirstName")%></font></b></td>
						<td><input type="text" id="rfirst_name" name="first_name"></td>
					</tr>
					<tr>
						<td><b><font color="white"><%= bundle.getString("LastName")%></font></b></td>
						<td><input type="text" id="rlast_name" name="last_name"></td>
					</tr>
					
					<tr>
						<td><b><font color="white"><%= bundle.getString("Username")%></font></b></td>
						<td><input type="text" id="ruser_name" name="user_name"></td>
					</tr>
					<tr>
						<td><b><font color="white" ><%= bundle.getString ("EmailId") %></font></b></td>
						<td><input type="text" id="remail" name="remail"> </td>
					</tr>
					<tr>
						<td><b><font color="white"><%= bundle.getString("PhoneNumber")%> </font></b></td>
						<td><input type="text" id="rphone_no" name="phone_no"></td>
					</tr>
					<tr>
						<td><b><font color="white"><%= bundle.getString("Password")%></font></b></td>
						<td><input type="password" id="rpassword" name="password"></td>
					</tr>
					<tr>
						<td><b><font color="white"><%= bundle.getString("ConfirmPassword")%></font></b></td>
					
						<td><input type="password" id="rcpassword" name="cpassword">
											  <span id='message'></span>
					
						</td>
					</tr>
					
					<tr>
						<td></td>
						<td><input type="submit" value="<%= bundle.getString("Register")%>" >
						</td>
					</tr>
				</table>
			</form>
		</div>
			
	</div>	
<br><br><br><br>
<script type="text/javascript">

$('#rpassword, #rcpassword').on('keyup', function () {
	  if ($('#rpassword').val() == $('#rcpassword').val()) {
	    $('#rcpassword').css('background-color', 'lightgreen');
	  } else 
	    $('#rcpassword').css('background-color', '#FF5733');
	});
	
</script>		
</div>

</body>
</html>