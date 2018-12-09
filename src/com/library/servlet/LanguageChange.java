package com.library.servlet;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LanguageChange
 */
@WebServlet("/LanguageChange")
public class LanguageChange extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();	    

		ResourceBundle bundle=null;

		String lang=request.getParameter("language");
		
		if(lang.equals("es")) 
		{
	    	 Locale locale = new Locale("es","ES");
	    	 bundle=ResourceBundle.getBundle("TestBundle",locale);
	    	 session.setAttribute("locale",locale); 
	 		session.setAttribute("bundle",bundle); 
	 		
	    } 
		else if(lang.equals("en")) 
		{
	    	 Locale locale = new Locale("en","US");
	    	 bundle=ResourceBundle.getBundle("TestBundle",locale);
	    	 session.setAttribute("locale",locale); 
	 		 session.setAttribute("bundle",bundle); 
	 		
	    } 
		session.setAttribute("change", "yes");
		String page=request.getParameter("page");
		if (page.equals("login"))
		{
			getServletConfig().getServletContext().getRequestDispatcher("/login.jsp").forward(request,response);

		}
		else
		{
			getServletConfig().getServletContext().getRequestDispatcher("/LibraryHome.jsp").forward(request,response);
		}

	}


}


