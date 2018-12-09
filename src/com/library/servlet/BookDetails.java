package com.library.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.bean.BookBean;
import com.library.dao.LibraryDAO;

/**
 * Servlet implementation class BookDetails
 */
@WebServlet("/BookDetails")
public class BookDetails extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String book_name=request.getParameter("book_name");

		LibraryDAO dao=new LibraryDAO();
		
		PrintWriter out=response.getWriter();

		BookBean bean = dao.bookDetails(book_name);
		HttpSession session = request.getSession();	    
		session.setAttribute("div", "hide");

		if (bean==null)
		{
			 request.getRequestDispatcher("LibraryHome.jsp").include(request, response);  
			 out.print("<br><center><h4><font color='red'>Error: Could not fetch data from database</font></h4></center>"); 
	
		}
		else
		{
			request.setAttribute("book", bean);
			getServletConfig().getServletContext().getRequestDispatcher("/BookDetails.jsp").forward(request,response);
		}
	}
}
