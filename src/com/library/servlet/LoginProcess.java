package com.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.dao.LibraryDAO;

/**
 * Servlet implementation class loginProcess
 */
@WebServlet("/LoginProcess")

public class LoginProcess extends HttpServlet
{

	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();	    
		LibraryDAO dao=new LibraryDAO();
		 
		String user= request.getParameter("user");

		try
		{
			if (user.equals("guest"))
			{
				 session.setAttribute("div", "show");
				 session.setAttribute("currentRole","Guest"); 
				 List<String> books = dao.searchBooks();
					session.setAttribute("searchBooks", books);
					request.setAttribute("div", "show");
					getServletConfig().getServletContext().getRequestDispatcher("/LibraryHome.jsp").forward(request,response);
			     
			}
			else
			{
				session.setAttribute("div", "show");

				String user_name = request.getParameter("user_name");
				String password = request.getParameter("password");
				
				String user_role= dao.validateUser(user_name,password);
	 			
				if (user_role==null)
				{
					request.getRequestDispatcher("login.jsp").include(request, response);  
					out.print("<br><center><h4><span style='background-color:white'><font color='red'>Error: Could not login!</font><span></h4></center>");  

				}
				else if (user_role.equals("Invalid"))
				{
					 request.getRequestDispatcher("login.jsp").include(request, response);  
					 out.print("<br><center><h4><span style='background-color:white'><font color='red'>Please provide valid Username and Password!</font><span></h4></center>");  
				}
				else
				{
					session.setAttribute("currentRole",user_role); 
				    session.setAttribute("currentUserName",user_name);
				    List<String> books = dao.searchBooks();
					session.setAttribute("searchBooks", books);
					request.setAttribute("div", "show");
					getServletConfig().getServletContext().getRequestDispatcher("/LibraryHome.jsp").forward(request,response);
			     
				}
			}
		}
		 catch (Exception e)
		 {
			 System.out.println(e);
			 request.getRequestDispatcher("login.jsp").include(request, response);  
			 out.print("<br><center><h4><span style='background-color:white'><font color='red'>Error: Could not login!</font><span></h4></center>");  

		 }
			
		 
	}
	
}
