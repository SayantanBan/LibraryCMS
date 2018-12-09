package com.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet 
{

	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)   throws ServletException, IOException 
	{  
		response.setContentType("text/html");  
		PrintWriter out=response.getWriter();  

		request.getRequestDispatcher("login.jsp").include(request, response);  
		
		HttpSession session=request.getSession(false);  
		session.setAttribute("currentUserName","");
		session.setAttribute("currentRole","");
		session.setAttribute("search_string","");
		session.setAttribute("searchBooks","");
		session.setAttribute("div", "");
		session.setAttribute("locale", "");
		session.setAttribute("bundle", "");
		
		session.invalidate();
		
		out.print("<br><center><h4><span style='background-color:white'><font color='red'>You are successfully logged out!</font><span></h4></center>");  
		
		out.close();  
	}  
}
