package com.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.bean.IssueBean;
import com.library.dao.LibraryDAO;

/**
 * Servlet implementation class ReturnProcess
 */
@WebServlet("/ReturnProcess")
public class ReturnProcess extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out=response.getWriter();

		String user_id = request.getParameter("user_id");
		String book_id = request.getParameter("book_id");
		
		IssueBean bean=new IssueBean();
	
		 bean.setUserId(user_id);
		 bean.setBookId(book_id);
		 
		 LibraryDAO dao=new LibraryDAO();
	  
		 String returned=dao.returnBook(bean);
		 HttpSession session = request.getSession();	    
			session.setAttribute("div", "hide");

		 request.getRequestDispatcher("LibraryHome.jsp").include(request, response);  

		 if (returned==null)
		 {
			 out.print("<br><center><h4><font color='red'>Error: Could not fetch data from database</font></h4></center>"); 
		 }
		 else if(returned.equals("Returned"))
		 {
			 out.print("<br><center><font color='red'>Return Marked!</center>");  
		 }
	}
}
